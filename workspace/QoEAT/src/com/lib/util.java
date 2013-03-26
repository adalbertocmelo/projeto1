package com.lib;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
public class util 
{
	public static boolean strvazia(String scampo)
	{
		if (scampo == null)
		{
			return true;
		}
		else
		{
			if (scampo.equals(""))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
	}
	
	
	public static boolean eData(String data)
	{
		boolean retorno = true;
		try 
		{
			DateFormat df = DateFormat.getDateInstance( DateFormat.SHORT ); 
			df.setLenient( false ); // this is important! 
			Date dt2 = df.parse( data );
			retorno = true;
		}catch (ParseException e)
		{ 
			//data inválida 
			retorno = false;
		}catch (IllegalArgumentException e)
		{ 
			// data inválida 
			retorno = false;
		}
		return retorno;	
	}	

	
	public static boolean nstrvazia(String scampo)
	{
		return !(strvazia(scampo));
	}
	
	public static String toStr(String scampo)
	{
		if (scampo == null)
		{
			return "";
		}
		else
		{
			return scampo;
		}
	}
	
	public static int intervalToInt(String duracaoformatada, String unidadeTempo)
	{
		int retorno = 0;
		
		Propriedades arrInterval = lista.explode(':', duracaoformatada);

		int dia = Integer.valueOf((String)arrInterval.get(1));
		int mes = Integer.valueOf((String)arrInterval.get(2));
		int ano = Integer.valueOf((String)arrInterval.get(3));
		int hora = Integer.valueOf((String)arrInterval.get(4));
		int minuto = Integer.valueOf((String)arrInterval.get(5));
		int segundo = Integer.valueOf((String)arrInterval.get(6));
		
		if (unidadeTempo.equals("segundo"))
		{
			retorno = segundo;
			retorno += minuto * 60;
			retorno += hora * 60 * 60;
			retorno += dia * 24 * 60 * 60;
			retorno += mes * 31 * 24 * 60 * 60;
			retorno += ano * 365 * 31 * 24 * 60 * 60;
		}

		if (unidadeTempo.equals("minuto"))
		{
			retorno = minuto;
			retorno += hora * 60;
			retorno += dia * 24 * 60;
			retorno += mes * 31 * 24 * 60;
			retorno += ano * 365 * 31 * 24 * 60;
		}

		if (unidadeTempo.equals("hora"))
		{
			retorno = hora;
			retorno += dia * 24;
			retorno += mes * 31 * 24;
			retorno += ano * 365 * 31 * 24;
		}

		if (unidadeTempo.equals("dia"))
		{
			retorno = dia;
			retorno += mes * 31;
			retorno += ano * 365 * 31;
		}
		
		if (unidadeTempo.equals("mes"))
		{
			retorno = mes;
			retorno += ano * 365;
		}
		
		if (unidadeTempo.equals("ano"))
		{
			retorno = ano;
		}		
		return retorno;
	}	
	
	public static String dataNull(String data)
	{
		String dataTemp = data;
		dataTemp = dataTemp.replace("/","");
		dataTemp = dataTemp.replace("-","");
		if(dataTemp.equals(""))
		{
			data = "null";
		}
		else
		{
			data = "\""+data+"\"";		
		}
		return data;
	}	
	
	public static String dataBanco(String data)
	{
		String dataR = "";
		if (!data.equals(""))
		{
			String dataD = data.substring(0, 2);
			String dataM = data.substring(3, 5);
			String dataA = data.substring(6);	
			dataR = util.dataNull(dataA+"-"+dataM+"-"+dataD);
			dataR = dataR.replace("\"","'");
		}
		else
		{
			dataR = util.dataNull(data);
		}
		return dataR;
	}
	
	public static String dataHoraBanco(String data)
	{
		String dataR = "";
		if (!data.equals(""))
		{
			String dataD = data.substring(0, 2);
			String dataM = data.substring(3, 5);
			String dataA = data.substring(6,10);	
			String dataHo = data.substring(11,13);	
			String dataMi = data.substring(14,16);	
			dataR = util.dataNull(dataA+"-"+dataM+"-"+dataD);
			dataR = dataR.replace("\"","'");
			if (dataR.replace("'","") != "null")
			{
				dataR = "'"+dataR.replace("'","")+" "+dataHo+":"+dataMi+"'";
			}
		}
		else
		{
			dataR = util.dataNull(data);
		}
		return dataR;
	}	
}
