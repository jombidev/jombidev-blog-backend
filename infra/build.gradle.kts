dependencies {
    implementation(project(":core"))

    implementation("com.oracle.cloud.spring:spring-cloud-oci-starter-storage")

    implementation("io.jsonwebtoken:jjwt-api")
    runtimeOnly("io.jsonwebtoken:jjwt-impl")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson")
}
