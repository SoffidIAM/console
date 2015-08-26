

ALTER TRIGGER SC_USUGRU_AUDITO DISABLE;
ALTER TRIGGER SC_USUARI_AUDITO DISABLE;

DROP TRIGGER SC_USUGRU_AUDITO;
DROP TRIGGER SC_USUARI_AUDITO;
DROP TRIGGER SC_ROLUSU_AUDITO;
DROP TRIGGER SC_RLU_AUTO_CHECK;


CREATE OR REPLACE TRIGGER SC_RLU_TAS_TR
AFTER INSERT OR DELETE ON SC_ROLUSU
FOR EACH ROW
DECLARE
   	codi_usuari varchar2(50 char);
   	codi_bd varchar2(50 char);
   	codi_role varchar2(50 char);
BEGIN
	-- cercar el codi del usuari
	if deleting then
	   select usu_codi into codi_usuari from sc_usuari where usu_id=:old.rlu_idusu;
	   select rol_nom, dis_codi into codi_role,codi_bd 
           from sc_roles, sc_dispat  where dis_id=rol_iddispat(+) and rol_id=:old.rlu_idrol;
	else
	   select usu_codi into codi_usuari from sc_usuari where usu_id=:new.rlu_idusu;
	   select rol_nom, dis_codi into codi_role,codi_bd 
           from sc_roles, sc_dispat  where dis_id=rol_iddispat(+) and rol_id=:new.rlu_idrol;

           update sc_contra
           set    ctr_actiu='N'
           where  ctr_idusu = :new.rlu_idusu and ctr_ordre = 0;
   	end if;
	insert into sc_tasque
  	  (tas_id,tas_usuari,tas_status,tas_data,tas_transa)
	values
	  (sc_tas_seq.nextval,codi_usuari,'P',sysdate,'UpdateUser');
	insert into sc_tasque
  	  (tas_id,tas_role,tas_bd,tas_status,tas_data,tas_transa)
	values
	  (sc_tas_seq.nextval,codi_role,codi_bd,'P',sysdate,'UpdateRole');
END;
/
create or replace
TRIGGER SC_ROLES_UPD
AFTER INSERT OR UPDATE OR DELETE ON SC_ROLES
FOR EACH ROW
DECLARE
   	codi_bd varchar2(50 char);
BEGIN
	if deleting or updating then
	select dis_codi into codi_bd 
		from sc_dispat  where dis_id = :old.rol_iddispat;
	  insert into sc_tasque
	     (tas_id,tas_role, tas_bd,tas_status,tas_data,tas_transa)
	  values
	     (sc_tas_seq.nextval,:old.rol_nom,codi_bd,'P',sysdate,'UpdateRole');
	end if;
  if inserting or updating then
	select distinct dis_codi into codi_bd 
		from sc_dispat  where dis_id = :new.rol_iddispat;
    insert into sc_tasque
      (tas_id,tas_role,tas_bd,tas_status,tas_data,tas_transa)
    values
	     (sc_tas_seq.nextval,:new.rol_nom,codi_bd,'P',sysdate,'UpdateRole');
    end if;
END;
/
create or replace FUNCTION SC_ROLE_UPDATEABLE (ID IN NUMBER) 
RETURN BOOLEAN IS
BEGIN
   RETURN SC_ROLE_SUPERROLE_UPDATEABLE (ID);
END;
/

create or replace FUNCTION SC_ASIGNA_CONTRA (usuari varchar2)
RETURN VARCHAR2 IS
   CADENA VARCHAR2(10 char);
BEGIN
   CADENA:='ib';
   FOR i IN 1..4 LOOP  -- assign the values 1,2,3,4 to i
      CADENA:=CADENA||TO_CHAR(RANDOM.rndint(10));
   END LOOP;
   CADENA:=CADENA||substr('abcdefghijklmnopqrstuvwxyz',RANDOM.RNDINT(27),1);

   INSERT INTO SC_AUDITO (AUD_ID, AUD_USUAUD, AUD_ACCIO, AUD_IDUSU, AUD_DATA, AUD_INFO)
   SELECT SC_AUD_SEQ.NEXTVAL, USER,'U', USU2.USU_ID, SYSDATE, 'PASSWORD'
   FROM   SC_USUARI USU2
   WHERE  USU2.USU_CODI = usuari;

   INSERT INTO SC_TASQUE (TAS_ID, TAS_TRANSA, TAS_DATA, TAS_USUARI, TAS_CONTRA, TAS_CANCON)
   VALUES (SC_TAS_SEQ.NEXTVAL, 'UpdateUserPassword', SYSDATE,usuari,CADENA, 'S');
   RETURN(CADENA);
EXCEPTION
WHEN NO_DATA_FOUND THEN
   RAISE_APPLICATION_ERROR (-20002, 'L''usuari '||user||' no esta registrat');
END;
/

create or replace FUNCTION SC_ASIGNA_CONTRA (usuari varchar2)
RETURN VARCHAR2 IS
   CADENA VARCHAR2(10 char);
BEGIN
   CADENA:='ib';
   FOR i IN 1..4 LOOP  -- assign the values 1,2,3,4 to i
      CADENA:=CADENA||TO_CHAR(RANDOM.rndint(10));
   END LOOP;
   CADENA:=CADENA||substr('abcdefghijklmnopqrstuvwxyz',RANDOM.RNDINT(27),1);

   INSERT INTO SC_TASQUE (TAS_ID, TAS_TRANSA, TAS_DATA, TAS_USUARI, TAS_CONTRA, TAS_CANCON)
   VALUES (SC_TAS_SEQ.NEXTVAL, 'UpdateUserPassword', SYSDATE,usuari,CADENA, 'S');
   RETURN(CADENA);
EXCEPTION
WHEN NO_DATA_FOUND THEN
   RAISE_APPLICATION_ERROR (-20002, 'L''usuari '||user||' no esta registrat');
END;
/

create or replace
FUNCTION SC_ASIGNA_CONTRA (usuari varchar2)
RETURN VARCHAR2 IS
   CADENA VARCHAR2(10);
BEGIN
   CADENA:='ib';
   FOR i IN 1..4 LOOP  -- assign the values 1,2,3,4 to i
      CADENA:=CADENA||TO_CHAR(RANDOM.rndint(10));
   END LOOP;
   CADENA:=CADENA||substr('abcdefghijklmnopqrstuvwxyz',RANDOM.RNDINT(27),1);

   INSERT INTO SC_TASQUE (TAS_ID, TAS_TRANSA, TAS_DATA, TAS_USUARI, TAS_CONTRA, TAS_CANCON)
   VALUES (SC_TAS_SEQ.NEXTVAL, 'UpdateUserPassword', SYSDATE,usuari,CADENA, 'S');
   RETURN(CADENA);
EXCEPTION
WHEN NO_DATA_FOUND THEN
   RAISE_APPLICATION_ERROR (-20002, 'L''usuari '||user||' no esta registrat');
END;
/