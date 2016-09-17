drop table T_PHONENUMBER if exists;

create table T_PHONENUMBER (ID bigint identity primary key, NUMBER varchar(9),
                        NAME varchar(50) not null, unique(NUMBER));
                        
