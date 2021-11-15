package org.jesperancinha.vma.common.dto

import org.apache.avro.Schema
import org.apache.avro.SchemaBuilder
import org.apache.avro.generic.IndexedRecord

data class ArtistVotingDto(
    val userId: String,
    val idC: String,
    val idA: String
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
    val userId: String,
    val idC: String,
    val idS: String
) : IndexedRecord {
    override fun getSchema(): Schema =
        SchemaBuilder.record("SongVotingDto")
            .namespace("org.jesperancinha.vma.common.dto")
            .fields()
            .requiredString("userId")
            .requiredString("idC")
            .requiredString("idS")
            .endRecord();

    override fun put(i: Int, v: Any?) {
        println("$i + $v")
    }

    override fun get(i: Int) = when (i) {
        0 -> userId
        1 -> idC
        2 -> idS
        else -> ""
    }
}

data class VotingId(
    val id: String
)
