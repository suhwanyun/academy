package academy.group5.config;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import academy.group5.repo.TermRepo;
import academy.group5.util.PresidentElectionTrigger;

//Annotation Configurer
@Configuration
@EnableScheduling
public class PresidentElectionScheduler implements SchedulingConfigurer {
	
	private static final Logger logger = LoggerFactory.getLogger(PresidentElectionScheduler.class);
	
	/*@Autowired
	TermRepo termRepo;*/
	
	@Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        // 스케줄러 등록
        taskRegistrar.setScheduler(taskScheduler());
        taskRegistrar.addTriggerTask(
            new Runnable() {
                public void run() {              

                	logger.trace("voteScheduler"/*, termRepo.getTodayTerm().getTermYear()*/);
               	
                }
            },
            new PresidentElectionTrigger() );
    }
 
	@Bean(destroyMethod="shutdown")
    public Executor taskScheduler() {
        return Executors.newScheduledThreadPool(42);
    }
}