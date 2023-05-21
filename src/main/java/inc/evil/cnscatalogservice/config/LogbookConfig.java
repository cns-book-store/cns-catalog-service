package inc.evil.cnscatalogservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.logbook.Logbook;

import java.util.Set;

import static org.zalando.logbook.core.Conditions.*;
import static org.zalando.logbook.core.HeaderFilters.authorization;
import static org.zalando.logbook.core.QueryFilters.replaceQuery;

@Configuration
public class LogbookConfig {

    @Bean
    public Logbook logbook() {
        return Logbook.builder()
                .condition(exclude(
                        requestTo("/health"),
                        requestTo("/admin/**"),
                        contentType("application/octet-stream"),
                        header("X-Secret", Set.of("1", "true")::contains)))
                .headerFilter(authorization())
                .queryFilter(replaceQuery("password", "<secret>"))
                .build();
    }
}
