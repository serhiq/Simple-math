package com.gmail.uia059466.simplemath.forgit.setting.about

import com.gmail.uia059466.simplemath.forgit.utils.MviAction

sealed class SettingAboutAction : MviAction {
  object DisplayOfficialWebsite         : SettingAboutAction()
  object DisplayPrivacy                 : SettingAboutAction()
  object DisplayTermOfUse               : SettingAboutAction()
  object RunMainSetting                 : SettingAboutAction()
}