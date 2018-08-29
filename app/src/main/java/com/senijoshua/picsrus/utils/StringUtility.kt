package com.senijoshua.picsrus.utils

import android.support.annotation.StringRes
import com.senijoshua.picsrus.PicsRusApplication

class StringUtility {

    companion object {
        fun getString(@StringRes resId: Int): String {
           return PicsRusApplication.getApplicationContext().getString(resId)
        }
    }
}