package br.com.alisource.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;

import androidx.annotation.NonNull;

import java.util.Locale;

public class LocaleUtils {

    private LocaleUtils() {
    }

    /**
     * Returns the current device Locale.
     *
     * @param context - The context used to access resources.
     * @return The primary Locale of the device
     */
    public static Locale getCurrentLocale(@NonNull Context context) {
        Configuration configuration = context.getResources().getConfiguration();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return configuration.getLocales().get(0);
        } else {
            //noinspection deprecation
            return configuration.locale;
        }
    }

}