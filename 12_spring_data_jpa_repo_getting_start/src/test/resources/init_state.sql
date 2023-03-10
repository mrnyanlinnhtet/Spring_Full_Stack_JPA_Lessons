 drop table if exists states cascade;
 
 create table states (
       id bigint generated by default as identity (start with 1),
        capital varchar(255) not null,
        name varchar(255) not null,
        porpulation integer not null,
        region varchar(255) not null,
        type varchar(255) not null,
        primary key (id)
    );
    
    alter table states 
       add constraint UK_nau09mwrvhjj0n0a6gfo5xmp3 unique (name);