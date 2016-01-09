package com.atsoft.telegram.kui;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.atsoft.telegram.kui.kt.KTBuilder;

import org.telegram.messenger.ApplicationLoader;
import org.telegram.ui.Components.ResourceLoader;

/**
 * Created by future on 12/2/2015.
 */
public class ThemeCenter {
    private static ThemeCenter ourInstance = new ThemeCenter();

    public static ThemeCenter get() {
        return ourInstance;
    }

    private ThemeCenter() {

    }

    ////////////////////////////////
    private KharTheme theme;
    private KTBuilder ktBuilder;
    private String currentTheme;

    public void init(Context context)
    {
        updateCurrentTheme(context);
        if(ktBuilder == null) ktBuilder = new KTBuilder(context);
        theme = ktBuilder.build(currentTheme);
        if(theme == null){
            theme = ktBuilder.buildDefault();
        }

    }

    public KharTheme getTheme()
    {
        if (theme == null){
            init(ApplicationLoader.applicationContext);
        }
        return theme;
    }
    public KTBuilder getKtBuilder ()
    {
        return this.ktBuilder;
    }
    public void setCurrentTheme(Context context, String currentTheme) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences.edit().putString("current_theme", currentTheme).commit();
        this.currentTheme = currentTheme;
    }
    private void updateCurrentTheme(Context context)
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        currentTheme = sharedPreferences.getString("current_theme","");
    }


    public String getCurrentTheme()
    {
        return currentTheme;
    }

    public void invalidate(Context context)
    {
        theme = null;
        init(context);
        ResourceLoader.load = true;
        ResourceLoader.loadRecources(context);
    }
}
