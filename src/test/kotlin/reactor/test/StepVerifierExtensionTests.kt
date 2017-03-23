package reactor.test

import org.junit.Test
import reactor.core.publisher.Mono

class StepVerifierExtensionTests {

    @Test
    fun expectError() {
        val mono = Mono.error<Void>(IllegalStateException())
        StepVerifier.create(mono)
                .expectError(IllegalStateException::class)
                .verify()
    }

    @Test
    fun verifyError() {
        val mono = Mono.error<Void>(IllegalStateException())
        StepVerifier.create(mono)
                .verifyError(IllegalStateException::class)
    }

}