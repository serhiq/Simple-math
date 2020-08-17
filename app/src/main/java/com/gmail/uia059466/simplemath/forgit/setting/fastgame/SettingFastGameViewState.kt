package com.gmail.uia059466.simplemath.forgit.setting.fastgame

import com.gmail.uia059466.simplemath.forgit.utils.FragmentType
import com.gmail.uia059466.simplemath.forgit.utils.IViewState

sealed class SettingFastGameViewState : IViewState {
  class SettingFastGame(
          val isUseSayNext           : Boolean,
          val isUseAdditionVerbal    : Boolean
                       ) : SettingFastGameViewState() {
    
    override val fragmentType = FragmentType.SETTING_FAST_GAME
  }
}