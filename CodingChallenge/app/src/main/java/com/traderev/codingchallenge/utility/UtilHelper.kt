package com.traderev.codingchallenge.utility

import android.util.Log
import com.traderev.codingchallenge.common.LogType

/**
 *Utility class for common functionalities
 * across the app
 */
class UtilHelper {

    companion object {
        //To display logs in the app
        fun displayLog(message: String, tag: String, logType: LogType) {

            when (logType) {

                LogType.DEBUG -> Log.d(tag, message)
                LogType.ERROR -> Log.e(tag, message)
                LogType.VERBOSE -> Log.v(tag, message)
                LogType.INFO -> Log.i(tag, message)
            }
        }
    }
}