package com.qoeat.web;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.qoeat.db.dbVide;
import com.lib.lista;
import com.lib.tCad;
import com.mvc.BusinessLogic;

public class cadVide extends tCad implements BusinessLogic{
	String link = "/qoeat/cadVide";	
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
		this.campo.put("duracao", "");
		this.campo.put("origem", "");
							
		// objeto de persistencia
		this.putCampos(request);
		
		this.consTitulo = "Lista de Videos";
		this.consPista = this.campo.gP("pista");
		this.consCmpFiltro = lista.arrExplode(':',"vide.id:vide.nome:vide.duracao:vide.origem");
		this.consCmpTitulo = lista.arrExplode(':',"&nbsp;:Id:Nome:Duracao:Ação");
		this.consCmpLargura = lista.arrExplode(' ', "30px 30px 400px 100px 60px");
		this.consCmpAlinhamento = lista.arrExplode(' ', "left center left left left");				
		this.consSql   = 	" select  	'<input type=\"checkbox\" id=\"chkVide' || id || '\" value=\"' || id || '\" onClick=\"oVide.atualizaLst(this)\">' as col1" +
							" ,			 vide.id" +
							" ,			 vide.nome" +
							" , 		 vide.duracao" +
							" ,			 '<input type=\"button\" id=\"botAlterarVide' || id || '\" value=\"Alterar\" onClick=\"oVide.telaCadastro(' || id || ')\">' as col9" +
							" from    	video vide" +
							" where     @Filtro ";	
		this.consSqlOrderBy = "order by id";		
		// propriedades tela
		this.htmlTela = "qoeat/html/cadVide.htm";
		this.htmlFrm = "qoeat/html/cadVideFrm.htm";
		this.titulo = "Controle de Videos";
		this.objJs = "oVide";
	
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
/*	
	public String criarCombos(String str) throws SQLException
	{			
		// Combo Perfil
		tCombo cmbPerf = new tCombo();		
		cmbPerf.sql = 	" select 	id" +
						" , 		nome" +
						" from 	perfil" +
						" order by nome";
		cmbPerf.objetoNome = "perfid";
		cmbPerf.indexTabulacao = "";
		cmbPerf.valorInicial = this.campo.getProperty("perfid");
		cmbPerf.constroi();
		str = str.replace("@perfid@",cmbPerf.conteudo);

		return str.replace("@unorid@", dbUnor.combo(this.campo.gP("unorid"), "unorid"));		
	}*/
	
	public void salvar()
	{
		dbVide.salvar(this.campo);			
	}		

	public void excluir()
	{
		dbVide.excluirLista(this.campo.getProperty("lstExcluir"));
	}			
	
	public void recuperar()
	{
		dbVide.recuperar(this.campo);
	}	
}

