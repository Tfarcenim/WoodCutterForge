package tfar.woodcutter;

import java.util.logging.LogRecord;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.filter.AbstractFilter;


public class ShutUpRecipeBookFilter extends AbstractFilter implements java.util.logging.Filter {

	// Oracle/Java Filter
	@Override
	public boolean isLoggable(LogRecord record) {
		return false;
	}

	// Apache/Log4J Filter
	@Override
	public Result filter(LogEvent event) {
		return Result.DENY;
	}

	@Override
	public Result filter(Logger logger, Level level, Marker marker, String msg, Object p0) {
		return Result.DENY;
	}

	@Override
	public Result filter(Logger logger, Level level, Marker marker, String msg, Object... params) {
		return Result.DENY;
	}
}