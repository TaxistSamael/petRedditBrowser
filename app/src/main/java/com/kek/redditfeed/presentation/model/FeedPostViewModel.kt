package com.kek.redditfeed.presentation.model

import android.content.Context
import android.os.Parcelable
import com.kek.redditfeed.R
import kotlinx.android.parcel.Parcelize
import java.util.concurrent.TimeUnit

//todo: вынести логику конвертации в отдельный файл
//todo: прикркутить parcelize
private const val DAY_IN_MILLISECOND = 86400000
private const val HOUR_IN_MILLISECOND = 3600000
private const val MINUTE_IN_MILLISECOND = 60000
private const val DURATION_SEPARATOR = " "
private const val ONE_MINUTE_STUB = 1L
private const val EMPTY_STRING = ""

@Parcelize
class FeedPostViewModel(
    val id: String = "",
    val title: String = "",
    val author: String = "",
    val createdUtc: Long = 0L,
    val numComments: Int = 0,
    val url: String = "",
    val thumbnail: String = ""
) : Parcelable {

  fun getAuthorTimeString(context: Context): String {
    val milliseconds = TimeUnit.SECONDS.toMillis(createdUtc)
    val countMinutes = milliseconds / MINUTE_IN_MILLISECOND % 60
    val countHours = milliseconds / HOUR_IN_MILLISECOND % 24
    val countDays = milliseconds / DAY_IN_MILLISECOND

    return buildString {
      append(context.getString(R.string.format_user_posted, author))
      if (countDays != 0L) {
        append(DURATION_SEPARATOR)
        append(String.format(context.getString(R.string.cooldown_days), countDays))
      }
      if (countHours != 0L) {
        append(DURATION_SEPARATOR)
        append(String.format(context.getString(R.string.cooldown_hours), countHours))
      }
      append(DURATION_SEPARATOR)

      val minutes = when {
        countDays == 0L && countHours == 0L && countMinutes == 0L -> ONE_MINUTE_STUB.toString()
        countMinutes != 0L -> countMinutes.toString()
        else -> EMPTY_STRING
      }
      append(String.format(context.getString(R.string.cooldown_minutes), minutes))
    }
  }
}