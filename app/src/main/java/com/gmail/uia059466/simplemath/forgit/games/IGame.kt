package com.gmail.uia059466.simplemath.forgit.games

import com.gmail.uia059466.simplemath.forgit.data.workout.Interval
import com.gmail.uia059466.simplemath.forgit.utils.IViewState

interface IGame {
  val id: Int?
  fun createViewState(id: Int? = null): IViewState
  fun requestInterval()               : Interval
}
