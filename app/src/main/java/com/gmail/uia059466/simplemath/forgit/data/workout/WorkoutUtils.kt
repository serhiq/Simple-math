package com.gmail.uia059466.simplemath.forgit.data.workout

import com.gmail.uia059466.simplemath.forgit.data.WorkoutDb
import com.gmail.uia059466.simplemath.forgit.data.WorkoutType
import com.gmail.uia059466.simplemath.forgit.data.workout.addverbal.AddVerbalDirect
import com.gmail.uia059466.simplemath.forgit.data.workout.addverbal.AddVerbalRandom
import com.gmail.uia059466.simplemath.forgit.data.workout.addverbal.AddVerbalRandomPermute
import com.gmail.uia059466.simplemath.forgit.data.workout.addverbal.AddVerbalReverse
import com.gmail.uia059466.simplemath.forgit.data.workout.saynext.SayNextDirect
import com.gmail.uia059466.simplemath.forgit.data.workout.saynext.SayNextRandom

fun WorkoutDb.toWorkout(): Workout {
  
  return when(this.type){
    WorkoutType.SAY_NEXT_DIRECT      -> SayNextDirect(
            from = this.from,
            to = this.to,
            id = this.id,
            add = this.delta
                                                                                                                            )
    WorkoutType.SAY_NEXT_RANDOM      -> SayNextRandom(
            from = this.from,
            to = this.to,
            id = this.id,
            add = this.delta
                                                                                                                            )
    WorkoutType.ADD_VERBAL_DIRECT    -> AddVerbalDirect(
            from = this.from,
            to = this.to,
            add = this.delta,
            id = this.id
                                                                                                                                )
    WorkoutType.ADD_VERBAL_REVERSE   -> AddVerbalReverse(
            from = this.from,
            to = this.to,
            add = this.delta,
            id = this.id
                                                                                                                                 )
    WorkoutType.ADD_VERBAL_RANDOM    -> AddVerbalRandom(
            from = this.from,
            to = this.to,
            add = this.delta,
            id = this.id
                                                                                                                                )
    WorkoutType.ADD_VERBAL_RANDOM_PERMUTE -> AddVerbalRandomPermute(
            from = this.from,
            to = this.to,
            add = this.delta,
            id = this.id
                                                                                                                                            )
  }
}