# Desafio Votação 

API de Gerenciamento de votos.
### Funcionalidades

- [x] Cadastrar Associados
- [x] Cadastrar Associado como votante de uma Pauta
- [x] Cadastrar Pautas a serem votadas
- [x] Listar todas as Pautas
- [x] Atualizar Pauta
- [x] Buscar Pauta
- [x] Deletar Pauta
- [x] Cadastrar Votos em uma Pauta
- [x] Resultado da votação em uma Pauta



### Tecnologias



| Uso                         | Tecnologia       |
| --------------------------- |------------------|
| Linguagem de Programação    | Java 17          |
| Banco de Dados              | PostgreSQL       |
| Framework                   | Spring Boot      |
| Interface para consultar BD | DBeaver          |
| Documentação                | Swagger          |
| Testes Unitários            | jUnit5 e Mockito |



### Como rodar o projeto



Antes de rodar o projeto é necessário ter instalado e configurado na máquina o Banco de Dados. Outra opção é fazer a instalação do Docker e  configurar um container para subir um BD.

O `application.properties` está configurado da seguinte forma:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5431/votacao
spring.datasource.username=root
spring.datasource.password=123
```

Mas se preferir você pode criar um banco com nome, usuário e senha da sua preferencia e alterar essas configurações
no application.properties.

## Testes

#### Use no terminal mvn test para rodar os testes

## Swagger

```
http://localhost:8080/swagger-ui/index.html
http://localhost:8080/v3/api-docs
```