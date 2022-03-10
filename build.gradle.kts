// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
//    ext {
//        compose_version = '1.0.0'
//    }
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Libraries.classpathGradle)
        classpath(Libraries.classpathKotlinGradle)
        classpath(Libraries.hilt)
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}