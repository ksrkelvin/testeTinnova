# Teste Tinnova 🚗

Este repositório contém a resolução de 5 exercícios propostos para avaliação, sendo eles:

## 🧠 Exercícios

1. **Totais e Percentuais de eleições**
2. **Bubble Sort**
3. **Fatorial**
4. **Múltiplos de 3 e 5**
5. **API de Cadastro de Veículos** (Spring Boot + JPA + Lombok)

---

## ▶️ Como executar os projetos

Cada exercício possui um script `.sh` para facilitar a execução no terminal dentro da pasta `executaveis/`.

### 🔧 Linux/MacOS

```bash
chmod +x executar.sh
./run_{exercicio}.sh

Api excuta com docker compose
```

### 🪟 Windows (CMD ou PowerShell)

```cmd
./run_{exercicio}.bat
```

---

## 📬 Postman Collection

Você pode importar a collection do Postman para testar a API do **Exercício 5 (API de Cadastro de Veículos)**:

```
./postman/TesteTinnova.postman_collection.json
```

---

## 📚 Documentação da API - Exercício 5

### 🔹 POST `/veiculos` - Cadastrar veículo

**Request_body:**

```json
{
  "veiculo": "Corolla",
  "marca": "Toyota",
  "ano": 2021,
  "cor": "vermelho",
  "vendido": false
}
```

**Response_body:**

```json
{
  "id": 1,
  "veiculo": "Corolla",
  "marca": "TOYOTA",
  "ano": 2021,
  "cor": "vermelho",
  "vendido": false,
  "created": "2025-04-05T11:15:22.641472",
  "updated": "2025-04-05T11:15:22.641487"
}
```

```Curl
curl --location 'http://localhost:8080/veiculos' \
--header 'Content-Type: application/json' \
--data '{
    "veiculo":"Corolla",
    "marca":"Toyota",
    "ano":2021,
    "cor":"vermelho",
    "vendido":false
}'
```

---
---

### 🔹 PUT `/veiculos/{veiculo_id}` - Atualizar veículo inteiro

**Request_body:** (mesmo corpo do POST)

**Response:** (mesmo retorno com `id` do veículo correspondente)

```Curl
curl --location --request PUT 'http://localhost:8080/veiculos/20' \
--header 'Content-Type: application/json' \
--data '{
    "veiculo":"Gol",
    "marca":"volkswagens",
    "ano":2011,
    "cor":"branco",
    "vendido":true
}'
```

---
---

### 🔹 PATCH `/veiculos/{veiculo_id}` - Atualização parcial

**Request_body:**

```json
{
  "vendido": true
}
```

**Response:** Veículo atualizado com campo alterado.


```Curl
curl --location --request PATCH 'http://localhost:8080/veiculos/20' \
--header 'Content-Type: application/json' \
--data '{

    "vendido":false

}'
```

---
---

### 🔹 GET `/veiculos` - Listar todos os veículos

**Response:**

```json
[
  {
    "id": 1,
    "veiculo": "Monza",
    "marca": "CHEVROLET",
    "ano": 1990,
    "cor": "prata",
    "vendido": true
  }
]
```

```Curl
curl --location 'http://localhost:8080/veiculos'
```

---
---

### 🔹 GET `/veiculos?marca=&cor=&ano=` - Filtro por parâmetros

**Query Params:**
- `marca`
- `cor`
- `ano`
- `vendido`

**Response:**

```json
[
  {
    "id": 1,
    "veiculo": "Monza",
    "marca": "CHEVROLET",
    "ano": 1990,
    "cor": "prata",
    "vendido": true
  }
]
```

```Curl
curl --location 'http://localhost:8080/veiculos?marca=chevrolet&cor=prata&ano=1990'
```

---
---

### 🔹 GET `/veiculos/info` - Informações adicionais

**Response:**

```json
{
  "vendidos": 3,
  "decadas": [
    { "decada": 1990, "quantidade": 3 },
    { "decada": 2021, "quantidade": 1 }
  ],
  "fabricantes": [
    { "fabricante": "CHEVROLET", "quantidade": 2 },
    { "fabricante": "VOLKSWAGEN", "quantidade": 3 }
  ],
  "ultimosCadastrados": [
    {
      "id": 12,
      "veiculo": "Corola",
      "marca": "TOYOTA"
    }
  ]
}
```

```Curl
curl --location 'http://localhost:8080/veiculos/info'
```

---
---

### 🔹 GET `/veiculos/{veiculo_id}` - Buscar veículo por ID


**Response_body:**

```json
  {
    "id": 1,
    "veiculo": "Monza",
    "marca": "CHEVROLET",
    "ano": 1990,
    "cor": "prata",
    "vendido": true
  }

```

```Curl
curl --location 'http://localhost:8080/veiculos/9' \
--header 'Content-Type: application/json'
```

---
---

### 🔹 DELETE `/veiculos/{veiculo_id}` - Remover veículo


```Curl
curl --location --request DELETE 'http://localhost:8080/veiculos/2'
```
---

### 🔹 GET `/marcas` - Listar marcas válidas

**Response_body:**

```json
[
  "TOYOTA", "HONDA", "FORD", "CHEVROLET", "VOLKSWAGEN",
  "BMW", "FIAT", "HYUNDAI", "NISSAN", "RENAULT"
]
```

```Curl
curl --location 'http://localhost:8080/marcas'
```

---
---

## 📁 Estrutura

```
.
├── exercicio1/
├── exercicio2/
├── exercicio3/
├── exercicio4/
├── exercicio5/ (API de veículos)
├── postman/
│   └── TesteTinnova.postman_collection.json
└── executaveis/ 
    ├──run_exercicio1.sh
    ├──run_exercicio1.bat
    ├──run_exercicio2.sh
    ├──run_exercicio2.bat
    ├──run_exercicio3.sh
    ├──run_exercicio3.bat
    ├──run_exercicio4.sh
    ├──run_exercicio4.bat
    ├──run_exercicio5.sh
    └──run_exercicio5.bat
```

---

## ✅ Tecnologias

- Java 17
- Spring Boot
- Lombok
- JPA (Hibernate)
- Postman (testes)

---

## 📌 Observações

- O campo `marca e cor` serão salvos em **letras maiúsculas** automaticamente, para evitar erros de sensitiveCase.

---



