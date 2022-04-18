create or replace FUNCTION totalGigRating (input_id INT )
RETURN number IS 
   total number(5) := 0;
   n number(5) := 0;
   rating Review.gig_rating%type;
   order_no order_info.order_id%type;
   gig_id gig_tag.gig_id%type;
   CURSOR c_gigs IS 
   (SELECT order_id, gig_id FROM order_info where input_id =  order_info.gig_id); 
BEGIN 
   OPEN c_gigs; 
   LOOP 
   FETCH c_gigs into order_no, gig_id; 
      EXIT WHEN c_gigs%notfound; 
      SELECT gig_rating into rating from REVIEW where order_no=order_id;
      total := total + rating;
      n := n + 1;
   END LOOP; 
   CLOSE c_gigs;
   rating := total / n;
   RETURN rating; 
END; 
