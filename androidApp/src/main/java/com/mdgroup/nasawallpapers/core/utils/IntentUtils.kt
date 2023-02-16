package com.mdgroup.nasawallpapers.core.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.FileProvider
import com.mdgroup.nasawallpapers.BuildConfig
import com.mdgroup.nasawallpapers.R
import com.mdgroup.nasawallpapers.core.platform.Logger

object IntentUtils {

    fun sendPhotos(context: Context, uri: Uri) {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND_MULTIPLE
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Here are some files.")
        sendIntent.type = "image/jpeg"
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

    fun sendEmailToDeveloper(context: Context, email: String) {
        var subject = "${context.resources.getString(R.string.app_name)}: ${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE}) OS: Android"
        if (BuildConfig.DEBUG) subject += " - DEBUG"

        val mailto = "mailto:$email?subject=${Uri.encode(subject)}&body=${Uri.encode("")}"
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse(mailto)
        context.startActivity(Intent.createChooser(intent, context.getString(R.string.send_mail)))
    }

    fun callPhone(context: Context, phone: String) {
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${phone}"))
        context.startActivity(intent)
    }

    fun shareApp(context: Context, text: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, "$text\nhttps://play.google.com/store/apps/details?id=${context.packageName}")
        context.startActivity(intent)
    }

    private fun openMessenger(context: Context, url: String, mass: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.setPackage(mass)
            context.startActivity(intent)
        } catch (e: Exception) {
            Logger.e(e)
            openMarket(context, mass)
        }
    }

    fun openMarket(context: Context, mass: String) {
        try {
            context.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("market://details?id=$mass")
                )
            )
        } catch (anfe: ActivityNotFoundException) {
            context.startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store/apps/details?id=$mass")
                )
            )
        }
    }
}