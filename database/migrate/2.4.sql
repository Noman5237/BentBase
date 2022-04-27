create table project_status
(
    id          number(3) primary key,
    name        varchar2(50) unique,
    description varchar2(512)
)

create table application_status
(
    id          number(3) primary key,
    name        varchar2(50) unique,
    description varchar2(512)
)

alter table application
    modify status number;

alter table project
    modify status number;

alter table application
    add constraint fk_application_status foreign key (status) references application_status (id);

alter table project
    add constraint fk_project_status foreign key (status) references project_status (id);

