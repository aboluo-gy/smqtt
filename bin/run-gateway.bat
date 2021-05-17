@echo off
echo.
echo [��Ϣ] ����gateway���̡�
echo.

cd %~dp0
cd ../smqtt-gateway/target

set JAVA_OPTS=-Xms512m -Xmx1024m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=512m

java -Dfile.encoding=utf-8 -jar %JAVA_OPTS% smqtt-gateway.jar

cd bin
pause
