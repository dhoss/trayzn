drop database trayzn;
create database trayzn;

drop owned by trayzn;
drop user trayzn;
create user trayzn with password 'trayzn';

grant all privileges on database trayzn to trayzn;

grant all on schema public to trayzn;

grant usage, create on schema public to trayzn;

alter database trayzn owner to trayzn;