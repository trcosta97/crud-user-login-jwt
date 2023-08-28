desc tb_address;
desc tb_user;

alter table tb_user
drop column id_address;

select * from  tb_user;
select * from  tb_address;

delete  from  tb_user where id=26;
delete  from  tb_address where id=41;

CREATE SEQUENCE address_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE user_seq START WITH 1 INCREMENT BY 1;
