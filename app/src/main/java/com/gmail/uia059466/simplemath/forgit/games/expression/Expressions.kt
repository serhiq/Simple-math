package com.gmail.uia059466.simplemath.forgit.games.expression

interface Expression {
  val number_1: Int
  val number_2: Int
  val id      : Int
  
  fun getResult(): Int
  fun getMainPart(): String
  fun getResultPart(): String
  fun getTips(): String
}

class SumExpression(
        override val number_1: Int,
        override val number_2: Int,
        override val id: Int
                   ) : Expression {
  
  override fun getMainPart(): String {
    return "$number_1 + $number_2"
  }
  
  override fun getResultPart(): String {
    val result = getResult()
    return " = $result"
  }
  
  override fun getTips(): String {
    return getResultPart()
  }
  
  override fun getResult(): Int {
    return number_1 + number_2
  }
}

class ExampleForDisplay(private val expression: Expression, val isShowTips: Boolean) {
  val main: String = expression.getMainPart()
  val result: String = expression.getResultPart()
  val cards: List<CardDisplay> = generateCard()
  
  private fun generateCard(): List<CardDisplay> {
    val result = expression.getResult()
    val listCardValue=generateCardValueGranted(result)
    
    val listCard = mutableListOf<CardDisplay>()
    for (i in listCardValue.indices) {
      val card = CardDisplay(
              value = listCardValue[i].toString(),
              isRight = listCardValue[i] == result
                                                                                               )
      listCard.add(card)
    }
    return listCard
  }
  

}

class CardDisplay(
        val isRight: Boolean,
        val value: String
                 )

fun generateCardValueGranted(result: Int): List<Int> {
  val minus = result - 5
  val plus = result + 5
  
  val from: Int = if (minus >= 5) minus else 0
  val to = plus
  
  val wrongV = mutableListOf<Int>()
  for (i in from..to) {
    if (i != result) {
      wrongV.add(i)
    }
  }
  val picked = wrongV.shuffled().take(3 - 1)
  
  val listV = mutableListOf<Int>()
  listV.add(result)
  listV.addAll(picked)
  
  if (listV.size!=3){
    while (listV.size!=3){
      listV.add(listV.random())
    }
  }
  return listV.shuffled()
}