Buildroot: /home/gbuades/soffid/console/install/target/soffid-iamconsole-3.0.0
Name: soffid-iamconsole
Version: ${parsedVersion.majorVersion}.${parsedVersion.minorVersion}.${parsedVersion.incrementalVersion}
Release: 1
Summary: Soffid IAM Console
License: GPL
Distribution: Soffid IAM
Group: Administration/Netwokr

%define _rpmdir target
%define _rpmfilename %%{NAME}-%%{VERSION}-%%{RELEASE}.%%{ARCH}.rpm
%define _unpackaged_files_terminate_build 0

%post
#!/bin/sh
set -e
if ! getent group soffid >/dev/null 2>&1; then
    groupadd soffid
fi

if ! getent passwd soffid >/dev/null 2>&1; then
    adduser --system --gid soffid --no-create-home --home-dir /opt/soffid soffid
fi
chown -R soffid:soffid /opt/soffid/iam-console-3

# This will only remove masks created by d-s-h on package removal.
systemctl enable 'soffid-iamconsole.service' || true 	
systemctl start soffid-iamconsole || true 

echo "Starting Soffid console. Please connect to http://localhost:8080 to configure"

%preun
#!/bin/sh
#!/bin/bash

systemctl stop 'soffid-iamconsole.service' || true

systemctl disable 'soffid-iamconsole.service' || true

%description


(Converted from a deb package by alien version 8.95.)

%files
%config "/opt/soffid/iam-console-3/bin/env.sh"
%config "/opt/soffid/iam-console-3/conf/server.xml"
%config "/opt/soffid/iam-console-3/conf/tomee.xml"
