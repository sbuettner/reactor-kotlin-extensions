package reactor.core.publisher

import reactor.test.StepVerifier
import java.util.concurrent.Callable
import java.util.concurrent.CompletableFuture
import kotlin.reflect.KClass


/**
 * Extension for transforming an object to a [Mono].
 *
 * @author Sebastien Deleuze
 */
fun <T> T.toMono(): Mono<T> = Mono.just(this)

/**
 * Extension for transforming an [CompletableFuture] to a [Mono].
 *
 * @author Sebastien Deleuze
 */
fun <T> CompletableFuture<T>.toMono(): Mono<T> = Mono.fromFuture(this)

/**
 * Extension for transforming an [Callable] to a [Mono].
 *
 * @author Sebastien Deleuze
 */
fun <T> Callable<T>.toMono(): Mono<T> = Mono.fromCallable(this::call)

/**
 * Extension for transforming an exception to a [Mono] that completes with the specified error.
 *
 * @author Sebastien Deleuze
 */
fun <T> Throwable.toMono(): Mono<T> = Mono.error(this)

/**
 * Extension for testing [Mono] with [StepVerifier] API.
 *
 * @author Sebastien Deleuze
 */
fun <T> Mono<T>.test(): StepVerifier.FirstStep<T> = StepVerifier.create(this)

/**
 * Extension for testing [Mono] with [StepVerifier] API.
 *
 * @author Sebastien Deleuze
 */
fun <T> Mono<T>.test(n: Long): StepVerifier.FirstStep<T> = StepVerifier.create(this, n)

/**
 * Extension for [Mono.cast] providing a `cast<Foo>()` variant.
 *
 * @author Sebastien Deleuze
 */
inline fun <reified T : Any> Mono<*>.cast(): Mono<T> = cast(T::class.java)

/**
 * Extension for [Mono.doOnError] providing a [KClass] based variant.
 *
 * @author Sebastien Deleuze
 */
fun <T, E : Throwable> Mono<T>.doOnError(exceptionType: KClass<E>, onError: (E) -> Unit) : Mono<T> =
        doOnError(exceptionType.java, { onError(it) })

/**
 * Extension for [Mono.mapError] providing a [KClass] based variant.
 *
 * @author Sebastien Deleuze
 */
fun <T, E : Throwable> Mono<T>.mapError(exceptionType: KClass<E>, mapper: (E) -> Throwable) : Mono<T> =
        mapError(exceptionType.java, { mapper(it) })

/**
 * Extension for [Mono.ofType] providing a [KClass] based variant.
 *
 * @author Sebastien Deleuze
 */
fun <T : Any> Mono<*>.ofType(kClass: KClass<T>) : Mono<T> = ofType(kClass.java)

/**
 * Extension for [Mono.ofType] providing a `ofType<Foo>()` variant.
 *
 * @author Sebastien Deleuze
 */
inline fun <reified T : Any> Mono<*>.ofType() : Mono<T> = ofType(T::class.java)

/**
 * Extension for [Mono.otherwise] providing a [KClass] based variant.
 *
 * @author Sebastien Deleuze
 */
fun <T : Any, E : Throwable> Mono<T>.otherwise(exceptionType: KClass<E>, fallback: (E) -> Mono<T>) : Mono<T> =
        otherwise(exceptionType.java, { fallback(it) })

/**
 * Extension for [Mono.otherwiseReturn] providing a [KClass] based variant.
 *
 * @author Sebastien Deleuze
 */
fun <T : Any, E : Throwable> Mono<T>.otherwiseReturn(exceptionType: KClass<E>, value: T) : Mono<T> =
        otherwiseReturn(exceptionType.java, value)

/**
 * Extension for zipping an [Iterable] of [Mono]s.
 *
 * @author DoHyung Kim
 */
@Suppress("UNCHECKED_CAST")
inline fun <T, V> Iterable<Mono<out T>>.zip(crossinline combinator: (List<T>) -> V): Mono<V> =
        Mono.zip({ combinator(it.asList() as List<T>) }, this)
