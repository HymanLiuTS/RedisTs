package cn.codenest.redists.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

public class LogUtil {
    private static ConcurrentHashMap<Class<?>, Logger> map = new ConcurrentHashMap();

    public static void info(Class<?> clazz, String msg) {
        Logger logger = null;
        if (map.containsKey(clazz)) {
            logger = (Logger) map.get(clazz);
        } else {
            logger = (Logger) LoggerFactory.getLogger(clazz);
            map.put(clazz, logger);
        }
        if (logger != null) {
            logger.info(msg);
        }
    }

    public static void debug(Class<?> clazz, String msg) {
        Logger logger = null;
        if (map.containsKey(clazz)) {
            logger = (Logger) map.get(clazz);
        } else {
            logger = (Logger) LoggerFactory.getLogger(clazz);
            map.put(clazz, logger);
        }
        if (logger != null) {
            logger.debug(msg);
        }
    }

    public static void error(Class<?> clazz, String msg) {
        Logger logger = null;
        if (map.containsKey(clazz)) {
            logger = (Logger) map.get(clazz);
        } else {
            logger = (Logger) LoggerFactory.getLogger(clazz);
            map.put(clazz, logger);
        }
        if (logger != null) {
            logger.error(msg);
        }
    }

    public static void error(Class<?> clazz, Exception ex) {
        Logger logger = null;
        if (map.containsKey(clazz)) {
            logger = (Logger) map.get(clazz);
        } else {
            logger = (Logger) LoggerFactory.getLogger(clazz);
            map.put(clazz, logger);
        }
        if (logger != null) {
            logger.error("运行异常",ex);
        }
    }
}
