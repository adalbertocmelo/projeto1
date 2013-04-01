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
		String retorno = "";
		try 
		{
			tQuery qry = new tQuery();
			qry.add("select	vide.nome " +
				",	codi.id "+
				", 	tran.mode "+
				", 	pltr.id "+
				",	tran.systemload "+
				", 	avg(aval.valor) as media "+
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
				" order by tran.mode, tran.systemload");

			qry.abrir();
			Propriedades linhasql = new Propriedades();
						
			if (qry.proximo())
			{
				System.out.println("entrou_loop");
				qry.putLinha(cp);
				/*vide.nome" +
				",	codi.id"+
				", 	tran.mode"+
				", 	pltr.id"+
				",	tran.systemload"+
				", 	avg(aval.valor) as media"+
				*/
				
				retorno = cp.gP("systemload");

				retorno = cp.gP("media");

				retorno = cp.gP("mode");
				
				System.out.print("socorro2" +retorno);
			}

		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
			//retorno = false;
		}
		return retorno;
	}
}

