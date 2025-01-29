package org.example

import kotlinx.benchmark.*

@State(Scope.Benchmark)
open class SubListBenchmark {
    @Param("1", "10", "100")
    var depth: Int = 0
    @Param("asList", "toList", "toMutableList", "ArrayList")
    var constructionMode: String = ""

    private var list = listOf<Any>()
    private var sublist = list

    @Setup
    fun setup() {
        val arr = Array(depth + 1) { Any() }
        list = when (constructionMode) {
            "asList" -> arr.asList()
            "toList" -> arr.toList()
            "toMutableList" -> arr.toMutableList()
            "ArrayList" -> ArrayList(arr.asList())
            else -> throw IllegalArgumentException("Unknown construction mode: $constructionMode")
        }
        sublist = list
        repeat(depth) {
            sublist = sublist.subList(1, sublist.size)
        }
    }

    @Benchmark
    fun get(): Any = sublist[0]
}
