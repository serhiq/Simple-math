package com.gmail.uia059466.simplemath.forgit.games.lessonverbal

import com.gmail.uia059466.simplemath.forgit.fastlesson.ruler.IGameWithRuler
import com.gmail.uia059466.simplemath.forgit.fastlesson.ruler.RulerViewState
import com.gmail.uia059466.simplemath.forgit.games.HelpMessage
import com.gmail.uia059466.simplemath.forgit.games.IGameViewState
import com.gmail.uia059466.simplemath.forgit.utils.FragmentType
import com.gmail.uia059466.simplemath.forgit.utils.IViewState

class VerbalLessonViewState(
        val game : VerbalLessonEngine,
        val id   : Int? = null,
        val help : HelpMessage
                           ) : IViewState,
                               IGameViewState, IGameWithRuler {
  
  override val fragmentType = FragmentType.TEST_LESSON
  
  override var isUseHelpButton :Boolean=false
  override var isUseHomeButton :Boolean=false
  override var ruler          : RulerViewState? = null
}