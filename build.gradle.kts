import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
	val kotlinVersion = "1.1.1"
	extra["kotlinVersion"] = kotlinVersion

	repositories {
		mavenCentral()
	}

	dependencies {
		classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
	}
}

apply {
	plugin("kotlin")
	plugin("maven")
}

group = "io.projectreactor"
version = "1.0.0.BUILD-SNAPSHOT"

repositories {
	mavenCentral()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		jvmTarget = "1.8"
	}
}

val kotlinVersion = extra["kotlinVersion"] as String
val reactorCoreVersion = "3.0.5.RELEASE"

dependencies {
	compile("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
	compile("io.projectreactor:reactor-core:$reactorCoreVersion")
	compileOnly("io.projectreactor.addons:reactor-test:$reactorCoreVersion")
	testCompile("io.projectreactor.addons:reactor-test:$reactorCoreVersion")
	testCompile("junit:junit:4.12")
}

