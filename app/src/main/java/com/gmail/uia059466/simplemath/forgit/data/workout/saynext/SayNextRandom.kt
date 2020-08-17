package com.gmail.uia059466.simplemath.forgit.data.workout.saynext

import com.gmail.uia059466.simplemath.forgit.R

import com.gmail.uia059466.simplemath.forgit.data.workout.Interval
import com.gmail.uia059466.simplemath.forgit.data.workout.Workout
import com.gmail.uia059466.simplemath.forgit.games.HelpUtils
import com.gmail.uia059466.simplemath.forgit.games.IGame
import com.gmail.uia059466.simplemath.forgit.games.lessonverbal.VerbalLesson
import com.gmail.uia059466.simplemath.forgit.games.lessonverbal.saynext.SayNext
import com.gmail.uia059466.simplemath.forgit.utils.StringWrapper

class SayNextRandom(val from: Int, val to: Int, override val id: Int, val add: Int) :
        Workout {
  
  override fun getIGames(): List<IGame> {
        val list = SayNext(from, to, add)
            .createExamples().shuffled()
    val state =
            VerbalLesson(
                    data = list,
                    id = id,
                    help = HelpUtils.helpSayNext(add),
                    interval = Interval(from,to)
                                                                                             )
    
    return listOf(state)
  }
  
  override fun generateName(): StringWrapper {
    val id= when (add) {
      2    -> R.string.say_next_from_1_random
      else -> R.string.say_next_random
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