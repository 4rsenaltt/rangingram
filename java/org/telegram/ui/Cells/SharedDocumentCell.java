/*
 * This is the source code of Telegram for Android v. 2.0.x.
 * It is licensed under GNU GPL v. 2 or later.
 * You should have received a copy of the license in this archive (see LICENSE).
 *
 * Copyright Nikolai Kudashov, 2013-2015.
 */

package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.atsoft.telegram.kui.KharTheme;
import com.atsoft.telegram.kui.ThemeCenter;

import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CheckBox;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LineProgressView;

import java.io.File;
import java.util.Date;

public class SharedDocumentCell extends FrameLayout implements MediaController.FileDownloadProgressListener {

    private KharTheme theme = ThemeCenter.get().getTheme();

    private ImageView placeholderImabeView;
    private BackupImageView thumbImageView;
    private TextView nameTextView;
    private TextView extTextView;
    private TextView dateTextView;
    private ImageView statusImageView;
    private LineProgressView progressView;
    private CheckBox checkBox;

    private boolean needDivider;

    private static Paint paint;

    private int TAG;

    private MessageObject message;
    private boolean loading;
    private boolean loaded;

    private int icons[] = {
            theme.SharedDocumentCell_icons_1_R,
            theme.SharedDocumentCell_icons_2_R,
            theme.SharedDocumentCell_icons_3_R,
            theme.SharedDocumentCell_icons_4_R
    };

    public SharedDocumentCell(Context context) {
        super(context);

        if (paint == null) {
            paint = new Paint();
            paint.setColor(theme.SharedDocumentCell_paint_color);
            paint.setStrokeWidth(1);
        }

        TAG = MediaController.getInstance().generateObserverTag();

        placeholderImabeView = new ImageView(context);
        addView(placeholderImabeView, LayoutHelper.createFrame(40, 40, (LocaleController.isRTL ? Gravity.RIGHT : Gravity.LEFT) | Gravity.TOP, LocaleController.isRTL ? 0 : 12, 8, LocaleController.isRTL ? 12 : 0, 0));

        extTextView = new TextView(context);
        extTextView.setTextColor(theme.SharedDocumentCell_extTextView_color);
        extTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
        extTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        extTextView.setLines(1);
        extTextView.setMaxLines(1);
        extTextView.setSingleLine(true);
        extTextView.setGravity(Gravity.CENTER);
        extTextView.setEllipsize(TextUtils.TruncateAt.END);
        addView(extTextView, LayoutHelper.createFrame(32, LayoutHelper.WRAP_CONTENT, (LocaleController.isRTL ? Gravity.RIGHT : Gravity.LEFT) | Gravity.TOP, LocaleController.isRTL ? 0 : 16, 22, LocaleController.isRTL ? 16 : 0, 0));

        thumbImageView = new BackupImageView(context);
        addView(thumbImageView, LayoutHelper.createFrame(40, 40, (LocaleController.isRTL ? Gravity.RIGHT : Gravity.LEFT) | Gravity.TOP, LocaleController.isRTL ? 0 : 12, 8, LocaleController.isRTL ? 12 : 0, 0));
        thumbImageView.getImageReceiver().setDelegate(new ImageReceiver.ImageReceiverDelegate() {
            @Override
            public void didSetImage(ImageReceiver imageReceiver, boolean set, boolean thumb) {
                extTextView.setVisibility(set ? INVISIBLE : VISIBLE);
                placeholderImabeView.setVisibility(set ? INVISIBLE : VISIBLE);
            }
        });

        nameTextView = new TextView(context);
        nameTextView.setTextColor(theme.SharedDocumentCell_nameTextView_color);
        nameTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
        nameTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
        nameTextView.setLines(1);
        nameTextView.setMaxLines(1);
        nameTextView.setSingleLine(true);
        nameTextView.setEllipsize(TextUtils.TruncateAt.END);
        nameTextView.setGravity((LocaleController.isRTL ? Gravity.RIGHT : Gravity.LEFT) | Gravity.CENTER_VERTICAL);
        addView(nameTextView, LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT, LayoutHelper.WRAP_CONTENT, (LocaleController.isRTL ? Gravity.RIGHT : Gravity.LEFT) | Gravity.TOP, LocaleController.isRTL ? 8 : 72, 5, LocaleController.isRTL ? 72 : 8, 0));

        statusImageView = new ImageView(context);
        statusImageView.setVisibility(INVISIBLE);
        addView(statusImageView, LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT, LayoutHelper.WRAP_CONTENT, (LocaleController.isRTL ? Gravity.RIGHT : Gravity.LEFT) | Gravity.TOP, LocaleController.isRTL ? 8 : 72, 35, LocaleController.isRTL ? 72 : 8, 0));

        dateTextView = new TextView(context);
        dateTextView.setTextColor(theme.SharedDocumentCell_dateTextView_color);
        dateTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);
        dateTextView.setLines(1);
        dateTextView.setMaxLines(1);
        dateTextView.setSingleLine(true);
        dateTextView.setEllipsize(TextUtils.TruncateAt.END);
        dateTextView.setGravity((LocaleController.isRTL ? Gravity.RIGHT : Gravity.LEFT) | Gravity.CENTER_VERTICAL);
        addView(dateTextView, LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT, LayoutHelper.WRAP_CONTENT, (LocaleController.isRTL ? Gravity.RIGHT : Gravity.LEFT) | Gravity.TOP, LocaleController.isRTL ? 8 : 72, 30, LocaleController.isRTL ? 72 : 8, 0));

        progressView = new LineProgressView(context);
        addView(progressView, LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT, 2, (LocaleController.isRTL ? Gravity.RIGHT : Gravity.LEFT) | Gravity.TOP, LocaleController.isRTL ? 0 : 72, 54, LocaleController.isRTL ? 72 : 0, 0));

        checkBox = new CheckBox(context, R.drawable.round_check2);
        checkBox.setVisibility(INVISIBLE);
        addView(checkBox, LayoutHelper.createFrame(22, 22, (LocaleController.isRTL ? Gravity.RIGHT : Gravity.LEFT) | Gravity.TOP, LocaleController.isRTL ? 0 : 34, 30, LocaleController.isRTL ? 34 : 0, 0));
    }

    private int getThumbForNameOrMime(String name, String mime) {
        if (name != null && name.length() != 0) {
            int color = -1;
            if (name.contains(".doc") || name.contains(".txt") || name.contains(".psd")) {
                color = 0;
            } else if (name.contains(".xls") || name.contains(".csv")) {
                color = 1;
            } else if (name.contains(".pdf") || name.contains(".ppt") || name.contains(".key")) {
                color = 2;
            } else if (name.contains(".zip") || name.contains(".rar") || name.contains(".ai") || name.contains(".mp3")  || name.contains(".mov") || name.contains(".avi")) {
                color = 3;
            }
            if (color == -1) {
                int idx;
                String ext = (idx = name.lastIndexOf(".")) == -1 ? "" : name.substring(idx + 1);
                if (ext.length() != 0) {
                    color = ext.charAt(0) % icons.length;
                } else {
                    color = name.charAt(0) % icons.length;
                }
            }
            return icons[color];
        }
        return icons[0];
    }

    public void setTextAndValueAndTypeAndThumb(String text, String value, String type, String thumb, int resId) {
        nameTextView.setText(text);
        dateTextView.setText(value);
        if (type != null) {
            extTextView.setVisibility(VISIBLE);
            extTextView.setText(type);
        } else {
            extTextView.setVisibility(INVISIBLE);
        }
        if (resId == 0) {
            placeholderImabeView.setImageResource(getThumbForNameOrMime(text, type));
            placeholderImabeView.setVisibility(VISIBLE);
        } else {
            placeholderImabeView.setVisibility(INVISIBLE);
        }
        if (thumb != null || resId != 0) {
            if (thumb != null) {
                thumbImageView.setImage(thumb, "40_40", null);
            } else  {
                thumbImageView.setImageResource(resId);
            }
            thumbImageView.setVisibility(VISIBLE);
        } else {
            thumbImageView.setVisibility(INVISIBLE);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        MediaController.getInstance().removeLoadingFileObserver(this);
    }

    public void setChecked(boolean checked, boolean animated) {
        if (checkBox.getVisibility() != VISIBLE) {
            checkBox.setVisibility(VISIBLE);
        }
        checkBox.setChecked(checked, animated);
    }

    public void setDocument(MessageObject document, boolean divider) {
        needDivider = divider;
        message = document;
        loaded = false;
        loading = false;

        if (document != null && document.messageOwner.media != null && document.messageOwner.media.document != null) {
            int idx;
            String name = FileLoader.getDocumentFileName(document.messageOwner.media.document);
            placeholderImabeView.setVisibility(VISIBLE);
            extTextView.setVisibility(VISIBLE);
            placeholderImabeView.setImageResource(getThumbForNameOrMime(name, document.messageOwner.media.document.mime_type));
            nameTextView.setText(name);
            extTextView.setText((idx = name.lastIndexOf(".")) == -1 ? "" : name.substring(idx + 1).toLowerCase());
            if (document.messageOwner.media.document.thumb instanceof TLRPC.TL_photoSizeEmpty || document.messageOwner.media.document.thumb == null) {
                thumbImageView.setVisibility(INVISIBLE);
                thumbImageView.setImageBitmap(null);
            } else {
                thumbImageView.setVisibility(VISIBLE);
                thumbImageView.setImage(document.messageOwner.media.document.thumb.location, "40_40", (Drawable) null);
            }
            long date = (long) document.messageOwner.date * 1000;
            dateTextView.setText(String.format("%s, %s", AndroidUtilities.formatFileSize(document.messageOwner.media.document.size), LocaleController.formatString("formatDateAtTime", R.string.formatDateAtTime, LocaleController.formatterYear.format(new Date(date)), LocaleController.formatterDay.format(new Date(date)))));
        } else {
            nameTextView.setText("");
            extTextView.setText("");
            dateTextView.setText("");
            placeholderImabeView.setVisibility(VISIBLE);
            extTextView.setVisibility(VISIBLE);
            thumbImageView.setVisibility(INVISIBLE);
            thumbImageView.setImageBitmap(null);
        }

        setWillNotDraw(!needDivider);
        progressView.setProgress(0, false);
        updateFileExistIcon();
    }

    public void updateFileExistIcon() {
        if (message != null && message.messageOwner.media != null) {
            String fileName = null;
            File cacheFile;
            if (message.messageOwner.attachPath == null || message.messageOwner.attachPath.length() == 0 || !(new File(message.messageOwner.attachPath).exists())) {
                cacheFile = FileLoader.getPathToMessage(message.messageOwner);
                if (!cacheFile.exists()) {
                    fileName = FileLoader.getAttachFileName(message.messageOwner.media.document);
                }
            }
            loaded = false;
            if (fileName == null) {
                statusImageView.setVisibility(INVISIBLE);
                dateTextView.setPadding(0, 0, 0, 0);
                loading = false;
                loaded = true;
                MediaController.getInstance().removeLoadingFileObserver(this);
            } else {
                MediaController.getInstance().addLoadingFileObserver(fileName, this);
                loading = FileLoader.getInstance().isLoadingFile(fileName);
                statusImageView.setVisibility(VISIBLE);
                statusImageView.setImageResource(loading ? theme.SharedDocumentCell_statusImageView_img_1_R : theme.SharedDocumentCell_statusImageView_img_2_R);
                dateTextView.setPadding(LocaleController.isRTL ? 0 : AndroidUtilities.dp(14), 0, LocaleController.isRTL ? AndroidUtilities.dp(14) : 0, 0);
                if (loading) {
                    progressView.setVisibility(VISIBLE);
                    Float progress = ImageLoader.getInstance().getFileProgress(fileName);
                    if (progress == null) {
                        progress = 0.0f;
                    }
                    progressView.setProgress(progress, false);
                } else {
                    progressView.setVisibility(INVISIBLE);
                }
            }
        } else {
            loading = false;
            loaded = true;
            progressView.setVisibility(INVISIBLE);
            progressView.setProgress(0, false);
            statusImageView.setVisibility(INVISIBLE);
            dateTextView.setPadding(0, 0, 0, 0);
            MediaController.getInstance().removeLoadingFileObserver(this);
        }
    }

    public MessageObject getDocument() {
        return message;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public boolean isLoading() {
        return loading;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(56) + (needDivider ? 1 : 0), MeasureSpec.EXACTLY));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (needDivider) {
            canvas.drawLine(AndroidUtilities.dp(72), getHeight() - 1, getWidth() - getPaddingRight(), getHeight() - 1, paint);
        }
    }

    @Override
    public void onFailedDownload(String name) {
        updateFileExistIcon();
    }

    @Override
    public void onSuccessDownload(String name) {
        progressView.setProgress(1, true);
        updateFileExistIcon();
    }

    @Override
    public void onProgressDownload(String fileName, float progress) {
        if (progressView.getVisibility() != VISIBLE) {
            updateFileExistIcon();
        }
        progressView.setProgress(progress, true);
    }

    @Override
    public void onProgressUpload(String fileName, float progress, boolean isEncrypted) {

    }

    @Override
    public int getObserverTag() {
        return TAG;
    }
}