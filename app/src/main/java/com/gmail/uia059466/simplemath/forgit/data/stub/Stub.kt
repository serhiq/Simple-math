package com.gmail.uia059466.simplemath.forgit.data.stub

import com.gmail.uia059466.simplemath.forgit.data.Skill
import com.gmail.uia059466.simplemath.forgit.data.WorkoutDb
import com.gmail.uia059466.simplemath.forgit.data.WorkoutType

class WorkoutRaw(
        val skill: Skill,
        val type:WorkoutType,
        val from       : Int,
        val to         : Int,
        val delta       :Int=0
        ){
  init {
    if (from>=to){
      throw IllegalArgumentException("from>=to  $from   $to")
      }
  }
}

fun createSayNext(from: Int, to: Int, delta: Int):List<WorkoutRaw>{
  if(from>to)
    throw IllegalArgumentException("Incorrect interval from ${from} to${to}")

  val list = mutableListOf<WorkoutRaw>().apply {
    add(
            WorkoutRaw(
                    skill =Skill.SAY_NEXT,
                    type =WorkoutType.SAY_NEXT_DIRECT,
                    from = from,
                    to = to,
                    delta = delta
                      )
       )
    add(
            WorkoutRaw(
                    skill =Skill.SAY_NEXT,
                    type =WorkoutType.SAY_NEXT_RANDOM,
                    from = from,
                    to = to,
                    delta = delta
                      )
       )
}
return list
}

fun createAdding(from: Int, to: Int, delta: Int):List<WorkoutRaw>{
  if(from>to)
    throw IllegalArgumentException("Incorrect interval from ${from} to${to}")
  
  val list = mutableListOf<WorkoutRaw>().apply {
    add(
            WorkoutRaw(
                    skill =Skill.ADDITION_VERBAL,
                    type =WorkoutType.ADD_VERBAL_DIRECT,
                    from = from,
                    to = to,
                    delta = delta
                      )
       )
  
    add(
            WorkoutRaw(
                    skill =Skill.ADDITION_VERBAL,
                    type =WorkoutType.ADD_VERBAL_REVERSE,
                    from = from,
                    to = to,
                    delta = delta
                      )
       )
    
    add(
            WorkoutRaw(
                    skill =Skill.ADDITION_VERBAL,
                    type =WorkoutType.ADD_VERBAL_RANDOM,
                    from = from,
                    to = to,
                    delta = delta
                      )
       )
    add(
            WorkoutRaw(
                    skill =Skill.ADDITION_VERBAL,
                    type =WorkoutType.ADD_VERBAL_RANDOM_PERMUTE,
                    from = from,
                    to = to,
                    delta = delta
                      )
       )
  }
  return list
  
}

fun createAdding():List<WorkoutRaw>{
  val list= mutableListOf<WorkoutRaw>()
  list.addAll(createSayNext(from = 1, to = 10, delta = 1))
  list.addAll(createAdding(from = 1, to = 10, delta = 1))
  
  list.addAll(createSayNext(from = 11, to = 20, delta = 1))
  list.addAll(createAdding(from = 11, to = 20, delta = 1))
  
  list.addAll(createSayNext(from = 21, to = 30, delta = 1))
  list.addAll(createAdding(from = 21, to = 30, delta = 1))
  
  list.addAll(createSayNext(from = 1, to = 10, delta = 2))
  list.addAll(createAdding(from = 1, to = 10, delta = 2))
  
  list.addAll(createSayNext(from = 11, to = 20, delta = 2))
  list.addAll(createAdding(from = 11, to = 20, delta = 2))
  
  list.addAll(createSayNext(from = 21, to = 30, delta = 2))
  list.addAll(createAdding(from = 21, to = 30, delta = 2))
  
  // +3
  list.addAll(createAdding(from = 1, to = 10, delta = 3))
  list.addAll(createAdding(from = 11, to = 20, delta = 3))
  list.addAll(createAdding(from = 21, to = 30, delta = 3))
  
//  +4
  list.addAll(createAdding(from = 31, to = 40, delta = 1))
  list.addAll(createAdding(from = 41, to = 50, delta = 1))
  list.addAll(createAdding(from = 51, to = 60, delta = 1))
  list.addAll(createAdding(from = 61, to = 70, delta = 1))
  list.addAll(createAdding(from = 71, to = 80, delta = 1))
  list.addAll(createAdding(from = 81, to = 90, delta = 1))
  list.addAll(createAdding(from = 91, to = 100, delta = 1))
 
  // adding 2
  list.addAll(createAdding(from = 31, to = 40, delta = 2))
  list.addAll(createAdding(from = 41, to = 50, delta = 2))
  list.addAll(createAdding(from = 51, to = 60, delta = 2))
  list.addAll(createAdding(from = 61, to = 70, delta = 2))
  list.addAll(createAdding(from = 71, to = 80, delta = 2))
  list.addAll(createAdding(from = 81, to = 90, delta = 2))
  list.addAll(createAdding(from = 91, to = 100, delta = 2))
  
 // adding 3
  list.addAll(createAdding(from = 31, to = 40, delta = 3))
  list.addAll(createAdding(from = 41, to = 50, delta = 3))
  list.addAll(createAdding(from = 51, to = 60, delta = 3))
  list.addAll(createAdding(from = 61, to = 70, delta = 3))
  list.addAll(createAdding(from = 71, to = 80, delta = 3))
  list.addAll(createAdding(from = 81, to = 90, delta = 3))
  list.addAll(createAdding(from = 91, to = 100, delta =3))

//  adding 4
  list.addAll(createAdding(from = 1, to = 10, delta = 4))
  list.addAll(createAdding(from = 11, to = 20, delta = 4))
  list.addAll(createAdding(from = 21, to = 30, delta = 4))
  list.addAll(createAdding(from = 31, to = 40, delta = 4))
  list.addAll(createAdding(from = 41, to = 50, delta = 4))
  list.addAll(createAdding(from = 51, to = 60, delta = 4))
  list.addAll(createAdding(from = 61, to = 70, delta = 4))
  list.addAll(createAdding(from = 71, to = 80, delta = 4))
  list.addAll(createAdding(from = 81, to = 90, delta = 4))
  list.addAll(createAdding(from = 91, to = 100, delta =4))
  
  //  +5
  list.addAll(createAdding(from = 1, to = 10, delta = 5))
  list.addAll(createAdding(from = 11, to = 20, delta = 5))
  list.addAll(createAdding(from = 21, to = 30, delta = 5))
  list.addAll(createAdding(from = 31, to = 40, delta = 5))
  list.addAll(createAdding(from = 41, to = 50, delta = 5))
  list.addAll(createAdding(from = 51, to = 60, delta = 5))
  list.addAll(createAdding(from = 61, to = 70, delta = 5))
  list.addAll(createAdding(from = 71, to = 80, delta = 5))
  list.addAll(createAdding(from = 81, to = 90, delta = 5))
  list.addAll(createAdding(from = 91, to = 100, delta =5))
  
  //  +6
  list.addAll(createAdding(from = 1, to = 10, delta = 6))
  list.addAll(createAdding(from = 11, to = 20, delta = 6))
  list.addAll(createAdding(from = 21, to = 30, delta = 6))
  list.addAll(createAdding(from = 31, to = 40, delta = 6))
  list.addAll(createAdding(from = 41, to = 50, delta = 6))
  list.addAll(createAdding(from = 51, to = 60, delta = 6))
  list.addAll(createAdding(from = 61, to = 70, delta = 6))
  list.addAll(createAdding(from = 71, to = 80, delta = 6))
  list.addAll(createAdding(from = 81, to = 90, delta = 6))
  list.addAll(createAdding(from = 91, to = 100, delta =6))
  
  //  +7
  list.addAll(createAdding(from = 1, to = 10, delta = 7))
  list.addAll(createAdding(from = 11, to = 20, delta = 7))
  list.addAll(createAdding(from = 21, to = 30, delta = 7))
  list.addAll(createAdding(from = 31, to = 40, delta = 7))
  list.addAll(createAdding(from = 41, to = 50, delta = 7))
  list.addAll(createAdding(from = 51, to = 60, delta = 7))
  list.addAll(createAdding(from = 61, to = 70, delta = 7))
  list.addAll(createAdding(from = 71, to = 80, delta = 7))
  list.addAll(createAdding(from = 81, to = 90, delta = 7))
  list.addAll(createAdding(from = 91, to = 100, delta =7))
  
  //  +8
  list.addAll(createAdding(from = 1, to = 10, delta = 8))
  list.addAll(createAdding(from = 11, to = 20, delta = 8))
  list.addAll(createAdding(from = 21, to = 30, delta = 8))
  list.addAll(createAdding(from = 31, to = 40, delta = 8))
  list.addAll(createAdding(from = 41, to = 50, delta = 8))
  list.addAll(createAdding(from = 51, to = 60, delta = 8))
  list.addAll(createAdding(from = 61, to = 70, delta = 8))
  list.addAll(createAdding(from = 71, to = 80, delta = 8))
  list.addAll(createAdding(from = 81, to = 90, delta = 8))
  list.addAll(createAdding(from = 91, to = 100, delta =8))
  
  //  +9
  list.addAll(createAdding(from = 1, to = 10, delta = 9))
  list.addAll(createAdding(from = 11, to = 20, delta = 9))
  list.addAll(createAdding(from = 21, to = 30, delta = 9))
  list.addAll(createAdding(from = 31, to = 40, delta = 9))
  list.addAll(createAdding(from = 41, to = 50, delta = 9))
  list.addAll(createAdding(from = 51, to = 60, delta = 9))
  list.addAll(createAdding(from = 61, to = 70, delta = 9))
  list.addAll(createAdding(from = 71, to = 80, delta = 9))
  list.addAll(createAdding(from = 81, to = 90, delta = 9))
  list.addAll(createAdding(from = 91, to = 100, delta =9))
  
  //  +9
  list.addAll(createAdding(from = 1, to = 10, delta = 10))
  list.addAll(createAdding(from = 11, to = 20, delta = 10))
  list.addAll(createAdding(from = 21, to = 30, delta = 10))
  list.addAll(createAdding(from = 31, to = 40, delta = 10))
  list.addAll(createAdding(from = 41, to = 50, delta = 10))
  list.addAll(createAdding(from = 51, to = 60, delta = 10))
  list.addAll(createAdding(from = 61, to = 70, delta = 10))
  list.addAll(createAdding(from = 71, to = 80, delta = 10))
  list.addAll(createAdding(from = 81, to = 90, delta = 10))
  list.addAll(createAdding(from = 91, to = 100, delta =10))
  
  return list
}

fun createRawTreeSkill():List<WorkoutRaw>{
  val list= mutableListOf<WorkoutRaw>()
  list.addAll(createAdding())
  return list
}

fun createDateBase():List<WorkoutDb>{
  val list= createRawTreeSkill()
  val workouts= mutableListOf<WorkoutDb>()
  var order=100
  for (raw in list){
    workouts.add(WorkoutDb(order =order, raw = raw))
    order += 50
  }
  return workouts
}