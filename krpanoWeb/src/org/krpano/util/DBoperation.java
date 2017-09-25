package org.krpano.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import java.sql.ResultSetMetaData;

public class DBoperation {

	public static Connection connection = null;
	public static Statement statement = null;
	public static ResultSet resultSet = null;

	public static boolean connectDB() {
		try {
			String driverName = "com.mysql.jdbc.Driver";
			Class.forName(driverName).newInstance();
			String url = "jdbc:mysql://localhost:3306/krpano";
			connection = DriverManager.getConnection(url, "root", "lupan4083");
			System.out.println("the connection is finished");
		} catch (Exception e) {
			System.out.println("SQLException" + e.getMessage());
			return false;
		}
		return true;
	}

	public static boolean disconnectDB() {
		try {
			if (connection != null) {
				connection.close();
				connection = null;
			}
			if (statement != null) {
				statement.close();
				statement = null;
			}
			if (resultSet != null) {
				resultSet.close();
				resultSet = null;
			}
		} catch (Exception e) {
		}
		return true;
	}

	/**
	 * 执行sql查询语句
	 * */
	public static Vector<Vector<String>> execSearch(String sql) {
		ResultSetMetaData data;
		int columnNum = 0;
		Vector<Vector<String>> result = new Vector<Vector<String>>();

		try {
			statement = connection
					.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
			resultSet = statement.executeQuery(sql);

			data = resultSet.getMetaData();
			columnNum = data.getColumnCount();
			while (resultSet.next()) {
				Vector<String> vector = new Vector<String>();
				for (int i = 0; i < columnNum; i++) {
					vector.add(resultSet.getString(i + 1));
				}
				result.add(vector);
			}
			if (statement != null) {
				statement.close();
				statement = null;
			}
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * 执行sql语句
	 * */
	public static boolean execsql(String sql) {
		try {
			statement = connection.createStatement();
			statement.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return false;
		}
		return true;

	}
}
