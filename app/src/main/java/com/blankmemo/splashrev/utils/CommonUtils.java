package com.blankmemo.splashrev.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by hongyuchen on 2018-08-29.
 */

public class CommonUtils {

    /**
     * convert Server Time Format into Local Time Format
     */
    public static String convertDate (String date) {
        String formattedDate;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date newDate = formatter.parse(date);

            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            formattedDate = dateFormatter.format(newDate);
        }
        catch (Exception e) {
            formattedDate = "N/A";
        }
        return formattedDate;
    }
}
