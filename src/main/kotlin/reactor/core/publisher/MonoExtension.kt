package reactor.core.publisher

import reactor.test.StepVerifier
import java.util.concurrent.Callable
import java.util.concurrent.CompletableFuture
import kotlin.reflect.KClass

fun <T> T.toMono(): Mono<T> = Mono.just(this)
fun <T> CompletableFuture<T>.toMono(): Mono<T> = Mono.fromFuture(this)
fun <T> Callable<T>.toMono(): Mono<T> = Mono.fromCallable(this::call)
fun <T> Throwable.toMono(): Mono<T> = Mono.error(this)

fun <T> Mono<T>.test(): StepVerifier.FirstStep<T> = StepVerifier.create(this)
fun <T> Mono<T>.test(n: Long): StepVerifier.FirstStep<T> = StepVerifier.create(this, n)

inline fun <reified T : Any> Mono<*>.cast(): Mono<T> = cast(T::class.java)

fun <T, E : Throwable> Mono<T>.doOnError(exceptionType: KClass<E>, onError: (E) -> Unit) : Mono<T> =
        doOnError(exceptionType.java, { onError(it) })

fun <T, E : Throwable> Mono<T>.mapError(exceptionType: KClass<E>, mapper: (E) -> Throwable) : Mono<T> =
        mapError(exceptionType.java, { mapper(it) })

fun <T : Any> Mono<*>.ofType(kClass: KClass<T>) : Mono<T> = ofType(kClass.java)
inline fun <reified T : Any> Mono<*>.ofType() : Mono<T> = ofType(T::class.java)

fun <T : Any, E : Throwable> Mono<T>.otherwise(exceptionType: KClass<E>, fallback: (E) -> Mono<T>) : Mono<T> =
        otherwise(exceptionType.java, { fallback(it) })

fun <T : Any, E : Throwable> Mono<T>.otherwiseReturn(exceptionType: KClass<E>, value: T) : Mono<T> =
        otherwiseReturn(exceptionType.java, value)