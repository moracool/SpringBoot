
CREATE TABLE client (
   id UUID PRIMARY KEY,
   name VARCHAR (45),   
   email VARCHAR (45),
   createdAt TIMESTAMP(2) WITH TIME ZONE,
   modifiedAt TIMESTAMP(2) WITH TIME ZONE,   
);

CREATE TABLE client_login ( 
	id UUID PRIMARY KEY,
	client_id UUID,
	login_name VARCHAR (25),
	password_hash VARCHAR (255),
	password_salt VARCHAR (100),
	token_acces VARCHAR (255),
	token_generation_time TIMESTAMP(2) WITH TIME ZONE, 	
	isactive TINYINT,
	createdAt TIMESTAMP(2) WITH TIME ZONE,
   	modifiedAt TIMESTAMP(2) WITH TIME ZONE	
);

CREATE TABLE phone (
   id UUID PRIMARY KEY,
   client_id UUID,
   number VARCHAR (15),   
   citycode VARCHAR (4),
   contrycode VARCHAR (4),
   createdAt TIMESTAMP(2) WITH TIME ZONE,
   modifiedAt TIMESTAMP(2) WITH TIME ZONE   
);


ALTER TABLE client_login
ADD FOREIGN KEY (user_id) 
REFERENCES client(id);


ALTER TABLE phone_client
ADD FOREIGN KEY (user_id) 
REFERENCES client(id);
