package com.qoeat.web;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.lib.Propriedades;
import com.lib.lista;
import com.lib.tCad;
import com.lib.tCombo;
import com.lib.tHtml;
import com.mvc.BusinessLogic;
import com.qoeat.db.dbGraMQoE;

public class GraMQoECongest extends tCad implements BusinessLogic{
	String link = "/qoeat/GraMQoECongest";	
	public void iniciar(HttpServletRequest request) throws SQLException
	{
		// carregar Campos
		this.campo.put("acao","");
		this.campo.put("nBarraDePaginacao","");
		this.campo.put("nPag","");
		this.campo.put("pista","");
		this.campo.put("lstExcluir", "");

		this.campo.put("metrid", "");
		this.campo.put("pltrid", "");
		this.campo.put("codiid", "");
							
		// objeto de persistencia
		this.putCampos(request);
		
		this.consTitulo = "Gráfico de Métricas de QoE x Congestionamento";
		
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
		this.htmlTela = "qoeat/html/GraMQoECongest.htm";
		this.htmlFrm = "qoeat/html/GraMQoECongestFrm.htm";
		this.titulo = "Gráfico de Métricas de QoE x Congestionamento";
		this.objJs = "oGraMQoECongest";
	
		// executa a acao
		this.executar(this.campo.getProperty("acao"));
	}
	
	public void exec(String acao) throws SQLException
	{
    	if (acao.equals("gerarTabelaGraMQoECongest")){this.gerarTabelaGraMQoECongest();}
    	if (acao.equals("gerarTabelaGraMQoEGop")){this.gerarTabelaGraMQoEGop();}
    	if (acao.equals("telaCadastro")){this.telaCadastro();}
    	if (acao.equals("aoExibirTela")){this.aoExibirTela();}
    	if (acao.equals("exibirTela")){this.exibirTela();}
	}
	
	public String criarCombos(String str) throws SQLException
	{			
		// Combo Metrica
		tCombo cmbMetr = new tCombo();		
		cmbMetr.sql = 	" select 	id" +
						" , 		nome" +
						" from 	metrica" +
						" order by nome";
		cmbMetr.objetoNome = "metrid";
		cmbMetr.indexTabulacao = "";
		cmbMetr.complemento = " style=\"width:300px\"";
		cmbMetr.valorInicial = "5";
		//cmbMetr.valorInicial = this.campo.getProperty("metrid");
		cmbMetr.constroi();
		
		str = str.replace("@metrid@",cmbMetr.conteudo);
		
		// Combo Codificacao
		tCombo cmbCodi = new tCombo();		
		cmbCodi.sql = 	" select 	codi.id" +
						" , 		vide.nome  || ' ' || codi.id" +
						" from 	codificacao codi" +
						", video vide" +
						" where vide.id = codi.videid" +
						" order by vide.nome";
		cmbCodi.objetoNome = "codiid";
		cmbCodi.indexTabulacao = "";
		cmbCodi.complemento = " style=\"width:300px\"";
		cmbCodi.valorInicial = "58";
		//cmbCodi.valorInicial = this.campo.getProperty("codiid");
		cmbCodi.constroi();
		
		str = str.replace("@codiid@",cmbCodi.conteudo);
		
		// Combo Plano de Trabalho
		tCombo cmbPltr = new tCombo();		
		cmbPltr.sql = 	" select 	id" +
						" , 		nome" +
						" from 	planotrabalho" +
						" order by nome";
		cmbPltr.objetoNome = "pltrid";
		cmbPltr.indexTabulacao = "";
		cmbPltr.complemento = " style=\"width:300px\"";
		cmbPltr.valorInicial = "7";
		//cmbPltr.valorInicial = this.campo.getProperty("pltrid");
		cmbPltr.constroi();
		
		str = str.replace("@pltrid@",cmbPltr.conteudo);
		
		return str;		
	}
	
	public void gerarTabelaGraMQoECongest()
	{
		Propriedades cp = new Propriedades();
		cp.put("metrid", this.campo.gP("metrid"));
		cp.put("pltrid", this.campo.gP("pltrid"));
		cp.put("codiid", this.campo.gP("codiid"));

		echo = dbGraMQoE.gerarGraMQoECongest(cp);
	}	
	
	public void gerarTabelaGraMQoEGop()
	{
		Propriedades cp = new Propriedades();
		cp.put("metrid", this.campo.gP("metrid"));
		cp.put("pltrid", this.campo.gP("pltrid"));
		cp.put("codiid", this.campo.gP("codiid"));

		echo = dbGraMQoE.gerarGraMQoEGop(cp);
	}	
	
}

