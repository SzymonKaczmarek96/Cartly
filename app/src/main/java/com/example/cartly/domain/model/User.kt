package com.example.cartly.domain.model

import java.util.UUID

data class User(
    val id: UserId,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: UserPassword,
    val favoriteItemIds: Set<ItemId>
)

@JvmInline
value class UserId(val value: UUID)

@JvmInline
value class UserPassword(val value: String)