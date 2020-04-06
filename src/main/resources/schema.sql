create table if not exists web_tool.user
(
	userId varchar(255) not null
		primary key,
	userAvatar varchar(255) null,
	userEmail varchar(255) null,
	userLastLoginIp varchar(255) null,
	userLastLoginTime datetime null,
	userName varchar(255) null,
	userPassword varchar(255) null,
	userRegisterTime datetime null,
	userStatus int null,
	userUrl varchar(255) null,
	token varchar(6) null
);
