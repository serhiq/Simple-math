package com.gmail.uia059466.simplemath.forgit.games.lessonverbal.saynext

import com.gmail.uia059466.simplemath.forgit.games.examplescreate.IStrategyCreateExamples

class SayNext(val from: Int, val to: Int, val add: Int) : IStrategyCreateExamples {
  override fun createExamples(): List<String> {
    return when (add) {
      1  -> (from..to).toList().map { "$it ."  }
      2  -> (from..to).toList().map { "$it . ." }
      else -> throw IllegalArgumentException("Unknown add type")
    }
  }
}