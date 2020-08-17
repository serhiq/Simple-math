package com.gmail.uia059466.simplemath.forgit.fastlesson.gameend

import com.gmail.uia059466.simplemath.forgit.R


enum class UserGameEndMessage {
  EXCELLENT,
  GOOD,
  TRY_AGAIN
}

fun UserGameEndMessage.stringRes(): Int {
  return when (this) {
    UserGameEndMessage.EXCELLENT -> R.string.game_end_message_excellent
    UserGameEndMessage.GOOD      -> R.string.game_end_message_good
    UserGameEndMessage.TRY_AGAIN -> R.string.game_end_message_try_again
  }
}