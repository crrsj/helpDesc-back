# 🛠️ Sistema de Helpdesk - API REST

API REST desenvolvida para gerenciamento de chamados de suporte técnico (helpdesk), com recursos de cadastro de usuários, abertura de tickets  visualização de chamados, etc.

---

## 🚀 Tecnologias Utilizadas

| Tecnologia     | Versão / Descrição                          |
|----------------|---------------------------------------------|
| ☕ **Java**     | 21                                          |
| 🌱 **Spring Boot** | Framework principal para construção da API |
| 🗃️ **H2 Database** | Banco de dados em memória para testes     |
| 📑 **Swagger UI** | Documentação interativa da API             |
| 🔄 **ModelMapper** | Conversão entre entidades e DTOs          |

---

## 📌 Funcionalidades

- 👤 Cadastro de usuários
- 📝 Abertura de chamados (tickets)
- 📄 Listagem de chamados com informações do usuário
- 🗨️ Adição de comentários (em progresso)
- 📅 Registro automático da data de criação
- ⚙️ Enum de status para controle do andamento dos chamados

---

## 📂 Estrutura de Camadas

📦 helpdesk-api ┣ 📁 controller ┣ 📁 service ┣ 📁 repository ┣ 📁 dto ┣ 📁 entidade ┣ 📄 application.properties ┗ 📄 HelpdeskApplication.java
🛠️ Configurações do Banco de Dados (H2)

Acesse o console do H2:

bash
Copiar
Editar
http://localhost:8080/h2-console
Credenciais padrão:

JDBC URL: jdbc:h2:mem:testdb

Usuário: sa
Senha: (em branco)

📃 Exemplos de Endpoints
🔸 Cadastrar Usuário

POST /usuarios
Content-Type: application/json

{
  "nome": "Carlos Junior",
  "email": "carlos@email.com",
  "telefone": "11999999999",
  "cpf": "12345678900"
}

POST /tickets
Content-Type: application/json

{  
  "codigo": "0000",
   "criadoEm" :"13/04/2025"
  "titulo": "Erro na aplicação",
  "descricao": "Erro ao tentar logar",
  "usuario": {
    "id": 1
  }
}


POST /comentarios
Content-Type: application/json
🔸 Corpo da Requisição
json
Copiar
Editar
{
  "mensagem": "Estamos analisando o problema relatado.",
  "ticketId": 1
}
