# O que é
Medcare é uma solução para agendamentos médicos, focada em entregar uma API que
realize os trabalhos de agendamento  de consultas médicas e afins.

# Processo
A API Medcare se encarrega de cobrir todo o fluxo de cadastro de médicos e pacientes,
consulta de horários, agendamentos de consultas, feedbacks, retornos médicos e encaminhamentos para outros médicos e consultas.

# <u>[Modelo físico](https://dbdiagram.io/d/6665e2909713410b05224ef1)</u>

# Rodando o docker (na pasta raiz)
- ./mvnw clean -f pom.xml
- ./mvnw package -f pom.xml (se der erro tem que rodar pelo intelliJ que da certo)
- docker build -t imgmedicare .
- docker run -p 8080:8080 --name medicare imgmedicare

## Documentação das tecnologias utilizadas
* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.3.0/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.3.0/maven-plugin/reference/html/#build-image)
* [Spring for RabbitMQ](https://docs.spring.io/spring-boot/docs/3.3.0/reference/htmlsingle/index.html#messaging.amqp)
* [Spring for RabbitMQ Streams](https://docs.spring.io/spring-amqp/reference/stream.html)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.3.0/reference/htmlsingle/index.html#data.sql.jpa-and-spring-data)
* [Spring Data Redis (Access+Driver)](https://docs.spring.io/spring-boot/docs/3.3.0/reference/htmlsingle/index.html#data.nosql.redis)
* [Spring Data Reactive Redis](https://docs.spring.io/spring-boot/docs/3.3.0/reference/htmlsingle/index.html#data.nosql.redis)
* [Spring Security](https://docs.spring.io/spring-boot/docs/3.3.0/reference/htmlsingle/index.html#web.security)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.3.0/reference/htmlsingle/index.html#web)

## Tutoriais de referencia

* [Messaging with RabbitMQ](https://spring.io/guides/gs/messaging-rabbitmq/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Messaging with Redis](https://spring.io/guides/gs/messaging-redis/)
* [Messaging with Redis](https://spring.io/guides/gs/messaging-redis/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

## Documentação

**REQUISIÇÕES PESSOA**

**1 – Inserir Pessoa:**
POST
http://localhost:8080/Pessoa/InserirPessoa
{
“pessoa”: {
“nome”: “teste1”,
“tipo": 1,
“identificacao”: “00000000000”
}
“documento”:{
“urldocumento”: “www.teste.com/imagem.jpg”
“descrição”: “teste”
	}
}
Erros esperados:
- Erro ao inserir registro!


**2 – Alterar Pessoa:**
PUT
http://localhost:8080/Pessoa/AlterarPessoa
 {
“pessoa”: {
“nome”: “teste1”,
“tipo": 1,
“identificacao”: “00000000000”
}
“documento”:{
“urldocumento”: “www.teste.com/imagem.jpg”
“descrição”: “teste”
	}
}
Erros esperados:
- Erro ao alterar registro!


**3 – Listar Pessoa:**
GET
http://localhost:8080/Pessoa/ListarPessoa
{
"tipo": "Medico",
"page": 1,
"rowsPerPage": 10
}
Erros esperados:
- Erro ao recuperar registros!


**4 – Remover Pessoa:**
DELETE
http://localhost:8080/Pessoa/Remover?id={id}
Erros esperados:
- Erro ao remover registros!


**5 – Recuperar Pessoa:**
GET
http://localhost:8080/Pessoa/RecuperarPessoa?id={id}
Erros esperados: 
- Erro ao recuperar registro!


**6- RecuperarTodos Pessoa:**
GET
http://localhost:8080/Pessoa/RecuperarTodos
 Erros esperados:
- Erro ao recuperar registros!


**REQUISIÇÃO AGENDAMENTO**

**1 – Inserir Agendamento:**
POST
http://localhost:8080/Agendamento/InserirAgendamento
“agendamento”: {
“tipo”: “Retorno”
“descricao”: “teste4”
“horaInicio”: “18-10-2024T08:00:00”
“horaFim”: “2024-10-16T08:45:00” 
“dadosConsulta”: “teste1”
"documento": {
        "urlDocumento": "www.teste.com/imagem.jpg",
        "descricao": "teste"
    }
Erros esperados:
- Erro ao inserir resistro!
- Este horário já está agendado, por favor escolha outro”
- Nenhum horário disponível para o agendamento!


**2 – Alterar Agendamento:**
PUT
http://localhost:8080/Agendamento/Alterar
“agendamento”: {
“tipo”: “Retorno”,
“descricao”: “teste4”,
“horaInicio”: “18-10-2024T08:00:00”
“horaFim”: “2024-10-16T08:45:00” 
 “dadosConsulta”: “teste1”,
"documento": {
        "urlDocumento": "www.teste.com/imagem.jpg",
        "descricao": "teste"
    }
Erros esperados:
- Erro ao alterar resgistro!
- Este horário já está agendado, por favor escolha outro!
- Nenhum horário disponível para o agendamento!


**3 – Listar Agendamento:**
GET
http://localhost:8080/Agendamento/ListarAgendamento
{
“tipo”: “Consulta”
"page": 1,
"rowsPerPage": 10
}
Erros esperados:
- Erro ao recuperar registros!

**4 – Remover Agendamento:**
DELETE
http://localhost:8080/Agendamento/ Remover?id={id}
Erros esperados:
- Erro ao remover registro!


**5 - Recuperar Agendamento:**
GET
http://localhost:8080/Agendamento/Recuperar?id={id}
Erros esperados: 
- Erro ao recuperar registro!


**6 – Folgar Agendamento:**
POST 
http://localhost:8080/Agendamento/Folgar?dia={data}
Erros esperados:	
- Erro ao criar folga!
- Este horário já está agendado, por favor escolha outro”
- Nenhum horário disponível para o agendamento!


**7 – RecuperarDia Agendamento:**
GET
http://localhost:8080/Agendamento/RecuperarDia
{
“tipo”: “Folga”,
“horaInicio”: “18-10-2024T08:00:00”,
“horaFim”: “2024-10-16T08:45:00” ,
“diaSemana”: “Quinta”
}
Erros esperados:
- Erro ao recuperar agendamentos do dia! 


**8 - RecuperarTodos Agendamento:**
GET
http://localhost:8080/Agendamento/RecuperarTodos
Erros esperados:
- Erro ao recuperar registros!


**REQUISIÇÃO GRADEHORARIO**

**1 – Inserir GradeHorario**
POST
http://localhost:8080/GradeHorario/Inserir
{
“inicioExpediente”: “18-10-2024T08:00:00”
“fimExpediente”: “2024-10-16T08:45:00”
“diaSemana”: “Quinta
 “Descrição”: “teste”
}
Erros esperados:
- Erro ao inserir registro!


**2 – Alterar GradeHorario:**
PUT
http://localhost:8080/GradeHorario/Alterar
{
“inicioExpediente”: “18-10-2024T08:00:00”
“fimExpediente”: “2024-10-16T08:45:00”
“diaSemana”: “Quinta
 “Descrição”: “teste”
}
Erros esperados:
- Erro ao alterar registro!


**3 – Listar GradeHorario:**
GET
http://localhost:8080/GradeHorario/ListarGradeHorario
{	
"page": 1,
"rowsPerPage": 10
}
Erros esperados:
- Erro ao recuperar registros!


**4 – Remover GradeHorario:**
DELETE
http://localhost:8080/GardeHorario/Remover?id={id}
Erros esperados:	
- Erro ao remover registro!


**5 – InserirSemanal GradeHorario:**
POST
http://localhost:8080/GradeHorario/InserirSemanal
{
“quinta”:{
	“inicioExpediente”: “18-10-2024T08:00:00”
“fimExpediente”: “2024-10-16T08:45:00”
“diaSemana”: “Quinta
 “Descrição”: “teste”
}
Erros esperados:
- Erro ao recuperar registros!


**7 – Recuperar GradeHorario:**
GET
http://localhost:8080/GradeHorario/Recuperar?id={id}
Erros esperados: 
- Erro ao recuperar registro!


**8- RecuperarTodos GradeHorario:**
GET
http://localhost:8080/GradeHorario/RecuperarTodos

Erros esperados:
- Erro ao recuperar registros!

