package reactor.test

import org.junit.Test
import reactor.core.publisher.Mono
import reactor.core.publisher.test
import reactor.core.publisher.toMono

class StepVerifierExtensionTests {

    @Test
    fun `expectError() with KClass parameter`() {
        IllegalStateException()
                .toMono<Void>()
                .test()
                .expectError(IllegalStateException::class)
                .verify()
    }

    @Test
    fun `expectError() with generic parameter`() {
        IllegalStateException()
                .toMono<Void>()
                .test()
                .expectError<IllegalStateException>()
                .verify()
    }

    @Test
    fun `verifyError() with KClass parameter`() {
        IllegalStateException()
                .toMono<Void>()
                .test()
                .verifyError(IllegalStateException::class)
    }

    @Test
    fun `verifyError() with generic parameter`() {
        IllegalStateException()
                .toMono<Void>()
                .test()
                .verifyError<IllegalStateException>()
    }

}