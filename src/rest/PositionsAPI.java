package rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import service.PositionService;

@Path("/positions")
public class PositionsAPI {
	
	@GET
	@Produces("application/json")
	@Consumes("application/json")
	
	public String listPositions() {
	
		JSONArray positions = PositionService.listPositions();
		
		return positions.toString();
	}
	
	@PUT
	@Produces("application/json")
	@Consumes("application/json")
	
	public String updatePosition(String inputParms) {
		
		
		try {
			JSONObject result = PositionService.updatePosition(new JSONObject(inputParms));
			
			return result.toString();
		
		} catch (JSONException e) {
			e.printStackTrace();
		}
				
		return "updatePosition failed";
		

}
	
	@GET
	@Path("/{id}/")
	@Produces("application/json")
	@Consumes("application/json")
	
	public String getPosition(@PathParam("id") Integer id) {
		
		JSONObject positions = PositionService.getPosition(id);
			
		return positions.toString();
}
	
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	
	public String addPosition(String inputParms) {
		
	
		try {
			JSONObject result = PositionService.addPosition(new JSONObject(inputParms));
			
			return result.toString();
			
		} catch (JSONException e) {
	
			e.printStackTrace();
			
			return "addPositionAPI failed";
		}
				

	}	
	
	@DELETE
	@Produces("application/json")
	@Consumes("application/json")
	
	public String deletePosition(String inputParms) {
		
	
		try {
			JSONObject result = PositionService.deletePosition(new JSONObject(inputParms));
			
			return result.toString();
			
		} catch (JSONException e) {
	
			e.printStackTrace();
			
			return "deletePositionAPI failed";
		}
}
}