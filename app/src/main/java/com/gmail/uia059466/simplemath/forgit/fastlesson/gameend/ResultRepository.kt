package com.gmail.uia059466.simplemath.forgit.fastlesson.gameend

import com.gmail.uia059466.simplemath.forgit.fastlesson.ResultGame

interface ResultRepository {
  fun getCurrentResult()                  : ResultGame
  fun saveResult      (result: ResultGame)
  fun cleanResult     ()
  fun getUserTarget   ()                  : Int
}