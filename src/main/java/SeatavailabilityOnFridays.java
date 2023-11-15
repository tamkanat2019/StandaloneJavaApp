import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

public class SeatavailabilityOnFridays {

	//public static void main(String args[]) {
	//https://rapidapi.com/IRCTCAPI/api/irctc1/pricing
	
	public static String trainSeatAvailabilityCheck() {
	/*	HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://irctc1.p.rapidapi.com/api/v1/checkSeatAvailability?classType=3A&fromStationCode=SC&quota=GN&toStationCode=PUNE&trainNo=12220&date=2023-12-01"))
				.header("X-RapidAPI-Key", "5bea123d6dmsh1ed7fe7d183cb46p164913jsna26dbe1ec5fe")
				.header("X-RapidAPI-Host", "irctc1.p.rapidapi.com")
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = null;*/
		String message = null;
		String response = "{\"status\":true,\"message\":\"Success\",\"timestamp\":1700028295465,\"data\":[{\"ticket_fare\":1520,\"catering_charge\":20,\"total_fare\":1540,\"date\":\"1-12-2023\",\"current_status\":\"AVAILABLE-0017.\"},{\"ticket_fare\":1520,\"catering_charge\":20,\"total_fare\":1540,\"date\":\"5-12-2023\","
							+ "\"current_status\":\"AVAILABLE-0152.\"}]}";
		JSONObject jsonObject = new JSONObject(response);
		JSONArray availabilityData = jsonObject.getJSONArray("data");
		 for (int i=0; i<availabilityData.length(); i++) {
			 JSONObject last_json = availabilityData.getJSONObject(i);
			 //get date and seatStatus from the json/arrays
			 String current_status = last_json.getString("current_status");
			 String dateStr = last_json.getString("date");
			 //string to date conversion
			 SimpleDateFormat formatter = new SimpleDateFormat("d-M-yyyy");
	            Date date = null;
				try {
					date = formatter.parse(dateStr);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				//get count of seats from current_status
				int status_count=Integer.parseInt(current_status.split("-")[1].replace(".", ""));
				if(date.getDay()==5 && status_count <=20) {
					message = "Please book your tickets, as seats remaining for "+date +" " + "are:"+" "+status_count;
					//System.out.println(date.getDay()+" "+status_count); 
				}
				
			
			 //chk date is friday or not
			 //if friday then get the seat availability count in int and send to WhatsappSender
			 //System.out.println(current_status + " " + dateStr);
		 }
		return message;
			  
			  
		 
		/*
		 * try { response = HttpClient.newHttpClient().send(request,
		 * HttpResponse.BodyHandlers.ofString()); } catch (IOException e) {
		 * e.printStackTrace(); } catch (InterruptedException e) { e.printStackTrace();
		 * } System.out.println(response.body());
		 */
//}
}
}