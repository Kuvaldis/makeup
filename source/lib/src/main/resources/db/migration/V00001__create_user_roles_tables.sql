create sequence if not exists user_seq start with 1 increment by 1;
create sequence if not exists user_auth_seq start with 1 increment by 1;
create sequence if not exists profile_seq start with 1 increment by 1;

create table if not exists users (
  id int default user_seq.nextval primary key,
  roles array
);

create table if not exists auth_way (
  name varchar(255) primary key
);

create table if not exists user_auth (
  id int default user_auth_seq.nextval primary key,
  user_id int not null,
  login varchar(255) not null,
  password varchar(255),
  auth_way varchar(255) not null,
  foreign key (user_id) references users(id),
  foreign key (auth_way) references auth_way(name)
);

create table if not exists profile (
  id int default profile_seq.nextval primary key,
  user_id int not null,
  username varchar(255) not null,
  foreign key (user_id) references users(id),
  unique (username)
);