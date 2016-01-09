package com.atsoft.telegram.kui.wizard;

import android.graphics.Color;

import com.atsoft.telegram.kui.ThemeBuilderBase;

/**
 * Created by future on 12/11/2015.
 */
public class Wiz
{
    public static final int TYPE_COLOR = 1;
    public static final int Type_ITEMS = 2;
    private static ThemeBuilderBase builder;
    public static void init(ThemeBuilderBase builderBase)
    {
        builder = builderBase;
    }

    private int id;
    private int property = -1;
    private int defaultProperty = Color.WHITE;

    public Wiz(int id)
    {
        this.id = id;
    }


    public void setProperty(int property) {
        this.property = property;
    }

    public int getProperty() {
        return property;
    }

    public int getId() {
        return id;
    }

    public void setDefaultProperty(int defaultProperty) {
        this.defaultProperty = defaultProperty;
    }

    public int getDefaultProperty() {
        return defaultProperty;
    }

    public void done()
    {
        builder.accessWizz().add(this);
    }
    ///View
    private String title;
    private String[] items;
    private int imageRes;
    private int viewType = 1;


    public void setTitle(String title) {
        this.title = title;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public int getImageRes() {
        return imageRes;
    }

    public int getViewType() {
        return viewType;
    }

    public String getTitle() {
        return title;
    }

    public void setItems(String[] items) {
        this.items = items;
    }

    public String[] getItems() {
        return items;
    }
}

