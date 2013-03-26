package com.lib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

public class db {
	public static String banco = "dbqoe"; 
	public static String usuario = "postgres";
	public static String senha = "postgres";

	public static int contaCon = 0;
	public static Connection con = null;
	public static Connection getConnection() throws SQLException {
		if (con == null)
		{			
			try {
				Class.forName("org.postgresql.Driver");
				con = DriverManager.getConnection("jdbc:postgresql://192.168.1.57:5432/"+db.banco, db.usuario, db.senha);
				contaCon++;
				System.out.println("Conta Pg:"+Integer.toString(contaCon));
			} catch (ClassNotFoundException e) {
				throw new SQLException(e.getMessage());
			}
		}
		if (con == null)
		{
			System.out.println("Segunda Tentativa PG");
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
	
	public static Connection getConnectionRel() {
		Connection con1 = null;
		try {
			Class.forName("org.postgresql.Driver");
			try {
				con1 = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/"+db.banco, db.usuario, db.senha);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			try {
				throw new SQLException(e.getMessage());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return con1;
	}	
	
	public static String filtroSql(String pista, ArrayList<String> arrayList)
	{
		String filtro = "";
		if ((arrayList != null) && (!pista.equals("")))
		{
			pista = pista.trim();
			Properties lstpalavras = lista.explode(' ', pista);
			String tipoBanco = "PG";
			String palavra = "";
			for (int p = 0; p <= lstpalavras.size(); p++)
			{
				if (p > 0)
				{
					filtro += " and ";
				}
				palavra = util.toStr((String) lstpalavras.get(p));
				palavra = palavra.toLowerCase();
				for (int c = 0; c < arrayList.size(); c++)
				{
					if (c == 0)
					{
						if (tipoBanco == "PG")
						{
							filtro += " ( lower(to_ascii(" + arrayList.get(c) + ")) like (to_ascii('%"+palavra.replace("'","''")+"%'))";
						}
						
						if (tipoBanco == "MSSQL")
						{
							filtro += " ( lower(" + arrayList.get(c) + ") like ('%"+palavra.replace("'","''")+"%')";
						}
					}
					else
					{
						if (tipoBanco == "PG")
						{
							filtro += " or    lower(to_ascii(" + arrayList.get(c) + ")) like (to_ascii('%"+palavra.replace("'","''")+"%'))";
						}
						if (tipoBanco == "MSSQL")
						{							
							filtro += " or    lower(" + arrayList.get(c) + ") like ('%"+palavra.replace("'","''")+"%')";
						}
					}
				}
				filtro += ") ";
			}
		}
		return filtro;
	}
}
