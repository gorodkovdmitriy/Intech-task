package com.intech.task.adapter

import android.app.PendingIntent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.PlayerNotificationManager
import com.intech.task.model.data_class.Result
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.lang.Exception

class DescriptionAdapter(val detailSong: Result): PlayerNotificationManager.MediaDescriptionAdapter {
    override fun createCurrentContentIntent(player: Player): PendingIntent? {
        return null
    }

    override fun getCurrentContentText(player: Player): String? {
        return detailSong.artistName
    }

    override fun getCurrentContentTitle(player: Player): String {
        return detailSong.trackName
    }

    override fun getCurrentLargeIcon(player: Player,
                                     callback: PlayerNotificationManager.BitmapCallback): Bitmap? {

        var largeIcon: Bitmap? = null

        Picasso.get().load(detailSong.artworkUrl100).into(object: Target {
            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {}

            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                largeIcon = bitmap
            }
        })

        return largeIcon
    }
}