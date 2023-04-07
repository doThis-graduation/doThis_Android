package com.example.healthcare_exercise

import android.net.Uri

data class RecordData(
    val num: String,
    val uri: Uri,
    val part: String,
    val date: String
)