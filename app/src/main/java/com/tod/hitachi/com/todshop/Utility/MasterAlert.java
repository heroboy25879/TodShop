package com.tod.hitachi.com.todshop.Utility;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.tod.hitachi.com.todshop.R;

public class MasterAlert {

    private Context context;

    public MasterAlert(Context context) {
        this.context = context;
    }


    public void normalDialog(String titleString, String MessageString) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable((false)); //สั่งไม่ให้กด cancel
        builder.setIcon(R.drawable.ic_action_name);
        builder.setTitle(titleString);
        builder.setMessage(MessageString);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();

    }
}
