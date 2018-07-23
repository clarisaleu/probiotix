package com.angelhack.probiotix.probiotix;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.util.DisplayMetrics;

/**
 * Created by William Zulueta on 7/21/18.
 */

public class Utils
{
//    private static MediaPlayer _mediaPlayer;
    /**
     * SOURCE: https://stackoverflow.com/questions/4605527/converting-pixels-to-dp
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px      A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    public static float convertPixelsToDp(float px, Context context)
    {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return dp;
    }

    public static MediaPlayer playAudioEffect(String audio, Context context)
    {
        return playAudioEffect(findResourceByName(audio, "raw", context), context);
    }

    public static MediaPlayer playAudioEffect(int audio, Context context)
    {
//        if (_mediaPlayer == null)
//        {
//            _mediaPlayer = new MediaPlayer();
//        }
        MediaPlayer mediaPlayer = MediaPlayer.create(context, audio);
        mediaPlayer.start();
        return mediaPlayer;
//        _mediaPlayer.cre
//        return _mediaPlayer;
    }

    public static Bitmap getImage(String image, Context context)
    {
        return getImage(findResourceByName(image, "drawable", context), context);
    }

    public static Bitmap getImage(int image, Context context)
    {
        return ((BitmapDrawable)context.getResources().getDrawable(image)).getBitmap();
    }

    public static int findResourceByName(String name, String resource, Context context)
    {
        return context.getResources().getIdentifier(name, resource, context.getPackageName());
    }
}
