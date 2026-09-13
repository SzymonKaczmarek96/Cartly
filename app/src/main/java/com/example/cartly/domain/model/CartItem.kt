package com.example.cartly.domain.model

import java.util.UUID

data class CartItem(
    val id: CartItemId,
    val item: Item,
    val quantity: Int,
    val isPurchased: Boolean
)

@JvmInline
value class CartItemId(
    val value: UUID
)
