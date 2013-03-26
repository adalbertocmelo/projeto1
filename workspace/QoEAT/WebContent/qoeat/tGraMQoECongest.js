var oGraMQoECongest = new tCad();

oGraMQoECongest.constroi = function()
{	
	alert("teste2");
	this.arqServer = '../qoeat?objServer=GraMQoECongest';
	this.lstExcluir = '';
	
	this.nome = 'GraMQoECongest';
	this.objFocus = 'nome';
	
	this.cmpNome  = new Array('id','nome','duracao','origem');
	this.cmpValor = new Array('','','','');	
	this.cmpFiltro = new Array();
	this.aoConstruir();
}

oGraMQoECongest.gerarGraMQoECongest = function()
{
	alert("teste1");
	var oAjax = new tAjax(this.arqServer);
	oAjax.addCampo('acao','gerarGraMQoECongest');
	oAjax.addCampo('metrid',document.getElementById('metrid').value);
	oAjax.addCampo('codiid',document.getElementById('codiid').value);
	oAjax.addCampo('pltrid',document.getElementById('pltrid').value);
	oAjax.enviar('NoExec');
	alert(oAjax.retorno);
	document.getElementById('tabGrafGraMQoECongest').innerHTML = oAjax.retorno;
}

oGraMQoECongest.atualizarTab = function(){}

oGraMQoECongest.constroi();