package operation;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.restassured.RestAssured;

public class ServiceTesting {
	String content;
	JsonArray jsonArray;
	HttpResponse response;
	int statusCode;
	HttpEntity entity;
	String path;
	String requestBody;
	HttpGet get;

	public void retrieveAllFixtures() throws ClientProtocolException, IOException {

		get = new HttpGet("http://localhost:3000/fixtures");
		HttpResponse response = HttpClientBuilder.create().build().execute(get);
		int statusCode = response.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, 200);
		System.out.println("Log in status code: " + statusCode);
		HttpEntity entity = response.getEntity();
		content = EntityUtils.toString(entity);
		// Retrieve all fixtures and Print in console in string formate
		System.out.println("Get contact data: " + content);
	}

	public void total_Fixtures() {

		JsonParser parser = new JsonParser();
		jsonArray = (JsonArray) parser.parse(content);
		System.out.println("Total Fixture is " + jsonArray.size());
		// Assert that there are 3 fixtures within the returned object.
		Assert.assertEquals(jsonArray.size(), 3);
	}

	public void assert_fixerId() {
		for (int i = 0; i < jsonArray.size(); i++) {
			JsonObject item = (JsonObject) jsonArray.get(i);
			System.out.println(item.get("fixtureId").getAsString());
			// Assert that each of the 3 fixtures has a fixtureId value.
			Assert.assertNotNull(("fixtureId"));
		}
	}

	public void createnewfixture() throws ClientProtocolException, IOException {
		HttpPost post = new HttpPost("http://localhost:3000/fixture");
		path = "C:\\Users\\rbajaj\\eclipse-workspace\\PokerMockAPI\\src\\test\\resources\\json\\addfixture4.json";
		requestBody = new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
		post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
		post.setHeader(HttpHeaders.ACCEPT, "*/*");
		post.setEntity(new StringEntity(requestBody));
		response = HttpClientBuilder.create().build().execute(post);
		statusCode = response.getStatusLine().getStatusCode();
		// Create and Store a new fixture fixtureid=4
		System.out.println("Log in status code: " + statusCode);
		Assert.assertEquals(statusCode, 200);
		entity = response.getEntity();
		String content = EntityUtils.toString(entity);
		System.out.println("Get contact data: " + content);
	}

	public void get_New_fixture() throws ClientProtocolException, IOException {
		get = new HttpGet("http://localhost:3000/fixture/4");
		response = HttpClientBuilder.create().build().execute(get);
		statusCode = response.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, 200);
		System.out.println("Log in status code: " + statusCode);
		entity = response.getEntity();
		content = EntityUtils.toString(entity);
		// Get the new fixture
		System.out.println("Get contact data: " + content);
	}

	public void assert_teamId() {
		JsonObject obj = new Gson().fromJson(content, JsonObject.class);
		String teamid = obj.getAsJsonObject("footballFullState").getAsJsonArray("teams").get(0).getAsJsonObject()
				.get("teamId").getAsString();
		// Assert, within the teams array, that the first object has a teamId of 'HOME'.
		System.out.println("team id value " + teamid);
		Assert.assertEquals("HOME", teamid);
	}

	
	public void createnewfixtureForPerformance() throws ClientProtocolException, IOException {
		HttpPost post = new HttpPost("http://localhost:3000/fixture");
		path = "C:\\Users\\rbajaj\\eclipse-workspace\\PokerMockAPI\\src\\test\\resources\\json\\addfixture6.json";
		requestBody = new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
		post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
		post.setHeader(HttpHeaders.ACCEPT, "*/*");
		post.setEntity(new StringEntity(requestBody));
		response = HttpClientBuilder.create().build().execute(post);
		statusCode = response.getStatusLine().getStatusCode();
		// Create and Store a new fixture for performance check fixtureid=6
		System.out.println("Log in status code: " + statusCode);
		Assert.assertEquals(statusCode, 200);
	}

	public void retrieve_fixture_as_soon_as_availaible() throws ClientProtocolException, IOException {
		// Bearing the delay in mind, create a new fixture and then retrieve it as soon
		// as it's available
		if (statusCode == 200) {
			get_New_fixture_Performance();
		}
	}

	public void get_New_fixture_Performance() throws ClientProtocolException, IOException {
		get = new HttpGet("http://localhost:3000/fixture/6");
		response = HttpClientBuilder.create().build().execute(get);
		statusCode = response.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, 200);
		System.out.println("Log in status code: " + statusCode);
		entity = response.getEntity();
		content = EntityUtils.toString(entity);
		// Get the new fixture
		System.out.println("Get contact data: " + content);
	}
	
	public void createDeletenewFixture() throws IOException {
		HttpPost post = new HttpPost("http://localhost:3000/fixture");
		path = "C:\\Users\\rbajaj\\eclipse-workspace\\PokerMockAPI\\src\\test\\resources\\json\\addfixture5.json";
		requestBody = new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
		post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
		post.setHeader(HttpHeaders.ACCEPT, "*/*");
		post.setEntity(new StringEntity(requestBody));
		response = HttpClientBuilder.create().build().execute(post);
		statusCode = response.getStatusLine().getStatusCode();
		// Creating new Fixture as fixture id =5 to use for deleting
		System.out.println("Log in status code: " + statusCode);
		entity = response.getEntity();
		content = EntityUtils.toString(entity);
		System.out.println("Get contact data: " + content);
		HttpDelete delete = new HttpDelete("http://localhost:3000/fixture/5");
		response = HttpClientBuilder.create().build().execute(delete);
		statusCode = response.getStatusLine().getStatusCode();
		// Delete a new fixture having a fixture id =5
		Assert.assertEquals(statusCode, 200);
		System.out.println("Log in status code: " + statusCode);
		entity = response.getEntity();
		content = EntityUtils.toString(entity);
		// Delete a new fixture.
		System.out.println("Get contact data: " + content);
	}

	public void assert_Fixture_No_Longer_exist() throws ClientProtocolException, IOException {
		get = new HttpGet("http://localhost:3000/fixture/5");
		response = HttpClientBuilder.create().build().execute(get);
		statusCode = response.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, 404);
		System.out.println("Log in status code: " + statusCode);
		entity = response.getEntity();
		content = EntityUtils.toString(entity);
		// Get the new fixture
		System.out.println("Get contact data: " + content);
		// Assert that the fixture no longer exists
		Assert.assertEquals(content, "Fixture not found");

	}


}
