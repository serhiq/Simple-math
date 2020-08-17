package com.gmail.uia059466.simplemath.forgit.setting.about

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.gmail.uia059466.simplemath.forgit.setting.general.CreateStateSetting
import com.gmail.uia059466.simplemath.forgit.utils.FragmentType
import com.gmail.uia059466.simplemath.forgit.utils.ISuspendAction
import com.gmail.uia059466.simplemath.forgit.utils.SingleLiveEvent
import com.gmail.uia059466.simplemath.forgit.utils.StateProvider
import kotlinx.coroutines.launch
import timber.log.Timber

class SettingAboutViewModel(
        application: Application,
        private val stateProvider: StateProvider
                           ) : AndroidViewModel(application) {

  private val typeScreen = FragmentType.SETTING_ABOUT
  
  private val _state = SingleLiveEvent<SettingAboutViewState>()
          val  state: LiveData<SettingAboutViewState> = _state
  
  private val _runFragment = SingleLiveEvent<FragmentType>()
          val  runFragment: LiveData<FragmentType> = _runFragment
  
  private val  _link = SingleLiveEvent<String>()
          val   link: LiveData<String> = _link

  init {
    val state = stateProvider.getActualState(typeScreen)
    if (state is SettingAboutViewState) {
      _state.postValue(state)
    } else {
      //       oops
      Timber.d("incorrect state ${state.fragmentType}")
    }
  }

  fun takeAction(action: SettingAboutAction) {
    return when (action) {
      SettingAboutAction.DisplayOfficialWebsite   -> _link.value="https://sites.google.com/view/simple-math-app/page"
      SettingAboutAction.DisplayPrivacy           -> _link.value="https://sites.google.com/view/simple-math-app/privacy-policy"
      SettingAboutAction.DisplayTermOfUse         -> _link.value="https://sites.google.com/view/simple-math-app/term-of-use"
      SettingAboutAction.RunMainSetting -> prepareNextScreen(CreateStateSetting())
    }
  }

  private fun prepareNextScreen(action: ISuspendAction) {
    viewModelScope.launch {
      val nextScreenType = stateProvider.createNextState(action)
      _runFragment.value = nextScreenType
    }
  }
}