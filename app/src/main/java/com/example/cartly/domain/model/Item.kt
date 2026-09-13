package com.example.cartly.domain.model

import java.util.UUID

data class Item(
    val id: ItemId,
    val name: String
)

@JvmInline
value class ItemId(val value: UUID)
