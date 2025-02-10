package psa.core.load;

import io.gatling.javaapi.core.PopulationBuilder;

public interface LoadModel {
    PopulationBuilder buildLoadModel();
}
