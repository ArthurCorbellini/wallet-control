-- Criação da tabela "vaults" e suas dependências
create table vaults (
	id bigint not null primary key AUTO_INCREMENT,
	name varchar(50) not null,
	amount decimal,
	user_username varchar(50) not null,
	
	foreign key (user_username) references users(username)
);
