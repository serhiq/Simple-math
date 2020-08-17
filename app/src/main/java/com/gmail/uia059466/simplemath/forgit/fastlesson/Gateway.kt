package com.gmail.uia059466.simplemath.forgit.fastlesson

import com.gmail.uia059466.simplemath.forgit.data.Skill
import com.gmail.uia059466.simplemath.forgit.data.WorkoutDb
import com.gmail.uia059466.simplemath.forgit.data.workout.Workout
import com.gmail.uia059466.simplemath.forgit.fastlesson.gameend.ResultRepository

interface Gateway {
          fun getCurrentLesson()             : Workout?
  suspend fun finishGame           (id: Int)      : Int
  suspend fun getNumComplected     (skill: Skill) : Int
  suspend fun getNumber            (skill: Skill) : Int
  suspend fun getListWorkoutBySkill(skill: Skill) : List<WorkoutDb>
  suspend fun getWorkoutById (id: Int): WorkoutDb?
          fun isUseInFastGame(skill: Skill): Boolean
          fun getResultRepository(): ResultRepository
          fun isFocusMode(): Boolean
          fun isUseRuler(): Boolean
}