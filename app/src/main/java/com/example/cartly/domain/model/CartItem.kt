package com.example.cartly.domain.model

import java.util.UUID
import java.time.Instant

data class CartItem(
    val id: CartItemId,
    val item: Item,
    val quantity: Int,
    val isPurchased: Boolean,
    val addedBy: UserId,
    val createAt: Instant,
    val updateAt: Instant,

)

@JvmInline
value class CartItemId(
    val value: UUID
)
