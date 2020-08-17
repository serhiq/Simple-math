package com.gmail.uia059466.simplemath.forgit.games.examplescreate

import com.gmail.uia059466.simplemath.forgit.games.expression.SumExpression

class AdditionExamples(val from:Int, val to:Int, val add: Int) : IStrategyCreateExamples {
  override fun createExamples(): List<String> {
    val list = mutableListOf<String>()
    for (i in from..to) {
      val expression=
              SumExpression(
                      number_1 = i,
                      number_2 = add,
                      id = i
                                                                                              )
    
      list.add(expression.getMainPart())
    }
    return list
  }
}