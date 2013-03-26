package com.acesso.db;

import java.sql.SQLException;
import java.util.Properties;

import com.lib.Propriedades;
import com.lib.tQuery;

public class dbPeop {
	Properties campo = new Properties();
	
	public static boolean salvar(Properties cp)
	{
		boolean retorno = false;
		try 
		{
			tQuery qry = new tQuery();
			// testes e ajustes
			// operação de salvamento
			if (dbPeop.exists(cp) != true)
			{
				qry.add("insert into perfilopcao (perfid, opcoid)");
				qry.add("values("+cp.get("perfid")+", "+cp.get("opcoid")+");");
				qry.executar();
				/*
				qry.limpar();
				qry.add("select currval('perfis_id_seq') as id;");
				qry.abrir();
				if (qry.proximo())
				{
					cp.put("id", qry.result.getString("id"));
				}	
				*/
			}
			else
			{
				qry.add(" update perfilopcao" +
						" set    perfid = "+ cp.get("perfid") +
						" ,	     opcoid = "+ cp.get("opcoid") +
						" where  perfid = "+ cp.get("perfid") +
						" and    opcoid = "+ cp.get("opcoid") );
				qry.executar();							
			}	
			retorno = true;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			retorno = false;
		}
		return retorno;
	}
	
	public static boolean excluir(Properties cp)
	{
		boolean retorno = false;
		try 
		{
			tQuery qry = new tQuery();
			// testes e ajustes
			// operação de salvamento
			qry.add(" delete from perfilopcao" +
					" where perfid = " + cp.get("perfid") + 
					" and   opcoid = " + cp.get("opcoid"));
			qry.executar();
			retorno = true;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
			retorno = false;
		}
		return retorno;
	}
	
	public static boolean exists(Properties cp) 
	{
		boolean retorno = false;
		if (!cp.getProperty("id").equals(""))
		{
			try 
			{
				// cria informando a SQL
				tQuery qry = new tQuery(" select id   " +
										" from  perfilopcao" +
										" where perfid = " + cp.get("perfid") + 
										" and   opcoid = " + cp.get("opcoid"));
									
				// preenche os valores
				qry.abrir();
				retorno = qry.proximo();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
				retorno = false;
			}
		}
		return retorno;
	}
	
	public static boolean recuperar(Propriedades cp)
	{
		boolean retorno = false;
		try 
		{
			tQuery qry = new tQuery();
			qry.add(" select  * "+	
					" from  perfilopcao"+
					" where perfid = " + cp.get("perfid") + 
					" and   opcoid = " + cp.get("opcoid"));	
			qry.abrir();

			if (qry.proximo())
			{
				qry.putLinha(cp);
			}
			retorno = true;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
			retorno = false;
		}
		return retorno;
	}
}
