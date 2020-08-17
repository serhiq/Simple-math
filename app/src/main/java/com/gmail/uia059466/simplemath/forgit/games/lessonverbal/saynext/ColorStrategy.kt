package com.gmail.uia059466.simplemath.forgit.games.lessonverbal.saynext

fun getColorStrategy(text: String): IColorStrategy? {
  return when {
    text.startsWith(".") -> OneDotFirstRed()
    text.endsWith(".")   -> OneDotLastRed()
    else -> null
  }
}