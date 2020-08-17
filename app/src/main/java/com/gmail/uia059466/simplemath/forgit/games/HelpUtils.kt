package com.gmail.uia059466.simplemath.forgit.games

import com.gmail.uia059466.simplemath.forgit.R

import com.gmail.uia059466.simplemath.forgit.utils.StringWrapper

class HelpUtils{
  companion object{
    fun helpSayNext(add: Int): HelpMessage {
      val id= when (add) {
        2    -> R.string.help_say_next_2
        else -> R.string.help_say_next
      }
      val str= StringWrapper(id, listOf(add.toString()))
      return HelpMessage(text = str)
    }
  
    fun helpAddVerbal(): HelpMessage {
      val id=R.string.help_add_verbal
      val str= StringWrapper(id, emptyList())
      return HelpMessage(text = str)
    }
  
    fun helpAddVerbalPermute(): HelpMessage {
      val id=R.string.help_add_verbal_permute
      val str= StringWrapper(id, emptyList())
      return HelpMessage(text = str)
    }
  }
}