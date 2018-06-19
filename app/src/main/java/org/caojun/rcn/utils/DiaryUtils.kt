package org.caojun.rcn.utils

import android.content.Context
import org.caojun.rcn.ormlite.Diary
import org.caojun.rcn.ormlite.DiaryDatabase
import org.caojun.utils.TimeUtils

/**
 * Created by CaoJun on 2017/8/7.
 */
object DiaryUtils {
    fun insert(context: Context, diary : Diary): Boolean {
        return DiaryDatabase.getInstance(context)?.insert(diary)?:false
    }

    fun update(context: Context, diary : Diary): Boolean {
        return DiaryDatabase.getInstance(context)?.update(diary)?:false
    }

    fun queryToday(context: Context): Diary? {
        val dateFormat = "yyyyMMdd"
        val time = TimeUtils.getTime(dateFormat)
        val list = DiaryDatabase.getInstance(context)?.query("day", time)
        if (list == null || list.size != 1) {
            return null
        }
        return list[0]
    }
}