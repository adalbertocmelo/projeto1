package com.lib;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class tQuery {
	public String tipo = "PG";
	public String sql = "";
	Connection con = null; 
	public PreparedStatement stmt;
	String preSqlConfig = "SET DATESTYLE TO SQL; SET DATESTYLE TO European; ";
	public ResultSet result;
	public int qtdColunas;
	public ResultSetMetaData rsmd;
	
	public tQuery () throws SQLException
	{
		this.setTipo("");
	}
	
	public tQuery (String aSql) throws SQLException
	{
		this.sql = aSql;
	}	

	public void setTipo(String vtipo) throws SQLException
	{
		if (vtipo.equals(""))
		{
			vtipo = "PG";
		}
		this.tipo = vtipo;
	}
	
	public void conectar() throws SQLException
	{
		if (this.tipo.equals("PG"))
		{
			this.con = db.getConnection(); 
		}
		if (this.tipo.equals("MSSQL"))
		{
			this.con = dbMSSQL.getConnection();
		}
	}
	/*
	public void desconectar() throws SQLException
	{
		if (this.tipo.equals("PG"))
		{
			this.con.close();
			db.contaCon--;		
			System.out.println("Conta Pg:"+Integer.toString(db.contaCon));
		}
		if (this.tipo.equals("MSSQL"))
		{
			this.con.close();
			db.contaConMSSQL--;		
			System.out.println("Conta MSSQL:"+Integer.toString(db.contaConMSSQL));
		}
		//this.con = null;
	}
	*/
	public boolean abrir(){
		boolean retorno = false;
		try {
			this.conectar();
			this.stmt = con.prepareStatement(this.sql);
			this.result = this.stmt.executeQuery();
			this.rsmd = this.result.getMetaData();
		    this.qtdColunas = rsmd.getColumnCount();
		    //this.desconectar();
		    retorno = true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(this.sql);
		}		
		return retorno;
	}
	
	public boolean executar(){
		boolean retorno = false;
		try {
			this.conectar();
			this.stmt = this.con.prepareStatement(this.sql);
			this.stmt.execute();
			//this.desconectar();
			retorno = true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(this.sql);
			retorno = false;
		}
		return retorno;
	}
	
	public void add(String aStr){
		this.sql = this.sql.concat(" "+aStr);
	}

	public void limpar(){
		this.sql = "";
	}
	
	public void fechar() throws SQLException{
		this.stmt.close();
	}

	public boolean proximo() throws SQLException{
		return this.result.next();
	}

	public boolean ultimo() throws SQLException{
		return this.result.last();
	}
	
	public String get(String campo) throws SQLException
	{
		if (this.result.getString(campo) == null)
		{
			return ""; 
		}
		else
		{
			if (tipo.equals("MSSQL"))
			{
				//System.out.println(campo);
			}
			return this.result.getString(campo);
		}
	}
	
	public void putLinha(Propriedades cp) throws SQLException
	{
		for (int x = 1; x <= qtdColunas; x++)
		{
			//System.out.println("Key:"+this.rsmd.getColumnName(x)+";Value"+this.result.getString(this.rsmd.getColumnName(x))+";");
			cp.put(this.rsmd.getColumnName(x), this.get(rsmd.getColumnName(x)).trim());
		}		
	}
}
