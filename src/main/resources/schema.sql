create table  IF NOT EXISTS  dividendannouncement (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    stock_symbol text not null,
    declared_amount DECIMAL(10, 4) not null,
    declared_date date not null, 
    ex_dividend_date date not null, 
    pay_date date not null,
    dividend_frequency text, 
    primary key(id) 
);

insert into dividendannouncement (stock_symbol, declared_amount, declared_date, ex_dividend_date, pay_date, dividend_frequency) 
    values ('ULTY', '0.0908' , '2025-10-14', '2025-10-15', '2025-10-16', 'Weekly');

insert into dividendannouncement (stock_symbol, declared_amount, declared_date, ex_dividend_date, pay_date, dividend_frequency) 
    values ('YMAG', '0.2009' , '2025-10-14', '2025-10-15', '2025-10-16', 'Weekly');
    
insert into dividendannouncement (stock_symbol, declared_amount, declared_date, ex_dividend_date, pay_date, dividend_frequency) 
    values ('YMAX', '0.1743' , '2025-10-14', '2025-10-15', '2025-10-16', 'Weekly');

insert into dividendannouncement (stock_symbol, declared_amount, declared_date, ex_dividend_date, pay_date, dividend_frequency) 
    values ('WPAY', '0.856935' , '2025-10-14', '2025-10-15', '2025-10-16', 'Weekly');            
        

create table IF NOT EXISTS  account (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    account_id INT not null, 
    account_name text not null,
    account_owner text not null, 
    broker text not null, 
    account_type text not null, 
    opened_date date not null,
    primary key(id) 
);

insert into ACCOUNT (account_id, account_name, account_owner, broker, account_type, opened_date) 
    values (101, '401K', 'bb', 'Webull', 'Traditional IRA', '2005-08-01'); 

insert into ACCOUNT (account_id, account_name, account_owner, broker, account_type, opened_date) 
    values (203, 'Margin', 'mm', 'Robinhood', 'Margin', '2005-08-01'); 

create table  IF NOT EXISTS  dividendpayout (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    account_id INT, 
    stock_symbol text not null,
    current_stock_quantity DECIMAL(10, 4) not null,
    current_stock_price DECIMAL(10, 4) not null, 
    current_yield date not null, 
    payout_amount DECIMAL(10, 4) not null, 
    payout_date date not null,
    dividend_frequency text, 
    primary key(id) 
);

insert into dividendpayout (account_id, stock_symbol, current_stock_quantity, current_stock_price, current_yield, payout_amount, payout_date, dividend_frequency) 
    values (1, 'AAPL', 20, 245.5, 0.17, 0.01, '2005-08-01', 'Weekly'); 
 
create table  IF NOT EXISTS  stockportfolio (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    account_id INT not null, 
    stock_symbol text not null,
    stock_name text,
    product_type text not null default 'Regular', 
    stock_quantity DECIMAL(10, 4) not null,
    current_stock_price DECIMAL(10, 4), 
    average_stock_price DECIMAL(10, 4) not null, 
    current_market_cap DECIMAL(10, 4) , 
    original_investment DECIMAL(10, 4),
    drip text, 
    purchase_date date not null,
    dividend_frequency text,
    comments text,
    total_dividend_amount DECIMAL(10, 4), 
    pl_current DECIMAL(10, 4), 
    pl_with_dividend DECIMAL(10, 4), 
    current_yield DECIMAL(10, 4),
    
    primary key(id) 
);

insert into stockportfolio (account_id, stock_symbol, stock_name, product_type, stock_quantity, current_stock_price, average_stock_price, current_market_cap, original_investment, drip, purchase_date, dividend_frequency, comments, total_dividend_amount, pl_current, pl_with_dividend, current_yield) 
    values (101, 'NVDA', 'NVDA', 'Regular', 20.0, 185.5, 185.0, 185, 185, 'Yes', '2005-08-01', 'Weekly', 'adfds', 0,0,0,0); 

insert into stockportfolio (account_id, stock_symbol, stock_name, product_type, stock_quantity, current_stock_price, average_stock_price, current_market_cap, original_investment, drip, purchase_date, dividend_frequency, comments, total_dividend_amount, pl_current, pl_with_dividend, current_yield) 
    values (101, 'ULTY', '', 'Dividend-ETF', 200, 5.41, 5, 1000, 1000, 'Yes', '2005-08-01', 'Weekly', 'adfds', 0,0,0,0); 

insert into stockportfolio (account_id, stock_symbol, stock_name, product_type, stock_quantity, current_stock_price, average_stock_price, current_market_cap, original_investment, drip, purchase_date, dividend_frequency, comments, total_dividend_amount, pl_current, pl_with_dividend, current_yield) 
    values (203, 'ULTY', '', 'Dividend-ETF', 500, 6, 6, 1000, 1000, 'Yes', '2005-08-01', 'Weekly', 'adfds', 0,0,0,0); 

    
create table  IF NOT EXISTS  stocktradeorder (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    account_id INT not null, 
    stock_symbol text not null,
    order_type text,
    quantity float not null,
    price DECIMAL(10, 4) not null,  
    execution_date date not null,
    comments text,
    primary key(id) 
);

insert into stocktradeorder (account_id, stock_symbol, order_type, quantity, price, execution_date, comments)
    values (333, 'NVDA', 'Buy', 20.0, 185.5,'2005-08-01', 'adfds'); 

insert into stocktradeorder (account_id, stock_symbol, order_type, quantity, price, execution_date, comments) 
    values (112, 'NVDA', 'Sell', 20.0, 185.5,'2005-08-01', 'adfds');     
   
insert into stocktradeorder (account_id, stock_symbol, order_type, quantity, price, execution_date, comments) 
    values (113, 'SOFI', 'Buy', 50, 30,'2025-08-01', 'enter into position');  

insert into stocktradeorder (account_id, stock_symbol, order_type, quantity, price, execution_date, comments) 
    values (113, 'SOFI', 'Buy', 100, 25,'2025-10-15', 'second transaction');   
