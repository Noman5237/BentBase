create or replace package p_gig as
    function get_total_earning(inp_gig_id in number, start_date in date, end_date in date) return number;
end;

create or replace package body p_gig as
    function get_total_earning(inp_gig_id in number, start_date in date, end_date in date) return number
        is
        total_earning  number := 0;
        order_complete number;
        cursor orders is
            select price, tips
            from order_info
            where gig_id = inp_gig_id
              and date_of_delivery between start_date and end_date
              and status = order_complete;
    begin
        select id into order_complete from order_status where name = 'COMPLETE';
        for order_ in orders
            loop
                total_earning := total_earning + order_.price + order_.tips;
            end loop;

        return total_earning;
    end;
end;

create or replace package p_seller as
    function get_total_earning(inp_seller_email varchar2, start_date in date, end_date in date) return number;
end;

create or replace package body p_seller as
    function get_total_earning(inp_seller_email varchar2, start_date in date, end_date in date) return number
        is
        total_earning number := 0;
    begin
        select sum(p_gig.get_total_earning(gig.id, start_date, end_date))
        into total_earning
        from gig
        where gig.seller_email = inp_seller_email;

        return total_earning;
    end;
end;
