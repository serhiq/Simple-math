package com.gmail.uia059466.simplemath.forgit.games.lessonverbal

import com.gmail.uia059466.simplemath.forgit.data.workout.Interval
import com.gmail.uia059466.simplemath.forgit.games.HelpMessage
import com.gmail.uia059466.simplemath.forgit.games.IGame
import com.gmail.uia059466.simplemath.forgit.utils.IViewState

class VerbalLesson(val data: List<String>, override val id: Int?, val help: HelpMessage,val interval: Interval) : IGame {
  private val game: VerbalLessonEngine = VerbalLessonEngine(idCode = id, data = data)
  
  override fun createViewState(id: Int?): IViewState {
    return VerbalLessonViewState(game = game, id = id, help = help)
  }
  
  override fun requestInterval(): Interval {
    return interval
  }
}