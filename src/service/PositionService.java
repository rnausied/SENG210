package service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import model.Position;

public class PositionService {
	
public static JSONArray listPositions() {
		
		JSONArray positions = new JSONArray();
		
		Position position= new Position();
		positions = position.listPositions();
		
		return positions;

}

public static JSONObject updatePosition(JSONObject inputParms) {
	
	String message = "";
	
	JSONObject result = new JSONObject();
	
	try {
		
		int positionID = inputParms.getInt("positionID");
		String positionTitle = inputParms.getString("positionTitle");
		String positionOverview = inputParms.getString("positionOverview");
		
		Position position = new Position();
		position.setPositionID(positionID);
		position.setPositionTitle(positionTitle);
		position.setPositionOverview(positionOverview);
		
	
	message = position.updatePosition();
	
	
		result.put("message", message);
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return result;
	
}

public static JSONObject getPosition(int id) {
	
	JSONObject positionJO = new JSONObject();
	
	Position position = new Position();
	position.setPositionID(id);
	
	positionJO = position.getPosition();
	
	return positionJO;
}

public static JSONObject addPosition(JSONObject inputParms) {
	
	String message = "";
	
	JSONObject result = new JSONObject();
	
	try {
		
		
		String positionTitle = inputParms.getString("positionTitle");
		String positionOverview = inputParms.getString("positionOverview");
		

	
		Position position = new Position();
		position.setPositionTitle(positionTitle);
		position.setPositionOverview(positionOverview);
		
	
	message = position.addPosition();
	
	
		result.put("message", message);
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return result;
	
	
}

public static JSONObject deletePosition(JSONObject inputParms) {
	
	String message = "";
	
	JSONObject result = new JSONObject();
	
	try {
		
			
		int positionID = inputParms.getInt("positionID");
			
		Position position = new Position();
		position.setPositionID(positionID);
		
		message = position.deletePosition();
		
		result.put("message", message);
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return result;
	
	
	
}



}

