package org.jesperancinha.vma.common.dto

import org.apache.avro.Schema
import org.apache.avro.SchemaBuilder
import org.apache.avro.generic.IndexedRecord

data class ArtistVotingDto(
    val userId: String? = null,
    val idC: String? = null,
    val idA: String? = null
) : IndexedRecord {
    override fun getSchema(): Schema =
        SchemaBuilder.record("ArtistVotingDto")
            .namespace("org.jesperancinha.vma.common.dto")
            .fields()
            .requiredString("userId")
            .requiredString("idC")
            .requiredString("idA")
            .endRecord();

    override fun put(i: Int, v: Any?) {
        println("$i + $v")
    }

    override fun get(i: Int) = when (i) {
        0 -> userId
        1 -> idC
        2 -> idA
        else -> ""
    }
}

data class SongVotingDto(
    val userId: String? = null,
    val idC: String? = null,
    val idS: String? = null
)

data class ArtistVotingEvent(
    val userId: String? = null,
    val idC: String? = null,
    val idA: String? = null
)

data class SongVotingEvent(
    val userId: String? = null,
    val idC: String? = null,
    val idS: String? = null
)
