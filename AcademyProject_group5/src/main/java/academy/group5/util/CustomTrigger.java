package academy.group5.util;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;

public class CustomTrigger implements Trigger {

	private static final Logger logger = LoggerFactory.getLogger(CustomTrigger.class);

	@SuppressWarnings("deprecation")
	public Date nextExecutionTime(TriggerContext triggerContext) {

		logger.trace("Trigger Last Completion Time :  " + triggerContext.lastCompletionTime());

		Calendar cal = Calendar.getInstance();
		Date nextDate = cal.getTime();

		logger.trace("{}", nextDate);
		int nextDateMM = nextDate.getSeconds();
		// 1 분 후에 Trigger 가 발생
		nextDateMM = nextDateMM + 1;
		nextDate.setSeconds(nextDateMM);
		logger.trace("{}", nextDate);

		return nextDate;
	}
}
