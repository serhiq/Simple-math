package com.gmail.uia059466.simplemath.forgit.data.workout

import com.gmail.uia059466.simplemath.forgit.games.IGame
import com.gmail.uia059466.simplemath.forgit.utils.StringWrapper

interface Workout{
  val id:Int
  fun getIGames()          :List<IGame>
  fun generateName()       : StringWrapper
  fun generateDescription(): StringWrapper
}