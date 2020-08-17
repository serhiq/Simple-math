package com.gmail.uia059466.simplemath.forgit.setting.fastgame

import com.gmail.uia059466.simplemath.forgit.data.Skill
import com.gmail.uia059466.simplemath.forgit.fastlesson.Gateway
import com.gmail.uia059466.simplemath.forgit.utils.ISuspendAction
import com.gmail.uia059466.simplemath.forgit.utils.IViewState

class CreateStateFastGameSetting : ISuspendAction {

    override suspend fun createState(gateway: Gateway): List<IViewState> {
        val state= SettingFastGameViewState.SettingFastGame(
                isUseSayNext           = gateway.isUseInFastGame(Skill.SAY_NEXT),
                isUseAdditionVerbal    = gateway.isUseInFastGame(Skill.ADDITION_VERBAL))
        return listOf(state)
    }
}