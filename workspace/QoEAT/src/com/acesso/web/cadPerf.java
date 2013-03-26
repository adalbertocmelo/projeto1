package com.acesso.web;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import com.acesso.db.dbPerf;
import com.lib.lista;
import com.lib.tCad;
import com.mvc.BusinessLogic;

public class cadPerf extends tCad implements BusinessLogic{
	String link = "/acesso/cadPerf";	
	public void iniciar(HttpServletRequest request) throws SQLException
	{
		// carregar Campos
		this.campo.put("acao","");
		this.campo.put("nBarraDePaginacao","");
		this.campo.put("nPag","");
		this.campo.put("pista","");
		this.campo.put("lstExcluir", "");		

		this.campo.put("id", "");
		this.campo.put("nome", "");		
		// objeto de persistencia
		this.putCampos(request);
		
		this.consTitulo = "Lista de Perfis";
		this.consPista = this.campo.gP("pista");
		this.consCmpFiltro = lista.arrExplode(':',"id:nome");
		this.consCmpTitulo = lista.arrExplode(':',"&nbsp;:Id:Nome:Acao");
		this.consCmpLargura = lista.arrExplode(' ', "20px 30px 300px 100px");
		this.consCmpAlinhamento = lista.arrExplode(' ', "left center left left");				
		this.consSql   = 	" select  	'<input type=\"checkbox\" id=\"chkPerf' || id || '\" value=\"' || id || '\" onClick=\"oPerf.atualizaLst(this)\">' as col1" +
							" ,			 id" +
							" ,			 nome" +
							" ,			 '<input type=\"button\" id=\"botAlterarPerf' || id || '\" value=\"Alterar\" onClick=\"oPerf.telaCadastro(' || id || ')\">' as col9" +
							" from    perfil" +
							" where @Filtro";
		this.consSqlOrderBy = "order by id";		
					
		// propriedades tela
		this.htmlTela = "acesso/html/cadPerf.htm";
		this.htmlFrm = "acesso/html/cadPerfFrm.htm";
		this.titulo = "Controle de Perfis";
		this.objJs = "oPerf";
		
		// executa a acao
		this.executar(this.campo.getProperty("acao"));
	}
	
	public void exec(String acao) throws SQLException
	{
		if (acao.equals("salvar")) {this.salvar();}
    	if (acao.equals("excluir")){this.excluir();}
    	if (acao.equals("atualizarTab")){this.atualizarTab();}
    	if (acao.equals("telaCadastro")){this.telaCadastro();}
    	if (acao.equals("aoExibirTela")){this.aoExibirTela();}
    	if (acao.equals("exibirTela")){this.exibirTela();}
	}
	
	public boolean comChaves()
	{
		return (!this.campo.getProperty("id").equals(""));
	}
	
	public void salvar()
	{
		dbPerf.salvar(this.campo);			
	}		

	public void excluir()
	{
		dbPerf.excluirLista(this.campo.getProperty("lstExcluir"));
	}			
	
	public void recuperar()
	{
		dbPerf.recuperar(this.campo);
	}	
}

