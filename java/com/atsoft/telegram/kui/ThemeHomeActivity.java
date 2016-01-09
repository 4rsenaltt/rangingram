package com.atsoft.telegram.kui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.atsoft.telegram.kui.kt.KTBuilder;
import com.atsoft.telegram.kui.wizard.KTWizardActivity;

import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.Cells.DialogCell;
import org.telegram.ui.DialogsActivity;
import org.telegram.ui.LaunchActivity;

import java.util.ArrayList;

public class ThemeHomeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener
{
    private ListView              listView;
    private Adapter               adapter;
    private KTBuilder             ktBuilder;
    private ArrayList<String>     themes;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_home);
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
        ktBuilder = ThemeCenter.get().getKtBuilder();
        adapter = new Adapter();
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.themes = ktBuilder.getThemes();
        listView.setAdapter(adapter);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //ktBuilder.load(themes.get(position));
        if(position == adapter.getCount()-1){
            createNewTheme();
            return;
        }
        startDialog(position);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {


        return false;
    }

    private class Adapter extends BaseAdapter
    {
        Context context = ThemeHomeActivity.this;

        @Override
        public int getCount() {
            return themes.size()+1;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                TextView textView = new TextView(context);
                textView.setTextSize(21);
                textView.setTextColor(0xffffffff);

                textView.setPadding(10, 10, 10, 10);

                if(position == getCount()-1)
                    textView.setTextColor(Color.RED);

                convertView = textView;
            }
            TextView textView = (TextView) convertView;


            if(position == getCount()-1){
                textView.setText("ایجاد تم جدید...");
                textView.setGravity(Gravity.CENTER);
                return textView;
            }
            else if(position == 0){
                textView.setText("تم پیشفرض تلگرام");
            }
            if(themes.get(position).equals(ThemeCenter.get().getCurrentTheme())){
                textView.setBackgroundColor(0xaa3335b5);
            }
            else if(ThemeCenter.get().getCurrentTheme().equals("default_telegram79.ktt"))
            {

            }
            String text = themes.get(position);
            text = text.replace(".ktt", "");
            textView.setText(text);

            textView.setLayoutParams(new ListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            textView.setGravity(Gravity.RIGHT);
            return textView;

        }
    }


    private void createNewTheme()
    {
        final EditText editText = new EditText(this);
        editText.setGravity(Gravity.CENTER);
        editText.setTextSize(20);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("ایجاد تم جدید")
                .setMessage("نام تم جدید را وارد کنید")
                .setView(editText).setPositiveButton("ایجاد", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = editText.getText().toString();
                ThemeCenter.get().getKtBuilder().buildWizUI();
                ThemeCenter.get().getKtBuilder().getWizzUI().setName(name);
                Bundle bundle = new Bundle();
                bundle.putString("id", name);
                Intent intent = new Intent(ThemeHomeActivity.this, KTWizardActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);


            }
        }).setNegativeButton("بی خیال", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        Dialog dialog = builder.create();
        dialog.show();
    }


    private void startDialog(final int pos)
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("تم "+ themes.get(pos).replace(".ktt", ""))
                .setItems(new String[]{"اعمال", "ویرایش"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which == 0)
                        {
                            ThemeCenter.get().setCurrentTheme(ThemeHomeActivity.this, themes.get(pos));
                            ThemeCenter.get().invalidate(ThemeHomeActivity.this);
                            ThemeHomeActivity.this.finish();
                            BaseFragment.theme = ThemeCenter.get().getTheme();
                            DialogCell.theme = ThemeCenter.get().getTheme();
                            DialogCell.invalidate = true;
                            DialogsActivity.theAc.invalidate();
                            LaunchActivity.theAc.invalidate();

                            Toast.makeText(ThemeHomeActivity.this, "تغییرات اعمال شد.", Toast.LENGTH_LONG).show();

                        }
                        else if(which == 1)
                        {
                            if(pos>0) {
                                ktBuilder.prepareThemeForEdit(themes.get(pos));
                                startActivity(new Intent(ThemeHomeActivity.this, KTWizardActivity.class));
                            }
                            else Toast.makeText(ThemeHomeActivity.this,"تم اصلی قابل ویرایش نمی باشد.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).create().show();


    }
}
