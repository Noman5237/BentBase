create table order_status
(
    id          number(3) primary key,
    name        varchar2(50) unique,
    description varchar2(512)
);

alter table order_info
    add order_status number;

alter table order_info
    add constraint fk_order_info_order_status foreign key (order_status) references order_status (id);

alter table order_info
    drop constraint fk_order_info_seller_email;

alter table order_info
    drop column seller_email;

drop function totalEarning;
drop function totalEarningLastMonth;
drop function totalGigRating;
drop function totalRating;

create sequence application_status_id_sequence;
create sequence order_status_id_sequence;
create sequence order_id_sequence;


