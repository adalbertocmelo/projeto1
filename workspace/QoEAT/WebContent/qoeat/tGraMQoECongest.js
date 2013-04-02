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

oGraMQoECongest.gerarGraMQoECongest = function()
{
	var oAjax = new tAjax(this.arqServer);//alert("oi1");
	oAjax.addCampo('acao','gerarGraMQoECongest');
	oAjax.addCampo('metrid',document.getElementById('metrid').value);
	oAjax.addCampo('codiid',document.getElementById('codiid').value);
	oAjax.addCampo('pltrid',document.getElementById('pltrid').value);
	oAjax.enviar('NoExec');
	//alert(oAjax.retorno);
	document.getElementById('tabGraMQoECongest').innerHTML = oAjax.retorno;
	
	oGraMQoECongest.gerarTabela();
	setTimeout("oGraMQoECongest.google()",800);
}

oGraMQoECongest.google = function()
{
	alert("google");
	document.getElementById("teste").src="../qoeat/GraPsnrCongestIFrame.html";
	
}

oGraMQoECongest.gerarTabela = function()
{
	tabela = new Array( );
	
	var oAjax = new tAjax(this.arqServer);
	oAjax.addCampo('acao','gerarTabela');
	oAjax.addCampo('metrid',document.getElementById('metrid').value);
	oAjax.addCampo('codiid',document.getElementById('codiid').value);
	oAjax.addCampo('pltrid',document.getElementById('pltrid').value);
	oAjax.enviar('NoExec');
	alert(oAjax.retorno);
	tabela = oAjax.retorno;
	//document.getElementById('tabGraMQoECongest').innerHtml = oAjax.retorno;
	//alert(tabela);
}

oGraMQoECongest.atualizarTab = function(){}

oGraMQoECongest.constroi();


