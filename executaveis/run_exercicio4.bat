@echo off
REM Caminho da pasta do projeto
set "PROJECT_DIR=../exercicio4multiplos3e5"

REM Classe principal
set "MAIN_CLASS=com.exercicio4multiplos3e5.Main"

REM Verifica se o argumento foi passado
if "%~1"=="" (
    echo Uso: executarMultiplos3e5.bat ^<numero^>
    exit /b 1
)

set "ARGUMENTO=%~1"

REM Entra na pasta do projeto
cd /d "%PROJECT_DIR%"
if errorlevel 1 (
    echo Pasta %PROJECT_DIR% não encontrada.
    exit /b 1
)

REM Verifica se o projeto já foi compilado
if not exist "target\classes" (
    echo Projeto não compilado. Compilando agora...
    mvn compile
    if errorlevel 1 (
        echo Falha na compilação.
        exit /b 1
    )
) else (
    echo Projeto já compilado.
)

REM Executa a aplicação com o argumento
echo Executando a aplicação com argumento: %ARGUMENTO%
mvn exec:java -Dexec.mainClass=%MAIN_CLASS% -Dexec.args="%ARGUMENTO%"

pause

