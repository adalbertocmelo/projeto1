package com.acesso.web;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.acesso.db.dbUsua;
import com.lib.lista;
import com.lib.tCad;
import com.lib.tCombo;
import com.mvc.BusinessLogic;

public class cadUsua extends tCad implements BusinessLogic{
	String link = "/acesso/cadUsua";	
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
		this.campo.put("login", "");
		this.campo.put("email", "");
		this.campo.put("perfid", "");
		this.campo.put("senha", "");
		this.campo.put("pessidempr", "");

							
		// objeto de persistencia
		this.putCampos(request);
		
		this.consTitulo = "Lista de Usuarios";
		this.consPista = this.campo.gP("pista");
		this.consCmpFiltro = lista.arrExplode(':',"usua.id:usua.email:usua.nome:usua.login");
		this.consCmpTitulo = lista.arrExplode(':',"&nbsp;:Id:Login:Nome:Ação");
		this.consCmpLargura = lista.arrExplode(' ', "30px 30px 260px 300px 100px");
		this.consCmpAlinhamento = lista.arrExplode(' ', "left center left left left");				
		this.consSql   = 	" select  	'<input type=\"checkbox\" id=\"chkUsua' || id || '\" value=\"' || id || '\" onClick=\"oUsua.atualizaLst(this)\">' as col1" +
							" ,			 usua.id" +
							" ,			 usua.login" +
							" , 			 usua.nome" +
							" ,			 '<input type=\"button\" id=\"botAlterarUsua' || id || '\" value=\"Alterar\" onClick=\"oUsua.telaCadastro(' || id || ')\">' as col9" +
							" from    	usuario usua" +
							" where     @Filtro ";	
		this.consSqlOrderBy = "order by login";	
		// propriedades tela
		this.htmlTela = "acesso/html/cadUsua.htm";
		this.htmlFrm = "acesso/html/cadUsuaFrm.htm";
		this.titulo = "Controle de Usuarios";
		this.objJs = "oUsua";

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
		
		return str;		
	}
	
	public void salvar()
	{
		dbUsua.salvar(this.campo);			
	}		

	public void excluir()
	{
		dbUsua.excluirLista(this.campo.getProperty("lstExcluir"));
	}			
	
	public void recuperar()
	{
		dbUsua.recuperar(this.campo);
	}	
}

