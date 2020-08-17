package com.gmail.uia059466.simplemath.forgit.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.gmail.uia059466.simplemath.forgit.fastlesson.CreateStateFastLesson
import com.gmail.uia059466.simplemath.forgit.listskill.CreateStateListSkill
import com.gmail.uia059466.simplemath.forgit.setting.general.CreateStateSetting
import com.gmail.uia059466.simplemath.forgit.utils.FragmentType
import com.gmail.uia059466.simplemath.forgit.utils.ISuspendAction
import com.gmail.uia059466.simplemath.forgit.utils.SingleLiveEvent
import com.gmail.uia059466.simplemath.forgit.utils.StateProvider
import kotlinx.coroutines.launch
import timber.log.Timber

class MainViewModel(
        application: Application,
        private val stateProvider: StateProvider
                   ) : AndroidViewModel(application) {
  
  private val _runFragment =  SingleLiveEvent<FragmentType>()
          val  runFragment: LiveData<FragmentType> = _runFragment
  
  private val typeScreen = FragmentType.MAIN
  
  init {
    val state = stateProvider.getActualState(typeScreen)
    
    if (state !is MainViewState) {
      //       oops
      Timber.d("incorrect state ${state.fragmentType}")
    }
  }
  
  fun takeAction(action: MainAction) {
    return when(action){
      MainAction.RunFastLesson   -> prepareNextScreen(CreateStateFastLesson())
      MainAction.RunListSkill    -> prepareNextScreen(CreateStateListSkill())
      MainAction.RunSetting      -> prepareNextScreen(CreateStateSetting())
    }
  }
  
  private fun prepareNextScreen(action: ISuspendAction) {
    viewModelScope.launch {
      val nextScreenType = stateProvider.createNextState(action)
      _runFragment.value = nextScreenType
    }
  }
}