package stepDefProgram;

import org.junit.Before;

import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

public class GetAllProgram {

	private final String baseurl = "http://lms-backend-service.herokuapp.com/lms/";
	private Response response;
	private String getURL = "";
	/*private Scenario scenario;
	
	@Before
	public void before(Scenario scenario)
	{
		this.scenario=scenario;
	}*/
	@Given("A Service with (.*)$")
	public void a_service_with_all_programs(String url) {
	   baseURI = baseurl;
	   getURL = url;
	}

	@When("Get request is made")
	public void get_request_is_made() {
	    response = given().get(getURL);
	}

	@Then("Validate {int}")
	public void validate(Integer status) {
	    response.then().assertThat().statusCode(status);
	}

}
