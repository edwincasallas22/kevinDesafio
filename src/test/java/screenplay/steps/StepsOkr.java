package screenplay.steps;

import co.com.sofkau.api.model.page.Page;
import co.com.sofkau.api.model.task.GetRequest;
import io.cucumber.java.Before;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.core.util.EnvironmentVariables;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.assertj.core.api.Assertions.assertThat;

public class StepsOkr {
    Actor actor = Actor.named("Elkin");
    EnvironmentVariables variables;
    @Before
    public void setup() {
        actor.whoCan(CallAnApi.at(variables.getProperty("baseurl")));
    }

    @Cuando("entre a la opción de dashboard de mi nombre")
    public void entreALaOpciónDeDashboardDeMiNombre() {
        actor.attemptsTo(GetRequest.withResource(variables.getProperty("pathUser").concat("6112ef6370e2131bb4730d1a")));
    }

    @Entonces("podré ver el dashboard de uno de mis OKR")
    public void podréVerElDashboardDeUnoDeMisOKR() {
        actor.should(seeThatResponse("ver el código de respuesta",
                response -> response.statusCode(200)));
        SerenityRest.lastResponse().print();
        Page page = SerenityRest.lastResponse()
                .jsonPath().getObject("", Page.class);
        assertThat(page).hasNoNullFieldsOrProperties();

    }
}
