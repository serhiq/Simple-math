package com.gmail.uia059466.simplemath.forgit.listworkout

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.gmail.uia059466.simplemath.forgit.fastlesson.CreateStateSingleWorkout
import com.gmail.uia059466.simplemath.forgit.listskill.CreateStateListSkill
import com.gmail.uia059466.simplemath.forgit.listworkout.WorkoutListAction.*
import com.gmail.uia059466.simplemath.forgit.utils.FragmentType
import com.gmail.uia059466.simplemath.forgit.utils.ISuspendAction
import com.gmail.uia059466.simplemath.forgit.utils.SingleLiveEvent
import com.gmail.uia059466.simplemath.forgit.utils.StateProvider
import kotlinx.coroutines.launch
import timber.log.Timber

class WorkoutListViewModel(
        application: Application,
        private val stateProvider: StateProvider
                          ) : AndroidViewModel(application) {
  
  private val _runFragment = SingleLiveEvent<FragmentType>()
          val runFragment: LiveData<FragmentType> = _runFragment
  
  private val typeScreen = FragmentType.WORKOUT_LIST
  
  private val _state = SingleLiveEvent<WorkoutListViewState>()
          val  state: LiveData<WorkoutListViewState> = _state
  
  init {
    val state = stateProvider.getActualState(typeScreen)
    
    if (state is WorkoutListViewState) {
      _state.postValue(state)
    } else {
      //       oops
      Timber.d("incorrect state ${state.fragmentType}")
    }
  }
  
  fun takeAction(action: WorkoutListAction) {
    return when (action) {
      is RunWorkout    -> prepareNextScreen(CreateStateSingleWorkout(action.workout))
         RunSkillList  -> prepareNextScreen(CreateStateListSkill())
    }
  }
  
  private fun prepareNextScreen(action: ISuspendAction) {
    viewModelScope.launch {
      val nextScreenType = stateProvider.createNextState(action)
      _runFragment.value = nextScreenType
    }
  }
}