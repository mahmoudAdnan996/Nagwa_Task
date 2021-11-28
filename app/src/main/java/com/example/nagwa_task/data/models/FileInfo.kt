package com.example.nagwa_task.data.models

import com.example.nagwa_task.enums.Type

data class FileInfo(
    val id : Long,
    val type : Type,
    val url : String,
    val name : String
)

