package Stepdefination;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import operation.ServiceTesting;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;


public class FixturesAPI {
	ServiceTesting serviceTesting;
	@Before
	public void setup() {
		serviceTesting = new ServiceTesting();
	}

	@Given("I want to Retrieve all fixtures")
	public void i_want_Retrieve_all_fixtures() throws ClientProtocolException, IOException {
		
		serviceTesting.retrieveAllFixtures();		
	}	
	
	@And("Assert that there are total three fixtures in return result")
	public void Assert_Total_three_Fixtures() {
		serviceTesting.total_Fixtures();	
	}
	
	@And("Assert that each of the three fixtures has a fixtureId value")
	public void Assert_each_fixture_has_fixtureid() {	
		serviceTesting.assert_fixerId();
	}
		
	@Given("I want to create new fixture")
	public void create_new_fixture() throws ClientProtocolException, IOException {	
		serviceTesting.createnewfixture();
	}
	
	@And("Get the new fixture")
	public void get_new_fixture() throws ClientProtocolException, IOException {
		serviceTesting.get_New_fixture();
	}
	
	@And("Assert within the teams array that the first object has a teamId")
	public void assert_teamId() {
		serviceTesting.assert_teamId();
	}
	
	@Given("I want to create new fixture for performance check")
	public void create_new_fixture_for_performance() throws ClientProtocolException, IOException {
		serviceTesting.createnewfixtureForPerformance();
	}
	
	@And("then retrieve it as soon as it is available")
	public void retrieve_fixture_as_soon_as_it_is_available() throws ClientProtocolException, IOException {
		serviceTesting.retrieve_fixture_as_soon_as_availaible();
		
	}
	
	@Given("I want to create and then delete a new fixture")
	public void create_and_delete_fixture() throws IOException {
		serviceTesting.createDeletenewFixture();
		
	}
	
	@And("Assert that the fixture no longer exists")
	public void assert_fixture_no_longer_exists() throws ClientProtocolException, IOException {
		serviceTesting.assert_Fixture_No_Longer_exist();
	}


}
