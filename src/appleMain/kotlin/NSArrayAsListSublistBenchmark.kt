package org.example

import kotlinx.benchmark.*
import platform.Foundation.NSMutableArray

@State(Scope.Benchmark)
open class NSArrayAsListSublistBenchmark {
    @Param("1", "10", "100")
    var depth: Int = 0

    private var list = listOf<Any?>()
    private var sublist = list

    @Setup
    fun setup() {
        val arr = NSMutableArray()
        repeat(depth + 1) {
            arr.addObject(Any())
        }
        list = arr as MutableList<Any?>
        sublist = list
        repeat(depth) {
            sublist = sublist.subList(1, sublist.size)
        }
    }

    @Benchmark
    fun get(): Any? = sublist[0]
}
