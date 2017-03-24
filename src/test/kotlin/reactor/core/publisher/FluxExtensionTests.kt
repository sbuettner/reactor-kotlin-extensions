package reactor.core.publisher

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

}