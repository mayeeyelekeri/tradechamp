/* table account */
insert into account (id, account_id, account_name) 
  values ('1', '1111', 'MM'); 

insert into account (id, account_id, account_name) 
  values ('2', '1112', 'BB'); 

insert into account (id, account_id, account_name) 
  values ('6', '3333', 'MM'); 
    
/* table "Stock" */
insert into stock (id, stock_symbol, current_stock_price, stock_name, stock_type, dividend_yield, dividend_frequency)
  values ('1', 'AAPL', '185.6', 'AAPL', 'Regular', '0.1', 'Quarterly'); 

insert into stock (id, stock_symbol, current_stock_price, stock_name, stock_type, dividend_yield, dividend_frequency) 
  values ('2', 'TSYY', '8', 'Tesla', 'DividendETF', '90', 'Weekly'); 

   

/* table stockportfolio */ 
/* insert into stockportfolio (id, account, stock, stock_quantity, average_stock_price, original_investment, total_dividend_amount)
  values (1, 1, 1, '50', '190', '9000', '0'); 
  
insert into stockportfolio (id, account, stock, stock_quantity, average_stock_price, original_investment, total_dividend_amount)
  values (2, 1, 2, '80', '7.0', '1000', '0'); 
*/
insert into stockportfolio (id, account, stock, stock_quantity, average_stock_price, original_investment, total_dividend_amount)
  values (3, 2, 2, '500', '8.04', '4000', '450');
    
insert into stockportfolio (id, account, stock, stock_quantity, average_stock_price, original_investment, total_dividend_amount)
  values (6, 6, 2, '100', '8', '800', '0');

         

/*  table "dividendannouncement" */   
/*insert into dividendannouncement (id, stock, declared_amount, declared_date, ex_dividend_date, pay_date, dividend_frequency) 
    values (1, 2, '0.0908' , '2025-10-14', '2025-10-15', '2025-10-16', 'Weekly'); 

insert into dividendannouncement (id, stock, declared_amount, declared_date, ex_dividend_date, pay_date, dividend_frequency) 
    values (2, 1, '0.09' , '2025-10-14', '2025-10-15', '2025-10-16', 'Quarterly'); 
 /*  For Postman Post 
  { 
    "stock": "TSYY", 
    "declaredAmount": 0.22, 
    "declaredDate": "2025-10-29", 
    "exDividendDate": "2025-10-30",
    "payDate": "2025-10-31",
    "dividendFrequency": "Weekly"
  }  
  */           
 /*table "dividendpayout" 
insert into dividendpayout (id, account, stock, payout_amount, current_stock_quantity, current_stock_price, current_yield)
   values (1, 1, 2, '50.6', '400', '8.7', '89.8'); */ 
    
   
insert into stocktradeorder (id, account, stock, quantity, price, order_type)
  values (6, 6, 2, '100', '8', 'BUY');
