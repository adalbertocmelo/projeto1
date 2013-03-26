package com.lib;

public class tMensagem {
	public String titulo = "";
	public String texto  = "";
	public String link   = "";
	public String conteudo = "";
	
	public void constroi()
	{
		tHtml janela = new tHtml("acesso/html/janela_erro2.htm");

		// Substitue as Variáveis
		janela.conteudo = janela.conteudo.replace("@Titulo",this.titulo);
		janela.conteudo = janela.conteudo.replace("@Corpo",this.texto);
		janela.conteudo = janela.conteudo.replace("@Link",this.link);							
		this.conteudo = janela.conteudo;
	}

	public void aplicaTela()
	{ 
		this.constroi();
	}
	
	public void show()
	{
		this.constroi();
	}	

	public void showTela()
	{
		this.aplicaTela();
	}
}
