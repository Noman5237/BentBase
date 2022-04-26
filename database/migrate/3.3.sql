create table project_tag
(
    project_id number,
    tag_id     number,
    constraint pk_project_tag primary key (project_id, tag_id),
    constraint fk_project_tag_project_id foreign key (project_id) references project (id) on delete cascade,
    constraint fk_project_tag_tag_id foreign key (tag_id) references tag (id) on delete cascade
);
