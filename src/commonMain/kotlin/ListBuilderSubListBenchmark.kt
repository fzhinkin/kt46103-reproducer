package org.example

import kotlinx.benchmark.*

@State(Scope.Benchmark)
open class ListBuilderSubListBenchmark {
    @Param("1", "10", "100")
    var depth: Int = 0

    private var sublist: List<Any> = emptyList()

    @Setup
    fun setup() {
        buildList {
            repeat(depth + 1) { add(Any()) }
            sublist = this
            repeat(depth) {
                sublist = sublist.subList(1, sublist.size)
            }
        }
    }

    @Benchmark
    fun get(): Any = sublist[0]
}
