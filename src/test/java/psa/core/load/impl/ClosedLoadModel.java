package psa.core.load.impl;

import psa.core.config.DefaultConfig;
import psa.core.load.LoadModel;

import io.gatling.javaapi.core.PopulationBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;


public class ClosedLoadModel implements LoadModel {
    DefaultConfig config;
    ScenarioBuilder userScenario;
    public ClosedLoadModel(ScenarioBuilder userScenario, DefaultConfig config) {
        this.userScenario = userScenario;
        this.config = config;
    }

    @Override
    public PopulationBuilder buildLoadModel() {
        return userScenario
                .injectClosed(
                        rampConcurrentUsers(0).to(config.vUsers).during(config.rampDuration),
                        constantConcurrentUsers(config.vUsers).during(config.constantDuration)
                );
    }
}
