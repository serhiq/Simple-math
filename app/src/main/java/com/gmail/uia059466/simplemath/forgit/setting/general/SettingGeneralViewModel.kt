package com.gmail.uia059466.simplemath.forgit.setting.general

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.gmail.uia059466.simplemath.forgit.fastlesson.gameend.Prefs
import com.gmail.uia059466.simplemath.forgit.main.CreateStateMain
import com.gmail.uia059466.simplemath.forgit.setting.about.CreateAboutSetting
import com.gmail.uia059466.simplemath.forgit.setting.fastgame.CreateStateFastGameSetting
import com.gmail.uia059466.simplemath.forgit.utils.FragmentType
import com.gmail.uia059466.simplemath.forgit.utils.IAction
import com.gmail.uia059466.simplemath.forgit.utils.ISuspendAction
import com.gmail.uia059466.simplemath.forgit.utils.SingleLiveEvent
import com.gmail.uia059466.simplemath.forgit.utils.StateProvider
import kotlinx.coroutines.launch
import timber.log.Timber

class SettingGeneralViewModel(
        application: Application,
        private val stateProvider: StateProvider
                             ) : AndroidViewModel(application) {
  private val pref = Prefs(application)
  private val typeScreen = FragmentType.SETTING_GENERAL
  
  private val _state = SingleLiveEvent<SettingGeneralViewState>()
          val  state: LiveData<SettingGeneralViewState> = _state
  
  private val _runFragment = SingleLiveEvent<FragmentType>()
          val  runFragment: LiveData<FragmentType> = _runFragment

  init {
    val state = stateProvider.getActualState(typeScreen)
    if (state is SettingGeneralViewState) {
      _state.postValue(state)
    } else {
      //       oops
      Timber.d("incorrect state ${state.fragmentType}")
    }
  }

  fun takeAction(action: SettingGeneralAction) {
    return when (action) {
      SettingGeneralAction.RunMainFragment        -> prepareNextScreen(CreateStateMain())
      SettingGeneralAction.RunForPartsOfFastGame  -> prepareNextScreen(CreateStateFastGameSetting())
      is SettingGeneralAction.IsUseFocusMode      -> pref.isUseFocus     = action.value
      is SettingGeneralAction.IsUseRuler          -> pref.isUserRuler    = action.value
      SettingGeneralAction.RunAbout                ->prepareNextScreen(CreateAboutSetting())
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