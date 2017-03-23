package reactor.core.publisher

import org.junit.Test
import reactor.test.StepVerifier
import reactor.test.expectError

class FluxTests {

    @Test
    fun throwableToFlux() {
        var ex = IllegalStateException()
        val mono: Flux<Any> = ex.toFlux()
        StepVerifier.create(mono)
                .expectError(IllegalStateException::class)
                .verify()
    }

}