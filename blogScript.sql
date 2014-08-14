-- -------------------------------------------------------------- --
-- -----Script para la creacion de la base de datos blog--------- --
-- ----------------Y su usuario db_admin------------------------- --
create database blog;

use blog;

-- create user 'db_Admin'@'localhost' identified by 'db_admin';

grant all on blog.* to 'db_Admin'@'localhost' identified by 'db_admin';

-- Tabla de usuarios --
create table users 
 (
	user_id int(6) auto_increment,
	user_name varchar(25) not null comment "nombre unico para el usuario admin",
	password varchar(40) not null  comment "campo de clave que es encriptada en la aplicacion.",
	status varchar(1) default 'A'  comment "Campo que representa el estado del usuario A=Activo, I=Inactivo",
	type varchar(15)  default 'ADMIN' comment "Tipo de usuario, para una proxima version se tiene contemplado moderadores y miembros",
	init_date datetime default current_timestamp comment "fecha en que fue creado el usuario",
	unique(user_name),
	primary key(user_id),
	check(status in ('A','I'))
) comment="Contiene los usuarios administradores del sistema.";

-- Tabla de Categorias --
create table categories 
(
	category_id int(6) auto_increment,
	name varchar(30) not null, 
	description varchar(200) not null,
	icon varchar(30) comment "nombre de la foto con su extencion ejemplo icon.png",
	primary key(category_id),
	unique(name)
) comment="contiene las categorias y la informacion concerniente a las mismas.";

-- Tabla de Post con los aportes a la pagina --
create table post (
	post_id int(6) auto_increment,
	category_id int(6) comment "Llave foranea que apunta hacia la tbl de categorias.",
	title varchar(30) not null comment "Titulo del post.",
	content longtext comment "Contenido de la publicacion.",
	init_date datetime default current_timestamp comment "Fecha en la se creo la publicacion",
	user_id int(6) comment "Llave foranea que apunta a la tbl de users para identificar que usuario creo la publicacion",
	primary key (post_id),
	foreign key (category_id) references categories (category_id),
	foreign key (user_id) references users (user_id)
) comment="tabla que contiene las publicaciones realizadas en la pagina.";

-- Tabla que contiene los documentos adjuntos a las publicaciones --
create table attached 
(
	attached_id int(6) auto_increment,
	post_id int(6) comment "Llave foranea que apunto a la tabla de post para referenciar los adjuntos de cada post",
	file_name varchar(30) not null comment "Nombre del archivo adjunto, el cual sera asignado por la aplicacion formato dd-MM-yyyy HH-MM",
	userName varchar(25),
	primary key(attached_id),
	foreign key(post_id) references post(post_id)
) comment="Tabla que contiene los diferentes documentos adjuntos de una publicacion.";

-- Tabla de subcristores --
create table subscribers 
(
	subcriber_id int(6) primary key auto_increment,
	email varchar(40) not null,
	name varchar(25) default 'ANONYMOUS',
	unique(email)
) comment="Tabla de subcristores al blog";

-- Tabla de comentarios --
create table comments 
(
	comment_id int(6) primary key auto_increment,
	name varchar(25) default 'ANONYMOUS' comment "Nombre de la persona que comente",
	email varchar(40) comment "Correo requerido para reenviar los proximos comentarios de otras personas.",
	content longtext not null comment "comentario para el post",
	post_id int(6) not null comment "Post al cual se encuentra enlazado el comentario.",
	foreign key(post_id) references post(post_id)
) comment="Tabla de comentarios a los post.";

create table subscriber_categories 
(
	id int(6) primary key auto_increment,
	subcriber_id int(6),
	category_id int(6),
	foreign key(subcriber_id) references subscribers(subcriber_id),
	foreign key(category_id) references categories(category_id),
	unique(id,subcriber_id)
) comment="tabla de relacion entre subcritores y categorias de interes.";

insert into users (user_name, password) values ('admin','admin');
insert into categories (name, description,icon) values ('Java','Lenguaje de Programacion Orientado a Objetos','java.png');
insert into categories (name, description, icon) values ('C#','Lenguaje de programacion orientado a objetos desarrollado por microsoft.','cSharp.png');
insert into categories (name, description, icon) values ('C++','Lenguaje de programacion diseñado a mediados de los años 1980 por Bjarne Stroustrup.','cPlus.png');
insert into categories (name, description, icon) values ('PHP','Es un lenguaje de programación de uso general de código del lado del servidor originalmente diseñado para el desarrollo web de contenido dinámico.','php.png');
insert into categories (name, description, icon) values ('Python','es un lenguaje de programación interpretado cuya filosofía hace hincapié en una sintaxis muy limpia y que favorezca un código legible.','python.png');

insert into post (category_id, title, content, user_id) values (1,'JSTL','<p>La tecnología <b>JavaServer Pages Standard Tag Library</b> (<b>JSTL</b>) es un componente de <a href="/wiki/Java_EE" title="Java EE">Java EE</a>. Extiende las ya conocidas <a href="/wiki/JavaServer_Pages" title="JavaServer Pages">JavaServer Pages</a> (JSP) proporcionando cuatro bibliotecas de etiquetas (Tag Libraries) con utilidades ampliamente utilizadas en el desarrollo de páginas web dinámicas.</p>
<p>Estas bibliotecas de etiquetas extienden de la especificación de JSP (la cual a su vez extiende de la especificación de Servlet). Su API nos permite además desarrollar nuestras propias bibliotecas de etiquetas.</p>',1);

insert into post (category_id, title, content, user_id) values (1,'JPA','Java Persistence API, más conocida por sus siglas JPA, es la API de persistencia desarrollada para la plataforma Java EE
Es un framework del lenguaje de programación Java que maneja datos relacionales en aplicaciones usando la Plataforma Java en sus ediciones Standard (Java SE) y Enterprise (Java EE).
La JPA fue originada a partir del trabajo del JSR 220 Expert Group. Ha sido incluida en el estándar EJB3.',1);

insert into post (category_id, title, content, user_id) values (1,'JSP','JavaServer Pages (JSP) es una tecnología que ayuda a los desarrolladores de software a crear páginas web dinámicas basadas en HTML, XML, entre otros tipos de documentos. JSP es similar a PHP, pero usa el lenguaje de programación Java.
Para desplegar y correr JavaServer Pages, se requiere un servidor web compatible con contenedores servlet como Apache Tomcat o Jetty.',1);

insert into post (category_id, title, content, user_id) values (1,'EL','El Unified Expression Language de Java es un lenguaje de programación de propósito especial utilizado principalmente en aplicaciones web en Java para incrustar expresiones en páginas web. Los redactores de especificaciones de Java y los grupos de expertos de las tecnologías de capa web de Java han trabajado en un lenguaje de expresiones unificado que fue incluido por primera vez en la especificación JSP 2.1 (JSR-245), y más adelante especificado por sí mismo en el JSR-341, que forma parte de Java EE 7.',1);

insert into post (category_id, title, content, user_id) values (1,'JDBC','Java Database Connectivity, más conocida por sus siglas JDBC1 2 , es una API que permite la ejecución de operaciones sobre bases de datos desde el lenguaje de programación Java, independientemente del sistema operativo donde se ejecute o de la base de datos a la cual se accede, utilizando el dialecto SQL del modelo de base de datos que se utilice.',1);

insert into post (category_id, title, content, user_id) values (1,'Spring Framework','The Spring Framework provides a comprehensive programming and configuration model for modern Java-based enterprise applications - on any kind of deployment platform. A key element of Spring is infrastructural support at the application level: Spring focuses on the "plumbing" of enterprise applications so that teams can focus on application-level business logic, without unnecessary ties to specific deployment environments.',1);

insert into comments (name,email,content,post_id) values ('Armando Torres','ing.armandotorres@gmail.com','Nice post!!!',1);

insert into subscribers(email,name) values ('ing.armandotorres@gmail.com','Armando Torres');
insert into subscribers(email,name) values ('torres1523@hotmail.com','Antonio Donastorg');

insert into subscriber_categories (subcriber_id, category_id) values (2,1);
insert into subscriber_categories (subcriber_id, category_id) values (2,2);
insert into subscriber_categories (subcriber_id, category_id) values (2,3);
insert into subscriber_categories (subcriber_id, category_id) values (1,1);
insert into subscriber_categories (subcriber_id, category_id) values (1,2);
insert into subscriber_categories (subcriber_id, category_id) values (1,3);