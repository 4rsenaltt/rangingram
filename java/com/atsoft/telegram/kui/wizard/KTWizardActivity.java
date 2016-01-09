package com.atsoft.telegram.kui.wizard;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.atsoft.telegram.kui.ThemeCenter;
import com.atsoft.telegram.kui.kt.KTBuilder;

import org.telegram.messenger.R;

public class KTWizardActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener
{
    private ViewPager pager;
    private Adapter adapter;
    private Wizz wizz;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_wizard);
        KTBuilder builder = ThemeCenter.get().getKtBuilder();
        wizz = builder.getWizzUI();
        if(getIntent().getExtras() != null) {
            name = getIntent().getExtras().getString("id");
            wizz.setName(name);
        }

        if(name == null)name = wizz.getName();
        if(name.endsWith(".ktt")) name = name.replace(".ktt","");
        getSupportActionBar().setTitle(name);
        pager = (ViewPager) findViewById(R.id.vp);
        pager.addOnPageChangeListener(this);
        adapter = new Adapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    private class Adapter extends FragmentStatePagerAdapter
    {
        Activity context = KTWizardActivity.this;

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            WizFragment fragment = new WizFragment();
            if(position <= wizz.size()-1)fragment.setWiz(wizz.get(position));

            return fragment;
        }

        @Override
        public int getCount() {
            return wizz.size();
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("ذخیره تم")
                .setMessage("تغییرات ذخیره شود؟")
                .setPositiveButton("آره", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        KTBuilder builder = ThemeCenter.get().getKtBuilder();
                        builder.save(wizz);
                        finish();
                    }
                })
                .setNegativeButton("نه", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).create().show();
    }
}
