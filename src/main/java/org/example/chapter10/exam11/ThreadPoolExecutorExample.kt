package org.example.chapter10.exam11

import java.util.concurrent.*

fun main() {
    val executor = ThreadPoolExecutor(
        2, 4, 0L,
        TimeUnit.SECONDS,
        ArrayBlockingQueue(4)
    )

    try {
        repeat(9) { taskId ->
            try {
                executor.execute {
                    println("${Thread.currentThread().name} 가 task $taskId 를 실행하고 있습니다.")
                }
            } catch (e: RejectedExecutionException) {
                println("Task $taskId 거부됨")
            }
        }
    } finally {
        // 예외가 발생하더라도 반드시 shutdown 실행
        executor.shutdown()
        executor.awaitTermination(5, TimeUnit.SECONDS)
    }
}