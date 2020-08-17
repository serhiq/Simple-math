package com.gmail.uia059466.simplemath.forgit.data.workout.addverbal

import com.gmail.uia059466.simplemath.forgit.R

import com.gmail.uia059466.simplemath.forgit.data.workout.Interval
import com.gmail.uia059466.simplemath.forgit.data.workout.Workout
import com.gmail.uia059466.simplemath.forgit.games.HelpUtils
import com.gmail.uia059466.simplemath.forgit.games.IGame
import com.gmail.uia059466.simplemath.forgit.games.examplescreate.AdditionExamples
import com.gmail.uia059466.simplemath.forgit.games.lessonverbal.VerbalLesson
import com.gmail.uia059466.simplemath.forgit.utils.StringWrapper

class AddVerbalReverse(val from: Int, val to: Int, val add: Int, override val id: Int) :
        Workout {
  
  override fun getIGames(): List<IGame> {
    val list = AdditionExamples(from, to, add)
            .createExamples().reversed()
    val state =
            VerbalLesson(
                    data = list,
                    id = id,
                    help = HelpUtils.helpAddVerbal(),
                    interval = Interval(from,to)
                                                                                             )
    return listOf(state)
  }
  
  override fun generateName(): StringWrapper {
    return StringWrapper(
            R.string.add_verbal_reverse,
            listOf(add.toString())
                                                                                )
  }
  
  override fun generateDescription(): StringWrapper {
    return StringWrapper(
            R.string.from_to,
            listOf(from.toString(), to.toString())
                                                                                )
  }
}