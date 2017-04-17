package com.shall.util.misc;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;


import com.shall.MainActivity;

import java.util.Locale;

/**
 * Created by elsaidel on 1/16/2017.
 */

public class LocaleUtil {
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
}
