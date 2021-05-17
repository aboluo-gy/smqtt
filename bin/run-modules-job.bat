@echo off
echo.
echo [��Ϣ] ����modules-job���̡�
echo.

cd %~dp0
cd ../smqtt-modules/smqtt-job/target

set JAVA_OPTS=-Xms512m -Xmx1024m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=512m

java -Dfile.encoding=utf-8 -jar %JAVA_OPTS% smqtt-modules-job.jar

cd bin
pause
