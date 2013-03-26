package com.lib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbMSSQL {
	public static int contaCon = 0;
	public static Connection con = null;
	public static Connection getConnection() throws SQLException {		
		if (con == null)
		{
			try {
				Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
//				con = DriverManager.getConnection("jdbc:microsoft:sqlserver://sisca.cacomunicacao.com.br:1433;DatabaseName=ca", "sa", "CA");
				con = DriverManager.getConnection("jdbc:microsoft:sqlserver://127.0.0.1:1433;DatabaseName=nomedia", "sa", "lagosta");
				contaCon++;		
				System.out.println("Conta MSSQL:"+Integer.toString(contaCon));
			} catch (ClassNotFoundException e) {
				throw new SQLException(e.getMessage());
			}
		}
		if (con == null)
		{			
			System.out.println("Segunda Tentativa MSSQL");
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			getConnection();
		}
		return con;
	}
}
