package hello;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import com.typesafe.config.ConfigFactory;
import org.jooby.test.JoobyRule;
import org.junit.ClassRule;
import org.junit.Test;

public class AppTest {

  /**
   * One app/server for all the test of this class. If you want to start/stop a new server per test,
   * remove the static modifier and replace the {@link ClassRule} annotation with {@link Rule}.
   */
  @ClassRule
  public static JoobyRule app = new JoobyRule(new App().conf("application.testing.conf"));

  public static final int appPort = ConfigFactory.load("application.testing.conf").getInt("application.port");

  @Test
  public void integrationTest() {
    given().
      port(appPort).
    when().
      get("/").
    then().
      body(equalTo("Hello!")).
      statusCode(200);
  }

  @Test
  public void nameParamIntegrationTest() {
    given().
      port(appPort).
    given().
      queryParam("name", "Peter").
    when().
      get("/").
    then().
      body(equalTo("Hello Peter!")).
      statusCode(200);
  }
}
