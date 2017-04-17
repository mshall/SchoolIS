package com.shall.util.image;

import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.DrawableRes;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

/**
 * Created by elsaidel on 1/19/2017.
 */

public class ImageUtils {
    //-----------------------------------------------------------
    // Load image uri of a specific image
    //-----------------------------------------------------------
    public static Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    //-----------------------------------------------------------
    // Load real pth of a specific image uri
    //-----------------------------------------------------------
    public static String getRealPathFromURI(Context context, Uri uri) {
        Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }

    public static String getImageNameFromImageView(ImageView imageView) {
        return imageView.getContentDescription().toString();
    }

    //-----------------------------------------------------------
    // convert from bitmap to byte array
    //-----------------------------------------------------------
    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    //-----------------------------------------------------------
    // convert from byte array to bitmap
    //-----------------------------------------------------------
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    //-----------------------------------------------------------
    // Get bitmap image from ImageView
    //-----------------------------------------------------------
    public static Bitmap getImageFromImageView(ImageView imageView) {
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        return bitmap;
    }

    //-----------------------------------------------------------
    // Add glow to an image
    //-----------------------------------------------------------
    public static Bitmap addImageGlow(Context context, int drawableSource) {
        // An added margin to the initial image
        int margin = 24;
        int halfMargin = margin / 2;

        // the glow radius
        int glowRadius = 20;

        // the glow color
        int glowColor = Color.rgb(0, 192, 255);

        // The original image to use
        Bitmap src = BitmapFactory.decodeResource(context.getResources(), drawableSource);

        // extract the alpha from the source image
        Bitmap alpha = src.extractAlpha();

        // The output bitmap (with the icon + glow)
        Bitmap bmp = Bitmap.createBitmap(src.getWidth() + margin,
                src.getHeight() + margin, Bitmap.Config.ARGB_8888);

        // The canvas to paint on the image
        Canvas canvas = new Canvas(bmp);

        Paint paint = new Paint();
        paint.setColor(glowColor);

        // outer glow
        paint.setMaskFilter(new BlurMaskFilter(glowRadius, BlurMaskFilter.Blur.OUTER));
        canvas.drawBitmap(alpha, halfMargin, halfMargin, paint);

        // original icon
        canvas.drawBitmap(src, halfMargin, halfMargin, null);

        return bmp;
    }

    //-----------------------------------------------------------
    // Get circular bitmap from drawable
    //-----------------------------------------------------------
    public static RoundedBitmapDrawable getRoundedBitmap(Context context, @DrawableRes int id) {
        Resources res = context.getResources();
        Bitmap src = BitmapFactory.decodeResource(res, id);

        int size = Math.max(src.getHeight(), src.getWidth());
        RoundedBitmapDrawable dr =
                RoundedBitmapDrawableFactory.create(res, src);
        dr.setCornerRadius(size / 8.0f);
        return dr;
    }

    //-----------------------------------------------------------
    // Get drawable from resource id
    //-----------------------------------------------------------
    public static Drawable getDrawable(Context context, int resourceId) {
        return context.getResources().getDrawable(resourceId);
    }
}
