package com.gmail.uia059466.simplemath.forgit.games.lessonverbal

import com.gmail.uia059466.simplemath.forgit.utils.MviAction

sealed class VerbalLessonAction : MviAction {
  object Back                             : VerbalLessonAction()
  class  NextCard(val error: String = "",
                  val right: String = "") : VerbalLessonAction()
}