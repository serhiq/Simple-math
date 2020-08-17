package com.gmail.uia059466.simplemath.forgit.games.lessonverbal.saynext

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan

class OneDotFirstRed : IColorStrategy {
  override fun createColored(text: String): SpannableString {
    val spannable = SpannableString(text)
    spannable.setSpan(
            ForegroundColorSpan(Color.RED),
            0, 1,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                     )
    
    return spannable
  }
}