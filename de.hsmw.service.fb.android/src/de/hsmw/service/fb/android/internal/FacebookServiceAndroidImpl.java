package de.hsmw.service.fb.android.internal;

import java.sql.*;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.Composite;

import de.hsmw.service.fb.android.api.IFacebookServiceAndroid;

public class FacebookServiceAndroidImpl implements IFacebookServiceAndroid {
	
	
	Connection connDB = null;
	ResultSet rs = null;
	
	private String androidGetOwnerQuery = "SELECT value FROM preferences WHERE key like '/auth/auth_device_based_login_credentials_______________'";
	private String androidGetContatcsQuery = "SELECT user_key, first_name, last_name, username FROM thread_users";
	private String androidGetConversationsQuery = "SELECT thread_key, name, senders, custom_nicknames,last_call_ms FROM threads";
	private String androidGetSharedPlacesQuery = "SELECT shares FROM messages WHERE shares IS NOT NULL";
	private String androidGetOwnerMailQuery = "SELECT value FROM preferences WHERE key='/google_accounts'";
	
	@Override
	public void getData(String threadsdb2) {
		
		
		try {
			connectDatabase(threadsdb2);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		try {
			retrieveContactData(threadsdb2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("retrieve Contact Data Problem");
		}
		
		
		
		try {
			retrieveConversationData(threadsdb2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Retrieve Conversation Data Problem");
		}
		
		try {
			retrieveSharedPlaces(threadsdb2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Retreive Shared places Problem");
		}
		
		
		
	}
	
	
	
	
	// einzelner Aufruf da Besitzerdaten in anderer Datenbank
	@Override
	public void getOwnerData(String prefsDB) {
		
		try {
			connectDatabase(prefsDB);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		try {
			retrieveOwnerData(prefsDB);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
			
	@Override
	public void connectDatabase(String dbPath) throws SQLException {
		// TODO Auto-generated method stub
		
	//	System.out.println(dbPath);
		
		StringBuilder sb = new StringBuilder();
		sb.append("jdbc:sqlite:");
		sb.append(dbPath);
	//	System.out.println(sb.toString());
		
		
		try {
		
			
		Class.forName("org.sqlite.JDBC");
		
		//connDB = DriverManager.getConnection("jdbc:sqlite:G:\\Softwareprojekt\\Facebook\\001Testdaten_Grundlegendes\\ExtraktionGeorgSamsonG4\\Facebook Export\\threads_db2");
		connDB = DriverManager.getConnection(sb.toString());
		
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("SQL Problem");
		
		
	}
	
		try {
			if( !connDB.isClosed()) {
				
				System.out.println("Verbunden");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
		
		

}

	
	@Override
	public void retrieveOwnerData(String prefsDb) throws SQLException {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		Statement st = null;
		try {
			st = connDB.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {

			rs = st.executeQuery(androidGetOwnerQuery);
			String name = rs.getString(1);
					name = (String) name.subSequence(name.indexOf(":")+1, name.indexOf(","));
			
			System.out.println("\n" + "Besitzer" +"\n " + name +"\n");
			
			
			rs = st.executeQuery(androidGetOwnerMailQuery);
		
			System.out.println("E-Mail" + "\n" + rs.getString(1) +"\n");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void retrieveContactData(String threads_db2) throws SQLException {
	
		Statement st = null;
		try {
			st = connDB.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			
			rs = st.executeQuery(androidGetContatcsQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("\n" +"\n" +"Contacts" +"\n");
		
		// Itterate rows of Resultset
		while(rs.next()) {
						
			//Itterate Columns until ColumnCount reached
			for(int i=1; i<=rs.getMetaData().getColumnCount(); i+=4) {
								
				String fbid = rs.getString(i);
				String firstname = rs.getString(i+1);
				String lastname = rs.getString(i+2);
				String username = rs.getString(i+3);
				
				System.out.println(fbid +" "+ firstname +" "+ lastname +" "+ username);
			}
		
		
		
		}
	
	
	}


	@Override
	public void retrieveConversationData(String threads_db2) throws SQLException {
		// TODO Auto-generated method stub
	
		
		
		Statement st = null;
		try {
			st = connDB.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			
			rs = st.executeQuery(androidGetConversationsQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("\n" +"\n" + "Conversations" +"\n");
		
		// Itterate rows of Resultset
		while(rs.next()) {
						
			//Itterate Columns until ColumnCount reached
			for(int i=1; i<=rs.getMetaData().getColumnCount(); i+=5) {
								
				String thread_key = rs.getString(i);
				String name = rs.getString(i+1);
				String senders = rs.getString(i+2);
				String custom_nicknames = rs.getString(i+3);
				String last_call_ms = rs.getString(i+4);
				
				
				System.out.println(thread_key +" "+ name +" "+ senders +" "+ custom_nicknames +" "+last_call_ms);
			}
		
		
		
		
		
	}

	
}


	@Override
	public void retrieveSharedPlaces(String threads_db2) throws SQLException {
		// TODO Auto-generated method stub
		
		
		Statement st = null;
		try {
			st = connDB.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			
			rs = st.executeQuery(androidGetSharedPlacesQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("\n" +"\n" + "Shared Places" +"\n");
		
		
		while(rs.next()) {
			
		String ort = null;
		String coords =null;
		
		ort = rs.getString(1).substring(rs.getString(1).indexOf(","));
		ort = ort.substring(ort.indexOf(":")+1);
		ort = ort.substring(ort.length()-ort.length(), ort.indexOf(","));
		
		coords = rs.getString(1);
		
		if (coords.indexOf("Fq") == -1) {
			coords = "";
			
		}else {
			coords = coords.substring(coords.indexOf("Fq%3D")+5);
			coords = coords.substring(0, coords.indexOf("%3D"));
			coords = coords.replace("%252C", " ");
			coords = coords.replace("%26hl", "");
		}
	//	
		
		System.out.println(ort + " @ " + coords);
		
		
		}
		
		
		
		}

	
	
	
	
	

	
}		
		




	
	
	
	
	
