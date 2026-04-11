
plugins {
    id("com.gtnewhorizons.gtnhconvention")
}

version = "1.0.1"

// This has to be here, because otherwise 2.0.7 GTNH Gradle script pulls GTNHLib version
// incompatible with dependencies from 2.8.4.
configurations.all {
    resolutionStrategy {
        force("com.github.GTNewHorizons:GTNHLib:0.7.10")
    }
}

// Angelica's GLSM mixins crash on dedicated servers
tasks.named("runServer25") {
    (this as JavaExec).classpath = classpath.filter { !it.name.contains("Angelica") }
}
