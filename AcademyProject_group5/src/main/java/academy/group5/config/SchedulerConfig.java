package academy.group5.config;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import academy.group5.util.CustomTrigger;
import academy.group5.util.PresidentElection;

//Annotation Configurer
@Configuration
@EnableScheduling
public class SchedulerConfig implements SchedulingConfigurer {

	@Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        // 스케줄러 등록
        taskRegistrar.setScheduler(taskScheduler());
        taskRegistrar.addTriggerTask(
            new Runnable() {
                public void run() {              
                	presidentElection().work();
                }
            },
            new CustomTrigger() );
    }
 
	@Bean(destroyMethod="shutdown")
    public Executor taskScheduler() {
        return Executors.newScheduledThreadPool(42);
    }
    
    @Bean
    public PresidentElection presidentElection() {
        return new PresidentElection();
    }
}