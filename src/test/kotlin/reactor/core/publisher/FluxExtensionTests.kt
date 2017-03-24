package reactor.core.publisher

import org.junit.Assert
import org.junit.Test
import reactor.test.verifyError

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

}