package kz.home.librarysystem;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "kz.home.librarysystem")
@PropertySource("application.properties")
public class SpringConfiguration {
}
