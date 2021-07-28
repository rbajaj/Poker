package TestRun;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features="C:\\Users\\rbajaj\\eclipse-workspace\\PokerMockAPI\\src\\test\\resources\\Feature",glue={"Stepdefination"}, monochrome=true, plugin = { "pretty", "html:target/cucumber-reports" })
public class RunTest {

}
 