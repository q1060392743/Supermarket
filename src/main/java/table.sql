create table custom(
  cu_id varchar2(32) primary key,
  cu_username varchar2(32) not null unique,
  cu_password varchar2(32) not null,
  cu_score number(32) not null,
  cu_rank number(32) not null
);

create table product(
  pr_id varchar2(32) primary key,
  pr_name varchar2(32) not null unique,
  pr_price number(10,2) not null
);