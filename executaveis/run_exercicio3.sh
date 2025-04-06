#!/bin/bash

# Caminho da pasta do projeto
PROJECT_DIR="../exercicio3Fatorial"

# Nome completo da classe principal
MAIN_CLASS="com.exercicio3Fatorial.Main"

# Entra na pasta do projeto
cd "$PROJECT_DIR" || { echo "Pasta $PROJECT_DIR não encontrada."; exit 1; }

# Verifica se o projeto já foi compilado
if [ ! -d "target/classes" ]; then
  echo "Projeto não compilado. Compilando agora..."
  mvn compile || { echo "Falha na compilação."; exit 1; }
else
  echo "Projeto já compilado."
fi

# Executa a classe principal
echo "Executando a aplicação..."
mvn exec:java -Dexec.mainClass="$MAIN_CLASS"

