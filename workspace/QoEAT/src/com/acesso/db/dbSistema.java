package com.acesso.db;

import java.sql.SQLException;

import com.lib.tQuery;

public class dbSistema 
{
	public static int tempoTermino = 0;
	public static String mensagem = "";
	public static String getDataHoje() throws SQLException
	{
		String datahoje = "";
		tQuery qry = new tQuery("select  to_char(current_timestamp, 'DD/MM/YYYY') as datahoje");
		if (qry.abrir())
		{
			if (qry.proximo())
			{
				datahoje = qry.result.getString("datahoje");
			}				
		}
		return datahoje;
	}		
}
