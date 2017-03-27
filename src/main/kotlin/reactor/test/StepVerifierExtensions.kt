package reactor.test

import reactor.test.StepVerifier.Assertions
import java.time.Duration
import kotlin.reflect.KClass

fun StepVerifier.LastStep.expectError(kClass: KClass<out Throwable>) : StepVerifier = expectError(kClass.java)

@Suppress("EXTENSION_SHADOWED_BY_MEMBER")
inline fun <reified T : Throwable> StepVerifier.LastStep.expectError() : StepVerifier = expectError(T::class.java)

fun StepVerifier.LastStep.verifyError(kClass: KClass<out Throwable>) : Duration = verifyError(kClass.java)

@Suppress("EXTENSION_SHADOWED_BY_MEMBER")
inline fun <reified T : Throwable> StepVerifier.LastStep.verifyError(): Duration = verifyError(T::class.java)

fun Assertions.hasDroppedErrorOfType(kClass: KClass<out Throwable>) : Assertions = hasDroppedErrorOfType(kClass.java)

@Suppress("EXTENSION_SHADOWED_BY_MEMBER")
inline fun <reified T : Throwable> Assertions.hasDroppedErrorOfType() : Assertions = hasDroppedErrorOfType(T::class.java)

fun Assertions.hasOperatorErrorOfType(kClass: KClass<out Throwable>) : Assertions = hasOperatorErrorOfType(kClass.java)

@Suppress("EXTENSION_SHADOWED_BY_MEMBER")
inline fun <reified T : Throwable> Assertions.hasOperatorErrorOfType() : Assertions = hasOperatorErrorOfType(T::class.java)
