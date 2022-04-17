create or replace FUNCTION totalEarning (input_email varchar2 )
RETURN number IS 
   total number(2) := 0;
   price number(2) := 0;
   tips number(2) := 0;
BEGIN 
   SELECT sum(price) into price 
   FROM order_info where seller_email=input_email;
   SELECT sum(tips) into tips 
   FROM order_info where seller_email=input_email;
   total := price + tips;
   RETURN total; 
END; 


create or replace FUNCTION totalEarningLastMonth(input_email varchar2 )
RETURN number IS 
   total number(2) := 0;
   price number(2) := 0;
   tips number(2) := 0;
   CURRENT_DATE_TIME Date;
   PREV_MONTH Date;
BEGIN 
   
   SELECT SYSDATE into CURRENT_DATE_TIME
   FROM Dual;
   SELECT ADD_MONTHS(SYSDATE, -1)  into PREV_MONTH
   FROM Dual;
   SELECT sum(price) into price 
   FROM order_info where seller_email=input_email and 
   (date_of_delivery >= PREV_MONTH and date_of_delivery < CURRENT_DATE_TIME);
   SELECT sum(tips) into tips 
   FROM order_info where seller_email=input_email and 
   date_of_delivery >= PREV_MONTH and date_of_delivery < CURRENT_DATE_TIME;
   total := price + tips;
   RETURN total; 
END; 
