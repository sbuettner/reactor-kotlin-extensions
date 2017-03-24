package reactor.core.publisher

import org.junit.Assert
import org.junit.Test
import reactor.test.verifyError
import java.io.IOException

class FluxExtensionTests {

    @Test
    fun `Throwable to Flux`() {
        IllegalStateException()
                .toFlux<Any>()
                .test()
                .verifyError(IllegalStateException::class)
    }

    @Test
    fun cast() {
        val fluxOfAny: Flux<Any> = Flux.just("foo")
        fluxOfAny.cast<String>().test().expectNext("foo").verifyComplete()
    }

    @Test
    fun `doOnError() with KClass parameter`() {
        val fluxOnError: Flux<Any> = IllegalStateException().toFlux()
        var invoked = false
        fluxOnError.doOnError(IllegalStateException::class, {
            invoked = true
        }).subscribe()
        Assert.assertTrue(invoked)
    }

    @Test
    fun `doOnError() with generic parameter`() {
        val fluxOnError: Flux<Any> = IllegalStateException().toFlux()
        var invoked = false
        fluxOnError.doOnError<IllegalStateException>{
            invoked = true
        }.subscribe()
        Assert.assertTrue(invoked)
    }

    @Test
    fun `mapError() with KClass parameter`() {
        IOException()
                .toFlux<Any>()
                .mapError(IOException::class, ::IllegalStateException)
                .test()
                .verifyError<IllegalStateException>()
    }

    @Test
    fun `mapError() with generic parameter`() {
        IOException()
                .toFlux<Any>()
                .mapError<IOException>(::IllegalStateException)
                .test()
                .verifyError<IllegalStateException>()
    }

    @Test
    fun `ofType() with KClass parameter`() {
        arrayOf("foo", 1).toFlux().ofType(String::class).test().expectNext("foo").verifyComplete()
    }

    @Test
    fun `ofType() with generic parameter`() {
        arrayOf("foo", 1).toFlux().ofType<String>().test().expectNext("foo").verifyComplete()
    }

    @Test
    fun `onErrorResumeWith() with KClass parameter`() {
        IOException()
                .toFlux<String>()
                .onErrorResumeWith(IOException::class, { "foo".toMono() })
                .test()
                .expectNext("foo")
                .verifyComplete()
    }

}
