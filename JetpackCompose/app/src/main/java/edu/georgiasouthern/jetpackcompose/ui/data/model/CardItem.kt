package edu.georgiasouthern.jetpackcompose.ui.data.model

import java.util.UUID

data class CardItem(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val fields: List<CardField>
)
