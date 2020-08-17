package com.gmail.uia059466.simplemath.forgit.utils

interface StateProvider {
          fun getNextScreenType()                         : FragmentType
  suspend fun getNextGame      (id: Int?): FragmentType
          fun cleanQueue       ()
          fun createNextState  (action: IAction)          : FragmentType
  suspend fun createNextState  (action: ISuspendAction)   : FragmentType
          fun getActualState   (type: FragmentType)       : IViewState
}