package com.lib.storage.type;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.lib.storage.Storage;
import com.lib.storage.util.PropertiesUtil;

public class SQLiteStorage extends Storage {

	String sqliteDBpath = "filestore.db";

	
	@Override
	protected void loadProperties(PropertiesUtil properties) {
		this.sqliteDBpath = properties.getProperty("storage.sqllite.dbpath");
		
	}
	
	public void instantiate() {
		String createTable = "CREATE TABLE IF NOT EXISTS Filestore ( "+
				"file_id INTEGER  NOT NULL PRIMARY KEY AUTOINCREMENT, "+
				"file_name TEXT NOT NULL UNIQUE,"+
				"data blob  NOT NULL,"+
				"crdt_dt time,"+
				"crtd_by text,"+
				"updt_dt time,"+
				"updt_by text "+

			");";
		try (Connection conn = connect(); Statement stmt = conn.createStatement()) {

			stmt.execute(createTable);
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void saveDocument(String data, String fileName) {
		String inserStmt = "insert or replace into  filestore(file_name,data,crdt_dt,crtd_by) values(?,?,?,?)";

		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(inserStmt)) {

			// set parameters
			pstmt.setString(1, fileName);
			pstmt.setString(2, data);
			pstmt.setDate(3, new Date(System.currentTimeMillis()));
			pstmt.setString(4, "admin");

			pstmt.executeUpdate();
			System.out.println("Stored the file in the BLOB column.");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public String openDocument(String fileName) {

		String sql = "select data from filestore where file_name = ?";
		ResultSet rs = null;
		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, fileName);
			rs = pstmt.executeQuery();

			String data = null;
			
			if(rs.next()) {
				data = rs.getString("data");
			}

			return data;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return null;
	}

	public void renameDocument(String name, String replacementName) {
		String inserStmt = "update  filestore set file_name = ?,updt_dt = ?,updt_by = ? where file_name = ?";

		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(inserStmt)) {

			pstmt.setString(1, replacementName);
			pstmt.setDate(2, new Date(System.currentTimeMillis()));
			pstmt.setString(3, "admin");
			pstmt.setString(4, name);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void deleteDocument(String fileName) {
		String inserStmt = "delete from  filestore where file_name = ?";

		try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(inserStmt)) {

			pstmt.setString(1, fileName);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private Connection connect() {
		String url = "jdbc:sqlite:"+sqliteDBpath ;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	

	

}
