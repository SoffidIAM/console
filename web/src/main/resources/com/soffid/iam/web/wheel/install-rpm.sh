wget -O /tmp/syncserver.rpm 'https://download.soffid.com/maven/com/soffid/iam/sync/syncserver/VERSION/syncserver-VERSION.rpm' 
sudo yum install java-11-openjdk
sudo rpm -i /tmp/syncserver.rpm
sudo /opt/soffid/iam-sync/bin/configure -configurl 'URL'
