package com.ope.base.helper

import java.nio.charset.Charset
import java.security.MessageDigest

/**
 * md5算法编码
 * @param origin 原值
 * @param charset 字符集
 */
fun md5(origin: String, charset: Charset = Charsets.UTF_8): String {
    return try {
        byteArrayToHexString(MessageDigest.getInstance("MD5").digest(origin.toByteArray(charset)))
    } catch (t: Throwable) {
        t.printStackTrace()
        ""
    }
}

private fun byteArrayToHexString(b: ByteArray): String {
    val sb = StringBuilder()
    b.forEach {
        sb.append(byteToHexString(it))
    }
    return sb.toString()
}

private fun byteToHexString(b: Byte): String {
    var n = b.toInt()
    if (n < 0) {
        n += 256
    }
    return hexDigits[n / 16] + hexDigits[n % 16]
}

private val hexDigits = arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f")