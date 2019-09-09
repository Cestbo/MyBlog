create table comment
(
	id bigint,
	parent_id bigint,
	type int,
	commentator bigint,
	gmt_create BIGINT,
	gmt_modified bigint,
	like_count bigint default 0,
	content varchar(1024),
	constraint comment_pk
		primary key (id)
);