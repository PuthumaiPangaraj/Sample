package com.demo.yeldi.listlogiapplication.Utils;

/**
 * Created by pudumai on 6/14/2016.
 */
import android.app.ProgressDialog;
import android.content.Context;

public class MyProgress {
    public static ProgressDialog pDialog;

    public MyProgress(Context ctx, String message)
    {
        pDialog = new ProgressDialog(ctx);
        pDialog.setMessage(message);
        pDialog.setCancelable(false);

    }
    public void hideProgress() {
        // TODO Auto-generated method stub
        if(pDialog.isShowing())
            pDialog.dismiss();
    }

    public void showProgress() {
        // TODO Auto-generated method stub

        if(!pDialog.isShowing())
            pDialog.show();

    }
}
