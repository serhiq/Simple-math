package com.gmail.uia059466.simplemath.forgit.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.gmail.uia059466.simplemath.forgit.data.WorkoutDb
import com.gmail.uia059466.simplemath.forgit.data.converters.SkillTypeConverter
import com.gmail.uia059466.simplemath.forgit.data.workers.SeedDatabaseWorker
import com.gmail.uia059466.simplemath.forgit.data.workout.WorkoutDao
import com.gmail.uia059466.simplemath.forgit.data.workout.WorkoutTypeConverter

@Database(
        entities = [WorkoutDb::class],
        version = 1,
        exportSchema = false
         )
@TypeConverters(
        WorkoutTypeConverter::class,
        SkillTypeConverter::class
               )
abstract class AppDatabase : RoomDatabase() {

  abstract fun workoutDao()       : WorkoutDao

  companion object {
    private var INSTANCE: AppDatabase? = null
    private const val DATABASE_NAME = "simple_match.db"

    fun getInstance(context: Context): AppDatabase {
      synchronized(this) {
        var instance =
          INSTANCE
        if (instance == null) {

          instance =
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DATABASE_NAME)
                    .addCallback(object : RoomDatabase.Callback() {
                      override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
  
                        val request: OneTimeWorkRequest = OneTimeWorkRequest.Builder(
                                SeedDatabaseWorker::class.java
                                                                                    ).build()
                        WorkManager.getInstance(context).enqueue(request)
                      }
                    })
              .build()

          INSTANCE = instance
        }
        return instance
      }
    }
  }
}