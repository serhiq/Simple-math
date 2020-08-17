package com.gmail.uia059466.simplemath.forgit.fastlesson.gameend

import com.gmail.uia059466.simplemath.forgit.R

import com.gmail.uia059466.simplemath.forgit.fastlesson.Gateway
import com.gmail.uia059466.simplemath.forgit.fastlesson.ResultGame
import com.gmail.uia059466.simplemath.forgit.utils.ISuspendAction
import com.gmail.uia059466.simplemath.forgit.utils.IViewState

class CreateGameEndState(val id: Int?) : ISuspendAction {
  
  override suspend fun createState(gateway: Gateway): List<IViewState> {
    val result = gateway.getResultRepository().getCurrentResult()
    
    return listOf(createGameEndResult(result,id))
  }
  private fun createGameEndResult(game: ResultGame, id: Int?): IViewState {
    val result = game.correctPercent
    val resultTitle =
            when {
              result > 95 -> UserGameEndMessage.EXCELLENT
              result > 75 -> UserGameEndMessage.GOOD
              
              else -> UserGameEndMessage.TRY_AGAIN
            }
    val drawable = when (result < 75) {
      true  -> R.drawable.game_end_sad_cat
      false -> R.drawable.game_end_happy_cat
    }
    
    return GameEndViewState.ResultState(
            idImg     = drawable,
            titleId   = resultTitle.stringRes(),
            acc       = "$result %",
            speed     =  game.speed?:0,
            error     = "${game.error}",
            id        = id
                                       )
  }
}