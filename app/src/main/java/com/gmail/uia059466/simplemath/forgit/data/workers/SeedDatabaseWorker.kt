package com.gmail.uia059466.simplemath.forgit.data.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.gmail.uia059466.simplemath.forgit.data.WorkoutDb
import com.gmail.uia059466.simplemath.forgit.data.db.AppDatabase
import com.gmail.uia059466.simplemath.forgit.data.stub.createDateBase
import kotlinx.coroutines.coroutineScope
import timber.log.Timber

class SeedDatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = coroutineScope {
        try {
        
                    val lessonList: List<WorkoutDb> = createDateBase()

                    val database = AppDatabase.getInstance(applicationContext)
                    database.workoutDao().insertAll(lessonList)

                    Result.success()
       
        } catch (ex: Exception) {
            Timber.d("Error seeding database")
            Result.failure()
        }
    }

    companion object {
        private const val TAG = "SeedDatabaseWorker"
    }
}