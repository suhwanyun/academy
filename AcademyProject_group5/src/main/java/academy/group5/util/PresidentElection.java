package academy.group5.util;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

public class PresidentElection {
	private static final Logger logger = LoggerFactory.getLogger(PresidentElection.class);

	// Schedule 1 second Repeat Time
	@Scheduled(fixedRate = 1000)
	public void work() {
		// 여기에 Business Logic 을 넣으면 됩니다
		Calendar cal = Calendar.getInstance();

		logger.trace("First MyTask Work  :" + cal.getTime());
	}
}
