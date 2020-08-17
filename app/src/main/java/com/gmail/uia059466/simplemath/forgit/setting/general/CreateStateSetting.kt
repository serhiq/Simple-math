package com.gmail.uia059466.simplemath.forgit.setting.general

import com.gmail.uia059466.simplemath.forgit.fastlesson.Gateway
import com.gmail.uia059466.simplemath.forgit.utils.ISuspendAction
import com.gmail.uia059466.simplemath.forgit.utils.IViewState

class CreateStateSetting : ISuspendAction {
  override suspend fun createState(gateway: Gateway): List<IViewState> {
    val state = SettingGeneralViewState.SettingMainState(
                                                         isUseFocusMode = gateway.isFocusMode(),
                                                        isUseRuler      = gateway.isUseRuler())
    return listOf(state)
  }
}