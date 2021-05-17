@echo off
echo.
echo [��Ϣ] ����modules-system���̡�
echo.

cd %~dp0
cd ../smqtt-modules/smqtt-system/target

set JAVA_OPTS=-Xms512m -Xmx1024m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=512m

java -Dfile.encoding=utf-8 -jar %JAVA_OPTS% smqtt-modules-system.jar

cd bin
pause
