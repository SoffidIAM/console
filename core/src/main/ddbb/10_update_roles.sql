
DECLARE
	RLU_IDROL_VAR sc_rolusu.RLU_IDROL%TYPE;
	RLU_IDUSU_VAR sc_rolusu.RLU_IDUSU%TYPE;
	RLU_ID_VAR sc_rolusu.RLU_ID%TYPE; 
	RLU_ROLUSU_GRU_VAR sc_rolusu.RLU_ROLUSU_GRU%TYPE; 
	RLU_VALDOM_VAR sc_rolusu.RLU_VALDOM%TYPE;
	RLU_TIPDOM_VAR sc_rolusu.RLU_TIPDOM%TYPE;
	RLU_ADMAPP_VAR sc_rolusu.RLU_ADMAPP%TYPE;
	CURSOR rolusu_seycon IS
		select 	rol_weblogic.ROL_ID, rolusu.RLU_IDUSU, rolusu.RLU_ROLUSU_GRU, rolusu.RLU_VALDOM, rolusu.RLU_TIPDOM, rolusu.RLU_ADMAPP
		from sc_rolusu rolusu, sc_roles rol, sc_dispat dispat, sc_roles rol_weblogic, sc_dispat dis_weblogic
		where 
		rol.rol_iddispat = dispat.dis_id and
		rolusu.rlu_idrol = rol.rol_id and
		dispat.dis_codi = 'seycon' and
		rol_weblogic.rol_iddispat = dis_weblogic.dis_id and
		dis_weblogic.dis_codi = 'weblogic' and
		rol.rol_nom = rol_weblogic.rol_nom;
begin
	select max(rlu_id) into RLU_ID_VAR from sc_rolusu;
	open rolusu_seycon;
	loop
		RLU_ID_VAR := RLU_ID_VAR + 1;
		fetch rolusu_seycon into RLU_IDROL_VAR, RLU_IDUSU_VAR, RLU_ROLUSU_GRU_VAR, RLU_VALDOM_VAR, RLU_TIPDOM_VAR, RLU_ADMAPP_VAR;
		exit when rolusu_seycon%NOTFOUND;
		insert into sc_rolusu (RLU_IDROL, RLU_IDUSU, RLU_ID, RLU_ROLUSU_GRU, RLU_VALDOM, RLU_TIPDOM, RLU_ADMAPP) values(RLU_IDROL_VAR, RLU_IDUSU_VAR, RLU_ID_VAR, RLU_ROLUSU_GRU_VAR, RLU_VALDOM_VAR, RLU_TIPDOM_VAR, RLU_ADMAPP_VAR);
	end loop;
	close rolusu_seycon;
end;
/
DECLARE
  rol_id sc_rolusu.RLU_IDROL%TYPE;
	seycon_id sc_aplica.apl_id%TYPE;
  dis_id sc_dispat.dis_id%TYPE;
begin
select apl_id into seycon_id from sc_aplica where apl_codi = 'SEYCON';
select max(rol_id) + 1 into rol_id from sc_roles;
select dis_id into dis_id from sc_dispat where dis_codi = 'seycon';
insert into sc_roles (ROL_ID, ROL_NOM, ROL_DESCRI, ROL_IDAPL, ROL_DEFECT, ROL_BD, ROL_CONTRA, ROL_IDDISPAT, ROL_DOMAPP, ROL_TIPDOM) values (rol_id, 'SC_CIUTADA', 'Ciutadà', seycon_id, 'N', 'seycon', 'N', dis_id, null, null);
END;
/

declare
	rol_idapl sc_roles.rol_idapl%type;
	rol_id sc_roles.rol_id%type;
	rol_iddispat sc_roles.rol_iddispat%type;
begin
select max(rol_id) + 1 into rol_id from sc_roles;
select apl_id into rol_idapl from sc_aplica where apl_codi = 'SEYCON';
select dis_id into rol_iddispat from sc_dispat where dis_codi = 'seycon';

insert into sc_roles (ROL_ID, ROL_NOM, ROL_DESCRI, ROL_IDAPL, ROL_DEFECT, ROL_CONTRA, 
ROL_IDDISPAT, ROL_DOMAPP, ROL_TIPDOM, ROL_BD) 
values(rol_id + 1, 'SC_RESPONSABLE', 'Responsable', 
rol_idapl, 'S', 'N', rol_iddispat, null, null, 'seycon');

insert into sc_roles (ROL_ID, ROL_NOM, ROL_DESCRI, ROL_IDAPL,
ROL_DEFECT, ROL_CONTRA, ROL_IDDISPAT, ROL_DOMAPP, ROL_TIPDOM, ROL_BD) 
values(rol_id + 2, 'SC_RESPONSABLE_SEGURETAT', 'Responsable de seguretat', 
rol_idapl, 'S', 'N', rol_iddispat, null, null, 'seycon');

insert into sc_roles (ROL_ID, ROL_NOM, ROL_DESCRI, ROL_IDAPL, 
ROL_DEFECT, ROL_CONTRA, ROL_IDDISPAT, ROL_DOMAPP, ROL_TIPDOM, ROL_BD) 
values(rol_id + 3, 'SC_ADMINISTRADOR_SEGURETAT', 'Administrador de seguretat', 
rol_idapl, 'S', 'N', rol_iddispat, null, null, 'seycon');
end;
/

update sc_wl_usugru  
set ugr_codgru = REPLACE(ugr_codgru, '@seycon','') 
where ugr_codgru like '%@seycon' and 
REPLACE(ugr_codgru, '@seycon','') not in (select ugr_codgru from sc_wl_usugru);

update sc_wl_usugru  
set ugr_codgru = REPLACE(ugr_codgru, '@SEYCON','') 
where ugr_codgru like '%@SEYCON' and 
REPLACE(ugr_codgru, '@SEYCON','') not in (select ugr_codgru from sc_wl_usugru);

update sc_wl_usugru@prod4
set ugr_codgru = REPLACE(ugr_codgru, '@seycon','') 
where ugr_codgru like '%@seycon' and 
REPLACE(ugr_codgru, '@seycon','') not in (select ugr_codgru from sc_wl_usugru);

update sc_wl_usugru@prod4 
set ugr_codgru = REPLACE(ugr_codgru, '@SEYCON','') 
where ugr_codgru like '%@SEYCON' and 
REPLACE(ugr_codgru, '@SEYCON','') not in (select ugr_codgru from sc_wl_usugru);

COMMIT;


alter trigger SC_AUS_ALTA enable;
alter trigger SC_AUS_TAS_TR enable;
alter trigger SC_EXTLCO_DEL enable;
alter trigger SC_EXTLCO_UPD enable;
alter trigger SC_GIM_TAS_TR enable;
alter trigger SC_GRU_TAS_TR enable;
alter trigger SC_GRUPS_UPD enable;
alter trigger SC_IMP_TAS_TR enable;
alter trigger SC_LCOLCO_DEL enable;
alter trigger SC_LCOLCO_UPD enable;
alter trigger SC_LLICOR_DEL enable;
alter trigger SC_LLICOR_UPD enable;
alter trigger SC_MAQ_TAS_TR enable;
alter trigger SC_PLU_ALTA enable;
alter trigger SC_PLU_TAS_TR enable;
alter trigger SC_RLU_ALTA enable;
--alter trigger SC_RLU_AUTO_CHECK enable;
alter trigger SC_RLU_TAS_TR enable;
--alter trigger SC_ROLUSU_AUDITO enable;
alter trigger SC_UGR_ALTA enable;
alter trigger SC_UGR_AUTO_CHECK enable;
alter trigger SC_UGR_TAS_TR enable;
alter trigger SC_UIM_ALTA enable;
alter trigger SC_UIM_TAS_TR enable;
alter trigger SC_USU_ALTA enable;
alter trigger SC_USU_TAS_TR enable;
alter trigger SC_USU_UPD enable;
--alter trigger SC_USUARI_AUDITO enable;
alter trigger SC_USUARI_AUTO_CHECK enable;
alter trigger SC_USUARI_LCO_UPD enable;
alter trigger SC_USUARI_LCO_UPD2 enable;
--alter trigger SC_USUGRU_AUDITO enable;
alter trigger SC_USULCO_DEL enable;
alter trigger SC_USULCO_UPD enable;
alter trigger SC_XAR_TAS_TR enable;
alter trigger SC_ROLES_UPD enable;