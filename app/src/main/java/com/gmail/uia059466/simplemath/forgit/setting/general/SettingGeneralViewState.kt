package com.gmail.uia059466.simplemath.forgit.setting.general

import com.gmail.uia059466.simplemath.forgit.utils.FragmentType
import com.gmail.uia059466.simplemath.forgit.utils.IViewState

sealed class SettingGeneralViewState : IViewState {
  class SettingMainState(
          val isUseFocusMode :Boolean,
          val isUseRuler     :Boolean
                        ) : SettingGeneralViewState() {
    
    override val fragmentType = FragmentType.SETTING_GENERAL
  }
}