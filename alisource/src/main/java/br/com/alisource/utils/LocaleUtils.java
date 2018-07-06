package br.com.alisource.utils;

import android.content.Context;
import android.os.Build;

import java.util.Locale;

public class LocaleUtils {

    private LocaleUtils() {
    }

    /**
     * Method to get current locale from device
     *
     * @param context - Context
     * @return Device current locale
     */
    public Locale getCurrentLocale(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return context.getResources().getConfiguration().getLocales().get(0);
        } else {
            //noinspection deprecation
            return context.getResources().getConfiguration().locale;
        }
    }

}