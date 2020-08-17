package com.gmail.uia059466.simplemath.forgit.fastlesson

import com.gmail.uia059466.simplemath.forgit.data.workout.Workout
import com.gmail.uia059466.simplemath.forgit.fastlesson.ruler.IGameWithRuler
import com.gmail.uia059466.simplemath.forgit.fastlesson.ruler.createRulerState
import com.gmail.uia059466.simplemath.forgit.games.IGameViewState
import com.gmail.uia059466.simplemath.forgit.utils.ISuspendAction
import com.gmail.uia059466.simplemath.forgit.utils.IViewState

class CreateStateSingleWorkout(val workout: Workout) :
        ISuspendAction {
  
  override suspend fun createState(gateway: Gateway): List<IViewState> {
    val games=workout.getIGames()
    val gameStates= mutableListOf<IViewState>()
  
    for (game in games){
      val state=game.createViewState(workout.id)
      if (gateway.isUseRuler()&&state is IGameWithRuler) {
        state.ruler = createRulerState(game.requestInterval())
      }
      gameStates.add(state)
    }
  
    if (!gateway.isFocusMode()){
      gameStates.forEach { if (it is IGameViewState){it.isUseHelpButton=true; it.isUseHomeButton=true} }
    }
    return gameStates
  }
}