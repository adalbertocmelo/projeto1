package com.lib;

import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

public class tTela {
	public String echo = "";
	public Propriedades campo = new Propriedades();
		
	public int executar(String acao) throws SQLException 
	{
		int retorno = 0;
		if (acao.equals(""))
		{
			//echo = "Ação não informada.";
			retorno = 1;
		}
		else
		{
			//call_user_func(array($this,$acao));
	        // Get the method named sayHello.
	        //Method metodo = this.getClass().getMethod(acao, null);
	        //metodo.invoke(this, null);
	        //acaoMethod.
	        //System.out.println("Public method found: " + metodo.toString());
	    	this.exec(acao);
	        //System.out.println("Method either doesn't exist or is not public: " + ex.toString());
		}
		return retorno;
	}		
			
	public void putCampos(HttpServletRequest request)
	{
		Enumeration<Object> chaves = this.campo.keys();
		String chave = "";
		
		while (chaves.hasMoreElements())
		{
			chave = (String) chaves.nextElement();
			//System.out.println(chave);
			if (request.getParameter(chave) != null)
			{
				//System.out.println(request.getParameter(chave));
				this.campo.put(chave,request.getParameter(chave));
			}
		}
	}
	
	public void exec(String acao) throws SQLException
	{
		
	}
}
