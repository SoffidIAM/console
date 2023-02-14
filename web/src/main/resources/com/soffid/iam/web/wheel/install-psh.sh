Invoke-WebRequest -Uri syncserver.msi -OutFile  'https://download.soffid.com/maven/com/soffid/iam/sync/syncserver/VERSION/syncserver-VERSION.msi' 
msiexec /i syncserver.msi
c:\program files\soffid\iam-sync\bin\configure -configurl 'URL'