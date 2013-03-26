package com.acesso.db;

import java.sql.SQLException;

import com.lib.Propriedades;
import com.lib.tCombo;

public class dbTopc 
{
	public Propriedades campo = new Propriedades();	

	public static String combo(String valorinicial, String nome) throws SQLException
	{
		tCombo cmb = new tCombo();				
		cmb.sql = 	" select 	id" +
						" , 	nome" +
						" from 	tipoopcao" +
						" order by nome";
		cmb.objetoNome = nome;
		cmb.indexTabulacao = "";
		cmb.valorInicial = valorinicial;
		cmb.constroi();
		return cmb.conteudo;
	}		
}