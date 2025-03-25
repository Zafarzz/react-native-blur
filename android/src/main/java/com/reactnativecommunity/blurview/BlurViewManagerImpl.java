package com.reactnativecommunity.blurview;

import android.view.View;
import com.facebook.react.uimanager.ThemedReactContext;

import eightbitlab.com.blurview.BlurView;

import java.util.Objects;
import javax.annotation.Nonnull;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.BitmapDrawable;

@SuppressWarnings("unused")
class BlurViewManagerImpl {

  public static final String REACT_CLASS = "AndroidBlurView";

  public static final int defaultRadius = 10;
  public static final int defaultSampling = 10;

  public static @Nonnull BlurView createViewInstance(@Nonnull ThemedReactContext ctx) {
    BlurView blurView = new BlurView(ctx);
    View decorView = Objects
      .requireNonNull(ctx.getCurrentActivity())
      .getWindow()
      .getDecorView();
    Drawable windowBackground = decorView.getBackground();
    Bitmap bitmap = Bitmap.createBitmap(
      windowBackground.getIntrinsicWidth(),
      windowBackground.getIntrinsicHeight(),
      Bitmap.Config.ARGB_8888
    );
    Canvas canvas = new Canvas(bitmap);
    windowBackground.draw(canvas);
    blurView
      .setupWith(decorView.findViewById(android.R.id.content))
      .setFrameClearDrawable(new BitmapDrawable(ctx.getResources(), bitmap))
      .setBlurRadius(defaultRadius);
    return blurView;
  }

  public static void setRadius(BlurView view, int radius) {
    view.setBlurRadius(radius);
    view.invalidate();
  }

  public static void setColor(BlurView view, int color) {
    view.setOverlayColor(color);
    view.invalidate();
  }

  public static void setDownsampleFactor(BlurView view, int factor) {}

  public static void setAutoUpdate(BlurView view, boolean autoUpdate) {
    view.setBlurAutoUpdate(autoUpdate);
    view.invalidate();
  }

  public static void setBlurEnabled(BlurView view, boolean enabled) {
    view.setBlurEnabled(enabled);
  }
}
