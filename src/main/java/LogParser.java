import java.io.*;
import java.util.regex.*;

public class LogParser {
    public static void main(String[] args) {
        File logFile = new File("logs/app.log");

        try (BufferedReader br = new BufferedReader(new FileReader(logFile))) {
            String line;
            Pattern errorPattern = Pattern.compile("\\[.*?\\] \\[ERROR\\] (.*)");

            while ((line = br.readLine()) != null) {
                Matcher matcher = errorPattern.matcher(line);
                if (matcher.find()) {
                    System.out.println("Found ERROR log: " + matcher.group(1));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading log file: " + e.getMessage());
        }
    }
}
