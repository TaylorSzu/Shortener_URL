# Shortener URL

Projeto de encurtamento de URLs usando Angular, Spring Boot + Java 24, PostgreSQL e Docker, com Swagger UI integrado.  
O projeto gera URLs curtas usando Base62 e permite testar todas as APIs diretamente pelo site.

## Links Importantes

- Aplicação Deployada: [https://shortener-url-iu04.onrender.com/](https://shortener-url-iu04.onrender.com/)  
- Swagger UI Backend: [https://shortenerurl-8bfw.onrender.com/swagger-ui/index.html](https://shortenerurl-8bfw.onrender.com/swagger-ui/index.html)  
- Repositório GitHub: [https://github.com/TaylorSzu/Shortener_URL](https://github.com/TaylorSzu/Shortener_URL)

## Tecnologias

Angular (Frontend)  
Spring Boot + Java 24 (Backend)  
PostgreSQL (Banco de Dados)  
Docker + Docker Compose  
Base62 (para encurtamento de URLs)  
Swagger UI integrado

## Como Rodar o Projeto

### 1. Rodando com Docker (recomendado)

1. Clone o repositório: `git clone https://github.com/TaylorSzu/Shortener_URL.git && cd Shortener_URL`  
2. Suba todos os serviços via Docker Compose: `docker-compose up --build`  
3. Acesse:
   - Frontend Angular: [http://localhost/](http://localhost/) → exibe o site  
   - Backend Spring Boot: [http://localhost:8080](http://localhost:8080)  
   - Swagger UI integrado: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

### 2. Rodando Localmente sem Docker

#### Backend (Spring Boot)
1. Entre na pasta do backend: `cd backend`  
2. Execute a aplicação: `./mvnw spring-boot:run`  
3. A API estará disponível em: `http://localhost:8080`  
4. Swagger UI: `http://localhost:8080/swagger-ui/index.html`

#### Frontend (Angular)
1. Entre na pasta do frontend: `cd frontend`  
2. Instale as dependências: `npm install`  
3. Rode a aplicação Angular: `ng serve`  
4. Acesse o site em: `http://localhost:4200`  
- O Angular faz proxy para o backend, incluindo o Swagger UI na rota `/swagger`.

## Observações

- PostgreSQL está configurado via Docker Compose e persiste os dados.  
- Base62 é usado internamente para gerar URLs curtas.  
- O Swagger UI pode ser acessado diretamente pelo backend ou via frontend.
