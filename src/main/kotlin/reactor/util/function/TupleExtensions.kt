package reactor.util.function


/**
 * Extension for [Tuple2] to work with destructuring declarations.
 *
 * @author DoHyung Kim
 */
operator fun <T> Tuple2<T, *>.component1(): T = t1

/**
 * Extension for [Tuple2] to work with destructuring declarations.
 *
 * @author DoHyung Kim
 */
operator fun <T> Tuple2<*, T>.component2(): T = t2

/**
 * Extension for [Tuple3] to work with destructuring declarations.
 *
 * @author DoHyung Kim
 */
operator fun <T> Tuple3<*, *, T>.component3(): T = t3

/**
 * Extension for [Tuple4] to work with destructuring declarations.
 *
 * @author DoHyung Kim
 */
operator fun <T> Tuple4<*, *, *, T>.component4(): T = t4

/**
 * Extension for [Tuple5] to work with destructuring declarations.
 *
 * @author DoHyung Kim
 */
operator fun <T> Tuple5<*, *, *, *, T>.component5(): T = t5

/**
 * Extension for [Tuple6] to work with destructuring declarations.
 *
 * @author DoHyung Kim
 */
operator fun <T> Tuple6<*, *, *, *, *, T>.component6(): T = t6

/**
 * Extension for [Tuple7] to work with destructuring declarations.
 *
 * @author DoHyung Kim
 */
operator fun <T> Tuple7<*, *, *, *, *, *, T>.component7(): T = t7

/**
 * Extension for [Tuple8] to work with destructuring declarations.
 *
 * @author DoHyung Kim
 */
operator fun <T> Tuple8<*, *, *, *, *, *, *, T>.component8(): T = t8
