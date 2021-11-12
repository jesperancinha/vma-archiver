package org.jesperancinha.vma

fun main(args: Array<String>) {
    println("Hello, World")
    yieldTest()
}

fun yieldTest() {
    val bugs = sequence {
        println("Going to the fields I saw a")
        yield("Grasshopper")
        println("Standing by the lake I was bit by a")
        yield("Mosquito")
        println("I ran way but I was chased by a")
        yield("Fly")
        println("Then I went home and in the garden I saw a")
        yield("Praying Mantis")
    }

    println("- Telling the story:")
    bugs.forEach { println(it) }
    println("- Story has been told and the sequence is built:")
    bugs.forEach { println(it) }
    println("- And these are all the characters:")
    bugs.toList().forEach { println(it) }

}

