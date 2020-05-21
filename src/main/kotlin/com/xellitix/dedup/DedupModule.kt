package com.xellitix.dedup

import com.google.inject.AbstractModule

class DedupModule : AbstractModule() {

    override fun configure() {
        bind(HashRepository::class.java).to(RedisHashRepository::class.java)
    }
}
