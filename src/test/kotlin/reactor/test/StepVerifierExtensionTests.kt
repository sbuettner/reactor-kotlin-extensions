package reactor.test

import org.junit.Test
import reactor.core.publisher.Mono
import reactor.core.publisher.test
import reactor.core.publisher.toMono

class StepVerifierExtensionTests {

    @Test
    fun expectError() {
        IllegalStateException()
                .toMono<Void>()
                .test()
                .expectError(IllegalStateException::class)
                .verify()
    }

    @Test
    fun verifyError() {
        IllegalStateException()
                .toMono<Void>()
                .test()
                .verifyError(IllegalStateException::class)
    }

}