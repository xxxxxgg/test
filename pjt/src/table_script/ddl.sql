create table member(
	id varchar(50) not null,
	pw varchar(50) not null,
	name varchar(50) not null,
	email varchar(100),
	regdate timestamp default now(),
	updatedate timestamp default now(),
	primary key(id)
);




/*------------------------------------------------------------*/
create table tbl_board(
	bno int not null auto_increment,
	title varchar(200) not null,
	content text,
	writer varchar(50) not null,
	regdate timestamp not null default now(),
	viewcnt int default 0,
	primary key(bno)
);

create table tbl_lexicon(
	id int not null auto_increment,
	lexicon varchar(200) not null,
	primary key(lexicon, id)
);

//create index idx_tlex_lex on tbl_lexicon(lexicon, id);

create table tbl_appearance(
	targetType varchar(200) not null,
	lid int not null,
	targetId varchar(200) not null,
	cnt int,
	primary key(lid, targetType, targetId)
);