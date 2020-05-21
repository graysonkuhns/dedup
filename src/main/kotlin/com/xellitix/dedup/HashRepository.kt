package com.xellitix.dedup

interface HashRepository {

    /**
     * Records a hash in the repository.
     */
    fun record(hash: String)

    /**
     * Checks if a hash has been recorded in the repository.
     */
    fun isRecorded(hash: String): Boolean
}
