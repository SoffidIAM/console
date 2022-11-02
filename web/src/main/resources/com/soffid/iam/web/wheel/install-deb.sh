wget -O /tmp/syncserver.deb 'https://download.soffid.com/maven/com/soffid/iam/sync/syncserver/VERSION/syncserver-VERSION.deb' 
sudo DEBIAN_FRONTEND=noninteractive apt install /tmp/syncserver.deb
sudo /opt/soffid/iam-sync/bin/configure -configurl 'URL'