Create table Review(
    order_id number(3),
    gig_review varchar2(1000),
    seller_review varchar2(1000),
    gig_rating INT,
    seller_rating INT,
    constraint fk_review_order_id foreign key (order_id) references order_info (order_id)
);

create or replace FUNCTION totalRating (input_email varchar2 )
RETURN number IS 
   total number(5) := 0;
   n number(5) := 0;
   rating Review.seller_rating%type;
   order_no order_info.order_id%type;
   seller_email order_info.seller_email%type;
   CURSOR c_sellers IS 
   (SELECT order_id, seller_email FROM order_info where input_email =  order_info.seller_email); 
BEGIN 
   OPEN c_sellers; 
   LOOP 
   FETCH c_sellers into order_no, seller_email; 
      EXIT WHEN c_sellers%notfound; 
      SELECT seller_rating into rating from REVIEW where order_no=order_id;
      total := total + rating;
      n := n + 1;
   END LOOP; 
   CLOSE c_sellers;
   rating := total / n;
   RETURN rating; 
END; 
