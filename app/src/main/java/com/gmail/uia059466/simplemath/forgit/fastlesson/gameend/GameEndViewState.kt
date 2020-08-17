package com.gmail.uia059466.simplemath.forgit.fastlesson.gameend

import com.gmail.uia059466.simplemath.forgit.utils.FragmentType
import com.gmail.uia059466.simplemath.forgit.utils.IViewState

sealed class GameEndViewState : IViewState {
  class ResultState(
          val idImg    : Int,
          val titleId  : Int,
          val acc      : String,
          val speed    : Int,
          val error    : String,
          val id       :Int?
                   ) : IViewState {
    
    override val fragmentType = FragmentType.RESULT
  }
}