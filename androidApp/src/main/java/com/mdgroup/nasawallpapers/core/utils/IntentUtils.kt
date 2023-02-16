package com.mdgroup.nasawallpapers.core.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import com.mdgroup.nasawallpapers.BuildConfig
import com.mdgroup.nasawallpapers.R

object IntentUtils {

    fun sendPhoto(context: Context, uri: Uri) {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND_MULTIPLE
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Here are some files.")
        sendIntent.type = "image/jpeg"
        sendIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, arrayListOf(uri))

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

    fun shareApp(context: Context, text: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, "$text\nhttps://play.google.com/store/apps/details?id=${context.packageName}")
        context.startActivity(intent)
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

    fun openUrl(context: Context, url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }
}