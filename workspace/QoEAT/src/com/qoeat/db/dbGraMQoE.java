package com.qoeat.db;

import java.sql.SQLException;

import com.lib.Propriedades;
import com.lib.lista;
import com.lib.tCombo;
import com.lib.tQuery;
import com.lib.util;

public class dbGraMQoE
{
	
	
	public static String gerarGraMQoECongest(Propriedades cp)
	{
		String retorno1 = "";
		String retorno2 = "";
		String retorno3 = "";
		String retornoTabTranId = "";
		try 
		{
			tQuery qry = new tQuery();
			qry.add("select	vide.nome " +
				",	codi.id as codiid"+
				", 	tran.mode "+
				", 	tran.id as tranid "+
				", 	pltr.id as pltrid"+
				",	tran.systemload "+
				", 	(avg(aval.valor))::numeric(15,4) as media "+
				" from	planotrabalho pltr "+
				", 	transmissao tran "+
				", 	video vide "+
				", 	codificacao codi "+
				",	codifram cofr "+
				", 	avaliacaoframe aval "+
				", 	metrica metr "+
				"where   vide.id = codi.videid "+
				"and     tran.codiid = codi.id "+
				"and	tran.pltrid = pltr.id "+
				"and  	tran.id = aval.tranid "+
				"and  	metr.id = aval.metrid "+
				"and	cofr.framid = aval.framid "+
				"and 	cofr.codiid = codi.id "+
				"and     pltr.id = "+ cp.gP("pltrid") + " " +
				"and 	metr.id = "+ cp.gP("metrid") + " " +
				"and	codi.id = "+ cp.gP("codiid") + " " +
				"group by vide.nome"+
				",	codi.id"+
				", 	tran.mode"+
				", 	tran.id"+
				", 	pltr.id"+
				",	tran.systemload  "+
				" order by tran.systemload, tran.mode");

			qry.abrir();
			Propriedades linhasql = new Propriedades();

			int contaSl = 0;
			String modeAnt = "";// guarda os macanismos para preenchar a primeira linha que formará a legenda do gráfico
			String slAnt = ""; //guarda a congestão anterior para saber quando houe mudança de mecanismo e assim começar uma nova coluna
			retorno1 = "oGraMQoECongest.tabelaGraMQoECongest = ([";
			retornoTabTranId = " oGraMQoECongest.tabelaTranId = ([";
			
			while (qry.proximo())
			{

				qry.putLinha(linhasql);
				if (!slAnt.equals(linhasql.gP("systemload")))
				{
					contaSl++;
					if (!slAnt.equals("") && slAnt.equals("260"))
					{
						retornoTabTranId += ", null ],";
						retorno3 += ", null ],";
						}else{
							if (!slAnt.equals("") && !(slAnt.equals("260")))
							{
								retorno3 += " ],";
								retornoTabTranId += " ],";
							}
						}
					slAnt = linhasql.gP("systemload");
					retorno3 += "['" +linhasql.gP("systemload") + "' ";
					retornoTabTranId += "['" +linhasql.gP("systemload") + "' ";
				}
				if (contaSl == 1)
				{
					if (!modeAnt.equals(linhasql.gP("mode")))
					{
						modeAnt = linhasql.gP("mode");
						if (retorno2.equals(""))
						{
							retorno2 += "['Congest','" +linhasql.gP("mode") + "' ";
						}
						else
						{
							retorno2 += ",'" +linhasql.gP("mode") + "' ";
						}
					}
				}
				retorno3 += ", "+ linhasql.gP("media") + " ";
				retornoTabTranId += ", "+ linhasql.gP("tranid") + " ";
			}
			retorno2 += "],";	
			retorno3 += "]]);";
			retornoTabTranId += "]]);";
		} 


		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return retorno1 + retorno2 + retorno3 + retornoTabTranId;
	}
	
	public static String gerarGraMQoEGop(Propriedades cp)
	{
		String retorno1 = "";
		String retorno2 = "";
		String retorno3 = "";
		try 
		{
			tQuery qry = new tQuery();
			qry.add("select	vide.nome " +
				",	codi.id as codiid"+
				", 	tran.mode "+
				", 	pltr.id as pltrid"+
				",	tran.systemload "+
				",	cogo.gopid "+
				", 	(avg(aval.valor))::numeric(15,4) as media "+
				" from	planotrabalho pltr "+
				", 	transmissao tran "+
				", 	video vide "+
				", 	codificacao codi "+
				", 	codigop cogo "+
				",	codifram cofr "+
				", 	avaliacaoframe aval "+
				", 	metrica metr "+
				"where   vide.id = codi.videid "+
				"and     tran.codiid = codi.id "+
				"and	tran.pltrid = pltr.id "+
				"and  	tran.id = aval.tranid "+
				"and  	metr.id = aval.metrid "+
				"and	cofr.framid = aval.framid "+
				"and	codi.id = cogo.codiid "+
				"and 	cofr.codiid = codi.id "+
				"and 	cofr.gopid = cogo.gopid "+
				"and 	metr.id = "+ cp.gP("metrid") + " " +
				"and	tran.id = "+ cp.gP("tranid") + " " +
				"group by vide.nome"+
				",	codi.id"+
				", 	tran.mode"+
				", 	pltr.id"+
				",	tran.systemload  "+
				" order by cogo.gopid");

			qry.abrir();
			Propriedades linhasql = new Propriedades();

			int contaSl = 0;
			String modeAnt = "";// guarda os macanismos para preenchar a primeira linha que formará a legenda do gráfico
			String slAnt = ""; //guarda a congestão anterior para saber quando houe mudança de mecanismo e assim começar uma nova coluna
			retorno1 = "oGraMQoECongest.tabelaGraMQoEGop = ([";

			while (qry.proximo())
			{

				qry.putLinha(linhasql);
				if (!slAnt.equals(linhasql.gP("systemload")))
				{
					contaSl++;
					if (!slAnt.equals("") && slAnt.equals("260"))
					{
						retorno3 += ", null ],";
						}else{
							if (!slAnt.equals("") && !(slAnt.equals("260")))
							{
								retorno3 += " ],";
							}
						}
					slAnt = linhasql.gP("systemload");
					retorno3 += "['" +linhasql.gP("systemload") + "' ";
				}
				if (contaSl == 1)
				{
					if (!modeAnt.equals(linhasql.gP("mode")))
					{
						modeAnt = linhasql.gP("mode");
						if (retorno2.equals(""))
						{
							retorno2 += "['Congest','" +linhasql.gP("mode") + "' ";
						}
						else
						{
							retorno2 += ",'" +linhasql.gP("mode") + "' ";
						}
					}
				}
				retorno3 += ", "+ linhasql.gP("media") + " ";
			}
			retorno2 += "],";
			retorno3 += "]]);";
		} 


		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return retorno1 + retorno2 + retorno3;
	}
}