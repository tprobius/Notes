package com.tprobius.notes.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Note(
    val id: Long,
    val title: String,
    val content: String,
    var isFavorite: Boolean,
    val timestamp: Long
) : Parcelable