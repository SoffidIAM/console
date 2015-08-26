
--------------------------------------------------------
--  DDL for Table JBPM_JOB

--------------------------------------------------------
  CREATE TABLE "JBPM_JOB" 
   (	"ID_" NUMBER(19,0), 
	"CLASS_" CHAR(1 CHAR), 
	"VERSION_" NUMBER(10,0), 
	"DUEDATE_" TIMESTAMP (6), 
	"PROCESSINSTANCE_" NUMBER(19,0), 
	"TOKEN_" NUMBER(19,0), 
	"TASKINSTANCE_" NUMBER(19,0), 
	"ISSUSPENDED_" NUMBER(1,0), 
	"ISEXCLUSIVE_" NUMBER(1,0), 
	"LOCKOWNER_" VARCHAR2(255 CHAR), 
	"LOCKTIME_" TIMESTAMP (6), 
	"EXCEPTION_" VARCHAR2(4000 CHAR), 
	"RETRIES_" NUMBER(10,0), 
	"NAME_" VARCHAR2(255 CHAR), 
	"REPEAT_" VARCHAR2(255 CHAR), 
	"TRANSITIONNAME_" VARCHAR2(255 CHAR), 
	"ACTION_" NUMBER(19,0), 
	"GRAPHELEMENTTYPE_" VARCHAR2(255 CHAR), 
	"GRAPHELEMENT_" NUMBER(19,0), 
	"NODE_" NUMBER(19,0)
   ) ;
 

--------------------------------------------------------
--  Constraints for Table JBPM_JOB
--------------------------------------------------------

  ALTER TABLE "JBPM_JOB" MODIFY ("ID_" NOT NULL ENABLE);
 
  ALTER TABLE "JBPM_JOB" MODIFY ("CLASS_" NOT NULL ENABLE);
 
  ALTER TABLE "JBPM_JOB" MODIFY ("VERSION_" NOT NULL ENABLE);
 
  ALTER TABLE "JBPM_JOB" ADD PRIMARY KEY ("ID_") ENABLE;
 

--------------------------------------------------------
--  Ref Constraints for Table JBPM_JOB
--------------------------------------------------------

  ALTER TABLE "JBPM_JOB" ADD CONSTRAINT "FK_JOB_ACTION" FOREIGN KEY ("ACTION_")
	  REFERENCES "JBPM_ACTION" ("ID_") ENABLE;
 
  ALTER TABLE "JBPM_JOB" ADD CONSTRAINT "FK_JOB_NODE" FOREIGN KEY ("NODE_")
	  REFERENCES "JBPM_NODE" ("ID_") ENABLE;
 
  ALTER TABLE "JBPM_JOB" ADD CONSTRAINT "FK_JOB_PRINST" FOREIGN KEY ("PROCESSINSTANCE_")
	  REFERENCES "JBPM_PROCESSINSTANCE" ("ID_") ENABLE;
 
  ALTER TABLE "JBPM_JOB" ADD CONSTRAINT "FK_JOB_TOKEN" FOREIGN KEY ("TOKEN_")
	  REFERENCES "JBPM_TOKEN" ("ID_") ENABLE;
 
  ALTER TABLE "JBPM_JOB" ADD CONSTRAINT "FK_JOB_TSKINST" FOREIGN KEY ("TASKINSTANCE_")
	  REFERENCES "JBPM_TASKINSTANCE" ("ID_") ENABLE;
 

 
ALTER TABLE "JBPM_MODULEINSTANCE" ADD ( "VERSION_" NUMBER (10,0) ); 

 

ALTER TABLE "JBPM_NODE" ADD ( "DESCRIPTION_" VARCHAR2(4000 CHAR) ); 
ALTER TABLE "JBPM_NODE" ADD ( "ISASYNCEXCL_" NUMBER (1,0) ); 
ALTER TABLE "JBPM_NODE" ADD ( "SUBPROCNAME_" VARCHAR2(255 CHAR) );
ALTER TABLE "JBPM_NODE" ADD ( "SCRIPT_" NUMBER (19,0) ); 
ALTER TABLE "JBPM_NODE" ADD ( "PARENTLOCKMODE_" VARCHAR2(255 CHAR) );


  
ALTER TABLE "JBPM_POOLEDACTOR" ADD ( "VERSION_" NUMBER (10,0) ); 


ALTER TABLE "JBPM_PROCESSDEFINITION" ADD ( "DESCRIPTION_" VARCHAR2(4000 CHAR) );
ALTER TABLE "JBPM_PROCESSDEFINITION" ADD CLASS_ CHAR(1 CHAR);


ALTER TABLE "JBPM_PROCESSINSTANCE" ADD ( "KEY_" VARCHAR2(255 CHAR) ); 


ALTER TABLE "JBPM_SWIMLANEINSTANCE" ADD ( "VERSION_" NUMBER (10,0) );


ALTER TABLE "JBPM_TASK" ADD ( "CONDITION_" VARCHAR2(255 CHAR) );
ALTER TABLE "JBPM_TASK" ADD ( "PRIORITY_" NUMBER (10,0) ); 


ALTER TABLE "JBPM_TASKINSTANCE" ADD ( "VERSION_" NUMBER (10,0) );	  
ALTER TABLE "JBPM_TASKINSTANCE" ADD ( "PROCINST_" NUMBER (19,0) );

 


	  
ALTER TABLE "JBPM_TOKEN" ADD ( "LOCK_" VARCHAR2(255 CHAR) );



  
	  
ALTER TABLE "JBPM_TOKENVARIABLEMAP" ADD ( "VERSION_" NUMBER (10,0) );


	  
	  
ALTER TABLE "JBPM_TRANSITION" ADD ( "DESCRIPTION_" VARCHAR2(4000 CHAR) ); 
ALTER TABLE "JBPM_TRANSITION" ADD ( "CONDITION_" VARCHAR2(255 CHAR) ); 

	  
ALTER TABLE "JBPM_VARIABLEINSTANCE" ADD ( "VERSION_" NUMBER (10,0) );


ALTER TABLE "SC_AUDITO" MODIFY ( "AUD_DIS" VARCHAR2(150) ); -- ES 150 BYTE.. DEBERIA SER CHAR?


ALTER TABLE "SC_AUTXAR" ADD CONSTRAINT "SC_AXA_PK" PRIMARY KEY ("AXA_ID") ENABLE;
 ALTER TABLE "SC_AUTXAR" ADD CONSTRAINT "SC_AXA_XAR_FK" FOREIGN KEY ("AXA_IDXAR")
	  REFERENCES "SC_XARXES" ("XAR_ID") ENABLE;
  ALTER TABLE "SC_AUTXAR" ADD CONSTRAINT "SC_AXA_ROL_FK" FOREIGN KEY ("AXA_IDROL")
	  REFERENCES "SC_ROLES" ("ROL_ID") ENABLE;
  ALTER TABLE "SC_AUTXAR" ADD CONSTRAINT "SC_AXA_USU_FK" FOREIGN KEY ("AXA_IDUSU")
	  REFERENCES "SC_USUARI" ("USU_ID") ENABLE;
  ALTER TABLE "SC_AUTXAR" ADD CONSTRAINT "SC_AXA_GRU_FK" FOREIGN KEY ("AXA_IDGRU")
	  REFERENCES "SC_GRUPS" ("GRU_ID") ENABLE;


ALTER TABLE "SC_CONFIG" MODIFY ( "CON_DESC" VARCHAR2(255) ); --ES DE TIPO BYTE !!

ALTER TABLE "SC_DISPAT" MODIFY ( "DIS_URL2" VARCHAR2(200 CHAR) ); 
ALTER TABLE "SC_DISPAT" ADD ( "DIS_TIPUSU" VARCHAR2(20 CHAR) );
ALTER TABLE "SC_DISPAT" ADD ( "DIS_GRUPS" VARCHAR2(200 CHAR) );


--------------------------------------------------------
--  DDL for Table SC_GRUDIS
--------------------------------------------------------

  CREATE TABLE "SC_GRUDIS" 
   (	"GRD_ID" NUMBER(10,0), 
	"GRD_IDGRUP" NUMBER(10,0), 
	"GRD_IDDIS" NUMBER(10,0)
   ) ;
 

--------------------------------------------------------
--  Constraints for Table SC_GRUDIS
--------------------------------------------------------

  ALTER TABLE "SC_GRUDIS" ADD UNIQUE ("GRD_ID") ENABLE;
 

--------------------------------------------------------
--  Ref Constraints for Table SC_GRUDIS
--------------------------------------------------------

  ALTER TABLE "SC_GRUDIS" ADD FOREIGN KEY ("GRD_IDDIS")
	  REFERENCES "SC_DISPAT" ("DIS_ID") ENABLE;
	  
  
 

ALTER TABLE "SC_GRUPS" ADD ( "GRU_ORGANITZATIU" VARCHAR2(300) ); --TIPO BYTE

ALTER TABLE SC_GRUPS DROP CONSTRAINT GRU_ROL_FK; -- ALEX: YA NO SE UTILIZA ESTA FK (TABLA NUEVA SC_ROLGRUP)

ALTER TABLE "SC_PETFAR" MODIFY ( "PEF_SIGREF" VARCHAR2(128) ); -- TIPO BYTE
ALTER TABLE "SC_PETFAR" MODIFY ( "PEF_DOCREF" VARCHAR2(128) ); -- TIPO BYTE


ALTER TABLE "SC_PORCPD" MODIFY ( "PCP_DESCR" VARCHAR2(255) );-- TIPO BYTE





--ALTER TABLE "SC_ROLES" DROP COLUMN "ROL_BD"; -- SE ELIMINA COLUMNA ¿NECESARIO?
ALTER TABLE "SC_ROLES" MODIFY ( "ROL_DESCRI" VARCHAR2(150 CHAR) ); 
ALTER TABLE "SC_ROLES" MODIFY ( "ROL_TIPDOM" VARCHAR2(50) ); -- TIPO BYTE

ALTER TABLE SC_ROLES DROP CONSTRAINT ROL_DIS_FK_2; --SE LE CAMBIA DE NOMBRE ROL_DIS_FK_2 POR ROL_DIS_FK �NECESARIO?
  ALTER TABLE "SC_ROLES" ADD CONSTRAINT "ROL_DIS_FK" FOREIGN KEY ("ROL_IDDISPAT")
	  REFERENCES "SC_DISPAT" ("DIS_ID") ENABLE;


--------------------------------------------------------
--  DDL for Table SC_ROLGRUP
--------------------------------------------------------

  CREATE TABLE "SC_ROLGRUP" 
   (	"RLG_ID" NUMBER(10,0), 
	"RLG_ROL" NUMBER(10,0), 
	"RLG_GRUP" NUMBER(10,0)
   ) ;
 

--------------------------------------------------------
--  Constraints for Table SC_ROLGRUP
--------------------------------------------------------

  ALTER TABLE "SC_ROLGRUP" MODIFY ("RLG_ID" NOT NULL ENABLE);
 
  ALTER TABLE "SC_ROLGRUP" MODIFY ("RLG_ROL" NOT NULL ENABLE);
 
  ALTER TABLE "SC_ROLGRUP" MODIFY ("RLG_GRUP" NOT NULL ENABLE);
 
  ALTER TABLE "SC_ROLGRUP" ADD PRIMARY KEY ("RLG_ID") ENABLE;
 

--------------------------------------------------------
--  Ref Constraints for Table SC_ROLGRUP
--------------------------------------------------------

  ALTER TABLE "SC_ROLGRUP" ADD CONSTRAINT "RLG_ROLGRUP_GRUPS_FK" FOREIGN KEY ("RLG_GRUP")
	  REFERENCES "SC_GRUPS" ("GRU_ID") ENABLE;
 
  ALTER TABLE "SC_ROLGRUP" ADD CONSTRAINT "RLG_ROLGRUP_ROLES_FK" FOREIGN KEY ("RLG_ROL")
	  REFERENCES "SC_ROLES" ("ROL_ID") ENABLE;
	  
 


--------------------------------------------------------
--  DDL for Table SC_ROLROL
--------------------------------------------------------

  CREATE TABLE "SC_ROLROL" 
   (	"RRL_ID" NUMBER(10,0), 
	"RRL_CONTENIDOR" NUMBER(10,0), 
	"RRL_CONTINGUT" NUMBER(10,0)
   ) ;
 

--------------------------------------------------------
--  Constraints for Table SC_ROLROL
--------------------------------------------------------

  ALTER TABLE "SC_ROLROL" ADD PRIMARY KEY ("RRL_ID") ENABLE;
 
  ALTER TABLE "SC_ROLROL" ADD UNIQUE ("RRL_CONTENIDOR", "RRL_CONTINGUT") DISABLE;
 

--------------------------------------------------------
--  Ref Constraints for Table SC_ROLROL
--------------------------------------------------------

  ALTER TABLE "SC_ROLROL" ADD CONSTRAINT "RRL_ROLROL_ROLES_FK1" FOREIGN KEY ("RRL_CONTENIDOR")
	  REFERENCES "SC_ROLES" ("ROL_ID") ENABLE;
 
  ALTER TABLE "SC_ROLROL" ADD CONSTRAINT "RRL_ROLROL_ROLES_FK2" FOREIGN KEY ("RRL_CONTINGUT")
	  REFERENCES "SC_ROLES" ("ROL_ID") ENABLE;
 

--ALTER TABLE SC_ROLUSU DROP CONSTRAINT RLU_VDO_FK;


ALTER TABLE "SC_TASQUE" MODIFY ( "TAS_GRUP" VARCHAR2(20) );-- TIPO BYTE

--------------------------------------------------------
--  DDL for Table SC_TIPDIS
--------------------------------------------------------

  CREATE TABLE "SC_TIPDIS" 
   (	"TPD_ID" NUMBER(10,0), 
	"TPD_TIPUS" VARCHAR2(10 CHAR), 
	"TPD_IDDIS" NUMBER(10,0)
   ) ;
 

--------------------------------------------------------
--  Constraints for Table SC_TIPDIS
--------------------------------------------------------

  ALTER TABLE "SC_TIPDIS" ADD UNIQUE ("TPD_ID") ENABLE;
 

--------------------------------------------------------
--  Ref Constraints for Table SC_TIPDIS
--------------------------------------------------------

  ALTER TABLE "SC_TIPDIS" ADD FOREIGN KEY ("TPD_IDDIS")
	  REFERENCES "SC_DISPAT" ("DIS_ID") ENABLE;
 


ALTER TABLE SC_TIPUSUO DROP CONSTRAINT TUO_TUO_FK; -- CORREGIDA FK AUNQUE NO SE USA
  ALTER TABLE "SC_TIPUSUO" ADD CONSTRAINT "TUO_TUO_FK" FOREIGN KEY ("TUO_PARE")
	  REFERENCES "SC_TIPUSUO" ("TUO_ID") ENABLE;
	  


--ALTER TABLE "SC_VALOR_DOMINI" MODIFY ( "VDO_DESC" NVARCHAR2 ); -- ALEX: SE CAMBIA A NVARCHAR2(50 CHAR) !! ????
COMMENT ON COLUMN "SC_WL_LOGAUT"."LOG_LOGIN" IS 'Usuario empleado para realizar el login. Para tipo A indicar� el usuario unathenticated, para tipo U indicar� el usuario j_username y para tipo C indicar� el DNI del certificado empleado. ' ;



--------------------------------------------------------
--  DDL for Index IDX_JOB_PRINST
--------------------------------------------------------

  CREATE INDEX "IDX_JOB_PRINST" ON "JBPM_JOB" ("PROCESSINSTANCE_") TABLESPACE SEYCON_IND
  ;
 

--------------------------------------------------------
--  DDL for Index IDX_JOB_TOKEN
--------------------------------------------------------

  CREATE INDEX "IDX_JOB_TOKEN" ON "JBPM_JOB" ("TOKEN_") TABLESPACE SEYCON_IND
  ;
 

--------------------------------------------------------
--  DDL for Index IDX_JOB_TSKINST
--------------------------------------------------------

  CREATE INDEX "IDX_JOB_TSKINST" ON "JBPM_JOB" ("TASKINSTANCE_")  TABLESPACE SEYCON_IND
  ;
 



--------------------------------------------------------
--  DDL for Index RAC_SER_FK_I
--------------------------------------------------------

  CREATE INDEX "RAC_SER_FK_I" ON "SC_REGACC" ("RAC_IDSER")  TABLESPACE SEYCON_IND
  ;
 


--------------------------------------------------------
--  DDL for Index RLG_ROLGRUP_GRUP
--------------------------------------------------------

CREATE INDEX SEYCON.RLG_ROLGRUP_GRUP ON SEYCON.SC_ROLGRUP
(RLG_GRUP)
NOLOGGING
TABLESPACE SEYCON_IND
NOPARALLEL;  
 


--------------------------------------------------------
--  DDL for Index RRL_ROL_FK_1
--------------------------------------------------------

CREATE INDEX SEYCON.RRL_ROL_FK_1 ON SEYCON.SC_ROLROL
(RRL_CONTENIDOR)
NOLOGGING
TABLESPACE SEYCON_IND
NOPARALLEL;  
 

--------------------------------------------------------
--  DDL for Index RRL_ROL_FK_2
--------------------------------------------------------

  
  
CREATE INDEX SEYCON.RRL_ROL_FK_2 ON SEYCON.SC_ROLROL
(RRL_CONTINGUT)
NOLOGGING
TABLESPACE SEYCON_IND
NOPARALLEL;  
 

--------------------------------------------------------
--  DDL for Index SC_AXA_GRU
--------------------------------------------------------

  CREATE INDEX "SC_AXA_GRU" ON "SC_AUTXAR" ("AXA_IDGRU")  TABLESPACE SEYCON_IND
  ;
 

--------------------------------------------------------
--  DDL for Index SC_AXA_ROL
--------------------------------------------------------

  CREATE INDEX "SC_AXA_ROL" ON "SC_AUTXAR" ("AXA_IDROL")  TABLESPACE SEYCON_IND
  ;
 

--------------------------------------------------------
--  DDL for Index SC_AXA_USU
--------------------------------------------------------

  CREATE INDEX "SC_AXA_USU" ON "SC_AUTXAR" ("AXA_IDUSU")  TABLESPACE SEYCON_IND
  ;
 


ALTER TRIGGER "SC_ROLES_AUTO_CHECK" DISABLE; -- ALEX: EST� DESHABILITADO EN AMBAS
 


--------------------------------------------------------
--  DDL for Trigger SC_USUARI_GRU_TAS_TR
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "SC_USUARI_GRU_TAS_TR" 
AFTER INSERT OR UPDATE OF USU_IDGRU ON SC_USUARI
FOR EACH ROW
DECLARE
   	codi_grup VARCHAR2(50);
BEGIN
	-- cercar el codi del usuari
	IF UPDATING THEN
	   SELECT gru_codi INTO codi_grup   
	   FROM SC_GRUPS  WHERE gru_id=:OLD.usu_idgru;
		INSERT INTO SC_TASQUE
	  	  (tas_id,tas_grup,tas_status,tas_data,tas_transa)
		VALUES
		  (sc_tas_seq.NEXTVAL,codi_grup,'P',SYSDATE,'UpdateGroup');
	END IF;
   SELECT gru_codi INTO codi_grup
   FROM SC_GRUPS  WHERE gru_id=:NEW.usu_idgru;
	INSERT INTO SC_TASQUE
  	  (tas_id,tas_grup,tas_status,tas_data,tas_transa)
	VALUES
	  (sc_tas_seq.NEXTVAL,codi_grup,'P',SYSDATE,'UpdateGroup');
END;
/
ALTER TRIGGER "SC_USUARI_GRU_TAS_TR" ENABLE;
 
--------------------------------
--- OTRAS TAREAS PENDIENTES: ---
--------------------------------
 -- SIN�NIMOS PARA LAS TABLAS NUEVAS
CREATE SYNONYM WWW_SEYCON.JBPM_JOB FOR SEYCON.JBPM_JOB;
CREATE SYNONYM WWW_SEYCON.SC_GRUDIS FOR SEYCON.SC_GRUDIS;
CREATE SYNONYM WWW_SEYCON.SC_ROLGRUP FOR SEYCON.SC_ROLGRUP;
CREATE SYNONYM WWW_SEYCON.SC_ROLROL FOR SEYCON.SC_ROLROL;
CREATE SYNONYM WWW_SEYCON.SC_TIPDIS FOR SEYCON.SC_TIPDIS;

-- GRANTS
GRANT DELETE, INSERT, SELECT, UPDATE ON  JBPM_JOB TO WWW_SEYCON;
GRANT DELETE, INSERT, SELECT, UPDATE ON  SC_GRUDIS TO WWW_SEYCON;
GRANT DELETE, INSERT, SELECT, UPDATE ON  SC_ROLGRUP TO WWW_SEYCON;
GRANT DELETE, INSERT, SELECT, UPDATE ON  SC_ROLROL TO WWW_SEYCON;
GRANT DELETE, INSERT, SELECT, UPDATE ON  SC_TIPDIS TO WWW_SEYCON;
 
 
-- INSERTAMOS LOS DATOS

-- SECUENCIA:
CREATE SEQUENCE SEYCON.SEQ_TIPDIS
  START WITH 1
  MAXVALUE 999999999999999999999999999
  MINVALUE 0
  NOCYCLE
  NOCACHE
  NOORDER;
--- INSERCI�N DE LOS DATOS
INSERT INTO SC_TIPDIS
SELECT SEQ_TIPDIS.NEXTVAL AS SSID, PERM,DID FROM (
SELECT  PERMISOS.PERMISO PERM, DP.DIS_ID DID
FROM SC_DISPAT dp,
(SELECT * FROM (SELECT 'E' PERMISO FROM DUAL
UNION
SELECT 'I' FROM DUAL
UNION
SELECT 'A' FROM DUAL)) PERMISOS
WHERE 1=1
ORDER BY DIS_ID);

-- INSERCIONES JBPM
UPDATE JBPM_TOKENVARIABLEMAP SET version_=1 WHERE version_ IS NULL;
UPDATE JBPM_TOKENVARIABLEMAP SET version_=1 WHERE version_ IS NULL;
UPDATE JBPM_TASKINSTANCE SET version_=1 WHERE version_ IS NULL;
UPDATE JBPM_SWIMLANEINSTANCE SET version_=1 WHERE version_ IS NULL;
UPDATE JBPM_MODULEINSTANCE SET version_=1 WHERE version_ IS NULL;
UPDATE JBPM_POOLEDACTOR SET version_=1 WHERE version_ IS NULL;
UPDATE JBPM_SWIMLANEINSTANCE SET version_=1 WHERE version_ IS NULL;
UPDATE JBPM_VARIABLEINSTANCE SET version_=1 WHERE version_ IS NULL;
UPDATE JBPM_NODE SET ISASYNCEXCL_=0 WHERE ISASYNCEXCL_ IS NULL;
UPDATE JBPM_TASK SET PRIORITY_=3 WHERE PRIORITY_ IS NULL;
