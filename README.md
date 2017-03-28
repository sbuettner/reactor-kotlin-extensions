# Kotlin extensions for Reactor

This library exposes a set of Kotlin [extensions](https://kotlinlang.org/docs/reference/extensions.html)
that allow a more idiomatic Kotlin usage of [Reactor](https://projectreactor.io/) APIs.

## Dependency

### Release

- Repository: `https://repo.spring.io/milestone`
- Artifact: `io.projectreactor:reactor-kotlin-extensions:1.0.0.M1`

### Snapshot

- Repository: `https://repo.spring.io/snapshot`
- Artifact: `io.projectreactor:reactor-kotlin-extensions:1.0.0.BUILD-SNAPSHOT`

## Usage

[API documentation](https://repo.spring.io/snapshot/io/projectreactor/reactor-kotlin-extensions/1.0.0.BUILD-SNAPSHOT/reactor-kotlin-extensions-1.0.0.BUILD-SNAPSHOT-javadoc.jar!/reactor-kotlin-extensions/index.html)

See these unit tests for code examples:
 - [Flux extensions](https://github.com/reactor/reactor-kotlin-extensions/blob/master/src/test/kotlin/reactor/core/publisher/FluxExtensionsTests.kt)
 - [Mono extensions](https://github.com/reactor/reactor-kotlin-extensions/blob/master/src/test/kotlin/reactor/core/publisher/MonoExtensionsTests.kt)
 - [StepVerifier extensions](https://github.com/reactor/reactor-kotlin-extensions/blob/master/src/test/kotlin/reactor/test/StepVerifierExtensionsTests.kt)
 - [Tuple extensions](https://github.com/reactor/reactor-kotlin-extensions/blob/master/src/test/kotlin/reactor/util/function/TupleExtensionsTests.kt)

## About

Some extensions are inspired from [RxKotlin](https://github.com/ReactiveX/RxKotlin).

This project is Apache 2.0 licensed.