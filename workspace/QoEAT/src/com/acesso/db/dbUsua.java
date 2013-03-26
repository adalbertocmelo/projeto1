package com.acesso.db;

import java.sql.SQLException;

import com.lib.Propriedades;
import com.lib.cripto;
import com.lib.lista;
import com.lib.tCombo;
import com.lib.tQuery;
import com.lib.util;

public class dbUsua 
{
	public Propriedades campo = new Propriedades();
	public Propriedades site = new Propriedades();
	public Propriedades tela = new Propriedades();
	
	
	public static boolean salvar(Propriedades cp)
	{
		boolean retorno = false;
		try 
		{
			tQuery qry = new tQuery();
			// testes e ajustes
			if(cp.get("perfid").equals(""))
			{
				cp.put("perfid","null");
			}			
			// operação de salvamento
			if (dbUsua.exists(cp) != true)
			{
				qry.add("insert into usuario (nome, login, email, perfid, senha)");
				qry.add("values('" + cp.get("nome")+ "','" + cp.get("login")+ "','" + cp.get("email")+ "'," + cp.get("perfid")+ ",'" + cripto.md5("senhagercom") + "')");
				qry.executar();
				
				qry.limpar();
				qry.add("select currval('usuario_id_seq') as id;");
				qry.abrir();
				if (qry.proximo())
				{
					cp.put("id", qry.result.getString("id"));
				}				
			}
			else
			{
				qry.add(" update usuario ");
				qry.add(" set id   = "+cp.get("id"));
				qry.add(" ,   nome = '"+cp.get("nome")+"' ");
				qry.add(" ,   login = '"+cp.get("login")+"' ");
				qry.add(" ,   email = '"+cp.get("email")+"' ");
				qry.add(" ,   perfid = "+cp.get("perfid")+" ");
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
			qry.add("delete from usuario where id = " + cp.get("id"));
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
				dbUsua.excluir(usuario);
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
				qry.add("select id   from usuario");
				if (util.nstrvazia(cp.getProperty("id")))
				{
					qry.add("where   id = "+cp.get("id"));
				}
				else
				{
					if (util.nstrvazia(cp.getProperty("login")))
					{
						qry.add("where   login = '"+cp.get("login")+"' ");				
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
			qry.add("select  id       ");
			qry.add(",       nome         ");
			qry.add(",       login        ");
			qry.add(",       email        ");
			qry.add(",       perfid       ");				
			qry.add(",       to_char(ultimo_acesso, 'DD/MM/YYYY HH:MI:SS') as ultimo_acesso ");
			qry.add("from    usuario            ");
			if (util.nstrvazia(cp.getProperty("id")))
			{
				qry.add("where   id = "+cp.get("id"));
			}
			else
			{
				if (util.nstrvazia(cp.getProperty("login")))
				{
					qry.add("where   login = '"+cp.get("login")+"' ");				
				}
				else
				{
					qry.add("where   id = "+cp.get("id"));
				}
			}				
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
	
	public int trocarSenha(String EdtNovaSenha, String EdtConfirmarNovaSenha) throws SQLException
	{
		int retorno = 0;
		tQuery qry = new tQuery();

		//Testes e Ajustes
		if (this.testarSenha(this.campo.getProperty("senha")) == false)
		{
			retorno = 10; //,'Senha atual informada incorretamente. <br><br> Tente Novamente.');
		}
		
		// Testar se senha atual é diferente de nova senha
		if ((retorno ==0) && (this.campo.get("senha").equals(EdtNovaSenha)))
		{
			retorno = 11; //,'Senha atual é igual a Nova senha. <br><br> Tente Novamente.');
		}

		// Testar se nova senha é igual a confirmação
		if ((retorno ==0) && (!EdtNovaSenha.equals(EdtConfirmarNovaSenha)))
		{
			retorno = 12; //,'Nova Senha confirmada incorretamente. <br><br> Tente Novamente.');
		}
							
		//Operação de Salvamento
		if (retorno == 0)
		{
			//Alterar
			qry.add(" update usuario ");
			qry.add(" set senha = '"+EdtNovaSenha+"' ");
			qry.add(" where id = "+this.campo.get("id"));
			if (qry.executar())
			{
				this.campo.put("senha", EdtNovaSenha);
				retorno = 14; // troca realizada com sucesso
			}
			else
			{
				retorno = 13; //,'Ocorreram problemas de processamento durante a tentativa de alteração de senha, tente novamente. Caso o problema persista, entre em contato com a CSC Consultoria Ltda pelo email: cscweb@cscweb.com.br');
			}
		}
		return retorno;
	}	

	public boolean testarSenha(String Senha) throws SQLException
	{
		boolean retorno = false;
		tQuery qry = new tQuery();
		// Testar se a senha atual foi informada corretamente
		qry.add(" select id from usuario ");
		qry.add(" where  senha = '"+Senha+"' ");
		qry.add(" and    id = "+ this.campo.get("id"));	
		if (qry.abrir())
		{
			retorno = qry.proximo();
		}
		//System.out.println(qry.sql);
		return retorno;
	}
	
	public String primeiroNome()
	{
		String nomeCompleto = (String) this.campo.get("nome");
		
		Propriedades nomes = lista.explode(' ', nomeCompleto);
		return (String) nomes.get(1);
	}
	
	public boolean logar() throws SQLException
	{
		boolean retorno = false;
		// Testar se a senha atual foi informada corretamente
		if (this.testarSenha((String) this.campo.get("senha")) == false)
		{
			retorno = false;
		}
		else
		{
			tQuery qry = new tQuery();
			// Testar se a senha atual foi informada corretamente
			qry.add(" update usuario");
			qry.add(" set ultimo_acesso = current_timestamp");					
			qry.add(" where  id = "+ this.campo.get("id"));	
			qry.executar();
			retorno = true;
		}
		return retorno;
	}

	public boolean testarPermissao(String link) throws SQLException
	{
		boolean retorno = false;
		tQuery qry = new tQuery();
		// Testar se a senha atual foi informada corretamente
		qry.add(" select opco.id ");
		qry.add(" from   opcao opco ");
		qry.add(" ,      perfilopcao peop ");
		qry.add(" where  peop.perfid = '" + this.campo.get("perfid") + "' ");
		qry.add(" and    opco.link like '%" + link + "%' ");	
		if (qry.abrir())
		{
			retorno  = qry.proximo();
		}
		return retorno;
	}

	public static String[] validarAcesso( boolean variavelSession,String opco_link, dbUsua usuarioAtual) throws SQLException
	{
		String[] retorno = {"","",""};
		
		if (variavelSession == false)
		{
			retorno[0] = "FALSE";
			retorno[1] = "Você deve primeiro se autenticar na intranet. <br> Clique em Ok e informe o seu usuário e senha para se autenticar.";
			retorno[2] = "/QoEAT/acesso/login.ca?acao=trocarUsuario";			
		}
		else
		{
			if (usuarioAtual.testarSenha(usuarioAtual.campo.getProperty("senha")) == false)
			{
				retorno[0] = "FALSE";
				retorno[1] = "Você deve primeiro se autenticar na intranet. <br> Clique em Ok e informe o seu usuário e senha para se autenticar.";
				retorno[2] = "/QoEAT/acesso/login.ca?acao=trocarUsuario";
				//System.out.println("teste:::"+usuarioAtual.campo.getProperty("senha"));
			}
			else
			{
				if (usuarioAtual.testarPermissao(opco_link) == false)
				{
					retorno[0] = "FALSE";
					retorno[1] = "Usuário não tem permissão para acessar esta opção.";
					retorno[2] = "/QoEAT/acesso/login.ca?acao=logar";
				}
			}
		}		
		return retorno;
	}
	
	public static boolean selecionarEmpresa(String usuaid, String pessidempr)
	{
		boolean retorno = false;
		try 
		{
			tQuery qry = new tQuery();
			// testes e ajustes
			// operação de salvamento
			qry.add(" update usuario ");
			qry.add(" where  id = " + usuaid);
			qry.executar();								
			retorno = true;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return retorno;
	}

	public static String getIdPorNome(String nome)
	{
		String retorno = "";
		try 
		{
			tQuery qry = new tQuery();
			qry.add("select  id ");						
			qry.add("from    usuario ");
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

	public boolean naoExibirTodasProxSido() throws SQLException
	{
		boolean retorno = false;

		if (this.campo.gP("perfid").equals("1"))
		{
			retorno = false;
		}
		else
		{
			retorno = true;	
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
					" from 	usuario" +
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
					" from 	usuario" +
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
						" from 	usuario" +
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
						" from 	usuario" +
						" order by nome";
		cmb.objetoNome = nome;
		cmb.indexTabulacao = "";
		cmb.valorInicial = valorinicial;
		cmb.funcaoAoAlterar = true;
		cmb.acao = acao;
		cmb.constroi();
		return cmb.conteudo;
	}	

	public static String comboUnor(String valorinicial, String nome, String unorid) throws SQLException
	{
		tCombo cmb = new tCombo();				
		cmb.sql = 	" select 	id" +
					" , 		nome" +
					" from 	usuario" +
					" order by nome";
		cmb.objetoNome = nome;
		cmb.indexTabulacao = "";
		cmb.valorInicial = valorinicial;
		cmb.constroi();
		return cmb.conteudo;
	}
	
	public static String comboUnor(String valorinicial, String nome, String unorid, String acao) throws SQLException
	{
		tCombo cmb = new tCombo();				
		cmb.sql = 	" select 	id" +
					" , 		nome" +
					" from 	usuario" +
					" order by nome";
		cmb.objetoNome = nome;
		cmb.indexTabulacao = "";
		cmb.valorInicial = valorinicial;
		cmb.funcaoAoAlterar = true;
		cmb.acao = acao;
		cmb.constroi();
		return cmb.conteudo;
	}
	
	public static String comboUnorComTodos(String valorinicial, String nome, String unorid, String acao) throws SQLException
	{
		tCombo cmb = new tCombo();				
		cmb.sql = 	" select 	null as id" +
					" , 		'TODOS' as nome" +
					" , 		0 as ordem" +
					" union all " +
					" select 	id" +
					" , 		nome" +
					" , 		1 as ordem" +
					" from 	usuario" +
					" order by ordem, nome";
		cmb.objetoNome = nome;
		cmb.indexTabulacao = "";
		cmb.valorInicial = valorinicial;
		cmb.funcaoAoAlterar = true;
		cmb.acao = acao;
		cmb.constroi();
		return cmb.conteudo;
	}	

	public static String comboComTodos(String valorinicial, String nome) throws SQLException
	{
		tCombo cmb = new tCombo();
		cmb.sql = 	" select 	null as id" +
					" , 		'TODOS' as nome" +
					" , 		0 as ordem" +
					" union all " +
					" select 	usua.id" +
					" , 		usua.nome" +
					" , 		1 as ordem" +
					" from 	    usuario usua" +
					" order by  ordem, nome";
		cmb.objetoNome = nome;
		cmb.indexTabulacao = "";
		cmb.valorInicial = valorinicial;
		cmb.constroi();
		return cmb.conteudo;
	}
		
	public static String comboComTodos(String valorinicial, String nome, String acao) throws SQLException
	{
		tCombo cmb = new tCombo();
		cmb.sql = 	" select 	null as id" +
					" , 		'TODOS' as nome" +
					" , 		0 as ordem" +
					" union all " +
					" select 	usua.id" +
					" , 		usua.nome" +
					" , 		1 as ordem" +
					" from 	    usuario usua" +
					" order by  ordem, nome";
		cmb.objetoNome = nome;
		cmb.indexTabulacao = "";
		cmb.valorInicial = valorinicial;
		cmb.funcaoAoAlterar = true;
		cmb.acao = acao;
		cmb.constroi();
		return cmb.conteudo;
	}
}

