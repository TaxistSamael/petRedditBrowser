package com.kek.redditfeed

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kek.redditfeed.R

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    setInitialDialog()
  }

  private fun setInitialDialog() {
    //todo: log how many fragments in the stack
//    supportFragmentManager.beginTransaction()
//      .replace(R.id.activity_home_frame_layout_details, DetailsFragment.getInstance(repository))
//      .commit()
  }
}