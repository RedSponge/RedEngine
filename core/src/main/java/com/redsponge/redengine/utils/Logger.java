package com.redsponge.redengine.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.TimeUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

    public static final int ERROR = 3;
    public static final int LOG = 2;
    public static final int DEBUG = 1;

    private static final String[] LOG_TITLES = {"DEBUG", "INFO", "ERR"};

    private static int logLevel = LOG;

    private static final DateFormat FORMAT = new SimpleDateFormat("HH:mm:ss");

    /**
     * Log if the {@link Logger#logLevel} is {@link Logger#LOG} or less
     * @param from Log from
     * @param toLog What to log
     */
    public static void log(Object from, Object... toLog) {
        log(from.getClass(), toLog);
    }

    /**
     * Log if the {@link Logger#logLevel} is {@link Logger#LOG} or less
     * @param from Log from class
     * @param toLog What to log
     */
    public static void log(Class<?> from, Object... toLog) {
        logUnderLevel(LOG, from, toLog);
    }

    /**
     * Log if the {@link Logger#logLevel} is {@link Logger#DEBUG} or less
     * @param from Log from
     * @param toLog What to log
     */
    public static void debug(Object from, Object... toLog) {
        debug(from.getClass(), toLog);
    }

    /**
     * Log if the {@link Logger#logLevel} is {@link Logger#DEBUG} or less
     * @param from Log from class
     * @param toLog What to log
     */
    public static void debug(Class<?> from, Object... toLog) {
        logUnderLevel(DEBUG, from, toLog);
    }

    /**
     * Log if the {@link Logger#logLevel} is {@link Logger#ERROR} or less
     * @param from Log from
     * @param toLog What to log
     */
    public static void error(Object from, Object... toLog) {
        error(from.getClass(), toLog);
    }

    /**
     * Log if the {@link Logger#logLevel} is {@link Logger#ERROR} or less
     * @param from Log from class
     * @param toLog What to log
     */
    public static void error(Class<?> from, Object... toLog) {
        logUnderLevel(ERROR, from, toLog);
    }

    /**
     * Prints a formatted log if the level is good
     * @param level The log level
     * @param from The class name
     * @param toLog What to print
     */
    private static void logUnderLevel(int level, Class<?> from, Object... toLog) {
        if(logLevel <= level) {
            StringBuilder sb = new StringBuilder();
            for (Object o : toLog) {
                sb.append(o).append(" ");
            }
            Gdx.app.log(FORMAT.format(new Date(TimeUtils.millis())) + "] [" + from.getSimpleName() + "] [" + LOG_TITLES[level-1], sb.toString());
        }
    }

    /**
     * Set the log level
     * DEBUG < LOG < ERROR
     * @param logLevel The new log level
     */
    public static void setLogLevel(int logLevel) {
        Logger.logLevel = logLevel;
    }

    /**
     * Get the log level
     * @return the log level
     */
    public static int getLogLevel() {
        return logLevel;
    }
}
