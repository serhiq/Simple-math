package com.gmail.uia059466.simplemath.forgit.fastlesson

import com.gmail.uia059466.simplemath.forgit.data.Skill
import com.gmail.uia059466.simplemath.forgit.data.WorkoutDb
import com.gmail.uia059466.simplemath.forgit.data.db.AppDatabase
import com.gmail.uia059466.simplemath.forgit.data.workout.Workout
import com.gmail.uia059466.simplemath.forgit.data.workout.toWorkout
import com.gmail.uia059466.simplemath.forgit.fastlesson.gameend.Prefs
import com.gmail.uia059466.simplemath.forgit.fastlesson.gameend.ResultRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LessonRepositoryImpl(private val db: AppDatabase, val prefs: Prefs) : Gateway {
  
  override suspend fun getWorkoutById(id: Int): WorkoutDb? {
    return db.workoutDao().getById(id)
  }
  
  override fun getCurrentLesson(): Workout? {
    return db.workoutDao().getCurrent()?.toWorkout()
  }
  
  override suspend fun finishGame(id: Int):Int {
    return withContext(Dispatchers.IO) {
        db.workoutDao().update(id = id, isComplected = true)
    }
  }
  
  override suspend fun getNumComplected(skill: Skill): Int {
    return withContext(Dispatchers.IO) {
      db.workoutDao().getCountComplected(skill)
    }  }
  
  override suspend fun getNumber(skill: Skill): Int {
    return withContext(Dispatchers.IO) {
      db.workoutDao().getCount(skill)
    }  }
  
  override suspend fun getListWorkoutBySkill(skill: Skill): List<WorkoutDb> {
    return withContext(Dispatchers.IO) {
      db.workoutDao().getWorkoutBySkill(skill)
    }
  }
  
  override fun isUseInFastGame(skill: Skill): Boolean {
    return  when(skill){
      Skill.SAY_NEXT -> prefs.isUseSayNext
      Skill.ADDITION_VERBAL -> prefs.isUseAdditionVerbal
    }
  }
  
  override fun getResultRepository(): ResultRepository {
    return prefs
  }
  
  override fun isFocusMode(): Boolean {
    return prefs.isUseFocus
  }
  
  override fun isUseRuler(): Boolean {
    return prefs.isUserRuler
  }
}