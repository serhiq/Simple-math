package com.gmail.uia059466.simplemath.forgit.data.workout.addverbal

import com.gmail.uia059466.simplemath.forgit.R

import com.gmail.uia059466.simplemath.forgit.data.workout.Interval
import com.gmail.uia059466.simplemath.forgit.data.workout.Workout
import com.gmail.uia059466.simplemath.forgit.games.HelpUtils
import com.gmail.uia059466.simplemath.forgit.games.IGame
import com.gmail.uia059466.simplemath.forgit.games.examplescreate.AdditionExamplesPermute
import com.gmail.uia059466.simplemath.forgit.games.lessonverbal.VerbalLesson
import com.gmail.uia059466.simplemath.forgit.utils.StringWrapper

class AddVerbalRandomPermute(val from: Int, val to: Int, val add: Int, override val id: Int) :
        Workout {
  
  override fun getIGames(): List<IGame> {
    val list = AdditionExamplesPermute(from, to, add)
            .createExamples().shuffled()
    val state =
            VerbalLesson(
                    data = list,
                    id = id,
                    help = HelpUtils.helpAddVerbalPermute(),
                    interval = Interval(from,to)
                                                                                             )
    return listOf(state)
  }
  
  override fun generateName(): StringWrapper {
    return StringWrapper(
            R.string.add_verbal_random,
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