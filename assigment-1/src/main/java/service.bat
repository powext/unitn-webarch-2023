@echo off
echo Content-type: text/plain
echo.

for  %%k in (%QUERY_STRING%) do set ARGS=%%k
for /f %%k in ('java -jar C:\Users\pow\active_projects\assigment-1\out\artifacts\processes_jar\processes.jar %ARGS%') do set res=%%k
echo %res%
