package com.acesso.web;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.acesso.db.dbPerf;
import com.acesso.db.dbSistema;
import com.acesso.db.dbUsua;
import com.lib.Propriedades;
import com.lib.cripto;
import com.lib.tHtml;
import com.lib.tTela;
import com.lib.util;

public class tLogin extends tTela{
	HttpSession sessao;
	dbUsua usuarioAtual = new dbUsua();
		
	public void iniciar(HttpServletRequest request) throws SQLException
	{
		// carregar Campos
		this.campo.put("acao","");
		this.campo.put("login", "");
		this.campo.put("senha", "");
		this.campo.put("telaAcao", "");
		this.campo.put("telaLink", "");
		this.campo.put("id", "");
		this.campo.put("empresaatual", "");

		this.campo.put("tempoTermino", "");
		this.campo.put("mensagem", "");
		this.campo.put("navegador", "");
		
		// objeto de persistencia
		this.putCampos(request);
		this.sessao = request.getSession();
		
		
		if (this.sessao.getAttribute("usuarioAtual") == null)
		{
			usuarioAtual.campo.put("login", this.campo.get("login"));
			usuarioAtual.campo.put("senha", cripto.md5(this.campo.getProperty("senha")));				
			//echo = "criou tUsuario";
		}
		else
		{
			usuarioAtual = (dbUsua) this.sessao.getAttribute("usuarioAtual");			
		}
		
		// executa a acao
		if (this.executar(this.campo.getProperty("acao")) == 1)
		{
			if (this.sessao.getAttribute("usuarioAtual") != null)
			{
				this.sessao.invalidate();
			}
			//echo ="Sem Acao, erro 1";				
			this.telaLogin();
		}
	}
	
	public void exec(String acao) throws SQLException
	{
		if (acao.equals("logar")) {this.logar();}
    	if (acao.equals("intranet")){this.intranet();}
    	if (acao.equals("constroiMenu")){this.constroiMenu();}
    	if (acao.equals("trocarUsuario")){this.trocarUsuario();}
    	if (acao.equals("telaLogin")){this.telaLogin();}
    	if (acao.equals("sair")){this.sair();}
    	if (acao.equals("setTela")){this.setTela();}
    	if (acao.equals("exibirVisaoGeral")){this.exibirVisaoGeral();}
    	if (acao.equals("avisoSistema")){this.avisoSistema();}
    	if (acao.equals("setAvisoSistema")){this.setAvisoSistema();}
	}
	
	public void trocarUsuario()
	{
		this.usuarioAtual.campo.put("login","");
		this.usuarioAtual.campo.put("senha","");			
		this.sessao.invalidate();				
		this.telaLogin();
	}
	
	public void telaLogin()
	{
		//imprimir a tela
		tHtml corpo = new tHtml("acesso/html/login.htm");
		echo = corpo.conteudo;
	}
	
	public void sair()
	{
		this.sessao.invalidate();
		this.telaLogin();
	}

	public void logar() throws SQLException
	{	
		boolean manutencao = true;
		manutencao = dbSistema.tempoTermino != 0;
		if (this.campo.get("login").equals("acmelo"))
		{
			manutencao = false;
		} 
		
		if (manutencao)
		{
			echo = "Erro:Sistema está em Manutenção.";
		} 
		else 
		{
			if (dbUsua.exists(this.usuarioAtual.campo) == false)
			{				
				echo = "Erro:Usuário ainda não cadastrado. Entre em contato com o Administrador do Sistema.";
			}
			else
			{	
				dbUsua.recuperar(usuarioAtual.campo);
				if (usuarioAtual.logar() == true)
				{
					// Constroi o menu
					dbPerf oPerf = new dbPerf();
					oPerf.campo.put("id", usuarioAtual.campo.getProperty("perfid"));
					String itensMenu = oPerf.constroiMenu("-1",this.campo.gP("navegador"));
					if (!itensMenu.equals(""))
					{	
						this.sessao.setAttribute("usuarioAtual", this.usuarioAtual);
						echo = "logado";	
					}
					else
					{				
						echo = "Erro:Usuário sem perfil. Entre em contato com o Administrador do Sistema.";
					}
				}
				else
				{
					echo = "Erro:Senha informada incorretamente. <br><br> Tente Novamente.";
				}
			}
		}
	}
	
	public void intranet() throws SQLException
	{					
		dbPerf oPerf = new dbPerf();
		oPerf.campo.put("id", this.usuarioAtual.campo.gP("perfid"));
	
		tHtml corpo = new tHtml("acesso/html/intranet.htm");
		corpo.conteudo = corpo.conteudo.replace("@nome@",usuarioAtual.primeiroNome());
		corpo.conteudo = corpo.conteudo.replace("@ultimoacesso@",usuarioAtual.campo.getProperty("ultimo_acesso"));
		corpo.conteudo = corpo.conteudo.replace("@usuario.nome@",usuarioAtual.campo.getProperty("nome"));
		corpo.conteudo = corpo.conteudo.replace("@usuario.login@",usuarioAtual.campo.getProperty("login"));
		corpo.conteudo = corpo.conteudo.replace("@usuario.pessidempr@",usuarioAtual.campo.getProperty("pessidempr"));
		dbPerf.recuperar(oPerf.campo);
		corpo.conteudo = corpo.conteudo.replace("@perfil.nome@",oPerf.campo.getProperty("nome"));
		if (util.toStr(usuarioAtual.tela.getProperty("telaLink")).equals(""))
		{
			corpo.conteudo = corpo.conteudo.replace("@carregar@","exibirMenu(); onMenu('Menu" + oPerf.primeiraOpcoId() + "',''); ");
		}
		else
		{
			corpo.conteudo = corpo.conteudo.replace("@carregar@","selecionarMenu('"+usuarioAtual.tela.getProperty("telaLink")+"','"+usuarioAtual.tela.getProperty("telaAcao")+"');");
		}
		echo = corpo.conteudo;
	}
	
	public void constroiMenu() throws SQLException
	{
		dbPerf oPerf = new dbPerf();
		//System.out.println(usuarioAtual.campo.getProperty("perfid"));
		oPerf.campo.put("id", usuarioAtual.campo.getProperty("perfid"));
		//System.out.println(oPerf.constroiMenu("-1"));
		echo = "<ul id=\"barraMenu\" class=\"baramenu\">"+oPerf.constroiMenu("-1",this.campo.gP("navegador"))+"</ul>";
		//System.out.println("passou por mim********************************");
	}
	

	public void setTela()
	{
		//Registrar a tela para atualização
		usuarioAtual.tela.put("telaLink", util.toStr(this.campo.getProperty("telaLink")));			
		usuarioAtual.tela.put("telaAcao", util.toStr(this.campo.getProperty("telaAcao")));
		this.sessao.setAttribute("usuarioAtual", usuarioAtual);
		//System.out.println("teste"+usuarioAtual.tela.getProperty("telaLink"));
	}		
	
	public void exibirVisaoGeral() throws SQLException
	{
		dbPerf oPerf = new dbPerf();
		oPerf.campo.put("id", usuarioAtual.campo.getProperty("perfid"));
		echo = oPerf.constroiVisaoGeral(this.campo.getProperty("id"));
	}
	
	public void avisoSistema() throws SQLException
	{
		if (!usuarioAtual.campo.gP("login").equals("acmelo"))
		{
			if (dbSistema.tempoTermino != 0)
			{
				echo = "terminar"+dbSistema.tempoTermino;
			}
		}
	}
	
	public void setAvisoSistema() throws SQLException
	{
		dbSistema.tempoTermino = Integer.valueOf(this.campo.gP("tempoTermino"));
		dbSistema.mensagem = this.campo.gP("mensagem");
	}	
}
