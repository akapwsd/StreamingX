package com.example.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

public class AgoraSizeUtils
{

    private final String TAG = "SizeUtils";

    private static AgoraSizeUtils sizeUtils;

    public static AgoraSizeUtils getInstance() {
        if (sizeUtils == null) {
            synchronized (AgoraSizeUtils.class) {
                if (sizeUtils == null) {
                    sizeUtils = new AgoraSizeUtils();
                }
            }
        }
        return sizeUtils;
    }

    public AgoraSizeUtils() {
    }

    public int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    public int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    public float getScreenDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    public int dip2px(Context context, float px) {
        final float scale = getScreenDensity(context);
        return (int) (px * scale + 0.5);
    }
    public int getStatusHeight(Context context) {
        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

    public Bitmap snapShotWithStatusBar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        int width = getScreenWidth(activity);
        int height = getScreenHeight(activity);
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bmp, 0, 0, width, height);
        view.destroyDrawingCache();
        return bp;
    }

    public Bitmap snapShotWithoutStatusBar(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bmp = view.getDrawingCache();
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        int width = getScreenWidth(activity);
        int height = getScreenHeight(activity);
        Bitmap bp = null;
        bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height
                - statusBarHeight);
        view.destroyDrawingCache();
        return bp;
    }

    public ImageView calculateImageView(int imgWidth, int imgHeight, int maxWidth, int maxHeight, ImageView imageView) {
        if (imgWidth <= 0 || imgHeight <= 0 || maxWidth <= 0 || maxHeight <= 0) return imageView;
        if (imgWidth < maxWidth && imgHeight < maxHeight) {
            ViewGroup.LayoutParams params = imageView.getLayoutParams();
            params.width = imgWidth;
            params.height = imgHeight;
            imageView.setLayoutParams(params);
            return imageView;
        }
        if (imgWidth > imgHeight) {
            //int calculateWidth = 0;
            //int calculateHeight = 0;
            if (imgWidth > maxWidth) {
                imgHeight = maxWidth * imgHeight / imgWidth;
                imgWidth = maxWidth;
                if (imgHeight > maxHeight) {
                    imgWidth = maxHeight * imgWidth / imgHeight;
                    imgHeight = maxHeight;
                }
            } else {
                if (imgHeight > maxHeight) {
                    imgWidth = maxHeight * imgWidth / imgHeight;
                    imgHeight = maxHeight;
                }
            }
            ViewGroup.LayoutParams params = imageView.getLayoutParams();
            params.width = imgWidth;
            params.height = imgHeight;
            imageView.setLayoutParams(params);
            return imageView;
        } else if (imgWidth < imgHeight) {
            if (imgHeight > maxHeight) {
                imgWidth = maxHeight * imgWidth / imgHeight;
                imgHeight = maxHeight;
            }
            ViewGroup.LayoutParams params = imageView.getLayoutParams();
            params.width = imgWidth;
            params.height = imgHeight;
            imageView.setLayoutParams(params);
            return imageView;
        } else {
            if (imgHeight > maxHeight) {
                imgHeight = maxHeight;
                imgWidth = imgHeight;
            }
            ViewGroup.LayoutParams params = imageView.getLayoutParams();
            params.width = imgWidth;
            params.height = imgHeight;
            imageView.setLayoutParams(params);
            return imageView;
        }
    }
}
