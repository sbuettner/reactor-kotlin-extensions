package reactor.core.publisher

import org.junit.Assert
import org.junit.Test
import reactor.test.expectError
import reactor.test.verifyError
import java.io.IOException
import java.util.concurrent.Callable
import java.util.concurrent.CompletableFuture

class MonoExtensionTests {

    @Test
    fun anyToMono() {
        "foo".toMono().test().expectNext("foo").verifyComplete()
    }

    @Test
    fun completableFutureToMono() {
        var future = CompletableFuture<String>()

        var verifier = future.toMono()
                .test()
                .expectNext("foo")
                .expectComplete()
        future.complete("foo")
        verifier.verify()
    }

    @Test
    fun callableToMono() {
        val callable = Callable { "foo" }
        var verifier = callable.toMono().test()
                .expectNext("foo")
                .expectComplete()
        verifier.verify()
    }

    @Test
    fun throwableToMono() {
        IllegalStateException()
                .toMono<Any>()
                .test()
                .expectError(IllegalStateException::class)
                .verify()
    }

    @Test
    fun doOnError() {
        val monoOnError: Mono<Any> = IllegalStateException().toMono()
        var invoked = false
        monoOnError.doOnError(IllegalStateException::class, {
            invoked = true
        }).subscribe()
        Assert.assertTrue(invoked)
    }

    @Test
    fun mapError() {
        IOException()
                .toMono<Any>()
                .mapError(IOException::class, ::IllegalStateException)
                .test()
                .verifyError<IllegalStateException>()
    }

    @Test
    fun `ofType() with KClass parameter`() {
        "foo".toMono().ofType(String::class).test().expectNext("foo").verifyComplete()
        "foo".toMono().ofType(Integer::class).test().verifyComplete()
    }

    @Test
    fun `ofType() with generic parameter`() {
        "foo".toMono().ofType<String>().test().expectNext("foo").verifyComplete()
        "foo".toMono().ofType<Integer>().test().verifyComplete()
    }

    @Test
    fun otherwise() {
        IOException()
                .toMono<String>()
                .otherwise(IOException::class, { "foo".toMono() })
                .test()
                .expectNext("foo")
                .verifyComplete()
    }

    @Test
    fun otherwiseReturn() {
        IOException()
                .toMono<String>()
                .otherwiseReturn(IOException::class, "foo")
                .test()
                .expectNext("foo")
                .verifyComplete()
    }

}


