package com.atsoft.telegram.kui.wizard;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.atsoft.telegram.kui.wizard.colorpicker.ColorPickerDialog;

import org.telegram.messenger.R;

/**
 * Created by future on 12/6/2015.
 */
public class WizFragment extends Fragment
{
    private View rootView;
    private TextView titleView;
    private ImageView imageView;
    private Wiz wiz;
    private Button action;
    private ColorPickerDialog colorPickerDialog;


    public void setWiz(Wiz wiz) {
        this.wiz = wiz;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.wiz_frag, null);
        if(wiz == null) return rootView;
        action = (Button) rootView.findViewById(R.id.action);
        titleView = (TextView) rootView.findViewById(R.id.title);
        imageView = (ImageView) rootView.findViewById(R.id.image);
        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAction();
            }
        });

        Log.i("khar", "wiz:p : " + wiz.getProperty());
        if(wiz.getImageRes() != 0){
            imageView.setImageResource(wiz.getImageRes());
        }
        if(wiz.getProperty() == -1) {
            action.setText("رنگ پیش فرض");
            action.setTextColor(Color.GRAY);
            action.setBackgroundColor(Color.WHITE);
        }
        action.setBackgroundColor(wiz.getProperty());
        titleView.setText(wiz.getTitle());
        return rootView;
    }

    private void onAction()
    {
        if(wiz.getViewType() == 1){

            int def = wiz.getProperty();
            colorPickerDialog = new ColorPickerDialog(getContext(),def);
            colorPickerDialog.setAlphaSliderVisible(true);
            colorPickerDialog.setHexValueEnabled(true);


            colorPickerDialog.setOnColorChangedListener(new ColorPickerDialog.OnColorChangedListener() {
                @Override
                public void onColorChanged(int color) {
                    if(color != -1){
                        action.setText("");
                    }
                    wiz.setProperty(color);
                    action.setBackgroundColor(color);
                }
            });

            colorPickerDialog.show();
        }
        else if(wiz.getViewType() == Wiz.Type_ITEMS)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(wiz.getTitle())
                    .setItems(wiz.getItems(), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            wiz.setProperty(which);
                        }
                    }).create().show();
        }

    }

}
