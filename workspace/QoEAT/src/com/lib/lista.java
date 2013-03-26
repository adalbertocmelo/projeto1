package com.lib;

import java.sql.SQLException;
import java.util.ArrayList;

public class lista {
	public static Propriedades explode (char separador, String lista)
	{
		Propriedades arrLista = new Propriedades();
		char[] listaChar = lista.toCharArray();
		String palavra = "";
		int i = 0;
		if (listaChar.length != 0)
		{
			for (int x = 0; x < listaChar.length ; x++)
			{
				if (listaChar[x] == separador)
				{
					i++;
					arrLista.put(i,palavra);
					palavra = "";
				}
				else
				{
					palavra += listaChar[x];
				}
				//System.out.println("x:" + x + ";palavra:"+palavra);	
			}
			if (listaChar[listaChar.length-1] != separador)
			{
				i++;
				arrLista.put(i, palavra);
			}
		}
		//System.out.println("i:" + i + ";palavra:"+palavra);
		
		return arrLista;
	}
	
	public static ArrayList<String> arrExplode (char separador, String lista)
	{
		Propriedades proLista = explode(separador, lista);
		ArrayList<String> arrLista = new ArrayList<String>();
		for (int i = 0; i <= proLista.size(); i++)
		{
			arrLista.add((String) proLista.get(i));
		}
		return arrLista;
	}
	
	public static String comboSN(String valorinicial, String nome) throws SQLException
	{
		tCombo cmb = new tCombo();				
		cmb.sql = 	" select 	'true' as id" +
					" , 		'Sim' as nome" +
					" union all " +
					" select 	'false' as id" + 
					" , 		'Não' as nome" +
					" order by  nome desc";
		cmb.objetoNome = nome;
		cmb.indexTabulacao = "";
		if (valorinicial.equals("t"))
		{
			valorinicial = "true";
		}
		else
		{
			valorinicial = "false";
		}
		cmb.valorInicial = valorinicial;
		cmb.constroi();
		return cmb.conteudo;
	}	
	
	public static String comboBoolean(String valorinicial, String nome, String nomeVerdadeiro, String nomeFalso) throws SQLException
	{
		tCombo cmb = new tCombo();				
		cmb.sql = 	" select 	'true' as id" +
					" , 		'"+nomeVerdadeiro+"' as nome" +
					" union all " +
					" select 	'false' as id" + 
					" , 		'"+nomeFalso+"' as nome" +
					" order by  nome desc";
		cmb.objetoNome = nome;
		cmb.indexTabulacao = "";
		if (valorinicial.equals("t"))
		{
			valorinicial = "true";
		}
		if (valorinicial.equals("f"))
		{
			valorinicial = "false";
		}
		cmb.valorInicial = valorinicial;
		cmb.constroi();
		return cmb.conteudo;
	}
	
	public static String comboBoolean(String valorinicial, String nome, String nomeVerdadeiro, String nomeFalso, String acao) throws SQLException
	{
		tCombo cmb = new tCombo();				
		cmb.sql = 	" select 	'true' as id" +
					" , 		'"+nomeVerdadeiro+"' as nome" +
					" union all " +
					" select 	'false' as id" + 
					" , 		'"+nomeFalso+"' as nome" +
					" order by  nome desc";
		cmb.objetoNome = nome;
		cmb.indexTabulacao = "";
		if (valorinicial.equals("t"))
		{
			valorinicial = "true";
		}
		if (valorinicial.equals("f"))
		{
			valorinicial = "false";
		}
		cmb.valorInicial = valorinicial;
		cmb.funcaoAoAlterar = true;
		cmb.acao = acao;
		cmb.constroi();
		return cmb.conteudo;
	}
	
	public static String comboRP(String valorinicial, String nome, String acao) throws SQLException
	{
		tCombo cmb = new tCombo();				
		cmb.sql = 	" select 	'true' as id" +
					" , 		'Recebimento' as nome" +
					" union all " +
					" select 	'false' as id" + 
					" , 		'Pagamento' as nome" +
					" order by  nome desc";
		cmb.objetoNome = nome;
		cmb.indexTabulacao = "";
		if (valorinicial.equals("t"))
		{
			valorinicial = "true";
		}
		else
		{
			valorinicial = "false";
		}
		cmb.valorInicial = valorinicial;
		cmb.funcaoAoAlterar = true;
		cmb.acao = acao;
		cmb.constroi();
		return cmb.conteudo;
	}	
	
	public static String comboSN(String valorinicial, String nome, String acao) throws SQLException
	{
		tCombo cmb = new tCombo();				
		cmb.sql = 	" select 	'true' as id" +
					" , 		'Sim' as nome" +
					" union all " +
					" select 	'false' as id" + 
					" , 		'Não' as nome" +
					" order by  nome";
		cmb.objetoNome = nome;
		cmb.indexTabulacao = "";
		if (valorinicial.equals("t"))
		{
			valorinicial = "true";
		}
		else
		{
			valorinicial = "false";
		}
		cmb.valorInicial = valorinicial;	
		cmb.funcaoAoAlterar = true;
		cmb.acao = acao;
		cmb.constroi();
		return cmb.conteudo;
	}	
	
	public static String comboPP(String valorinicial, String nome) throws SQLException
	{
		tCombo cmb = new tCombo();				
		cmb.sql = 	" select 	'true' as id" +
					" , 		'Publico' as nome" +
					" union all " +
					" select 	'false' as id" + 
					" , 		'Privado' as nome" +
					" order by  nome";
		cmb.objetoNome = nome;
		cmb.indexTabulacao = "";
		if (valorinicial.equals("t"))
		{
			valorinicial = "true";
		}
		else
		{
			valorinicial = "false";
		}
		cmb.valorInicial = valorinicial;
		cmb.constroi();
		return cmb.conteudo;
	}			

	public static String checkbox(String valorinicial, String nome) throws SQLException
	{
		String retorno = "<input id=\""+nome+"\" type=\"checkbox\" value=\"@valor@\" @checked@ onclick=\"if (this.value == 'true') { this.value = 'false'} else {this.value = 'true'}\">"; 
		if (!valorinicial.equals("f"))
		{
			retorno = retorno.replace("@checked@", "checked");
			retorno = retorno.replace("@valor@", "true");
		}
		else
		{
			retorno = retorno.replace("@checked@", "");
			retorno = retorno.replace("@valor@", "false");
		}
		return retorno;
	}
}
