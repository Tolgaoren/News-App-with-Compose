package com.toren.news.util
class ContentFormatter {
    companion object {
        fun formatContent(content: String?) : String {
            if (content == null) {
                return ""
            }
            val cutoffIndex = content.indexOf("…")
            var cleanedText = if (cutoffIndex != -1) {
                content.substring(0, cutoffIndex)
            } else {
                content
            }
            cleanedText += "...see more"

            return cleanedText
        }
    }
}