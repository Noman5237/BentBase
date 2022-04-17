create sequence tag_id_sequence;

alter table gig
    add title varchar2(127) not null;
alter table gig
    add about varchar2(512) not null;

create sequence gig_id_sequence;

alter table education
    modify institution not null;
alter table education
    modify degree not null;
alter table education
    modify major not null;
alter table education
    modify start_year not null;
alter table education
    modify end_year not null;

create sequence education_id_sequence;
