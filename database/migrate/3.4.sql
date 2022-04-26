alter table order_info
    rename column order_status to status;

create sequence project_status_id_sequence;

create sequence project_id_sequence;