package com.gmail.uia059466.simplemath.forgit.listworkout

import com.gmail.uia059466.simplemath.forgit.data.workout.Workout
import com.gmail.uia059466.simplemath.forgit.utils.MviAction

sealed class WorkoutListAction : MviAction {
  class RunWorkout     (val workout: Workout) : WorkoutListAction()
  object RunSkillList                         : WorkoutListAction()
}