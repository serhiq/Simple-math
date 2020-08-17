package com.gmail.uia059466.simplemath.forgit.main

import com.gmail.uia059466.simplemath.forgit.utils.IAction
import com.gmail.uia059466.simplemath.forgit.utils.IViewState

class CreateStateMain : IAction {
  override fun createState(): List<IViewState> {
    return listOf(MainViewState.MainViewState)
  }
}