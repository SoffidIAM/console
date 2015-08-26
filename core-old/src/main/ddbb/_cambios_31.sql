-- NOTA:
-- He realizado una exportación de la base de datos 18 agosto 2009 en mi carpeta
-- C:\Descargas\Documentos\Script_seycon31\export.sql

--------------------------------------------------------
-- RELACIÓN ENTRE GRUPOS Y ROLES (n:m)  ==> SC_ROLGRUP
--------------------------------------------------------
-- es necesario eliminar la coulumna ROL_GRUP de la tabla SC_ROLES, ya que se
-- utiliza la tabla intermedia SC_ROLGRUP (relación n:m entre roles y grupos)
--ALTER TABLE SEYCON.SC_ROLES DROP COLUMN ROL_GRUP; -- NOTA: Al borrarla da error... ¿se mantiene?
-- esta columna es nueva (no existe en la versión 3.0, se puede borrar) 

------------
-- REVISAR:
------------
-- En la tabla de grupos también tenemos una relación con la tabla de roles
-- ¿Eliminamos esta columna? (la he quitado en la base de datos)
-- Tabla: SC_GRUPS  Columna: GRU_ADMINISTRADOR referencia a SC_ROLES
-- En el modelo se ha eliminado esta dependencia (se hace por medio de SC_ROLGRUP)
-- Dependencia (foreign key):
--  ALTER TABLE "SEYCON"."SC_GRUPS" ADD CONSTRAINT "GRU_ROL_FK" FOREIGN KEY ("GRU_ADMINISTRADOR")
--	  REFERENCES "SEYCON"."SC_ROLES" ("ROL_ID") ENABLE;
-- Eliminación de columna (y dependencia)
--	ALTER TABLE SEYCON.SC_GRUPS DROP COLUMN GRU_ADMINISTRADOR
	-- NOTA:
	-- Se ha vuelto a añadir la columna para que funcione la versión 3.0.16   

-- tabla que relaciona roles y grupos (incluida en 02_create_tables.sql)
CREATE TABLE SC_ROLGRUP
(
  RLG_ID NUMBER(10, 0) NOT NULL,
  RLG_ROL NUMBER(10, 0) NOT NULL,
  RLG_GRUP NUMBER(10, 0) NOT NULL
)
LOGGING 
NOCACHE
NOPARALLEL;

ALTER TABLE "SC_ROLGRUP" ADD (PRIMARY KEY("RLG_ID"));

-- RELACIONES con Roles y grupos (incluida en 07_update_constraints.sql)
-- ROLES
ALTER TABLE SEYCON.SC_ROLGRUP
 ADD CONSTRAINT RLG_ROLGRUP_ROLES_FK 
 FOREIGN KEY (RLG_ROL) 
 REFERENCES SEYCON.SC_ROLES (ROL_ID);
-- GRUPS 
ALTER TABLE SEYCON.SC_ROLGRUP
 ADD CONSTRAINT RLG_ROLGRUP_GRUPS_FK 
 FOREIGN KEY (RLG_GRUP) 
 REFERENCES SEYCON.SC_GRUPS (GRU_ID); 

-- permisos (incluidos en 08_permisos_2):
grant insert,update,select,delete on SC_ROLGRUP to www_seycon;

-- sinónimos (incluidos en 09_synonym_2)
CREATE OR REPLACE SYNONYM www_seycon.SC_ROLGRUP FOR SEYCON.SC_ROLGRUP;

-- índices (por grupos)
CREATE INDEX SEYCON.RLG_ROLGRUP_GRUP ON SEYCON.SC_ROLGRUP
(RLG_GRUP)
NOLOGGING
TABLESPACE SEYCON_IND
NOPARALLEL;

--------------------------------------------------------
-- ASOCIACIÓN ENTRE ROLES (contenedores y contenido)
--------------------------------------------------------

-- Constraints de la SC_ROLROL (incluido en 07_update_constraints)
-- SC_ROLROL A SC_ROLES
alter table SC_ROLROL add constraint RRL_ROLROL_ROLES_FK1 foreign key("RRL_CONTENIDOR") references "SC_ROLES"("ROL_ID")
alter table SC_ROLROL add constraint RRL_ROLROL_ROLES_FK2 foreign key("RRL_CONTINGUT") references "SC_ROLES"("ROL_ID")

-- ¿ES NECESARIO LA RESTRICCIÓN ÚNICA EN LA TABLA?

-- INDICES PARA OPTIMIZAR BÚSQUEDA POR CONTENEDOR O CONTENIDO
CREATE INDEX SEYCON.RRL_ROL_FK_1 ON SEYCON.SC_ROLROL
(RRL_CONTENIDOR)
NOLOGGING
TABLESPACE SEYCON_IND
NOPARALLEL;

CREATE INDEX SEYCON.RRL_ROL_FK_2 ON SEYCON.SC_ROLROL
(RRL_CONTINGUT)
NOLOGGING
TABLESPACE SEYCON_IND
NOPARALLEL;


------------
-- REVISAR:
------------
-- NOTA: Revisar la tabla porque tiene la check CONSTRAINT de valor repetida varias veces
-- ALTER TABLE "SEYCON"."SC_ROLES" ADD CHECK ( rol_defect IN ( 'S' , 'N' )  ) ENABLE;



----------------------------
-- UNIDADES ORGANIZATIVAS
----------------------------

-- HAY QUE CORREGIR LA CLAVE AJENA (ESTABA INCORRECTA)
-- HACÍA REFERENCIA DE ID->ID Y ES DE PARE->ID
ALTER TABLE SC_TIPUSUO ADD (
  CONSTRAINT TUO_TUO_FK 
 FOREIGN KEY (TUO_PARE) 
 REFERENCES SC_TIPUSUO (TUO_ID));

 
 
 --------------------------
 --  AGENTES
 --------------------------
 
 -- NOTA: LE FALTABA PERMISOS DE DELETE PARA EL USUARIO WWW_SEYCON
 GRANT DELETE ON SEYCON.SC_TIPDIS TO WWW_SEYCON;
 
 -- INSERTAMOS TODOS LOS TIPOS DE USUARIO EN LA TABLA (QUE ASUMIMOS ESTÁ
 -- VACÍA).. ES NECESARIA UNA SECUANCIA PARA OBTENER EL ID
 
-- DELETE FROM SC_TIPDIS -- SI NO ESTÁ VACÍA

-- SECUENCIA:
CREATE SEQUENCE SEYCON.SEQ_TIPDIS
  START WITH 1
  MAXVALUE 999999999999999999999999999
  MINVALUE 0
  NOCYCLE
  NOCACHE
  NOORDER;
--- INSERCIÓN DE LOS DATOS
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
ORDER BY DIS_ID)
