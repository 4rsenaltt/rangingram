package com.atsoft.telegram.kui.wizard;

import java.util.ArrayList;

/**
 * Created by future on 12/15/2015.
 */
public class Wizz
{
    private ArrayList<Wiz> wizz = new ArrayList<>();
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int size()
    {
        return wizz.size();
    }
    public void add(Wiz wiz)
    {
        this.wizz.add(wiz);
    }

    public Wiz get(int i)
    {
       return this.wizz.get(i);
    }

    public void updateProperty(int id , int newProperty)
    {
        for (int i = 0 ; i < wizz.size() ; i++){
            if(wizz.get(i).getId() == id)
            {
                wizz.get(i).setProperty(newProperty);
                return;
            }
        }
    }

    public void updateProperty(Wiz wiz)
    {
        updateProperty(wiz.getId(), wiz.getProperty());
    }

    public void updateProperty(Wizz wizz1)
    {
        for (int i = 0 ; i < wizz1.size() ; i++){
            updateProperty(wizz1.get(i));
        }
    }

}
