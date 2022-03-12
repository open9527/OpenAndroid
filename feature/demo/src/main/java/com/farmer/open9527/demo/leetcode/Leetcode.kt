package com.farmer.open9527.demo.leetcode

object Leetcode {

    //1. 两数之和
  open  fun sum(arr: IntArray, target: Int): IntArray {
        val arrs = IntArray(2)
        for ((i,x) in arr.withIndex()){
            for ((j,y) in arr.withIndex()){
                if (target == (x + y)) {
                    arrs[0] = i
                    arrs[1] = j
                    return arrs
                }
            }
        }
        return arrs
    }

}