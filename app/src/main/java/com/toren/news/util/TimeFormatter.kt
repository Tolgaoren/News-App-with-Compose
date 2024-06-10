package com.toren.news.util

import com.toren.news.common.Constants
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.util.Locale

class TimeFormatter {
    companion object {
        fun formatDateTime(input: String?): String {
            if (input.isNullOrBlank()) return ""
            return DateTimeFormatter.ofPattern(
                "HH:mm d MMM yyyy",
                Locale(Constants.LANG.code))
                .format(ZonedDateTime.parse(input, DateTimeFormatter.ISO_ZONED_DATE_TIME))
        }
    }
}
