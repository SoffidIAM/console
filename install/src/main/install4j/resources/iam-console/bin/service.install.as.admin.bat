@echo off
cls
REM================================================
REM Licensed to the Apache Software Foundation (ASF) under one or more
REM contributor license agreements.  See the NOTICE file distributed with
REM this work for additional information regarding copyright ownership.
REM The ASF licenses this file to You under the Apache License, Version 2.0
REM (the "License"); you may not use this file except in compliance with
REM the License.  You may obtain a copy of the License at
REM
REM    http://www.apache.org/licenses/LICENSE-2.0
REM
REM Unless required by applicable law or agreed to in writing, software
REM distributed under the License is distributed on an "AS IS" BASIS,
REM WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
REM See the License for the specific language governing permissions and
REM limitations under the License.
REM _______________________________________________
REM $Rev$
REM================================================

@if not "%ECHO%" == ""  echo %ECHO%
@if "%OS%" == "Windows_NT" setlocal

set OLD_SERVICEUSER=%PR_SERVICEUSER%
set OLD_SERVICEPASSWORD=%PR_SERVICEPASSWORD%

if "%OS%" == "Windows_NT" (
  set "DIRNAME=%~dp0%"
) else (
  set DIRNAME=.\
)

pushd %DIRNAME%

MKDIR "%DIRNAME%\..\work" 
MKDIR "%DIRNAME%\..\temp" 


IF NOT EXIST "%DIRNAME%\..\conf\tomee.xml" COPY "%DIRNAME%\..\conf\tomee.xml.template" "%DIRNAME%\..\conf\tomee.xml" 
IF NOT EXIST "%DIRNAME%\..\conf\server.xml" COPY "%DIRNAME%\..\conf\server.xml.template" "%DIRNAME%\..\conf\server.xml" 
IF NOT EXIST "%DIRNAME%\..\conf\system.properties" COPY "%DIRNAME%\..\conf\system.properties.template" "%DIRNAME%\..\conf\system.properties" 

CACLS "%DIRNAME%\.." /T /E /C /G "NT Authority\LocalService:F" >NUL: 

:checkUser
if "x%1x" == "x/service-userx" goto serviceUser
if "x%1x" == "x--service-userx" goto serviceUser
if "x%1x" == "x/service-passwordx" goto servicePassword
if "x%1x" == "x--service-passwordx" goto servicePassword

goto install

:serviceUser
shift
if "x%1x" == "xx" goto displayUsage
set PR_SERVICEUSER=%1
shift
goto checkUser

:servicePassword
shift
if "x%1x" == "xx" goto displayUsage
set PR_SERVICEPASSWORD=%1
shift
goto checkUser

:install
call service install
set PR_SERVICEUSER=%OLD_SERVICEUSER%
set PR_SERVICEPASSWORD=%OLD_SERVICEPASSWORD%
rd /q /s "%DIRNAME%\..\work\soffid"
rd /q /s "%DIRNAME%\..\temp"
net start soffid-iamconsole
start http://localhost:8080