package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import org.json.JSONArray;
import org.json.JSONObject;

public class Position {
	
	int positionID;
	int positionAuthorID;
	String positionTitle;
	String positionOverview;
	
	//hello
	//have a nice day
	
	public Position() {
		
		
	}
	
	public Position(int positionID, int positionAuthorID, String positionTitle, String positionOverview) {
		this.positionID = positionID;
		this.positionAuthorID = positionAuthorID;
		this.positionTitle = positionTitle;
		this.positionOverview = positionOverview;
		
	}
	
	public JSONArray listPositions() {
		
		
		JSONObject positionJSONObject = null;
		JSONArray positionsJSONArray = new JSONArray();
		
		try {
			
			MSSQLConnection mssqlConnection = new MSSQLConnection();
			Connection connection = mssqlConnection.getConnection();
			Statement stmt = connection.createStatement(
					java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE, java.sql.ResultSet.CONCUR_READ_ONLY);
			
			String positionsQuery = "SELECT * " +
			  		"FROM PortfolioProject.dbo.positions WITH (NOLOCK) ";
				
				java.sql.ResultSet positionsResults = stmt.executeQuery(positionsQuery);
	
				while(positionsResults.next()){
					
					positionJSONObject = new JSONObject();
					
					String positionID = positionsResults.getString("positionID").trim();
					String positionTitle = positionsResults.getString("positionTitle").trim();
					String positionOverview = positionsResults.getString("positionOverview").trim();
					
					positionJSONObject.put("positionID", positionID);
					positionJSONObject.put("positionTitle", positionTitle);
					positionJSONObject.put("positionOverview", positionOverview);
					
					positionsJSONArray.put(positionJSONObject);

				}
				
				System.out.println(positionsJSONArray);

		    
			//Close Connections
			try { if (positionsResults != null) positionsResults.close(); } catch (Exception e) {}; 
	    	try { if (stmt!= null) stmt.close(); } catch (Exception e) {};
	    	try { if (connection != null) connection.close(); } catch (Exception e) {}; 
		
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		}
		
		return positionsJSONArray;
		
	}
	

	
	public String updatePosition() {
		
		String message = "Position Updated!";
		
		try {
			//creating sql server model -- this creates the connection
			MSSQLConnection mssqlConnection = new MSSQLConnection();
			Connection connection = mssqlConnection.getConnection();
			
			String updatePosition = "UPDATE "+mssqlConnection.getDatabase() +".dbo.positions SET " +
					
					//what this says is if ? is empty null it out
					"positionTitle=isNull(NullIf(?,''), positionTitle),"+
					"positionOverview=IsNull(NullIf(?,''),positionOverview) "+
					"WHERE positionID=" +positionID+ "";	
			
			PreparedStatement ps = connection.prepareStatement(updatePosition);
						
			
			ps.setString(1, positionTitle);
			ps.setString(2, positionOverview);
				
			
			//this will update on the string that had been added in
			ps.executeUpdate();
			
			try { if (ps!= null) ps.close(); } catch (Exception e) {};
	    	try { if (connection != null) connection.close(); } catch (Exception e) {}; 
			
		} catch (Exception e) {
		    System.out.println(e.getMessage());
		}
		
		return message;
				
		}
	
	public JSONObject getPosition() {
		
		MSSQLConnection mssqlConnection = new MSSQLConnection();

		String sqlString = "SELECT * " +
		  		"FROM "+mssqlConnection.getDatabase() +".dbo.positions WITH (NOLOCK) " +
				"WHERE positionID = "+positionID+"";
		
		SQLQuery sqlQuery = new SQLQuery();
		sqlQuery.setSqlString(sqlString);
		
		return sqlQuery.getQuery();
	
}

public String addPosition() {

String message = "Position Added!";

try {
	
	MSSQLConnection mssqlConnection = new MSSQLConnection();
	Connection connection = mssqlConnection.getConnection();
	
	
	String addPosition = "INSERT "+mssqlConnection.getDatabase() +".dbo.positions " +
			"(positionTitle, positionOverview) VALUES (?,?)";	
	
	PreparedStatement ps = connection.prepareStatement(addPosition);
	
	
	ps.setString(1, positionTitle);
	ps.setString(2, positionOverview);

	
			
	//this will update on the string that had been added in
	ps.executeUpdate();
	
	try { if (ps!= null) ps.close(); } catch (Exception e) {};
	try { if (connection != null) connection.close(); } catch (Exception e) {}; 
	
} catch (Exception e) {
    System.out.println(e.getMessage());
}

return message;
		
}

public String deletePosition() {
	
	String message = "Position " +positionID +" Deleted!";
	
	try {
		//creating sql server model -- this creates the connection
		MSSQLConnection mssqlConnection = new MSSQLConnection();
		Connection connection = mssqlConnection.getConnection();
		
		//it is going to insert -- all the question marks are the prepared statement
		
		String delete = "DELETE FROM "+mssqlConnection.getDatabase() +".dbo.positions " +
				"WHERE positionID="+positionID;	
		
		PreparedStatement ps = connection.prepareStatement(delete);
		
		ps.executeUpdate();
		
		try { if (ps!= null) ps.close(); } catch (Exception e) {};
    	try { if (connection != null) connection.close(); } catch (Exception e) {}; 
		
	} catch (Exception e) {
	    System.out.println(e.getMessage());
	}
	
	return message;
			
	}

	
	
	//Getters and Setters

	public int getPositionID() {
		return positionID;
	}

	public void setPositionID(int positionID) {
		this.positionID = positionID;
	}

	public int getPositionAuthorID() {
		return positionAuthorID;
	}

	public void setPositionAuthorID(int positionAuthorID) {
		this.positionAuthorID = positionAuthorID;
	}

	public String getPositionTitle() {
		return positionTitle;
	}

	public void setPositionTitle(String positionTitle) {
		this.positionTitle = positionTitle;
	}

	public String getPositionOverview() {
		return positionOverview;
	}

	public void setPositionOverview(String positionOverview) {
		this.positionOverview = positionOverview;
	}
	
	

}