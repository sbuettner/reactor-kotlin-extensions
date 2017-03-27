package reactor.core.publisher;

import org.reactivestreams.Publisher;

import java.util.function.Function;

/**
 * Utilities to avoid vararg array copying overhead when relaying vararg parameters
 * to underlying Java methods from their corresponding Kotlin functions.
 * <p>
 * When <a href="https://youtrack.jetbrains.com/issue/KT-17043">this</a> issue is
 * resolved, uses of these bridge methods can be removed.
 *
 * @author DoHyung Kim
 */
class MonoBridges {

    static Mono<Void> when(Publisher<Void>[] sources) {
        return Mono.when(sources);
    }

    static <R> Mono<R> when(Function<? super Object[], ? extends R> combinator, Mono<?>[] monos) {
        return Mono.when(combinator, monos);
    }

    private MonoBridges() {
        // Not to be instantiated
    }
}
