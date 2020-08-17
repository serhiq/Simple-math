package com.gmail.uia059466.simplemath.forgit.setting.fastgame

import com.gmail.uia059466.simplemath.forgit.data.Skill
import com.gmail.uia059466.simplemath.forgit.utils.MviAction

sealed class SettingFastGameAction : MviAction {
  object RunGeneralSetting : SettingFastGameAction()
  class  IncludeInComplexGame(val type: Skill, val isInclude: Boolean) : SettingFastGameAction()
}