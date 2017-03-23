package reactor.core.publisher

import org.junit.Test
import reactor.test.StepVerifier
import reactor.test.expectError
import java.util.concurrent.Callable
import java.util.concurrent.CompletableFuture

class MonoTests {

    @Test
    fun anyToMono() {
        var foo = "foo"
        val mono = foo.toMono()
        StepVerifier.create(mono)
                .expectNext("foo")
                .verifyComplete()
    }

    @Test
    fun completableFutureToMono() {
        var future = CompletableFuture<String>()
        val mono = future.toMono()
        var verifier = StepVerifier.create(mono)
                .expectNext("foo")
                .expectComplete()
        future.complete("foo")
        verifier.verify()
    }

    @Test
    fun callableToMono() {
        val callable = Callable { "foo" }
        val mono = callable.toMono()
        var verifier = StepVerifier.create(mono)
                .expectNext("foo")
                .expectComplete()
        verifier.verify()
    }

    @Test
    fun throwableToMono() {
        var ex = IllegalStateException()
        val mono: Mono<Any> = ex.toMono()
        StepVerifier.create(mono)
                .expectError(IllegalStateException::class)
                .verify()
    }

}


