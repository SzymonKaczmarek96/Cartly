package com.example.cartly.domain.model

import java.util.UUID

data class Cart(
    val id: CartId,
    val name: String,
    val ownerId: UserId,
    val iconUri: String?,
    val members: Set<CartMember>,
    val items: List<CartItem>
)

@JvmInline
value class CartId(val value: UUID)
