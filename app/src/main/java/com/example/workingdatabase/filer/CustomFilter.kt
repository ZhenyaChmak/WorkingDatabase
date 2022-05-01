package com.example.workingdatabase.filer

import android.text.InputFilter

interface CustomFilter {

    fun filter(): InputFilter {
        return InputFilter { source, start, end, _, _, _ ->
            for (i in start until end) {
                if (!Character.isLetter(source[i]) && !Character.isSpaceChar(source[i])) {
                    return@InputFilter ""
                }
            }
            null
        }
    }

}