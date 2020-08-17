package com.gmail.uia059466.simplemath.forgit.fastlesson.ruler

import com.gmail.uia059466.simplemath.forgit.data.workout.Interval

fun createRulerState(interval: Interval): RulerViewState {
  val tempFrom = interval.from - 5
  val newFrom = if (tempFrom < 0) 0 else tempFrom
  val rulerIntData = (newFrom..interval.to + 5).toList()
  val indexFrom = rulerIntData.indexOf(interval.from)
  
  val data = rulerIntData.map { NumberOfRuler(it.toString()) }
  
  val scrollTo = when (indexFrom) {
    0 -> 0
    else -> indexFrom - 1
  }
  return RulerViewState(data, scrollTo = scrollTo)
}
