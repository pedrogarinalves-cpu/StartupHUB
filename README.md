# StartupHub

Plataforma de gestão interna para pequenas startups/equipes de tecnologia, desenvolvida em Java com Spring Boot. Segundo projeto do portfólio, criado especificamente para praticar autenticação/autorização — um conceito que ainda não fazia parte da experiência prática do primeiro projeto (Queue Management API).

## Status do projeto

**Em desenvolvimento inicial.** Entidades de domínio (`Usuario`, `Role`) e repositório criados. Primeiras peças do Spring Security implementadas: codificação de senha (BCrypt), cadastro de usuário com senha hasheada, e a ponte entre `Usuario` e o mecanismo de autenticação do Spring (`UserDetails`). Configuração completa da autenticação (login, geração de token JWT, filtro de segurança) ainda em andamento.

## Sobre o projeto

A ideia central: uma startup pequena normalmente tem clientes, projetos, tarefas e membros de equipe espalhados em várias ferramentas. O StartupHub centraliza uma parte disso, funcionando como uma simulação de produto B2B, não apenas mais um CRUD isolado.

## Escopo da v1 (reduzido, intencionalmente)

Para manter o projeto viável como estudo em paralelo a outro projeto principal, a v1 foi definida de forma enxuta:

- **Usuario** (com login e papéis de acesso)
- **Cliente**
- **Projeto**
- **Tarefa**
- Autenticação simples com Spring Security + JWT

**Multi-tenancy (conceito de "Empresa", isolando dados entre organizações diferentes) foi deliberadamente deixado para a v2**, junto com funcionalidades mais avançadas como tickets, dashboard e integração com a API do GitHub.

## Estrutura do projeto

```
com.startuphub
├── core/
│   ├── model/        → Entidades de domínio (Usuario, Role)
│   ├── repository/   → Repositórios JPA (UsuarioRepository)
│   ├── service/       → Lógica de negócio (UsuarioService)
│   └── exception/     → Exceções de domínio (a definir)
└── api/
    ├── dto/           → Objetos de entrada/saída da API (a definir)
    ├── controller/    → Endpoints REST (a definir)
    └── security/      → Configuração do Spring Security e JWT (SecurityConfig, UsuarioDetails, AutenticacaoService)
```

## Domínio

- **Usuario**: entidade JPA com `id`, `nome`, `email` (único, usado como login) e `senha` (armazenada como hash via BCrypt, nunca em texto puro). Possui construtor completo (com `id`, usado pelo Hibernate) e um construtor reduzido para criação de novos usuários.
- **Role**: enum com os papéis de acesso (`ADMIN`, `GESTOR`, `MEMBRO`).

## Autenticação (em construção)

Peças do Spring Security implementadas até agora:

- **`SecurityConfig`**: classe de configuração (`@Configuration`) que expõe o `PasswordEncoder` (implementação `BCryptPasswordEncoder`) como Bean gerenciado pelo Spring.
- **`UsuarioService`**: contém o método `cadastrar(...)`, responsável por transformar a senha em texto puro num hash (via `PasswordEncoder`) antes de persistir o `Usuario`.
- **`UsuarioDetails`**: classe que implementa a interface `UserDetails` do Spring Security, adaptando a entidade `Usuario` para o formato que o framework entende durante a autenticação (username, senha, papéis/authorities).

Próximas peças a implementar: `AutenticacaoService` (implementando `UserDetailsService`, para o Spring Security buscar o usuário pelo email durante o login), geração e validação de token JWT, filtro de segurança (`OncePerRequestFilter`) e os endpoints de cadastro/login.

## Tecnologias

- Java 17
- Spring Boot
- Spring Security (BCrypt implementado; JWT em implementação)
- Spring Data JPA
- MySQL
- Maven
- Lombok
- Bean Validation

## Próximos passos

- [x] `UsuarioRepository`
- [x] `PasswordEncoder` (BCrypt) configurado
- [x] `UsuarioService` com cadastro e hash de senha
- [x] `UsuarioDetails` (adaptação para `UserDetails`)
- [ ] `AutenticacaoService` (`UserDetailsService`)
- [ ] Geração e validação de JWT
- [ ] Filtro de segurança e `SecurityConfig` completo (rotas públicas x protegidas)
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

Este projeto trabalha em paralelo com o [Queue Management API](../queue-management-api), que já está na v1 concluída (domínio, API REST, persistência MySQL, tratamento de erros HTTP) e com a v2 em andamento (testes automatizados concluídos). Enquanto o Queue Management API segue evoluindo, o StartupHub cobre uma lacuna diferente do portfólio: autenticação, autorização e, futuramente, arquitetura multiempresa.

## Como rodar

```bash
./mvnw spring-boot:run
```

> Observação: projeto em fase inicial. Autenticação ainda não está funcional de ponta a ponta (faltam login, JWT e filtro de segurança).
