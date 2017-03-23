package reactor.core.publisher

import org.junit.Test
import reactor.test.StepVerifier

class FluxExtensionTests {

    @Test
    fun throwableToFlux() {
        var ex = IllegalStateException()
        val flux: Flux<Any> = ex.toFlux()
        StepVerifier.create(flux)
                .expectError(IllegalStateException::class.java)
                .verify()
    }

}