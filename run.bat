@echo off
setlocal

if "%DB_URL%"=="" set DB_URL=jdbc:mysql://localhost:3306/inventario
if "%DB_USER%"=="" set DB_USER=root

mvn -q package
if errorlevel 1 goto :error

java -jar target\inventario-tienda-1.0.0.jar
goto :end

:error
echo Error al compilar el proyecto.
exit /b 1

:end
endlocal