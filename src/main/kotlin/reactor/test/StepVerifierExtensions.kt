package reactor.test

import reactor.test.StepVerifier.Assertions
import reactor.test.StepVerifier.LastStep
import java.time.Duration
import kotlin.reflect.KClass


/**
 * Extension for [StepVerifier.LastStep.expectError] providing a [KClass] based variant.
 *
 * @author Sebastien Deleuze
 */
fun LastStep.expectError(kClass: KClass<out Throwable>): StepVerifier = expectError(kClass.java)

/**
 * Extension for [StepVerifier.LastStep.expectError] providing a `expectError<Foo>()` variant.
 *
 * @author Sebastien Deleuze
 */
@Suppress("EXTENSION_SHADOWED_BY_MEMBER")
inline fun <reified T : Throwable> LastStep.expectError(): StepVerifier = expectError(T::class.java)

/**
 * Extension for [StepVerifier.LastStep.verifyError] providing a [KClass] based variant.
 *
 * @author Sebastien Deleuze
 */
fun LastStep.verifyError(kClass: KClass<out Throwable>): Duration = verifyError(kClass.java)

/**
 * Extension for [StepVerifier.LastStep.verifyError] providing a `verifyError<Foo>()` variant.
 *
 * @author Sebastien Deleuze
 */
@Suppress("EXTENSION_SHADOWED_BY_MEMBER")
inline fun <reified T : Throwable> LastStep.verifyError(): Duration = verifyError(T::class.java)

/**
 * Extension for [StepVerifier.Assertions.hasDroppedErrorOfType] providing a [KClass] based variant.
 *
 * @author Sebastien Deleuze
 */
fun Assertions.hasDroppedErrorOfType(kClass: KClass<out Throwable>): Assertions = hasDroppedErrorOfType(kClass.java)

/**
 * Extension for [StepVerifier.Assertions.hasDroppedErrorOfType] providing a `hasDroppedErrorOfType<Foo>()` variant.
 *
 * @author Sebastien Deleuze
 */
@Suppress("EXTENSION_SHADOWED_BY_MEMBER")
inline fun <reified T : Throwable> Assertions.hasDroppedErrorOfType(): Assertions = hasDroppedErrorOfType(T::class.java)

/**
 * Extension for [StepVerifier.Assertions.hasOperatorErrorOfType] providing a [KClass] based variant.
 *
 * @author Sebastien Deleuze
 */
fun Assertions.hasOperatorErrorOfType(kClass: KClass<out Throwable>): Assertions = hasOperatorErrorOfType(kClass.java)

/**
 * Extension for [StepVerifier.Assertions.hasOperatorErrorOfType] providing a `hasOperatorErrorOfType<Foo>()` variant.
 *
 * @author Sebastien Deleuze
 */
@Suppress("EXTENSION_SHADOWED_BY_MEMBER")
inline fun <reified T : Throwable> Assertions.hasOperatorErrorOfType(): Assertions = hasOperatorErrorOfType(T::class.java)
