package inc.evil.cnscatalogservice.web;

import inc.evil.cnscatalogservice.config.GreetingProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private final GreetingProperties greetingProperties;

    public HomeController(GreetingProperties greetingProperties) {
        this.greetingProperties = greetingProperties;
    }

    @GetMapping("/")
    public String getGreeting() {
        return greetingProperties.getGreeting();
    }
}
