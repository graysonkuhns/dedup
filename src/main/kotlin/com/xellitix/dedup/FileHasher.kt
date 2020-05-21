package com.xellitix.dedup

import com.google.common.hash.Hashing
import com.google.inject.Singleton
import java.io.File

@Singleton
class FileHasher {

    fun sha256sum(file: File): String {
        val hash: ByteArray = Hashing
            .sha256()
            .hashBytes(file.readBytes())
            .asBytes()

        return bytesToHex(hash)
    }

    private fun bytesToHex(bytes: ByteArray): String {
        return bytes.joinToString("") { "%02x".format(it) }
    }
}
