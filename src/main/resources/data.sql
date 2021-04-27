INSERT INTO ACCOUNT_HOLDER
	VALUES	
		(	1	, 'first'	, 'last'	, 'middle'		, 'ssn'	)
		, ( 2	, 'two'		, 'last two', 'middle two'	, 'ssn two'	);
INSERT INTO account_holders_contact_details
	values 
		(	1	, 'address1'	, 'eMail'	, 'phone' )
		, ( 2	, 'address2'	, 'eMail2'	, 'phone2' )
		, ( 3	, 'address13'	, 'eMail3'	, 'phone3' );
insert into CD_OFFERING
		(	ID	,INTEREST_RATE	,TERM	)
	values
		(	1	,0.01			,1 )
		, (	2	,0.02			,2 )
		, ( 3	,0.03			,3 );
insert into CD_ACCOUNT
		(	iD	,ACCOUNT_NUMBER	,BALANCE	,DATE	,INTEREST_RATE	,CD_OFFERING_ID	,ACCOUNT_HOLDER_ID	)
 	values
 		( 	1	,1				,1234.01	,'2021-01-01',0.001		,3				,1	)
 		,(	2	,1				,995.59		,'2020-12-06',0.002		,1				,1	);
insert into CHECKING_ACCOUNT 		
		(	ID	,ACCOUNT_NUMBER	,BALANCE	,DATE	,INTEREST_RATE	,ACCOUNT_HOLDER_ID	)
	values
		(	1	,1				,555.55		,'2021-02-15',0.001			,1	)
		,(	2	,2				,3333.33	,'2020-12-14',0.002			,1	);
insert into SAVINGS_ACCOUNT
		(	ID	,ACCOUNT_NUMBER	,BALANCE	,DATE	,INTEREST_RATE	,ACCOUNT_HOLDER_ID	)
	values
		(	1	,1				,2002.02	,'2020-12-18',0.0015	,1	);  
		