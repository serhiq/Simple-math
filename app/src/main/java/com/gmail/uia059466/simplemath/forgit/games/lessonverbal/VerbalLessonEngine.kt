package com.gmail.uia059466.simplemath.forgit.games.lessonverbal

import com.gmail.uia059466.simplemath.forgit.fastlesson.IFastGame

class VerbalLessonEngine(idCode:Int?, val data: List<String>): IFastGame(id=idCode, numberShow = data.size) {
  
  private var isGameStarted:Boolean=false
  private var isGameEnded:Boolean=false
  private var moving :Int =0
  
  fun movingRightList(){
    registerMoving()
  }
  
  fun movingErrorList(){
    state.registerError()
    registerMoving()
  }
  
  private fun registerMoving(){
    if (!isGameStarted) {isGameStarted=true; state.registerBegin()}
    moving++
    if (moving==data.size){
      isGameEnded=true
      state.registerEnd()
    }
  }
}