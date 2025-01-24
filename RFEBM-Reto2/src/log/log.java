package log;

import java.io.IOException;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

public class log {
    
    private static Logger LOG;
    
    public static Logger getLogger(Class<?> name) {
        try {
            LOG = Logger.getLogger(name);
            String logfile = "resources/logs/RFEMBlog";
            
            // Definir el patrón para el log
            PatternLayout defaultLayout = new PatternLayout("%d{dd-MM-yyyy HH:mm:ss} %m%n");
            
            // Crear y configurar el RollingFileAppender
            RollingFileAppender rollingFileAppender = new RollingFileAppender();
            rollingFileAppender.setFile(logfile + ".log", true, false, 0);
            rollingFileAppender.setMaxFileSize("10MB");
            rollingFileAppender.setAppend(true);
            rollingFileAppender.setLayout(defaultLayout);
            
            // Añadir el appender y configurar el logger
            LOG.removeAllAppenders();
            LOG.addAppender(rollingFileAppender);
            LOG.setAdditivity(true);
            
            // Mensajes para el log
           /** LOG.debug("DEBUG: imprimiendo");
            LOG.info("INFO: imprimiendo");
            LOG.error("ERROR: imprimiendo");
            LOG.warn("WARN: imprimiendo");
            LOG.fatal("FATAL: imprimiendo"); */

        } catch (IOException ex) {
            // Registrar cualquier error relacionado con la configuración
            java.util.logging.Logger.getLogger(log.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return LOG;
    }
}
