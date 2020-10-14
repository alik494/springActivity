 create sequence hibernate_sequence start 1 increment 1;

 create table activity (id int8 not null,
         active_act boolean not null,
         archive_act boolean not null,
         tag varchar(255), text varchar(2048),
         time int4 check (time>=1), primary key (id));

 create table activity_users (activity_id int8 not null, users_id int8 not null);

 create table user_role (user_id int8 not null, roles varchar(255));

 create table usr (id int8 not null,
                   active boolean not null,
                   email varchar(255),
                   password varchar(255) not null,
                   username varchar(255) not null,
                   primary key (id));
 alter table if exists activity_users add constraint activity_user_fk foreign key (users_id) references usr;
 alter table if exists activity_users add constraint activity_activity_fk foreign key (activity_id) references activity;
 alter table if exists user_role add constraint user_role_fk foreign key (user_id) references usr;