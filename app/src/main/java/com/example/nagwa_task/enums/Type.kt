package com.example.nagwa_task.enums

enum class Type(val value : String) {
    PDF("PDF"),
    Video("VIDEO");

    companion object {
        public fun fromValue(value: String): Type = when (value) {
            "PDF"   -> PDF
            "VIDEO" -> Video
            else    -> throw IllegalArgumentException()
        }
    }
}