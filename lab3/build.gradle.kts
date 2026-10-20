plugins {
    java
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.hibernate.validator:hibernate-validator:8.0.1.Final")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(23)
    }
}

sourceSets {
    main {
        java.setSrcDirs(listOf("src"))
    }
}

application {
    mainClass = "Main"
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}
