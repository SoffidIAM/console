---------------------------------------------------
--   DATA FOR TABLE SC_CONFIG
--   FILTER = none used
---------------------------------------------------
-- INSERTING into SC_CONFIG
Insert into SC_CONFIG (CON_ID,CON_IDXAR,CON_CODI,CON_VALOR,CON_DESC) values (6,null,'seycon.server.timeout','180000',null);
Insert into SC_CONFIG (CON_ID,CON_IDXAR,CON_CODI,CON_VALOR,CON_DESC) values (4,null,'SSOServer','localhost',null);
Insert into SC_CONFIG (CON_ID,CON_IDXAR,CON_CODI,CON_VALOR,CON_DESC) values (5,null,'QueryServer','localhost',null);
Insert into SC_CONFIG (CON_ID,CON_IDXAR,CON_CODI,CON_VALOR,CON_DESC) values (19,null,'seycon.server.list','https://localhost:5000/seycon/Server',null);
Insert into SC_CONFIG (CON_ID,CON_IDXAR,CON_CODI,CON_VALOR,CON_DESC) values (6,null,'seycon.https.port','5000',null);
Insert into SC_CONFIG (CON_ID,CON_IDXAR,CON_CODI,CON_VALOR,CON_DESC) values (9,null,'seycon.db.poolsize','20',null);
Insert into SC_CONFIG (CON_ID,CON_IDXAR,CON_CODI,CON_VALOR,CON_DESC) values (11,null,'seycon.db.version','2',null);
Insert into SC_CONFIG (CON_ID,CON_IDXAR,CON_CODI,CON_VALOR,CON_DESC) values (101,null,'seycon.server.agent','jboss',null);

---------------------------------------------------
--   END DATA FOR TABLE SC_CONFIG
---------------------------------------------------

Insert into sc_wl_usuari (usu_codi, usu_pass, usu_datcad) 
select 'admin', '{SHA-1}0DPiKuNIrrVmD8IUCuw1hQxNqZc=', sysdate+365 from dual;

insert into sc_wl_usugru (ugr_codusu, ugr_codgru) values ('admin', 'SC_ADMINISTRADOR');

insert into sc_aplica (apl_id, apl_codi, apl_nom) values (1, 'SEYCON', 'SEYCON');
insert into sc_dispat (dis_id, dis_codi, dis_nomcla, dis_url, dis_param1, dis_param2, dis_param3, dis_segur, dis_basrol) 
values (1, 'jboss', 'es.caib.seycon.agent.JBossAgent', 'local', 'USER', 'PASSWORD', 'jdbc:oracle:thin:@HOST:PORT:SID', 'S','N');
insert into sc_roles (rol_id, rol_nom, rol_descri, rol_idapl, rol_defect, rol_iddispat) 
values (1, 'SC_ADMINISTRADOR', 'Administrador SEYCON', 1, 'S', 1);

insert into sc_xarxes(xar_id, xar_codi, xar_descri, xar_adrip, xar_masip, xar_norm)
values (1, 'local', 'Xarxa Local', '127.0.0.1', '255.255.255.0', 'N');

insert into sc_maquin(maq_id, maq_nom, maq_descri, maq_adrip, maq_idxar, maq_ofimat, maq_correu, maq_sisope)
VALUES (1, 'nul', 'nul', null, 1, 'S', 'N', 'LIN');


insert into sc_grups (gru_id, gru_codi, gru_descri, gru_obsolet)
values (1, 'CAIB', 'Comunitat Autònoma Illes Balears', 'N');

insert into sc_usuari (usu_id, usu_codi, usu_nom, usu_prilli, usu_seglli, usu_actiu, usu_idmaq, usu_usucre, usu_datcre, usu_idmapr, usu_idmaco, usu_idgru, usu_mulses)
values (1, 'admin', 'Administrador', 'Administrador', null, 'S', 1, 'admin', sysdate, 1, 1, 1, 'S');

insert into sc_contra (ctr_id, ctr_idusu, ctr_contra, ctr_ordre, ctr_actiu, ctr_datcad)
select 1, 1, '0DPiKuNIrrVmD8IUCuw1hQxNqZc=', 0, 'S', sysdate+365
from dual;
