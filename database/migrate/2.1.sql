create table tag
(
    id          integer primary key,
    name        varchar2(50)  not null,
    description varchar2(512) not null
);

create table project
(
    id          integer primary key,
    title       varchar2(255) not null,
    description clob,
    deadline    integer,
    budget      integer,
    post_time   date,
    status      varchar2(255),
    buyer_email varchar2(255) not null,
    constraint fk_projects_buyer_email foreign key (buyer_email) references buyer (user_email)
);

create table gig
(
    id           integer primary key,
    seller_email varchar2(255),
    constraint fk_gig_seller_id foreign key (seller_email) references seller (user_email)
);

create table experience
(
    id          integer primary key,
    jobTitle    varchar2(255),
    workplace   varchar2(255),
    location    varchar2(255),
    startTime   date,
    endTime     date,
    description clob,
    gig_id      integer,
    constraint fk_experience_gig_id foreign key (gig_id) references gig (id)
);

create table education
(
    id          integer primary key,
    institution varchar2(255),
    degree      varchar2(255),
    major       varchar2(255),
    startYear   date,
    endYear     date,
    gig_id      integer,
    constraint fk_education_gig_id foreign key (gig_id) references gig (id)
);

create table gig_tag
(
    gig_id integer,
    tag_id integer,
    constraint fk_gig_tag_gig_id foreign key (gig_id) references gig (id),
    constraint fk_gig_tag_tag_id foreign key (tag_id) references tag (id)
);

create table application
(
    id           integer primary key,
    cover_letter clob,
    status       varchar2(255),
    gig_id       integer,
    seller_email varchar2(255),
    project_id   integer,
    constraint fk_application_gig_id foreign key (gig_id) references gig (id),
    constraint fk_application_seller_email foreign key (seller_email) references seller (user_email),
    constraint fk_application_project_id foreign key (project_id) references project (id)
);
