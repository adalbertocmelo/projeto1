package com.acesso.web;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.acesso.db.dbOpco;
import com.acesso.db.dbTopc;
import com.lib.lista;
import com.lib.tCad;
import com.mvc.BusinessLogic;

public class cadOpco extends tCad implements BusinessLogic{
	String link = "/acesso/cadOpco";
	public void iniciar(HttpServletRequest request) throws SQLException
	{
		// carregar Campos
		this.campo.put("acao","");
		this.campo.put("nBarraDePaginacao","");
		this.campo.put("nPag","");
		this.campo.put("pista","");
		this.campo.put("lstExcluir", "");

		this.campo.put("id", "");
		this.campo.put("opcoidpai", "");
		this.campo.put("nome", "");
		this.campo.put("link", "");
		this.campo.put("ordem", "");
		this.campo.put("imagem", "");
		this.campo.put("hierarquico", "");
		this.campo.put("hierarquia", "");
		this.campo.put("ancestrais", "");
		this.campo.put("descricao", "");
		this.campo.put("topcid", "");
							
		// objeto de persistencia
		this.putCampos(request);
		
		this.consTitulo = "Lista de Opções";
		this.consPista = this.campo.gP("pista");
		this.consCmpSemHint = "1;6;7;";
		this.consCmpFiltro = lista.arrExplode(':',"opco.id:opco.hierarquia:opco.nome:opco.link");
		this.consCmpTitulo = lista.arrExplode(':',"&nbsp;:Id:Ordem:Opção:Link:Ação:&nbsp;");
		this.consCmpLargura = lista.arrExplode(' ', "30px 30px 50px 260px 200px 70px 90px");
		this.consCmpAlinhamento = lista.arrExplode(' ', "left center left left left left left");				
		this.consSql   = 	" select  	'<input type=\"checkbox\" id=\"chkOpco' || id || '\" value=\"' || id || '\" onClick=\"oOpco.atualizaLst(this)\">' as col1 " +
							" ,			 opco.id" +
							" ,			 opco.hierarquia" +
							" , 			 substring(repeat('.', 100),1,length(trim(opco.hierarquico))) || nome as col2" +
							" ,			 opco.link" +
							" ,			 '<input type=\"button\" id=\"botAlterarOpco' || id || '\" value=\"Alterar\" onClick=\"oOpco.telaCadastro(' || id || ')\">' as col9 " +
							" ,			 '<input type=\"button\" id=\"botAdicionarFilho' || id || '\" value=\"Add Filho\" onClick=\"oOpco.adicionarFilho(' || id || ')\">' as col10" +
							" from    	opcao opco" +
							" where     @Filtro ";
		this.consSqlOrderBy = "order by hierarquia";		
		this.consQtdLinhas = 200;
		
		// propriedades tela
		this.htmlTela = "acesso/html/cadOpco.htm";
		this.htmlFrm = "acesso/html/cadOpcoFrm.htm";
		this.titulo = "Controle de Opções";
		this.objJs = "oOpco";
		
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
    	if (acao.equals("atualizarCodigoHierarquico")){this.atualizarCodigoHierarquico();}
	}

	public String criarCombos(String str) throws SQLException
	{			
		String str2 = str;
		str2 = str2.replace("@topcid@",dbTopc.combo(this.campo.gP("topcid"), "topcid"));
		return str2;
	}	
	
	public boolean comChaves()
	{
		return (!this.campo.getProperty("id").equals(""));
	}
	
	public void salvar()
	{
		dbOpco.salvar(this.campo);			
	}		

	public void excluir()
	{
		dbOpco.excluirLista(this.campo.getProperty("lstExcluir"));
	}			
	
	public void recuperar()
	{
		dbOpco.recuperar(this.campo);
	}	
	
	public void atualizarCodigoHierarquico() throws SQLException
	{
		dbOpco.atualizarCodigoHierarquico("");
	}		
}
