//acesso
importarJs('tAjax.js');
importarJs('tAjax2.js');
importarJs('tCad.js');
importarJs('tOpco.js');
importarJs('tPerf.js');
importarJs('tPeop.js');
importarJs('tUsua.js');
importarJs('tTrse.js');
importarJs('tSistema.js');

// qoeat

importarJs('https://www.google.com/jsapi?autoload={"modules":[{"name":"visualization","version":"1"}]}');
importarJs('../qoeat/tVide.js');
importarJs('../qoeat/tGraMQoECongest.js');

// sisca
importarJs('../sisca/tFltr.js');
importarJs('../sisca/tSitu.js');
importarJs('../sisca/tFuau.js');
importarJs('../sisca/tUnor.js');
importarJs('../sisca/tTare.js');
importarJs('../sisca/tFtta.js');
importarJs('../sisca/tTitr.js');
importarJs('../sisca/tTrab.js');
importarJs('../sisca/tAcomTrab.js');
importarJs('../sisca/tAcomTrabRespUnor.js');
importarJs('../sisca/tAcomTrabEmpresa.js');

importarJs('../sisca/tFefi.js');
importarJs('../sisca/tCose.js');
importarJs('../sisca/tCont.js');
importarJs('../sisca/tSido.js');
importarJs('../sisca/tBrie.js');
importarJs('../sisca/tTive.js'); //(meio)
importarJs('../sisca/tSexo.js');
importarJs('../sisca/tTpes.js');
importarJs('../sisca/tGPess.js');
importarJs('../sisca/tOrse.js');
importarJs('../sisca/tOrseAran.js');
importarJs('../sisca/tOrca.js');
importarJs('../sisca/tAupr.js');
importarJs('../sisca/tMami.js');
importarJs('../sisca/tAumi.js');

importarJs('../sisca/tMofiRec.js');
importarJs('../sisca/tMofiPag.js');
importarJs('../sisca/tNofi.js');

importarJs('../sisca/tAtiv.js');
importarJs('../sisca/tCarg.js');
importarJs('../sisca/tCida.js');
importarJs('../sisca/tDepa.js');
importarJs('../sisca/tEsta.js');
importarJs('../sisca/tGrup.js');
importarJs('../sisca/tProf.js');
importarJs('../sisca/tFovi.js');
importarJs('../sisca/tRede.js');
importarJs('../sisca/tProg.js');
importarJs('../sisca/tGene.js');
importarJs('../sisca/tTarg.js');
importarJs('../sisca/tTiin.js');
importarJs('../sisca/tInsu.js');
importarJs('../sisca/tMate.js');
importarJs('../sisca/tTido.js');
importarJs('../sisca/tFopa.js');
importarJs('../sisca/tFoap.js');
importarJs('../sisca/tPeca.js');
importarJs('../sisca/tPraz.js');
importarJs('../sisca/tFomi.js');
importarJs('../sisca/tCecu.js');
importarJs('../sisca/tTiaa.js');
importarJs('../sisca/tAran.js');
importarJs('../sisca/tBanc.js');
importarJs('../sisca/tCoco.js');
importarJs('../sisca/tDofi.js');
importarJs('../sisca/tTddf.js');
importarJs('../sisca/tConOrse.js');
importarJs('../sisca/tConBrie.js');
importarJs('../sisca/tConMami.js');
importarJs('../sisca/tConAumi.js');
importarJs('../sisca/tConOrca.js');
importarJs('../sisca/tConAupr.js');
importarJs('../sisca/tGerarMofiMami.js');
importarJs('../sisca/tGerarMofiAumi.js');
importarJs('../sisca/tGerarMofiOrca.js');
importarJs('../sisca/tGerarMofiAupr.js');
importarJs('../sisca/tGerarMofiAuprBv.js');

importarJs('../sisca/tUscl.js');
//migração
importarJs('../sisca/tMigr.js');

importarJs('../sisca/tEmiRecebimentos.js');
importarJs('../sisca/tEmiPagamentos.js');
importarJs('../sisca/tEmiHonorarios.js');
importarJs('../sisca/tEmiLiquidezOrcaAupr.js');
importarJs('../sisca/tEmiFaturamentos.js');
importarJs('../sisca/tEmiDiversos.js');
importarJs('../sisca/tEmiGrade.js');
importarJs('../sisca/tEmiInfo.js');
importarJs('../sisca/tEmiInVepe.js');
importarJs('../sisca/tEmiInEspr.js');
importarJs('../sisca/tEmiFecha.js');
importarJs('../sisca/tEmiOsco.js');
importarJs('../sisca/tEmiMafi.js');
importarJs('../sisca/tEmiRanqueamento.js');
importarJs('../sisca/tEmiRanqueamentoAnual.js');
importarJs('../sisca/tEmiRanqLiquidez.js');
importarJs('../sisca/tEmiCompAnual.js');
importarJs('../sisca/tEmiCompAnualAtendimento.js');
importarJs('../sisca/tEmiCompAnualUscl.js');
importarJs('../sisca/tEmiProdCria.js');
importarJs('../sisca/tEmiFechaBriefing.js');
importarJs('../sisca/tEmiFechaAtendimento.js');

importarJs('CSCLib.js');


menuAtual = new Array('','','','','');
menuAtualMarcado = new Array('','','','','');
selecionarMenuAtual = new Array('','','');

function onMenu(viMenu,vLink,execAoExibirTela)
{
	//alert(viMenu+';'+vLink);
	var iMenu = document.getElementById('li'+viMenu);
	var iMenuNivel = document.getElementById('li'+viMenu+'nivel').value;
	var iMenuOpcoid = document.getElementById('li'+viMenu+'opcoid').value;
	var iMenuIdent = document.getElementById('li'+viMenu+'ident').value;
	if (menuAtualMarcado[iMenuNivel] != '')
	{
		desmarcarMenu(menuAtualMarcado[iMenuNivel]);
	}

	menuAtualMarcado[iMenuNivel] = viMenu;
	marcarMenu(viMenu);
	
	if (menuAtual[iMenuNivel] != '')
	{
		offMenu(menuAtual[iMenuNivel]);
	}
	if (vLink != '')
	{	
		selecionarMenu(vLink,'exibirTela',execAoExibirTela);
	}
	else
	{	
		var iUl = iMenu.getElementsByTagName('ul');
		if (iUl.length > 0)
		{   
			iMenu.getElementsByTagName('ul')[0].style.paddingLeft= (parseInt(iMenuIdent) + 15) + "px";
			iMenu.getElementsByTagName('ul')[0].style.display="block";
			
			var iLi = iMenu.getElementsByTagName('ul')[0].getElementsByTagName('li');
			for (x=0; x<iLi.length;x++)
			{
				document.getElementById(iMenu.getElementsByTagName('ul')[0].getElementsByTagName('li')[x].id+'nivel').value = parseInt(iMenuNivel) + 1;
				document.getElementById(iMenu.getElementsByTagName('ul')[0].getElementsByTagName('li')[x].id+'ident').value = parseInt(iMenuIdent) + 15;				
			}			
		}
		menuAtual[iMenuNivel] = viMenu;
		exibirVisaoGeral(iMenuOpcoid);
	}
}

function offMenu(viMenu)
{
	var iMenu = document.getElementById('li'+viMenu);
	var iUl = iMenu.getElementsByTagName('ul');
	if (iUl.length > 0)
	{
		iMenu.getElementsByTagName('ul')[0].style.display="none";
	}
}

function marcarMenu(viMenu)
{
	var iMenu = document.getElementById('li'+viMenu);	
	var iMenuNivel = document.getElementById('li'+viMenu+'nivel').value;	
	var nivel = iMenuNivel+1;
	if ((menuAtualMarcado[nivel] != '') && (menuAtualMarcado[nivel] != undefined))
	{
		desmarcarMenu(menuAtualMarcado[nivel]);
	}

	if ((iMenu.className == 'mainmenu') || (iMenu.className == 'mainmenuIE'))
	{		
		document.getElementById('sp'+viMenu).style.backgroundColor = "#999999";
		document.getElementById('sp'+viMenu).style.color = "#FFFFFF";
	}
	else
	{
		document.getElementById('sp'+viMenu).style.backgroundColor = "#999999";
		document.getElementById('sp'+viMenu).style.color = "#FFFFFF";
	}	
}

function desmarcarMenu(viMenu)
{
	var iMenu = document.getElementById('li'+viMenu);
	if ((iMenu.className == 'mainmenu') || (iMenu.className == 'mainmenuIE'))
	{
		document.getElementById('sp'+viMenu).style.backgroundColor = "#DDDDDD";	
		document.getElementById('sp'+viMenu).style.color = "#000000";
	}
	else
	{
		document.getElementById('sp'+viMenu).style.backgroundColor = "#FFFFFF";	
		document.getElementById('sp'+viMenu).style.color = "#000000";		
	}	
}

////////////////////////////////////////////////////////////////////////
function selecionarMenu(vlink,acao,execAoExibirTela)
{	
	selecionarMenuAtual = new Array(vlink, acao, execAoExibirTela);
	exibirMenu();
	var oAjax = new tAjax('../../QoEAT/'+vlink.substr(1).replace('/','?objServer='));
	oAjax.addCampo('acao',acao);
	oAjax.enviar('NoExec');
	corpo.innerHTML = oAjax.retorno;

	var oAjax1 = new tAjax('../../QoEAT/acesso/login');
	oAjax1.addCampo('acao','setTela');
	oAjax1.addCampo('telaAcao',acao);	
	oAjax1.addCampo('telaLink',vlink);
	oAjax1.enviar('NoExec');	
	
	if (execAoExibirTela == undefined)
	{
		execAoExibirTela = true;
	}
	
	if (execAoExibirTela)
	{
		setTimeout("executarAoExibirTela('"+vlink+"');", 500);
	}
}

function exibirVisaoGeral(vid)
{
	var oAjax = new tAjax('login');
	oAjax.addCampo('acao', 'exibirVisaoGeral');	
	oAjax.addCampo('id', vid);
	oAjax.enviar('NoExec');
	corpo.innerHTML = oAjax.retorno;	
}

function executarAoExibirTela(vlink)
{
	var oAjaxExec = new tAjax('../../QoEAT/'+vlink.substr(1).replace('/','?objServer='));
	oAjaxExec.addCampo('acao','aoExibirTela');	
	oAjaxExec.enviar();
}

function importarJs(vNome)
{	
	document.write('<script type="text/javascript" src="'+vNome+'"></script>');
}

function exibirMenu()
{
	if (document.getElementById("divMenu").style.display != 'block')
	{
		constroiMenu();
		document.getElementById("divMenu").style.width = '200px';
		document.getElementById("divMenu").style.display = 'block';
		document.getElementById("tituloCorpo").style.display = 'none';
	}
}

function getNavegador()
{
	var nom = navigator.appName;  
	if (nom == "Microsoft Internet Explorer")
	{  
		return 'IE';
	}  
	else if (nom == "Netscape")
	{  
		return 'Netscape';  
	}  
	else 
	{  
		return 'não identificado';
	}
}

function constroiMenu()
{
	var oAjax = new tAjax('login');
	oAjax.addCampo('acao','constroiMenu');
	if (getNavegador() == 'IE')
	{
		oAjax.addCampo('navegador','IE');
	}
	else
	{
		oAjax.addCampo('navegador','Netscape');
	}
		
	/*alert('constroiMenu');*/
	oAjax.enviar('NoExec');
	divMenu.innerHTML = oAjax.retorno;
}

function avisoSistema()
{
	var oAjax = new tAjax('login');
	oAjax.addCampo('acao','avisoSistema');	
	oAjax.enviar('NoExec');
	
	if (oAjax.retorno.substring(0,8) == 'terminar')
	{
		terminar(oAjax.retorno.substring(8));
	}
	else
	{
		setTimeout("avisoSistema()", 60000);
	}
}

function terminar(tempo)
{
	if (tempo == 0)
	{
		document.location = '/QoEAT/acesso/login?acao=sair';
	}
	else
	{
		alert('O Sistema terminará em '+ tempo + ' minutos');
		tempo--;
		setTimeout("terminar("+tempo+")", 60000);
	}
}

function setAvisoSistema()
{
	var oAjax = new tAjax('login');
	oAjax.addCampo('acao','setAvisoSistema');
	oAjax.addCampo('mensagem',mensagem.value);
	oAjax.addCampo('tempoTermino',tempoTermino.value);	
	oAjax.enviar('NoExec');
}
