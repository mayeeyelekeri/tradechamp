
/* "account" table */ 
CREATE TABLE IF NOT EXISTS account (
    id bigint NOT NULL AUTO_INCREMENT, 
    account_id bigint DEFAULT NULL,
    account_name varchar(255) DEFAULT NULL,
    account_owner varchar(255) DEFAULT NULL,
    account_type varchar(255) DEFAULT NULL,
    broker varchar(255) DEFAULT NULL,
    opened_date date DEFAULT NULL,
    PRIMARY KEY (`id`)); 

 ALTER TABLE account
   ADD COLUMN IF NOT EXISTS modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;
 
/* Table "stock"   */
CREATE TABLE IF NOT EXISTS  stock (
   id bigint NOT NULL AUTO_INCREMENT,
   comments varchar(255) DEFAULT NULL,
   current_stock_price double DEFAULT NULL,
   dividend_frequency varchar(255) DEFAULT NULL,
   dividend_yield double DEFAULT NULL,
   industry varchar(255) DEFAULT NULL,
   next_dividend_date date DEFAULT NULL,
   stock_description varchar(255) DEFAULT NULL,
   stock_name varchar(255) DEFAULT NULL,
   stock_symbol varchar(255) NOT NULL,
   stock_type varchar(255) DEFAULT NULL,
   PRIMARY KEY (id)
 );  

 ALTER TABLE stock
   ADD COLUMN IF NOT EXISTS modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;
     
 ALTER TABLE stock
    ADD CONSTRAINT stock_uniq_key UNIQUE (stock_symbol);
  
/* stockportfolio */ 

CREATE TABLE IF NOT EXISTS `stockportfolio` (
   `id` bigint NOT NULL AUTO_INCREMENT,
   `average_stock_price` double DEFAULT NULL,
   `comments` varchar(255) DEFAULT NULL,
   `dividend_frequency` varchar(255) DEFAULT NULL,
   `drip` varchar(255) DEFAULT NULL,
   `original_investment` double DEFAULT NULL,
   `purchase_date` date DEFAULT NULL,
   `stock_quantity` double DEFAULT NULL,
   `total_dividend_amount` double DEFAULT NULL,
   `stock` bigint NOT NULL,
   `account` bigint NOT NULL,
   PRIMARY KEY (`id`)
 ); 
    
 ALTER TABLE stockportfolio
    ADD COLUMN IF NOT EXISTS modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;


 ALTER TABLE stockportfolio
    ADD CONSTRAINT port_multi_uniq_key UNIQUE (stock, account);
   
   
 ALTER TABLE stockportfolio
        ADD CONSTRAINT FK_port_stock
        FOREIGN KEY (stock) REFERENCES stock(id);  

 ALTER TABLE stockportfolio
        ADD CONSTRAINT FK_port_account
        FOREIGN KEY (account) REFERENCES account(id); 
        
       
/* Table "dividendannouncement"  */ 

CREATE TABLE IF NOT EXISTS `dividendannouncement` (
   `id` bigint NOT NULL AUTO_INCREMENT,
   `declared_amount` double DEFAULT NULL,
   `declared_date` date DEFAULT NULL,
   `dividend_frequency` varchar(255) DEFAULT NULL,
   `ex_dividend_date` date DEFAULT NULL,
   `pay_date` date DEFAULT NULL,
   `stock` bigint DEFAULT NULL,
   PRIMARY KEY (`id`)
 ); 

 ALTER TABLE dividendannouncement
    ADD COLUMN IF NOT EXISTS modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;
    
    /* 
 ALTER TABLE dividendannouncement
    ADD CONSTRAINT ann_multi_uniq_key UNIQUE (stock, ex_dividend_date); */ 
    
 ALTER TABLE dividendannouncement
    ADD CONSTRAINT FK_ann_stock
    FOREIGN KEY (stock) REFERENCES stock(id); 
          
/* table dividendpayout */ 
CREATE TABLE IF NOT EXISTS `dividendpayout` (
   `id` bigint NOT NULL AUTO_INCREMENT,
   `current_stock_price` double DEFAULT NULL,
   `current_stock_quantity` double DEFAULT NULL,
   `current_yield` double DEFAULT NULL,
   `dividend_frequency` varchar(255) DEFAULT NULL,
   `payout_amount` double DEFAULT NULL,
   `payout_date` date DEFAULT NULL,
   `stock` bigint NOT NULL,
   `account` bigint NOT NULL,
   PRIMARY KEY (`id`)
 );
 
  ALTER TABLE dividendpayout
      ADD COLUMN IF NOT EXISTS modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;
      
  /* ALTER TABLE dividendpayout
    ADD CONSTRAINT div_multi_uniq_key UNIQUE (stock, account, payout_date); */ 
    
  ALTER TABLE dividendpayout
    ADD CONSTRAINT FK_pay_stock
    FOREIGN KEY (stock) REFERENCES stock(id); 
     
  ALTER TABLE dividendpayout
        ADD CONSTRAINT FK_pay_account
        FOREIGN KEY (account) REFERENCES account(id); 
    
 /* table stocktradeorder */ 
CREATE TABLE IF NOT EXISTS `stocktradeorder` (
   `id` bigint NOT NULL AUTO_INCREMENT,
   `order_type` varchar(255) NOT NULL, 
   `quantity` double NOT NULL,
   `price` double NOT NULL,
   `execution_date` date DEFAULT NULL,
   `comments` varchar(255) NOT NULL, 
   `stock` bigint NOT NULL,
   `account` bigint NOT NULL,
   PRIMARY KEY (`id`)
 );
 
  ALTER TABLE stocktradeorder
      ADD COLUMN IF NOT EXISTS modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP;
     
  ALTER TABLE stocktradeorder
    ADD CONSTRAINT FK_order_stock
    FOREIGN KEY (stock) REFERENCES stock(id); 
     
  ALTER TABLE stocktradeorder
        ADD CONSTRAINT FK_order_account
        FOREIGN KEY (account) REFERENCES account(id);  
          
 