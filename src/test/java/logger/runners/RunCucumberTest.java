package logger.runners;

import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
// Denna pekar på mappen i src/test/resources där dina .feature-filer ligger
@SelectClasspathResource("features") 
// Denna berättar var dina steg-definitioner (Java-filer) ligger
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "logger.steps")
public class RunCucumberTest {
    // Denna klass ska vara tom, den fungerar bara som en startknapp
}