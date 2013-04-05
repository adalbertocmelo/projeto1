-- cofificação, lista de congestinamentos, psnr de cada congestionamento, tudo de um plano de teste, lista de mecanismos

--entrada : plano de teste, codificacao, congestao, mecanismos, metrica
--saida: psnr 

-- SQL 1 - gráfico Metrica X Congestionamento

select	vide.nome
,	codi.id as codiid
, 	tran.mode
, 	pltr.id as pltrid
,	tran.systemload
, 	avg (aval.valor) as media
from	planotrabalho pltr
, 	transmissao tran
, 	video vide
, 	codificacao codi
,	codifram cofr
, 	avaliacaoframe aval
, 	metrica metr
where   vide.id = codi.videid
and     tran.codiid = codi.id
and	tran.pltrid = pltr.id
and  	tran.id = aval.tranid
and  	metr.id = aval.metrid
and	cofr.framid = aval.framid
and 	cofr.codiid = codi.id
and     pltr.id = 7 
and 	metr.id =5
and	codi.id = 58
and	tran.systemload = 260
group by vide.nome
,	codi.id
, 	tran.mode
, 	pltr.id
,	tran.systemload
,	metr.nome
order by  tran.mode, tran.systemload

-- SQL 2 - 

select	vide.nome
,	codi.id as codiid
, 	tran.mode
, 	pltr.id as pltrid
,	tran.systemload
,	cogo.gopid
, 	avg (aval.valor) as media
from	planotrabalho pltr
, 	transmissao tran
, 	video vide
, 	codificacao codi
,	codigop cogo
,	codifram cofr
, 	avaliacaoframe aval
, 	metrica metr
where   vide.id = codi.videid
and     codi.id = tran.codiid 
and	pltr.id = tran.pltrid
and  	tran.id = aval.tranid
and  	metr.id = aval.metrid
and	cofr.framid = aval.framid
and	codi.id = cogo.codiid 
and 	codi.id = cofr.codiid 
and	cofr.gopid = cogo.gopid 
--and     pltr.id = 7 
and 	metr.id =5
and	tran.id = 45813
group by vide.nome
,	codi.id
, 	tran.mode
, 	pltr.id
,	tran.systemload
,	cogo.gopid
order by  tran.mode, tran.systemload

select * from codifram where codiid =1

select * from codigop where codiid = 1

select * from codificacao where id =1


