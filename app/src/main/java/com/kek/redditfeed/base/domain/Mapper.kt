package com.kek.redditfeed.base.domain

import java.util.ArrayList

abstract class Mapper<From, To> : BaseMapper<From, To>() {

  abstract fun reverse(to: To): From

  fun reverse(tos: List<To>): List<From> {
    val result = ArrayList<From>(tos.size)
    for (to in tos) {
      result.add(reverse(to))
    }
    return result
  }
}