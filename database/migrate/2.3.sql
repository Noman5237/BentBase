alter table experience
    rename column jobTitle to job_title;
alter table experience
    rename column startTime to start_time;
alter table experience
    rename column endTime to end_time;

alter table education
    rename column startYear to start_year;
alter table education
    rename column endYear to end_year;
