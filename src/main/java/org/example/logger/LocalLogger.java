package org.example.logger;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@AllArgsConstructor
public class LocalLogger {
    @Setter
    private Logger logger;

    public static LocalLogger getLogger(Class<?> clazz) {
        return new LocalLogger(LoggerFactory.getLogger(clazz));
    }

    public void error(String str) {
        logger.error(str);
    }

    public void info(String str) {
        logger.info(str);
    }

    public void warn(String str) {
        logger.warn(str);
    }

    public void debug(String str) {
        logger.debug(str);
    }
}
