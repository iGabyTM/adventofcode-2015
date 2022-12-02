plugins {
    java
}

group = "me.gabytm"
version = "2015"

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

repositories {
    mavenCentral()
}