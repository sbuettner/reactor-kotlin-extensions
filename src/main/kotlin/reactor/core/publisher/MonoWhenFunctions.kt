package reactor.core.publisher

import org.reactivestreams.Publisher
import reactor.util.function.*


/**
 * Merges the given [Mono]s into a new [Mono]. An alias for a corresponding [Mono.when]
 * to avoid use of `when`, which is a keyword in Kotlin.
 *
 * @author DoHyung Kim
 */
fun <T1, T2> whenComplete(p1: Mono<out T1>, p2: Mono<out T2>): Mono<Tuple2<T1, T2>> =
        Mono.`when`(p1, p2)

/**
 * Merges the given [Mono]s into a new [Mono] by combining them with [combinator].
 * An alias for a corresponding [Mono.when] to avoid use of `when`, which is a keyword in Kotlin.
 *
 * @author DoHyung Kim
 */
fun <T1, T2, O> whenComplete(p1: Mono<out T1>, p2: Mono<out T2>, combinator: (T1, T2) -> O): Mono<O> =
        Mono.`when`(p1, p2, combinator)

/**
 * Merges the given [Mono]s into a new [Mono]. An alias for a corresponding [Mono.when]
 * to avoid use of `when`, which is a keyword in Kotlin.
 *
 * @author DoHyung Kim
 */
fun <T1, T2, T3> whenComplete(p1: Mono<out T1>, p2: Mono<out T2>, p3: Mono<out T3>): Mono<Tuple3<T1, T2, T3>> =
        Mono.`when`(p1, p2, p3)

/**
 * Merges the given [Mono]s into a new [Mono]. An alias for a corresponding [Mono.when]
 * to avoid use of `when`, which is a keyword in Kotlin.
 *
 * @author DoHyung Kim
 */
fun <T1, T2, T3, T4> whenComplete(p1: Mono<out T1>, p2: Mono<out T2>, p3: Mono<out T3>,
                                  p4: Mono<out T4>): Mono<Tuple4<T1, T2, T3, T4>> =
        Mono.`when`(p1, p2, p3, p4)

/**
 * Merges the given [Mono]s into a new [Mono]. An alias for a corresponding [Mono.when]
 * to avoid use of `when`, which is a keyword in Kotlin.
 *
 * @author DoHyung Kim
 */
fun <T1, T2, T3, T4, T5> whenComplete(p1: Mono<out T1>, p2: Mono<out T2>,
                                      p3: Mono<out T3>, p4: Mono<out T4>,
                                      p5: Mono<out T5>): Mono<Tuple5<T1, T2, T3, T4, T5>> =
        Mono.`when`(p1, p2, p3, p4, p5)

/**
 * Merges the given [Mono]s into a new [Mono]. An alias for a corresponding [Mono.when]
 * to avoid use of `when`, which is a keyword in Kotlin.
 *
 * @author DoHyung Kim
 */
fun <T1, T2, T3, T4, T5, T6> whenComplete(p1: Mono<out T1>, p2: Mono<out T2>,
                                          p3: Mono<out T3>, p4: Mono<out T4>, p5: Mono<out T5>,
                                          p6: Mono<out T6>): Mono<Tuple6<T1, T2, T3, T4, T5, T6>> =
        Mono.`when`(p1, p2, p3, p4, p5, p6)

/**
 * Aggregates this [Iterable] of void [Publisher]s into a new [Mono].
 * An alias for a corresponding [Mono.when] to avoid use of `when`, which is a keyword in Kotlin.
 *
 * @author DoHyung Kim
 */
fun Iterable<Publisher<Void>>.whenComplete(): Mono<Void> = Mono.`when`(this)

/**
 * Merges this [Iterable] of [Mono]s into a new [Mono] by combining them
 * with [combinator]. An alias for a corresponding [Mono.when] to avoid use of `when`,
 * which is a keyword in Kotlin.
 *
 * @author DoHyung Kim
 */
@Suppress("UNCHECKED_CAST")
inline fun <T, R> Iterable<Mono<T>>.whenComplete(crossinline combinator: (List<T>) -> R): Mono<R> =
        Mono.`when`(this) { combinator(it.asList() as List<T>) }

/**
 * Aggregates the given void [Publisher]s into a new void [Mono].
 * An alias for a corresponding [Mono.when] to avoid use of `when`, which is a keyword in Kotlin.
 *
 * @author DoHyung Kim
 */
fun whenComplete(vararg sources: Publisher<Void>): Mono<Void> = MonoBridges.`when`(sources)

/**
 * Aggregates the given [Mono]s into a new [Mono]. An alias for a corresponding [Mono.when]
 * to avoid use of `when`, which is a keyword in Kotlin.
 *
 * @author DoHyung Kim
 */
@Suppress("UNCHECKED_CAST")
fun <R> whenComplete(vararg monos: Mono<*>, combinator: (Array<*>) -> R): Mono<R> =
        MonoBridges.`when`(combinator, monos)