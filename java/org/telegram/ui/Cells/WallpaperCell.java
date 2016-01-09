/*
 * This is the source code of Telegram for Android v. 3.x.x.
 * It is licensed under GNU GPL v. 2 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Nikolai Kudashov, 2013-2015.
 */

package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.atsoft.telegram.kui.KharTheme;
import com.atsoft.telegram.kui.ThemeCenter;

import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLoader;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.LayoutHelper;

public class WallpaperCell extends FrameLayout {

    KharTheme theme = ThemeCenter.get().getTheme();

    private BackupImageView imageView;
    private View selectionView;
    private ImageView imageView2;

    public WallpaperCell(Context context) {
        super(context);

        imageView = new BackupImageView(context);
        addView(imageView, LayoutHelper.createFrame(100, 100, Gravity.LEFT | Gravity.BOTTOM));

        imageView2 = new ImageView(context);
        imageView2.setImageResource(theme.WallpaperCell_imageView2_img_R);
        imageView2.setScaleType(ImageView.ScaleType.CENTER);
        addView(imageView2, LayoutHelper.createFrame(100, 100, Gravity.LEFT | Gravity.BOTTOM));

        selectionView = new View(context);
        selectionView.setBackgroundResource(theme.WallpaperCell_selectionView_R);
        addView(selectionView, LayoutHelper.createFrame(100, 102));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(100), MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(102), MeasureSpec.EXACTLY));
    }

    public void setWallpaper(TLRPC.WallPaper wallpaper, int selectedBackground) {
        if (wallpaper == null) {
            imageView.setVisibility(INVISIBLE);
            imageView2.setVisibility(VISIBLE);
            selectionView.setVisibility(selectedBackground == -1 ? View.VISIBLE : INVISIBLE);
            imageView2.setBackgroundColor(selectedBackground == -1 || selectedBackground == 1000001 ? theme.WallpaperCell_imageView2_bg_color_1 : theme.WallpaperCell_imageView2_bg_color_2);
        } else {
            imageView.setVisibility(VISIBLE);
            imageView2.setVisibility(INVISIBLE);
            selectionView.setVisibility(selectedBackground == wallpaper.id ? View.VISIBLE : INVISIBLE);

            if (wallpaper instanceof TLRPC.TL_wallPaperSolid) {
                imageView.setImageBitmap(null);
                imageView.setBackgroundColor(theme.WallpaperCell_imageView_bg_color | wallpaper.bg_color);
            } else {
                TLRPC.PhotoSize size = FileLoader.getClosestPhotoSizeWithSize(wallpaper.sizes, AndroidUtilities.dp(100));
                if (size != null && size.location != null) {
                    imageView.setImage(size.location, "100_100", (Drawable) null);
                }
                imageView.setBackgroundColor(theme.WallpaperCell_imageView_bg_color_2);
            }
        }
    }
}
