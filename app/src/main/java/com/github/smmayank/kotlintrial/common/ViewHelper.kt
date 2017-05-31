package com.github.smmayank.kotlintrial.common

import android.content.Context
import android.view.View

/**
 * @author Mayank Saxena
 * @version 1.0
 * @since 2017-05-31
 */
object ViewHelper {
    @Suppress("UNCHECKED_CAST")
    fun <R : View> findById(root: View?, id: Int): R? = root?.findViewById(id) as R?
}