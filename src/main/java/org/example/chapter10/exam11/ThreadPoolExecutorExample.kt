package org.example.chapter10.exam11

import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.BlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

fun main() {
    val corePoolSize = 2
    val maxPoolSize = 4
    val keepAliveTime = 0L

    val workQueue: BlockingQueue<Runnable> = ArrayBlockingQueue(4)

    val taskNum = 9

    val executor = ThreadPoolExecutor(
        corePoolSize,
        maxPoolSize,
        keepAliveTime,
        TimeUnit.SECONDS,
        workQueue,
    ).apply {
        rejectedExecutionHandler = ThreadPoolExecutor.CallerRunsPolicy()
    }

    repeat(taskNum) { taskId ->
        executor.execute {
            println("${Thread.currentThread().name} 가 테스크 $taskId 를 실행하고 있습니다.")
        }
    }

    executor.shutdown()

    if (!executor.awaitTermination(3, TimeUnit.SECONDS)) {
        executor.shutdownNow()
    } else {
        println("모든 테스크가 완료되었습니다.")
    }
}