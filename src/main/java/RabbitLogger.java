import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Static class responsible for logging messages and errors.
 */
public class RabbitLogger {
    private static PrintWriter errorWriter, logWriter, shutdownWriter;
    private static String errorsPath = "/var/log/eiffelactory/lib_java_errors.log";
    private static String rabbitLogsPath = "//var/log/eiffelactory/lib_rabbitmq_received.log";
    private static String shutdownPath = "/var/log/eiffelactory/lib_shutdown_errors.log";
    static {
        FileWriter errorFileWriter, logFileWriter, shutdownFileWriter;
        try {
            errorFileWriter = new FileWriter(errorsPath,  true);
            logFileWriter = new FileWriter (rabbitLogsPath,  true);
            shutdownFileWriter = new FileWriter(shutdownPath, true);
            errorWriter = new PrintWriter(errorFileWriter);
            logWriter = new PrintWriter(logFileWriter);
            shutdownWriter = new PrintWriter(shutdownFileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeRabbitLog(String log) {
        logWriter.println(new Timestamp(new Date().getTime()));
        logWriter.flush();
        logWriter.println(log);
        logWriter.flush();
    }

    public static void writeJavaError(Throwable e) {
        errorWriter.println(new Timestamp(new Date().getTime()));
        errorWriter.flush();
        e.printStackTrace(errorWriter);
        errorWriter.flush();
    }

    public static void writeJavaError(String e) {
        errorWriter.println(new Timestamp(new Date().getTime()));
        errorWriter.flush();
        errorWriter.println(e);
        errorWriter.flush();
    }

    public static void writeShutdownError(Throwable e) {
        shutdownWriter.println(new Timestamp(new Date().getTime()));
        shutdownWriter.flush();
        e.printStackTrace(shutdownWriter);
        shutdownWriter.flush();
    }

    public static void writeShutdownError(String e) {
        shutdownWriter.println(new Timestamp(new Date().getTime()));
        shutdownWriter.flush();
        shutdownWriter.println(e);
        shutdownWriter.flush();
    }

    public static void closeShutdownWriter() {
        if (shutdownWriter != null ) {
            shutdownWriter.close();
        }
    }

    public static void closeWriters() {
        if (errorWriter != null) {
            errorWriter.close();
            errorWriter = null;
        }
        if (logWriter != null) {
            logWriter.close();
            logWriter = null;
        }
    }
}
