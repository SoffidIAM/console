alter trigger SC_ROLES_AUTO_CHECK disable;
alter trigger SC_AUS_ALTA disable;
alter trigger SC_AUS_TAS_TR disable;
alter trigger SC_EXTLCO_DEL disable;
alter trigger SC_EXTLCO_UPD disable;
alter trigger SC_GIM_TAS_TR disable;
alter trigger SC_GRU_TAS_TR disable;
alter trigger SC_GRUPS_UPD disable;
alter trigger SC_IMP_TAS_TR disable;
alter trigger SC_LCOLCO_DEL disable;
alter trigger SC_LCOLCO_UPD disable;
alter trigger SC_LLICOR_DEL disable;
alter trigger SC_LLICOR_UPD disable;
alter trigger SC_MAQ_TAS_TR disable;
alter trigger SC_PLU_ALTA disable;
alter trigger SC_PLU_TAS_TR disable;
alter trigger SC_RLU_ALTA disable;
alter trigger SC_RLU_AUTO_CHECK disable;
alter trigger SC_RLU_TAS_TR disable;
alter trigger SC_ROLUSU_AUDITO disable;
alter trigger SC_UGR_ALTA disable;
alter trigger SC_UGR_AUTO_CHECK disable;
alter trigger SC_UGR_TAS_TR disable;
alter trigger SC_UIM_ALTA disable;
alter trigger SC_UIM_TAS_TR disable;
alter trigger SC_USU_ALTA disable;
alter trigger SC_USU_TAS_TR disable;
alter trigger SC_USU_UPD disable;
alter trigger SC_USUARI_AUDITO disable;
alter trigger SC_USUARI_AUTO_CHECK disable;
alter trigger SC_USUARI_LCO_UPD disable;
alter trigger SC_USUARI_LCO_UPD2 disable;
alter trigger SC_USUGRU_AUDITO disable;
alter trigger SC_USULCO_DEL disable;
alter trigger SC_USULCO_UPD disable;
alter trigger SC_XAR_TAS_TR disable;
alter trigger SC_ROLES_UPD disable;

ALTER TABLE SC_AUDITO DROP CONSTRAINT AUD_PK;

ALTER TABLE SC_TASQUE DROP CONSTRAINT TAS_PK;

DECLARE 
	SEQ INTEGER;
BEGIN
	SELECT SC_TAS_SEQ.NEXTVAL INTO SEQ FROM DUAL;
	DELETE SC_TASQUE WHERE TAS_ID >= SEQ;
END;
/

DELETE SC_TASQUE WHERE TAS_ID IN (SELECT TAS_ID FROM SC_TASQUE GROUP BY TAS_ID HAVING COUNT(TAS_ID) > 1); 

--
--SC_PETFAR[
alter table sc_petfar add  pef_sigref varchar2(128 char);
alter table sc_petfar add  pef_docref varchar2(128 char);
--]
--
-- SC_USULCO:   
-- ULC_ID     NUMBER(10) NOT NULL
-- [
ALTER TABLE SC_USULCO ADD ulc_id NUMBER(10);
UPDATE SC_USULCO SET ulc_id = ROWNUM;
ALTER TABLE SC_USULCO MODIFY ulc_id NOT NULL;
-- ]

-- SC_USUIMP :  
-- UIM_ID     NUMBER(10)  
-- [
ALTER TABLE SC_USUIMP ADD UIM_ID NUMBER(10);
UPDATE SC_USUIMP SET UIM_ID = ROWNUM;
ALTER TABLE SC_USUIMP MODIFY UIM_ID NOT NULL;
-- ]

-- SC_USUGRU :
-- UGR_ID      NUMBER(10)                            NULL,
-- [
ALTER TABLE SC_USUGRU ADD UGR_ID NUMBER(10);
UPDATE SC_USUGRU SET UGR_ID = ROWNUM;
ALTER TABLE SC_USUGRU ADD CONSTRAINT UGR_UK_ID UNIQUE (UGR_ID  );
ALTER TABLE SC_USUGRU MODIFY UGR_ID NOT NULL;
-- ]

-- SC_TIPDAD:
-- TDA_ID     NUMBER(10)                         NOT NULL
-- [
ALTER TABLE SC_TIPDAD ADD TDA_ID NUMBER(10);
UPDATE SC_TIPDAD SET TDA_ID = ROWNUM;
ALTER TABLE SC_TIPDAD ADD CONSTRAINT TDA_UK_ID UNIQUE (TDA_ID  );
-- ]

-- SC_TARCPD:
-- TCP_ID     NUMBER(10)
-- [
ALTER TABLE SC_TARCPD ADD TCP_ID NUMBER(10);
UPDATE SC_TARCPD SET TCP_ID = ROWNUM;
ALTER TABLE SC_TARCPD MODIFY TCP_ID NOT NULL;
ALTER TABLE SC_TARCPD DROP COLUMN TCP_FOTO;
ALTER TABLE SC_TARCPD ADD (TCP_FOTO VARCHAR2(50 char));
-- ]

-- SC_ROLUSU :
-- RLU_ROLUSU_GRU  NUMBER(10)                        NULL,
-- [
ALTER TABLE SC_ROLUSU ADD RLU_ROLUSU_GRU NUMBER(10);
ALTER TABLE SC_ROLUSU ADD RLU_ADMAPP NUMBER(10);
ALTER TABLE SC_ROLUSU ADD (RLU_VALDOM NUMBER(10));
ALTER TABLE SC_ROLUSU ADD (RLU_TIPDOM VARCHAR2(20 char));
UPDATE SC_ROLUSU
SET RLU_TIPDOM = 'SENSE_DOMINI'
WHERE RLU_TIPDOM IS NULL;
ALTER TABLE SC_ROLUSU MODIFY RLU_TIPDOM NOT NULL;
-- ]

-- RLU_ID          NUMBER(10)                    NOT NULL
-- [
ALTER TABLE SC_ROLUSU ADD RLU_ID NUMBER(10);
UPDATE SC_ROLUSU SET RLU_ID = ROWNUM;
-- ]


-- SC_ROLFIT : 
-- RLF_ID     NUMBER(10)    
-- [
ALTER TABLE SC_ROLFIT ADD RLF_ID NUMBER(10);
UPDATE SC_ROLFIT SET RLF_ID = ROWNUM;
ALTER TABLE SC_ROLFIT MODIFY RLF_ID NOT NULL;
-- ]

-- SC_ROLES:
-- ROL_BD      VARCHAR2(50 CHAR)  -> ROL_IDDISPAT  NUMBER(10)   
-- [
ALTER TABLE SC_ROLES ADD ROL_IDDISPAT NUMBER(10);
UPDATE SC_ROLES ROLES SET ROL_IDDISPAT = (SELECT DIS_ID FROM SC_DISPAT WHERE DIS_CODI = ROLES.ROL_BD);
--ALTER TABLE SC_ROLES DROP COLUMN ROL_BD;
ALTER TABLE SC_ROLES ADD ROL_DOMAPP NUMBER(10);
ALTER TABLE SC_ROLES ADD ROL_TIPDOM VARCHAR2(50 char);
ALTER TABLE SC_ROLES DROP COLUMN ROL_SUPER;

delete from sc_roles where rol_iddispat not in (select dis_id from sc_dispat);
alter table sc_roles add constraint ROL_DIS_FK_2 foreign key (rol_iddispat) references sc_dispat(dis_id);


-- ]

-- SC_REGCPD:
-- RCP_DATA   DATE  
-- [
ALTER TABLE SC_REGCPD ADD RCP_DATA DATE;
-- ]

-- SC_PORCPD:
-- PCP_DESCR  VARCHAR2(255 CHAR) 
-- [
ALTER TABLE SC_PORCPD ADD PCP_DESCR VARCHAR2(255 char);
-- ]

-- SC_LCOLCO :
-- LLC_ID      NUMBER(10)  
-- [
ALTER TABLE SC_LCOLCO ADD LLC_ID NUMBER(10);
UPDATE SC_LCOLCO SET LLC_ID = ROWNUM;
ALTER TABLE SC_LCOLCO MODIFY LLC_ID NOT NULL;
-- ]

-- SC_GRUPS:
-- GRU_CENSAL         VARCHAR2(30 CHAR)              NULL,
-- [
-- ALTER TABLE SC_GRUPS ADD GRU_CENSAL VARCHAR2(30);
-- ]

-- GRU_PARE           NUMBER(10)                     NULL,
-- [
ALTER TABLE SC_GRUPS ADD GRU_PARE NUMBER(10);
-- ]

-- GRU_TIPUS          VARCHAR2(20 CHAR)              NULL,
-- [
ALTER TABLE SC_GRUPS ADD GRU_TIPUS NUMBER(10);
-- ]

-- GRU_ADMINISTRADOR  NUMBER(10)                     NULL,
-- [
ALTER TABLE SC_GRUPS ADD GRU_ADMINISTRADOR NUMBER(10);
-- ]
-- SC_GRUIMP :
-- GIM_ID     NUMBER(10)   
-- [
ALTER TABLE SC_GRUIMP ADD GIM_ID NUMBER(10);
UPDATE SC_GRUIMP SET GIM_ID = ROWNUM;
ALTER TABLE SC_GRUIMP MODIFY GIM_ID NOT NULL;
-- ]

-- SC_FITXER:
-- FIT_RESP    VARCHAR2(450 CHAR)   -> FIT_RESP     NUMBER(10)
-- [
ALTER TABLE SC_FITXER ADD fit_idresp NUMBER(10);
UPDATE SC_FITXER fitxer SET fit_idresp = (SELECT usu_id FROM SC_usuari WHERE usu_codi = fitxer.fit_resp);
ALTER TABLE SC_FITXER DROP COLUMN fit_resp CASCADE CONSTRAINT;
-- ]


-- FIT_DG VARCHAR -> FIT_IDDG       NUMBER(10)                       
-- [
ALTER TABLE SC_FITXER ADD FIT_IDDG NUMBER(10);
UPDATE SC_FITXER fitxer SET FIT_IDDG = (SELECT gru_id FROM sc_grups WHERE fit_dg = gru_codi);
ALTER TABLE SC_FITXER DROP COLUMN fit_dg CASCADE CONSTRAINT;
-- ]

-- FIT_RSTEC VARCHAR -> FIT_IDRSTEC  NUMBER(10)
-- [
ALTER TABLE SC_FITXER ADD fit_idrstec NUMBER(10);
UPDATE SC_FITXER fitxer SET fit_idrstec = (SELECT usu_id FROM SC_usuari WHERE usu_codi = fitxer.fit_rstec);
ALTER TABLE SC_FITXER DROP COLUMN fit_rstec CASCADE CONSTRAINT;
-- ]



-- SC_EXTLCO : 
-- ELC_ID      NUMBER
-- [
ALTER TABLE SC_EXTLCO ADD ELC_ID NUMBER(10);
 UPDATE SC_EXTLCO SET ELC_ID = ROWNUM;
 ALTER TABLE SC_EXTLCO MODIFY ELC_ID NOT NULL;
-- ]

-- SC_DADUSU :

-- DUS_ID     NUMBER(10)   
-- [
ALTER TABLE SC_DADUSU ADD DUS_ID NUMBER(10);
UPDATE SC_DADUSU SET DUS_ID = ROWNUM;
ALTER TABLE SC_DADUSU MODIFY DUS_ID NOT NULL;

-- DUS_CODTDA -> DUS_TDAID  NUMBER(10)
-- [
ALTER TABLE sc_dadusu ADD dus_tdaid NUMBER(10);
UPDATE SC_dadusu dadusu SET dus_tdaid = (SELECT tda_id FROM SC_tipdad WHERE tda_codi = dadusu.dus_codtda);
DELETE FROM SC_DADUSU WHERE DUS_TDAID IS NULL;
ALTER TABLE SC_DADUSU DROP COLUMN dus_codtda CASCADE CONSTRAINT;
ALTER TABLE SC_dadusu MODIFY dus_tdaid NOT NULL;


delete from sc_dadusu where dus_tdaid not in (select tda_id from sc_tipdad);
alter table sc_dadusu add constraint DUS_TDA_FK  foreign key (dus_tdaid) references sc_tipdad (tda_id);

delete from sc_dadusu where (dus_idusu, dus_tdaid) in (select dus_idusu, dus_tdaid from sc_dadusu group by dus_idusu, dus_tdaid having count(*) > 1);
alter table sc_dadusu add constraint DUS_PK unique (dus_idusu, dus_tdaid);

-- ]


-- SC_CONTRA :
-- CTR_ID      NUMBER(10)
-- [
CREATE SEQUENCE  SC_CONTRA_SEQ  MINVALUE 1 MAXVALUE 1.00000000000000E+27 INCREMENT BY 1 START WITH 69734101 NOCACHE  NOORDER  NOCYCLE ;
ALTER TABLE SC_CONTRA ADD CTR_ID NUMBER(10);
UPDATE SC_CONTRA SET CTR_ID = SC_CONTRA_SEQ.NEXTVAL;
ALTER TABLE SC_CONTRA MODIFY CTR_ID NOT NULL;
-- ]

-- SC_CONFIG:
-- CON_DESC   NVARCHAR2(255)
-- [
ALTER TABLE SC_CONFIG ADD CON_DESC VARCHAR2(255 char);
-- ]

--SC_APLICA
--APL_CODI
--[
ALTER TABLE SC_APLICA ADD (APL_IDCONTACT NUMBER(10));
UPDATE SC_APLICA APP
SET APL_IDCONTACT = 
( 
	SELECT USU_ID
	FROM 
	SC_USUARI
	WHERE USU_CODI = APP.APL_RESPON
);
ALTER TABLE SC_APLICA DROP COLUMN APL_RESPON;
--]
--SC_PETFAR
ALTER TABLE SC_PETFAR ADD PEF_IDUSU NUMBER(10);
UPDATE SC_PETFAR PETFAR SET PEF_IDUSU = (SELECT USU_ID FROM SC_USUARI WHERE PETFAR.PEF_CODUSU = SC_USUARI.USU_CODI);
ALTER TABLE SC_PETFAR DROP COLUMN PEF_CODUSU;


--SC_AUDITO
--alter table sc_audito add aud_idfitxer number(10) references sc_fitxer(fit_id);
alter table sc_audito add aud_fitxer varchar2(100 char);
alter table sc_audito modify AUD_USUAUD VARCHAR2(100 CHAR) null;

update sc_audito
set aud_usuaud = (Lower(substr(aud_usuaud,1,1)) || substr(aud_usuaud,2,length(aud_usuaud)))
where not (aud_usuaud = Lower(aud_usuaud));

--alter table sc_dispat modify dis_id primary key;
alter table sc_audito add aud_dis varchar2(100 char);
alter table sc_audito add aud_dco varchar2(100 char);

alter table sc_audito add aud_apl varchar(100 char);
update sc_audito set aud_apl = (select apl_codi from sc_aplica where aud_idapl = apl_id);
alter table sc_audito drop column aud_idapl;

alter table sc_audito add aud_lco varchar(100 char);
update sc_audito set aud_lco = (select lco_nom from sc_llicor where aud_idlco = lco_id);
alter table sc_audito drop column aud_idlco;

alter table sc_audito add aud_imp varchar(100 char);
update sc_audito set aud_imp = (select imp_codi from sc_impres where aud_idimp = imp_id);
alter table sc_audito drop column aud_idimp;

alter table sc_audito add aud_xar varchar(100 char);
update sc_audito set aud_xar = (select xar_codi from sc_xarxes where aud_idxar = xar_id);
alter table sc_audito drop column aud_idxar;

alter table sc_audito add aud_maq varchar(100 char);
update sc_audito set aud_maq = (select maq_nom from sc_maquin where aud_idmaq = maq_id);
alter table sc_audito drop column aud_idmaq;

alter table sc_audito add aud_rol varchar(100 char);
update sc_audito set aud_rol = (select rol_nom from sc_roles where aud_idrol = rol_id);
alter table sc_audito drop column aud_idrol;


--alter table sc_audito add aud_iddco number(10,0);
--alter table sc_audito add constraint aud_dis_fk foreign key (aud_iddis) references sc_dispat(dis_id);
--alter table sc_audito add constraint aud_dco_fk foreign key (aud_iddco) references sc_domcor(dco_id);

alter table sc_audito add aud_domrlu varchar2 (100 char);
alter table sc_audito add aud_valdomrlu varchar2 (100 char);
alter table sc_audito add aud_con varchar2 (100 char);
--]

alter table sc_grups add gru_obsolet varchar2(5 char);
alter table sc_domcor add dco_obsolet varchar2(5 char);
update sc_domcor set dco_obsolet = 'N';
update sc_grups set gru_obsolet = 'N';
alter table sc_domcor modify dco_obsolet not null;
alter table sc_grups modify gru_obsolet not null;

update sc_roles  set rol_bd = (select dis_codi from sc_dispat where dis_id = rol_iddispat);
delete from sc_roles where rol_iddispat is null;
alter table sc_roles modify rol_iddispat not null;

ALTER TABLE SC_ROLES ADD ROL_BD2 VARCHAR2(50CHAR) NULL;
UPDATE SC_ROLES SET ROL_BD2 = ROL_BD;
ALTER TABLE SC_ROLES DROP COLUMN ROL_BD;
ALTER TABLE SC_ROLES ADD ROL_BD VARCHAR(50CHAR) NULL;
UPDATE SC_ROLES SET ROL_BD = ROL_BD2;
ALTER TABLE SC_ROLES DROP COLUMN ROL_BD2;

ALTER TABLE SC_AUDITO ADD CONSTRAINT SC_AUDITO_ID_PK PRIMARY KEY(AUD_ID) using index TABLESPACE "SEYCON_IND";
ALTER TABLE SC_AUTXAR ADD CONSTRAINT SC_AUTXAR_ID_PK PRIMARY KEY(AXA_ID) using index TABLESPACE "SEYCON_IND";
ALTER TABLE SC_ADMIN_SEG_ORG_USUARI ADD CONSTRAINT SC_ADMIN_SEG_ORG_USUARI_ID_PK PRIMARY KEY(ASO_ID) using index TABLESPACE "SEYCON_IND";


