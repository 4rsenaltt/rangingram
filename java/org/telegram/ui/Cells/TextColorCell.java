/*
 * This is the source code of Telegram for Android v. 3.x.x.
 * It is licensed under GNU GPL v. 2 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Nikolai Kudashov, 2013-2015.
 */

package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.atsoft.telegram.kui.KharTheme;
import com.atsoft.telegram.kui.ThemeCenter;

import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.ui.Components.LayoutHelper;

public class TextColorCell extends FrameLayout {

    KharTheme theme = ThemeCenter.get().getTheme();

    private TextView textView;
    private boolean needDivider;
    private int currentColor;

    private Drawable colorDrawable;
    private static Paint paint;

    public TextColorCell(Context context) {
        super(context);

        if (paint == null) {
            paint = new Paint();
            paint.setColor(theme.TextColorCell_paint_color);
            paint.setStrokeWidth(1);
        }

        colorDrawable = getResources().getDrawable(theme.TextColorCell_colorDrawable_R);

        textView = new TextView(context);
        textView.setTextColor(theme.TextColorCell_textView_color);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
        textView.setLines(1);
        textView.setMaxLines(1);
        textView.setSingleLine(true);
        textView.setGravity((LocaleController.isRTL ? Gravity.RIGHT : Gravity.LEFT) | Gravity.CENTER_VERTICAL);
        addView(textView, LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT, LayoutHelper.MATCH_PARENT, (LocaleController.isRTL ? Gravity.RIGHT : Gravity.LEFT) | Gravity.TOP, 17, 0, 17, 0));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48) + (needDivider ? 1 : 0), MeasureSpec.EXACTLY));
    }

    public void setTextAndColor(String text, int color, boolean divider) {
        textView.setText(text);
        needDivider = divider;
        currentColor = color;
        colorDrawable.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.MULTIPLY));
        setWillNotDraw(!needDivider && currentColor == 0);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (needDivider) {
            canvas.drawLine(getPaddingLeft(), getHeight() - 1, getWidth() - getPaddingRight(), getHeight() - 1, paint);
        }
        if (currentColor != 0 && colorDrawable != null) {
            int x;
            int y = (getMeasuredHeight() - colorDrawable.getMinimumHeight()) / 2;
            if (!LocaleController.isRTL) {
                x = getMeasuredWidth() - colorDrawable.getIntrinsicWidth() - AndroidUtilities.dp(14.5f);
            } else {
                x = AndroidUtilities.dp(14.5f);
            }
            colorDrawable.setBounds(x, y, x + colorDrawable.getIntrinsicWidth(), y + colorDrawable.getIntrinsicHeight());
            colorDrawable.draw(canvas);
        }
    }
}
