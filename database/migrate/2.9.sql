create sequence application_id_sequence;

delete
from gig
where seller_email IS NULL;

alter table gig
    modify seller_email not null;
