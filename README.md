# MoneyTrack - API de Controle de Gastos Pessoais

API desenvolvida em Spring Boot para controle de gastos pessoais, permitindo registrar despesas e receitas, categorizar transações e gerar relatórios.

## Funcionalidades

- Registro de despesas e receitas
- Categorização de transações
- Listagem de transações por período
- Cálculo de saldo por período
- Relatórios por tipo de transação

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3.2.3
- Spring Data JPA
- H2 Database
- Docker
- Maven
- Lombok

## Requisitos

- Docker
- Docker Compose

## Como Executar

1. Clone o repositório
2. Na pasta do projeto, execute:
```bash
docker-compose up --build
```

A aplicação estará disponível em `http://localhost:8080`

## Endpoints da API

### Categorias

- `POST /api/categorias` - Criar nova categoria
- `GET /api/categorias/tipo/{tipo}` - Listar categorias por tipo (RECEITA/DESPESA)

### Transações

- `POST /api/transacoes` - Registrar nova transação
- `GET /api/transacoes/periodo` - Listar transações por período
- `GET /api/transacoes/tipo/{tipo}/periodo` - Listar transações por tipo e período
- `GET /api/transacoes/saldo/periodo` - Calcular saldo por período

## Exemplos de Uso

### Criar Categoria
```json
POST /api/categorias
{
    "nome": "Salário",
    "tipo": "RECEITA"
}
```

### Registrar Transação
```json
POST /api/transacoes
{
    "descricao": "Salário Mensal",
    "valor": 5000.00,
    "data": "2024-03-15T10:00:00",
    "categoriaId": 1,
    "tipo": "RECEITA"
}
```

### Listar Transações por Período
```
GET /api/transacoes/periodo?inicio=2024-03-01T00:00:00&fim=2024-03-31T23:59:59
```

### Calcular Saldo
```
GET /api/transacoes/saldo/periodo?inicio=2024-03-01T00:00:00&fim=2024-03-31T23:59:59
``` 