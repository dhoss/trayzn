create database trayzn;

create user trayzn with password 'trayzn';

grant all privileges on database "trayzn" to trayzn;

grant all on schema public to trayzn;