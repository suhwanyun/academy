package academy.group5.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

// Spring Core에 대한 설정
@Configuration
@ComponentScans({
	@ComponentScan("academy.group5.beans"),
	@ComponentScan("academy.group5.controller")
})
public class ApplicationConfig {
}
