# README

Requisitos:

- Java 11
- pgAdmin 4
- Necessário criar um banco de dados “pedidos” e utilizar a porta 5432, é possível alterar essas configurações no arquivo [application.properties](http://application.properties) no projeto, alterando spring.datasource.url.
- Todas as bibliotecas do projeto são baixadas automaticamente com o maven ao clonar o projeto
- O projeto tem 4 objetos principais e cada um seu CRUD (Create, Read, Update e Delete) consequentemente suas respectivas rotas, exemplo:
    - Classe Usuario
        - Create
            - Um post para  “/usuario”
        - Read pode ser feito utilizando ou não paginação
            - Get para “/usuario” ou “/usuario/page”
            - Get para “/id/{id}” onde ‘{id}’ é o número de identificação única do usuário
        - Update
            - Put para “/update/{id}”
        - Delete
            - Delete para “/{id}”

- Após criado o banco e baixada as dependências, iniciar o projeto, por padrão ele será inicializado na porta 8080, é possível alterar no arquivo [application.properties](http://application.properties) alterando o valor de server.port.
- Utilizando JPA o projeto irá inicializar criando a estutura de dados ‘automaticamente’
- A documentação completa foi feita utilizando a o conjunto de ferramente Swagger, e pode ser consultada com a aplicação rodando localmente na máquina pela rota [http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)