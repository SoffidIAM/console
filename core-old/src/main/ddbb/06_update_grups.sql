alter table sc_grups modify gru_descri varchar(100 char);
alter table sc_grups modify gru_codi varchar(20 char);
alter table sc_tasque modify tas_grup varchar(20 char);

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'dgagric'
)
where gr.gru_codi like 'dgagri%' and not gr.gru_codi = 'dgagric';

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'dgadmedu'
)
where gr.gru_codi = 'dgadmeduie';

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'dgconsum'
)
where gr.gru_codi like 'dgconsum%' and not gr.gru_codi = 'dgconsum';

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'dgfarmacia'
)
where gr.gru_codi like 'dgfarma%' and not gr.gru_codi = 'dgfarmacia';

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'dgindust'
)
where gr.gru_codi = 'dgindustmi';

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'dgesport'
)
where gr.gru_codi like 'dgesport%' and not gr.gru_codi = 'dgesport';

Insert into SC_GRUPS (GRU_ID,GRU_OBSOLET,GRU_CODI,GRU_DESCRI,GRU_IDMAQ,GRU_QUOTA,GRU_UNIOFI,GRU_PARE,GRU_ADMINISTRADOR,GRU_TIPUS) values (1008709,'N','CAIB','Comunitat Autònoma de les Illes Balears',null,101,null,null,null,null);
Insert into SC_GRUPS (GRU_ID,GRU_OBSOLET,GRU_CODI,GRU_DESCRI,GRU_IDMAQ,GRU_QUOTA,GRU_UNIOFI,GRU_PARE,GRU_ADMINISTRADOR,GRU_TIPUS) values (1008711,'N','CONS_ECOHISINNO','Conselleria d''Economia, Hisenda i Innovació',null,0,null,1008709,null,null);
Insert into SC_GRUPS (GRU_ID,GRU_OBSOLET,GRU_CODI,GRU_DESCRI,GRU_IDMAQ,GRU_QUOTA,GRU_UNIOFI,GRU_PARE,GRU_ADMINISTRADOR,GRU_TIPUS) values (1008710,'N','CONS_PRESI','Conselleria de Presidència',null,0,null,1008709,null,null);
Insert into SC_GRUPS (GRU_ID,GRU_OBSOLET,GRU_CODI,GRU_DESCRI,GRU_IDMAQ,GRU_QUOTA,GRU_UNIOFI,GRU_PARE,GRU_ADMINISTRADOR,GRU_TIPUS) values (1008712,'N','CONS_MODORDTERR','Conselleria de Mobilitat i Ordenació del Territori',null,null,null,1008709,null,null);
Insert into SC_GRUPS (GRU_ID,GRU_OBSOLET,GRU_CODI,GRU_DESCRI,GRU_IDMAQ,GRU_QUOTA,GRU_UNIOFI,GRU_PARE,GRU_ADMINISTRADOR,GRU_TIPUS) values (1008713,'N','CONS_TUR','Conselleria de Turisme',null,null,null,1008709,null,null);
Insert into SC_GRUPS (GRU_ID,GRU_OBSOLET,GRU_CODI,GRU_DESCRI,GRU_IDMAQ,GRU_QUOTA,GRU_UNIOFI,GRU_PARE,GRU_ADMINISTRADOR,GRU_TIPUS) values (1008714,'N','CONS_EDUCUL','Conselleria d''Educació i Cultura',null,null,null,1008709,null,null);
Insert into SC_GRUPS (GRU_ID,GRU_OBSOLET,GRU_CODI,GRU_DESCRI,GRU_IDMAQ,GRU_QUOTA,GRU_UNIOFI,GRU_PARE,GRU_ADMINISTRADOR,GRU_TIPUS) values (1008715,'N','CONS_SALCON','Conselleria de Salut i Consum',null,null,null,1008709,null,null);
Insert into SC_GRUPS (GRU_ID,GRU_OBSOLET,GRU_CODI,GRU_DESCRI,GRU_IDMAQ,GRU_QUOTA,GRU_UNIOFI,GRU_PARE,GRU_ADMINISTRADOR,GRU_TIPUS) values (1008716,'N','CONS_MEDIAM','Conselleria de Medi Ambient',null,null,null,1008709,null,null);
Insert into SC_GRUPS (GRU_ID,GRU_OBSOLET,GRU_CODI,GRU_DESCRI,GRU_IDMAQ,GRU_QUOTA,GRU_UNIOFI,GRU_PARE,GRU_ADMINISTRADOR,GRU_TIPUS) values (1008719,'N','CONS_SOCPROMINM','Conselleria d''Afers Socials, Promoció i Immigració',null,null,null,1008709,null,null);
Insert into SC_GRUPS (GRU_ID,GRU_OBSOLET,GRU_CODI,GRU_DESCRI,GRU_IDMAQ,GRU_QUOTA,GRU_UNIOFI,GRU_PARE,GRU_ADMINISTRADOR,GRU_TIPUS) values (1008720,'N','CONS_HABIOPUB','Conselleria d''Habitatge i Obres Públiques',null,null,null,1008709,null,null);
Insert into SC_GRUPS (GRU_ID,GRU_OBSOLET,GRU_CODI,GRU_DESCRI,GRU_IDMAQ,GRU_QUOTA,GRU_UNIOFI,GRU_PARE,GRU_ADMINISTRADOR,GRU_TIPUS) values (1008721,'N','CONS_TREBFORM','Conselleria de Treball i Formació',null,null,null,1008709,null,null);
Insert into SC_GRUPS (GRU_ID,GRU_OBSOLET,GRU_CODI,GRU_DESCRI,GRU_IDMAQ,GRU_QUOTA,GRU_UNIOFI,GRU_PARE,GRU_ADMINISTRADOR,GRU_TIPUS) values (1008722,'N','CONS_COMINDENE','Conselleria de Comerç, Indústria i Energia',null,null,null,1008709,null,null);
Insert into SC_GRUPS (GRU_ID,GRU_OBSOLET,GRU_CODI,GRU_DESCRI,GRU_IDMAQ,GRU_QUOTA,GRU_UNIOFI,GRU_PARE,GRU_ADMINISTRADOR,GRU_TIPUS) values (1008723,'N','CONS_AGRIPESCA','Conselleria d''Agricultura i Pesca',null,null,null,1008709,null,null);
Insert into SC_GRUPS (GRU_ID,GRU_OBSOLET,GRU_CODI,GRU_DESCRI,GRU_IDMAQ,GRU_QUOTA,GRU_UNIOFI,GRU_PARE,GRU_ADMINISTRADOR,GRU_TIPUS) values (1008724,'N','CONS_INTERIOR','Conselleria d''Interior',null,null,null,1008709,null,null);
Insert into SC_GRUPS (GRU_ID,GRU_OBSOLET,GRU_CODI,GRU_DESCRI,GRU_IDMAQ,GRU_QUOTA,GRU_UNIOFI,GRU_PARE,GRU_ADMINISTRADOR,GRU_TIPUS) values (1008725,'N','CONS_ESPJOV','Conselleria d''Esports i Joventut',null,null,null,1008709,null,null);

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'CONS_EDUCUL'
)
where gr.gru_codi = 'dgadmedu';

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'CONS_AGRIPESCA'
)
where gr.gru_codi = 'dgagric';

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'CONS_HABIOPUB'
)
where gr.gru_codi = 'dgarquit';

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'CONS_SALCON'
)
where gr.gru_codi = 'dgaval';

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'CONS_COMINDENE'
)
where gr.gru_codi = 'dgcomerc';

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'CONS_COMINDENE'
)
where gr.gru_codi = 'dgconsum';

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'CONS_SOCPROMINM'
)
where gr.gru_codi = 'dgcooper';

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'CONS_MEDIAM'
)
where gr.gru_codi = 'dgcostes';

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'CONS_EDUCUL'
)
where gr.gru_codi = 'dgcultur';

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'CONS_INTERIOR'
)
where gr.gru_codi = 'dgemerge';

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'CONS_ESPJOV'
)
where gr.gru_codi = 'dgesport';

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'CONS_SALCON'
)
where gr.gru_codi = 'dgfarmacia';

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'CONS_TREBFORM'
)
where gr.gru_codi = 'dgform';

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'CONS_EDUCUL'
)
where gr.gru_codi = 'dgfpie';

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'CONS_COMINDENE'
)
where gr.gru_codi = 'dgindust';

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'CONS_ECOHISINNO'
)
where gr.gru_codi = 'dginnova';

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'CONS_MEDIAM'
)
where gr.gru_codi = 'dgqal';

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'CONS_ECOHISINNO'
)
where gr.gru_codi = 'dgproeco';

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'CONS_PRESI'
)
where gr.gru_codi = 'dgrelext';


update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'CONS_ECOHISINNO'
)
where gr.gru_codi = 'dgrdi';

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'CONS_ECOHISINNO'
)
where gr.gru_codi = 'dgrecap';

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'CONS_COMINDENE'
)
where gr.gru_codi = 'energia';

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'CONS_ECOHISINNO'
)
where gr.gru_codi = 'dgtic';

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'CONS_ESPJOV'
)
where gr.gru_codi = 'dgjovent';

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'CONS_INTERIOR'
)
where gr.gru_codi = 'dgperson';

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'CONS_INTERIOR'
)
where gr.gru_codi = 'dginteri';

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'CONS_SALCON'
)
where gr.gru_codi = 'dgconsum';

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'CONS_EDUCUL'
)
where gr.gru_codi = 'dgplacen';

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'CONS_EDUCUL'
)
where gr.gru_codi = 'dgcultur';

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'CONS_ESPJOV'
)
where gr.gru_codi = 'dgesport';

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'CONS_AGRIPESCA'
)
where gr.gru_codi = 'dgagric';

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'CONS_COMINDENE'
)
where gr.gru_codi = 'dgindust';

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'CONS_AGRIPESCA'
)
where gr.gru_codi = 'dgpesca';

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'CONS_MODORDTERR'
)
where gr.gru_codi = 'dgoterri';

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'CONS_MEDIAM'
)
where gr.gru_codi = 'dgreghid';

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'CONS_MEDIAM'
)
where gr.gru_codi = 'dgmambie';

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'CONS_MEDIAM'
)
where gr.gru_codi = 'dgcostes';

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'CONS_SOCPROMINM'
)
where gr.gru_codi = 'dgmenors';

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'CONS_ECOHISINNO'
)
where gr.gru_codi = 'dgeconom';

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'CONS_ECOHISINNO'
)
where gr.gru_codi = 'dgpresup';

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'CONS_TREBFORM'
)
where gr.gru_codi = 'dgtrebal';

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'CONS_TREBFORM'
)
where gr.gru_codi = 'dgform';

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'CONS_ECOHISINNO'
)
where gr.gru_codi = 'dginnova';

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'CONS_PRESI'
)
where gr.gru_codi = 'dgproject';

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'CONS_ESPJOV'
)
where gr.gru_codi = 'dgjoventsu';

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'CONS_PRESI'
)
where gr.gru_codi = 'rinforma';

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'CONS_TREBFORM'
)
where gr.gru_codi = 'dgslab';

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'CONS_HABIOPUB'
)
where gr.gru_codi = 'dgarquit';

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'CONS_EDUCUL'
)
where gr.gru_codi = 'dgpdocen';

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'CONS_ECOHISINNO'
)
where gr.gru_codi = 'dgtribut';

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'CONS_SALCON'
)
where gr.gru_codi = 'dgfarmacia';

update sc_grups gr set gr.gru_pare = 
(
select pare.gru_id
from sc_grups pare
where pare.gru_codi = 'CONS_SALCON'
)
where gr.gru_codi = 'dgaval';

Insert into SC_TIPUSUO (TUO_ID,TUO_CODI,TUO_DESC,TUO_PARE) values (1,'SERVEI','Servei',null);
Insert into SC_TIPUSUO (TUO_ID,TUO_CODI,TUO_DESC,TUO_PARE) values (2,'SECCIO','Secció',null);
Insert into SC_TIPUSUO (TUO_ID,TUO_CODI,TUO_DESC,TUO_PARE) values (3,'NEGOCIAT','Negociat',null);
Insert into SC_TIPUSUO (TUO_ID,TUO_CODI,TUO_DESC,TUO_PARE) values (4,'GRUP_TREBALL','Grup de treball',null);
Insert into SC_TIPUSUO (TUO_ID,TUO_CODI,TUO_DESC,TUO_PARE) values (5,'CENTRE_SALUT','Centre de salut',null);
Insert into SC_TIPUSUO (TUO_ID,TUO_CODI,TUO_DESC,TUO_PARE) values (6,'UNITAT_BASICA_SALUT','Unitat bàsica de salut',null);
Insert into SC_TIPUSUO (TUO_ID,TUO_CODI,TUO_DESC,TUO_PARE) values (7,'ESCOLA_PRIMARIA','Escola de primària',null);
Insert into SC_TIPUSUO (TUO_ID,TUO_CODI,TUO_DESC,TUO_PARE) values (8,'INSTITUT_SECUNDARIA','Institut de secundària',null);
Insert into SC_TIPUSUO (TUO_ID,TUO_CODI,TUO_DESC,TUO_PARE) values (9,'CONSELLERIA','Conselleria',null);
Insert into SC_TIPUSUO (TUO_ID,TUO_CODI,TUO_DESC,TUO_PARE) values (10,'DIRECCIO_GENERAL','Direcció general',null);

update sc_grups set gru_tipus = (select tuo_id from sc_tipusuo where tuo_codi = 'DIRECCIO_GENERAL')
where 
gru_codi = 'dgagric' or gru_codi = 'dgadmedu' or gru_codi = 'dgadmeduie' or 
gru_codi = 'dgconsum' or gru_codi = 'dgfarmacia' or 
gru_codi = 'dgindust' or gru_codi = 'dgindustmi' or gru_codi = 'dgesport' or 
gru_codi = 'dgadmedu' or gru_codi = 'dgagric' or gru_codi = 'dgarquit' or gru_codi = 'dgaval' or 
gru_codi = 'dgcomerc' or gru_codi = 'dgconsum' or gru_codi = 'dgcooper' or gru_codi = 'dgcostes' or 
gru_codi = 'dgcultur' or gru_codi = 'dgemerge' or gru_codi = 'dgesport' or gru_codi = 'dgfarmacia' or 
gru_codi = 'dgform' or gru_codi = 'dgfpie' or gru_codi = 'dgindust' or gru_codi = 'dginnova' or 
gru_codi = 'dgqal' or gru_codi = 'dgproeco' or gru_codi = 'dgrelext' or gru_codi = 'dgrdi' or 
gru_codi = 'dgrecap' or gru_codi = 'energia' or gru_codi = 'dgtic' or gru_codi = 'dgjovent' or 
gru_codi = 'dgperson' or gru_codi = 'dginteri' or gru_codi = 'dgconsum' or gru_codi = 'dgplacen' or 
gru_codi = 'dgcultur' or gru_codi = 'dgesport' or gru_codi = 'dgagric' or gru_codi = 'dgindust' or 
gru_codi = 'dgpesca' or gru_codi = 'dgoterri' or gru_codi = 'dgreghid' or gru_codi = 'dgmambie' or 
gru_codi = 'dgcostes' or gru_codi = 'dgmenors' or gru_codi = 'dgeconom' or gru_codi = 'dgpresup' or 
gru_codi = 'dgtrebal' or gru_codi = 'dgform' or gru_codi = 'dginnova' or gru_codi = 'dgproject' or 
gru_codi = 'dgjoventsu' or gru_codi = 'rinforma' or gru_codi = 'dgslab' or gru_codi = 'dgarquit' or 
gru_codi = 'dgpdocen' or gru_codi = 'dgtribut' or gru_codi = 'dgfarmacia' or gru_codi = 'dgaval';

update sc_grups 
set  gru_tipus = (select tuo_id from sc_tipusuo where tuo_codi = 'CONSELLERIA')
where 
gru_codi like 'CONS_%';
