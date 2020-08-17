package com.gmail.uia059466.simplemath.forgit.setting.about

import com.gmail.uia059466.simplemath.forgit.utils.IAction
import com.gmail.uia059466.simplemath.forgit.utils.IViewState

class CreateAboutSetting : IAction {
  override fun createState(): List<IViewState> {
    val state = SettingAboutViewState.SettingMainState()
    return listOf(state)
  }
}