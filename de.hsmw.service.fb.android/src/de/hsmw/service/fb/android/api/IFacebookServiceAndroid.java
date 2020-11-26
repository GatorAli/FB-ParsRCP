package de.hsmw.service.fb.android.api;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IFacebookServiceAndroid {
	
	
	
	Connection connectDatabase(String dbPath) throws SQLException;
	
	
	void getData(String threadsdb2, String prefsDb);
	
	void getOwnerData(String prefsDB);
	
		
	void retrieveContactData(String threads_db2) throws SQLException;

	
	void retrieveOwnerData(String prefsDb) throws SQLException;

	
	void retrieveConversationData(String threads_db2) throws SQLException;
	
	void retrieveSharedPlaces(String threads_db2) throws SQLException;
	
	
	public ArrayList retrieveMessages(Connection connDB, String db1path, String db2path) throws SQLException;
	
	public void retrieveContacts(Connection connDB, String db1path, String db2path) throws SQLException;
	
	
	
}
