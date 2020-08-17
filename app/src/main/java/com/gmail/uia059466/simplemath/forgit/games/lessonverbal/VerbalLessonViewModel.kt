package com.gmail.uia059466.simplemath.forgit.games.lessonverbal

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.gmail.uia059466.simplemath.forgit.fastlesson.Gateway
import com.gmail.uia059466.simplemath.forgit.fastlesson.ResultGame
import com.gmail.uia059466.simplemath.forgit.fastlesson.gameend.ResultRepository
import com.gmail.uia059466.simplemath.forgit.games.lessonverbal.VerbalLessonAction.NextCard
import com.gmail.uia059466.simplemath.forgit.listworkout.CreateStateWorkoutListFromId
import com.gmail.uia059466.simplemath.forgit.main.CreateStateMain
import com.gmail.uia059466.simplemath.forgit.utils.FragmentType
import com.gmail.uia059466.simplemath.forgit.utils.IAction
import com.gmail.uia059466.simplemath.forgit.utils.ISuspendAction
import com.gmail.uia059466.simplemath.forgit.utils.SingleLiveEvent
import com.gmail.uia059466.simplemath.forgit.utils.StateProvider
import kotlinx.coroutines.launch
import timber.log.Timber

class VerbalLessonViewModel(
        application: Application,
        private val stateProvider: StateProvider,
        private val repository   : Gateway,
        private val resultRepository: ResultRepository
                           ) : AndroidViewModel(application) {
  
  private val typeScreen = FragmentType.TEST_LESSON
  
  private val _state = MutableLiveData<VerbalLessonViewState>()
          val  state: LiveData<VerbalLessonViewState> = _state
  
  private val _stateProgress = MutableLiveData<Progress>()
          val  stateProgress: LiveData<Progress> = _stateProgress
 
  private val _runFragment = SingleLiveEvent<FragmentType>()
          val  runFragment: LiveData<FragmentType> = _runFragment
  
  lateinit var game:VerbalLessonEngine
  
  fun init() {
    val state = stateProvider.getActualState(typeScreen)
    
    if (state is VerbalLessonViewState) {
      val size=state.game.data.size
      game=state.game
      _state.postValue(state)
      _stateProgress.postValue(createProgress(size))
    }
    else {
      //       oops
      Timber.d("incorrect state ${state.fragmentType}")
    }
  }
  
  fun takeAction(action: VerbalLessonAction) {
    return when (action) {
     is NextCard -> nextCard(action)
      VerbalLessonAction.Back -> comeBack()
    }
  }
  
  private fun comeBack() {
    val state = state.value
    val id = when (state) {
      is VerbalLessonViewState -> state.id
      else -> null
    }
    
    when (id) {
      null -> prepareNextScreen(CreateStateMain())
      else -> prepareNextScreen(CreateStateWorkoutListFromId(id))
    }
  }
  
  private fun nextCard(action: NextCard) {
    if (action.error.isNotBlank()) {
      game.movingErrorList()
    } else {
      game.movingRightList()
    }
    
    val progress = updateProgress(_stateProgress.value!!)
    
    if (progress.isLast) {
      saveResultIfNeed()
      launchNextGame()
    } else {
      _stateProgress.postValue(progress)
    }
  }
  
  private fun saveResultIfNeed() {
    if (game.isSavable()){
      saveResult(game.getResult())
    }
  }
  
  private fun saveResult(result: ResultGame) {
    resultRepository.saveResult(result)
    val targetResult=resultRepository.getUserTarget()
    if (result.correctPercent >=targetResult){
      if (result.id!=null){
        finishGame(result.id)
      }
    }
  }
  
  private fun finishGame(id: Int) {
    viewModelScope.launch {
      repository.finishGame(id)
    }
  }
  
  private fun launchNextGame() {
    val state = state.value
    val id = when (state) {
      is VerbalLessonViewState -> state.id
      else -> null
    }
    runNextGame(id)
  }
  
  private fun runNextGame(id: Int?) {
    viewModelScope.launch {
      val nextScreenType = stateProvider.getNextGame(id)
      _runFragment.value = nextScreenType
    }
  }
  
  
  private fun prepareNextScreen(action: ISuspendAction) {
    viewModelScope.launch {
      val nextScreenType = stateProvider.createNextState(action)
      _runFragment.value = nextScreenType
    }
  }
  
  private fun prepareNextScreen(action: IAction) {
    val nextScreenType = stateProvider.createNextState(action)
    _runFragment.value = nextScreenType
  }
}