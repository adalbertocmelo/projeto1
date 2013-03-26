package com.acesso.web;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.acesso.db.dbPeop;
import com.lib.lista;
import com.lib.tCad;
import com.lib.tCombo;
import com.mvc.BusinessLogic;

public class cadPeop extends tCad implements BusinessLogic{
	String link = "/acesso/cadPeop";
	public void iniciar(HttpServletRequest request) throws SQLException
	{
		// carregar Campos
		this.campo.put("acao","");
		this.campo.put("nBarraDePaginacao","");
		this.campo.put("nPag","");

		this.campo.put("perfid", "");
		this.campo.put("opcoid", "");
							
		// objeto de persistencia
		this.putCampos(request);
		
		this.consTitulo = "Lista de Permissões";
		this.consCmpTitulo = lista.arrExplode(':',"Permitir:Opções:Hierarquia");
		this.consCmpLargura = lista.arrExplode(' ', "60px 330px 330px");
		this.consCmpAlinhamento = lista.arrExplode(' ', "left left left");				
		this.consSql   = 	" select  '<input type=\"checkbox\" id=\"chk' || opco.id || '\" value=\"' || opco.id || '\" onClick=\"oPeop.inserirExcluir(''" + this.campo.getProperty("perfid") +"'',''' || opco.id || ''',''chk' || opco.id || ''');\" checked>' as marca " +
							" , substring(repeat('.', 100),1,length(trim(opco.hierarquico))) || opco.nome as nome" +
							" , opco.hierarquia" +
							" from opcao opco" +
							" where @Filtro" +
							" and   exists (select peop.opcoid from perfilopcao peop where peop.perfid = " + this.campo.getProperty("perfid") + " and opco.id = peop.opcoid)" +
							" union all" +
							" select '<input type=\"checkbox\" id=\"chk'|| opco.id || '\" value=\"'|| opco.id || '\" onClick=\"oPeop.inserirExcluir(''"+this.campo.getProperty("perfid") + "'',''' || opco.id || ''',''chk' || opco.id || ''');\" >' as marca" +
							" , substring(repeat('.', 100),1,length(trim(opco.hierarquico))) || opco.nome as nome" +
							" , opco.hierarquia" +
							" from opcao opco" +
							" where @Filtro" +
							" and   not exists (select peop.opcoid from perfilopcao peop where peop.perfid = " + this.campo.getProperty("perfid") + " and opco.id = peop.opcoid)";	
		this.consSqlOrderBy = "order by hierarquia";		
		//System.out.println("SQLCons:"+this.consSql);
		this.consQtdLinhas = 200;
		// propriedades tela
		this.htmlTela = "acesso/html/cadPeop.htm";
		this.htmlFrm = "acesso/html/cadPeopFrm.htm";
		this.titulo = "Controle de Permissões";
		this.objJs = "oPeop";
		
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
		cmbPerf.funcaoAoAlterar = true;
		cmbPerf.acao = "oPeop.atualizarTab();";	
		cmbPerf.constroi();

		return str.replace("@perfid@",cmbPerf.conteudo);	
	}
	
	public boolean comChaves()
	{
		return (!this.campo.getProperty("perfid").equals("")) && (!this.campo.getProperty("opcoid").equals(""));
	}
	
	public void salvar()
	{
		dbPeop.salvar(this.campo);			
	}		

	public void excluir()
	{
		dbPeop.excluir(this.campo);
	}			
	
	public void recuperar()
	{
		dbPeop.recuperar(this.campo);
	}	
}

