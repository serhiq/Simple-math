package com.gmail.uia059466.simplemath.forgit.fastlesson.gameend

import com.gmail.uia059466.simplemath.forgit.utils.MviAction

sealed class GameEndAction : MviAction {
  object RunMain          : GameEndAction()
  object RunComeBack      : GameEndAction()
  object RunSetting : GameEndAction()
}