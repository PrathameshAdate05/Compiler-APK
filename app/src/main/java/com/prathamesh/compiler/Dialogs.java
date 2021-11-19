package com.prathamesh.compiler;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;

import com.bumptech.glide.Glide;

public class Dialogs {

    public static void showDialog(Context context, int layoutId, int IVid, int iconId){
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layoutId,null);

        ImageView imageView = view.getRootView().findViewById(IVid);
        Glide.with(context).load(iconId).into(imageView);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setView(view);
        alertDialog.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        AlertDialog showDialog = alertDialog.create();
        showDialog.show();

        final Button okButton = showDialog.getButton(AlertDialog.BUTTON_POSITIVE);
        LinearLayout.LayoutParams okButtonLL = (LinearLayout.LayoutParams) okButton.getLayoutParams();
        okButtonLL.weight = 15.0f;
//        okButtonLL.gravity = Gravity.CENTER_HORIZONTAL;
        okButton.setLayoutParams(okButtonLL);

    }
}
