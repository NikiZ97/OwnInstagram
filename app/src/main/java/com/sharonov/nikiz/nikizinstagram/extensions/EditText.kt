package com.sharonov.nikiz.nikizinstagram.extensions

import android.content.Context
import android.widget.EditText
import android.widget.Toast

fun <T: EditText> T.setupValidator(errorResourceMessageId: Int, validate: (T) -> Boolean, context: Context): Boolean {
    return if (!validate(this)) {
        Toast.makeText(context, errorResourceMessageId, Toast.LENGTH_SHORT).show()
        false
    } else {
        true
    }
}

// Here may be real login validation. It doesn't exist yet.
fun EditText.isStringNotEmpty() = text.toString().isNotEmpty()

fun EditText.isPasswordCorrect() = text.length > 5

fun EditText.isEmailCorrect() = text.toString().matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}".toRegex())