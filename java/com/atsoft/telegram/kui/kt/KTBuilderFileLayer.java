package com.atsoft.telegram.kui.kt;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by future on 12/12/2015.
 */
public class KTBuilderFileLayer extends KTBuilderBase
{
    private static final String EX_PATH = Environment.getExternalStorageDirectory().getPath()+"/telegramThemes";

    public KTBuilderFileLayer()
    {
        init();
    }

    private void init()
    {
        new File(EX_PATH).mkdirs();
    }

    private void writeFile(String path, String data)
    {
        File file = new File(path);

        try
        {
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(data.getBytes("UTF-8"));
            fileOutputStream.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }


    }

    private File open(String path)
    {
        return new File(path);
    }

    private InputStream readFile(String path)
    {
        return null;
    }

    private File[] getFiles(String path)
    {
        File folder = new File(path);
        if(!folder.exists()){
            return null;
        }

        if(folder.isFile()){
            return null;
        }

        return folder.listFiles();
    }


    //expose
    protected final ArrayList<File> getThemes_f()
    {
        File[] files = getFiles(EX_PATH);
        ArrayList<File> themes = new ArrayList<>();
        for (int i = 0 ; i < files.length ; i++){
            if(files[i].getName().endsWith(".ktt"))
            {
                themes.add(files[i]);
            }
        }

        return themes;
    }

    protected final void saveTheme(String name, String data)
    {
        if(!name.endsWith(".ktt")){
            name = name+".ktt";
        }
        writeFile(EX_PATH+"/"+name, data);
    }

    protected final File getThemeFile(String name)
    {
        if(!name.endsWith(".ktt")){
            name = name+".ktt";
        }
        return open(EX_PATH+"/"+name);
    }

    protected final InputStream getThemeIS(String name)
    {
        if(!name.endsWith(".ktt")){
            name = name+".ktt";
        }

        return readFile(EX_PATH + "/" + name);
    }

    protected final boolean exists(String name)
    {
        if(!name.endsWith(".ktt")){
            name = name+".ktt";
        }

        File file = new File(EX_PATH + "/"+name);
        return file.exists();
    }

    protected final void copyNativeThemes(Context context)
    {
        AssetManager assetManager = context.getAssets();
        try {
            String [] file_names = assetManager.list("themes");

            Log.i("mehrdad", file_names+"");
            for (int i = 0 ; i < file_names.length ; i++) copyFile("themes/",file_names[i], context);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void copyFile(String path, String filename, Context context) {
        AssetManager assetManager = context.getAssets();

        InputStream in = null;
        OutputStream out = null;
        try {
            if(exists(filename)) return;
            in = assetManager.open(path+filename);
            String newFileName = EX_PATH + "/" + filename;
            out = new FileOutputStream(newFileName);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();
            in = null;
            out.flush();
            out.close();
            out = null;
        } catch (Exception e) {
            Log.e("tag", e.getMessage());
        }

    }

}
