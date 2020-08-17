package com.gmail.uia059466.simplemath.forgit.games.lessonverbal

class Progress(
        val size: Int,
        val current: Int,
        val isLast: Boolean = false
              )

fun createProgress(size: Int): Progress {
  return Progress(
          current = 0,
          size = size
                 )
}

fun updateProgress(state: Progress): Progress {
  val current = state.current + 1
  val size = state.size
  val isLast = state.size == current
  
  return Progress(
          current = current,
          size = size,
          isLast = isLast
                 )
}