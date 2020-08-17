package com.gmail.uia059466.simplemath.forgit.fastlesson

class GameState(
        private val numberShowUnits: Int, val id: Int?) {
  
  private var startTime :Long = 0L
  private var endTime   :Long = 0L
          var error     :Int  = 0
  
  fun getResult(): ResultGame {
    val speed = calculateSpeed(endTime, startTime, numberShowUnits)
    val correct = calcCorrectPercentAcc(numberShowUnits, error)
    
    return ResultGame(speed = speed, error = error, correctPercent = correct, id = id)
  }
  
  private fun calculateSpeed(end: Long, start: Long, number: Int): Int {
    return (((end - start) / number) / 100).toInt()
  }
  
  private fun calcCorrectPercentAcc(numShow: Int, numError: Int): Int {
    if (numError == 0) return 100
    
    val onePercent = numShow.toDouble() / 100.00
    val percentErr = numError.toDouble() / onePercent
    val result = (100 - percentErr).toInt()
    
    return if (result > 0) result else 0
  }
  
  fun registerBegin() {
    startTime = System.currentTimeMillis()
  }
  
  fun registerEnd() {
    endTime = System.currentTimeMillis()
  }
  
  fun registerError() {
    error++
  }
}