package com.gmail.uia059466.simplemath.forgit.listworkout

import com.gmail.uia059466.simplemath.forgit.data.Skill
import com.gmail.uia059466.simplemath.forgit.data.workout.toWorkout
import com.gmail.uia059466.simplemath.forgit.fastlesson.Gateway
import com.gmail.uia059466.simplemath.forgit.utils.ISuspendAction
import com.gmail.uia059466.simplemath.forgit.utils.IViewState

class CreateStateWorkoutListFromId(val id: Int) : ISuspendAction {
 
  override suspend fun createState(gateway: Gateway): List<IViewState> {
    
    val workoutDb=gateway.getWorkoutById(id)
    
    val skill= when (workoutDb) {
      null -> Skill.SAY_NEXT
      else -> workoutDb.skill
    }
    
    val list= mutableListOf<DataItemWorkout>()
    val listWorkout=gateway.getListWorkoutBySkill(skill)
    
    var scrollPosition=0
    for (w in listWorkout){
    
      val workout=w.toWorkout()
      val dataWorkout = DataItemWorkout(
              name         = workout.generateName(),
              description  = workout.generateDescription(),
              result       = w.result,
              isComplected = w.isComplected,
              workout       = workout)
      
      list.add(dataWorkout)
    }
    scrollPosition=listWorkout.indexOfFirst { it.id==id }
    return listOf(
            WorkoutListViewState.LessonList(
                    data = list,
                    position = scrollPosition
                                           )
                 )
  }
}