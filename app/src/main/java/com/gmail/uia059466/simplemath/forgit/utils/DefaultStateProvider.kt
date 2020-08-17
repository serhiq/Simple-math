package com.gmail.uia059466.simplemath.forgit.utils

import com.gmail.uia059466.simplemath.forgit.fastlesson.Gateway
import com.gmail.uia059466.simplemath.forgit.fastlesson.gameend.CreateGameEndState
import com.gmail.uia059466.simplemath.forgit.main.MainViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.util.Queue
import java.util.concurrent.ConcurrentLinkedQueue

class DefaultStateProvider(private val gateway: Gateway) : StateProvider {

    companion object {
        private var INSTANCE: DefaultStateProvider? = null

        fun getInstance(repository: Gateway): DefaultStateProvider {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = DefaultStateProvider(repository)
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

    private var queue: Queue<IViewState> = ConcurrentLinkedQueue<IViewState>()
    
    override fun getNextScreenType(): FragmentType {
    val state = queue.peek()
    return when (state) {
        null -> FragmentType.MAIN
        else -> state.fragmentType
    }
    }
    
    override fun cleanQueue() {
        queue.clear()
    }

    override fun createNextState(action: IAction): FragmentType {
        val state = action.createState()
        queue.clear()
        queue.addAll(state)
        return getNextScreenType()
    }
    
    override suspend fun createNextState(action: ISuspendAction): FragmentType {
        return withContext(Dispatchers.IO) {
            val state = action.createState(gateway)
            queue.clear()
            queue.addAll(state)
            return@withContext getNextScreenType()
        }
    }
    
    override fun getActualState(type: FragmentType): IViewState {
        val state = queue.poll()

        return when {
            state==null ->  MainViewState.MainViewState
            type == state.fragmentType -> state
            else -> {
                Timber.d("incorrect state ${state.fragmentType}")
                MainViewState.MainViewState
            }
        }
    }
  
    override suspend fun getNextGame(id: Int?): FragmentType {
      return withContext(Dispatchers.IO) {
          if (queue.size==0){
              addingGameEnd(id)
          }
          return@withContext getNextScreenType()
      }
  }
    
    private suspend fun addingGameEnd(id: Int?) {
        val states =
            CreateGameEndState(id).createState(gateway)
          queue.addAll(states)
    }
}