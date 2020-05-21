package com.xellitix.dedup

import com.google.inject.Guice
import java.io.File

fun main(args: Array<String>) {
    val workdir: String = System.getProperty("user.dir")

    Guice
        .createInjector(DedupModule())
        .getInstance(Deduplicator::class.java)
        .deduplicate(File(workdir))
}
