package com.example.healthcare_exercise.recyclerView

import android.net.Uri

data class RecordData(
    val num: String,
    val uri: Uri,
    val part: String,
    val date: String
)