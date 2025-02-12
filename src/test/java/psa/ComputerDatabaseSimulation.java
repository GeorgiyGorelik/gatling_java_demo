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
            .exec(http("GET_/").get("/"))
            .exec(http("GET_computers").get("/computers/"))
            .exec(http("GET_computers_search").get("/computers?f=Macbook")
                    .check(css("a:contains('MacBook Pro')", "href").saveAs("computerUrl")))
            .exec(http("GET_#{computerUrl}").get("#{computerUrl}").check(status().is(200)))
            .exec(http("GET_computers_search").get("/computers?f=eee")
                    .check(css("a:contains('ASUS Eee PC 1005PE')", "href").saveAs("computerUrl2")))
            .exec(http("GET_#{computerUrl2}").get("#{computerUrl2}").check(status().is(200)));

    PopulationBuilder loadModel = new OpenLoadModel(myFirstScenario, config).buildLoadModel();

    {
        setUp(loadModel).protocols(httpProtocol);
    }

}