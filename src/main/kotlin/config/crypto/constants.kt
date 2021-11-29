package config.crypto

import java.security.MessageDigest

// Message digest
val MD: MessageDigest = MessageDigest.getInstance("SHA-256")

val HASH_SIZE: Int = when(MD.algorithm) {
    "SHA-256" -> 64
    else -> 32
}

const val HASH_DIFFICULTY = "a0"