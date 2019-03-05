package com.iwith.base.helper

import android.content.Context
import android.graphics.Typeface

/**
 * 应用字体
 *
 * <item name="android:typeface">monospace</item>
 *
 * monospace为staticTypefaceFieldName这个变量
 *
 */
object FontsOverride {
    fun setDefaultFont(context: Context,
                       staticTypefaceFieldName: String, fontAssetName: String) {
        val regular = Typeface.createFromAsset(context.assets,
                fontAssetName)
        replaceFont(staticTypefaceFieldName, regular)
    }

    internal fun replaceFont(staticTypefaceFieldName: String,
                             newTypeface: Typeface) {
        try {
            val staticField = Typeface::class.java
                    .getDeclaredField(staticTypefaceFieldName)
            staticField.isAccessible = true
            staticField.set(null, newTypeface)
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }

    }
}