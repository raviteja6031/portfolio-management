INSERT INTO portfolio (portfolio_id) VALUES (101);
INSERT INTO portfolio (portfolio_id) VALUES (1);

INSERT INTO share_details (share_details_id,portfolio_id,share_name,count) VALUES (401,101,'DLF',6);
INSERT INTO share_details (share_details_id,portfolio_id,share_name,count) VALUES (402,101,'Reliance',2);
INSERT INTO share_details (share_details_id,portfolio_id,share_name,count) VALUES (403,101,'Zee Entertain',8);
INSERT INTO share_details (share_details_id,portfolio_id,share_name,count) VALUES (406,101,'Bharti Airtel Ltd.',12);
INSERT INTO share_details (share_details_id,portfolio_id,share_name,count) VALUES (407,101,'INDUS TOWERS',4);

INSERT INTO share_details (share_details_id,portfolio_id,share_name,count) VALUES (404,1,'Zee Entertain',2);
INSERT INTO share_details (share_details_id,portfolio_id,share_name,count) VALUES (405,1,'Bharti Airtel Ltd.',10);

INSERT INTO mutual_fund_details (mf_details_id,portfolio_id,mutual_fund_name,count) VALUES (501,101,'ICICI Prudential Technology Fund - Direct Plan - Growth',5);
INSERT INTO mutual_fund_details (mf_details_id,portfolio_id,mutual_fund_name,count) VALUES (502,101,'DSP Government Securities Fund - Direct Plan - Growth',20);
INSERT INTO mutual_fund_details (mf_details_id,portfolio_id,mutual_fund_name,count) VALUES (505,101,'Nippon Value Fund (G)',7);

INSERT INTO mutual_fund_details (mf_details_id,portfolio_id,mutual_fund_name,count) VALUES (503,1,'ICICI Prudential Technology Fund',20);