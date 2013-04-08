var oGraMQoECongest = new tCad();

oGraMQoECongest.constroi = function()
{	
	this.arqServer = '../qoeat?objServer=GraMQoECongest';
	this.lstExcluir = '';
	
	this.nome = 'GraMQoECongest';
	this.objFocus = 'nome';
	
	this.cmpNome  = new Array('id','nome','duracao','origem');
	this.cmpValor = new Array('','','','');	
	this.cmpFiltro = new Array();
	
	this.aoConstruir();
}

// M�todo para gerar o gr�fico de M�trica X Congestionamento
oGraMQoECongest.gerarGraMQoECongest = function()
{
	// Pegar a m�trica e armazenar diretamente no objeto pra passar pro java script do google como legenda pro gr�fico
	this.metrname = document.getElementById('metrid').options[document.getElementById('metrid').selectedIndex].text;
	
	// Chama o m�todo do ".java" passando os par�metros a serem utilizados pela sql para gerar o gr�fico
	var oAjax = new tAjax(this.arqServer);
	oAjax.addCampo('acao','gerarTabelaGraMQoECongest');
	oAjax.addCampo('metrid',document.getElementById('metrid').value);
	oAjax.addCampo('codiid',document.getElementById('codiid').value);
	oAjax.addCampo('pltrid',document.getElementById('pltrid').value);
	oAjax.enviar();
	
	// Chama o java script que gera o gr�fico (Google Graphics)
	document.getElementById("ifrGraMQoECongest").src="../qoeat/GraPsnrCongestIFrame.html";
}


oGraMQoECongest.gerarGraMQoEGop = function()
{
	//
	this.metrname = document.getElementById('metrid').options[document.getElementById('metrid').selectedIndex].text;
	
	
	var oAjax = new tAjax(this.arqServer);
	oAjax.addCampo('acao','gerarTabelaGraMQoEGop');
	oAjax.addCampo('metrid',document.getElementById('metrid').value);
	oAjax.addCampo('codiid',document.getElementById('codiid').value);
	oAjax.addCampo('pltrid',document.getElementById('pltrid').value);
	oAjax.enviar();
	
	
	document.getElementById("ifrGraMQoEGop").src="../qoeat/GraPsnrGopIFrame.html";
}

oGraMQoECongest.atualizarTab = function(){}

oGraMQoECongest.constroi();


