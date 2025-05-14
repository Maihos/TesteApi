# Cadastro de Usuários API - Spring Boot

Este é um projeto em Java utilizando o **Spring Boot** para implementar uma API de cadastro de usuários. A API oferece funcionalidades básicas para cadastro, atualização, exclusão, busca de um usuário específico pelo seu **ID**, e listagem de todos os usuários registrados. Além disso, a API também garante que o **e-mail** não seja registrado mais de uma vez e utiliza a **data de nascimento** como uma senha provisória para o acesso ao cadastro do usuário.

## Funcionalidades

1. **Cadastrar usuário**:
   - Permite o cadastro de novos usuários fornecendo os seguintes dados:
     - Nome
     - E-mail (A API verifica se o e-mail já foi registrado)
     - Data de Nascimento (Que será utilizada como senha provisória para o acesso do usuário)
   
2. **Atualizar cadastro**:
   - Permite que o usuário atualizado suas informações pessoais, exceto o e-mail (pois o e-mail é único e não pode ser modificado).

3. **Deletar cadastro**:
   - Permite a exclusão de um usuário existente pelo seu **ID**.

4. **Buscar usuário por ID**:
   - Permite a busca de um usuário específico através do seu **ID**.

5. **Mostrar todos os usuários cadastrados**:
   - Exibe a lista de todos os usuários registrados na base de dados.

## Requisitos

- **Java** 11 ou superior
- **Spring Boot** 2.x
- **JPA / Hibernate** (para persistência dos dados)
- **Banco de dados**: H2 (por padrão) ou qualquer outro banco de dados relacional (como MySQL, PostgreSQL).
  
## Tecnologias Usadas

- **Spring Boot** (Framework principal)
- **Spring Data JPA** (Para interação com o banco de dados)
- **Spring Web** (Para criar as APIs RESTful)
- **H2 Database** (Banco de dados embutido para testes)
- **Spring Validation** (Para validação de dados de entrada)
- **DTO (Data Transfer Object)** (Para desacoplar a camada de persistência da camada de apresentação)
  
## Endpoints
### 1. **Cadastrar Usuário**

**POST /api/cadastrar**

- **Request Body**:
  ```json
  {
    "nome": "Nome do Usuário",
    "email": "email@dominio.com",
    "dataNascimento": "YYYY-MM-DD"
  }
  
**Resposta:**

- 201 CREATED: Usuário cadastrado com sucesso.

- 400 Bad Request: Caso o e-mail já exista na base de dados.

## 2. Atualizar Usuário
PUT /api/atualizar/{id}

- **Request Body:**

  json
  Copiar
  Editar
  ```json  
  {
    "nome": "Novo Nome",
    "dataNascimento": "YYYY-MM-DD",
    "senha": "Nova Senha"
  }
**Resposta:**

- 200 OK: Usuário atualizado com sucesso.

- 404 Not Found: Usuário não encontrado.

## 3. Deletar Usuário
DELETE /api/deletar/{id}

**Resposta:**

- 200 OK: Usuário deletado com sucesso.

- 404 Not Found: Usuário não encontrado.

## 4. Buscar Usuário por ID
GET /api/lista/{id}

**Resposta:**

- 200 OK: Usuário encontrado com sucesso.

- 404 Not Found: Usuário não encontrado.

## 5. Mostrar Todos os Usuários
GET /api/lista

**Resposta:**

- 200 OK: Lista de usuários cadastrados.
