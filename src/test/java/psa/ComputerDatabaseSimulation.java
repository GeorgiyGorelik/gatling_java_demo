package psa;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;
import psa.core.config.DefaultConfig;
import psa.core.load.impl.OpenLoadModel;

public class ComputerDatabaseSimulation extends Simulation {

    public static DefaultConfig config = new DefaultConfig();

    HttpProtocolBuilder httpProtocol =
            http.baseUrl(config.baseUrl)
                    .acceptHeader("application/json")
                    .contentTypeHeader("application/json");

    ScenarioBuilder myFirstScenario = scenario("My First Scenario")
            .exec(http("GET_computers")
                    .get("/computers/"));

    PopulationBuilder loadModel = new OpenLoadModel(myFirstScenario, config).buildLoadModel();

    {
        setUp(loadModel).protocols(httpProtocol);
    }

}