package com.gmail.uia059466.simplemath.forgit.setting.general

import com.gmail.uia059466.simplemath.forgit.utils.MviAction

sealed class SettingGeneralAction : MviAction {
  object RunMainFragment         : SettingGeneralAction()
  object RunAbout                : SettingGeneralAction()
  object RunForPartsOfFastGame   : SettingGeneralAction()
  class  IsUseFocusMode    (val value: Boolean) : SettingGeneralAction()
  class  IsUseRuler        (val value: Boolean) : SettingGeneralAction()
}