package reactor.test

import kotlin.reflect.KClass

fun StepVerifier.LastStep.expectError(kClass: KClass<out Throwable>) : StepVerifier = expectError(kClass.java)