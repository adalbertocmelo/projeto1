var oPeop = new tCad();

oPeop.constroi = function()
{
	this.arqServer = '../acesso?objServer=cadPeop';
	this.lstExcluir = '';
	
	this.nome = 'Peop';
	this.objFocus = 'perfid';
	
	this.cmpNome  = new Array('perfid');
	this.cmpValor = new Array('ignorar');
	this.cmpFiltro = new Array('perfid');
	this.aoConstruir();
}

oPeop.inserirExcluir = function(perfid, opcoid, obj)
{
	var oAjax = new tAjax(this.arqServer);
	if (document.getElementById(obj).checked)
	{
		oAjax.addCampo('acao','salvar');		
	}
	else
	{
		oAjax.addCampo('acao','excluir');
	}	
	oAjax.addCampo('perfid', perfid+'');
	oAjax.addCampo('opcoid', opcoid+'');
	oAjax.enviar('NoExec');
}

oPeop.constroi();