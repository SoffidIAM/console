
-- SC_TIPUSUO

--SC_DOMINI
--[ 

--]

-- SC_ADMIN_SEG_ORG_USUARI  
 
  CREATE INDEX "IDX_TASK_ACTORID" ON "JBPM_TASKINSTANCE" ("ACTORID_") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT)
  TABLESPACE "SEYCON_IND" ;
  