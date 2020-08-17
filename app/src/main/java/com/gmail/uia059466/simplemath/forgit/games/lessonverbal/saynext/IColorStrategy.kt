package com.gmail.uia059466.simplemath.forgit.games.lessonverbal.saynext

import android.text.SpannableString

interface IColorStrategy {
  fun createColored(text: String): SpannableString
}