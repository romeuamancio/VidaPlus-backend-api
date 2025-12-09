# 🏥 Clinic Backend API

API REST desenvolvida com **Java + Spring Boot** para gerenciamento de pacientes e usuários, com autenticação JWT, banco de dados H2 e arquitetura em camadas.

Este projeto foi desenvolvido como parte do trabalho da disciplina de Projetos, com foco em backend, segurança, persistência de dados e boas práticas.

## 🚀 Tecnologias Utilizadas

- Java 21
- Spring Boot 3
- Spring Web
- Spring Data JPA
- Spring Security
- JWT (JSON Web Token)
- H2 Database
- Maven
- Lombok 

## 📚 Funcionalidades

### 🔐 Autenticação e Autorização
- Cadastro de usuários (`/auth/signup`)
- Login com JWT (`/auth/login`)
- Proteção de endpoints com token Bearer
- Roles e permissões básicas

### 👥 Pacientes
- Criar pacientes
- Listar pacientes
- Buscar por ID
- Atualizar paciente
- Remover paciente

### 🛡️ Segurança
- Hash de senha com BCrypt
- JWT com expiração
- Filtro de autenticação
- Rotas públicas e privadas configuradas

## 📁 Estrutura do Projeto (resumo)

```
src/main/java/br/com/clinicap/
├─ config/
│  ├─ JwtAuthenticationFilter.java
│  ├─ SecurityConfig.java
│
├─ controller/
│  ├─ AuthController.java
│  ├─ PacienteController.java
│  │
│  ├─ request/
│  │  ├─ LoginRequest.java
│  │  ├─ SignupRequest.java
│  │  ├─ PacienteRequest.java
│  │
│  ├─ response/
│     ├─ LoginResponse.java
│     ├─ UsuarioResponse.java
│     ├─ PacienteResponse.java
│
├─ domain/
│  ├─ Paciente.java
│  ├─ Usuario.java
│
├─ repository/
│  ├─ PacienteRepository.java
│  ├─ UsuarioRepository.java
│
├─ service/
│  ├─ PacienteService.java
│  ├─ TokenService.java
│  ├─ UsuarioDetailsService.java
│  ├─ UsuarioService.java
│
├─ ClinicBackendApiApplication.java
│
src/main/resources/
├─ application.yaml
├─ data.sql
```

## 🧰 Pré-requisitos

Antes de rodar este projeto, você precisa ter instalado:

- Java 121+
- Maven 3.6+
- Git (opcional)

## ▶️ Como Rodar a Aplicação

### 1. Clone o repositório

```bash
git clone https://github.com/SEU_USUARIO/clinic-backend-api.git
cd clinic-backend-api
```

### 2. Build do projeto

```bash
mvn clean install
```

### 3. Rodar a aplicação

```bash
mvn spring-boot:run
```

Ou:

```bash
java -jar target/clinic-backend-api-0.0.1-SNAPSHOT.jar
```

A aplicação ficará disponível em:

> http://localhost:8080

## 🧪 Testando a API

### 🔐 1. Criar usuário (signup)

```bash
curl -X POST http://localhost:8080/auth/signup -H "Content-Type: application/json" -d '{
  "nome": "João",
  "email": "joao@example.com",
  "senha": "123456"
}'
```

### 🔐 2. Login (gera token JWT)

```bash
curl -X POST http://localhost:8080/auth/login -H "Content-Type: application/json" -d '{
  "email": "joao@example.com",
  "senha": "123456"
}'
```

## 🗂️ Console do Banco H2

Acesse:

> http://localhost:8080/h2-console

Configuração padrão:

- JDBC URL: `jdbc:h2:file:./data/clinicdb`
- User: `sa`
- Password: (deixe vazio)

## 📦 Variáveis de Ambiente (opcional)

Você pode sobrescrever a secret e expiração do JWT:

```properties
security.jwt.secret=MINHA_SECRET
security.jwt.expiration=3600000
```

## 🧪 Documentação dos Endpoints

### Auth

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | /auth/signup | Cadastrar usuário |
| POST | /auth/login | Login com JWT |

### Pacientes

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | /pacientes | Criar paciente |
| GET | /pacientes | Listar todos |
| GET | /pacientes/{id} | Buscar por ID |
| PUT | /pacientes/{id} | Atualizar |
| DELETE | /pacientes/{id} | Remover |

## 🧾 Licença

Este projeto é acadêmico e pode ser utilizado livremente para estudos e trabalhos.

## ✨ Autor

**Seu Nome Aqui**  
- GitHub: https://github.com/SEU_USUARIO  
- LinkedIn: https://www.linkedin.com/in/SEU_USUARIO  
