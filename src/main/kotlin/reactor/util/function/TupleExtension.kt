package reactor.util.function

// Extensions for Tuples to work with destructuring declarations

operator fun <T> Tuple2<T, *>.component1(): T? = t1
operator fun <T> Tuple2<*, T>.component2(): T? = t2
operator fun <T> Tuple3<*, *, T>.component3(): T? = t3
operator fun <T> Tuple4<*, *, *, T>.component4(): T? = t4
operator fun <T> Tuple5<*, *, *, *, T>.component5(): T? = t5
operator fun <T> Tuple6<*, *, *, *, *, T>.component6(): T? = t6
operator fun <T> Tuple7<*, *, *, *, *, *, T>.component7(): T? = t7
operator fun <T> Tuple8<*, *, *, *, *, *, *, T>.component8(): T? = t8

