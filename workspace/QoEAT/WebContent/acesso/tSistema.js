function tSistema()
{
	this.getDataHoje = function()
	{
		var oAjax = new tAjax('../acesso?objServer=sistema');		
		oAjax.addCampo('acao','getDataHoje');
		oAjax.enviar('NoExec');

		if (oAjax.retorno != '')
		{
			return oAjax.retorno;
		}
		else
		{
			return '';
		}
	}
	
	this.difData = function(strData1, strData2)
	{
		return (((Date.parse(strData2))-(Date.parse(strData1)))/(24*60*60*1000)).toFixed(0);
	}
	
	this.formataDataSistema = function(Data)
	{
		var mes = [];
		mes[0] = "Jan";
		mes[1] = "Feb";
		mes[2] = "Mar";
		mes[3] = "Apr";
		mes[4] = "May";
		mes[5] = "Jun";
		mes[6] = "Jul";
		mes[7] = "Aug";
		mes[8] = "Sep";
		mes[9] = "Oct";
		mes[10] = "Nov";
		mes[11] = "Dec";
		// Separa a data informada pelo usuário através da barra /
		var arrData = Data.split('/');
		// Formata a data para o seguinte formato: Nov 22, 2006
		var novaData = mes[(arrData[1] - 1)] + ' ' + arrData[0] + ', ' + arrData[2];
		return novaData;		
	}
	
	this.diasEntreDatas = function(strData1, strData2) 
	{
		var novaData1, novaData2, diasEntreDatas;
		novaData1 = this.formataDataSistema(strData1);
		novaData2 = this.formataDataSistema(strData2);
		diasEntreDatas = this.difData(novaData1, novaData2);
		return diasEntreDatas;
	}
}

var oSistema = new tSistema();