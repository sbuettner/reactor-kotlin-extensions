package reactor.core.publisher

import org.reactivestreams.Publisher
import reactor.test.StepVerifier
import java.util.stream.Stream
import kotlin.reflect.KClass


fun <T> Iterator<T>.toFlux(): Flux<T> = toIterable().toFlux()
fun <T> Iterable<T>.toFlux(): Flux<T> = Flux.fromIterable(this)
fun <T> Sequence<T>.toFlux(): Flux<T> = Flux.fromIterable(object : Iterable<T> {
    override fun iterator(): Iterator<T> = this@toFlux.iterator()
})

fun <T> Stream<T>.toFlux(): Flux<T> = Flux.fromStream(this)

fun BooleanArray.toFlux(): Flux<Boolean> = this.toList().toFlux()
fun ByteArray.toFlux(): Flux<Byte> = this.toList().toFlux()
fun ShortArray.toFlux(): Flux<Short> = this.toList().toFlux()
fun IntArray.toFlux(): Flux<Int> = this.toList().toFlux()
fun LongArray.toFlux(): Flux<Long> = this.toList().toFlux()
fun FloatArray.toFlux(): Flux<Float> = this.toList().toFlux()
fun DoubleArray.toFlux(): Flux<Double> = this.toList().toFlux()
fun <T> Array<out T>.toFlux(): Flux<T> = Flux.fromArray(this)

private fun <T> Iterator<T>.toIterable() = object : Iterable<T> {
    override fun iterator(): Iterator<T> = this@toIterable
}

fun <T> Throwable.toFlux(): Flux<T> = Flux.error(this)

fun Flux<*>.test() = StepVerifier.create(this)
fun Flux<*>.test(n: Long) = StepVerifier.create(this, n)

inline fun <reified T : Any> Flux<*>.cast(): Flux<T> = cast(T::class.java)

fun <T, E : Throwable> Flux<T>.doOnError(exceptionType: KClass<E>, onError: (E) -> Unit) : Flux<T> =
        doOnError(exceptionType.java, { onError(it) })

fun <T, E : Throwable> Flux<T>.mapError(exceptionType: KClass<E>, onError: (E) -> Throwable) : Flux<T> =
        mapError(exceptionType.java, { onError(it) })

fun <T : Any> Flux<*>.ofType(kClass: KClass<T>) : Flux<*> = ofType(kClass.java)
inline fun <reified T : Any> Flux<*>.ofType() : Flux<*> = ofType(T::class.java)

fun <T : Any, E : Throwable> Flux<T>.onErrorResumeWith(exceptionType: KClass<E>, onError: (E) -> Publisher<T>) : Flux<T> =
        onErrorResumeWith(exceptionType.java, { onError(it) })
