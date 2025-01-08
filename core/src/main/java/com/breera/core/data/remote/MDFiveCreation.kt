package com.breera.core.data.remote

/**
 * Converts the string into its MD5 hash representation.
 *
 * This function takes a plain string and transforms it into an MD5 hash,
 * a one-way ticket to a land of cryptographic mystery. Useful for scenarios
 * where you need a consistent, hashed representation of a string, like
 * securing sensitive data or generating unique identifiers.
 *
 * @receiver The string to be hashed.
 * @return The MD5 hash of the string, represented as a hexadecimal string.
 */

fun String.md5(): String {
    val digest = java.security.MessageDigest.getInstance("MD5")
    digest.update(this.toByteArray())
    return digest.digest().joinToString("") { "%02x".format(it) }
}