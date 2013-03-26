package com.acesso.db;

import java.sql.SQLException;
import java.util.ArrayList;

import com.lib.Propriedades;
import com.lib.lista;
import com.lib.tCombo;
import com.lib.tHtml;
import com.lib.tQuery;
import com.lib.util;

public class dbPerf 
{
	public Propriedades campo = new Propriedades();
	
	public static boolean salvar(Propriedades cp)
	{
		boolean retorno = false;
		try 
		{
			tQuery qry = new tQuery();
			// testes e ajustes
			// operação de salvamento
			if (dbPerf.exists(cp) != true)
			{
				qry.add("insert into perfil (nome)");
				qry.add("values ('" + cp.get("nome")+ "');");
				qry.executar();
				
				qry.limpar();
				qry.add("select currval('perfil_id_seq') as id;");
				qry.abrir();
				if (qry.proximo())
				{
					cp.put("id", qry.result.getString("id"));
				}				
			}
			else
			{
				qry.add("update perfil");
				qry.add("set    id = " + cp.get("id") );
				qry.add(", 		nome = '" + cp.get("nome") + "'");
				qry.add("where  id = " + cp.get("id") );
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
			qry.add("delete from perfil where id = " + cp.get("id"));
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

			Propriedades perfil = new Propriedades();
			
			// loop para exclusão
			for (int x=1; x  <= arrLista.size();x++)
			{
				perfil.put("id", arrLista.get(x));
				dbPerf.excluir(perfil);
			}							
		}				
	}
	
	public static boolean exists(Propriedades cp) 
	{
		boolean retorno = false;
		if (cp.get("id") != "")
		{
			try 
			{
				// cria informando a SQL
				tQuery qry = new tQuery("select id   from perfil where id = " + cp.get("id"));
				
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
			qry.add("from    perfil ");
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
	
	public ArrayList<Propriedades> listarOpcoes() throws SQLException
	{
		Propriedades opcoes = new Propriedades();
		ArrayList<Propriedades> lista = new ArrayList<Propriedades>();
		
		tQuery qry = new tQuery();
		
		qry.add("select  opco.id       ");
		qry.add(",       opco.opcoidpai   ");
		qry.add(",       opco.nome         ");
		qry.add(",       opco.link         ");
		qry.add("from    opcao         opco ");
		qry.add(",       perfilopcao  peop ");
		qry.add("where   peop.perfid = "+this.campo.get("id"));
		qry.add("and     opco.id = peop.opcoid");			
		qry.add("order by hierarquia");						
		if (qry.abrir())
		{
			while(qry.proximo())
			{
				qry.putLinha(opcoes);
				
				lista.add(opcoes);
			}			
		}
		return lista;
	}
	

	public ArrayList<Propriedades> listarOpcoesPai() throws SQLException
	{
		Propriedades opcoes = new Propriedades();
		ArrayList<Propriedades> lista = new ArrayList<Propriedades>();
		
		tQuery qry = new tQuery();
		
		qry.add("select  opco.id       ");
		qry.add(",       opco.opcoidpai   ");
		qry.add(",       opco.nome         ");
		qry.add(",       opco.imagem          ");
		qry.add(",       opco.link         ");
		qry.add(",       (select count(*) from opcao opcofilho where opco.id = opcofilho.opcoidpai) as qtdfilhos");				
		qry.add("from    opcao         opco ");
		qry.add(",       perfilopcao  peop ");
		qry.add("where   peop.perfid = "+this.campo.get("id"));
		qry.add("and     opco.id = peop.opcoid");
		qry.add("and     opco.id in (select opcoidpai from opcao)");
		qry.add("order by opcoidpai, hierarquia");
		if (qry.abrir())
		{
			while(qry.proximo())
			{
				qry.putLinha(opcoes);

				lista.add(opcoes);
			}
		}
		return lista;
	}
	
	public String constroiMenu(String pai, String navegador) throws SQLException
	{
		String retorno = "";
		tQuery qry = new tQuery();
		
		qry.add("select  opco.id  ");
		qry.add(",       opco.opcoidpai");
		qry.add(",       opco.nome         ");
		qry.add(",       opco.imagem       ");
		qry.add(",       opco.link         ");
		qry.add(",       (select count(*) from opcao opcofilho where opco.id = opcofilho.opcoidpai) as qtdfilhos");			
		qry.add("from    opcao         opco ");
		qry.add(",       perfilopcao  peop ");
		qry.add("where   peop.perfid = 0"+this.campo.get("id"));
		qry.add("and     opco.id = peop.opcoid");
		qry.add("and     opco.opcoidpai = "+pai);
		qry.add("order by hierarquia");
		if (qry.abrir())
		{
			String modeloMainMenuSemFilho = "<li id=\"liMenu@id@\" class=\"mainmenu\"><input type=\"hidden\" id=\"liMenu@id@ident\" value=\"0\"> <input type=\"hidden\" id=\"liMenu@id@nivel\" value=\"0\"> <input type=\"hidden\" id=\"liMenu@id@opcoid\" value=\"@id@\"><div id=\"spMenu@id@\" class=\"mainmenuv1\" onClick=\"onMenu('Menu@id@','@link@');\">@nome@</div></li>";
			String modeloMainMenuComFilho = "<li id=\"liMenu@id@\" class=\"mainmenu\"><input type=\"hidden\" id=\"liMenu@id@ident\" value=\"0\"> <input type=\"hidden\" id=\"liMenu@id@nivel\" value=\"0\"><input type=\"hidden\" id=\"liMenu@id@opcoid\" value=\"@id@\"><div id=\"spMenu@id@\" class=\"mainmenuv1\" onClick=\"onMenu('Menu@id@','@link@');\">@nome@</div><ul class=\"menu\">@filhosMenu@</ul></li>";
			String modeloMenuSemFilho = "<li id=\"liMenu@id@\"><input type=\"hidden\" id=\"liMenu@id@ident\" value=\"0\"> <input type=\"hidden\" id=\"liMenu@id@nivel\" value=\"0\"> <input type=\"hidden\" id=\"liMenu@id@opcoid\" value=\"@id@\"><div id=\"spMenu@id@\" class=\"menuv1\" onClick=\"onMenu('Menu@id@','@link@');\">@nome@</div></li>";
			String modeloMenuComFilho	= "<li id=\"liMenu@id@\"><input type=\"hidden\" id=\"liMenu@id@ident\" value=\"0\"> <input type=\"hidden\" id=\"liMenu@id@nivel\" value=\"0\"> <input type=\"hidden\" id=\"liMenu@id@opcoid\" value=\"@id@\"><div id=\"spMenu@id@\" class=\"menuv1\" onClick=\"onMenu('Menu@id@','@link@');\">@nome@</div><ul>@filhosMenu@</ul></li>";

			if (navegador.equals("IE"))
			{
				modeloMainMenuSemFilho = "<li id=\"liMenu@id@\" class=\"mainmenuIE\"><input type=\"hidden\" id=\"liMenu@id@ident\" value=\"0\"> <input type=\"hidden\" id=\"liMenu@id@nivel\" value=\"0\"> <input type=\"hidden\" id=\"liMenu@id@opcoid\" value=\"@id@\"><div id=\"spMenu@id@\" class=\"mainmenuv1\" onClick=\"onMenu('Menu@id@','@link@');\">@nome@</div></li>";
				modeloMainMenuComFilho = "<li id=\"liMenu@id@\" class=\"mainmenuIE\"><input type=\"hidden\" id=\"liMenu@id@ident\" value=\"0\"> <input type=\"hidden\" id=\"liMenu@id@nivel\" value=\"0\"><input type=\"hidden\" id=\"liMenu@id@opcoid\" value=\"@id@\"><div id=\"spMenu@id@\" class=\"mainmenuv1\" onClick=\"onMenu('Menu@id@','@link@');\">@nome@</div><ul class=\"menu\">@filhosMenu@</ul></li>";
				modeloMenuSemFilho = "<li id=\"liMenu@id@\"><input type=\"hidden\" id=\"liMenu@id@ident\" value=\"0\"> <input type=\"hidden\" id=\"liMenu@id@nivel\" value=\"0\"> <input type=\"hidden\" id=\"liMenu@id@opcoid\" value=\"@id@\"><div id=\"spMenu@id@\" class=\"menuv1\" onClick=\"onMenu('Menu@id@','@link@');\">@nome@</div></li>";
				modeloMenuComFilho	= "<li id=\"liMenu@id@\"><input type=\"hidden\" id=\"liMenu@id@ident\" value=\"0\"> <input type=\"hidden\" id=\"liMenu@id@nivel\" value=\"0\"> <input type=\"hidden\" id=\"liMenu@id@opcoid\" value=\"@id@\"><div id=\"spMenu@id@\" class=\"menuv1\" onClick=\"onMenu('Menu@id@','@link@');\">@nome@</div><ul>@filhosMenu@</ul></li>";
			}

			retorno = "";
			String temp = "";

			while(qry.proximo())
			{
				temp = "";
				//System.out.println(qry.result.getString("nome").trim());
				// verifica se é MainMenu
				if (qry.result.getInt("opcoidpai") == -1)
				{
					//verfica se tem filhos
					if (qry.result.getInt("qtdfilhos") > 0)
					{
						temp = modeloMainMenuComFilho;
						temp = temp.replace("@id@", qry.result.getString("id").trim());
						temp = temp.replace("@nome@",qry.result.getString("nome").trim());
						temp = temp.replace("@link@",qry.result.getString("link").trim());
						temp = temp.replace("@filhosMenu@",this.constroiMenu(qry.result.getString("id").trim(),navegador));
					}
					else
					{
						temp = modeloMainMenuSemFilho;
						temp = temp.replace("@id@",qry.result.getString("id").trim());
						temp = temp.replace("@nome@",qry.result.getString("nome").trim());
						temp = temp.replace("@link@",qry.result.getString("link").trim());
					}
				}
				else
				{
					//verfica se tem filhos
					if (qry.result.getInt("qtdfilhos") > 0)
					{
						temp = modeloMenuComFilho;
						temp = temp.replace("@id@",qry.result.getString("id").trim());
						temp = temp.replace("@nome@",qry.result.getString("nome").trim());
						temp = temp.replace("@link@",qry.result.getString("link").trim());
						temp = temp.replace("@filhosMenu@",this.constroiMenu(qry.result.getString("id").trim(),navegador));
					}
					else
					{
						temp = modeloMenuSemFilho;
						temp = temp.replace("@id@",qry.result.getString("id").trim());
						temp = temp.replace("@nome@",qry.result.getString("nome").trim());
						temp = temp.replace("@link@",qry.result.getString("link").trim());
					}
				}
				retorno += temp;
			}
		}		

		return retorno;
	}
	

	public ArrayList<Propriedades> listarOpcoesPorPai(String pai) throws SQLException
	{
		Propriedades opcoes = new Propriedades();
		ArrayList<Propriedades> lista = new ArrayList<Propriedades>();

		tQuery qry = new tQuery();
		qry.add("select  opco.id");
		qry.add(",       opco.opcoidpai");
		qry.add(",       opco.nome         ");
		qry.add(",       opco.imagem       ");
		qry.add(",       opco.link         ");
		qry.add(",       (select count(*) from opcao opcofilho where opco.id = opcofilho.opcoidpai) as qtdfilhos");			
		qry.add("from    opcao         opco ");
		qry.add(",       perfilopcao  peop ");
		qry.add("where   peop.perfid = "+this.campo.get("id"));
		qry.add("and     opco.id = peop.opcoid");
		qry.add("and     opco.opcoidpai = "+pai);
		qry.add("order by hierarquia");
		if (qry.abrir())
		{
			while(qry.proximo())
			{
				opcoes.put("id", qry.result.getString("id").trim());
				opcoes.put("opcoidpai", qry.result.getString("opcoidpai").trim());
				opcoes.put("nome", qry.result.getString("nome").trim());
				opcoes.put("link", qry.result.getString("link").trim());
				opcoes.put("qtdfilhos", qry.result.getString("qtdfilhos").trim());
				opcoes.put("imagem", qry.result.getString("imagem").trim());		
				
				lista.add(opcoes);
			}
		}		
		return lista;
	}

	public ArrayList<Propriedades> listarUsuarios() throws SQLException
	{
		Propriedades usuarios = new Propriedades();
		ArrayList<Propriedades> lista = new ArrayList<Propriedades>();
		
		tQuery qry = new tQuery();
		qry.add("select  id       ");
		qry.add(",       nome         ");
		qry.add(",       email        ");
		qry.add("from    usuario          ");
		qry.add("where   perfid = "+this.campo.get("id"));
		if (qry.abrir())
		{
			while(qry.proximo())
			{
				usuarios.put("usua_codigo", qry.result.getString("id").trim());
				usuarios.put("usua_nome", qry.result.getString("nome").trim());
				usuarios.put("usua_email", qry.result.getString("email").trim());
				
				lista.add(usuarios);
			}
		}
		return lista;		
	}
	
	public String constroiVisaoGeralOpco(String pai) throws SQLException
	{
		String retorno = "";
		String temp = "";
		tQuery qry = new tQuery();
		qry.add("select  opco.id  ");
		qry.add(",       opco.nome         ");
		qry.add(",       opco.imagem       ");
		qry.add(",       opco.descricao       ");
		qry.add(",       opco.link         ");		
		qry.add("from    opcao         opco ");
		qry.add(",       perfilopcao  peop ");
		qry.add("where   peop.perfid = "+this.campo.getProperty("id"));
		qry.add("and     opco.id = peop.opcoid");
		qry.add("and     opco.opcoidpai = "+pai);
		qry.add("order by hierarquia");
		if (qry.abrir())
		{
			tHtml modelo = new tHtml("acesso/html/exibirVigeOpco.htm");
			retorno = "";

			while(qry.proximo())
			{
				
				temp = "";
				// verifica se é MainMenu
				temp = modelo.conteudo;
				temp = temp.replace("@id@",qry.result.getString("id").trim());
				temp = temp.replace("@nome@",qry.result.getString("nome").trim());
				if (qry.result.getString("imagem").trim().equals(""))
				{
					temp = temp.replace("@imagem@","telacadastro");
				}
				else
				{
					temp = temp.replace("@imagem@",qry.result.getString("imagem").trim());
				}
				temp = temp.replace("@descricao@",qry.result.getString("descricao").trim());
				temp = temp.replace("@link@",qry.result.getString("link").trim());
				retorno += temp;
			}
		}		

		return retorno;
	}		
	
	public String constroiVisaoGeral(String id) throws SQLException
	{
		//global $UsuarioAtual;
		String retorno = "";
		String temp = "";
		
		tQuery qry = new tQuery();
		qry.add("select  opco.id  ");
		qry.add(",       opco.nome         ");
		qry.add(",       opco.imagem       ");
		qry.add(",       opco.descricao       ");
		qry.add(",       opco.link         ");		
		qry.add("from    opcao         opco ");
		qry.add(",       perfilopcao  peop ");
		qry.add("where   peop.perfid = 0"+this.campo.get("id"));
		qry.add("and     opco.id = peop.opcoid");
		qry.add("and     opco.id = "+id);
		qry.add("order by hierarquia");
		if (qry.abrir())
		{
			if (qry.proximo())
			{
				tHtml modelo = new tHtml("acesso/html/exibirVige.htm");
				temp = "";
				// verifica se é MainMenu
				temp = modelo.conteudo;
				temp = temp.replace("@id@",qry.result.getString("id").trim());
				temp = temp.replace("@nome@",qry.result.getString("nome").trim());
				temp = temp.replace("@imagem@",qry.result.getString("imagem").trim());
				temp = temp.replace("@descricao@",util.toStr(qry.result.getString("descricao")).trim());
				//temp = temp.replace("@dominio@",trim($UsuarioAtual->Site["dominio"));
				temp = temp.replace("@grpOpco@",this.constroiVisaoGeralOpco(id).trim());
				retorno += temp;
			}
		}	
		return retorno;
	}		
	
	
	public String primeiraOpcoId() throws SQLException
	{
		String id = "";
		tQuery qry = new tQuery();
		
		qry.add("select  opco.id  ");
		qry.add("from    opcao         opco ");
		qry.add(",       perfilopcao  peop ");
		qry.add("where   peop.perfid = "+this.campo.get("id"));
		qry.add("and     opco.id = peop.opcoid");
		qry.add("order by hierarquia");
		qry.add("limit 1 offset 0");
		if (qry.abrir())
		{
			if (qry.proximo())
			{
				 id = qry.result.getString("id");
			}				
		}
		return id;
	}
	
	public static String combo(String valorinicial, String nome, String perfid, String link) throws SQLException
	{
		tCombo cmb = new tCombo();				
		cmb.sql = 	" select  opco.ordem  " +
					" ,       opco.nome         " +
					" from    opcoes         opco" +
					" ,		  opcao         opcopai " +
					" ,       perfilopcao  peop " +
					" where   opco.id = peop.opcoid" +
					" and     opcopai.id = opco.opcoidpai" +
					" and     peop.perfid = 0" + perfid +
					" and     opcopai.link = '"+link+"' " +
					" and     opco.link = '"+nome+"' " +
					" order by opco.hierarquia";

		cmb.objetoNome = nome;
		cmb.indexTabulacao = "";
		cmb.valorInicial = valorinicial;
		cmb.constroi();
		return cmb.conteudo;
	}	

	public static String combo(String valorinicial, String nome, String perfid, String link, String acao) throws SQLException
	{
		tCombo cmb = new tCombo();				
		cmb.sql = 	" select  opco.ordem  " +
					" ,       opco.nome         " +
					" from    opcao         opco" +
					" ,		  opcao         opcopai " +
					" ,       perfilopcao  peop " +
					" where   opco.id = peop.opcoid" +
					" and     opcopai.id = opco.opcoidpai" +
					" and     peop.perfid = 0" + perfid +
					" and     opcopai.link = '"+link+"' " +
					" and     opco.link = '"+nome+"' " +
					" order by opco.hierarquia";
		cmb.objetoNome = nome;
		cmb.indexTabulacao = "";
		cmb.valorInicial = valorinicial;
		cmb.funcaoAoAlterar = true;
		cmb.acao = acao;
		cmb.constroi();
		return cmb.conteudo;
	}	
}
