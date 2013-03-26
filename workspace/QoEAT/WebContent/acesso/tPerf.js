var oPerf = new tCad();

oPerf.constroi = function()
{
	this.arqServer = '../acesso?objServer=cadPerf';
	this.lstExcluir = '';
	
	this.nome = 'Perf';
	this.objFocus = 'nome';	
	
	this.cmpNome  = new Array('id','nome');
	this.cmpValor = new Array('','');	
	this.cmpFiltro = new Array();
	this.aoConstruir();
}

oPerf.constroi();