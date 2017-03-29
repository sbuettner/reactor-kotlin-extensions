package reactor.core.publisher

import org.reactivestreams.Publisher
import reactor.test.StepVerifier
import java.util.stream.Stream
import kotlin.reflect.KClass


/**
 * Extension for transforming an [Iterator] to a [Flux].
 *
 * @author Sebastien Deleuze
 */
fun <T> Iterator<T>.toFlux(): Flux<T> = toIterable().toFlux()

/**
 * Extension for transforming an [Iterable] to a [Flux].
 *
 * @author Sebastien Deleuze
 */
fun <T> Iterable<T>.toFlux(): Flux<T> = Flux.fromIterable(this)

/**
 * Extension for transforming a [Sequence] to a [Flux].
 *
 * @author Sebastien Deleuze
 */
fun <T> Sequence<T>.toFlux(): Flux<T> = Flux.fromIterable(object : Iterable<T> {
    override fun iterator(): Iterator<T> = this@toFlux.iterator()
})

/**
 * Extension for transforming a [Stream] to a [Flux].
 *
 * @author Sebastien Deleuze
 */
fun <T> Stream<T>.toFlux(): Flux<T> = Flux.fromStream(this)

/**
 * Extension for transforming a [BooleanArray] to a [Flux].
 *
 * @author Sebastien Deleuze
 */
fun BooleanArray.toFlux(): Flux<Boolean> = this.toList().toFlux()

/**
 * Extension for transforming a [ByteArray] to a [Flux].
 *
 * @author Sebastien Deleuze
 */
fun ByteArray.toFlux(): Flux<Byte> = this.toList().toFlux()

/**
 * Extension for transforming a [ShortArray] to a [Flux].
 *
 * @author Sebastien Deleuze
 */
fun ShortArray.toFlux(): Flux<Short> = this.toList().toFlux()

/**
 * Extension for transforming a [IntArray] to a [Flux].
 *
 * @author Sebastien Deleuze
 */
fun IntArray.toFlux(): Flux<Int> = this.toList().toFlux()

/**
 * Extension for transforming a [LongArray] to a [Flux].
 *
 * @author Sebastien Deleuze
 */
fun LongArray.toFlux(): Flux<Long> = this.toList().toFlux()

/**
 * Extension for transforming a [FloatArray] to a [Flux].
 *
 * @author Sebastien Deleuze
 */
fun FloatArray.toFlux(): Flux<Float> = this.toList().toFlux()

/**
 * Extension for transforming a [DoubleArray] to a [Flux].
 *
 * @author Sebastien Deleuze
 */
fun DoubleArray.toFlux(): Flux<Double> = this.toList().toFlux()

/**
 * Extension for transforming an [Array] to a [Flux].
 *
 * @author Sebastien Deleuze
 */
fun <T> Array<out T>.toFlux(): Flux<T> = Flux.fromArray(this)

private fun <T> Iterator<T>.toIterable() = object : Iterable<T> {
    override fun iterator(): Iterator<T> = this@toIterable
}

/**
 * Extension for transforming an exception to a [Flux] that completes with the specified error.
 *
 * @author Sebastien Deleuze
 */
fun <T> Throwable.toFlux(): Flux<T> = Flux.error(this)

/**
 * Extension for testing [Flux] with [StepVerifier] API.
 *
 * @author Sebastien Deleuze
 */
fun <T> Flux<T>.test(): StepVerifier.FirstStep<T> = StepVerifier.create(this)

/**
 * Extension for testing [Flux] with [StepVerifier] API.
 *
 * @author Sebastien Deleuze
 */
fun <T> Flux<T>.test(n: Long): StepVerifier.FirstStep<T> = StepVerifier.create(this, n)

/**
 * Extension for [Flux.cast] providing a `cast<Foo>()` variant.
 *
 * @author Sebastien Deleuze
 */
inline fun <reified T : Any> Flux<*>.cast(): Flux<T> = cast(T::class.java)

/**
 * Extension for [Flux.doOnError] providing a [KClass] based variant.
 *
 * @author Sebastien Deleuze
 */
fun <T, E : Throwable> Flux<T>.doOnError(exceptionType: KClass<E>, onError: (E) -> Unit): Flux<T> =
        doOnError(exceptionType.java, { onError(it) })

/**
 * Extension for [Flux.mapError] providing a [KClass] based variant.
 *
 * @author Sebastien Deleuze
 */
fun <T, E : Throwable> Flux<T>.mapError(exceptionType: KClass<E>, mapper: (E) -> Throwable): Flux<T> =
        mapError(exceptionType.java, { mapper(it) })

/**
 * Extension for [Flux.ofType] providing a [KClass] based variant.
 *
 * @author Sebastien Deleuze
 */
fun <T : Any> Flux<*>.ofType(kClass: KClass<T>): Flux<T> = ofType(kClass.java)

/**
 * Extension for [Flux.ofType] providing a `ofType<Foo>()` variant.
 *
 * @author Sebastien Deleuze
 */
inline fun <reified T : Any> Flux<*>.ofType(): Flux<T> = ofType(T::class.java)

/**
 * Extension for [Flux.onErrorResumeWith] providing a [KClass] based variant.
 *
 * @author Sebastien Deleuze
 */
fun <T : Any, E : Throwable> Flux<T>.onErrorResumeWith(exceptionType: KClass<E>, fallback: (E) -> Publisher<T>): Flux<T> =
        onErrorResumeWith(exceptionType.java, { fallback(it) })

/**
 * Extension for [Flux.onErrorReturn] providing a [KClass] based variant.
 *
 * @author Sebastien Deleuze
 */
fun <T : Any, E : Throwable> Flux<T>.onErrorReturn(exceptionType: KClass<E>, value: T): Flux<T> =
        onErrorReturn(exceptionType.java, value)

/**
 * Extension for [Flux.switchOnError] providing a [KClass] based variant.
 *
 * @author Sebastien Deleuze
 */
fun <T : Any, E : Throwable> Flux<T>.switchOnError(exceptionType: KClass<E>, publisher: Publisher<T>): Flux<T> =
        switchOnError(exceptionType.java, publisher)

/**
 * Extension for merging a [Flux] with a [Publisher].
 *
 * @author Simon Buettner
 */
operator fun <T> Flux<T>.plus(p: Publisher<T>): Flux<T> = this.mergeWith(p)

/**
 * Extension for creating an empty [Flux] from a nullable one.
 *
 * @author Simon Buettner
 */
fun <T> Flux<T>?.orEmpty(): Flux<T> = this?: Flux.empty()