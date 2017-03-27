package reactor.core.publisher

import org.junit.Test
import reactor.test.publisher.TestPublisher
import reactor.util.function.Tuples

class MonoWhenFunctionsTests {

    @Test
    fun `whenComplete with two Monos`() {
        whenComplete("foo1".toMono(), "foo2".toMono())
                .test()
                .expectNext(Tuples.of("foo1", "foo2"))
                .verifyComplete()
    }

    @Test
    fun `whenComplete with two Monos and combinator`() {
        whenComplete("foo1".toMono(), "foo2".toMono()) { a, b -> Pair(a, b) }
                .test()
                .expectNext(Pair("foo1", "foo2"))
                .verifyComplete()
    }

    @Test
    fun `whenComplete with three Monos`() {
        whenComplete("foo1".toMono(), "foo2".toMono(), "foo3".toMono())
                .test()
                .expectNext(Tuples.of("foo1", "foo2", "foo3"))
                .verifyComplete()
    }

    @Test
    fun `whenComplete with four Monos`() {
        whenComplete("foo1".toMono(), "foo2".toMono(), "foo3".toMono(), "foo4".toMono())
                .test()
                .expectNext(Tuples.of("foo1", "foo2", "foo3", "foo4"))
                .verifyComplete()
    }

    @Test
    fun `whenComplete with five Monos`() {
        whenComplete("foo1".toMono(), "foo2".toMono(), "foo3".toMono(), "foo4".toMono(), "foo5".toMono())
                .test()
                .expectNext(Tuples.of("foo1", "foo2", "foo3", "foo4", "foo5"))
                .verifyComplete()
    }

    @Test
    fun `whenComplete with six Monos`() {
        whenComplete("foo1".toMono(), "foo2".toMono(), "foo3".toMono(),
                "foo4".toMono(), "foo5".toMono(), "foo6".toMono())
                .test()
                .expectNext(Tuples.of("foo1", "foo2", "foo3", "foo4", "foo5", "foo6"))
                .verifyComplete()
    }

    @Test
    fun `whenComplete on an Iterable of void Publishers`() {
        val publishers = Array(3, { TestPublisher.create<Void>() })
        publishers.forEach { it.complete() }
        publishers.asIterable().whenComplete()
                .test()
                .verifyComplete()
    }

    @Test
    fun `whenComplete on an Iterable of Monos with combinator`() {
        listOf("foo1", "foo2", "foo3").map { it.toMono() }.whenComplete { it.joinToString() }
                .test()
                .expectNext("foo1, foo2, foo3")
                .verifyComplete()
    }

    @Test
    fun `whenComplete with void Publishers`() {
        val publishers = Array(3, { TestPublisher.create<Void>() })
        publishers.forEach { it.complete() }
        whenComplete(*publishers)
                .test()
                .verifyComplete()
    }

    @Test
    fun `whenComplete with Monos and combinator`() {
        whenComplete("foo1".toMono(), "foo2".toMono(), "foo3".toMono()) { it.joinToString() }
                .test()
                .expectNext("foo1, foo2, foo3")
                .verifyComplete()
    }
}