package listeners;

import org.apache.commons.io.FileUtils;
import org.testng.IExecutionListener;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class ConfigurationReportListener implements IExecutionListener {

    private String xmlReporterFolderTextPath = System
            .getProperty("xml-report-folder");
    private String environmentConfigurationFileTextPath = System
            .getProperty("environment.configuration");

    @Override
    public void onExecutionStart() {

    }

    @Override
    public void onExecutionFinish() {
        copyConfigurationsForReport();
    }

    /**
     * copy configuration properties file to report
     */
    private void copyConfigurationsForReport() {
        String errorMessage;
        Path xmlReporterFolderPath = Paths.get(xmlReporterFolderTextPath);
        if (Files.exists(xmlReporterFolderPath)) {
            if (new File(environmentConfigurationFileTextPath).exists()) {
                try {
                    FileUtils.copyFileToDirectory(new File(
                            environmentConfigurationFileTextPath), new File(
                            xmlReporterFolderTextPath));
                } catch (IOException e) {

                }
            } else {
                errorMessage = "Environment configuration file "
                        + environmentConfigurationFileTextPath
                        + " doesn't exist";
                throw new RuntimeException(errorMessage);
            }
        } else {
            errorMessage = "Path for xml report files: "
                    + xmlReporterFolderPath.toAbsolutePath() + " doesn't exist";
            throw new RuntimeException(errorMessage);
        }
    }
}
