package com.gmail.uia059466.simplemath.forgit.utils

import android.content.Context

class StringWrapper(
        val idRes:Int,
        val list :List<String>
                   ) {
  fun getString(context: Context): String {
    return when(list.size) {
      1    -> context.getString(idRes,list[0])
      2    -> context.getString(idRes,list[0],list[1])
      3    -> context.getString(idRes,list[0],list[1],list[2])
      else -> context.getString(idRes)
    }
  }
}