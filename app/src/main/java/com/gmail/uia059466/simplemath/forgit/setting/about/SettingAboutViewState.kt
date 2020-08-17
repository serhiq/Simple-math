package com.gmail.uia059466.simplemath.forgit.setting.about

import com.gmail.uia059466.simplemath.forgit.utils.FragmentType
import com.gmail.uia059466.simplemath.forgit.utils.IViewState

sealed class SettingAboutViewState : IViewState {
  class SettingMainState: SettingAboutViewState() {
    override val fragmentType = FragmentType.SETTING_ABOUT
  }
}