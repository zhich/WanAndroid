package com.zch.base.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by zch on 2019/01/12.
 */
object DateUtil {

    /**
     * 默认格式
     */
    val DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm"

    /**
     * 自定义一些格式
     */
    val YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss"

    /**
     * 日期类型转字符串
     *
     * @param date
     */
    fun date2Str(date: Date): String {
        return SimpleDateFormat(DEFAULT_DATETIME_FORMAT).format(date)
    }

    /**
     * 日期类型转字符串
     *
     * @param date
     * @param format
     */
    fun date2Str(date: Date, format: SimpleDateFormat): String {
        return format.format(date)
    }


    /**
     * 毫秒值转字符串
     *
     * @param timeMillis
     */
    fun long2Str(timeMillis: Long): String {
        return SimpleDateFormat(DEFAULT_DATETIME_FORMAT).format(Date(timeMillis))
    }

    /**
     * 毫秒值转字符串
     *
     * @param timeMillis
     * @param formatStr
     */
    fun long2Str(timeMillis: Long, formatStr: String): String {
        val format = SimpleDateFormat(formatStr)
        return format.format(Date(timeMillis))
    }

    /**
     * 判断给定字符串是否指定格式的日期
     *
     * @param str
     * @param format
     * @return
     */
    fun isDateFormatStr(str: String, format: SimpleDateFormat): Boolean {
        try {
            val date = format.parse(str)
            return str == format.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return false
    }
}