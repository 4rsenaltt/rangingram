package com.atsoft.telegram.kui.kt;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.NinePatchDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.StateSet;

import com.atsoft.telegram.kui.KharTheme;
import com.atsoft.telegram.kui.wizard.Wiz;
import com.atsoft.telegram.kui.wizard.Wizz;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.telegram.messenger.R;

import java.io.IOException;

/**
 * Created by future on 12/12/2015.
 */
public class KTBuilderEngineLayer extends KTBuilderFileLayer
{
    private KharTheme theme;
    private Context context;
    private Wizz wizzUI;

    public KTBuilderEngineLayer(Context context)
    {
        super();
        this.context = context;
        initInternal();
    }

    private void initInternal()
    {

    }

    protected void setContext(Context context)
    {
        this.context = context;
    }

    protected KharTheme getTheme()
    {
        return theme;
    }

    protected final void initThemeIfNeeded()
    {
        if(theme == null){
            initTheme();
        }
    }
    protected final void initTheme()
    {
        this.theme = new KharTheme();
        theme.main = context.getResources().getColor(R.color.main);

        theme.main_dark = context.getResources().getColor(R.color.main_dark);
        theme.main_light = context.getResources().getColor(R.color.main_light);

        buildFloatingDrawable(theme.main);
        theme.ResourceLoader_backgroundDrawableIn = context.getResources().getDrawable(R.drawable.msg_in);
        theme.ResourceLoader_backgroundDrawableOut = context.getResources().getDrawable(R.drawable.msg_out);
        theme.ResourceLoader_checkDrawable = context.getResources().getDrawable(R.drawable.msg_check);
        theme.ResourceLoader_halfCheckDrawable = context.getResources().getDrawable(R.drawable.msg_halfcheck);

        theme.list_selector_ktt = context.getResources().getDrawable(R.drawable.list_selector_ktt);
        //theme.DialogsActivity_floatingButton_bg = context.getResources().getDrawable(R.drawable.floating_states);


    }

    protected final void applyWiz(Wizz wizz)
    {
        for (int i = 0 ; i < wizz.size() ; i++){
            doWiz(wizz.get(i));
        }
    }



    private void doWiz(Wiz wiz)
    {
        if (wiz == null) return;
        if(wiz.getProperty() == -1) return;
        switch (wiz.getId())
        {
            case  PP_msg_in_color:
                buildBackgroundDrawableIn(wiz.getProperty());
                break;

            case  PP_msg_out_color:
                buildBackgroundDrawableOut(wiz.getProperty());
                break;

            case  PP_first_tick_color:
                buildCheckDrawable(wiz.getProperty());
                break;

            case  PP_second_tick_color:
                buildHalfCheckDrawable(wiz.getProperty());
                break;
            case  PP_msg_text_in_color:
                theme.MessageObject_textPaint_color_in_Color = wiz.getProperty();
                break;
            case  PP_msg_text_out_color:
                theme.MessageObject_textPaint_color_out_Color = wiz.getProperty();
                break;
            case  PP_list_selector_color1:
                buildListSelector(wiz.getProperty(), -1);
                break;
            case  PP_list_selector_color2:
                buildListSelector(-1, wiz.getProperty());
                break;
            case  PP_dialog_bg_color:
                theme.DialogsActivity_listView_bg_color = wiz.getProperty();
                break;
            case  PP_left_drawer_bg_color:
                theme.LaunchActivity_listView_bg_color = wiz.getProperty();
                break;
            case  PP_floating_bg:
                buildFloatingDrawable(wiz.getProperty());
                break;
            case  PP_message_typeface:
                buildMessageTypeface(wiz.getProperty());
                break;
            case  PP_dialog_name_color:
                theme.DialogCell_namePaint_color = wiz.getProperty();
                break;
            case  PP_baseFragment_actionbar_color:
                theme.BaseFragment_actionBar_bg_color = wiz.getProperty();
                theme.updateMain(wiz.getProperty());
                break;
            case  PP_chat_bg_color:
                theme.ChatActivity_contentView_bg_color = wiz.getProperty();
                break;
            case  PP_chat_editText_bg_color:
                theme.ChatActivityEnterView__bg_color = wiz.getProperty();
                theme.ChatActivityEnterView_textFieldContainer_color = wiz.getProperty();
                break;
            case  PP_chat_editText_text_color:
                theme.ChatActivityEnterView__messageEditText_text_color = wiz.getProperty();
                break;
            case  PP_send_ic_color:
                buildSendBitmap(wiz.getProperty());
                break;
            case  PP_drawer_actionCell_text_color:
                theme.DrawerActionCell_textView_color = wiz.getProperty();
                break;


            //
            case  PP_dialog_message_color:
                theme.DialogCell_messagePaint_color = wiz.getProperty();
                break;
            case  PP_dialog_time_color:
                theme.DialogCell_timePaint_color = wiz.getProperty();
                break;
            case  PP_msg_time_in_time_color:
                theme.ChatBaseCell_timePaintIn_color = wiz.getProperty();
                break;
            case  PP_msg_time_out_time_color:
                theme.ChatBaseCell_timePaintOut_color = wiz.getProperty();
                break;

        }
    }

    private void applyWizzFromFile(String name)
    {
        Wizz wizz = buildWizzFromFile(name);
        applyWiz(wizz);
    }

    protected final Wizz buildWizzFromFile(String name)
    {
        Document document = null;
        try
        {
            document = Jsoup.parse(getThemeFile(name), "UTF-8");
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        Wizz wizz = new Wizz();

        Wiz wiz = null;
        Elements elements = document.getAllElements();
        Element element;

        for (int i = 0 ; i < elements.size() ; i++){
            element = elements.get(i);
            String tagName = element.tagName();
            String tagText = element.text();
            if(tagName.equals("wiz")) {

                wiz = new Wiz(Integer.valueOf(element.attr("n")));
                wiz.setProperty(Integer.valueOf(element.attr("p")));
                wizz.add(wiz);

            }

        }

        return wizz;
    }

    protected final void setWizzUI(Wizz wizzUI)
    {
        this.wizzUI = wizzUI;
    }



    protected final String buildThemeData(Wizz wizz)
    {
        String data = "";
        int version = 1;

        data = "<root>"+"\n"+
                "<meta version=\""+version+"\"/>"+"\n";


        for (int i = 0; i < wizz.size() ; i++){
            data = data+"<wiz n=\""+wizz.get(i).getId()+"\"  "+"p=\""+wizz.get(i).getProperty()+"\"/>";

        }

        data = data+"\n"+"</root>";

        return data;
    }

    public Wizz getWizzUI()
    {
        if(wizzUI == null){
            buildWizUI();
        }

        return wizzUI;
    }

    public void buildWizUI()
    {
        wizzUI = new Wizz();
        newWiz(PP_msg_in_color, "رنگ زمینه پیام های ورودی", R.drawable.wiz_msg_in_bg);
        newWiz(PP_msg_out_color, "رنگ زممینه پیام های خروجی", R.drawable.wiz_msg_out_bg);
        newWiz(PP_first_tick_color, "رنگ تیک اول", R.drawable.wiz_tick_color);
        newWiz(PP_second_tick_color, "رنگ تیک دوم", R.drawable.wiz_tick_color);
        newWiz(PP_msg_text_in_color, "رنگ متن پیام های وردی", R.drawable.wiz_msg_in_color);
        newWiz(PP_msg_text_out_color, "رنگ متن پیام های خروجی", R.drawable.wiz_msg_out_color);
        newWiz(PP_list_selector_color2, "رنگ هنگام کلیک روی لیست ها", R.drawable.wiz_dialogs_bg);
        newWiz(PP_dialog_bg_color, "رنگ زمینه صفحه اول(چت ها)", R.drawable.wiz_dialogs_bg);
        newWiz(PP_left_drawer_bg_color, "رنگ منوی کشویی", R.drawable.wiz_drawer_bg);
        newWiz(PP_floating_bg, "رنگ دکمه شناور", R.drawable.wiz_floating_button);
        newWiz(PP_dialog_name_color, "رنگ نام ها در صفحه اصلی", R.drawable.wiz_dialogs_name_color);
        newWiz(PP_baseFragment_actionbar_color, "رنگ اکشن بار", R.drawable.wiz_actionbar);
        newWiz(PP_chat_bg_color, "رنگ زمینه صفحه چت", R.drawable.wiz_chat_bg);
        newWiz(PP_message_typeface,Wiz.Type_ITEMS,  "فونت پیام ها",typefaces, R.drawable.wiz_msg_out_color);
        newWiz(PP_chat_editText_bg_color,"رنگ زمینه محل تایپ پیام", R.drawable.wiz_type_text_bg);
        newWiz(PP_chat_editText_text_color, "رنگ پیام در حال تایپ", R.drawable.wiz_type_text_color);
        newWiz(PP_send_ic_color, "رنگ دکمه ارسال", R.drawable.wiz_send_ic);
        newWiz(PP_drawer_actionCell_text_color, "رنگ متن ها در منوی کشویی",R.drawable.wiz_drawer_text_color);
        newWiz(PP_msg_time_in_time_color, "رنگ تاریخ پیام های ورودی", R.drawable.wiz_ms_time_in);
        newWiz(PP_msg_time_out_time_color, "رنگ تاریخ پیام های خروجی", R.drawable.wiz_msg_time_out);
        newWiz(PP_dialog_message_color, "رنگ پیام ها در صفحه اول", R.drawable.wiz_dialog_msg);
        newWiz(PP_dialog_time_color, "رنگ تاریخ در صفحه اول", R.drawable.wiz_dialog_time);
    }

    private void newWiz(int id, String title, int res)
    {
        Wiz wiz = new Wiz(id);
        wiz.setTitle(title);
        wiz.setImageRes(res);
        wizzUI.add(wiz);
    }

    private void newWiz(int id,int type, String title, String[] items, int res)
    {
        Wiz wiz = new Wiz(id);
        wiz.setTitle(title);
        wiz.setViewType(type);
        wiz.setItems(items);
        wiz.setImageRes(res);
        wizzUI.add(wiz);
    }


    private void buildSendBitmap(int color)
    {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inMutable = true;
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_send, options);

        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        int color_red = Color.red(color);
        int color_green = Color.green(color);
        int color_blue = Color.blue(color);

        for (int x = 0 ; x < w ; x++)
        {
            for (int y = 0 ; y < h ; y++){

                int pixel = bitmap.getPixel(x, y);
                int a = Color.alpha(pixel);
                int r = Color.red(pixel);
                int g = Color.green(pixel);
                int b = Color.blue(pixel);

                if(b > 22)
                {
                    bitmap.setPixel(x, y, Color.argb(a, color_red, color_green, color_blue));
                }


            }
        }

        theme.sendBitmap = bitmap;

    }


    private void buildFloatingDrawable(int color)
    {
        StateListDrawable stateListDrawable = new StateListDrawable();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inMutable = true;
        Bitmap bitmap_pressed = BitmapFactory.decodeResource(context.getResources(), R.drawable.floating_pressed, options);
        int w = bitmap_pressed.getWidth();
        int h = bitmap_pressed.getHeight();

        int color_red = Color.red(color);
        int color_green = Color.green(color);
        int color_blue = Color.blue(color);
        for (int x = 0 ; x < w ; x++)
        {
            for (int y = 0 ; y < h ; y++){

                int pixel = bitmap_pressed.getPixel(x, y);
                int a = Color.alpha(pixel);
                int r = Color.red(pixel);
                int g = Color.green(pixel);
                int b = Color.blue(pixel);

                if(b > 50)
                {
                    bitmap_pressed.setPixel(x, y, Color.argb(a, color_red, color_green, color_blue));
                }


            }
        }

        options = new BitmapFactory.Options();
        options.inMutable = true;
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.floating_pressed, options);
         w = bitmap.getWidth();
         h = bitmap.getHeight();

        for (int x = 0 ; x < w ; x++)
        {
            for (int y = 0 ; y < h ; y++){

                int pixel = bitmap.getPixel(x, y);
                int a = Color.alpha(pixel);
                int r = Color.red(pixel);
                int g = Color.green(pixel);
                int b = Color.blue(pixel);

                if(b > 50)
                {
                    bitmap.setPixel(x, y, Color.argb(a, color_red, color_green, color_blue));
                }


            }
        }


        stateListDrawable.addState(new int[]{android.R.attr.state_pressed, android.R.attr.state_selected}, new BitmapDrawable(context.getResources(), bitmap_pressed));
        stateListDrawable.addState(StateSet.WILD_CARD, new BitmapDrawable(context.getResources(), bitmap));

        theme.DialogsActivity_floatingButton_bg = stateListDrawable;

    }

    public static final String[] typefaces = {"afsaneh", "banoo", "banoo_light", "banoo_thin",
    "gol", "kavir", "phalls_khodkar", "Rezvan", "Rezvan_bold", "sols_2", "yekan", "yekan_bold"};
    private void buildMessageTypeface(int tf)
    {
        Typeface t = Typeface.createFromAsset(context.getAssets(), "fonts/"+typefaces[tf]+".ttf");
        theme.MessageObject_textPaint_typeface = t;
    }

    private void buildBackgroundDrawableIn(int color)
    {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inMutable = true;
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.msg_in, options);

        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        int red = Color.red(color);
        int blue = Color.blue(color);

        for (int x = 0 ; x < w ; x++)
        {
            for (int y = 0 ; y < h ; y++){

                int pixel = bitmap.getPixel(x, y);
                int a = Color.alpha(pixel);
                int r = Color.red(pixel);
                int g = Color.green(pixel);
                int b = Color.blue(pixel);

                if(a>0)
                {
                    bitmap.setPixel(x, y, color);
                }


            }
        }


        NinePatchDrawable drawable = new NinePatchDrawable(bitmap, bitmap.getNinePatchChunk(), new Rect(), null);


        theme.ResourceLoader_backgroundDrawableIn = drawable;
    }

    private void buildBackgroundDrawableOut(int color)
    {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inMutable = true;
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.msg_out, options);
        bitmap.setHasAlpha(true);
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        for (int x = 0 ; x < w ; x++)
        {
            for (int y = 0 ; y < h ; y++){

                int pixel = bitmap.getPixel(x, y);
                int a = Color.alpha(pixel);
                int r = Color.red(pixel);
                int g = Color.green(pixel);
                int b = Color.blue(pixel);

                if(a>0)
                {
                    bitmap.setPixel(x, y, color);
                }


            }
        }


        NinePatchDrawable drawable = new NinePatchDrawable(bitmap, bitmap.getNinePatchChunk(), new Rect(), null);


        theme.ResourceLoader_backgroundDrawableOut = drawable;
    }


    private void buildCheckDrawable(int color)
    {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inMutable = true;
        BitmapDrawable bitmapDrawable = (BitmapDrawable) context.getResources().getDrawable(R.drawable.msg_check);

        Bitmap bitmap = bitmapDrawable.getBitmap().copy(Bitmap.Config.ARGB_8888, true);

        bitmap.setHasAlpha(true);
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        for (int x = 0 ; x < w ; x++)
        {
            for (int y = 0 ; y < h ; y++){

                int pixel = bitmap.getPixel(x, y);
                int a = Color.alpha(pixel);
                int r = Color.red(pixel);
                int g = Color.green(pixel);
                int b = Color.blue(pixel);

                if(a != 0)
                {
                    bitmap.setPixel(x, y, Color.argb(a, Color.red(color), Color.green(color), Color.blue(color)));
                }


            }
        }


        BitmapDrawable drawable = new BitmapDrawable(context.getResources(), bitmap);

        theme.ResourceLoader_checkDrawable = drawable;
        buildCheckDrawable2(color);
    }

    private void buildCheckDrawable2(int color)
    {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inMutable = true;
        BitmapDrawable bitmapDrawable = (BitmapDrawable) context.getResources().getDrawable(R.drawable.dialogs_check);

        Bitmap bitmap = bitmapDrawable.getBitmap().copy(Bitmap.Config.ARGB_8888, true);

        bitmap.setHasAlpha(true);
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        for (int x = 0 ; x < w ; x++)
        {
            for (int y = 0 ; y < h ; y++){

                int pixel = bitmap.getPixel(x, y);
                int a = Color.alpha(pixel);
                int r = Color.red(pixel);
                int g = Color.green(pixel);
                int b = Color.blue(pixel);

                if(a != 0)
                {
                    bitmap.setPixel(x, y, Color.argb(a, Color.red(color), Color.green(color), Color.blue(color)));
                }


            }
        }


        BitmapDrawable drawable = new BitmapDrawable(context.getResources(), bitmap);

        theme.DialogCell_checkDrawable = drawable;
    }


    private void buildHalfCheckDrawable(int color)
    {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inMutable = true;
        BitmapDrawable bitmapDrawable = (BitmapDrawable) context.getResources().getDrawable(R.drawable.msg_halfcheck);

        Bitmap bitmap = bitmapDrawable.getBitmap().copy(Bitmap.Config.ARGB_8888, true);
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        for (int x = 0 ; x < w ; x++)
        {
            for (int y = 0 ; y < h ; y++){

                int pixel = bitmap.getPixel(x, y);
                int a = Color.alpha(pixel);
                int r = Color.red(pixel);
                int g = Color.green(pixel);
                int b = Color.blue(pixel);

                if(g > 150)
                {
                    bitmap.setPixel(x, y, Color.argb(a, Color.red(color), Color.green(color), Color.blue(color)));
                }


            }
        }


        BitmapDrawable drawable = new BitmapDrawable(context.getResources(), bitmap);

        theme.ResourceLoader_halfCheckDrawable = drawable;
        buildHalfCheckDrawable2(color);
    }

    private void buildHalfCheckDrawable2(int color)
    {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inMutable = true;
        BitmapDrawable bitmapDrawable = (BitmapDrawable) context.getResources().getDrawable(R.drawable.dialogs_halfcheck);

        Bitmap bitmap = bitmapDrawable.getBitmap().copy(Bitmap.Config.ARGB_8888, true);
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        for (int x = 0 ; x < w ; x++)
        {
            for (int y = 0 ; y < h ; y++){

                int pixel = bitmap.getPixel(x, y);
                int a = Color.alpha(pixel);
                int r = Color.red(pixel);
                int g = Color.green(pixel);
                int b = Color.blue(pixel);

                if(g > 150)
                {
                    bitmap.setPixel(x, y, Color.argb(a, Color.red(color), Color.green(color), Color.blue(color)));
                }


            }
        }


        BitmapDrawable drawable = new BitmapDrawable(context.getResources(), bitmap);

        theme.DialogCell_halfCheckDrawable = drawable;

    }
    private void buildListSelector(int color1, int color2)
    {
        StateListDrawable stateListDrawable;
        stateListDrawable = (StateListDrawable) theme.list_selector_ktt;



        DrawableContainer parentDrawable = (DrawableContainer) stateListDrawable;
        //parentDrawable.mutate();
        DrawableContainer.DrawableContainerState containerState = (DrawableContainer.DrawableContainerState) parentDrawable.getConstantState();
        Drawable[] children = containerState.getChildren();
        GradientDrawable gradientDrawable;

        if(color2 != -1) {
            gradientDrawable = (GradientDrawable) children[0];
            gradientDrawable.setColor(color2);
            gradientDrawable = (GradientDrawable) children[1];
            gradientDrawable.setColor(color2);
            gradientDrawable = (GradientDrawable) children[2];
            gradientDrawable.setColor(color2);
        }

        if(color1 != -1) {
            gradientDrawable = (GradientDrawable) children[3];
            gradientDrawable.setColor(color1);
        }

        theme.list_selector_ktt = stateListDrawable;
    }
}
