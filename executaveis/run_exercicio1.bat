@echo off
REM Caminho da pasta do projeto
set "PROJECT_DIR=../exercicio1TotalEleitores"

REM Nome completo da classe principal
set "MAIN_CLASS=com.exercicio1TotalEleitores.Main"

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

REM Executa a classe principal
echo Executando a aplicação...
mvn exec:java -Dexec.mainClass=%MAIN_CLASS%

pause
