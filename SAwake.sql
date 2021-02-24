create database SPAwake;

use SPAwake;

drop table ADMINISTRACION;
select * from ADMINISTRACION;
create table ADMINISTRACION(
id_admi int primary key auto_increment,
nom_admi varchar (50) not null,
ape_admi varchar (50) not null,
email_admi varchar (50) not null,
log_admi varchar (50) not null,
pass_admi varchar (50) not null
);



drop table ACUDIENTES;
select * from ACUDIENTES;
create table ACUDIENTES(
id_acu int primary key auto_increment,
nom_acu varchar (50) not null,
ape_acu varchar (50) not null,
doc_acu numeric not null,
sex_acu varchar (1) null,
tel_acu numeric not null,
email_acu varchar (50) not null,
log_acu varchar (50) not null,
pass_acu varchar (50) not null
);



drop table DOCENTES;
select * from DOCENTES;
create table DOCENTES(
id_doc int primary key auto_increment,
nom_doc varchar (50) not null,
ape_doc varchar (50) not null,
doc_doc numeric not null,
sex_doc varchar (1) not null,
tel_doc numeric not null,
email_doc varchar (50) not null,
log_doc varchar (50) not null,
pass_doc varchar (50) not null
);



drop table GRADOS;
select * from GRADOS;
create table GRADOS(
id_grados int primary key auto_increment,
grado_grados varchar (20) not null
);



drop table GRUPO;
select * from GRUPO;
create table GRUPO(
id_grupo int primary key auto_increment,
num_grupo varchar (10) not null,
grado_id int,
foreign key (grado_id) references GRADOS (id_grados)
);



drop table SALONES;
select * from SALONES;
create table SALONES(
id_salon int primary key auto_increment,
nom_salon varchar (20) not null,
grupo_id int,
foreign key (grupo_id) references GRUPO (id_grupo)
);



drop table ESTUDIANTES;
select * from ESTUDIANTES;
create table ESTUDIANTES(
id_est int primary key auto_increment,
nom_est varchar (50) not null,
ape_est varchar (50) not null,
doc_est numeric (15) not null,
edad_est numeric (2) not null,
sex_est varchar(1) not null,
tel_est numeric null,
grupo_id int,
foreign key (grupo_id) references GRUPO (id_grupo)
);



drop table ACU_EST;
select * from ACU_EST;
create table ACU_EST(
id_acuest int primary key auto_increment,
acu_id int,
est_id int,
foreign key (acu_id) references ACUDIENTES (id_acu),
foreign key (est_id) references ESTUDIANTES (id_est)
);



drop table REGISTRO;
select * from REGISTRO;
create table REGISTRO(
id_reg int primary key auto_increment,
fEntrada_reg datetime null,
fSalida_reg datetime null,
est_id int,
foreign key (est_id) references ESTUDIANTES (id_est)
);



drop table DIAS;
select * from DIAS;
create table DIAS(
id_dias int primary key auto_increment,
nom_dias varchar (10) not null
);


drop table ASISTENCIA;
select * from ASISTENCIA;
create table ASISTENCIA(
id_asist int primary key auto_increment,
asist_asist varchar (1) not null,
fecha_asist date not null,
doc_id int,
dias_id int,
est_id int,
salon_id int,
foreign key (doc_id) references DOCENTES (id_doc),
foreign key (dias_id) references DIAS (id_dias),
foreign key (est_id) references ESTUDIANTES (id_est),
foreign key (salon_id) references SALONES (id_salon)
);



drop table OBS;
select * from OBS;
create table OBS(
id_obs int primary key auto_increment,
detalle_obs varchar (255) not null,
fecha_obs datetime not null,
fechaCita_obs datetime null,
doc_id int,
est_id int,
acu_id int,
foreign key (doc_id) references  DOCENTES (id_doc),
foreign key (est_id) references ESTUDIANTES (id_est),
foreign key (acu_id) references ACUDIENTES (id_acu)
);



insert into ADMINISTRACION (nom_admi,ape_admi,email_admi,log_admi,pass_admi) values ('Edward Alejandro','Garcia Gonzalez','egarciag97@hotmail.com','ADMIN1','admin1');
insert into ADMINISTRACION (nom_admi,ape_admi,email_admi,log_admi,pass_admi) values ('Jhonatan Yesid','Jaramillo Dias','jhonatanjaramillo8@gmail.com ','ADMIN2','admin2');

insert into ACUDIENTES (nom_acu,ape_acu,doc_acu,sex_acu,tel_acu,email_acu,log_acu,pass_acu) values ('Juan Carlos','Garcia Castillo',79644829,'M',3024605474,'jcgar07@hotmail.com','jcgar07','0000');
insert into ACUDIENTES (nom_acu,ape_acu,doc_acu,sex_acu,tel_acu,email_acu,log_acu,pass_acu) values ('Deisy Lizceth','Gonzalez Rojas',65564321,'F',3005352744,'liz.gonzalez17@hotmail.com','liz17','1111');
insert into ACUDIENTES (nom_acu,ape_acu,doc_acu,sex_acu,tel_acu,email_acu,log_acu,pass_acu) values ('Miguel Alfonso','Fontecha Londoño',10242936,'M',305753607,'miguel.f@hotmail.com','miguelF','2222');

insert into DOCENTES (nom_doc,ape_doc,doc_doc,sex_doc,tel_doc,email_doc,log_doc,pass_doc) values ('Dilia Ines','Molina',74635209,'F',3114715890,'dinesmolina@mail.unicundi.edu.co','dinesmolina','docente1');
insert into DOCENTES (nom_doc,ape_doc,doc_doc,sex_doc,tel_doc,email_doc,log_doc,pass_doc) values ('Manuel Hernan','Alvarado',53426790,'M',0000000000,'mhas2013@gmail.com','mhas2013','docente2');

insert into GRADOS (grado_grados) values ('Sexto');
insert into GRADOS (grado_grados) values ('Septimo');
insert into GRADOS (grado_grados) values ('Octavo');
insert into GRADOS (grado_grados) values ('Noveno');
insert into GRADOS (grado_grados) values ('Decimo');
insert into GRADOS (grado_grados) values ('Once');

insert into GRUPO (num_grupo,grado_id) values ('601',1);
insert into GRUPO (num_grupo,grado_id) values ('602',1);
insert into GRUPO (num_grupo,grado_id) values ('701',2);
insert into GRUPO (num_grupo,grado_id) values ('702',2);
insert into GRUPO (num_grupo,grado_id) values ('801',3);
insert into GRUPO (num_grupo,grado_id) values ('802',3);
insert into GRUPO (num_grupo,grado_id) values ('901',4);
insert into GRUPO (num_grupo,grado_id) values ('902',4);
insert into GRUPO (num_grupo,grado_id) values ('101',5);
insert into GRUPO (num_grupo,grado_id) values ('102',5);
insert into GRUPO (num_grupo,grado_id) values ('111',6);
insert into GRUPO (num_grupo,grado_id) values ('112',6);

insert into SALONES (nom_salon,grupo_id) values ('6A',1);
insert into SALONES (nom_salon,grupo_id) values ('6B',2);
insert into SALONES (nom_salon,grupo_id) values ('7A',3);
insert into SALONES (nom_salon,grupo_id) values ('7B',4);
insert into SALONES (nom_salon,grupo_id) values ('8A',5);
insert into SALONES (nom_salon,grupo_id) values ('8B',6);
insert into SALONES (nom_salon,grupo_id) values ('9A',7);
insert into SALONES (nom_salon,grupo_id) values ('9B',8);
insert into SALONES (nom_salon,grupo_id) values ('10A',9);
insert into SALONES (nom_salon,grupo_id) values ('10B',10);
insert into SALONES (nom_salon,grupo_id) values ('11A',11);
insert into SALONES (nom_salon,grupo_id) values ('11B',12);

insert into ESTUDIANTES (nom_est,ape_est,doc_est,edad_est,sex_est,tel_est,grupo_id) values ('Edward','Gonzalez',97011208065,11,'M',3012272798,1);
insert into ESTUDIANTES (nom_est,ape_est,doc_est,edad_est,sex_est,tel_est,grupo_id) values ('Yesid','Dias',11111111111,11,'M',3207185504,1);
insert into ESTUDIANTES (nom_est,ape_est,doc_est,edad_est,sex_est,tel_est,grupo_id) values ('Guian','Gutierrez',22222222222,11,'M',3185657601,1);
insert into ESTUDIANTES (nom_est,ape_est,doc_est,edad_est,sex_est,tel_est,grupo_id) values ('Alejandro','Romero',33333333333,11,'M',3223651302,1);
insert into ESTUDIANTES (nom_est,ape_est,doc_est,edad_est,sex_est,tel_est,grupo_id) values ('Julian','Cortez',444444444444,11,'M',3104790237,1);
insert into ESTUDIANTES (nom_est,ape_est,doc_est,edad_est,sex_est,tel_est,grupo_id) values ('Jani','Garcia',55555555555,11,'F',3045290786,1);
insert into ESTUDIANTES (nom_est,ape_est,doc_est,edad_est,sex_est,tel_est,grupo_id) values ('Danna','Vargas',66666666666,11,'F',3006610338,1);
insert into ESTUDIANTES (nom_est,ape_est,doc_est,edad_est,sex_est,tel_est,grupo_id) values ('Gineth','Andrade',77777777777,11,'F',3219791331,1);
insert into ESTUDIANTES (nom_est,ape_est,doc_est,edad_est,sex_est,tel_est,grupo_id) values ('Lucia','Otalora',88888888888,11,'F',3202400726,1);
insert into ESTUDIANTES (nom_est,ape_est,doc_est,edad_est,sex_est,tel_est,grupo_id) values ('Edwin','Escobar',99999999999,11,'M',3166146880,1);

insert into ACU_EST (acu_id,est_id) values (1,18);
insert into ACU_EST (acu_id,est_id) values (1,20);
insert into ACU_EST (acu_id,est_id) values (3,16);
insert into ACU_EST (acu_id,est_id) values (3,11);
insert into ACU_EST (acu_id,est_id) values (3,17);
insert into ACU_EST (acu_id,est_id) values (4,18);
insert into ACU_EST (acu_id,est_id) values (4,19);

insert into REGISTRO (fEntrada_reg,fSalida_reg,est_id) values ('2017-10-2 08:00:00','2017-10-2 13:00:00',11);

insert into DIAS (nom_dias) values('lunes');
insert into DIAS (nom_dias) values('Martes');
insert into DIAS (nom_dias) values('Miercoles');
insert into DIAS (nom_dias) values('Jueves');
insert into DIAS (nom_dias) values('Viernes');
insert into DIAS (nom_dias) values('Sabado');

insert into ASISTENCIA (asist_asist,fecha_asist,doc_id,dias_id,est_id,salon_id) values ('t','2017-10-31',1,1,11,11);