package com.atsoft.telegram.kui.kt;

import android.content.Context;

import com.atsoft.telegram.kui.KharTheme;
import com.atsoft.telegram.kui.wizard.Wizz;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by future on 12/12/2015.
 */
public class KTBuilder extends KTBuilderEngineLayer
{

    public KTBuilder(Context context)
    {
        super(context);
        copyNativeThemes(context);
    }

    public KharTheme buildDefault()
    {
        initTheme();
        return getTheme();
    }


    public KharTheme build(Wizz wizz)
    {
        initTheme();
        applyWiz(wizz);
        //String data = buildThemeData(wizz);
        //saveTheme("khar.ktt", data);
        return getTheme();
    }

    public void save(Wizz wizz)
    {
        String data = buildThemeData(wizz);
        saveTheme(wizz.getName(), data);
    }
    public KharTheme build(String name)
    {
        if(!exists(name)){
            return null;
        }
        initTheme();
        Wizz wizz = buildWizzFromFile(name);
        applyWiz(wizz);
        return getTheme();
    }

    public ArrayList<String> getThemes()
    {
        ArrayList<String> out = new ArrayList<>();
        ArrayList<File> files = getThemes_f();
        for (int i = 0 ; i < files.size() ; i++)
        {
            out.add(files.get(i).getName());
        }
            out.add(0, "default_telegram.ktt");
        return out;
    }

    public void prepareThemeForEdit(String theme)
    {
        Wizz wizz = buildWizzFromFile(theme);
        buildWizUI();
        Wizz wizzUI = getWizzUI();
        wizzUI.updateProperty(wizz);
        wizzUI.setName(theme);
    }

}
