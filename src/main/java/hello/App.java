package hello;

import org.jooby.Jooby;

/**
 * Hello world gradle project.
 */
public class App extends Jooby {

  {
    get(req -> {
      String name = req.param("name").value("");
      if (name != "") {
        return "Hello " + name + "!";
      } else {
        return "Hello!";
      }
    });
  }

  public static void main(String[] args) {
    run(App::new, args);
  }
}
