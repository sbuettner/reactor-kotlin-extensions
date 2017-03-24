package reactor.test

import java.time.Duration
import kotlin.reflect.KClass

fun StepVerifier.LastStep.expectError(kClass: KClass<out Throwable>) : StepVerifier = expectError(kClass.java)

@Suppress("EXTENSION_SHADOWED_BY_MEMBER")
inline fun <reified T : Throwable> StepVerifier.LastStep.expectError() : StepVerifier = expectError(T::class.java)

fun StepVerifier.LastStep.verifyError(kClass: KClass<out Throwable>) = verifyError(kClass.java)

@Suppress("EXTENSION_SHADOWED_BY_MEMBER")
inline fun <reified T : Throwable> StepVerifier.LastStep.verifyError(): Duration = verifyError(T::class.java)