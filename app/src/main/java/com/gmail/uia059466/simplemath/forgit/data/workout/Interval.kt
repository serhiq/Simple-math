package com.gmail.uia059466.simplemath.forgit.data.workout

class Interval (val from:Int,val to:Int){
  fun firstHalf(): Interval {
    val new=from+(calcDelta()/2)
    return Interval(from=from,to = new)
  }
  fun secondHalf(): Interval {
    val delta=calcDelta()/2
    val new=from+delta
    return Interval(from=new,to = to)
  }
  
  private fun calcDelta(): Int {
    return  to-from
  }
}