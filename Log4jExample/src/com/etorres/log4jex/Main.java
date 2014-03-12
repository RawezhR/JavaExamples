package com.etorres.log4jex;

import org.apache.log4j.Logger;
/*
 * Author: Edwin Torres
 *  email: CoachEd@gmail.com
 * 
 * This example shows how to use log4j logging in your programs.
 * 
 * log4J is a hierarchy: all, trace, debug, info, warn, error, fatal
 * For example, setting the log level to warn will give you warn, error and fatal
 * messages.
 * 
 * When running this program, make sure the log4j.properties file is in the same
 * directory. The data/log4j.properties file is included in the project for
 * reference.
 * 
 * The log file will be in a folder named logs in the current directory.
 */
public class Main {

    /*
     * A static logger for all classes in the project to use.
     */
    private static Logger log = Logger.getLogger("My Logger");

    public static void main(final String[] args) {
        Main.getLog().trace("Main::main()  entered");
        
        //print the current log level (set by log4j.properties)
        System.out.println(log.getEffectiveLevel().toString());
        
        Main m = new Main();
        
        String str = "Hello";
        m.foo(str);
        
        str = null;
        m.foo(str);
        
        m.bar();
        
        Main.getLog().trace("Main::main()  exited");
    }
    
    public void foo(String s) {
        Main.getLog().trace("Main::foo()  entered");
        
        if (s == null)
            Main.getLog().error("Main::foo()  s is null!");
        else
            Main.getLog().debug("Main::foo()  s is " + s);
        
        Main.getLog().trace("Main::foo()  exited");
    }
    
    public void bar() {
        Main.getLog().trace("Main::bar()  entered");
        
        for (int i=0; i < 5; i++) {
            Main.getLog().debug("Main::bar()  i is " + i);
        }
        
        Main.getLog().trace("Main::bar()  exited");
    }
    
    public static Logger getLog() {
        return log;
    }

    public static void setLog(Logger log) {
        Main.log = log;
    }
}
