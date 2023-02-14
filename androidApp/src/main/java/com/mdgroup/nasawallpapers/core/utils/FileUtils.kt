package com.mdgroup.nasawallpapers.core.utils

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.Base64
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.core.content.FileProvider
import com.mdgroup.nasawallpapers.R
import java.io.*
import java.net.URL
import java.util.*


object FileUtils {

    private const val maxSize = 1024
    private const val minSize = 640

    fun saveFileUsingMediaStore(context: Context, url: String, fileName: String): Uri? {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val contentValues = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
                put(MediaStore.MediaColumns.MIME_TYPE, getMimeType(url))
                put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS)
            }
            val resolver = context.contentResolver
            val uri = resolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues)
            if (uri != null) {
                URL(url).openStream().use { input ->
                    resolver.openOutputStream(uri).use { output ->
                        output?.let {
                            input.copyTo(it, DEFAULT_BUFFER_SIZE)
                        }
                    }
                }
            }
            return uri
        } else {
            val target = File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                fileName
            )
            URL(url).openStream().use { input ->
                FileOutputStream(target).use { output ->
                    input.copyTo(output)
                }
            }
            return FileProvider.getUriForFile(
                context,
                "${context.packageName}.provider",
                target
            )
        }
    }

    @Throws(IOException::class)
    fun saveFilesToPublic(context: Context, list: List<String>) {
        // For Android 11
        val albumName = context.getString(R.string.app_name)
        val resolver = context.contentResolver
        var outStream: OutputStream

        // For Android lower 11
        val folder = AndroidFileManager.getAppropriateDirectory(
            context,
            Environment.DIRECTORY_PICTURES,
            AndroidFileManager.DIR_EXTERNAL_PUBLIC
        )
        if (!folder.exists()) folder.mkdirs()

        list.forEach {
            FileUtils.createFileFromUri(context, Uri.parse(it))?.let { file ->
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    val mime = getMimeType(file.path)

                    val directory: String
                    val contentUri: Uri

                    if (mime == "video/mp4") {
                        directory = "${Environment.DIRECTORY_MOVIES}${File.separator}${albumName}"
                        contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                    } else {
                        directory = "${Environment.DIRECTORY_PICTURES}${File.separator}${albumName}"
                        contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    }

                    val contentValues = ContentValues().apply {
                        put(MediaStore.Images.Media.DISPLAY_NAME, file.name)
                        put(MediaStore.Images.Media.MIME_TYPE, mime)
                        put(MediaStore.MediaColumns.RELATIVE_PATH, directory)
                    }

                    resolver.run {
                        val uri = resolver.insert(contentUri, contentValues) ?: return
                        outStream = openOutputStream(uri) ?: return
                    }

                    outStream.use { os -> file.inputStream().copyTo(os) }
                } else {
                    val newFile = File(folder, file.name)
                    if (newFile.exists()) newFile.delete()
                    file.copyTo(newFile)
                }
            }
        }
    }

    private fun getMimeType(url: String?): String? {
        var type: String? = null
        val extension = MimeTypeMap.getFileExtensionFromUrl(url)
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
        }
        return type
    }

    fun moveFileToLocal(inputStream: InputStream, ext: String, context: Context): File? {
        return try {
            val file = getOutputMediaFile(ext, context)
            inputStream.use { input ->
                file?.outputStream().use { fileOut ->
                    input.copyTo(fileOut!!)
                }
            }
            file
        } catch (e: OutOfMemoryError) {
            null
        }
    }

    private fun getOutputMediaFile(ext: String, context: Context): File? {
        val mediaStorageDir = File(context.filesDir, "Photo")

        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null
            }
        }

        return saveFileAtStorage(mediaStorageDir, ext)
    }

    private fun saveFileAtStorage(mediaStorageDir: File, ext: String): File {

        val timeStamp = Calendar.getInstance().timeInMillis.toString()
        val mediaFile: File
        val newFile = "Auto_$timeStamp.$ext"
        mediaFile = File(mediaStorageDir.path + File.separator + newFile)
        return mediaFile
    }

    private fun getResizedBitmap(bm: Bitmap, path: String): Bitmap? {
        try {
            val width = bm.width
            val height = bm.height
            val newWidth: Int
            val newHeight: Int

            when {
                width > height -> {
                    newWidth = maxSize
                    newHeight = maxSize * height / width
                }
                width < height -> {
                    newHeight = maxSize
                    newWidth = maxSize * width / height
                }
                else -> {
                    newWidth = minSize
                    newHeight = minSize
                }
            }

            val scaleWidth = newWidth.toFloat() / width
            val scaleHeight = newHeight.toFloat() / height
            val matrix = Matrix()
            matrix.postScale(scaleWidth, scaleHeight)
            matrix.postRotate(getRotateFactor(path).toFloat())

            // "RECREATE" THE NEW BITMAP
            val resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false
            )
            bm.recycle()
            return resizedBitmap
        } catch (e: Exception) {
            return null
        }
    }

    private fun getRotateFactor(path: String): Int {
        var rotate = 0
        val exif = ExifInterface(path)
        val orientation = exif.getAttributeInt(
            ExifInterface.TAG_ORIENTATION,
            ExifInterface.ORIENTATION_NORMAL
        )
        when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_270 -> rotate = 270
            ExifInterface.ORIENTATION_ROTATE_180 -> rotate = 180
            ExifInterface.ORIENTATION_ROTATE_90 -> rotate = 90
        }

        return rotate
    }

    fun fileToString(file: File): String? {
        try {
            var bm = BitmapFactory.decodeFile(file.path)
            if (bm.height > maxSize || bm.width > maxSize) {
                getResizedBitmap(bm, file.path)?.let { resizedBitmap ->
                    bm = resizedBitmap
                } ?: kotlin.run {
                    return null
                }
            }
            val bao = ByteArrayOutputStream()
            bm.compress(Bitmap.CompressFormat.JPEG, 50, bao)
            val ba = bao.toByteArray()
            return Base64.encodeToString(ba, Base64.DEFAULT)
        } catch (e: Exception) {
            return null
        }
    }

    fun removeTempFile(file: File?) {
        file?.delete()
    }

    @Throws(IOException::class)
    fun createFileFromUri(context: Context, selectedImage: Uri): File? {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        val file = File(selectedImage.toString())
        return if (file.exists()) {
            file
        } else {
            val imageStream = context.contentResolver.openInputStream(selectedImage)
            imageStream.use { stream ->
                file.createNewFile()
                file.outputStream().use { fileOut ->
                    stream?.copyTo(fileOut)
                }
                file
            }
        }
    }

    @Throws(IOException::class)
    fun bitmapFromUri(context: Context, uri: Uri): Bitmap? {
        var inputStream: InputStream? = null
        return try {
            inputStream = context.contentResolver.openInputStream(uri)
            BitmapFactory.decodeStream(inputStream)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            null
        } finally {
            inputStream?.close()
        }
    }

    //    fun bitmapFromUri(context: Context, uri: Uri): Bitmap? {
    //        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
    //            ImageDecoder.decodeBitmap(ImageDecoder.createSource(context.contentResolver, uri))
    //        } else {
    //            MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
    //        }
    //    }

    fun saveFileToLocal(context: Context, path: Uri, outOfMemoryError: String): File? {
        context.contentResolver?.let { contentResolver ->
            val inputStream = contentResolver.openInputStream(path)
            inputStream?.let {
                getMimeType(path.toString())?.let { memeType ->
                    return moveFileToLocal(
                        inputStream,
                        memeType, context.applicationContext
                    )
                }
            } ?: run {
                Toast.makeText(
                    context,
                    outOfMemoryError,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        return null
    }
}