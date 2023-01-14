package stepDefProgram;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import java.io.Console;
import java.text.DateFormatSymbols;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Hashtable;
import java.util.Random;

import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
public class CreateGetProgram {
	private final String baseurl = "http://lms-backend-service.herokuapp.com/lms/";
	private static Hashtable<String, Response> responsesTable = new Hashtable<String, Response>();
	private Response response;
	private String postURL = "", getURL = "";
	
	
	@Given("A Post Service (.*)$")
	public void a_post_service(String url) {
	   baseURI = baseurl;
	   postURL = url;
	}
	
	@When("Request POST with sequence number (.*)$")
	public void request_post(String seqNo) {
		String requestStr = GetProgramJson(seqNo);
		response = given().body(requestStr).contentType(ContentType.JSON).log().body(true).post(postURL);
		responsesTable.put(seqNo, response);
	}

	@Then("Check status code {int}")
	public void check_status_code(Integer statusCode) {
		response.then().assertThat().statusCode(statusCode).log().body(true);
	}
	
	@Given("A Get Service (.*)$")
	public void a_get_service(String url) {
	   baseURI = baseurl;
	   getURL = url;
	}
	
	@When("Request GET with sequence number (.*)$")
	public void request_get(String seqNo) {
		String programId = "NO_ID";
		if(responsesTable.containsKey(seqNo)) {
			JSONObject jsonObj = new JSONObject(responsesTable.get(seqNo).body().asString());
			programId = jsonObj.get("programId").toString();
			
		}
		//System.out.println("programId: "+ programId + responsesTable.size());
		response = given().log().params().get(getURL, programId);
	}
	
	private String GetProgramJson(String seqNo) {
		JSONObject program = new JSONObject();
		
		String programName = GenerateProgramName(seqNo);
		program.put("programName", programName);
		program.put("programDescription", programName);
		program.put("programStatus", "Active");
		
		DateTimeFormatter dtf = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
		program.put("creationTime", dtf.format(ZonedDateTime.now()));
		program.put("lastModTime", dtf.format(ZonedDateTime.now()));

        return program.toString();
	}

	private String GenerateProgramName(String seqNo) {
		return String.format("%s%s-%s-%s-%s", 
				ZonedDateTime.now().getDayOfMonth(), 
				new DateFormatSymbols().getShortMonths()[ZonedDateTime.now().getMonth().getValue()-1],
				"NinjaSpark",
				"SDET",
				seqNo);
	}
}
