package com.lib;

import java.util.Properties;

public class Propriedades extends Properties{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getProperty(String value)
	{
		String retorno = "";
		if (super.getProperty(value) != null)
		{
			retorno = super.getProperty(value); 
		}
		return retorno;
	}
	
	public String gP(String value)
	{
		return getProperty(value);
	}

	public String gPB(String value)
	{
		return getProperty(value).replace("'", "''"); 
	}
	
	public String gPHTML(String value)
	{
		String resultado = value;
		resultado = getProperty(resultado).replace(">", "&gt;");
		resultado = getProperty(resultado).replace("<", "&lt;");
		return resultado;
	}	
	
	
	public void put(String key, String value)
	{
		String retorno = "";
		if (value != null)
		{
			retorno = value; 
		}
		super.put(key, retorno);
	}	
}
