package com.qoeat.db;

import java.sql.SQLException;

import com.lib.Propriedades;
import com.lib.lista;
import com.lib.tCombo;
import com.lib.tQuery;
import com.lib.util;

public class dbGraMQoECongest
{
	public static String gerar(Propriedades cp)
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
				", 	(avg(aval.valor))::numeric(15,4) as media "+
				"from	planotrabalho pltr "+
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
				", 	pltr.id"+
				",	tran.systemload  "+
				" order by tran.systemload, tran.mode");

			qry.abrir();
			Propriedades linhasql = new Propriedades();

			int contaSl = 0;
			String modeAnt = "";
			String slAnt = ""; //guarda o mecanismo anterior para saber quando houe mudança de mecanismo e assim começar uma nova coluna
			retorno1 = "tabela = ([";
						
			while (qry.proximo())
			{
				qry.putLinha(linhasql);
				if (!slAnt.equals(linhasql.gP("systemload")))
				{
					contaSl++;
					if(contaSl==17)
					{
						break;
					}
					if (!slAnt.equals(""))
					{
						retorno3 += "],";
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
				retorno3 += ", "+linhasql.gP("media") + " ";
			}
			retorno2 += "],";
			retorno3 += "]]);";
			System.out.println(retorno1 + retorno2 + retorno3);
		} 

		
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return retorno1 + retorno2 + retorno3;
	}
}

