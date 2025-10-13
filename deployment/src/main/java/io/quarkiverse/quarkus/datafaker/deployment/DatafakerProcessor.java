package io.quarkiverse.quarkus.datafaker.deployment;

import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.nativeimage.NativeImageResourcePatternsBuildItem;
import io.quarkus.deployment.builditem.nativeimage.RuntimeInitializedClassBuildItem;

class DatafakerProcessor {

    @BuildStep
    RuntimeInitializedClassBuildItem randomService() {
        return new RuntimeInitializedClassBuildItem("net.datafaker.service.RandomService");
    }

    @BuildStep
    NativeImageResourcePatternsBuildItem yamlResources() {
        return NativeImageResourcePatternsBuildItem.builder()
                .includePatterns("en/.*.yml$")
                .includePatterns(".*.yml$")
                .build();
    }
}
