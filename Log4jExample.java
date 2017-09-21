import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
/*
 * This example shows how to use log4j logging in your programs.
 * 
 * log4J is a hierarchy: all, trace, debug, info, warn, error, fatal
 * For example, setting the log level to warn will give you warn, error and fatal
 * messages.
 * 
 * The dat/log4j.properties file is included in the project for
 * reference.
 * 
 * The log file will be in a folder named logs in the current directory.
 */
public class Log4jExample {

	/*
	 * A static logger for all classes in the project to use.
	 */
	private static Logger log = Logger.getLogger("My Logger");
	private static final String path = "dat/log4j.properties";

	public static void main(final String[] args) {

		PropertyConfigurator.configure(path); //find the properties file

		Log4jExample.getLog().trace("Log4jExample::main()  entered");

		//print the current log level (set by log4j.properties)
		System.out.println(log.getEffectiveLevel().toString());

		Log4jExample logger = new Log4jExample();

		String str = "Hello";
		logger.foo(str);

		str = null;
		logger.foo(str);

		logger.bar();

		Log4jExample.getLog().trace("Log4jExample::main()  exited");
	}

	public void foo(String s) {
		Log4jExample.getLog().trace("Log4jExample::foo()  entered");

		if (s == null)
			Log4jExample.getLog().error("Log4jExample::foo()  s is null!");
		else
			Log4jExample.getLog().debug("Log4jExample::foo()  s is " + s);

		Log4jExample.getLog().trace("Log4jExample::foo()  exited");
	}

	public void bar() {
		Log4jExample.getLog().trace("Log4jExample::bar()  entered");

		for (int i=0; i < 5; i++) {
			Log4jExample.getLog().debug("Log4jExample::bar()  i is " + i);
		}

		Log4jExample.getLog().trace("Log4jExample::bar()  exited");
	}

	public static Logger getLog() {
		return log;
	}

	public static void setLog(Logger log) {
		Log4jExample.log = log;
	}
}
