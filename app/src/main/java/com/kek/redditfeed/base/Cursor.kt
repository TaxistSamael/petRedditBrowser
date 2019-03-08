package com.kek.redditfeed.base

class Cursor(
  var next: String = "",
  var offset: Int = 10,
  var isLoading: Boolean = false) {

  fun reset() {
    next = ""
  }
}