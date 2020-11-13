package de.hsmw.service.fb.apple.api;

import java.sql.Connection;
import java.sql.SQLException;

public interface IFacebookServiceApple {
	
	public static final Connection connDB = null;
	
	
	void getData(String dbPath);
	
	void connectDatabase(String dbPath) throws SQLException;
	
	void retrieveContactData(String dbPath) throws SQLException;

	
	void retrieveOwnerData(String dbPath) throws SQLException;

	
	void retrieveConversationData(String dbPath) throws SQLException;
	
	void retrieveSharedPlaces(String dbPath) throws SQLException;
	
	
	
}
