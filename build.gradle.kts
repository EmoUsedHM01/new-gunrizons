
plugins {
    id("com.gtnewhorizons.gtnhconvention")
}

configurations.all {
    resolutionStrategy {
        force("com.github.GTNewHorizons:GTNHLib:0.7.10")
    }
}
