Create table Review(
    order_id number(3),
    gig_review varchar2(1000),
    seller_review varchar2(1000),
    gig_rating INT,
    seller_rating INT,
    constraint fk_review_order_id foreign key (order_id) references order_info (id)
);

create or replace function totalRating(input_email varchar2)
    return number is
    total        number(5) := 0;
    n            number(5) := 0;
    rating       review.seller_rating%type;
    order_no     order_info.id%type;
    seller_email order_info.seller_email%type;
    cursor c_sellers is
        (select id, seller_email
         from order_info
         where input_email = order_info.seller_email);
begin
    open c_sellers;
    loop
        fetch c_sellers into order_no, seller_email;
        exit when c_sellers%notfound;
        select seller_rating into rating from review where order_no = order_id;
        total := total + rating;
        n := n + 1;
    end loop;
    close c_sellers;
    rating := total / n;
    return rating;
end;
