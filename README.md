Topics
======
Projeto Java Web de exemplo utilizando as bibliotecas e frameworks abaixo. É um aplicação para postar tópicos de perguntas com possibilidade de respostas.

Arquitetura
===========

+ Spring MVC 3 com REST e URL's amigáveis
+ ORM JPA/Hibernate
+ Migrations (com FlyWay)
+ Bean Validation
+ JQuery
+ Template com SiteMesh
+ Paginacao real (em banco)
+ Autenticação em base com Spring Security

Configurações
=============

1. Deve ser desenvolvido com encoding UTF-8.
2. Renomeie o arquivo "src\main\resources\topics_db_sample.properties" para "topics_db.properties" (sem o sample). Ele é um arquivo de exemplo de configurações de banco de dados relacional. Não versione o arquivo renomeado, e nunca altere/comite diretamente o arquivo sample.
	
Migrations
==========

Para saber mais sobre as configuraçõs e a motivação de utilizar Migrations, veja este post: http://blog.jopss.com/post/62717268881/automatizando-scripts-de-banco-com-java

Status
======

Finalizado. Em manutenção.
