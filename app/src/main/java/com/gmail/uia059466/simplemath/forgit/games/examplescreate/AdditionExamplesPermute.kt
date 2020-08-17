package com.gmail.uia059466.simplemath.forgit.games.examplescreate

class AdditionExamplesPermute(val from: Int, val to: Int, val add: Int) :
        IStrategyCreateExamples {
  
  override fun createExamples(): List<String> {
    val list = mutableListOf<String>()
    for (i in from..to) {
      list.add("$add + $i")
    }
    return list
  }
}