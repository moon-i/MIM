package com.moon.data.room.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.moon.data.room.dao.PlanDao
import com.moon.data.room.dao.StatisticsDao
import com.moon.data.room.dao.TagDao
import com.moon.data.room.entity.PlanEntity
import com.moon.data.room.entity.TagEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*

@Database(entities = [PlanEntity::class, TagEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MIMRoomDatabase : RoomDatabase() {
    abstract fun planDao(): PlanDao
    abstract fun statisticsDao(): StatisticsDao
    abstract fun tagDao(): TagDao

    class MIMDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    val tagDao = database.tagDao()
                    // 기본 생성 tag
                    tagDao.insert(TagEntity(0, "공부", "#DC5D6A", true))
                    tagDao.insert(TagEntity(0, "독서", "#E68765", true))
                    tagDao.insert(TagEntity(0, "운동", "#E8C26B", true))
                    tagDao.insert(TagEntity(0, "친목", "#A3CF77", true))
                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: MIMRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): MIMRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MIMRoomDatabase::class.java,
                    "mim_database"
                ).addCallback(MIMDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}