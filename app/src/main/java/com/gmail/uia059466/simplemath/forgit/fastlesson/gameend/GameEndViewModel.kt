package com.gmail.uia059466.simplemath.forgit.fastlesson.gameend

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gmail.uia059466.simplemath.forgit.listworkout.CreateStateWorkoutListFromId
import com.gmail.uia059466.simplemath.forgit.main.CreateStateMain
import com.gmail.uia059466.simplemath.forgit.setting.general.CreateStateSetting
import com.gmail.uia059466.simplemath.forgit.utils.FragmentType
import com.gmail.uia059466.simplemath.forgit.utils.IAction
import com.gmail.uia059466.simplemath.forgit.utils.ISuspendAction
import com.gmail.uia059466.simplemath.forgit.utils.SingleLiveEvent
import com.gmail.uia059466.simplemath.forgit.utils.StateProvider
import kotlinx.coroutines.launch
import timber.log.Timber

class GameEndViewModel(
        application: Application,
        private val stateProvider: StateProvider
                      ) : AndroidViewModel(application) {

  private val typeScreen = FragmentType.RESULT

  private val _state =
    MutableLiveData<GameEndViewState.ResultState>()

  val state: LiveData<GameEndViewState.ResultState> = _state

  private val _runFragment =
    SingleLiveEvent<FragmentType>()
  
  val runFragment: LiveData<FragmentType> = _runFragment

  init {
    val state = stateProvider.getActualState(typeScreen)

    if (state is GameEndViewState.ResultState) {
      _state.postValue(state)
    } else {
      //       oops
      Timber.d("incorrect state ${state.fragmentType}")
    }
  }

  fun takeAction(action: GameEndAction) {
    return when (action) {
      GameEndAction.RunMain     -> prepareNextScreen(CreateStateMain())
      GameEndAction.RunComeBack -> runComeBack()
      GameEndAction.RunSetting  -> prepareNextScreen(CreateStateSetting())
    }
  }
  
  private fun runComeBack() {
    val state = state.value
    val id = when (state) {
      is GameEndViewState.ResultState -> state.id
      else -> null
    }
    runBack(id)
  }
  
  private fun runBack(id: Int?) {
    when(id==null){
      true  -> prepareNextScreen(CreateStateMain())
      false -> prepareNextScreen(CreateStateWorkoutListFromId(id))
    }
  }
  
  private fun prepareNextScreen(action: IAction) {
        val nextScreenType = stateProvider.createNextState(action)
        _runFragment.value = nextScreenType
    }

  private fun prepareNextScreen(action: ISuspendAction) {
    viewModelScope.launch {
      val nextScreenType = stateProvider.createNextState(action)
      _runFragment.value = nextScreenType
    }
  }
}