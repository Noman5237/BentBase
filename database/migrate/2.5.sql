create table order_info
(
    order_id    number(3) primary key,
    gig_id      number(3),
    seller_email   varchar2(255),
    price       number(9,3),
    tips        number (5,3),
    date_of_order date,
    date_of_delivery date,
    constraint fk_order_info_gig_id foreign key (gig_id) references gig (id),
    constraint fk_order_info_seller_email foreign key (seller_email) references seller (user_email)
);
