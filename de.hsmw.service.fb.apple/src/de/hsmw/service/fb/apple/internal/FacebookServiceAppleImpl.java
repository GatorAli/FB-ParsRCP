package de.hsmw.service.fb.apple.internal;

import java.sql.*;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.swt.widgets.Composite;

import de.hsmw.service.fb.apple.api.IFacebookServiceApple;


public class FacebookServiceAppleImpl implements IFacebookServiceApple {
	
	Connection connDB = null;
	ResultSet rs = null;
	
	private String appleGetOwnerQuery = "SELECT thread_name FROM _self_thread_name";
	private String appleGetContatcsQuery = "SELECT contact_id, name, first_name FROM simple_contact";
	private String appleGetConversationsQuery = "SELECT thread_key, thread_name, nullstate_description_text1 FROM threads";
	private String appleGetSharedPlacesQuery = "SELECT timestamp_ms, default_cta_title, title_text FROM attachments WHERE default_cta_type = \"xma_live_location_sharing\"";
	private String appleGetOwnerMailQuery = "SELECT * FROM self_profile";
	
	
	
	@Override
	public void getData(String dbPath) {
		
		try {
			connectDatabase(dbPath);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			retrieveOwnerData(dbPath);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			retrieveContactData(dbPath);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			retrieveConversationData(dbPath);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			retrieveSharedPlaces(dbPath);
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
	public void retrieveOwnerData(String dbPath) throws SQLException {
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

			System.out.println("\n" + "Besitzer Daten");
			
			rs = st.executeQuery(appleGetOwnerQuery );
			
			for(int i=1; i<=rs.getMetaData().getColumnCount();i++){
				System.out.println(rs.getString(i));
					
				
			}
			
			
			rs = st.executeQuery(appleGetOwnerMailQuery );
			for(int i=1; i<=rs.getMetaData().getColumnCount();i++){
				System.out.println(rs.getString(i));
					
				
			}
			
			
		
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void retrieveContactData(String dbPath) throws SQLException {
	
		Statement st = null;
		try {
			st = connDB.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			
			rs = st.executeQuery(appleGetContatcsQuery );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("\n" +"\n" +"Contacts" +"\n");
		
		// Itterate rows of Resultset
		while(rs.next()) {
						
			//Itterate Columns until ColumnCount reached
			for(int i=1; i<=rs.getMetaData().getColumnCount(); i+=3) {
								
				String fbid = rs.getString(i);
				String firstname = rs.getString(i+1);
				String lastname = rs.getString(i+2);
				
				
				System.out.println(fbid +" "+ firstname +" "+ lastname);
		
		
			}
		}
	
	
	}


	@Override
	public void retrieveConversationData(String dbPath) throws SQLException {
		// TODO Auto-generated method stub
	
		
		
		Statement st = null;
		try {
			st = connDB.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			
			rs = st.executeQuery(appleGetConversationsQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("\n" +"\n" + "Conversations" +"\n");
		
		// Itterate rows of Resultset
		while(rs.next()) {
						
			//Itterate Columns until ColumnCount reached
			for(int i=1; i<=rs.getMetaData().getColumnCount(); i+=3) {
								
				String thread_key = rs.getString(i);
				String threadname = rs.getString(i+1);
				String senders = rs.getString(i+2);
				
				
				
				System.out.println(thread_key +" "+ threadname +" "+ senders);
			}
		
		
		
		
		
	}

	
}


	@Override
	public void retrieveSharedPlaces(String dbPath) throws SQLException {
		// TODO Auto-generated method stub
		
		
		Statement st = null;
		try {
			st = connDB.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			
			rs = st.executeQuery(appleGetSharedPlacesQuery);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("\n" +"\n" + "Shared Places" +"\n");
		
		
		while(rs.next()) {
			
		for(int i=1; i<rs.getMetaData().getColumnCount();i+=3) {
			
			String timestamp = rs.getString(i);
			String state = rs.getString(i+1);
			String text = rs.getString(i+2);
			
			
			System.out.println(timestamp + " " + state + " " + text);
		}
		
		
		}
		
		
		
		}

	
	
	
	
	

	
}		
		




	
	
	
	
	
