package de.hsmw.service.fb.android.internal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.inject.Inject;
import de.hsmw.service.fb.android.api.IFacebookServiceAndroid;

public class AndroidMessagesImpl  {
	@Inject
	FacebookServiceAndroidImpl androidService;
	Connection connDB2;
	ResultSet rs = null;
	
/** X **/	String	time;
/** X **/	String	mid;
/** X **/	String	fromId;
/** X **/	String fromName;
/** X **/	String conversationId;
/** X **/	boolean	isFromMe;
		String	senderId;
/** X **/	String	toId;
/** X **/	String	type;
/** X **/	String	message;
	String	mediaPath;
	boolean	isSecret;
	boolean	isDecrypted;
/** X **/	int	duration;
	
	String ownerID;
	String ownerName;
	
	
	ArrayList<String> messagEentry = new ArrayList<String>();
	
	
	public ArrayList<String> getMessagEentry() {
		return messagEentry;
	}

	public void  getMessages(Connection connDB, String db1path, String db2path) {
	
	try {
		retrieveOwnerData(db2path);
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}	
		
		Statement st = null;
		try {
			st = connDB.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		try {
			
			rs = st.executeQuery("Select timestamp_ms, msg_id, thread_key, text, sender, attachments, shares, msg_type, pending_send_media_attachment, admin_text_nickname, admin_text_target_id, generic_admin_message_extensible_data, sticker_id FROM messages");
			System.out.println(rs.getMetaData().getColumnCount());
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
		try {
			
			while (rs.next()){
				
				
				for(int i=1; i<=rs.getMetaData().getColumnCount(); i+=13) {
					time = covertDate(rs.getString(i));
					mid  = rs.getString(i+1);
					conversationId  = rs.getString(i+2);
					message = rs.getString(i+3);
					fromId  = extract_fromperson(rs.getString(i+4),"id");
					fromName = extract_fromperson(rs.getString(i+4), "name");
					isFromMe = ownerID.equals(fromId);
					toId = conversationId.replace(fromId, "");
					type = extractType(rs.getString(i+7),rs.getString(i+6), rs.getString(i+5),rs.getString(i+12),rs.getString(i+11));
					duration = getCallDuration(rs.getInt(i+7), rs.getString(i+11));
					mediaPath = extractMediaPath(rs.getString(i+8));
					String k = rs.getString(i+9);
					String l = rs.getString(i+10);
					String m = rs.getString(i+11);

					
					System.out.println(time);
					System.out.println(message);
					System.out.println("von "+ fromId+" "+fromName + " " +  "an "+ toId);
					System.out.println("von Besitzer " + isFromMe);
					System.out.println(type);
					
					if(rs.getString(i+8) != null) {
						
						System.out.println(mediaPath);
						
					}
					
					
					if(rs.getString(i+5) != null && (rs.getString(i+5).contains("video") || (rs.getString(i+5).contains("audio")))){
						System.out.println("duration " + duration);
					}
					
					System.out.println("");
					
					
					
					
					messagEentry.add(message +" "+  fromId+" "+ fromName);
								
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			
			
			}		
		
		private int getCallDuration(int msg_type,String admin_msg) {
			
			int type = msg_type;
			String duration = "";
			
			if(type == 9 || type == 203) {
				
				duration= admin_msg.substring(admin_msg.indexOf("call_duration")+15, admin_msg.indexOf("}", admin_msg.indexOf("call_duration")));
				System.out.println(type);
				
			}else {
				
				duration = "0";
				
			}
			
			
			return Integer.parseInt(duration);
		}

		private String extractType(String msg_type ,String shares,String atch,  String sticker, String isVideoCall) {
		
			String type = "";
			
			switch(msg_type) {
			
			case "0":
				if(atch != null) {
					
					type = atch.substring(atch.indexOf("type"), atch.indexOf(",",atch.indexOf("type")));
					
					if(type.contains("text")) {
						
						type="TEXT";
						
					}else if(type.contains("image")){
						
						type="IMAGE";
						
					}else if(type.contains("audio")) {
						
						type="AUDIO";
						
					}else if(type.contains("video")) {
						
						type="VIDEO";
						
					}else if(type.contains("application")) {
						
						type ="DOCUMENT" +" " + (atch.substring(atch.indexOf(".", atch.indexOf("filename")), atch.indexOf(",", atch.indexOf("filename")))).toUpperCase();
						
					}	
				
				
				
				}
				
				else if(shares != null) { 
					
					
					type = "LOCATION";
					
					}else if(sticker != null) {
						
						type = "STICKER";
						
			}else { type = "MSG";}
			
				
				
				break;
				
				
			case "9":
				
				if(isVideoCall.contains("missed")) {
					
					
					if(isVideoCall.contains("\"video\":true")) {
						
						type = "VIDEO_CALL_LOST One : One";
					}else { type = "CALL_LOST One : One"; }
					
					
				}else {
				
				
					if(isVideoCall.contains("\"video\":true")) {
						
						type = "VIDEO_CALL One : One";
					}else { type = "CALL One : One"; }
				}
					
					break;
					
					
			case "203":
				if(isVideoCall.contains("\"video\":true")) {
					
					type = "VIDEO_CALL Group";
				}else { type = "CALL Group"; }
			
				break;
				
			}
			
	
			
		return type;
	}

		private String covertDate(String timems) {
			
			long millis = Long.parseLong(timems);
		
			
			Date date = new Date(millis);
			
			
			String test = date.toString();
			
						
		
						
			return test;
		}
		
		private String extract_fromperson(String id, String descriptor) {
			
						
			switch (descriptor){
			
			case "id":

				if(id!=null) {
				
				id = id.substring(id.indexOf("FACEBOOK:", 1)+9, id.indexOf("name")-3);
				}else {
					id =" ";
				} return id;
				
				
				
				
			case "name":	
				
				if(id!=null) {
					
					id = id.substring(id.indexOf("name", 1)+7, id.indexOf("email")-3);
					}else {
						id =" ";
					}
			
					return id;
			}
			
			return id;
			
			
		}
		
		private String extractMediaPath(String path) {
			
			String mediapath="";
			
			if(path != null && path.contains("https")) {
				
				mediapath = path.substring(path.indexOf("https"), path.indexOf("?")-1);
				
			}else if(path != null && path.contains("file")) {
				
				mediapath = path.substring(path.indexOf("file"), path.indexOf(","));
				
			}
			
			
			
			return mediapath;
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
	