plugins {
    java
    id("com.gradleup.shadow") version "8.3.5"
}

group = "com.example"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://m2.dv8tion.net/releases")
    maven("https://nexus.scarsz.me/content/groups/public/")
}

dependencies {
    // Paper API 1.21.1+
    compileOnly("io.papermc.paper:paper-api:1.21.1-R0.1-SNAPSHOT")
    
    // DiscordSRV API
    compileOnly("com.discordsrv:discordsrv:1.29.0")
    
    // Jedis & Gson (Shaded)
    implementation("redis.clients:jedis:5.2.0")
    implementation("com.google.code.gson:gson:2.11.0")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

tasks {
    shadowJar {
        archiveClassifier.set("")
        // Relocate libraries to prevent conflicts with other plugins
        relocate("redis.clients.jedis", "com.example.aurachat.libs.jedis")
        relocate("org.apache.commons.pool2", "com.example.aurachat.libs.pool2")
        relocate("com.google.gson", "com.example.aurachat.libs.gson")
    }

    build {
        dependsOn(shadowJar)
    }
}
