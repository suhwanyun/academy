package academy.group5.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.Formatter;

import academy.group5.dto.etc.DateDto;

public class MinuteFormatter implements Formatter<DateDto>{
	
	private static final Logger logger = LoggerFactory.getLogger(MinuteFormatter.class);
	
	private final SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	@Override
	public String print(DateDto object, Locale locale) {
		logger.trace("dto:{}, str:{}", object.getTime(), fm.format(object.getTime()));
		return fm.format(object.getTime());
	}

	@Override
	public DateDto parse(String text, Locale locale) throws ParseException {
		DateDto dto = new DateDto();
		dto.setTime(fm.parse(text));
		return dto;
	}

}
