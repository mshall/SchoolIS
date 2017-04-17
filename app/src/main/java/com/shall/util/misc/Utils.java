package com.shall.util.misc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;


import com.shall.MainActivity;

import java.util.Locale;
import java.util.Random;

/**
 * Created by Shall on 29/6/2016.
 */
public class Utils {
    //----------------------------------------------------------------------------------------------
    //Set Language locale
    //----------------------------------------------------------------------------------------------
    public static void setLocale(Context context, String lang) {

        Locale locale = new Locale(lang);
        Resources res = context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = locale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(context, MainActivity.class);
        context.startActivity(refresh);
    }

    //--------------------------------------------------------
    // Hide keyboard
    //--------------------------------------------------------
    public static void hideKeyboard(Context ctx) {
        InputMethodManager inputManager = (InputMethodManager) ctx
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        // check if no view has focus:
        View v = ((Activity) ctx).getCurrentFocus();
        if (v == null)
            return;

        inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }


    public static int getRandomNumber(int minimumValue, int maximumValue) {
        Random r = new Random();
        return r.nextInt(maximumValue - minimumValue) + minimumValue;
    }
}
