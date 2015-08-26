--ATENCIO: TAN SOLS S'HA D'EXECUTAR A PRODUCCIÓ
-- S'HA DE FER AL SEYCON 2
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

commit;