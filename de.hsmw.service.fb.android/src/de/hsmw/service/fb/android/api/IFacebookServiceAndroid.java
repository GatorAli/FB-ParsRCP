package de.hsmw.service.fb.android.api;

import java.sql.Connection;
import java.sql.SQLException;

public interface IFacebookServiceAndroid {
	
	public static final Connection connDB = null;
	
	
	
	void getData(String threadsdb2);
	
	void getOwnerData(String prefsDB);
	
		
	void connectDatabase(String dbPath) throws SQLException;
	
	void retrieveContactData(String threads_db2) throws SQLException;

	
	void retrieveOwnerData(String prefsDb) throws SQLException;

	
	void retrieveConversationData(String threads_db2) throws SQLException;
	
	void retrieveSharedPlaces(String threads_db2) throws SQLException;
	
	
	
}
