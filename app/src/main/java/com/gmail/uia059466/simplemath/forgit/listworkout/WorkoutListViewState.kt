package com.gmail.uia059466.simplemath.forgit.listworkout

import com.gmail.uia059466.simplemath.forgit.utils.FragmentType
import com.gmail.uia059466.simplemath.forgit.utils.IViewState

sealed class WorkoutListViewState : IViewState {
  class LessonList(
          val data: List<DataItemWorkout>,
          val position:Int=0
                  ) : WorkoutListViewState() {
    
    override val fragmentType = FragmentType.WORKOUT_LIST
  }
}