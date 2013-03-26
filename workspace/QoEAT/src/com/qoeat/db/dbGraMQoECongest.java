package com.qoeat.db;

import java.sql.SQLException;

import com.lib.Propriedades;
import com.lib.lista;
import com.lib.tCombo;
import com.lib.tQuery;
import com.lib.util;

public class dbGraMQoECongest
{
	public Propriedades campo = new Propriedades();	
	
	public static boolean salvar(Propriedades cp)
	{
		boolean retorno = false;
		try 
		{
			tQuery qry = new tQuery();
			// testes e ajustes
/*			if(cp.get("perfid").equals(""))
			{
				cp.put("perfid","null");
			}		*/	
			// operação de salvamento
			if (dbGraMQoECongest.exists(cp) != true)
			{
				qry.add("insert into video (nome, duracao, origem)");
				qry.add("values('" + cp.get("nome")+ "','" + cp.get("duracao")+ "','" + cp.get("origem") + "')");
				qry.executar();
				
				qry.limpar();
				qry.add("select currval('video_id_seq') as id;");
				qry.abrir();
				if (qry.proximo())
				{
					cp.put("id", qry.result.getString("id"));
				}				
			}
			else
			{
				qry.add(" update video ");
				qry.add(" set id   = "+cp.get("id"));
				qry.add(" ,   nome = '"+cp.get("nome")+"' ");
				qry.add(" ,   duracao = '"+cp.get("duracao")+"' ");
				qry.add(" ,   origem = '"+cp.get("origem")+"' ");
				qry.add(" where id = "+cp.get("id"));
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
	
	public static boolean excluir(Propriedades cp)
	{
		boolean retorno = false;
		try 
		{
			tQuery qry = new tQuery();
			// testes e ajustes
			// operação de salvamento
			qry.add("delete from video where id = " + cp.get("id"));
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

			Propriedades usuario = new Propriedades();
			
			// loop para exclusão
			for (int x=1; x  <= arrLista.size();x++)
			{
				usuario.put("id", arrLista.get(x));
				dbGraMQoECongest.excluir(usuario);
			}							
		}				
	}
	
	public static boolean exists(Propriedades cp) 
	{
		boolean retorno = false;
		//System.out.println(cp.getProperty("login"));
		if ( !((cp.getProperty("id") + cp.getProperty("login")).isEmpty()) )
		{			
			try 
			{
				// cria informando a SQL
				tQuery qry = new tQuery();
				qry.add("select id   from video");
				if (util.nstrvazia(cp.getProperty("id")))
				{
					qry.add("where   id = "+cp.get("id"));
				}
				else
				{
					if (util.nstrvazia(cp.getProperty("nome")))
					{
						qry.add("where   nome = '"+cp.get("nome")+"' ");				
					}
					else
					{
						qry.add("where   id = "+cp.get("id"));
					}
				}				
				
				// preenche os valores
				if (qry.abrir())
				{
					retorno = qry.proximo();
				}
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
			qry.add("select	vide.nome" +
				",	codi.id"+
				", 	tran.mode"+
				", 	pltr.id"+
				",	tran.systemload"+
				", 	avg(aval.valor) as media"+
				"from	planotrabalho pltr"+
				", 	transmissao tran"+
				", 	video vide"+
				", 	codificacao codi"+
				",	codifram cofr"+
				", 	avaliacaoframe aval"+
				", 	metrica metr"+
				"where   vide.id = codi.videid"+
				"and     tran.codiid = codi.id"+
				"and	tran.pltrid = pltr.id"+
				"and  	tran.id = aval.tranid"+
				"and  	metr.id = aval.metrid"+
				"and	cofr.framid = aval.framid"+
				"and 	cofr.codiid = codi.id"+
				"and     pltr.id = "+ 
				"and 	metr.id = "+
				"and	codi.id = "+
				"group by vide.nome"+
				",	codi.id"+
				", 	tran.mode"+
				", 	pltr.id"+
				",	tran.systemload"+
				"order by tran.mode, tran.systemload");

			qry.abrir();

			if (qry.proximo())
			{
				System.out.println(cp);
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
	
	public String primeiroNome()
	{
		String nomeCompleto = (String) this.campo.get("nome");
		
		Propriedades nomes = lista.explode(' ', nomeCompleto);
		return (String) nomes.get(1);
	}

	public static String getIdPorNome(String nome)
	{
		String retorno = "";
		try 
		{
			tQuery qry = new tQuery();
			qry.add("select  id ");						
			qry.add("from    video ");
			qry.add("where 	 lower(nome) = lower('" + nome+"') ");		
			qry.abrir();

			if (qry.proximo())
			{
				retorno = qry.get("id");
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return retorno;
	}
	
	public static String comboComNenhum(String valorinicial, String nome) throws SQLException
	{
		tCombo cmb = new tCombo();				
		cmb.sql = 	" select 	null as id" +
					" , 		'Nenhum' as nome" +
					" ,			0 as ordem" +
					" union all" +
					" select 	id" +
					" , 		nome" +
					" , 		1 as ordem" +
					" from 	video" +
					" order by ordem, nome";
		cmb.objetoNome = nome;
		cmb.indexTabulacao = "";
		cmb.valorInicial = valorinicial;
		cmb.constroi();
		return cmb.conteudo;
	}	

	public static String listaComNenhum(String valorinicial, String nome) throws SQLException
	{
		tCombo cmb = new tCombo();				
		cmb.sql = 	" select 	null as id" +
					" , 		'Nenhum' as nome" +
					" ,			0 as ordem" +
					" union all" +
					" select 	id" +
					" , 		nome" +
					" , 		1 as ordem" +
					" from 	video" +
					" order by ordem, nome";
		cmb.objetoNome = nome;
		cmb.indexTabulacao = "";
		cmb.valorInicial = valorinicial;
		cmb.multiplasLinhas = true;
		cmb.qtdLinhas = "5";
		cmb.constroi();
		return cmb.conteudo;
	}	

	public static String combo(String valorinicial, String nome) throws SQLException
	{
		tCombo cmb = new tCombo();				
		cmb.sql = 	" select 	id" +
						" , 		nome" +
						" from 	video" +
						" order by nome";
		cmb.objetoNome = nome;
		cmb.indexTabulacao = "";
		cmb.valorInicial = valorinicial;
		cmb.constroi();
		return cmb.conteudo;
	}		
	
	public static String combo(String valorinicial, String nome, String acao) throws SQLException
	{
		tCombo cmb = new tCombo();				
		cmb.sql = 	" select 	id" +
						" , 		nome" +
						" from 	video" +
						" order by nome";
		cmb.objetoNome = nome;
		cmb.indexTabulacao = "";
		cmb.valorInicial = valorinicial;
		cmb.funcaoAoAlterar = true;
		cmb.acao = acao;
		cmb.constroi();
		return cmb.conteudo;
	}	

}

