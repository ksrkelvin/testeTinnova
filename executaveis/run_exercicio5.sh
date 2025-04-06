#!/bin/bash

# Caminho da pasta do projeto
PROJECT_DIR="../exercicio5ApiCadastroVeiculos"

# Entra na pasta do projeto
cd "$PROJECT_DIR" || { echo "Pasta $PROJECT_DIR não encontrada."; exit 1; }

# Verifica se o compose.yml existe
if [ ! -f "compose.yaml" ]; then
  echo "Arquivo compose.yml não encontrado na pasta $PROJECT_DIR."
  exit 1
fi

# Sobe os containers
echo "Iniciando os serviços com Docker Compose..."
docker-compose up --build

