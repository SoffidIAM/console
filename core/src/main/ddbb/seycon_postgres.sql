CREATE TABLE "public"."bpm_access_log"
(
   acc_id bigint NOT NULL,
   acc_type char(1) NOT NULL,
   acc_doc_id bigint NOT NULL,
   acc_user varchar(255) NOT NULL,
   acc_date timestamp NOT NULL,
   acc_role_id bigint
)
;
CREATE TABLE "public"."bpm_access_log"
(
   acc_id bigint NOT NULL,
   acc_type char(1) NOT NULL,
   acc_doc_id bigint NOT NULL,
   acc_user varchar(255) NOT NULL,
   acc_date timestamp NOT NULL,
   acc_role_id bigint
)
;
CREATE TABLE "public"."bpm_database_property"
(
   dbp_id bigint,
   dbp_app varchar(250),
   dbp_key varchar(250),
   dbp_value varchar(1024)
)
;
CREATE TABLE "public"."bpm_document"
(
   doc_id bigint PRIMARY KEY NOT NULL,
   doc_mime_type varchar(255) NOT NULL,
   doc_external_name varchar(255),
   doc_hash varchar(255),
   doc_fs_path varchar(255) NOT NULL
)
;
CREATE TABLE "public"."bpm_file_system"
(
   fil_id bigint PRIMARY KEY NOT NULL,
   fil_application varchar(255) NOT NULL,
   fil_year bigint NOT NULL,
   fil_next_doc bigint NOT NULL
)
;
CREATE TABLE "public"."bpm_permission"
(
   per_doc_id bigint NOT NULL,
   per_rol_id bigint NOT NULL,
   CONSTRAINT bpm_permission_pkey PRIMARY KEY (per_doc_id,per_rol_id)
)
;
CREATE TABLE "public"."bpm_proc_def_properties"
(
   pdp_id bigint PRIMARY KEY NOT NULL,
   pdp_process_definition_id bigint NOT NULL,
   pdp_name varchar(1000) NOT NULL,
   pdp_value varchar(1000)
)
;
CREATE TABLE "public"."bpm_process_definition_roles"
(
   pdr_process_definition_id bigint NOT NULL,
   pdr_app_role varchar(250),
   pdr_user_role varchar(250),
   pdr_is_user bigint,
   pdr_id bigint PRIMARY KEY NOT NULL
)
;
CREATE TABLE "public"."bpm_process_defintion_roles"
(
   pdr_id bigint PRIMARY KEY NOT NULL,
   pdr_process_definition_id bigint,
   pdr_app_role varchar(250),
   pdr_user_role varchar(250),
   pdr_is_user bigint
)
;
CREATE TABLE "public"."bpm_process_file"
(
   pro_id bigint PRIMARY KEY NOT NULL,
   pro_instance_id bigint NOT NULL,
   pro_doc_id bigint NOT NULL,
   pro_doc_hash varchar(250) NOT NULL,
   pro_deleted bigint NOT NULL
)
;
CREATE TABLE "public"."bpm_role"
(
   rol_id bigint PRIMARY KEY NOT NULL,
   rol_name varchar(255) NOT NULL
)
;
CREATE TABLE "public"."bpm_sign"
(
   sig_id bigint PRIMARY KEY NOT NULL,
   sig_sign_type char(1) NOT NULL,
   sig_fs_path varchar(255) NOT NULL,
   sig_timestamp date NOT NULL,
   sig_doc_id bigint NOT NULL
)
;
CREATE TABLE "public"."bpm_user_interface"
(
   usi_id bigint PRIMARY KEY NOT NULL,
   usi_task_name varchar(250),
   usi_file_name varchar(2048),
   usi_process_definition bigint
)
;
CREATE TABLE "public"."bpm_user_interface_class"
(
   usc_id bigint PRIMARY KEY NOT NULL,
   usc_usi_id bigint NOT NULL,
   usc_class_name varchar(250) NOT NULL,
   usc_class bytea
)
;
CREATE TABLE "public"."dbcopytest"
(
   use_favs char(10)
)
;
CREATE TABLE "public"."jbpm_action"
(
   id_ bigint PRIMARY KEY NOT NULL,
   class char(1) NOT NULL,
   name_ varchar(255),
   ispropagationallowed_ bigint,
   actionexpression_ varchar(255),
   isasync_ bigint,
   referencedaction_ bigint,
   actiondelegation_ bigint,
   event_ bigint,
   processdefinition_ bigint,
   timername_ varchar(255),
   duedate_ varchar(255),
   repeat_ varchar(255),
   transitionname_ varchar(255),
   timeraction_ bigint,
   expression_ varchar(4000),
   eventindex_ bigint,
   exceptionhandler_ bigint,
   exceptionhandlerindex_ bigint
)
;
CREATE TABLE "public"."jbpm_bytearray"
(
   id_ bigint PRIMARY KEY NOT NULL,
   name_ varchar(255),
   filedefinition_ bigint
)
;
CREATE TABLE "public"."jbpm_byteblock"
(
   processfile_ bigint NOT NULL,
   bytes_ bytea,
   index_ bigint NOT NULL,
   CONSTRAINT jbpm_byteblock_pkey PRIMARY KEY (index_,processfile_)
)
;
CREATE TABLE "public"."jbpm_comment"
(
   id_ bigint PRIMARY KEY NOT NULL,
   version_ bigint NOT NULL,
   actorid_ varchar(255),
   time_ timestamp,
   message_ varchar(4000),
   token_ bigint,
   taskinstance_ bigint,
   tokenindex_ bigint,
   taskinstanceindex_ bigint
)
;
CREATE TABLE "public"."jbpm_decisionconditions"
(
   decision_ bigint NOT NULL,
   transitionname_ varchar(255),
   expression_ varchar(255),
   index_ bigint NOT NULL,
   CONSTRAINT jbpm_decisionconditions_pkey PRIMARY KEY (decision_,index_)
)
;
CREATE TABLE "public"."jbpm_delegation"
(
   id_ bigint PRIMARY KEY NOT NULL,
   classname_ varchar(4000),
   configuration_ varchar(4000),
   configtype_ varchar(255),
   processdefinition_ bigint
)
;
CREATE TABLE "public"."jbpm_event"
(
   id_ bigint PRIMARY KEY NOT NULL,
   eventtype_ varchar(255),
   type_ char(1),
   graphelement_ bigint,
   processdefinition_ bigint,
   node_ bigint,
   transition_ bigint,
   task_ bigint
)
;
CREATE TABLE "public"."jbpm_exceptionhandler"
(
   id_ bigint PRIMARY KEY NOT NULL,
   exceptionclassname_ varchar(4000),
   type_ char(1),
   graphelement_ bigint,
   processdefinition_ bigint,
   graphelementindex_ bigint,
   node_ bigint,
   transition_ bigint,
   task_ bigint
)
;
CREATE TABLE "public"."jbpm_id_group"
(
   id_ bigint PRIMARY KEY NOT NULL,
   class_ char(1) NOT NULL,
   name_ varchar(255),
   type_ varchar(255),
   parent_ bigint
)
;
CREATE TABLE "public"."jbpm_id_membership"
(
   id_ bigint PRIMARY KEY NOT NULL,
   class_ char(1) NOT NULL,
   name_ varchar(255),
   role_ varchar(255),
   user_ bigint,
   group_ bigint
)
;
CREATE TABLE "public"."jbpm_id_permissions"
(
   entity_ bigint NOT NULL,
   class_ varchar(255),
   name_ varchar(255),
   action_ varchar(255)
)
;
CREATE TABLE "public"."jbpm_id_user"
(
   id_ bigint PRIMARY KEY NOT NULL,
   class_ char(1) NOT NULL,
   name_ varchar(255),
   email_ varchar(255),
   password_ varchar(255)
)
;
CREATE TABLE "public"."jbpm_job"
(
   id_ bigint PRIMARY KEY NOT NULL,
   class_ char(1) NOT NULL,
   version_ bigint NOT NULL,
   duedate_ timestamp,
   processinstance_ bigint,
   token_ bigint,
   taskinstance_ bigint,
   issuspended_ bigint,
   isexclusive_ bigint,
   lockowner_ varchar(255),
   locktime_ timestamp,
   exception_ text,
   retries_ bigint,
   name_ varchar(255),
   repeat_ varchar(255),
   transitionname_ varchar(255),
   action_ bigint,
   graphelementtype_ varchar(255),
   graphelement_ bigint,
   node_ bigint
)
;
CREATE TABLE "public"."jbpm_log"
(
   id_ bigint PRIMARY KEY NOT NULL,
   class_ char(1) NOT NULL,
   index_ bigint,
   date_ timestamp,
   token_ bigint,
   parent_ bigint,
   message_ varchar(4000),
   exception_ varchar(4000),
   action_ bigint,
   node_ bigint,
   enter_ timestamp,
   leave_ timestamp,
   duration_ bigint,
   newlongvalue_ bigint,
   transition_ bigint,
   child_ bigint,
   sourcenode_ bigint,
   destinationnode_ bigint,
   variableinstance_ bigint,
   oldbytearray_ bigint,
   newbytearray_ bigint,
   olddatevalue_ timestamp,
   newdatevalue_ timestamp,
   olddoublevalue_ float(19),
   newdoublevalue_ float(19),
   oldlongidclass_ varchar(255),
   oldlongidvalue_ bigint,
   newlongidclass_ varchar(255),
   newlongidvalue_ bigint,
   oldstringidclass_ varchar(255),
   oldstringidvalue_ varchar(255),
   newstringidclass_ varchar(255),
   newstringidvalue_ varchar(255),
   oldlongvalue_ bigint,
   oldstringvalue_ varchar(4000),
   newstringvalue_ varchar(4000),
   taskinstance_ bigint,
   taskactorid_ varchar(255),
   taskoldactorid_ varchar(255),
   swimlaneinstance_ bigint
)
;
CREATE TABLE "public"."jbpm_message"
(
   id_ bigint PRIMARY KEY NOT NULL,
   class_ char(1) NOT NULL,
   destination_ varchar(255),
   exception_ varchar(4000),
   issuspended_ bigint,
   token_ bigint,
   text_ varchar(255),
   action_ bigint,
   node_ bigint,
   transitionname_ varchar(255),
   taskinstance_ bigint
)
;
CREATE TABLE "public"."jbpm_moduledefinition"
(
   id_ bigint PRIMARY KEY NOT NULL,
   class_ char(1) NOT NULL,
   name_ varchar(4000),
   processdefinition_ bigint,
   starttask_ bigint
)
;
CREATE TABLE "public"."jbpm_moduleinstance"
(
   id_ bigint PRIMARY KEY NOT NULL,
   class_ char(1) NOT NULL,
   processinstance_ bigint,
   taskmgmtdefinition_ bigint,
   name_ varchar(255),
   version_ bigint
)
;
CREATE TABLE "public"."jbpm_node"
(
   id_ bigint PRIMARY KEY NOT NULL,
   class_ char(1) NOT NULL,
   name_ varchar(255),
   processdefinition_ bigint,
   isasync_ bigint,
   action_ bigint,
   superstate_ bigint,
   subprocessdefinition_ bigint,
   decisionexpression_ varchar(255),
   decisiondelegation bigint,
   signal_ bigint,
   createtasks_ bigint,
   endtasks_ bigint,
   nodecollectionindex_ bigint,
   description_ varchar(4000),
   isasyncexcl_ bigint,
   subprocname_ varchar(255),
   script_ bigint,
   parentlockmode_ varchar(255)
)
;
CREATE TABLE "public"."jbpm_pooledactor"
(
   id_ bigint PRIMARY KEY NOT NULL,
   actorid_ varchar(255),
   swimlaneinstance_ bigint,
   version_ bigint
)
;
CREATE TABLE "public"."jbpm_processdefinition"
(
   id_ bigint PRIMARY KEY NOT NULL,
   name_ varchar(255),
   version_ bigint,
   isterminationimplicit_ bigint,
   startstate_ bigint,
   description_ varchar(4000),
   class_ char(1)
)
;
CREATE TABLE "public"."jbpm_processinstance"
(
   id_ bigint PRIMARY KEY NOT NULL,
   version_ bigint NOT NULL,
   start_ timestamp,
   end_ timestamp,
   issuspended_ bigint,
   processdefinition_ bigint,
   roottoken_ bigint,
   superprocesstoken_ bigint,
   key_ varchar(255)
)
;
CREATE TABLE "public"."jbpm_runtimeaction"
(
   id_ bigint PRIMARY KEY NOT NULL,
   version_ bigint NOT NULL,
   eventtype_ varchar(255),
   type_ char(1),
   graphelement_ bigint,
   processinstance_ bigint,
   action_ bigint,
   processinstanceindex_ bigint
)
;
CREATE TABLE "public"."jbpm_swimlane"
(
   id_ bigint PRIMARY KEY NOT NULL,
   name_ varchar(255),
   actoridexpression_ varchar(255),
   pooledactorsexpression_ varchar(255),
   assignmentdelegation_ bigint,
   taskmgmtdefinition_ bigint
)
;
CREATE TABLE "public"."jbpm_swimlaneinstance"
(
   id_ bigint PRIMARY KEY NOT NULL,
   name_ varchar(255),
   actorid_ varchar(255),
   swimlane_ bigint,
   taskmgmtinstance_ bigint,
   version_ bigint
)
;
CREATE TABLE "public"."jbpm_task"
(
   id_ bigint PRIMARY KEY NOT NULL,
   name_ varchar(255),
   processdefinition_ bigint,
   description_ varchar(4000),
   isblocking_ bigint,
   issignalling_ bigint,
   duedate_ varchar(255),
   actoridexpression_ varchar(255),
   pooledactorsexpression_ varchar(255),
   taskmgmtdefinition_ bigint,
   tasknode_ bigint,
   startstate_ bigint,
   assignmentdelegation_ bigint,
   swimlane_ bigint,
   taskcontroller_ bigint,
   condition_ varchar(255),
   priority_ bigint
)
;
CREATE TABLE "public"."jbpm_taskactorpool"
(
   taskinstance_ bigint NOT NULL,
   pooledactor_ bigint NOT NULL,
   CONSTRAINT jbpm_taskactorpool_pkey PRIMARY KEY (pooledactor_,taskinstance_)
)
;
CREATE TABLE "public"."jbpm_taskcontroller"
(
   id_ bigint PRIMARY KEY NOT NULL,
   taskcontrollerdelegation_ bigint
)
;
CREATE TABLE "public"."jbpm_taskinstance"
(
   id_ bigint PRIMARY KEY NOT NULL,
   class_ char(1) NOT NULL,
   name_ varchar(255),
   description_ varchar(4000),
   actorid_ varchar(255),
   create_ timestamp,
   start_ timestamp,
   end_ timestamp,
   duedate_ timestamp,
   priority_ bigint,
   iscancelled_ bigint,
   issuspended_ bigint,
   isopen_ bigint,
   issignalling_ bigint,
   isblocking_ bigint,
   task_ bigint,
   token_ bigint,
   swimlaninstance_ bigint,
   taskmgmtinstance_ bigint,
   version_ bigint,
   procinst_ bigint
)
;
CREATE TABLE "public"."jbpm_timer"
(
   id_ bigint PRIMARY KEY NOT NULL,
   name_ varchar(255),
   duedate_ timestamp,
   repeat_ varchar(255),
   transitionname_ varchar(255),
   exception_ varchar(4000),
   issuspended_ bigint,
   action_ bigint,
   token_ bigint,
   processinstance_ bigint,
   taskinstance_ bigint,
   graphelementtype_ varchar(255),
   graphelement_ bigint
)
;
CREATE TABLE "public"."jbpm_token"
(
   id_ bigint PRIMARY KEY NOT NULL,
   version_ bigint NOT NULL,
   name_ varchar(255),
   start_ timestamp,
   end_ timestamp,
   nodeenter_ timestamp,
   nextlogindex_ bigint,
   isabletoreactivateparent_ bigint,
   isterminationimplicit_ bigint,
   issuspended_ bigint,
   node_ bigint,
   processinstance_ bigint,
   parent_ bigint,
   subprocessinstance_ bigint,
   lock_ varchar(255)
)
;
CREATE TABLE "public"."jbpm_tokenvariablemap"
(
   id_ bigint PRIMARY KEY NOT NULL,
   token_ bigint,
   contextinstance_ bigint,
   version_ bigint
)
;
CREATE TABLE "public"."jbpm_transition"
(
   id_ bigint PRIMARY KEY NOT NULL,
   name_ varchar(255),
   processdefinition_ bigint,
   from_ bigint,
   to_ bigint,
   fromindex_ bigint,
   description_ varchar(4000),
   condition_ varchar(255)
)
;
CREATE TABLE "public"."jbpm_variableaccess"
(
   id_ bigint PRIMARY KEY NOT NULL,
   variablename_ varchar(255),
   access_ varchar(255),
   mappedname_ varchar(255),
   processstate_ bigint,
   taskcontroller_ bigint,
   index_ bigint,
   script_ bigint
)
;
CREATE TABLE "public"."jbpm_variableinstance"
(
   id_ bigint PRIMARY KEY NOT NULL,
   class_ char(1) NOT NULL,
   name_ varchar(255),
   converter_ char(1),
   token_ bigint,
   tokenvariablemap_ bigint,
   processinstance_ bigint,
   bytearrayvalue_ bigint,
   datevalue_ timestamp,
   doublevalue_ float(19),
   longidclass_ varchar(255),
   longvalue_ bigint,
   stringidclass_ varchar(255),
   stringvalue_ varchar(2048),
   taskinstance_ bigint,
   version_ bigint
)
;
CREATE TABLE "public"."sc_admin_seg_org_usuari"
(
   aso_id bigint,
   aso_idusu bigint NOT NULL,
   aso_idfit bigint NOT NULL
)
;
CREATE TABLE "public"."sc_adminapp"
(
   adp_id bigint,
   adp_idapp bigint NOT NULL,
   adp_idrlu bigint NOT NULL,
   CONSTRAINT sc_adminapp_pkey PRIMARY KEY (adp_idapp,adp_idrlu)
)
;
CREATE TABLE "public"."sc_admmaq"
(
   adm_id bigint PRIMARY KEY NOT NULL,
   adm_usuid bigint NOT NULL,
   adm_datcad date NOT NULL,
   adm_idwf bigint NOT NULL,
   adm_maqid bigint NOT NULL,
   adm_datini date NOT NULL
)
;
CREATE TABLE "public"."sc_aplica"
(
   apl_id bigint PRIMARY KEY NOT NULL,
   apl_codi varchar(20) NOT NULL,
   apl_nom varchar(50) NOT NULL,
   apl_dirfon varchar(50),
   apl_direct varchar(50),
   apl_bd varchar(25),
   apl_idcontact bigint,
   apl_gestionablewf varchar(1),
   apl_mailnotif varchar(512)
)
;
CREATE TABLE "public"."sc_audito"
(
   aud_id bigint NOT NULL,
   aud_idgru bigint,
   aud_idusu bigint,
   aud_data date NOT NULL,
   aud_usuaud varchar(100),
   aud_accio varchar(1) NOT NULL,
   aud_info varchar(1024),
   aud_fitxer varchar(100),
   aud_dis varchar(150),
   aud_dco varchar(100),
   aud_apl varchar(100),
   aud_lco varchar(100),
   aud_imp varchar(100),
   aud_xar varchar(100),
   aud_maq varchar(100),
   aud_rol varchar(100),
   aud_domrlu varchar(100),
   aud_valdomrlu varchar(100),
   aud_con varchar(100)
)
;
CREATE TABLE "public"."sc_autcfg"
(
   acf_server varchar(50),
   acf_topic varchar(50)
)
;
CREATE TABLE "public"."sc_autser"
(
   aus_id bigint PRIMARY KEY NOT NULL,
   aus_usuaut varchar(10),
   aus_dataut date,
   aus_idser bigint NOT NULL,
   aus_idusu bigint NOT NULL,
   aus_idmaq bigint
)
;
CREATE TABLE "public"."sc_autxar"
(
   axa_idxar bigint NOT NULL,
   axa_idusu bigint,
   axa_idrol bigint,
   axa_idgru bigint,
   axa_id bigint PRIMARY KEY NOT NULL,
   axa_nivell varchar(1) NOT NULL,
   axa_masmaq varchar(50)
)
;
CREATE TABLE "public"."sc_conacc"
(
   cac_id bigint PRIMARY KEY NOT NULL,
   cac_dis_id bigint,
   cac_rol_id bigint,
   cac_program varchar(2048) NOT NULL,
   cac_gen_usu varchar(2048),
   cac_gen_maq varchar(2048),
   cac_ipmaq_ora varchar(2048)
)
;
CREATE TABLE "public"."sc_config"
(
   con_id bigint PRIMARY KEY NOT NULL,
   con_idxar bigint,
   con_codi varchar(50) NOT NULL,
   con_valor varchar(250) NOT NULL,
   con_desc varchar(255)
)
;
CREATE TABLE "public"."sc_consell"
(
   gru_codi varchar(100),
   conselleria varchar(100)
)
;
CREATE TABLE "public"."sc_contar"
(
   cta_idtar bigint NOT NULL,
   cta_filcol varchar(10) NOT NULL,
   cta_valor varchar(10) NOT NULL,
   cta_dadaus date,
   cta_id bigint,
   CONSTRAINT sc_contar_pkey PRIMARY KEY (cta_filcol,cta_idtar)
)
;
CREATE TABLE "public"."sc_contra"
(
   ctr_idusu bigint NOT NULL,
   ctr_contra varchar(50),
   ctr_actiu varchar(1),
   ctr_ordre bigint NOT NULL,
   ctr_data date,
   ctr_datcad date,
   ctr_id bigint NOT NULL,
   CONSTRAINT sc_contra_pkey PRIMARY KEY (ctr_idusu,ctr_ordre)
)
;
CREATE TABLE "public"."sc_dadusu"
(
   dus_idusu bigint NOT NULL,
   dus_valor varchar(1024),
   dus_id bigint PRIMARY KEY NOT NULL,
   dus_tdaid bigint NOT NULL
)
;
CREATE TABLE "public"."sc_dispat"
(
   dis_id bigint PRIMARY KEY NOT NULL,
   dis_codi varchar(50),
   dis_nomcla varchar(100),
   dis_url varchar(100),
   dis_param0 varchar(100),
   dis_param1 varchar(100),
   dis_param2 varchar(100),
   dis_param3 varchar(100),
   dis_param4 varchar(100),
   dis_param5 varchar(100),
   dis_param6 varchar(100),
   dis_param7 varchar(100),
   dis_param8 varchar(100),
   dis_param9 varchar(100),
   dis_basrol varchar(1),
   dis_segur varchar(1),
   dis_url2 varchar(200),
   dis_tipusu varchar(20),
   dis_grups varchar(200),
   dis_conac varchar(1) NOT NULL
)
;
CREATE TABLE "public"."sc_domapp"
(
   dom_id bigint PRIMARY KEY NOT NULL,
   dom_app bigint,
   dom_nom varchar(30),
   dom_desc varchar(50)
)
;
CREATE TABLE "public"."sc_domcor"
(
   dco_id bigint PRIMARY KEY NOT NULL,
   dco_codi varchar(10) NOT NULL,
   dco_descri varchar(50),
   dco_obsolet varchar(5) NOT NULL
)
;
CREATE TABLE "public"."sc_exepue"
(
   epe_idpue bigint NOT NULL,
   epe_ambit varchar(1) NOT NULL,
   epe_codexe varchar(10) NOT NULL,
   epe_contin varchar(4000) NOT NULL,
   epe_id bigint PRIMARY KEY NOT NULL
)
;
CREATE TABLE "public"."sc_extlco"
(
   elc_idlco bigint NOT NULL,
   elc_adreca varchar(50) NOT NULL,
   elc_id bigint NOT NULL
)
;
CREATE TABLE "public"."sc_farren"
(
   far_id bigint PRIMARY KEY NOT NULL,
   far_idusu bigint,
   far_data date,
   far_ren date,
   far_actiu varchar(3)
)
;
CREATE TABLE "public"."sc_fitxer"
(
   fit_id bigint PRIMARY KEY NOT NULL,
   fit_nom varchar(450) NOT NULL,
   fit_fin varchar(350),
   fit_norma varchar(900),
   fit_nivel varchar(600) NOT NULL,
   fit_sist varchar(2000),
   fit_regapd varchar(9) NOT NULL,
   fit_nomapd varchar(450),
   fit_datapd date,
   fit_disleg varchar(300),
   fit_dcarid varchar(2000),
   fit_dcarpe varchar(2000),
   fit_dcirso varchar(2000),
   fit_dacpro varchar(2000),
   fit_demple varchar(2000),
   fit_dincor varchar(2000),
   fit_decfin varchar(2000),
   fit_dtrans varchar(2000),
   fit_dcomin varchar(2000),
   fit_cons varchar(200),
   fit_rsorg varchar(200),
   fit_codapd varchar(50),
   fit_obs varchar(2000),
   fit_datbai date,
   fit_motbai varchar(500),
   fit_idresp bigint,
   fit_iddg bigint,
   fit_idrstec bigint
)
;
CREATE TABLE "public"."sc_grudis"
(
   grd_id bigint,
   grd_idgrup bigint,
   grd_iddis bigint
)
;
CREATE TABLE "public"."sc_gruimp"
(
   gim_idgru bigint NOT NULL,
   gim_idimp bigint NOT NULL,
   gim_ordre bigint,
   gim_id bigint NOT NULL,
   CONSTRAINT sc_gruimp_pkey PRIMARY KEY (gim_idgru,gim_idimp)
)
;
CREATE TABLE "public"."sc_grups"
(
   gru_id bigint PRIMARY KEY NOT NULL,
   gru_codi varchar(20) NOT NULL,
   gru_descri varchar(100),
   gru_idmaq bigint,
   gru_quota bigint,
   gru_uniofi varchar(2),
   gru_pare bigint,
   gru_tipus bigint,
   gru_administrador bigint,
   gru_obsolet varchar(5) NOT NULL,
   gru_organitzatiu varchar(300),
   gru_secpre varchar(50)
)
;
CREATE TABLE "public"."sc_grupue"
(
   gpe_idgru bigint NOT NULL,
   gpe_nivaut varchar(1) NOT NULL,
   gpe_idpue bigint NOT NULL,
   gpe_id bigint PRIMARY KEY NOT NULL
)
;
CREATE TABLE "public"."sc_icones"
(
   ico_id bigint PRIMARY KEY NOT NULL,
   ico_icona bytea
)
;
CREATE TABLE "public"."sc_impres"
(
   imp_id bigint PRIMARY KEY NOT NULL,
   imp_model varchar(50),
   imp_codi varchar(12) NOT NULL,
   imp_idmaq bigint NOT NULL,
   imp_local varchar(1)
)
;
CREATE TABLE "public"."sc_lcolco"
(
   llc_idlco1 bigint NOT NULL,
   llc_idlco2 bigint NOT NULL,
   llc_id bigint NOT NULL,
   CONSTRAINT sc_lcolco_pkey PRIMARY KEY (llc_idlco1,llc_idlco2)
)
;
CREATE TABLE "public"."sc_llicor"
(
   lco_id bigint PRIMARY KEY NOT NULL,
   lco_descri varchar(50),
   lco_nom varchar(25) NOT NULL,
   lco_iddco bigint
)
;
CREATE TABLE "public"."sc_maquin"
(
   maq_id bigint PRIMARY KEY NOT NULL,
   maq_nom varchar(25) NOT NULL,
   maq_sisope varchar(3) NOT NULL,
   maq_adrip varchar(25),
   maq_adrmac varchar(25),
   maq_descri varchar(50),
   maq_pardhc varchar(50),
   maq_correu varchar(1) NOT NULL,
   maq_ofimat varchar(1) NOT NULL,
   maq_idxar bigint NOT NULL,
   maq_alias varchar(2048),
   maq_usuadm varchar(50),
   maq_contra varchar(50),
   maq_datcon date
)
;
CREATE TABLE "public"."sc_maquinalias"
(
   mal_id bigint PRIMARY KEY NOT NULL,
   mal_maqid bigint NOT NULL,
   mal_alias varchar(2048)
)
;
CREATE TABLE "public"."sc_notifica"
(
   ntf_id bigint PRIMARY KEY NOT NULL,
   ntf_apl bigint NOT NULL,
   ntf_rol bigint,
   ntf_usu bigint NOT NULL,
   ntf_data date,
   ntf_info varchar(512)
)
;
CREATE TABLE "public"."sc_petfar"
(
   pef_id bigint PRIMARY KEY NOT NULL,
   pef_data date,
   pef_pet bytea,
   pef_sign bytea,
   pef_tipo varchar(10),
   pef_sigref varchar(128),
   pef_docref varchar(128),
   pef_idusu bigint
)
;
CREATE TABLE "public"."sc_politi"
(
   pol_id bigint PRIMARY KEY NOT NULL,
   pol_codi varchar(10) NOT NULL,
   pol_descri varchar(50)
)
;
CREATE TABLE "public"."sc_polusu"
(
   plu_idpol bigint NOT NULL,
   plu_idusu bigint NOT NULL,
   CONSTRAINT sc_polusu_pkey PRIMARY KEY (plu_idpol,plu_idusu)
)
;
CREATE TABLE "public"."sc_porcpd"
(
   pcp_id bigint PRIMARY KEY NOT NULL,
   pcp_nom varchar(50) NOT NULL,
   pcp_descr varchar(255)
)
;
CREATE TABLE "public"."sc_ports"
(
   por_id bigint PRIMARY KEY NOT NULL,
   por_idmaq bigint NOT NULL,
   por_socket bigint,
   por_idser bigint NOT NULL
)
;
CREATE TABLE "public"."sc_puepue"
(
   ppe_idpefi bigint NOT NULL,
   ppe_idpepa bigint NOT NULL,
   ppe_ordre bigint NOT NULL,
   ppe_id bigint PRIMARY KEY NOT NULL
)
;
CREATE TABLE "public"."sc_punent"
(
   pue_id bigint PRIMARY KEY NOT NULL,
   pue_idapl bigint NOT NULL,
   pue_codi varchar(10),
   pue_nom varchar(128) NOT NULL,
   pue_visibl varchar(1) NOT NULL,
   pue_icon bigint,
   pue_icon2 bigint,
   pue_menu varchar(1) NOT NULL,
   pue_numcol bigint,
   pue_public varchar(1),
   pue_tipmen varchar(1),
   pue_xml text
)
;
CREATE TABLE "public"."sc_regacc"
(
   rac_id bigint PRIMARY KEY NOT NULL,
   rac_idses varchar(200),
   rac_datini date,
   rac_datfi date,
   rac_idmaq bigint,
   rac_idusu bigint,
   rac_idmaor bigint,
   rac_idser bigint,
   rac_codage varchar(50),
   rac_info varchar(1024),
   rac_tipusacc varchar(1)
)
;
CREATE TABLE "public"."sc_regcpd"
(
   rcp_id varchar(10) PRIMARY KEY NOT NULL,
   rcp_idpcp bigint NOT NULL,
   rcp_codtcp varchar(4),
   rcp_data date
)
;
CREATE TABLE "public"."sc_resp_seg_org_usuari"
(
   res_id bigint PRIMARY KEY NOT NULL,
   res_idusu bigint,
   res_idfit bigint
)
;
CREATE TABLE "public"."sc_roles"
(
   rol_id bigint PRIMARY KEY NOT NULL,
   rol_nom varchar(150) NOT NULL,
   rol_descri varchar(150) NOT NULL,
   rol_idapl bigint NOT NULL,
   rol_defect varchar(1) NOT NULL,
   rol_contra varchar(1),
   rol_iddispat bigint NOT NULL,
   rol_domapp bigint,
   rol_tipdom varchar(50),
   rol_bd varchar(50),
   rol_gest_wf varchar(1)
)
;
CREATE TABLE "public"."sc_rolfit"
(
   rlf_idrol bigint NOT NULL,
   rlf_idfit bigint NOT NULL,
   rlf_id bigint NOT NULL,
   CONSTRAINT sc_rolfit_pkey PRIMARY KEY (rlf_idfit,rlf_idrol)
)
;
CREATE TABLE "public"."sc_rolgrup"
(
   rlg_id bigint PRIMARY KEY NOT NULL,
   rlg_rol bigint NOT NULL,
   rlg_grup bigint NOT NULL
)
;
CREATE TABLE "public"."sc_rolpue"
(
   rpe_idpue bigint NOT NULL,
   rpe_nivaut varchar(1) NOT NULL,
   rpe_idrol bigint NOT NULL,
   rpe_id bigint PRIMARY KEY NOT NULL
)
;
CREATE TABLE "public"."sc_rolrol"
(
   rrl_id bigint PRIMARY KEY NOT NULL,
   rrl_contenidor bigint,
   rrl_contingut bigint,
   rrl_tipdom varchar(20),
   rrl_aplica bigint,
   rrl_grup bigint,
   rrl_valdom bigint
)
;
CREATE TABLE "public"."sc_rolusu"
(
   rlu_idrol bigint NOT NULL,
   rlu_idusu bigint NOT NULL,
   rlu_rolusu_gru bigint,
   rlu_admapp bigint,
   rlu_valdom bigint,
   rlu_tipdom varchar(20) NOT NULL,
   rlu_id bigint PRIMARY KEY NOT NULL
)
;
CREATE TABLE "public"."sc_secret"
(
   sec_id bigint PRIMARY KEY NOT NULL,
   sec_idusu bigint,
   sec_valor bytea,
   sec_idsrv bigint
)
;
CREATE TABLE "public"."sc_servei"
(
   ser_id bigint PRIMARY KEY NOT NULL,
   ser_codi varchar(10) NOT NULL,
   ser_descri varchar(50)
)
;
CREATE TABLE "public"."sc_server"
(
   srv_id bigint PRIMARY KEY NOT NULL,
   srv_nom varchar(100) NOT NULL,
   srv_pk bytea
)
;
CREATE TABLE "public"."sc_sessio"
(
   ses_id bigint PRIMARY KEY NOT NULL,
   ses_idmaq bigint NOT NULL,
   ses_port bigint NOT NULL,
   ses_idusu bigint NOT NULL,
   ses_idmacl bigint,
   ses_key varchar(50),
   ses_key2 varchar(50)
)
;
CREATE TABLE "public"."sc_sso"
(
   sso_id bigint PRIMARY KEY NOT NULL,
   sso_ordre bigint NOT NULL,
   sso_idgru bigint,
   sso_idusu bigint,
   sso_nomapl varchar(250),
   sso_idmaq bigint,
   sso_socket bigint NOT NULL,
   sso_idser bigint NOT NULL,
   sso_tipus varchar(1)
)
;
CREATE TABLE "public"."sc_tarcpd"
(
   tcp_codi varchar(4) PRIMARY KEY NOT NULL,
   tcp_idusu bigint NOT NULL,
   tcp_id bigint NOT NULL,
   tcp_foto varchar(50)
)
;
CREATE TABLE "public"."sc_target"
(
   tar_id bigint PRIMARY KEY NOT NULL,
   tar_idusu bigint NOT NULL,
   tar_codi varchar(10) NOT NULL,
   tar_datemi date NOT NULL,
   tar_datcad date NOT NULL,
   tar_activa varchar(1) NOT NULL
)
;
CREATE TABLE "public"."sc_tasklog"
(
   tlo_idtasque bigint NOT NULL,
   tlo_codidispat varchar(50) NOT NULL,
   tlo_complete varchar(1),
   tlo_missat varchar(1024)
   CONSTRAINT sc_tasklog_pkey PRIMARY KEY (tlo_codidispat,tlo_idtasque)
)
;
CREATE TABLE "public"."sc_tasque"
(
   tas_id bigint PRIMARY KEY NOT NULL,
   tas_usuari varchar(10),
   tas_contra varchar(128),
   tas_cancon varchar(1),
   tas_carpet varchar(50),
   tas_tipcar varchar(1),
   tas_impres varchar(12),
   tas_maquin varchar(25),
   tas_subxar varchar(10),
   tas_missat varchar(1024),
   tas_status varchar(1),
   tas_data date NOT NULL,
   tas_transa varchar(50) NOT NULL,
   tas_grup varchar(20),
   tas_alies varchar(50),
   tas_domcor varchar(50),
   tas_role varchar(50),
   tas_bd varchar(50),
   tas_coddis varchar(50),
   tas_server varchar(1024),
   tas_hash varchar(200)
)
;
CREATE TABLE "public"."sc_tipdad"
(
   tda_codi varchar(25) PRIMARY KEY NOT NULL,
   tda_ordre bigint NOT NULL,
   tda_id bigint
)
;
CREATE TABLE "public"."sc_tipdis"
(
   tpd_id bigint,
   tpd_tipus varchar(10),
   tpd_iddis bigint
)
;
CREATE TABLE "public"."sc_tipexe"
(
   exe_codi varchar(10) PRIMARY KEY NOT NULL,
   exe_mime varchar(50) NOT NULL,
   exe_planti varchar(2000),
   exe_id bigint
)
;
CREATE TABLE "public"."sc_tipusuo"
(
   tuo_id bigint PRIMARY KEY NOT NULL,
   tuo_codi varchar(20) NOT NULL,
   tuo_desc varchar(50) NOT NULL,
   tuo_pare bigint
)
;
CREATE TABLE "public"."sc_usuari"
(
   usu_id bigint PRIMARY KEY NOT NULL,
   usu_codi varchar(10) NOT NULL,
   usu_nom varchar(50) NOT NULL,
   usu_prilli varchar(50) NOT NULL,
   usu_seglli varchar(50),
   usu_nomcur varchar(25),
   usu_unofes varchar(50),
   usu_usumod varchar(10),
   usu_datmod date,
   usu_datcre date NOT NULL,
   usu_usucre varchar(10) NOT NULL,
   usu_iddco bigint,
   usu_idgru bigint NOT NULL,
   usu_idmaq bigint NOT NULL,
   usu_idmaco bigint NOT NULL,
   usu_idmapr bigint NOT NULL,
   usu_actiu varchar(1) NOT NULL,
   usu_alcoan varchar(240),
   usu_pend varchar(1),
   usu_quota bigint,
   usu_mulses varchar(1) NOT NULL,
   usu_coment varchar(1024),
   usu_tipusu varchar(1)
)
;
CREATE TABLE "public"."sc_usugru"
(
   ugr_idgru bigint NOT NULL,
   ugr_idusu bigint NOT NULL,
   ugr_id bigint NOT NULL,
   CONSTRAINT sc_usugru_pkey PRIMARY KEY (ugr_idgru,ugr_idusu)
)
;
CREATE TABLE "public"."sc_usuimp"
(
   uim_idimp bigint NOT NULL,
   uim_idusu bigint NOT NULL,
   uim_ordre bigint,
   uim_id bigint PRIMARY KEY NOT NULL
)
;
CREATE TABLE "public"."sc_usulco"
(
   ulc_idlco bigint NOT NULL,
   ulc_idusu bigint NOT NULL,
   ulc_id bigint NOT NULL,
   CONSTRAINT sc_usulco_pkey PRIMARY KEY (ulc_idlco,ulc_idusu)
)
;
CREATE TABLE "public"."sc_usupue"
(
   upe_idpue bigint NOT NULL,
   upe_nivaut varchar(1) NOT NULL,
   upe_idusu bigint NOT NULL,
   upe_id bigint PRIMARY KEY NOT NULL
)
;
CREATE TABLE "public"."sc_ususeu"
(
   use_id bigint PRIMARY KEY NOT NULL,
   use_usuid bigint NOT NULL,
   use_datseu date,
   use_verseu varchar(50),
   use_favs varchar(1024)
)
;
CREATE TABLE "public"."sc_valor_domini"
(
   vdo_id bigint PRIMARY KEY NOT NULL,
   vdo_valor varchar(30) NOT NULL,
   vdo_dom bigint,
   vdo_desc varchar(50)
)
;
CREATE TABLE "public"."sc_wl_logaut"
(
   log_dat date NOT NULL,
   log_tipo char(1) NOT NULL,
   log_login varchar(50) NOT NULL,
   log_codusu varchar(50),
   log_resul char(1) NOT NULL,
   log_certif varchar(4000),
   log_ip varchar(240)
)
;
CREATE TABLE "public"."sc_wl_usuari"
(
   usu_codi varchar(50) PRIMARY KEY NOT NULL,
   usu_pass varchar(255),
   usu_datcad date,
   usu_nom varchar(200),
   usu_nif varchar(15)
)
;
CREATE TABLE "public"."sc_wl_usugru"
(
   ugr_codusu varchar(50) NOT NULL,
   ugr_codgru varchar(150) NOT NULL,
   CONSTRAINT sc_wl_usugru_pkey PRIMARY KEY (ugr_codgru,ugr_codusu)
)
;
CREATE TABLE "public"."sc_xarxes"
(
   xar_id bigint PRIMARY KEY NOT NULL,
   xar_codi varchar(10) NOT NULL,
   xar_adrip varchar(25) NOT NULL,
   xar_descri varchar(50),
   xar_masip varchar(25),
   xar_norm varchar(1) NOT NULL,
   xar_pardhc varchar(50)
)
;
CREATE UNIQUE INDEX bpm_document_pkey ON "public"."bpm_document"(doc_id)
;
CREATE UNIQUE INDEX sys_c004043 ON "public"."bpm_document"(doc_fs_path)
;
CREATE UNIQUE INDEX sys_c004042 ON "public"."bpm_document"(doc_id)
;
CREATE UNIQUE INDEX sys_c004048 ON "public"."bpm_file_system"(fil_id)
;
CREATE UNIQUE INDEX bpm_file_system_pkey ON "public"."bpm_file_system"(fil_id)
;
ALTER TABLE "public"."bpm_permission"
ADD CONSTRAINT bpm_permission_per_rol_id_fkey
FOREIGN KEY (per_rol_id)
REFERENCES "public"."bpm_role"(per_rol_id)
;
ALTER TABLE "public"."bpm_permission"
ADD CONSTRAINT bpm_permission_per_doc_id_fkey
FOREIGN KEY (per_doc_id)
REFERENCES "public"."bpm_document"(per_doc_id)
;
CREATE UNIQUE INDEX sys_c004051 ON "public"."bpm_permission"
(
  per_rol_id,
  per_doc_id
)
;
CREATE UNIQUE INDEX bpm_permission_pkey ON "public"."bpm_permission"
(
  per_doc_id,
  per_rol_id
)
;
CREATE UNIQUE INDEX bpm_process__pk ON "public"."bpm_proc_def_properties"(pdp_id)
;
CREATE UNIQUE INDEX bpm_proc_def_properties_pkey ON "public"."bpm_proc_def_properties"(pdp_id)
;
CREATE UNIQUE INDEX bpm_pdp_uk ON "public"."bpm_proc_def_properties"
(
  pdp_process_definition_id,
  pdp_name
)
;
CREATE UNIQUE INDEX bpm_process_definition_roles_pkey ON "public"."bpm_process_definition_roles"(pdr_id)
;
CREATE UNIQUE INDEX sys_c004058 ON "public"."bpm_process_definition_roles"(pdr_id)
;
CREATE UNIQUE INDEX bpm_process_defintion_roles_pkey ON "public"."bpm_process_defintion_roles"(pdr_id)
;
CREATE UNIQUE INDEX sys_c004059 ON "public"."bpm_process_defintion_roles"(pdr_id)
;
CREATE UNIQUE INDEX sys_c004064 ON "public"."bpm_process_file"(pro_id)
;
CREATE UNIQUE INDEX bpm_process_file_pkey ON "public"."bpm_process_file"(pro_id)
;
CREATE UNIQUE INDEX bpm_role_pkey ON "public"."bpm_role"(rol_id)
;
CREATE UNIQUE INDEX sys_c004068 ON "public"."bpm_role"(rol_name)
;
CREATE UNIQUE INDEX sys_c004067 ON "public"."bpm_role"(rol_id)
;
ALTER TABLE "public"."bpm_sign"
ADD CONSTRAINT bpm_sign_sig_doc_id_fkey
FOREIGN KEY (sig_doc_id)
REFERENCES "public"."bpm_document"(sig_doc_id)
;
CREATE UNIQUE INDEX sys_c004075 ON "public"."bpm_sign"(sig_fs_path)
;
CREATE UNIQUE INDEX sys_c004074 ON "public"."bpm_sign"(sig_id)
;
CREATE UNIQUE INDEX bpm_sign_pkey ON "public"."bpm_sign"(sig_id)
;
CREATE UNIQUE INDEX sys_c004076 ON "public"."bpm_user_interface"(usi_id)
;
CREATE UNIQUE INDEX bpm_user_interface_pkey ON "public"."bpm_user_interface"(usi_id)
;
CREATE UNIQUE INDEX bpm_user_interface_class_pkey ON "public"."bpm_user_interface_class"(usc_id)
;
CREATE UNIQUE INDEX sys_c004079 ON "public"."bpm_user_interface_class"(usc_id)
;
ALTER TABLE "public"."jbpm_action"
ADD CONSTRAINT jbpm_action_timeraction__fkey
FOREIGN KEY (timeraction_)
REFERENCES "public"."jbpm_action"(timeraction_)
;
ALTER TABLE "public"."jbpm_action"
ADD CONSTRAINT jbpm_action_referencedaction__fkey
FOREIGN KEY (referencedaction_)
REFERENCES "public"."jbpm_action"(referencedaction_)
;
ALTER TABLE "public"."jbpm_action"
ADD CONSTRAINT jbpm_action_actiondelegation__fkey
FOREIGN KEY (actiondelegation_)
REFERENCES "public"."jbpm_delegation"(actiondelegation_)
;
ALTER TABLE "public"."jbpm_action"
ADD CONSTRAINT jbpm_action_exceptionhandler__fkey
FOREIGN KEY (exceptionhandler_)
REFERENCES "public"."jbpm_exceptionhandler"(exceptionhandler_)
;
ALTER TABLE "public"."jbpm_action"
ADD CONSTRAINT jbpm_action_event__fkey
FOREIGN KEY (event_)
REFERENCES "public"."jbpm_event"(event_)
;
CREATE UNIQUE INDEX jbpm_action_pkey ON "public"."jbpm_action"(id_)
;
CREATE UNIQUE INDEX sys_c004082 ON "public"."jbpm_action"(id_)
;
CREATE UNIQUE INDEX jbpm_bytearray_pkey ON "public"."jbpm_bytearray"(id_)
;
CREATE UNIQUE INDEX sys_c004084 ON "public"."jbpm_bytearray"(id_)
;
CREATE INDEX jbpm_bytearr_fdefinition ON "public"."jbpm_bytearray"(filedefinition_)
;
ALTER TABLE "public"."jbpm_byteblock"
ADD CONSTRAINT jbpm_byteblock_processfile__fkey
FOREIGN KEY (processfile_)
REFERENCES "public"."jbpm_bytearray"(processfile_)
;
CREATE UNIQUE INDEX sys_c004087 ON "public"."jbpm_byteblock"
(
  processfile_,
  index_
)
;
CREATE UNIQUE INDEX jbpm_byteblock_pkey ON "public"."jbpm_byteblock"
(
  index_,
  processfile_
)
;
ALTER TABLE "public"."jbpm_comment"
ADD CONSTRAINT jbpm_comment_taskinstance__fkey
FOREIGN KEY (taskinstance_)
REFERENCES "public"."jbpm_taskinstance"(taskinstance_)
;
ALTER TABLE "public"."jbpm_comment"
ADD CONSTRAINT jbpm_comment_token__fkey
FOREIGN KEY (token_)
REFERENCES "public"."jbpm_token"(token_)
;
CREATE UNIQUE INDEX sys_c004090 ON "public"."jbpm_comment"(id_)
;
CREATE UNIQUE INDEX jbpm_comment_pkey ON "public"."jbpm_comment"(id_)
;
ALTER TABLE "public"."jbpm_decisionconditions"
ADD CONSTRAINT jbpm_decisionconditions_decision__fkey
FOREIGN KEY (decision_)
REFERENCES "public"."jbpm_node"(decision_)
;
CREATE UNIQUE INDEX jbpm_decisionconditions_pkey ON "public"."jbpm_decisionconditions"
(
  decision_,
  index_
)
;
CREATE UNIQUE INDEX sys_c004093 ON "public"."jbpm_decisionconditions"
(
  decision_,
  index_
)
;
CREATE UNIQUE INDEX sys_c004095 ON "public"."jbpm_delegation"(id_)
;
CREATE UNIQUE INDEX jbpm_delegation_pkey ON "public"."jbpm_delegation"(id_)
;
ALTER TABLE "public"."jbpm_event"
ADD CONSTRAINT jbpm_event_processdefinition__fkey
FOREIGN KEY (processdefinition_)
REFERENCES "public"."jbpm_processdefinition"(processdefinition_)
;
ALTER TABLE "public"."jbpm_event"
ADD CONSTRAINT jbpm_event_task__fkey
FOREIGN KEY (task_)
REFERENCES "public"."jbpm_task"(task_)
;
CREATE UNIQUE INDEX sys_c004097 ON "public"."jbpm_event"(id_)
;
CREATE UNIQUE INDEX jbpm_event_pkey ON "public"."jbpm_event"(id_)
;
CREATE UNIQUE INDEX sys_c004099 ON "public"."jbpm_exceptionhandler"(id_)
;
CREATE UNIQUE INDEX jbpm_exceptionhandler_pkey ON "public"."jbpm_exceptionhandler"(id_)
;
ALTER TABLE "public"."jbpm_id_group"
ADD CONSTRAINT jbpm_id_group_parent__fkey
FOREIGN KEY (parent_)
REFERENCES "public"."jbpm_id_group"(parent_)
;
CREATE UNIQUE INDEX sys_c004102 ON "public"."jbpm_id_group"(id_)
;
CREATE UNIQUE INDEX jbpm_id_group_pkey ON "public"."jbpm_id_group"(id_)
;
ALTER TABLE "public"."jbpm_id_membership"
ADD CONSTRAINT jbpm_id_membership_group__fkey
FOREIGN KEY (group_)
REFERENCES "public"."jbpm_id_group"(group_)
;
ALTER TABLE "public"."jbpm_id_membership"
ADD CONSTRAINT jbpm_id_membership_user__fkey
FOREIGN KEY (user_)
REFERENCES "public"."jbpm_id_user"(user_)
;
CREATE UNIQUE INDEX jbpm_id_membership_pkey ON "public"."jbpm_id_membership"(id_)
;
CREATE UNIQUE INDEX sys_c004105 ON "public"."jbpm_id_membership"(id_)
;
CREATE UNIQUE INDEX sys_c004109 ON "public"."jbpm_id_user"(id_)
;
CREATE UNIQUE INDEX jbpm_id_user_pkey ON "public"."jbpm_id_user"(id_)
;
ALTER TABLE "public"."jbpm_job"
ADD CONSTRAINT jbpm_job_action__fkey
FOREIGN KEY (action_)
REFERENCES "public"."jbpm_action"(action_)
;
ALTER TABLE "public"."jbpm_job"
ADD CONSTRAINT jbpm_job_processinstance__fkey
FOREIGN KEY (processinstance_)
REFERENCES "public"."jbpm_processinstance"(processinstance_)
;
ALTER TABLE "public"."jbpm_job"
ADD CONSTRAINT jbpm_job_taskinstance__fkey
FOREIGN KEY (taskinstance_)
REFERENCES "public"."jbpm_taskinstance"(taskinstance_)
;
ALTER TABLE "public"."jbpm_job"
ADD CONSTRAINT jbpm_job_node__fkey
FOREIGN KEY (node_)
REFERENCES "public"."jbpm_node"(node_)
;
ALTER TABLE "public"."jbpm_job"
ADD CONSTRAINT jbpm_job_token__fkey
FOREIGN KEY (token_)
REFERENCES "public"."jbpm_token"(token_)
;
CREATE INDEX idx_job_prinst ON "public"."jbpm_job"(processinstance_)
;
CREATE UNIQUE INDEX jbpm_job_pkey ON "public"."jbpm_job"(id_)
;
CREATE UNIQUE INDEX sys_c005353 ON "public"."jbpm_job"(id_)
;
CREATE INDEX idx_job_token ON "public"."jbpm_job"(token_)
;
CREATE INDEX idx_job_tskinst ON "public"."jbpm_job"(taskinstance_)
;
ALTER TABLE "public"."jbpm_log"
ADD CONSTRAINT jbpm_log_newbytearray__fkey
FOREIGN KEY (newbytearray_)
REFERENCES "public"."jbpm_bytearray"(newbytearray_)
;
ALTER TABLE "public"."jbpm_log"
ADD CONSTRAINT jbpm_log_taskinstance__fkey
FOREIGN KEY (taskinstance_)
REFERENCES "public"."jbpm_taskinstance"(taskinstance_)
;
ALTER TABLE "public"."jbpm_log"
ADD CONSTRAINT jbpm_log_token__fkey
FOREIGN KEY (token_)
REFERENCES "public"."jbpm_token"(token_)
;
ALTER TABLE "public"."jbpm_log"
ADD CONSTRAINT jbpm_log_destinationnode__fkey
FOREIGN KEY (destinationnode_)
REFERENCES "public"."jbpm_node"(destinationnode_)
;
ALTER TABLE "public"."jbpm_log"
ADD CONSTRAINT jbpm_log_variableinstance__fkey
FOREIGN KEY (variableinstance_)
REFERENCES "public"."jbpm_variableinstance"(variableinstance_)
;
ALTER TABLE "public"."jbpm_log"
ADD CONSTRAINT jbpm_log_oldbytearray__fkey
FOREIGN KEY (oldbytearray_)
REFERENCES "public"."jbpm_bytearray"(oldbytearray_)
;
ALTER TABLE "public"."jbpm_log"
ADD CONSTRAINT jbpm_log_node__fkey
FOREIGN KEY (node_)
REFERENCES "public"."jbpm_node"(node_)
;
ALTER TABLE "public"."jbpm_log"
ADD CONSTRAINT jbpm_log_action__fkey
FOREIGN KEY (action_)
REFERENCES "public"."jbpm_action"(action_)
;
ALTER TABLE "public"."jbpm_log"
ADD CONSTRAINT jbpm_log_child__fkey
FOREIGN KEY (child_)
REFERENCES "public"."jbpm_token"(child_)
;
ALTER TABLE "public"."jbpm_log"
ADD CONSTRAINT jbpm_log_transition__fkey
FOREIGN KEY (transition_)
REFERENCES "public"."jbpm_transition"(transition_)
;
ALTER TABLE "public"."jbpm_log"
ADD CONSTRAINT jbpm_log_parent__fkey
FOREIGN KEY (parent_)
REFERENCES "public"."jbpm_log"(parent_)
;
ALTER TABLE "public"."jbpm_log"
ADD CONSTRAINT jbpm_log_sourcenode__fkey
FOREIGN KEY (sourcenode_)
REFERENCES "public"."jbpm_node"(sourcenode_)
;
ALTER TABLE "public"."jbpm_log"
ADD CONSTRAINT jbpm_log_swimlaneinstance__fkey
FOREIGN KEY (swimlaneinstance_)
REFERENCES "public"."jbpm_swimlaneinstance"(swimlaneinstance_)
;
CREATE INDEX jbpm_log_parent ON "public"."jbpm_log"(parent_)
;
CREATE INDEX jbpm_log_swiminst ON "public"."jbpm_log"(swimlaneinstance_)
;
CREATE INDEX jbpm_log_oldbytearray ON "public"."jbpm_log"(oldbytearray_)
;
CREATE UNIQUE INDEX sys_c004112 ON "public"."jbpm_log"(id_)
;
CREATE INDEX jbpm_log_action ON "public"."jbpm_log"(action_)
;
CREATE INDEX jbpm_log_child ON "public"."jbpm_log"(child_)
;
CREATE INDEX jbpm_log_taskinstance ON "public"."jbpm_log"(taskinstance_)
;
CREATE INDEX jbpm_log_transition ON "public"."jbpm_log"(transition_)
;
CREATE INDEX jbpm_log_token ON "public"."jbpm_log"(token_)
;
CREATE INDEX jbpm_log_node ON "public"."jbpm_log"(node_)
;
CREATE INDEX jbpm_log_varinstance ON "public"."jbpm_log"(variableinstance_)
;
CREATE INDEX jbpm_log_destinationnode ON "public"."jbpm_log"(destinationnode_)
;
CREATE INDEX jbpm_log_sourcenode ON "public"."jbpm_log"(sourcenode_)
;
CREATE INDEX jbpm_log_newbytearray ON "public"."jbpm_log"(newbytearray_)
;
CREATE UNIQUE INDEX jbpm_log_pkey ON "public"."jbpm_log"(id_)
;
ALTER TABLE "public"."jbpm_message"
ADD CONSTRAINT jbpm_message_node__fkey
FOREIGN KEY (node_)
REFERENCES "public"."jbpm_node"(node_)
;
ALTER TABLE "public"."jbpm_message"
ADD CONSTRAINT jbpm_message_token__fkey
FOREIGN KEY (token_)
REFERENCES "public"."jbpm_token"(token_)
;
ALTER TABLE "public"."jbpm_message"
ADD CONSTRAINT jbpm_message_action__fkey
FOREIGN KEY (action_)
REFERENCES "public"."jbpm_action"(action_)
;
ALTER TABLE "public"."jbpm_message"
ADD CONSTRAINT jbpm_message_taskinstance__fkey
FOREIGN KEY (taskinstance_)
REFERENCES "public"."jbpm_taskinstance"(taskinstance_)
;
CREATE UNIQUE INDEX jbpm_message_pkey ON "public"."jbpm_message"(id_)
;
CREATE UNIQUE INDEX sys_c004115 ON "public"."jbpm_message"(id_)
;
ALTER TABLE "public"."jbpm_moduledefinition"
ADD CONSTRAINT jbpm_moduledefinition_processdefinition__fkey
FOREIGN KEY (processdefinition_)
REFERENCES "public"."jbpm_processdefinition"(processdefinition_)
;
ALTER TABLE "public"."jbpm_moduledefinition"
ADD CONSTRAINT jbpm_moduledefinition_starttask__fkey
FOREIGN KEY (starttask_)
REFERENCES "public"."jbpm_task"(starttask_)
;
CREATE UNIQUE INDEX jbpm_moduledefinition_pkey ON "public"."jbpm_moduledefinition"(id_)
;
CREATE UNIQUE INDEX sys_c004118 ON "public"."jbpm_moduledefinition"(id_)
;
ALTER TABLE "public"."jbpm_moduleinstance"
ADD CONSTRAINT jbpm_moduleinstance_processinstance__fkey
FOREIGN KEY (processinstance_)
REFERENCES "public"."jbpm_processinstance"(processinstance_)
;
ALTER TABLE "public"."jbpm_moduleinstance"
ADD CONSTRAINT jbpm_moduleinstance_taskmgmtdefinition__fkey
FOREIGN KEY (taskmgmtdefinition_)
REFERENCES "public"."jbpm_moduledefinition"(taskmgmtdefinition_)
;
CREATE UNIQUE INDEX jbpm_moduleinstance_pkey ON "public"."jbpm_moduleinstance"(id_)
;
CREATE UNIQUE INDEX sys_c004121 ON "public"."jbpm_moduleinstance"(id_)
;
CREATE INDEX jbpm_modinst_taskmdef ON "public"."jbpm_moduleinstance"(taskmgmtdefinition_)
;
ALTER TABLE "public"."jbpm_node"
ADD CONSTRAINT jbpm_node_processdefinition__fkey
FOREIGN KEY (processdefinition_)
REFERENCES "public"."jbpm_processdefinition"(processdefinition_)
;
ALTER TABLE "public"."jbpm_node"
ADD CONSTRAINT jbpm_node_decisiondelegation_fkey
FOREIGN KEY (decisiondelegation)
REFERENCES "public"."jbpm_delegation"(decisiondelegation)
;
ALTER TABLE "public"."jbpm_node"
ADD CONSTRAINT jbpm_node_action__fkey
FOREIGN KEY (action_)
REFERENCES "public"."jbpm_action"(action_)
;
ALTER TABLE "public"."jbpm_node"
ADD CONSTRAINT jbpm_node_subprocessdefinition__fkey
FOREIGN KEY (subprocessdefinition_)
REFERENCES "public"."jbpm_processdefinition"(subprocessdefinition_)
;
ALTER TABLE "public"."jbpm_node"
ADD CONSTRAINT jbpm_node_superstate__fkey
FOREIGN KEY (superstate_)
REFERENCES "public"."jbpm_node"(superstate_)
;
CREATE UNIQUE INDEX jbpm_node_pkey ON "public"."jbpm_node"(id_)
;
CREATE UNIQUE INDEX sys_c004124 ON "public"."jbpm_node"(id_)
;
ALTER TABLE "public"."jbpm_pooledactor"
ADD CONSTRAINT jbpm_pooledactor_swimlaneinstance__fkey
FOREIGN KEY (swimlaneinstance_)
REFERENCES "public"."jbpm_swimlaneinstance"(swimlaneinstance_)
;
CREATE UNIQUE INDEX jbpm_pooledactor_pkey ON "public"."jbpm_pooledactor"(id_)
;
CREATE UNIQUE INDEX sys_c004126 ON "public"."jbpm_pooledactor"(id_)
;
ALTER TABLE "public"."jbpm_processdefinition"
ADD CONSTRAINT jbpm_processdefinition_startstate__fkey
FOREIGN KEY (startstate_)
REFERENCES "public"."jbpm_node"(startstate_)
;
CREATE UNIQUE INDEX jbpm_processdefinition_pkey ON "public"."jbpm_processdefinition"(id_)
;
CREATE UNIQUE INDEX sys_c004128 ON "public"."jbpm_processdefinition"(id_)
;
ALTER TABLE "public"."jbpm_processinstance"
ADD CONSTRAINT jbpm_processinstance_processdefinition__fkey
FOREIGN KEY (processdefinition_)
REFERENCES "public"."jbpm_processdefinition"(processdefinition_)
;
ALTER TABLE "public"."jbpm_processinstance"
ADD CONSTRAINT jbpm_processinstance_superprocesstoken__fkey
FOREIGN KEY (superprocesstoken_)
REFERENCES "public"."jbpm_token"(superprocesstoken_)
;
ALTER TABLE "public"."jbpm_processinstance"
ADD CONSTRAINT jbpm_processinstance_roottoken__fkey
FOREIGN KEY (roottoken_)
REFERENCES "public"."jbpm_token"(roottoken_)
;
CREATE UNIQUE INDEX sys_c004131 ON "public"."jbpm_processinstance"(id_)
;
CREATE UNIQUE INDEX jbpm_processinstance_pkey ON "public"."jbpm_processinstance"(id_)
;
ALTER TABLE "public"."jbpm_runtimeaction"
ADD CONSTRAINT jbpm_runtimeaction_action__fkey
FOREIGN KEY (action_)
REFERENCES "public"."jbpm_action"(action_)
;
ALTER TABLE "public"."jbpm_runtimeaction"
ADD CONSTRAINT jbpm_runtimeaction_processinstance__fkey
FOREIGN KEY (processinstance_)
REFERENCES "public"."jbpm_processinstance"(processinstance_)
;
CREATE UNIQUE INDEX sys_c004134 ON "public"."jbpm_runtimeaction"(id_)
;
CREATE UNIQUE INDEX jbpm_runtimeaction_pkey ON "public"."jbpm_runtimeaction"(id_)
;
ALTER TABLE "public"."jbpm_swimlane"
ADD CONSTRAINT jbpm_swimlane_assignmentdelegation__fkey
FOREIGN KEY (assignmentdelegation_)
REFERENCES "public"."jbpm_delegation"(assignmentdelegation_)
;
ALTER TABLE "public"."jbpm_swimlane"
ADD CONSTRAINT jbpm_swimlane_taskmgmtdefinition__fkey
FOREIGN KEY (taskmgmtdefinition_)
REFERENCES "public"."jbpm_moduledefinition"(taskmgmtdefinition_)
;
CREATE UNIQUE INDEX jbpm_swimlane_pkey ON "public"."jbpm_swimlane"(id_)
;
CREATE UNIQUE INDEX sys_c004136 ON "public"."jbpm_swimlane"(id_)
;
ALTER TABLE "public"."jbpm_swimlaneinstance"
ADD CONSTRAINT jbpm_swimlaneinstance_taskmgmtinstance__fkey
FOREIGN KEY (taskmgmtinstance_)
REFERENCES "public"."jbpm_moduleinstance"(taskmgmtinstance_)
;
ALTER TABLE "public"."jbpm_swimlaneinstance"
ADD CONSTRAINT jbpm_swimlaneinstance_swimlane__fkey
FOREIGN KEY (swimlane_)
REFERENCES "public"."jbpm_swimlane"(swimlane_)
;
CREATE UNIQUE INDEX jbpm_swimlaneinstance_pkey ON "public"."jbpm_swimlaneinstance"(id_)
;
CREATE UNIQUE INDEX sys_c004138 ON "public"."jbpm_swimlaneinstance"(id_)
;
ALTER TABLE "public"."jbpm_task"
ADD CONSTRAINT jbpm_task_tasknode__fkey
FOREIGN KEY (tasknode_)
REFERENCES "public"."jbpm_node"(tasknode_)
;
ALTER TABLE "public"."jbpm_task"
ADD CONSTRAINT jbpm_task_processdefinition__fkey
FOREIGN KEY (processdefinition_)
REFERENCES "public"."jbpm_processdefinition"(processdefinition_)
;
ALTER TABLE "public"."jbpm_task"
ADD CONSTRAINT jbpm_task_swimlane__fkey
FOREIGN KEY (swimlane_)
REFERENCES "public"."jbpm_swimlane"(swimlane_)
;
ALTER TABLE "public"."jbpm_task"
ADD CONSTRAINT jbpm_task_assignmentdelegation__fkey
FOREIGN KEY (assignmentdelegation_)
REFERENCES "public"."jbpm_delegation"(assignmentdelegation_)
;
ALTER TABLE "public"."jbpm_task"
ADD CONSTRAINT jbpm_task_taskcontroller__fkey
FOREIGN KEY (taskcontroller_)
REFERENCES "public"."jbpm_taskcontroller"(taskcontroller_)
;
ALTER TABLE "public"."jbpm_task"
ADD CONSTRAINT jbpm_task_taskmgmtdefinition__fkey
FOREIGN KEY (taskmgmtdefinition_)
REFERENCES "public"."jbpm_moduledefinition"(taskmgmtdefinition_)
;
ALTER TABLE "public"."jbpm_task"
ADD CONSTRAINT jbpm_task_startstate__fkey
FOREIGN KEY (startstate_)
REFERENCES "public"."jbpm_node"(startstate_)
;
CREATE UNIQUE INDEX jbpm_task_pkey ON "public"."jbpm_task"(id_)
;
CREATE UNIQUE INDEX sys_c004140 ON "public"."jbpm_task"(id_)
;
ALTER TABLE "public"."jbpm_taskactorpool"
ADD CONSTRAINT jbpm_taskactorpool_pooledactor__fkey
FOREIGN KEY (pooledactor_)
REFERENCES "public"."jbpm_pooledactor"(pooledactor_)
;
ALTER TABLE "public"."jbpm_taskactorpool"
ADD CONSTRAINT jbpm_taskactorpool_taskinstance__fkey
FOREIGN KEY (taskinstance_)
REFERENCES "public"."jbpm_taskinstance"(taskinstance_)
;
CREATE UNIQUE INDEX jbpm_taskactorpool_pkey ON "public"."jbpm_taskactorpool"
(
  pooledactor_,
  taskinstance_
)
;
CREATE UNIQUE INDEX sys_c004143 ON "public"."jbpm_taskactorpool"
(
  taskinstance_,
  pooledactor_
)
;
ALTER TABLE "public"."jbpm_taskcontroller"
ADD CONSTRAINT jbpm_taskcontroller_taskcontrollerdelegation__fkey
FOREIGN KEY (taskcontrollerdelegation_)
REFERENCES "public"."jbpm_delegation"(taskcontrollerdelegation_)
;
CREATE UNIQUE INDEX sys_c004145 ON "public"."jbpm_taskcontroller"(id_)
;
CREATE UNIQUE INDEX jbpm_taskcontroller_pkey ON "public"."jbpm_taskcontroller"(id_)
;
ALTER TABLE "public"."jbpm_taskinstance"
ADD CONSTRAINT jbpm_taskinstance_taskmgmtinstance__fkey
FOREIGN KEY (taskmgmtinstance_)
REFERENCES "public"."jbpm_moduleinstance"(taskmgmtinstance_)
;
ALTER TABLE "public"."jbpm_taskinstance"
ADD CONSTRAINT jbpm_taskinstance_token__fkey
FOREIGN KEY (token_)
REFERENCES "public"."jbpm_token"(token_)
;
ALTER TABLE "public"."jbpm_taskinstance"
ADD CONSTRAINT jbpm_taskinstance_task__fkey
FOREIGN KEY (task_)
REFERENCES "public"."jbpm_task"(task_)
;
ALTER TABLE "public"."jbpm_taskinstance"
ADD CONSTRAINT jbpm_taskinstance_swimlaninstance__fkey
FOREIGN KEY (swimlaninstance_)
REFERENCES "public"."jbpm_swimlaneinstance"(swimlaninstance_)
;
CREATE INDEX idx_task_actorid ON "public"."jbpm_taskinstance"(actorid_)
;
CREATE UNIQUE INDEX jbpm_taskinstance_pkey ON "public"."jbpm_taskinstance"(id_)
;
CREATE UNIQUE INDEX sys_c004148 ON "public"."jbpm_taskinstance"(id_)
;
ALTER TABLE "public"."jbpm_timer"
ADD CONSTRAINT jbpm_timer_processinstance__fkey
FOREIGN KEY (processinstance_)
REFERENCES "public"."jbpm_processinstance"(processinstance_)
;
ALTER TABLE "public"."jbpm_timer"
ADD CONSTRAINT jbpm_timer_token__fkey
FOREIGN KEY (token_)
REFERENCES "public"."jbpm_token"(token_)
;
ALTER TABLE "public"."jbpm_timer"
ADD CONSTRAINT jbpm_timer_action__fkey
FOREIGN KEY (action_)
REFERENCES "public"."jbpm_action"(action_)
;
ALTER TABLE "public"."jbpm_timer"
ADD CONSTRAINT jbpm_timer_taskinstance__fkey
FOREIGN KEY (taskinstance_)
REFERENCES "public"."jbpm_taskinstance"(taskinstance_)
;
CREATE UNIQUE INDEX sys_c004150 ON "public"."jbpm_timer"(id_)
;
CREATE UNIQUE INDEX jbpm_timer_pkey ON "public"."jbpm_timer"(id_)
;
ALTER TABLE "public"."jbpm_token"
ADD CONSTRAINT jbpm_token_parent__fkey
FOREIGN KEY (parent_)
REFERENCES "public"."jbpm_token"(parent_)
;
ALTER TABLE "public"."jbpm_token"
ADD CONSTRAINT jbpm_token_processinstance__fkey
FOREIGN KEY (processinstance_)
REFERENCES "public"."jbpm_processinstance"(processinstance_)
;
ALTER TABLE "public"."jbpm_token"
ADD CONSTRAINT jbpm_token_node__fkey
FOREIGN KEY (node_)
REFERENCES "public"."jbpm_node"(node_)
;
ALTER TABLE "public"."jbpm_token"
ADD CONSTRAINT jbpm_token_subprocessinstance__fkey
FOREIGN KEY (subprocessinstance_)
REFERENCES "public"."jbpm_processinstance"(subprocessinstance_)
;
CREATE UNIQUE INDEX jbpm_token_pkey ON "public"."jbpm_token"(id_)
;
CREATE UNIQUE INDEX sys_c004153 ON "public"."jbpm_token"(id_)
;
ALTER TABLE "public"."jbpm_tokenvariablemap"
ADD CONSTRAINT jbpm_tokenvariablemap_token__fkey
FOREIGN KEY (token_)
REFERENCES "public"."jbpm_token"(token_)
;
ALTER TABLE "public"."jbpm_tokenvariablemap"
ADD CONSTRAINT jbpm_tokenvariablemap_contextinstance__fkey
FOREIGN KEY (contextinstance_)
REFERENCES "public"."jbpm_moduleinstance"(contextinstance_)
;
CREATE UNIQUE INDEX sys_c004155 ON "public"."jbpm_tokenvariablemap"(id_)
;
CREATE UNIQUE INDEX jbpm_tokenvariablemap_pkey ON "public"."jbpm_tokenvariablemap"(id_)
;
ALTER TABLE "public"."jbpm_transition"
ADD CONSTRAINT jbpm_transition_from__fkey
FOREIGN KEY (from_)
REFERENCES "public"."jbpm_node"(from_)
;
ALTER TABLE "public"."jbpm_transition"
ADD CONSTRAINT jbpm_transition_to__fkey
FOREIGN KEY (to_)
REFERENCES "public"."jbpm_node"(to_)
;
ALTER TABLE "public"."jbpm_transition"
ADD CONSTRAINT jbpm_transition_processdefinition__fkey
FOREIGN KEY (processdefinition_)
REFERENCES "public"."jbpm_processdefinition"(processdefinition_)
;
CREATE UNIQUE INDEX sys_c004157 ON "public"."jbpm_transition"(id_)
;
CREATE UNIQUE INDEX jbpm_transition_pkey ON "public"."jbpm_transition"(id_)
;
ALTER TABLE "public"."jbpm_variableaccess"
ADD CONSTRAINT jbpm_variableaccess_processstate__fkey
FOREIGN KEY (processstate_)
REFERENCES "public"."jbpm_node"(processstate_)
;
ALTER TABLE "public"."jbpm_variableaccess"
ADD CONSTRAINT jbpm_variableaccess_taskcontroller__fkey
FOREIGN KEY (taskcontroller_)
REFERENCES "public"."jbpm_taskcontroller"(taskcontroller_)
;
ALTER TABLE "public"."jbpm_variableaccess"
ADD CONSTRAINT jbpm_variableaccess_script__fkey
FOREIGN KEY (script_)
REFERENCES "public"."jbpm_action"(script_)
;
CREATE UNIQUE INDEX sys_c004159 ON "public"."jbpm_variableaccess"(id_)
;
CREATE UNIQUE INDEX jbpm_variableaccess_pkey ON "public"."jbpm_variableaccess"(id_)
;
ALTER TABLE "public"."jbpm_variableinstance"
ADD CONSTRAINT jbpm_variableinstance_bytearrayvalue__fkey
FOREIGN KEY (bytearrayvalue_)
REFERENCES "public"."jbpm_bytearray"(bytearrayvalue_)
;
ALTER TABLE "public"."jbpm_variableinstance"
ADD CONSTRAINT jbpm_variableinstance_processinstance__fkey
FOREIGN KEY (processinstance_)
REFERENCES "public"."jbpm_processinstance"(processinstance_)
;
ALTER TABLE "public"."jbpm_variableinstance"
ADD CONSTRAINT jbpm_variableinstance_taskinstance__fkey
FOREIGN KEY (taskinstance_)
REFERENCES "public"."jbpm_taskinstance"(taskinstance_)
;
ALTER TABLE "public"."jbpm_variableinstance"
ADD CONSTRAINT jbpm_variableinstance_token__fkey
FOREIGN KEY (token_)
REFERENCES "public"."jbpm_token"(token_)
;
ALTER TABLE "public"."jbpm_variableinstance"
ADD CONSTRAINT jbpm_variableinstance_tokenvariablemap__fkey
FOREIGN KEY (tokenvariablemap_)
REFERENCES "public"."jbpm_tokenvariablemap"(tokenvariablemap_)
;
CREATE INDEX jbpm_varinst_taskinstance ON "public"."jbpm_variableinstance"(taskinstance_)
;
CREATE UNIQUE INDEX sys_c004162 ON "public"."jbpm_variableinstance"(id_)
;
CREATE INDEX jbpm_varinst_bytearrayvalue ON "public"."jbpm_variableinstance"(bytearrayvalue_)
;
CREATE UNIQUE INDEX jbpm_variableinstance_pkey ON "public"."jbpm_variableinstance"(id_)
;
CREATE UNIQUE INDEX ado_uk_idfit_idusu ON "public"."sc_admin_seg_org_usuari"
(
  aso_idfit,
  aso_idusu
)
;
ALTER TABLE "public"."sc_adminapp"
ADD CONSTRAINT sc_adminapp_adp_idrlu_fkey
FOREIGN KEY (adp_idrlu)
REFERENCES "public"."sc_rolusu"(adp_idrlu)
;
ALTER TABLE "public"."sc_adminapp"
ADD CONSTRAINT sc_adminapp_adp_idapp_fkey
FOREIGN KEY (adp_idapp)
REFERENCES "public"."sc_aplica"(adp_idapp)
;
CREATE UNIQUE INDEX adp_pk_idapp_idrlu ON "public"."sc_adminapp"
(
  adp_idapp,
  adp_idrlu
)
;
CREATE UNIQUE INDEX sc_adminapp_pkey ON "public"."sc_adminapp"
(
  adp_idapp,
  adp_idrlu
)
;
ALTER TABLE "public"."sc_admmaq"
ADD CONSTRAINT sc_admmaq_adm_usuid_fkey
FOREIGN KEY (adm_usuid)
REFERENCES "public"."sc_usuari"(adm_usuid)
;
ALTER TABLE "public"."sc_admmaq"
ADD CONSTRAINT sc_admmaq_adm_idwf_fkey
FOREIGN KEY (adm_idwf)
REFERENCES "public"."jbpm_processinstance"(adm_idwf)
;
ALTER TABLE "public"."sc_admmaq"
ADD CONSTRAINT sc_admmaq_adm_maqid_fkey
FOREIGN KEY (adm_maqid)
REFERENCES "public"."sc_maquin"(adm_maqid)
;
CREATE UNIQUE INDEX sc_admmaq_pkey ON "public"."sc_admmaq"(adm_id)
;
CREATE UNIQUE INDEX sc_admmaq_pk ON "public"."sc_admmaq"(adm_id)
;
CREATE UNIQUE INDEX sc_aplica_pkey ON "public"."sc_aplica"(apl_id)
;
CREATE UNIQUE INDEX apl_uk_codi ON "public"."sc_aplica"(apl_codi)
;
CREATE UNIQUE INDEX apl_pk_id ON "public"."sc_aplica"(apl_id)
;
ALTER TABLE "public"."sc_audito"
ADD CONSTRAINT sc_audito_aud_idusu_fkey
FOREIGN KEY (aud_idusu)
REFERENCES "public"."sc_usuari"(aud_idusu)
;
ALTER TABLE "public"."sc_audito"
ADD CONSTRAINT sc_audito_aud_idgru_fkey
FOREIGN KEY (aud_idgru)
REFERENCES "public"."sc_grups"(aud_idgru)
;
CREATE INDEX sc_audito_borrar ON "public"."sc_audito"(aud_data)
;
CREATE INDEX aud_gru_1 ON "public"."sc_audito"(aud_idgru)
;
CREATE INDEX aud_usu_1 ON "public"."sc_audito"(aud_idusu)
;
ALTER TABLE "public"."sc_autser"
ADD CONSTRAINT sc_autser_aus_idusu_fkey
FOREIGN KEY (aus_idusu)
REFERENCES "public"."sc_usuari"(aus_idusu)
;
ALTER TABLE "public"."sc_autser"
ADD CONSTRAINT sc_autser_aus_idmaq_fkey
FOREIGN KEY (aus_idmaq)
REFERENCES "public"."sc_maquin"(aus_idmaq)
;
ALTER TABLE "public"."sc_autser"
ADD CONSTRAINT sc_autser_aus_idser_fkey
FOREIGN KEY (aus_idser)
REFERENCES "public"."sc_servei"(aus_idser)
;
CREATE INDEX aus_ser_fk_i ON "public"."sc_autser"(aus_idser)
;
CREATE INDEX aus_usu_fk_i ON "public"."sc_autser"(aus_idusu)
;
CREATE INDEX aus_maq_fk_i ON "public"."sc_autser"(aus_idmaq)
;
CREATE UNIQUE INDEX aus_pk_id ON "public"."sc_autser"(aus_id)
;
CREATE UNIQUE INDEX sc_autser_pkey ON "public"."sc_autser"(aus_id)
;
ALTER TABLE "public"."sc_autxar"
ADD CONSTRAINT sc_autxar_axa_idusu_fkey
FOREIGN KEY (axa_idusu)
REFERENCES "public"."sc_usuari"(axa_idusu)
;
ALTER TABLE "public"."sc_autxar"
ADD CONSTRAINT sc_autxar_axa_idrol_fkey
FOREIGN KEY (axa_idrol)
REFERENCES "public"."sc_roles"(axa_idrol)
;
ALTER TABLE "public"."sc_autxar"
ADD CONSTRAINT sc_autxar_axa_idxar_fkey
FOREIGN KEY (axa_idxar)
REFERENCES "public"."sc_xarxes"(axa_idxar)
;
ALTER TABLE "public"."sc_autxar"
ADD CONSTRAINT sc_autxar_axa_idgru_fkey
FOREIGN KEY (axa_idgru)
REFERENCES "public"."sc_grups"(axa_idgru)
;
CREATE INDEX sc_axa_rol ON "public"."sc_autxar"(axa_idrol)
;
CREATE UNIQUE INDEX sc_axa_pk ON "public"."sc_autxar"(axa_id)
;
CREATE UNIQUE INDEX sc_autxar_pkey ON "public"."sc_autxar"(axa_id)
;
CREATE INDEX sc_axa_xar ON "public"."sc_autxar"(axa_idxar)
;
CREATE INDEX sc_axa_gru ON "public"."sc_autxar"(axa_idgru)
;
CREATE INDEX sc_axa_usu ON "public"."sc_autxar"(axa_idusu)
;
ALTER TABLE "public"."sc_conacc"
ADD CONSTRAINT sc_conacc_cac_rol_id_fkey
FOREIGN KEY (cac_rol_id)
REFERENCES "public"."sc_roles"(cac_rol_id)
;
ALTER TABLE "public"."sc_conacc"
ADD CONSTRAINT sc_conacc_cac_dis_id_fkey
FOREIGN KEY (cac_dis_id)
REFERENCES "public"."sc_dispat"(cac_dis_id)
;
CREATE UNIQUE INDEX sc_conacc_pkey ON "public"."sc_conacc"(cac_id)
;
CREATE UNIQUE INDEX sc_conacc_pk ON "public"."sc_conacc"(cac_id)
;
ALTER TABLE "public"."sc_config"
ADD CONSTRAINT sc_config_con_idxar_fkey
FOREIGN KEY (con_idxar)
REFERENCES "public"."sc_xarxes"(con_idxar)
;
CREATE UNIQUE INDEX con_pk_id ON "public"."sc_config"(con_id)
;
CREATE UNIQUE INDEX sc_config_pkey ON "public"."sc_config"(con_id)
;
ALTER TABLE "public"."sc_contar"
ADD CONSTRAINT sc_contar_cta_idtar_fkey
FOREIGN KEY (cta_idtar)
REFERENCES "public"."sc_target"(cta_idtar)
;
CREATE UNIQUE INDEX sc_contar_pkey ON "public"."sc_contar"
(
  cta_filcol,
  cta_idtar
)
;
CREATE UNIQUE INDEX cta_pk_id ON "public"."sc_contar"
(
  cta_idtar,
  cta_filcol
)
;
ALTER TABLE "public"."sc_contra"
ADD CONSTRAINT sc_contra_ctr_idusu_fkey
FOREIGN KEY (ctr_idusu)
REFERENCES "public"."sc_usuari"(ctr_idusu)
;
CREATE UNIQUE INDEX ctr_pk_idusu_ordre ON "public"."sc_contra"
(
  ctr_idusu,
  ctr_ordre
)
;
CREATE UNIQUE INDEX sc_contra_pkey ON "public"."sc_contra"
(
  ctr_idusu,
  ctr_ordre
)
;
ALTER TABLE "public"."sc_dadusu"
ADD CONSTRAINT sc_dadusu_dus_idusu_fkey
FOREIGN KEY (dus_idusu)
REFERENCES "public"."sc_usuari"(dus_idusu)
;
CREATE UNIQUE INDEX dus_pk_id ON "public"."sc_dadusu"(dus_id)
;
CREATE UNIQUE INDEX sc_dadusu_pkey ON "public"."sc_dadusu"(dus_id)
;
CREATE UNIQUE INDEX dus_pk ON "public"."sc_dadusu"
(
  dus_idusu,
  dus_tdaid
)
;
CREATE UNIQUE INDEX dis_pk ON "public"."sc_dispat"(dis_id)
;
CREATE UNIQUE INDEX dis_uk_codi ON "public"."sc_dispat"(dis_codi)
;
CREATE UNIQUE INDEX sc_dispat_pkey ON "public"."sc_dispat"(dis_id)
;
ALTER TABLE "public"."sc_domapp"
ADD CONSTRAINT sc_domapp_dom_app_fkey
FOREIGN KEY (dom_app)
REFERENCES "public"."sc_aplica"(dom_app)
;
CREATE UNIQUE INDEX sc_domapp_pkey ON "public"."sc_domapp"(dom_id)
;
CREATE UNIQUE INDEX sys_c004202 ON "public"."sc_domapp"(dom_id)
;
CREATE UNIQUE INDEX dom_uk_nom_app ON "public"."sc_domapp"
(
  dom_nom,
  dom_app
)
;
CREATE UNIQUE INDEX sc_domcor_pkey ON "public"."sc_domcor"(dco_id)
;
CREATE UNIQUE INDEX dco_pk_id ON "public"."sc_domcor"(dco_id)
;
CREATE UNIQUE INDEX dco_uk_codi ON "public"."sc_domcor"(dco_codi)
;
ALTER TABLE "public"."sc_exepue"
ADD CONSTRAINT sc_exepue_epe_idpue_fkey
FOREIGN KEY (epe_idpue)
REFERENCES "public"."sc_punent"(epe_idpue)
;
ALTER TABLE "public"."sc_exepue"
ADD CONSTRAINT sc_exepue_epe_codexe_fkey
FOREIGN KEY (epe_codexe)
REFERENCES "public"."sc_tipexe"(epe_codexe)
;
CREATE UNIQUE INDEX sc_exepue_pkey ON "public"."sc_exepue"(epe_id)
;
CREATE UNIQUE INDEX epe_pk_idpue_ambit ON "public"."sc_exepue"
(
  epe_idpue,
  epe_ambit
)
;
CREATE UNIQUE INDEX epe_pk_id ON "public"."sc_exepue"(epe_id)
;
ALTER TABLE "public"."sc_extlco"
ADD CONSTRAINT sc_extlco_elc_idlco_fkey
FOREIGN KEY (elc_idlco)
REFERENCES "public"."sc_llicor"(elc_idlco)
;
ALTER TABLE "public"."sc_farren"
ADD CONSTRAINT sc_farren_far_idusu_fkey
FOREIGN KEY (far_idusu)
REFERENCES "public"."sc_usuari"(far_idusu)
;
CREATE UNIQUE INDEX sc_farren_pkey ON "public"."sc_farren"(far_id)
;
CREATE UNIQUE INDEX far_pk_id ON "public"."sc_farren"(far_id)
;
ALTER TABLE "public"."sc_fitxer"
ADD CONSTRAINT sc_fitxer_fit_idresp_fkey
FOREIGN KEY (fit_idresp)
REFERENCES "public"."sc_usuari"(fit_idresp)
;
ALTER TABLE "public"."sc_fitxer"
ADD CONSTRAINT sc_fitxer_fit_iddg_fkey
FOREIGN KEY (fit_iddg)
REFERENCES "public"."sc_grups"(fit_iddg)
;
CREATE UNIQUE INDEX sc_fitxer_pkey ON "public"."sc_fitxer"(fit_id)
;
CREATE UNIQUE INDEX fit_pk_id ON "public"."sc_fitxer"(fit_id)
;
ALTER TABLE "public"."sc_grudis"
ADD CONSTRAINT sc_grudis_grd_iddis_fkey
FOREIGN KEY (grd_iddis)
REFERENCES "public"."sc_dispat"(grd_iddis)
;
CREATE UNIQUE INDEX sys_c005364 ON "public"."sc_grudis"(grd_id)
;
ALTER TABLE "public"."sc_gruimp"
ADD CONSTRAINT sc_gruimp_gim_idgru_fkey
FOREIGN KEY (gim_idgru)
REFERENCES "public"."sc_grups"(gim_idgru)
;
ALTER TABLE "public"."sc_gruimp"
ADD CONSTRAINT sc_gruimp_gim_idimp_fkey
FOREIGN KEY (gim_idimp)
REFERENCES "public"."sc_impres"(gim_idimp)
;
CREATE INDEX gim_gru_fk_i ON "public"."sc_gruimp"(gim_idgru)
;
CREATE UNIQUE INDEX sc_gruimp_pkey ON "public"."sc_gruimp"
(
  gim_idgru,
  gim_idimp
)
;
CREATE INDEX gim_imp_fk_i ON "public"."sc_gruimp"(gim_idimp)
;
CREATE UNIQUE INDEX gim_pk_idgru_idimp ON "public"."sc_gruimp"
(
  gim_idgru,
  gim_idimp
)
;
ALTER TABLE "public"."sc_grups"
ADD CONSTRAINT sc_grups_gru_tipus_fkey
FOREIGN KEY (gru_tipus)
REFERENCES "public"."sc_tipusuo"(gru_tipus)
;
ALTER TABLE "public"."sc_grups"
ADD CONSTRAINT sc_grups_gru_pare_fkey
FOREIGN KEY (gru_pare)
REFERENCES "public"."sc_grups"(gru_pare)
;
ALTER TABLE "public"."sc_grups"
ADD CONSTRAINT sc_grups_gru_idmaq_fkey
FOREIGN KEY (gru_idmaq)
REFERENCES "public"."sc_maquin"(gru_idmaq)
;
CREATE UNIQUE INDEX gru_uk_codi ON "public"."sc_grups"(gru_codi)
;
CREATE UNIQUE INDEX gru_pk_id ON "public"."sc_grups"(gru_id)
;
CREATE INDEX gru_maq_fk_i ON "public"."sc_grups"(gru_idmaq)
;
CREATE UNIQUE INDEX sc_grups_pkey ON "public"."sc_grups"(gru_id)
;
ALTER TABLE "public"."sc_grupue"
ADD CONSTRAINT sc_grupue_gpe_idpue_fkey
FOREIGN KEY (gpe_idpue)
REFERENCES "public"."sc_punent"(gpe_idpue)
;
ALTER TABLE "public"."sc_grupue"
ADD CONSTRAINT sc_grupue_gpe_idgru_fkey
FOREIGN KEY (gpe_idgru)
REFERENCES "public"."sc_grups"(gpe_idgru)
;
CREATE INDEX gru_pue_2 ON "public"."sc_grupue"(gpe_idpue)
;
CREATE UNIQUE INDEX sc_grupue_pkey ON "public"."sc_grupue"(gpe_id)
;
CREATE INDEX gru_pue_1 ON "public"."sc_grupue"(gpe_idgru)
;
CREATE UNIQUE INDEX gpe_pk_id ON "public"."sc_grupue"(gpe_id)
;
CREATE UNIQUE INDEX gpe_pk_idgru_idpue ON "public"."sc_grupue"
(
  gpe_idgru,
  gpe_idpue
)
;
CREATE UNIQUE INDEX sc_icones_pkey ON "public"."sc_icones"(ico_id)
;
CREATE UNIQUE INDEX ico_pk_id ON "public"."sc_icones"(ico_id)
;
ALTER TABLE "public"."sc_impres"
ADD CONSTRAINT sc_impres_imp_idmaq_fkey
FOREIGN KEY (imp_idmaq)
REFERENCES "public"."sc_maquin"(imp_idmaq)
;
CREATE UNIQUE INDEX imp_uk_codi ON "public"."sc_impres"(imp_codi)
;
CREATE UNIQUE INDEX imp_pk_id ON "public"."sc_impres"(imp_id)
;
CREATE INDEX imp_maq_fk_i ON "public"."sc_impres"(imp_idmaq)
;
CREATE UNIQUE INDEX sc_impres_pkey ON "public"."sc_impres"(imp_id)
;
ALTER TABLE "public"."sc_lcolco"
ADD CONSTRAINT sc_lcolco_llc_idlco2_fkey
FOREIGN KEY (llc_idlco2)
REFERENCES "public"."sc_llicor"(llc_idlco2)
;
ALTER TABLE "public"."sc_lcolco"
ADD CONSTRAINT sc_lcolco_llc_idlco1_fkey
FOREIGN KEY (llc_idlco1)
REFERENCES "public"."sc_llicor"(llc_idlco1)
;
CREATE INDEX llc_lco2_fk ON "public"."sc_lcolco"(llc_idlco2)
;
CREATE UNIQUE INDEX llc_pk_lco1_lco2 ON "public"."sc_lcolco"
(
  llc_idlco1,
  llc_idlco2
)
;
CREATE UNIQUE INDEX sc_lcolco_pkey ON "public"."sc_lcolco"
(
  llc_idlco1,
  llc_idlco2
)
;
ALTER TABLE "public"."sc_llicor"
ADD CONSTRAINT sc_llicor_lco_iddco_fkey
FOREIGN KEY (lco_iddco)
REFERENCES "public"."sc_domcor"(lco_iddco)
;
CREATE UNIQUE INDEX sc_llicor_pkey ON "public"."sc_llicor"(lco_id)
;
CREATE UNIQUE INDEX lco_uk_nom_iddco ON "public"."sc_llicor"
(
  lco_nom,
  lco_iddco
)
;
CREATE UNIQUE INDEX lco_pk_id ON "public"."sc_llicor"(lco_id)
;
ALTER TABLE "public"."sc_maquin"
ADD CONSTRAINT sc_maquin_maq_idxar_fkey
FOREIGN KEY (maq_idxar)
REFERENCES "public"."sc_xarxes"(maq_idxar)
;
CREATE INDEX maq_xar_fk_i ON "public"."sc_maquin"(maq_idxar)
;
CREATE UNIQUE INDEX sc_maquin_pkey ON "public"."sc_maquin"(maq_id)
;
CREATE UNIQUE INDEX maq_uk_ip ON "public"."sc_maquin"(maq_adrip)
;
CREATE UNIQUE INDEX maq_pk_id ON "public"."sc_maquin"(maq_id)
;
CREATE UNIQUE INDEX maq_uk_nom ON "public"."sc_maquin"(maq_nom)
;
CREATE UNIQUE INDEX sc_maquinalias_pkey ON "public"."sc_maquinalias"(mal_id)
;
CREATE UNIQUE INDEX sc_maquinalias_pk ON "public"."sc_maquinalias"(mal_id)
;
ALTER TABLE "public"."sc_notifica"
ADD CONSTRAINT sc_notifica_ntf_rol_fkey
FOREIGN KEY (ntf_rol)
REFERENCES "public"."sc_roles"(ntf_rol)
;
ALTER TABLE "public"."sc_notifica"
ADD CONSTRAINT sc_notifica_ntf_usu_fkey
FOREIGN KEY (ntf_usu)
REFERENCES "public"."sc_usuari"(ntf_usu)
;
ALTER TABLE "public"."sc_notifica"
ADD CONSTRAINT sc_notifica_ntf_apl_fkey
FOREIGN KEY (ntf_apl)
REFERENCES "public"."sc_aplica"(ntf_apl)
;
CREATE UNIQUE INDEX sc_notifica_pkey ON "public"."sc_notifica"(ntf_id)
;
CREATE UNIQUE INDEX sc_notifica_pk ON "public"."sc_notifica"(ntf_id)
;
ALTER TABLE "public"."sc_petfar"
ADD CONSTRAINT sc_petfar_pef_idusu_fkey
FOREIGN KEY (pef_idusu)
REFERENCES "public"."sc_usuari"(pef_idusu)
;
CREATE UNIQUE INDEX sc_petfar_pkey ON "public"."sc_petfar"(pef_id)
;
CREATE UNIQUE INDEX pef_pk_id ON "public"."sc_petfar"(pef_id)
;
CREATE UNIQUE INDEX sc_politi_pkey ON "public"."sc_politi"(pol_id)
;
CREATE UNIQUE INDEX pol_uk_codi ON "public"."sc_politi"(pol_codi)
;
CREATE UNIQUE INDEX pol_pk_id ON "public"."sc_politi"(pol_id)
;
ALTER TABLE "public"."sc_polusu"
ADD CONSTRAINT sc_polusu_plu_idusu_fkey
FOREIGN KEY (plu_idusu)
REFERENCES "public"."sc_usuari"(plu_idusu)
;
ALTER TABLE "public"."sc_polusu"
ADD CONSTRAINT sc_polusu_plu_idpol_fkey
FOREIGN KEY (plu_idpol)
REFERENCES "public"."sc_politi"(plu_idpol)
;
CREATE INDEX plu_pol_fk_i ON "public"."sc_polusu"(plu_idpol)
;
CREATE INDEX plu_usu_fk_i ON "public"."sc_polusu"(plu_idusu)
;
CREATE UNIQUE INDEX plu_pk_id ON "public"."sc_polusu"
(
  plu_idpol,
  plu_idusu
)
;
CREATE UNIQUE INDEX sc_polusu_pkey ON "public"."sc_polusu"
(
  plu_idpol,
  plu_idusu
)
;
CREATE UNIQUE INDEX pcp_pk_id ON "public"."sc_porcpd"(pcp_id)
;
CREATE UNIQUE INDEX sc_porcpd_pkey ON "public"."sc_porcpd"(pcp_id)
;
ALTER TABLE "public"."sc_ports"
ADD CONSTRAINT sc_ports_por_idser_fkey
FOREIGN KEY (por_idser)
REFERENCES "public"."sc_servei"(por_idser)
;
ALTER TABLE "public"."sc_ports"
ADD CONSTRAINT sc_ports_por_idmaq_fkey
FOREIGN KEY (por_idmaq)
REFERENCES "public"."sc_maquin"(por_idmaq)
;
CREATE UNIQUE INDEX por_pk_id ON "public"."sc_ports"(por_id)
;
CREATE UNIQUE INDEX sc_ports_pkey ON "public"."sc_ports"(por_id)
;
ALTER TABLE "public"."sc_puepue"
ADD CONSTRAINT sc_puepue_ppe_idpefi_fkey
FOREIGN KEY (ppe_idpefi)
REFERENCES "public"."sc_punent"(ppe_idpefi)
;
ALTER TABLE "public"."sc_puepue"
ADD CONSTRAINT sc_puepue_ppe_idpepa_fkey
FOREIGN KEY (ppe_idpepa)
REFERENCES "public"."sc_punent"(ppe_idpepa)
;
CREATE UNIQUE INDEX ppe_pk_idpepa_idpefi ON "public"."sc_puepue"
(
  ppe_idpepa,
  ppe_idpefi
)
;
CREATE UNIQUE INDEX sc_puepue_pkey ON "public"."sc_puepue"(ppe_id)
;
CREATE UNIQUE INDEX ppe_pk_id ON "public"."sc_puepue"(ppe_id)
;
ALTER TABLE "public"."sc_punent"
ADD CONSTRAINT sc_punent_pue_idapl_fkey
FOREIGN KEY (pue_idapl)
REFERENCES "public"."sc_aplica"(pue_idapl)
;
CREATE UNIQUE INDEX sc_punent_pkey ON "public"."sc_punent"(pue_id)
;
CREATE INDEX pue_apl_1 ON "public"."sc_punent"(pue_idapl)
;
CREATE UNIQUE INDEX pue_pk_id ON "public"."sc_punent"(pue_id)
;
ALTER TABLE "public"."sc_regacc"
ADD CONSTRAINT sc_regacc_rac_idmaor_fkey
FOREIGN KEY (rac_idmaor)
REFERENCES "public"."sc_maquin"(rac_idmaor)
;
ALTER TABLE "public"."sc_regacc"
ADD CONSTRAINT sc_regacc_rac_idser_fkey
FOREIGN KEY (rac_idser)
REFERENCES "public"."sc_servei"(rac_idser)
;
ALTER TABLE "public"."sc_regacc"
ADD CONSTRAINT sc_regacc_rac_idusu_fkey
FOREIGN KEY (rac_idusu)
REFERENCES "public"."sc_usuari"(rac_idusu)
;
ALTER TABLE "public"."sc_regacc"
ADD CONSTRAINT sc_regacc_rac_idmaq_fkey
FOREIGN KEY (rac_idmaq)
REFERENCES "public"."sc_maquin"(rac_idmaq)
;
CREATE INDEX rac_maq_ori ON "public"."sc_regacc"
(
  rac_idmaor,
  rac_datini
)
;
CREATE INDEX rac_ser_fk_i ON "public"."sc_regacc"(rac_idser)
;
CREATE INDEX rac_age_i ON "public"."sc_regacc"
(
  rac_codage,
  rac_idses
)
;
CREATE INDEX rac_usu_fk_i ON "public"."sc_regacc"
(
  rac_idusu,
  rac_datini
)
;
CREATE INDEX rac_maq_fk_i ON "public"."sc_regacc"(rac_idmaq)
;
CREATE UNIQUE INDEX sc_regacc_pkey ON "public"."sc_regacc"(rac_id)
;
CREATE UNIQUE INDEX rac_pk ON "public"."sc_regacc"(rac_id)
;
ALTER TABLE "public"."sc_regcpd"
ADD CONSTRAINT sc_regcpd_rcp_idpcp_fkey
FOREIGN KEY (rcp_idpcp)
REFERENCES "public"."sc_porcpd"(rcp_idpcp)
;
CREATE UNIQUE INDEX rcp_pk_id ON "public"."sc_regcpd"(rcp_id)
;
CREATE UNIQUE INDEX sc_regcpd_pkey ON "public"."sc_regcpd"(rcp_id)
;
ALTER TABLE "public"."sc_resp_seg_org_usuari"
ADD CONSTRAINT sc_resp_seg_org_usuari_res_idusu_fkey
FOREIGN KEY (res_idusu)
REFERENCES "public"."sc_usuari"(res_idusu)
;
CREATE UNIQUE INDEX sc_resp_seg_org_usuari_pkey ON "public"."sc_resp_seg_org_usuari"(res_id)
;
CREATE UNIQUE INDEX res_pk_id ON "public"."sc_resp_seg_org_usuari"(res_id)
;
ALTER TABLE "public"."sc_roles"
ADD CONSTRAINT sc_roles_rol_domapp_fkey
FOREIGN KEY (rol_domapp)
REFERENCES "public"."sc_domapp"(rol_domapp)
;
ALTER TABLE "public"."sc_roles"
ADD CONSTRAINT sc_roles_rol_idapl_fkey
FOREIGN KEY (rol_idapl)
REFERENCES "public"."sc_aplica"(rol_idapl)
;
ALTER TABLE "public"."sc_roles"
ADD CONSTRAINT sc_roles_rol_iddispat_fkey
FOREIGN KEY (rol_iddispat)
REFERENCES "public"."sc_dispat"(rol_iddispat)
;
CREATE UNIQUE INDEX rol_uk_nom_iddispat_idapl ON "public"."sc_roles"
(
  rol_nom,
  rol_iddispat,
  rol_idapl
)
;
CREATE UNIQUE INDEX sc_roles_pkey ON "public"."sc_roles"(rol_id)
;
CREATE UNIQUE INDEX rol_pk_id ON "public"."sc_roles"(rol_id)
;
CREATE INDEX rol_apl_fk_i ON "public"."sc_roles"(rol_idapl)
;
ALTER TABLE "public"."sc_rolfit"
ADD CONSTRAINT sc_rolfit_rlf_idrol_fkey
FOREIGN KEY (rlf_idrol)
REFERENCES "public"."sc_roles"(rlf_idrol)
;
ALTER TABLE "public"."sc_rolfit"
ADD CONSTRAINT sc_rolfit_rlf_idfit_fkey
FOREIGN KEY (rlf_idfit)
REFERENCES "public"."sc_fitxer"(rlf_idfit)
;
CREATE UNIQUE INDEX sc_rolfit_pkey ON "public"."sc_rolfit"
(
  rlf_idfit,
  rlf_idrol
)
;
CREATE UNIQUE INDEX rlf_pk_idrol_idfit ON "public"."sc_rolfit"
(
  rlf_idrol,
  rlf_idfit
)
;
ALTER TABLE "public"."sc_rolgrup"
ADD CONSTRAINT sc_rolgrup_rlg_rol_fkey
FOREIGN KEY (rlg_rol)
REFERENCES "public"."sc_roles"(rlg_rol)
;
ALTER TABLE "public"."sc_rolgrup"
ADD CONSTRAINT sc_rolgrup_rlg_grup_fkey
FOREIGN KEY (rlg_grup)
REFERENCES "public"."sc_grups"(rlg_grup)
;
CREATE UNIQUE INDEX sc_rolgrup_pkey ON "public"."sc_rolgrup"(rlg_id)
;
CREATE UNIQUE INDEX rlg_rolgrup_rolgrup ON "public"."sc_rolgrup"
(
  rlg_rol,
  rlg_grup
)
;
CREATE UNIQUE INDEX sys_c005370 ON "public"."sc_rolgrup"(rlg_id)
;
CREATE INDEX rlg_rolgrup_grup ON "public"."sc_rolgrup"(rlg_grup)
;
ALTER TABLE "public"."sc_rolpue"
ADD CONSTRAINT sc_rolpue_rpe_idpue_fkey
FOREIGN KEY (rpe_idpue)
REFERENCES "public"."sc_punent"(rpe_idpue)
;
ALTER TABLE "public"."sc_rolpue"
ADD CONSTRAINT sc_rolpue_rpe_idrol_fkey
FOREIGN KEY (rpe_idrol)
REFERENCES "public"."sc_roles"(rpe_idrol)
;
CREATE UNIQUE INDEX sc_rolpue_pkey ON "public"."sc_rolpue"(rpe_id)
;
CREATE UNIQUE INDEX rpe_pk_idpue_idrol ON "public"."sc_rolpue"
(
  rpe_idpue,
  rpe_idrol
)
;
CREATE INDEX pue_rol_2 ON "public"."sc_rolpue"(rpe_idrol)
;
CREATE INDEX pue_rol_1 ON "public"."sc_rolpue"(rpe_idpue)
;
CREATE UNIQUE INDEX rpe_pk_id ON "public"."sc_rolpue"(rpe_id)
;
ALTER TABLE "public"."sc_rolrol"
ADD CONSTRAINT sc_rolrol_rrl_valdom_fkey
FOREIGN KEY (rrl_valdom)
REFERENCES "public"."sc_valor_domini"(rrl_valdom)
;
ALTER TABLE "public"."sc_rolrol"
ADD CONSTRAINT sc_rolrol_rrl_contingut_fkey
FOREIGN KEY (rrl_contingut)
REFERENCES "public"."sc_roles"(rrl_contingut)
;
ALTER TABLE "public"."sc_rolrol"
ADD CONSTRAINT sc_rolrol_rrl_aplica_fkey
FOREIGN KEY (rrl_aplica)
REFERENCES "public"."sc_aplica"(rrl_aplica)
;
ALTER TABLE "public"."sc_rolrol"
ADD CONSTRAINT sc_rolrol_rrl_contenidor_fkey
FOREIGN KEY (rrl_contenidor)
REFERENCES "public"."sc_roles"(rrl_contenidor)
;
ALTER TABLE "public"."sc_rolrol"
ADD CONSTRAINT sc_rolrol_rrl_grup_fkey
FOREIGN KEY (rrl_grup)
REFERENCES "public"."sc_grups"(rrl_grup)
;
CREATE INDEX rrl_rol_fk_2 ON "public"."sc_rolrol"(rrl_contingut)
;
CREATE INDEX rrl_rol_fk_1 ON "public"."sc_rolrol"(rrl_contenidor)
;
CREATE UNIQUE INDEX sc_rolrol_pkey ON "public"."sc_rolrol"(rrl_id)
;
CREATE UNIQUE INDEX sys_c005373 ON "public"."sc_rolrol"(rrl_id)
;
ALTER TABLE "public"."sc_rolusu"
ADD CONSTRAINT sc_rolusu_rlu_idrol_fkey
FOREIGN KEY (rlu_idrol)
REFERENCES "public"."sc_roles"(rlu_idrol)
;
ALTER TABLE "public"."sc_rolusu"
ADD CONSTRAINT sc_rolusu_rlu_rolusu_gru_fkey
FOREIGN KEY (rlu_rolusu_gru)
REFERENCES "public"."sc_grups"(rlu_rolusu_gru)
;
ALTER TABLE "public"."sc_rolusu"
ADD CONSTRAINT sc_rolusu_rlu_admapp_fkey
FOREIGN KEY (rlu_admapp)
REFERENCES "public"."sc_aplica"(rlu_admapp)
;
ALTER TABLE "public"."sc_rolusu"
ADD CONSTRAINT sc_rolusu_rlu_valdom_fkey
FOREIGN KEY (rlu_valdom)
REFERENCES "public"."sc_valor_domini"(rlu_valdom)
;
ALTER TABLE "public"."sc_rolusu"
ADD CONSTRAINT sc_rolusu_rlu_idusu_fkey
FOREIGN KEY (rlu_idusu)
REFERENCES "public"."sc_usuari"(rlu_idusu)
;
CREATE UNIQUE INDEX rlu_pk_id ON "public"."sc_rolusu"(rlu_id)
;
CREATE INDEX rlu_rol_fk_i ON "public"."sc_rolusu"(rlu_idrol)
;
CREATE INDEX rlu_usu_fk_i ON "public"."sc_rolusu"(rlu_idusu)
;
CREATE UNIQUE INDEX sc_rolusu_pkey ON "public"."sc_rolusu"(rlu_id)
;
ALTER TABLE "public"."sc_secret"
ADD CONSTRAINT sc_secret_sec_idusu_fkey
FOREIGN KEY (sec_idusu)
REFERENCES "public"."sc_usuari"(sec_idusu)
;
ALTER TABLE "public"."sc_secret"
ADD CONSTRAINT sc_secret_sec_idsrv_fkey
FOREIGN KEY (sec_idsrv)
REFERENCES "public"."sc_server"(sec_idsrv)
;
CREATE INDEX sc_secret_ususrv ON "public"."sc_secret"
(
  sec_idusu,
  sec_idsrv
)
;
CREATE UNIQUE INDEX sc_secret_pk ON "public"."sc_secret"(sec_id)
;
CREATE UNIQUE INDEX sc_secret_pkey ON "public"."sc_secret"(sec_id)
;
CREATE UNIQUE INDEX ser_uk_codi ON "public"."sc_servei"(ser_codi)
;
CREATE UNIQUE INDEX ser_pk_id ON "public"."sc_servei"(ser_id)
;
CREATE UNIQUE INDEX sc_servei_pkey ON "public"."sc_servei"(ser_id)
;
CREATE UNIQUE INDEX sc_server_pk ON "public"."sc_server"(srv_id)
;
CREATE UNIQUE INDEX sc_server_uk1 ON "public"."sc_server"(srv_nom)
;
CREATE UNIQUE INDEX sc_server_pkey ON "public"."sc_server"(srv_id)
;
ALTER TABLE "public"."sc_sessio"
ADD CONSTRAINT sc_sessio_ses_idmaq_fkey
FOREIGN KEY (ses_idmaq)
REFERENCES "public"."sc_maquin"(ses_idmaq)
;
ALTER TABLE "public"."sc_sessio"
ADD CONSTRAINT sc_sessio_ses_idusu_fkey
FOREIGN KEY (ses_idusu)
REFERENCES "public"."sc_usuari"(ses_idusu)
;
ALTER TABLE "public"."sc_sessio"
ADD CONSTRAINT sc_sessio_ses_idmacl_fkey
FOREIGN KEY (ses_idmacl)
REFERENCES "public"."sc_maquin"(ses_idmacl)
;
CREATE UNIQUE INDEX sc_sessio_pkey ON "public"."sc_sessio"(ses_id)
;
CREATE UNIQUE INDEX ses_pk_id ON "public"."sc_sessio"(ses_id)
;
ALTER TABLE "public"."sc_sso"
ADD CONSTRAINT sc_sso_sso_idgru_fkey
FOREIGN KEY (sso_idgru)
REFERENCES "public"."sc_grups"(sso_idgru)
;
ALTER TABLE "public"."sc_sso"
ADD CONSTRAINT sc_sso_sso_idusu_fkey
FOREIGN KEY (sso_idusu)
REFERENCES "public"."sc_usuari"(sso_idusu)
;
ALTER TABLE "public"."sc_sso"
ADD CONSTRAINT sc_sso_sso_idmaq_fkey
FOREIGN KEY (sso_idmaq)
REFERENCES "public"."sc_maquin"(sso_idmaq)
;
ALTER TABLE "public"."sc_sso"
ADD CONSTRAINT sc_sso_sso_idser_fkey
FOREIGN KEY (sso_idser)
REFERENCES "public"."sc_servei"(sso_idser)
;
CREATE UNIQUE INDEX sso_pk_id ON "public"."sc_sso"(sso_id)
;
CREATE UNIQUE INDEX sc_sso_pkey ON "public"."sc_sso"(sso_id)
;
ALTER TABLE "public"."sc_tarcpd"
ADD CONSTRAINT sc_tarcpd_tcp_idusu_fkey
FOREIGN KEY (tcp_idusu)
REFERENCES "public"."sc_usuari"(tcp_idusu)
;
CREATE UNIQUE INDEX tcp_pk_codi ON "public"."sc_tarcpd"(tcp_codi)
;
CREATE UNIQUE INDEX sc_tarcpd_pkey ON "public"."sc_tarcpd"(tcp_codi)
;
ALTER TABLE "public"."sc_target"
ADD CONSTRAINT sc_target_tar_idusu_fkey
FOREIGN KEY (tar_idusu)
REFERENCES "public"."sc_usuari"(tar_idusu)
;
CREATE UNIQUE INDEX sc_target_pkey ON "public"."sc_target"(tar_id)
;
CREATE UNIQUE INDEX tar_uk_codi ON "public"."sc_target"(tar_codi)
;
CREATE UNIQUE INDEX tar_pk_id ON "public"."sc_target"(tar_id)
;
CREATE UNIQUE INDEX sc_tasklog_pk ON "public"."sc_tasklog"
(
  tlo_idtasque,
  tlo_codidispat
)
;
CREATE UNIQUE INDEX sc_tasklog_pkey ON "public"."sc_tasklog"
(
  tlo_codidispat,
  tlo_idtasque
)
;
CREATE UNIQUE INDEX sc_tasque_pkey ON "public"."sc_tasque"(tas_id)
;
CREATE INDEX sc_tas_hash ON "public"."sc_tasque"(tas_hash)
;
CREATE UNIQUE INDEX tas_pk_id ON "public"."sc_tasque"(tas_id)
;
CREATE UNIQUE INDEX sc_tipdad_pkey ON "public"."sc_tipdad"(tda_codi)
;
CREATE UNIQUE INDEX tda_pk_codi ON "public"."sc_tipdad"(tda_codi)
;
CREATE UNIQUE INDEX tda_uk_ordre ON "public"."sc_tipdad"(tda_ordre)
;
CREATE UNIQUE INDEX tda_uk_id ON "public"."sc_tipdad"(tda_id)
;
ALTER TABLE "public"."sc_tipdis"
ADD CONSTRAINT sc_tipdis_tpd_iddis_fkey
FOREIGN KEY (tpd_iddis)
REFERENCES "public"."sc_dispat"(tpd_iddis)
;
CREATE UNIQUE INDEX sys_c005377 ON "public"."sc_tipdis"(tpd_id)
;
CREATE UNIQUE INDEX sc_tipexe_pkey ON "public"."sc_tipexe"(exe_codi)
;
CREATE UNIQUE INDEX exe_pk_codi ON "public"."sc_tipexe"(exe_codi)
;
ALTER TABLE "public"."sc_tipusuo"
ADD CONSTRAINT sc_tipusuo_tuo_pare_fkey
FOREIGN KEY (tuo_pare)
REFERENCES "public"."sc_tipusuo"(tuo_pare)
;
CREATE UNIQUE INDEX tuo_uk_codi ON "public"."sc_tipusuo"(tuo_codi)
;
CREATE UNIQUE INDEX sc_tipusuo_pkey ON "public"."sc_tipusuo"(tuo_id)
;
CREATE UNIQUE INDEX sc_tipusuo_pk ON "public"."sc_tipusuo"(tuo_id)
;
ALTER TABLE "public"."sc_usuari"
ADD CONSTRAINT sc_usuari_usu_idmapr_fkey
FOREIGN KEY (usu_idmapr)
REFERENCES "public"."sc_maquin"(usu_idmapr)
;
ALTER TABLE "public"."sc_usuari"
ADD CONSTRAINT sc_usuari_usu_idmaco_fkey
FOREIGN KEY (usu_idmaco)
REFERENCES "public"."sc_maquin"(usu_idmaco)
;
ALTER TABLE "public"."sc_usuari"
ADD CONSTRAINT sc_usuari_usu_iddco_fkey
FOREIGN KEY (usu_iddco)
REFERENCES "public"."sc_domcor"(usu_iddco)
;
ALTER TABLE "public"."sc_usuari"
ADD CONSTRAINT sc_usuari_usu_idmaq_fkey
FOREIGN KEY (usu_idmaq)
REFERENCES "public"."sc_maquin"(usu_idmaq)
;
ALTER TABLE "public"."sc_usuari"
ADD CONSTRAINT sc_usuari_usu_idgru_fkey
FOREIGN KEY (usu_idgru)
REFERENCES "public"."sc_grups"(usu_idgru)
;
CREATE UNIQUE INDEX usu_uk_codi ON "public"."sc_usuari"(usu_codi)
;
CREATE UNIQUE INDEX sc_usuari_pkey ON "public"."sc_usuari"(usu_id)
;
CREATE INDEX usu_dco_fk_i ON "public"."sc_usuari"(usu_iddco)
;
CREATE INDEX usu_maq_fk_i ON "public"."sc_usuari"(usu_idmaq)
;
CREATE INDEX usu_gru_fk_i ON "public"."sc_usuari"(usu_idgru)
;
CREATE UNIQUE INDEX usu_uk_nomcur_iddco ON "public"."sc_usuari"
(
  usu_nomcur,
  usu_iddco
)
;
CREATE UNIQUE INDEX usu_pk_id ON "public"."sc_usuari"(usu_id)
;
CREATE INDEX usu_maq_sco_fk_i ON "public"."sc_usuari"(usu_idmaco)
;
ALTER TABLE "public"."sc_usugru"
ADD CONSTRAINT sc_usugru_ugr_idusu_fkey
FOREIGN KEY (ugr_idusu)
REFERENCES "public"."sc_usuari"(ugr_idusu)
;
ALTER TABLE "public"."sc_usugru"
ADD CONSTRAINT sc_usugru_ugr_idgru_fkey
FOREIGN KEY (ugr_idgru)
REFERENCES "public"."sc_grups"(ugr_idgru)
;
CREATE UNIQUE INDEX ugr_uk_id ON "public"."sc_usugru"(ugr_id)
;
CREATE UNIQUE INDEX ugr_pk_idgru_idusu ON "public"."sc_usugru"
(
  ugr_idgru,
  ugr_idusu
)
;
CREATE INDEX ugr_usu_fk_i ON "public"."sc_usugru"(ugr_idusu)
;
CREATE INDEX ugr_gru_fk_i ON "public"."sc_usugru"(ugr_idgru)
;
CREATE UNIQUE INDEX sc_usugru_pkey ON "public"."sc_usugru"
(
  ugr_idgru,
  ugr_idusu
)
;
ALTER TABLE "public"."sc_usuimp"
ADD CONSTRAINT sc_usuimp_uim_idusu_fkey
FOREIGN KEY (uim_idusu)
REFERENCES "public"."sc_usuari"(uim_idusu)
;
ALTER TABLE "public"."sc_usuimp"
ADD CONSTRAINT sc_usuimp_uim_idimp_fkey
FOREIGN KEY (uim_idimp)
REFERENCES "public"."sc_impres"(uim_idimp)
;
CREATE INDEX uim_imp_fk_i ON "public"."sc_usuimp"(uim_idimp)
;
CREATE UNIQUE INDEX uim_pk_id ON "public"."sc_usuimp"(uim_id)
;
CREATE UNIQUE INDEX sc_usuimp_pkey ON "public"."sc_usuimp"(uim_id)
;
CREATE INDEX uim_usu_fk_i ON "public"."sc_usuimp"(uim_idusu)
;
CREATE UNIQUE INDEX uim_uk_idimp_idusu ON "public"."sc_usuimp"
(
  uim_idimp,
  uim_idusu
)
;
ALTER TABLE "public"."sc_usulco"
ADD CONSTRAINT sc_usulco_ulc_idusu_fkey
FOREIGN KEY (ulc_idusu)
REFERENCES "public"."sc_usuari"(ulc_idusu)
;
ALTER TABLE "public"."sc_usulco"
ADD CONSTRAINT sc_usulco_ulc_idlco_fkey
FOREIGN KEY (ulc_idlco)
REFERENCES "public"."sc_llicor"(ulc_idlco)
;
CREATE UNIQUE INDEX ulc_pk_idlco_idusu ON "public"."sc_usulco"
(
  ulc_idlco,
  ulc_idusu
)
;
CREATE INDEX lco_usu ON "public"."sc_usulco"(ulc_idusu)
;
CREATE UNIQUE INDEX sc_usulco_pkey ON "public"."sc_usulco"
(
  ulc_idlco,
  ulc_idusu
)
;
ALTER TABLE "public"."sc_usupue"
ADD CONSTRAINT sc_usupue_upe_idusu_fkey
FOREIGN KEY (upe_idusu)
REFERENCES "public"."sc_usuari"(upe_idusu)
;
ALTER TABLE "public"."sc_usupue"
ADD CONSTRAINT sc_usupue_upe_idpue_fkey
FOREIGN KEY (upe_idpue)
REFERENCES "public"."sc_punent"(upe_idpue)
;
CREATE INDEX pue_usu_2 ON "public"."sc_usupue"(upe_idusu)
;
CREATE INDEX pue_usu_1 ON "public"."sc_usupue"(upe_idpue)
;
CREATE UNIQUE INDEX upe_pk_idpue_idusu ON "public"."sc_usupue"
(
  upe_idpue,
  upe_idusu
)
;
CREATE UNIQUE INDEX upe_pk_id ON "public"."sc_usupue"(upe_id)
;
CREATE UNIQUE INDEX sc_usupue_pkey ON "public"."sc_usupue"(upe_id)
;
ALTER TABLE "public"."sc_ususeu"
ADD CONSTRAINT sc_ususeu_use_usuid_fkey
FOREIGN KEY (use_usuid)
REFERENCES "public"."sc_usuari"(use_usuid)
;
CREATE UNIQUE INDEX sc_ususeu_pkey ON "public"."sc_ususeu"(use_id)
;
CREATE UNIQUE INDEX sc_ususeu_usu ON "public"."sc_ususeu"(use_usuid)
;
CREATE UNIQUE INDEX sc_ususeu_pk ON "public"."sc_ususeu"(use_id)
;
ALTER TABLE "public"."sc_valor_domini"
ADD CONSTRAINT sc_valor_domini_vdo_dom_fkey
FOREIGN KEY (vdo_dom)
REFERENCES "public"."sc_domapp"(vdo_dom)
;
CREATE UNIQUE INDEX unique_valor_domini ON "public"."sc_valor_domini"
(
  vdo_dom,
  vdo_desc,
  vdo_valor
)
;
CREATE UNIQUE INDEX sys_c004409 ON "public"."sc_valor_domini"(vdo_id)
;
CREATE UNIQUE INDEX sc_valor_domini_pkey ON "public"."sc_valor_domini"(vdo_id)
;
CREATE UNIQUE INDEX usu_wl_pk_id ON "public"."sc_wl_usuari"(usu_codi)
;
CREATE UNIQUE INDEX sc_wl_usuari_pkey ON "public"."sc_wl_usuari"(usu_codi)
;
CREATE UNIQUE INDEX sc_wl_usugru_pkey ON "public"."sc_wl_usugru"
(
  ugr_codgru,
  ugr_codusu
)
;
CREATE UNIQUE INDEX ugr_wl_pk_id ON "public"."sc_wl_usugru"
(
  ugr_codusu,
  ugr_codgru
)
;
CREATE UNIQUE INDEX xar_pk_id ON "public"."sc_xarxes"(xar_id)
;
CREATE UNIQUE INDEX xar_uk_codi ON "public"."sc_xarxes"(xar_codi)
;
CREATE UNIQUE INDEX sc_xarxes_pkey ON "public"."sc_xarxes"(xar_id)
;
CREATE UNIQUE INDEX xar_uk_adrip ON "public"."sc_xarxes"(xar_adrip)
;


CREATE SEQUENCE hibernate_sequence;