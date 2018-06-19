package org.caojun.rcn.ormlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.dao.Dao
import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.TableUtils
import java.sql.SQLException

class DiaryDatabase: OrmLiteSqliteOpenHelper {

    companion object {
        private var diaryDatabase: DiaryDatabase? = null
        private var diaryDao: Dao<Diary, Int>? = null

        fun getInstance(context: Context): DiaryDatabase? {
            if (diaryDatabase == null) {
                diaryDatabase = DiaryDatabase(context)
                try {
                    diaryDao = diaryDatabase!!.getDao(Diary::class.java)
                } catch (e: SQLException) {
                    e.printStackTrace()
                }

            }
            return diaryDatabase
        }
    }

    constructor(context: Context): super(context, "diary-db", null, 1)

    override fun onCreate(db: SQLiteDatabase, connectionSource: ConnectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, Diary::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun onUpgrade(db: SQLiteDatabase, connectionSource: ConnectionSource,
                           oldVersion: Int, newVersion: Int) {
        try {
            TableUtils.dropTable<Diary, Any>(connectionSource, Diary::class.java, true)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        onCreate(db, connectionSource)
    }

    fun insert(diary: Diary): Boolean {
        try {
            return diaryDao!!.create(diary) == 1
        } catch (e: SQLException) {
            e.printStackTrace()
        }

        return false
    }

    fun update(diary: Diary): Boolean {
        try {
            return diaryDao!!.update(diary) == 1
        } catch (e: SQLException) {
            e.printStackTrace()
        }

        return false
    }

    fun query(): List<Diary>? {
        try {
            return diaryDao!!.queryBuilder().query()
        } catch (e: SQLException) {
            e.printStackTrace()
        }

        return null
    }

    fun query(columnName: String, value: Any): List<Diary>? {
        try {
            return diaryDao!!.queryBuilder().where().eq(columnName, value).query()
        } catch (e: SQLException) {
            e.printStackTrace()
        }

        return null
    }
}