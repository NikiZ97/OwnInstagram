package com.sharonov.nikiz.nikizinstagram.extensions

import android.content.Context
import android.widget.EditText
import android.widget.Toast

fun <T: EditText> T.setupValidator(errorResourceMessageId: Int, validate: (T) -> Boolean, context: Context): Boolean {
    if (!validate(this)) {
        Toast.makeText(context, errorResourceMessageId, Toast.LENGTH_SHORT).show()
        return false
    } else {
        return true
    }
}

// Here may be real login validation. It doesn't exist yet.
fun EditText.isStringNotEmpty() = text.toString().isNotEmpty()

fun EditText.isPasswordCorrect() = text.length > 5