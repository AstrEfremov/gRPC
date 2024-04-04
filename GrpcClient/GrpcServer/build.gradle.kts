import com.google.protobuf.gradle.id

plugins {
    java
    id("org.springframework.boot") version "3.2.0"
    id("io.spring.dependency-management") version "1.1.4"
    id("com.google.protobuf") version "0.9.4"
    id("org.sonarqube") version "4.0.0.2929"
    jacoco
}
group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
val grpcStarterVersion = "3.0.0.RELEASE"
val grpcVersion = "1.62.2"
val protobufVersion = "3.24.4"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("javax.annotation:javax.annotation-api:1.3.2")
    implementation("io.grpc:grpc-netty:${grpcVersion}")
    implementation("net.devh:grpc-server-spring-boot-starter:${grpcStarterVersion}") {
        exclude(group = "io.grpc", module = "grpc-netty-shaded")
    }
}
protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:${protobufVersion}"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:${grpcVersion}"
        }
    }
    generateProtoTasks {
        ofSourceSet("main").forEach {
            it.plugins {
                id("grpc") { }
            }
        }
    }
}



tasks.test {
    useJUnitPlatform()
}