package de.hsmw.service.fb.android.internal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;

import javax.print.attribute.HashAttributeSet;

import de.hsmw.service.fb.android.api.IFacebookServiceAndroid;

public class AndroidContactImpl{

	Connection connDB2;
	ResultSet rs = null;
	ResultSet rs2 = null;
	
	String id;	
	String name;
	String localName;
	String nickname;
	String phoneNumber;
	String about;
	String profilePhotoPath;
	String isOwner;
	long createdAt;
	
	String ownerName = "";
	String ownerID = "";
	HashMap<String,String> nicknames = new HashMap<>();
	
	
	public void getContacts(Connection connDB, String db1path, String db2path) throws SQLException  {
		
		try {
			retrieveOwnerData(db2path);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Statement st = null;
		Statement st2 = null;
		try {
			st = connDB.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			st2 =connDB.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		try {
			
			rs = st.executeQuery("SELECT user_key, first_name, last_name, username, name, profile_pic_square FROM thread_users ");
			rs2 = st2.executeQuery("SELECT admin_text_nickname, admin_text_target_id FROM messages WHERE admin_text_nickname IS NOT NULL");
			System.out.println(rs.getMetaData().getColumnCount());
			
			getNickname();
			
			while(rs.next()) {
				
				for(int i=1; i<=rs.getMetaData().getColumnCount(); i+=6) {
					
					
					id				 = parseID(rs.getString(i));
					name 			 = rs.getString(i+3);
					localName 		 = rs.getString(i+4);
					profilePhotoPath = rs.getString(i+5);
					nickname		 = nicknames.get(id);
					
					
					System.out.println(id);
					System.out.println(name);
					System.out.println(localName);
					System.out.println(nickname);
					System.out.println(profilePhotoPath);
					System.out.println("\n");
					
					
					
					
				}
			
				
				
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	//	System.out.println(nicknames.size());
	//	System.out.println(nicknames.values());
		
	}
	
	
	public HashMap<String,String> getNickname() throws SQLException {

						
		while(rs2.next()) {
			
			nicknames.put(rs2.getString(2), rs2.getString(1));
			
		}
		
		return nicknames;
		
	}
	
	
	
	public String parseID(String id) {
		
		id = id.substring(id.indexOf(":")+1, id.length());
		
		return id;
	}
	
	public void retrieveOwnerData(String prefsDb) throws SQLException {
		
		StringBuilder sb = new StringBuilder();
		sb.append("jdbc:sqlite:");
		sb.append(prefsDb);

		try {
		
			
		Class.forName("org.sqlite.JDBC");
		
		
		connDB2 = DriverManager.getConnection(sb.toString());
		
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}  catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("SQL Problem");
		
		
	}
	
		try {
			if( !connDB2.isClosed()) {
				
				System.out.println("Verbunden");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
	// TODO Auto-generated method stub
	ResultSet rs2 = null;
	Statement st = null;
	try {
		st = connDB2.createStatement();
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	try {

		rs = st.executeQuery("SELECT key, value FROM preferences WHERE key like '/auth/auth_device_based_login_credentials_______________'");
		String name = rs.getString(2);
				name = (String) name.subSequence(name.indexOf(":")+1, name.indexOf(","));
		
		String id = rs.getString(1).substring(rs.getString(1).indexOf("credentials")+11);
		System.out.println("\n" + "Besitzer" +"\n " + name +"\n");
	
		ownerName = name;
		ownerID = id;
		
		System.out.println(ownerName +" , "+ownerID);
		

	
} catch (SQLException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}

}

	
	
	
	
}