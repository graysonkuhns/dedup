package com.xellitix.dedup

import com.google.inject.Inject
import com.google.inject.Singleton
import java.io.File

@Singleton
class Deduplicator @Inject constructor(
        private val hashRepository: HashRepository,
        private val hasher: FileHasher
    ) {

    fun deduplicate(target: File) {
        println("De-duplicating files in ${target.absolutePath}")

        target.walk().forEach {
            // Only process files
            if (!it.isFile) {
                return@forEach
            }

            // Calculate the file hash
            val hash: String = hasher.sha256sum(it)

            if (hashRepository.isRecorded(hash)) {
                it.delete()
                println("Deleted file ${it.absolutePath}. Its hash was found in the repository: $hash")
            } else {
                hashRepository.record(hash)
            }
        }

        println()
        println("Cleaning up empty directories in ${target.absolutePath}")

        target.walkBottomUp().forEach {
            // Only process directories
            if (!it.isDirectory) {
                return@forEach
            }

            // Don't delete the target
            if (it == target) {
                return@forEach
            }

            // Delete empty directories
            if (it.list()?.size == 0) {
                it.delete()
                println("Deleted empty directory ${it.absolutePath}")
            }
        }
    }
}
