@echo off
REM Caminho da pasta do projeto
set "PROJECT_DIR=../exercicio5ApiCadastroVeiculos"

REM Entra na pasta do projeto
cd /d "%PROJECT_DIR%"
if errorlevel 1 (
    echo Pasta %PROJECT_DIR% não encontrada.
    exit /b 1
)

REM Verifica se o compose.yml existe
if not exist "docker-compose.yaml" (
    echo Arquivo docker-compose.yml não encontrado na pasta %PROJECT_DIR%.
    exit /b 1
)

REM Sobe os containers
echo Iniciando os serviços com Docker Compose...
docker-compose up --build

pause

