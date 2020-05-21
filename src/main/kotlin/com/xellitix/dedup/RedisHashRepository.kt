package com.xellitix.dedup

import redis.clients.jedis.Jedis

/**
 * Hash repository backed by Redis.
 */
class RedisHashRepository : HashRepository {

    // Constants
    private companion object {
        val REPO_KEY = "dedup_file_hashes"
    }

    // Dependencies
    private val jedis = Jedis()

    /**
     * Records a hash in the repository.
     */
    override fun record(hash: String) {
        jedis.sadd(REPO_KEY, hash)
    }

    /**
     * Checks if a hash has been recorded in the repository.
     */
    override fun isRecorded(hash: String): Boolean {
        return jedis.sismember(REPO_KEY, hash)
    }
}
