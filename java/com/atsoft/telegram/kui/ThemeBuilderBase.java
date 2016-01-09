package com.atsoft.telegram.kui;

import android.graphics.Color;

import com.atsoft.telegram.kui.wizard.Wiz;
import com.atsoft.telegram.kui.wizard.Wizz;

/**
 * Created by future on 12/6/2015.
 */
public class ThemeBuilderBase
{
    private static int count = 0;
    public static final int PP_msg_in_color = 1;
    public static final int PP_msg_out_color = 2;
    public static final int PP_first_tick_color = 3;
    public static final int PP_second_tick_color = 4;
    public static final int PP_msg_text_in_color = 5;
    public static final int PP_msg_text_out_color = 6;
    public static final int PP_list_selector_color1 = 7;
    public static final int PP_list_selector_color2 = 8;


    private static Wizz wizz = new Wizz();

    public Wizz accessWizz()
    {
        return wizz;
    }

    public Wizz getWizz()
    {
        //wizz.clear();
        wizz = new Wizz();
        createWizz();
        return wizz;
    }

    private void createWizz()
    {
        Wiz.init(this);
        Wiz wiz;

        wiz = new Wiz(PP_msg_in_color);
        wiz.setTitle("رنگ زمینه پیام های ورودی");
        wiz.setDefaultProperty(Color.BLUE);
        wiz.done();

        wiz = new Wiz(PP_msg_out_color);
        wiz.setTitle("رنگ زممینه پیام های خروجی");
        wiz.done();

        wiz = new Wiz(PP_first_tick_color);
        wiz.setTitle("رنگ تیک اول");
        wiz.done();

        wiz = new Wiz(PP_second_tick_color);
        wiz.setTitle("رنگ تیک دوم");
        wiz.done();

        wiz = new Wiz(PP_msg_text_in_color);
        wiz.setTitle("رنگ متن پیام های وردی");
        wiz.done();

        wiz = new Wiz(PP_msg_text_out_color);
        wiz.setTitle("رنگ متن پیام های خروجی");
        wiz.done();

        wiz = new Wiz(PP_list_selector_color1);
        wiz.setTitle("رنگ اول لیست ها");
        wiz.done();

        wiz = new Wiz(PP_list_selector_color2);
        wiz.setTitle("رنگ دوم لیست ها");
        wiz.done();

    }


}
