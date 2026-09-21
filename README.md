# StartupHub

Plataforma de gestão interna para pequenas startups/equipes de tecnologia, desenvolvida em Java com Spring Boot. Segundo projeto do portfólio, criado especificamente para praticar autenticação/autorização — um conceito que ainda não fazia parte da experiência prática do primeiro projeto (Queue Management API).

## Status do projeto

**Em desenvolvimento inicial.** Primeira entidade de domínio (`Usuario`) criada. Autenticação, demais entidades e API REST ainda por vir.

## Sobre o projeto

A ideia central: uma startup pequena normalmente tem clientes, projetos, tarefas e membros de equipe espalhados em várias ferramentas. O StartupHub centraliza uma parte disso, funcionando como uma simulação de produto B2B, não apenas mais um CRUD isolado.

## Escopo da v1 (reduzido, intencionalmente)

Para manter o projeto viável como estudo em paralelo a outro projeto principal, a v1 foi definida de forma enxuta:

- **Usuario** (com login e papéis de acesso)
- **Cliente**
- **Projeto**
- **Tarefa**
- Autenticação simples com Spring Security + JWT

**Multi-tenancy (conceito de "Empresa", isolando dados entre organizações diferentes) foi deliberadamente deixado para a v2**, junto com funcionalidades mais avançadas como tickets, dashboard e integração com a API do GitHub. A decisão foi tomada para evitar acumular complexidade (autenticação + multi-tenancy ao mesmo tempo) antes de ter uma base sólida funcionando.

## Estrutura do projeto

```
com.startuphub
├── core/
│   ├── model/        → Entidades de domínio (Usuario, Role, ...)
│   ├── repository/   → Repositórios JPA
│   ├── service/       → Lógica de negócio
│   └── exception/     → Exceções de domínio
└── api/
    ├── dto/           → Objetos de entrada/saída da API
    ├── controller/    → Endpoints REST
    └── security/      → Configuração do Spring Security e JWT
```

## Domínio (em construção)

- **Usuario**: entidade JPA com `id`, `nome`, `email` (único, usado como login) e `senha` (armazenada como hash, nunca em texto puro).
- **Role**: enum com os papéis de acesso (`ADMIN`, `GESTOR`, `MEMBRO`), que futuramente vão diferenciar permissões dentro do sistema.

## Tecnologias

- Java 17
- Spring Boot
- Spring Security (JWT) — em implementação
- Spring Data JPA
- MySQL
- Maven
- Lombok
- Bean Validation

## Próximos passos

- [ ] `UsuarioRepository`
- [ ] Configuração do Spring Security (filtro de autenticação, geração e validação de JWT)
- [ ] Endpoints de cadastro e login
- [ ] Entidades `Cliente`, `Projeto`, `Tarefa`
- [ ] API REST para gestão de projetos e tarefas
- [ ] Autorização por papel (`ADMIN`, `GESTOR`, `MEMBRO`) nos endpoints

## Visão futura (v2 e além)

- **Multi-tenancy**: isolar dados entre diferentes empresas/organizações usando o mesmo sistema.
- **Sistema de tickets**: fluxo de status (aberto → em análise → em desenvolvimento → resolvido → fechado).
- **Dashboard**: métricas agregadas (projetos ativos, tarefas pendentes, tickets por prioridade).
- **Integração com GitHub**: vincular projetos a repositórios, consultando commits, branches e pull requests via API externa.
- **IA aplicada**: resumo automático de projetos e classificação de tickets, como funcionalidade de estudo futuro.

## Relação com o outro projeto do portfólio

Este projeto trabalha em paralelo com o [Queue Management API](../queue-management-api), que já está na v1 concluída (domínio, API REST, persistência MySQL, tratamento de erros HTTP). Enquanto o Queue Management API evolui para sua v2 (testes automatizados, CI/CD, Bean Validation), o StartupHub cobre uma lacuna diferente do portfólio: autenticação, autorização e, futuramente, arquitetura multiempresa.

## Como rodar

```bash
./mvnw spring-boot:run
```

> Observação: projeto em fase inicial. Autenticação e demais funcionalidades ainda não implementadas.
