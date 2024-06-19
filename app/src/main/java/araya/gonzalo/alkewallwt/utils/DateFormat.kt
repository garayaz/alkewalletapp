package araya.gonzalo.alkewallwt.utils

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

fun String.toReadableDate(): String {
    val isoFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    isoFormat.timeZone = TimeZone.getTimeZone("UTC")
    val date = isoFormat.parse(this)

    val readableFormat = SimpleDateFormat("MMM dd, hh:mm a", Locale.getDefault())
    return date?.let { readableFormat.format(it) } ?: this
}