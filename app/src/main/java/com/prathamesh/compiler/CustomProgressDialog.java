package com.prathamesh.compiler;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class CustomProgressDialog {
    private AlertDialog showAlertdialog;

    public void showCustomProgressDialog(Context context){

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_progressdialog,null);
        ImageView imageView = view.findViewById(R.id.IVCompilingPD);
        Glide.with(context).load(R.drawable.compiling).into(imageView);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

        alertDialog.setView(view);
        alertDialog.setCancelable(false);

        showAlertdialog = alertDialog.create();
        showAlertdialog.show();
    }

    public void dismissCustomProgressDialog(){
        showAlertdialog.dismiss();
    }
}
