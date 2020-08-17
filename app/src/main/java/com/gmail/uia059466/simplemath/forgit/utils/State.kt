package com.gmail.uia059466.simplemath.forgit.utils

import com.gmail.uia059466.simplemath.forgit.fastlesson.Gateway

interface IAction {
  fun createState(): List<IViewState>
}

interface ISuspendAction {
  suspend fun createState(gateway: Gateway): List<IViewState>
}