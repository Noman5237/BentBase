alter table seller
    drop constraint fk_seller_user_email;

alter table seller
    add constraint fk_seller_user_email foreign key (user_email) references "user" (email) on delete cascade;

alter table admin
    drop constraint fk_admin_user_email;

alter table admin
    add constraint fk_admin_user_email foreign key (user_email) references "user" (email) on delete cascade;

alter table buyer
    drop constraint fk_buyer_userEmail;

alter table buyer
    add constraint fk_buyer_user_email foreign key (user_email) references "user" (email) on delete cascade;

alter table gig
    drop constraint fk_gig_seller_id;

alter table gig
    add constraint fk_gig_seller_id foreign key (seller_email) references seller (user_email) on delete cascade;

alter table experience
    drop constraint fk_experience_gig_id;

alter table experience
    add constraint fk_experience_gig_id foreign key (gig_id) references gig (id) on delete cascade;

alter table education
    drop constraint fk_education_gig_id;

alter table education
    add constraint fk_education_gig_id foreign key (gig_id) references gig (id) on delete cascade;

alter table gig_tag
    add constraint pk_gig_tag primary key (gig_id, tag_id);

alter table gig_tag
    drop constraint fk_gig_tag_gig_id;

alter table gig_tag
    add constraint fk_gig_tag_gig_id foreign key (gig_id) references gig (id) on delete cascade;

alter table gig_tag
    drop constraint fk_gig_tag_tag_id;

alter table gig_tag
    add constraint fk_gig_tag_tag_id foreign key (tag_id) references tag (id) on delete cascade;

alter table application
    drop constraint fk_application_gig_id;

alter table application
    add constraint fk_application_gig_id foreign key (gig_id) references gig (id) on delete cascade;

alter table application
    drop constraint fk_application_project_id;

alter table application
    add constraint fk_application_project_id foreign key (project_id) references project (id) on delete cascade;

alter table order_info
    modify order_id number;

alter table order_info
    rename column order_id to id;

alter table order_info
    add project_id number;

alter table order_info
    add constraint fk_order_info_project_id foreign key (project_id) references project (id);

create sequence experience_id_sequence;
