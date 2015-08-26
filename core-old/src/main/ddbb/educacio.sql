declare
max_id_usuari number(10,0);
max_id_grup   number(10,0);
max_id_usugru	number(10,0);
max_id_xarxes	number(10,0);
max_id_maquin	number(10,0);
max_id_domcor	number(10,0);
max_id_llicor	number(10,0);
max_id_dispat	number(10,0);
max_id_aplica	number(10,0);
max_id_roles	number(10,0);
maq_id_nul  	number(10,0);
id_aplica_oposedu04 number (10,0);
id_aplica_gestib number (10,0);
dco_id_educacio number(10,0);
begin


select apl_id into id_aplica_oposedu04 from sc_aplica where apl_codi = 'OPOSEDU04';
select apl_id into id_aplica_gestib from sc_aplica where apl_codi = 'GESTIB';

select max(usu_id) into max_id_usuari from sc_usuari@seyconpre;
select max(gru_id) into max_id_grup from sc_grups@seyconpre;
select max(ugr_id) into max_id_usugru from sc_usugru@seyconpre;
select max(xar_id) into max_id_xarxes from sc_xarxes@seyconpre;
select max(maq_id) into max_id_maquin from sc_maquin@seyconpre;
select max(dis_id) into max_id_dispat from sc_dispat@seyconpre;
select max(apl_id) into max_id_aplica from sc_aplica@seyconpre;
select max(dco_id) into max_id_domcor from sc_domcor@seyconpre;
select max(lco_id) into max_id_llicor from sc_llicor@seyconpre;
select max(rol_id) into max_id_roles from sc_roles@seyconpre;

-- se insertan los dispatchers
insert into sc_dispat@seyconpre
(DIS_ID,DIS_CODI,DIS_NOMCLA,DIS_URL,DIS_PARAM0,DIS_PARAM1,DIS_PARAM2,DIS_PARAM3,DIS_PARAM4,
DIS_PARAM5,DIS_PARAM6,DIS_PARAM7,DIS_PARAM8,DIS_PARAM9,DIS_BASROL,DIS_SEGUR,DIS_URL2,DIS_GRUPS,
DIS_TIPUSU)
(
select max_id_dispat + DIS_ID,DIS_CODI,DIS_NOMCLA,DIS_URL,DIS_PARAM0,DIS_PARAM1,DIS_PARAM2,DIS_PARAM3,DIS_PARAM4,
DIS_PARAM5,DIS_PARAM6,DIS_PARAM7,DIS_PARAM8,DIS_PARAM9,DIS_BASROL,DIS_SEGUR, null, null, null
from sc_dispat
);

--se insertan las aplicaciones
insert into sc_aplica@seyconpre
(APL_ID,APL_CODI,APL_NOM,APL_DIRFON,APL_DIRECT,APL_BD,APL_IDCONTACT)
(
select max_id_aplica + APL_ID,APL_CODI,APL_NOM,APL_DIRFON,APL_DIRECT,APL_BD, null
from sc_aplica
where (not apl_codi = 'GESTIB') and (not apl_codi = 'OPOSEDU04')
);

--se insertan los roles
insert into sc_roles@seyconpre
(ROL_ID,ROL_NOM,ROL_BD,ROL_DESCRI,ROL_IDAPL,ROL_DEFECT,ROL_CONTRA,ROL_DOMAPP,ROL_TIPDOM,ROL_GRUP, ROL_IDDISPAT)
(
SELECT MAX_ID_ROLES + ROL_ID,ROL_NOM,ROL_BD,ROL_DESCRI,max_id_aplica + ROL_IDAPL,ROL_DEFECT,ROL_CONTRA,null,null,null,max_id_dispat
from sc_roles
where (not rol_idapl = id_aplica_gestib) and (not rol_idapl = id_aplica_oposedu04)
);

insert into sc_roles@seyconpre
(ROL_ID,ROL_NOM,ROL_BD,ROL_DESCRI,ROL_IDAPL,ROL_DEFECT,ROL_CONTRA,ROL_DOMAPP,ROL_TIPDOM,ROL_GRUP, ROL_IDDISPAT)
(
SELECT MAX_ID_ROLES + ROL_ID,ROL_NOM,ROL_BD,ROL_DESCRI,id_aplica_gestib,ROL_DEFECT,ROL_CONTRA,null,null,null,max_id_dispat
from sc_roles
where (rol_nom = 'GESTIB')
);

insert into sc_roles@seyconpre
(ROL_ID,ROL_NOM,ROL_BD,ROL_DESCRI,ROL_IDAPL,ROL_DEFECT,ROL_CONTRA,ROL_DOMAPP,ROL_TIPDOM,ROL_GRUP, ROL_IDDISPAT)
(
SELECT MAX_ID_ROLES + ROL_ID,ROL_NOM,ROL_BD,ROL_DESCRI,id_aplica_oposedu04,ROL_DEFECT,ROL_CONTRA,null,null,null,max_id_dispat
from sc_roles
where (rol_nom = 'OPOSEDU04')
);

-- se actualizan los dispatchers de los roles
update sc_roles@seyconpre 
set rol_iddispat = (SELECT DIS_ID FROM sc_dispat@seyconpre WHERE DIS_CODI = ROL_BD)
where rol_id > MAX_ID_ROLES;

--es creen les xarxes
insert into sc_xarxes@seyconpre
(XAR_ID, XAR_CODI,XAR_ADRIP,XAR_DESCRI,XAR_MASIP,XAR_NORM,XAR_PARDHC)
(
select max_id_xarxes + XAR_ID, XAR_CODI ,XAR_ADRIP,XAR_DESCRI,XAR_MASIP,XAR_NORM,XAR_PARDHC
from sc_xarxes
where 
xar_codi not in (select xar_codi from sc_xarxes@seyconpre)
);

--es creen les màquines
insert into sc_maquin@seyconpre
(MAQ_ID,MAQ_NOM,MAQ_SISOPE,MAQ_ADRIP,MAQ_ADRMAC,MAQ_DESCRI,MAQ_PARDHC,MAQ_CORREU,MAQ_OFIMAT,MAQ_IDXAR,MAQ_ALIAS)
(
select MAQ_ID + max_id_maquin,MAQ_NOM,MAQ_SISOPE,MAQ_ADRIP,MAQ_ADRMAC,MAQ_DESCRI,MAQ_PARDHC,MAQ_CORREU,MAQ_OFIMAT,MAQ_IDXAR,MAQ_ALIAS
from sc_maquin
where 
maq_idxar not in (select xar_id from sc_xarxes@seyconpre)
and
maq_nom not in (select maq_nom from sc_maquin@seyconpre)
);

--es creen els grups
insert into sc_grups@seyconpre
 (GRU_ID, GRU_CODI,GRU_DESCRI,GRU_IDMAQ,GRU_QUOTA,GRU_UNIOFI, GRU_OBSOLET) 
(
select max_id_grup + GRU_ID, GRU_CODI,GRU_DESCRI,max_id_maquin,GRU_QUOTA,GRU_UNIOFI, 'N'
from sc_grups
where 
gru_codi not in (select gru_codi from sc_grups@seyconpre)
--and
--GRU_IDMAQ not in (select GRU_IDMAQ from sc_grups@seyconpre)
);

update sc_grups@seyconpre
set gru_idmaq =
(
select maq_seycon.maq_id
from sc_maquin maq, sc_maquin@seyconpre maq_seycon, sc_grups gru
where 
maq.maq_nom = maq_seycon.maq_nom and 
gru.gru_idmaq = maq.maq_id and
gru.gru_codi = gru_codi and
gru_id > max_id_grup
);

--s'inserten els dominis de correu
insert into sc_domcor@seyconpre
 (DCO_ID,DCO_CODI,DCO_DESCRI,DCO_OBSOLET)
(
select max_id_domcor + DCO_ID,DCO_CODI,DCO_DESCRI,'N'
from sc_domcor
);


--es creen les llistes de correu
insert into sc_llicor@seyconpre
 (LCO_ID,LCO_DESCRI,LCO_NOM,LCO_IDDCO)
(
select max_id_llicor + LCO_ID,LCO_DESCRI,LCO_NOM, null
from sc_llicor
where 
lco_nom not in (select lco_nom from sc_llicor@seyconpre)
);

select dco_id into dco_id_educacio
from sc_domcor@seyconpre
where 
dco_codi = 'educacio';

update sc_llicor@seyconpre
set lco_iddco = dco_id_educacio
where lco_id > max_id_llicor;

select maq_id into maq_id_nul
from sc_maquin@seyconpre 
where maq_nom = 'nul';

--se crean los usuarios
insert into sc_usuari@seyconpre
(USU_ID,USU_CODI,USU_NOM,USU_PRILLI,USU_SEGLLI,USU_NOMCUR,USU_UNOFES,USU_USUMOD,
USU_DATMOD,USU_DATCRE,USU_USUCRE,USU_IDDCO,USU_IDGRU,USU_IDMAQ,USU_IDMACO,
USU_IDMAPR,USU_ACTIU,USU_ALCOAN,USU_PEND,USU_QUOTA,USU_MULSES,USU_COMENT,
USU_TIPUSU)
(
select distinct
max_id_usuari + USU_ID,USU_CODI,USU_NOM,USU_PRILLI,USU_SEGLLI,USU_NOMCUR,USU_UNOFES,USU_USUMOD,
USU_DATMOD,USU_DATCRE,USU_USUCRE, 
dco_id_educacio
,max_id_grup + USU_IDGRU,maq_id_nul,maq_id_nul,
maq_id_nul,USU_ACTIU,USU_ALCOAN,USU_PEND,USU_QUOTA,USU_MULSES,USU_COMENT,
USU_TIPUSU
from sc_usuari
where  
not usu_codi like 'c%'
and
not usu_codi = 'u80925'
and
not usu_nomcur is null
and 
USU_IDGRU in (select gru_id from sc_grups@seyconpre)
);

insert into sc_usuari@seyconpre
(USU_ID,USU_CODI,USU_NOM,USU_PRILLI,USU_SEGLLI,USU_NOMCUR,USU_UNOFES,USU_USUMOD,
USU_DATMOD,USU_DATCRE,USU_USUCRE,USU_IDDCO,USU_IDGRU,USU_IDMAQ,USU_IDMACO,
USU_IDMAPR,USU_ACTIU,USU_ALCOAN,USU_PEND,USU_QUOTA,USU_MULSES,USU_COMENT,
USU_TIPUSU)
(
select distinct
max_id_usuari + USU_ID,USU_CODI,USU_NOM,USU_PRILLI,USU_SEGLLI,USU_NOMCUR,USU_UNOFES,USU_USUMOD,
USU_DATMOD,USU_DATCRE,USU_USUCRE, 
null
,max_id_grup + USU_IDGRU,maq_id_nul,maq_id_nul,
maq_id_nul,USU_ACTIU,USU_ALCOAN,USU_PEND,USU_QUOTA,USU_MULSES,USU_COMENT,
USU_TIPUSU
from sc_usuari
where  
not usu_codi like 'c%'
and
not usu_codi = 'u80925'
and
usu_nomcur is null
and 
USU_IDGRU in (select gru_id from sc_grups@seyconpre)
);


insert into sc_usuari@seyconpre
(USU_ID,USU_CODI,USU_NOM,USU_PRILLI,USU_SEGLLI,USU_NOMCUR,USU_UNOFES,USU_USUMOD,
USU_DATMOD,USU_DATCRE,USU_USUCRE,USU_IDDCO,USU_IDGRU,USU_IDMAQ,USU_IDMACO,
USU_IDMAPR,USU_ACTIU,USU_ALCOAN,USU_PEND,USU_QUOTA,USU_MULSES,USU_COMENT,
USU_TIPUSU)
(
select distinct
max_id_usuari + USU_ID,USU_CODI,USU_NOM,USU_PRILLI,USU_SEGLLI,USU_NOMCUR,USU_UNOFES,USU_USUMOD,
USU_DATMOD,USU_DATCRE,USU_USUCRE, 
dco_id_educacio
,null,maq_id_nul,maq_id_nul,
maq_id_nul,USU_ACTIU,USU_ALCOAN,USU_PEND,USU_QUOTA,USU_MULSES,USU_COMENT,
USU_TIPUSU
from sc_usuari
where  
not usu_codi like 'c%'
and
not usu_codi = 'u80925'
and
not usu_nomcur is null
and 
USU_IDGRU not in (select gru_id from sc_grups@seyconpre)
);

insert into sc_usuari@seyconpre
(USU_ID,USU_CODI,USU_NOM,USU_PRILLI,USU_SEGLLI,USU_NOMCUR,USU_UNOFES,USU_USUMOD,
USU_DATMOD,USU_DATCRE,USU_USUCRE,USU_IDDCO,USU_IDGRU,USU_IDMAQ,USU_IDMACO,
USU_IDMAPR,USU_ACTIU,USU_ALCOAN,USU_PEND,USU_QUOTA,USU_MULSES,USU_COMENT,
USU_TIPUSU)
(
select distinct
max_id_usuari + USU_ID,USU_CODI,USU_NOM,USU_PRILLI,USU_SEGLLI,USU_NOMCUR,USU_UNOFES,USU_USUMOD,
USU_DATMOD,USU_DATCRE,USU_USUCRE, 
null
,null,maq_id_nul,maq_id_nul,
maq_id_nul,USU_ACTIU,USU_ALCOAN,USU_PEND,USU_QUOTA,USU_MULSES,USU_COMENT,
USU_TIPUSU
from sc_usuari
where  
not usu_codi like 'c%'
and
not usu_codi = 'u80925'
and
usu_nomcur is null
and 
USU_IDGRU not in (select gru_id from sc_grups@seyconpre)
);

-- se insertan las asociaciones entre usuario y grupo
INSERT INTO SC_USUGRU@seyconpre
(UGR_ID, UGR_IDGRU, UGR_IDUSU)
(
select rownum, max_id_grup + UGR_IDGRU, max_id_usuari + ugr_idusu
from sc_usugru
where 
ugr_idusu not in 
(
select usu_id
from sc_usuari
where 
usu_codi like 'c%'
or 
usu_codi = 'u80925'
)
);
-- se insertan las asociaciones entre usuario y grupo
INSERT INTO SC_USUGRU@seyconpre
(UGR_ID, UGR_IDGRU, UGR_IDUSU)
(
select rownum, max_id_grup + UGR_IDGRU, usu.usu_id
from sc_usugru ugr, sc_usuari@seyconpre usu
where 
ugr.ugr_idusu in 
(
select usu_id
from sc_usuari
where 
usu_codi like 'c%'
)
and
ugr.ugr_idusu = (
select usu_id
from sc_usuari
where 
usu_codi = usu.usu_codi
)
);


--se insertan las asociaciones entre roles y usuarios
insert into sc_rolusu@seyconpre
(RLU_ID, RLU_IDROL,RLU_IDUSU,RLU_ROLUSU_GRU,RLU_VALDOM,RLU_TIPDOM,RLU_ADMAPP)
(
-- hay que arreglar esto puesto que los usuarios no se crean sino que se actualizan
select rownum, MAX_ID_ROLES + RLU_IDROL,max_id_usuari + RLU_IDUSU,null,null,'SENSE_DOMINI',null
from sc_rolusu
where 
rlu_idusu in 
(
select usu_id
from sc_usuari
where 
not usu_codi = 'u80925'
and
not usu_codi like 'c%'
)
);

-- se insertan las asociaciones entre usuario y rol
INSERT INTO sc_rolusu@seyconpre
(RLU_ID, RLU_IDROL,RLU_IDUSU,RLU_ROLUSU_GRU,RLU_VALDOM,RLU_TIPDOM,RLU_ADMAPP)
(
select rownum, MAX_ID_ROLES + RLU_IDROL, usu.usu_id,null,null,'SENSE_DOMINI',null
from sc_rolusu rlu, sc_usuari@seyconpre usu
where 
rlu.rlu_idusu in 
(
select usu_id
from sc_usuari
where 
usu_codi like 'c%'
)
and
rlu.rlu_idusu = 
(
select usu_id
from sc_usuari
where 
usu_codi = usu.usu_codi
);

end;
/
