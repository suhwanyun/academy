package academy.group5.util;

import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;

public class TermEndTrigger implements Trigger {

	@SuppressWarnings("deprecation")
	public Date nextExecutionTime(TriggerContext triggerContext) {

		Calendar cal = Calendar.getInstance();
		Date nextDate = cal.getTime();

		int nextDateMM = nextDate.getSeconds();

		nextDateMM = nextDateMM + 30;
		nextDate.setSeconds(nextDateMM);

		return nextDate;
	}
}
