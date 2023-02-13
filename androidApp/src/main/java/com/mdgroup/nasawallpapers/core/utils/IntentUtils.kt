package com.mdgroup.nasawallpapers.core.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.FileProvider
import com.mdgroup.nasawallpapers.R

object IntentUtils {

    fun sendPhotos(context: Context, uri: Uri) {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND_MULTIPLE
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Here are some files.")
        sendIntent.type = "image/jpeg"

//        val files = ArrayList<Uri>()
//        list.forEach {
//            FileUtils.createFileFromUri(context, Uri.parse(it))?.let { file ->
//                files.add(
//                    FileProvider.getUriForFile(
//                        context,
//                        "${context.packageName}.provider",
//                        file
//                    )
//                )
//            }
//        }
        sendIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, arrayListOf(uri))

        val shareIntent = Intent.createChooser(sendIntent, null)
        context.startActivity(shareIntent)
    }

    fun sendVideo(context: Context, video: String) {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Here are some video.")
        sendIntent.type = "video/3gp"

        FileUtils.createFileFromUri(context, Uri.parse(video))?.let { file ->
            val uri = FileProvider.getUriForFile(
                context,
                "${context.packageName}.provider",
                file
            )
            sendIntent.putExtra(Intent.EXTRA_STREAM, uri)
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        context.startActivity(shareIntent)
    }

    @Throws(ActivityNotFoundException::class)
    fun openEmailActivity(context: Context?, email: String, subject: String?, body: String?) {
        val mailto = "mailto:$email?subject=${Uri.encode(subject)}&body=${Uri.encode(body)}"
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse(mailto)
        context?.startActivity(Intent.createChooser(intent, context.getString(R.string.send_mail)))
    }
}