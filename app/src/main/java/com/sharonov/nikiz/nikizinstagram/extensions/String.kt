package com.sharonov.nikiz.nikizinstagram.extensions

fun String.expandUsername() = replace(".", " ")

fun String.condenseUsername() = replace(" ", ".")