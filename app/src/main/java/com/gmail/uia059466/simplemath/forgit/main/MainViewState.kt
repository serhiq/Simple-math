package com.gmail.uia059466.simplemath.forgit.main

import com.gmail.uia059466.simplemath.forgit.utils.FragmentType
import com.gmail.uia059466.simplemath.forgit.utils.IViewState

sealed class MainViewState : IViewState {
  
  object MainViewState : IViewState {
    override val fragmentType = FragmentType.MAIN
  }
}