package com.ski.box.utils;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by tom on 2020/8/15.
 */
public class MyCustomTarage extends CustomTarget<Drawable> {
    private GifListener mGifListener;
    private ImageView mImageView;

    public MyCustomTarage(ImageView imageView, GifListener gifListener) {
        this.mImageView = imageView;
        this.mGifListener = gifListener;
    }

    @Override
    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
        if (resource instanceof GifDrawable) {
            GifDrawable gifDrawable = (GifDrawable) resource;
            gifDrawable.setLoopCount(1);
            mImageView.setImageDrawable(resource);

            try {
                Field gifStateField = GifDrawable.class.getDeclaredField("state");
                gifStateField.setAccessible(true);
                Class gifStateClass = Class.forName("com.bumptech.glide.load.resource.gif.GifDrawable$GifState");
                Field gifFrameLoaderField = gifStateClass.getDeclaredField("frameLoader");
                gifFrameLoaderField.setAccessible(true);
                Class gifFrameLoaderClass = Class.forName("com.bumptech.glide.load.resource.gif.GifFrameLoader");
                Field gifDecoderField = gifFrameLoaderClass.getDeclaredField("gifDecoder");
                gifDecoderField.setAccessible(true);
                Class gifDecoderClass = Class.forName("com.bumptech.glide.gifdecoder.GifDecoder");
                Object gifDecoder = gifDecoderField.get(gifFrameLoaderField.get(gifStateField.get(resource)));
                Method getDelayMethod = gifDecoderClass.getDeclaredMethod("getDelay", int.class);
                getDelayMethod.setAccessible(true);

                //获得总帧数
                int count = gifDrawable.getFrameCount();
                int delay = 0;
                for (int i = 0; i < count; i++) {
                    //计算每一帧所需要的时间进行累加
                    delay += (int) getDelayMethod.invoke(gifDecoder, i);
                }
                mImageView.postDelayed(() -> {
                    if (mGifListener != null) {
                        mGifListener.gifPlayComplete(mImageView);
                    }
                }, delay);

                gifDrawable.start();
            } catch (Exception e) {
                e.printStackTrace();
                mImageView.postDelayed(() -> {
                    if (mGifListener != null) {
                        mGifListener.gifPlayComplete(mImageView);
                    }
                }, 0);

            }
        }
    }

    @Override
    public void onLoadCleared(@Nullable Drawable placeholder) {

    }

    /**
     * Gif播放完毕回调
     */
    public interface GifListener {
        void gifPlayComplete(ImageView mImageView);
    }

}
