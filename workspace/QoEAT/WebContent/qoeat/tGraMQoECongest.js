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
	oAjax.enviar('NoExec');//alert("oi2");
	//alert(oAjax.retorno);
	document.getElementById('tabGraMQoECongest').innerHTML = oAjax.retorno;
	
	setTimeout("oGraMQoECongest.google()", 2000);
}

oGraMQoECongest.google = function()
{
	alert("google");
	google.load("visualization", "1", {packages:["corechart"]}); alert("google35");
	google.setOnLoadCallback(oGraMQoECongest.drawChart());	alert("google36");
	oGraMQoECongest.drawChart();//alert("google37");
}
       
oGraMQoECongest.drawChart = function ()
{
    alert("drawchart");

    var data = new google.visualization.arrayToDataTable(
    [
        ['Congest',     ' ',         'mec2',         'mec1',         'mec4',         'mec5',         'mec6',         'IEEE',         'dropQoEOnt'],
        [' ',           45, ,, , , , , ,              ],
        ['10',           49,             49,             49,             49,             49,             49,             49,             49          ],
                
        ['20',           46.81879,       46.50843,       47.00457,       47.66943,       46.86161,       45.92428,       46.06181,       47.66943    ],
        ['30',           46.19535,       45.82653,       47.15847,       46.60496,       47.97442,       45.74588,       46.66936,       47.97442    ],
        ['40',           47.31813,       45.6513,        47.0718,        46.58449,       45.86174,       45.8813,        46.45037,       47.0718	],
    ]);

    
    var options = {
        vAxis: {title: 'PSNR (dB)', titleTextStyle: {color:'blue'}, textPosition: 'in'},
        hAxis: {title: 'Congest (%)', titleTextStyle: {color:'blue'}, textPosition: 'in'},
        legend: {position: 'top', alignment: 'end'},
        pointSize:4,
        colors: ['white','yellow','gray','purple','black','green','pink','blue']

    };

            
    var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
    chart.draw(data, options);
    
    //google.visualization.events.addListener(table, 'select', drawChart2);
}
/*
function drawChart2(){
    var row = data.getSelection()[0].row;
    //var column = data.getSelection()[0].column;
    alert('You selected ' + data.getValue(row, 0));
    alert('Hello World');
}*/

oGraMQoECongest.atualizarTab = function(){}

oGraMQoECongest.constroi();


