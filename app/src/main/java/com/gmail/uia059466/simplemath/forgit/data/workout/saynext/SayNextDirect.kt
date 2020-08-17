package com.gmail.uia059466.simplemath.forgit.data.workout.saynext

import com.gmail.uia059466.simplemath.forgit.R

import com.gmail.uia059466.simplemath.forgit.data.workout.Interval
import com.gmail.uia059466.simplemath.forgit.data.workout.Workout
import com.gmail.uia059466.simplemath.forgit.games.HelpUtils
import com.gmail.uia059466.simplemath.forgit.games.IGame
import com.gmail.uia059466.simplemath.forgit.games.lessonverbal.VerbalLesson
import com.gmail.uia059466.simplemath.forgit.games.lessonverbal.saynext.SayNext
import com.gmail.uia059466.simplemath.forgit.utils.StringWrapper

class SayNextDirect(val from: Int, val to: Int, override val id: Int, val add: Int) :
        Workout {
  
  override fun getIGames(): List<IGame> {
    val list = SayNext(from, to, add)
            .createExamples()
   return   listOf(
           VerbalLesson(
                   data = list,
                   id = id,
                   help = HelpUtils.helpSayNext(add),
                   interval = Interval(from,to)
                                                                                            )
                  )
  }
  
  override fun generateName(): StringWrapper {
    val id= when (add) {
      2    -> R.string.say_next_from_1
      else -> R.string.say_next
    }
    return StringWrapper(
            id, emptyList()
                                                                                )
  }
  
  override fun generateDescription(): StringWrapper {
    return StringWrapper(
            R.string.from_to,
            listOf(from.toString(), to.toString())
                                                                                )
  }
}