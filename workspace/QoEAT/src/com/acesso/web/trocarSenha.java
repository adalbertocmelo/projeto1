package com.acesso.web;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.lib.cripto;
import com.lib.tCad;
import com.lib.tHtml;
import com.mvc.BusinessLogic;

public class trocarSenha extends tCad implements BusinessLogic{
	String link = "/acesso/trocarSenha";
	public void iniciar(HttpServletRequest request) throws SQLException
	{
		// carregar Campos
		this.campo.put("acao","");
		this.campo.put("nBarraDePaginacao","");
		this.campo.put("nPag","");

		this.campo.put("EdtNovaSenha", "");
		this.campo.put("EdtConfirmarNovaSenha", "");

							
		// objeto de persistencia
		this.putCampos(request);
			
		// propriedades tela
		this.htmlTela = "acesso/html/trocarSenha.htm";
		this.titulo = "Trocar Senha";
		this.objJs = "oTrse";
		
		// executa a acao
		this.executar(this.campo.getProperty("acao"));
	}
	
	public void exec(String acao) throws SQLException
	{
    	if (acao.equals("atualizarTab")){this.atualizarTab();}
    	if (acao.equals("trocar")){this.trocar();}
    	if (acao.equals("aoExibirTela")){this.aoExibirTela();}
    	if (acao.equals("exibirTela")){this.exibirTela();}
	}
	
	public String criarCombosTela(String str)
	{			
		str = str.replace("@nome@",usuarioAtual.campo.getProperty("nome"));
		str = str.replace("@login@",usuarioAtual.campo.getProperty("login"));			
		return str;	
	}
	
	public void atualizarTab()
	{
		
	}		
	
	public void exibirTela()
	{
		//imprimir a tela
		tHtml corpo = new tHtml(this.htmlTela);
		corpo.conteudo = this.criarCombosTela(corpo.conteudo);
		corpo.conteudo = corpo.conteudo.replace("@titulo@", this.titulo);
		echo = corpo.conteudo;		
	}
	
	public void trocar() throws SQLException
	{	
		int retorno = usuarioAtual.trocarSenha(cripto.md5(this.campo.getProperty("EdtNovaSenha")), cripto.md5(this.campo.getProperty("EdtConfirmarNovaSenha")));
		
		if (retorno == 10)
		{
			echo = "Senha atual informada incorretamente. Tente Novamente.";
		}

		if (retorno == 11)
		{
			echo = "Senha atual é igual a Nova senha. Tente Novamente.";				
		}

		if (retorno == 12)
		{
			echo = "Nova Senha confirmada incorretamente. Tente Novamente.";
		}

		if (retorno == 13)
		{
			echo = "Ocorreram problemas de processamento durante a tentativa de alteração de senha, " +
				   "tente novamente. Caso o problema persista, entre em contato com o Administrador do Sistema.";
		}

		if (retorno == 14)
		{
			echo = "Senha alterada com sucesso.";
		}
	}
}

