package com.gmail.uia059466.simplemath.forgit.listskill

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.gmail.uia059466.simplemath.forgit.listskill.SkillListAction.RunMain
import com.gmail.uia059466.simplemath.forgit.listskill.SkillListAction.RunWorkoutList
import com.gmail.uia059466.simplemath.forgit.listworkout.CreateStateWorkoutList
import com.gmail.uia059466.simplemath.forgit.main.CreateStateMain
import com.gmail.uia059466.simplemath.forgit.utils.FragmentType
import com.gmail.uia059466.simplemath.forgit.utils.IAction
import com.gmail.uia059466.simplemath.forgit.utils.ISuspendAction
import com.gmail.uia059466.simplemath.forgit.utils.SingleLiveEvent
import com.gmail.uia059466.simplemath.forgit.utils.StateProvider
import kotlinx.coroutines.launch
import timber.log.Timber

class SkillListViewModel(
        application: Application,
        private val stateProvider: StateProvider
                        ) : AndroidViewModel(application) {

  private val _runFragment = SingleLiveEvent<FragmentType>()
          val  runFragment: LiveData<FragmentType> = _runFragment
  
  private val typeScreen = FragmentType.SKILL_LIST

  private val _state = SingleLiveEvent<SkillListViewState>()
          val  state: LiveData<SkillListViewState> = _state

  init {
    val state = stateProvider.getActualState(typeScreen)

    if (state is SkillListViewState) {
      _state.postValue(state)
    } else {
      //       oops
      Timber.d("incorrect state ${state.fragmentType}")
    }
  }

  fun takeAction(action: SkillListAction) {
   return when (action) {
      is RunMain      -> prepareNextScreen  (CreateStateMain())
      is RunWorkoutList -> prepareNextScreen(CreateStateWorkoutList(skill =action.skill))
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