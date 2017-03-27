import org.gradle.jvm.tasks.Jar
import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
	val kotlinVersion = "1.1.1"
	extra["kotlinVersion"] = kotlinVersion

	repositories {
		mavenCentral()
		jcenter()
	}

	dependencies {
		classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
		classpath("org.jetbrains.dokka:dokka-gradle-plugin:0.9.13")
	}
}

apply {
	plugin("kotlin")
	plugin("maven")
	plugin("org.jetbrains.dokka")
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

val dokkaJar = task<Jar>("dokkaJar") {
	dependsOn("dokka")
	classifier = "javadoc"
	from((tasks.getByName("dokka") as DokkaTask).outputDirectory)
}
val sourcesJar = task<Jar>("sourcesJar") {
	classifier = "sources"
	from(the<JavaPluginConvention>().sourceSets.getByName("main").allSource)
}

artifacts.add("archives", dokkaJar)
artifacts.add("archives", sourcesJar)
