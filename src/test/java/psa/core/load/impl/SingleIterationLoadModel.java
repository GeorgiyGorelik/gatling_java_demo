package psa.core.load.impl;

import psa.core.config.DefaultConfig;
import psa.core.load.LoadModel;
import io.gatling.javaapi.core.PopulationBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;

public class SingleIterationLoadModel implements LoadModel {
    DefaultConfig config;
    ScenarioBuilder userScenario;
    public SingleIterationLoadModel(ScenarioBuilder userScenario, DefaultConfig config) {
        this.userScenario = userScenario;
        this.config = config;
    }

    @Override
    public PopulationBuilder buildLoadModel() {
        return userScenario
                .injectOpen(atOnceUsers(1));
    }
}
