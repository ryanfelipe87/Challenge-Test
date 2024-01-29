# ECM Projetos Industriais Ltda.

Este projeto foi desenvolvido em virtude de um processo seletivo, em que foi construído uma API REST para gerenciamento de catálogo de filmes.

## Pré-requisitos
Antes de executar a aplicação, certifique-se de ter instalado em seu ambiente:

- Acima da versão do Java 1.8
- Maven
- MySQL

## Instruções de execução da aplicação:
1. Baixe o projeto: 'git clone https://github.com/ryanfelipe87/Challenge-Test.git'
2. Abra o terminal e navegue até a pasta do projeto.
3. Execute o seguinte comando no terminal: 'mvn clean package'
4. Inicialize a aplicação com o comando: 'java -jar target\challenge-test-0.0.1-SNAPSHOT.jar'
5. Abra o Swagger no navegador através do link: 'http://localhost:8080/swagger-ui/index.html'

Observações:
- A porta em que a aplicação está rodando é a '8080'. Caso necessário, ajuste a porta ao utilizar o Swagger no navegador.
- Este projeto pode ser executado em qualquer IDE de preferência para desenvolvimento de API com Spring.

## Tecnologias utilizadas
- Java 1.8
- Spring Boot na versão 2.5.5
- MySQL
- Flyway Migrations
- JUnit 5 para testes unitários
- Insomnia para testar a API REST
- Git para versionamento de código e GitHub para hospedagem do projeto

Observações:
- Não foi implementado novos requisitos.
- Consegui implementar os requisitos, porém não consegui finalizar todos os testes unitários, pois tive bastante dificuldade no entendimento dos mocks.
