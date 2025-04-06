#!/bin/bash

# Caminho da pasta do projeto
PROJECT_DIR="../exercicio4multiplos3e5"

# Classe principal
MAIN_CLASS="com.exercicio4multiplos3e5.Main"

# Argumento passado pelo usuário (ex: 10)
ARGUMENTO="$1"

# Verificação se argumento foi passado
if [ -z "$ARGUMENTO" ]; then
  echo "Uso: ./run_exercicio4.sh <numero>"
  exit 1
fi

# Entra na pasta do projeto
cd "$PROJECT_DIR" || { echo "Pasta $PROJECT_DIR não encontrada."; exit 1; }

# Verifica se o projeto já foi compilado
if [ ! -d "target/classes" ]; then
  echo "Projeto não compilado. Compilando agora..."
  mvn compile || { echo "Falha na compilação."; exit 1; }
else
  echo "Projeto já compilado."
fi

# Executa a aplicação com o argumento
echo "Executando a aplicação com argumento: $ARGUMENTO"
mvn exec:java -Dexec.mainClass="$MAIN_CLASS" -Dexec.args="$ARGUMENTO"

