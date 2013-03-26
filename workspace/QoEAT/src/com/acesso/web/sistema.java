package com.acesso.web;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.acesso.db.dbSistema;
import com.lib.tCad;
import com.mvc.BusinessLogic;

public class sistema extends tCad implements BusinessLogic{
	String link = "/acesso/sistema";	
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
		
	
		// executa a acao
		this.executar(this.campo.getProperty("acao"));
	}
	
	public void exec(String acao) throws SQLException
	{
		if (acao.equals("getDataHoje")) {this.getDataHoje();}
	}
	
	public void getDataHoje() throws SQLException
	{	
		echo = dbSistema.getDataHoje();
	}
}

