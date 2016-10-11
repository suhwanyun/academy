package academy.group5.config;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

//Annotation Configurer
@Configuration
@EnableScheduling
public class TermEndScheduler implements SchedulingConfigurer {
	
	private static final Logger logger = LoggerFactory.getLogger(TermEndScheduler.class);
	
	/*@Autowired
	TermRepo termRepo;*/
	
	@Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        // 스케줄러 등록
        taskRegistrar.setScheduler(taskScheduler());
        taskRegistrar.addTriggerTask(
            new Runnable() {
                public void run() {              

            		logger.trace("termScheduler"); /*{}", termRepo.getTodayTerm().getTermYear());
*/               	
                }
            },
            new Trigger() {
                @Override
                public Date nextExecutionTime(TriggerContext triggerContext) {
                    String cron = cronConfig();

                    CronTrigger trigger = new CronTrigger(cron);
                    Date nextExec = trigger.nextExecutionTime(triggerContext);
                    return nextExec;
                } 
            });
    }
 
	@Bean(destroyMethod="shutdown")
    public Executor taskScheduler() {
        return Executors.newScheduledThreadPool(42);
    }
	
	private String cronConfig() {
        String cronTabExpression = "*/30 * * * * *";
        return cronTabExpression;
    }
}