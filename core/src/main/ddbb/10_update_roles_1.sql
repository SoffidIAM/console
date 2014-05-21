UPDATE SC_ROLES 
SET ROL_IDDISPAT = (SELECT DIS_ID FROM SC_DISPAT WHERE DIS_CODI = 'weblogic') 
WHERE 
ROL_IDDISPAT = (SELECT DIS_ID FROM SC_DISPAT WHERE DIS_CODI = 'seycon') AND 
ROL_IDAPL = (SELECT APL_ID FROM SC_APLICA WHERE APL_CODI = 'SEYCON');

DECLARE
  rol_id sc_rolusu.RLU_IDROL%TYPE;
	seycon_id sc_aplica.apl_id%TYPE;
  dis_id sc_dispat.dis_id%TYPE;
begin
select apl_id into seycon_id from sc_aplica where apl_codi = 'SEYCON';
select max(rol_id) + 1 into rol_id from sc_roles;
select dis_id into dis_id from sc_dispat where dis_codi = 'weblogic';
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
select dis_id into rol_iddispat from sc_dispat where dis_codi = 'weblogic';

insert into sc_roles (ROL_ID, ROL_NOM, ROL_DESCRI, ROL_IDAPL, ROL_DEFECT, ROL_CONTRA, 
ROL_IDDISPAT, ROL_DOMAPP, ROL_TIPDOM, ROL_BD) 
values(rol_id + 1, 'SC_RESPONSABLE', 'Responsable', 
rol_idapl, 'S', 'N', rol_iddispat, null, null, 'weblogic');

insert into sc_roles (ROL_ID, ROL_NOM, ROL_DESCRI, ROL_IDAPL,
ROL_DEFECT, ROL_CONTRA, ROL_IDDISPAT, ROL_DOMAPP, ROL_TIPDOM, ROL_BD) 
values(rol_id + 2, 'SC_RESPONSABLE_SEGURETAT', 'Responsable de seguretat', 
rol_idapl, 'S', 'N', rol_iddispat, null, null, 'weblogic');

insert into sc_roles (ROL_ID, ROL_NOM, ROL_DESCRI, ROL_IDAPL, 
ROL_DEFECT, ROL_CONTRA, ROL_IDDISPAT, ROL_DOMAPP, ROL_TIPDOM, ROL_BD) 
values(rol_id + 3, 'SC_ADMINISTRADOR_SEGURETAT', 'Administrador de seguretat', 
rol_idapl, 'S', 'N', rol_iddispat, null, null, 'weblogic');
end;
/

COMMIT;

