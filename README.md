Gym Management System
O Gym Management System é uma aplicação completa de gerenciamento de academias desenvolvida com o framework Spring Boot em Java, com integração de PostgreSQL como banco de dados relacional. O sistema oferece funcionalidades essenciais para administração de academias, como controle de usuários, gerenciamento de treinos personalizados, autenticação e autorização segura via Spring Security, além de integração futura para notificações via SMS e email.

Este projeto também foca na qualidade do código, utilizando boas práticas de desenvolvimento com testes unitários, cobertura de código com JUnit e Mockito. A interface de usuário será desenvolvida utilizando tecnologias modernas de front-end como HTML, CSS e JavaScript, permitindo uma experiência amigável e responsiva.
Estrutura do Projeto
Back-End:
O back-end foi desenvolvido em Java com Spring Boot, uma das mais populares frameworks para desenvolvimento de APIs e aplicações web. As principais dependências incluem:

Spring Web: Para criação da API RESTful.
Spring Data JPA: Para gerenciamento da persistência dos dados com JPA e Hibernate.
Spring Security: Para gerenciamento de autenticação e autorização.
Flyway: Para controle de migrações do banco de dados.
JUnit e Mockito: Para garantir a qualidade do código por meio de testes unitários e mock de dependências.
Banco de Dados:
O banco de dados escolhido foi o PostgreSQL, um sistema de gerenciamento de banco de dados relacional robusto e open-source, amplamente utilizado em aplicações corporativas de grande escala.

Front-End:
O front-end será desenvolvido utilizando as seguintes tecnologias:

HTML5: Para estruturar as páginas da aplicação.
CSS3: Para estilização e responsividade.
JavaScript: Para interatividade e manipulação dinâmica do conteúdo.

Notificações (Em Desenvolvimento)
O projeto também contará com a funcionalidade de notificações por email, permitindo que os usuários recebam alertas sobre novos treinos, atualizações em seus perfis, e lembretes sobre planos de treino diretamente em seus dispositivos móveis.

Objetivos Futuros
Algumas funcionalidades planejadas para versões futuras incluem:

Relatórios de Desempenho: Geração de relatórios detalhados sobre a evolução dos alunos e desempenho físico com base nos treinos concluídos.
Integração com Pagamentos: Implementação de gateways de pagamento para que os usuários possam pagar pelos planos de treino diretamente na plataforma.
