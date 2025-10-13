create table  IF NOT EXISTS  dividendpay (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    stock text not null,
    ex_dividend_date date not null, 
    pay_date date not null,
    amount float not null,
    primary key(id) 
);

insert into DIVIDENDPAY (stock, amount, ex_dividend_date, pay_date) 
    values ('AAPL', '0.87' , '2023-09-26', '2025-09-27');
    
insert into DIVIDENDPAY (stock, amount, ex_dividend_date, pay_date) 
    values ('RDS', '1.3' , '2023-09-23', '2025-09-24');
