package reactor.test

import kotlin.reflect.KClass

fun StepVerifier.LastStep.expectError(kClass: KClass<IllegalStateException>) = expectError(kClass.java)