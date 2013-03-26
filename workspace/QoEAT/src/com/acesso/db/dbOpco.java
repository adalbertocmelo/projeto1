package com.acesso.db;

import java.sql.SQLException;

import com.lib.Propriedades;
import com.lib.lista;
import com.lib.tQuery;
import com.lib.util;


public class dbOpco 
{
	Propriedades campo = new Propriedades();
	
	public static boolean salvar(Propriedades cp)
	{
		boolean retorno = false;
		try 
		{
			tQuery qry = new tQuery();
			// testes e ajustes
			dbOpco.listarAncestrais(cp);
			if(cp.get("opcoidpai").equals(""))
			{
				cp.put("opcoidpai","null");
			}

			// operação de salvamento
			if (dbOpco.exists(cp) != true)
			{
				qry.add(" insert into opcao (opcoidpai, nome, link, ordem, imagem, hierarquico, hierarquia, ancestrais, descricao, topcid)");
				qry.add(" values ("+cp.get("opcoidpai")+", '"+cp.get("nome")+"', '"+cp.get("link")+"', "+cp.get("ordem")+", '"+cp.get("imagem")+"', '"+cp.get("hierarquico")+"', '"+cp.get("hierarquia")+"', '"+cp.get("ancestrais")+"', '"+cp.get("descricao")+"', "+cp.get("topcid")+");");
				qry.executar();
				
				qry.limpar();
				qry.add("select currval('opcoao_id_seq') as id;");
				qry.abrir();
				if (qry.proximo())
				{
					cp.put("id", qry.result.getString("id"));
				}				
			}
			else
			{
				qry.add(" update opcao ");
				qry.add(" set opcoidpai   = "+cp.get("opcoidpai"));
				qry.add(" ,   nome = '"+cp.get("nome")+"'  ");								
				qry.add(" ,   link = '"+cp.get("link")+"'  ");		
				qry.add(" ,	  ordem   = "+cp.get("ordem"));
				qry.add(" ,	  imagem   = '"+cp.get("imagem")+"' ");						
				qry.add(" ,   hierarquico = '"+cp.get("hierarquico")+"'  ");							
				qry.add(" ,   hierarquia = '"+cp.get("hierarquia")+"'  ");							
				qry.add(" ,   ancestrais = '"+cp.get("ancestrais")+"'  ");						
				qry.add(" ,   descricao = '"+cp.get("descricao")+"'  ");			
				qry.add(" ,   topcid = "+cp.get("topcid")+"  ");			
				qry.add(" where  id = "+cp.get("id"));
				qry.executar();							
			}
			//dbOpco.atualizarCodigoHierarquico(cp.getProperty("hierarquico"));
			retorno = true;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			retorno = false;
		}
		return retorno;
	}

	public static boolean  excluir(Propriedades cp)
	{
		boolean retorno = false;
		try 
		{
			tQuery qry = new tQuery();
			// testes e ajustes
			// operação de salvamento
			qry.add("delete from opcao where id = " + cp.get("id"));
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
	
	public static void excluirLista(String listaId) 
	{
		if (!listaId.equals(""))
		{
			Propriedades arrLista = lista.explode(',',listaId); 

			Propriedades opcao = new Propriedades();
			
			// loop para exclusão
			for (int x=1; x  <= arrLista.size();x++)
			{
				opcao.put("id", arrLista.get(x));
				dbOpco.excluir(opcao);
			}							
		}				
	}
	
	public static boolean exists(Propriedades cp) 
	{
		boolean retorno = false;
		if (!cp.get("id").equals(""))
		{
			try 
			{
				// cria informando a SQL
				tQuery qry = new tQuery("select id from opcao where id = " + cp.get("id"));
				
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
			qry.add("select  * ");						
			qry.add("from    opcao ");
			qry.add("where id = " + cp.get("id"));		
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
	
	public static void listarAncestrais (Propriedades cp) throws SQLException
	{
		cp.put("ancestrais", "");
		cp.put("hierarquico", Integer.parseInt("0"+cp.getProperty("id")) + 1000);
		cp.put("hierarquia", Integer.parseInt("0"+cp.getProperty("ordem")) + 100);			
		
		Propriedades pai = new Propriedades();
		
		pai.put("opcoidpai", cp.get("opcoidpai"));
		while( !util.toStr(pai.getProperty("opcoidpai")).equals("") )
		{
			pai = dbOpco.achaPai(pai.getProperty("opcoidpai"));
			//$this->listadeascendentes[$x] = $pai; 
			cp.put("ancestrais", "-" + pai.get("nome") + cp.get("ancestrais"));
			cp.put("hierarquico", String.valueOf(Integer.parseInt("0"+pai.getProperty("id")) + 1000) + cp.get("hierarquico"));
			if (util.toStr(pai.getProperty("ordem")).equals(""))
			{
				cp.put("hierarquia", cp.get("hierarquia"));
			}
			else
			{
				cp.put("hierarquia", (Integer.parseInt("0"+pai.getProperty("ordem")) + 100) + "." + cp.get("hierarquia"));				
			}
		}	
	}
	
	public static Propriedades achaPai(String id) throws SQLException
	{
		Propriedades pai = new Propriedades();
		
		tQuery qry = new tQuery();
		qry.add("select  id       ");
		qry.add(",       opcoidpai   ");
		qry.add(",       nome         ");
		qry.add(",       ordem        ");
		qry.add("from    opcao            ");
		qry.add("where   id = "+id);
		if (qry.abrir())
		{
			if (!qry.proximo())
			{
				//mensagem de registro não encontrado.
				pai.put("id","0");
			}
			else
			{
				// vai para o único registro
				qry.putLinha(pai);					
			}
		}
		return pai;
	}		
	
	public static void atualizarCodigoHierarquico(String codigoHierarquico) throws SQLException
	{
		Propriedades cp = new Propriedades();
		tQuery qry = new tQuery();
		qry.add("select  id" +
				",       opcoidpai" +
				",       nome " +
				"from    opcao " +
				"where   hierarquico like '"+codigoHierarquico+"%'");
		if (qry.abrir())
		{
			while (qry.proximo())
			{
				cp.put("id", qry.result.getString("id"));
				dbOpco.recuperar(cp);
				dbOpco.salvar(cp);
		
			}
		}	
	}

}
