package com.gmail.uia059466.simplemath.forgit.setting.fastgame

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gmail.uia059466.simplemath.forgit.data.Skill
import com.gmail.uia059466.simplemath.forgit.fastlesson.gameend.Prefs
import com.gmail.uia059466.simplemath.forgit.setting.general.CreateStateSetting
import com.gmail.uia059466.simplemath.forgit.utils.FragmentType
import com.gmail.uia059466.simplemath.forgit.utils.ISuspendAction
import com.gmail.uia059466.simplemath.forgit.utils.SingleLiveEvent
import com.gmail.uia059466.simplemath.forgit.utils.StateProvider
import kotlinx.coroutines.launch
import timber.log.Timber

class SettingFastGameViewModel(
        application: Application,
        private val stateProvider: StateProvider
                              ) : AndroidViewModel(application) {
  private val pref = Prefs(application)
          val typeScreen = FragmentType.SETTING_FAST_GAME

  private val _state = MutableLiveData<SettingFastGameViewState>()
          val  state: LiveData<SettingFastGameViewState> = _state

  private val _runFragment = SingleLiveEvent<FragmentType>()
          val  runFragment: LiveData<FragmentType> = _runFragment

 init{
    val state = stateProvider.getActualState(typeScreen)

    if (state is SettingFastGameViewState) {
      _state.postValue(state)
    } else {
      //       oops
      Timber.d("incorrect state ${state.fragmentType}")
    }
  }

  fun takeAction(action: SettingFastGameAction) {
    return when (action) {
      SettingFastGameAction.RunGeneralSetting              -> prepareNextScreen(CreateStateSetting())
      is SettingFastGameAction.IncludeInComplexGame        -> configureIncludeInComplexGame(action)
    }
  }

  private fun configureIncludeInComplexGame(action: SettingFastGameAction.IncludeInComplexGame) {
    return when (action.type) {
      Skill.SAY_NEXT            -> pref.isUseSayNext           = action.isInclude
      Skill.ADDITION_VERBAL     -> pref.isUseAdditionVerbal    = action.isInclude
    }
  }
  
  private fun prepareNextScreen(action: ISuspendAction) {
    viewModelScope.launch {
      val nextScreenType = stateProvider.createNextState(action)
      _runFragment.value = nextScreenType
    }
  }
}