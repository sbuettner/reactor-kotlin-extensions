buildscript {
	val kotlinVersion = "1.0.6"
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
}

version = "0.0.1-SNAPSHOT"

repositories {
	mavenCentral()
}

val kotlinVersion = extra["kotlinVersion"] as String
val reactorCoreVersion = "3.0.4.RELEASE"

dependencies {
	compile("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
	compile("io.projectreactor:reactor-core:$reactorCoreVersion")
	compileOnly("io.projectreactor.addons:reactor-test:$reactorCoreVersion")
	testCompile("io.projectreactor.addons:reactor-test:$reactorCoreVersion")
	testCompile("junit:junit:4.12")
}

