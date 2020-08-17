package com.gmail.uia059466.simplemath.forgit.fastlesson

open class IFastGame(val id: Int? = null, numberShow: Int) {
  val state = GameState(numberShow, id)
  
  fun isSavable(): Boolean {
    return id != null
  }
  
  fun getResult(): ResultGame {
    return state.getResult()
  }
}