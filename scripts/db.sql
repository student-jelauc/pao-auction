create table users (
  id integer unsigned primary key auto_increment,
  username varchar(128) not null unique,
  password varchar(255) not null
);

create table auctions (
  id integer unsigned primary key auto_increment,
  user_id int unsigned not null,
  title varchar(255) not null,
  products varchar(255),
  images varchar(512),
  publish_at timestamp,
  price decimal(10, 2) not null,
  is_closed tinyint(1),
  foreign key (user_id) references users(id)
);

create table bids (
  id integer unsigned primary key auto_increment,
  user_id int unsigned,
  auction_id int unsigned not null,
  price decimal(10, 2) not null,
  foreign key (user_id) references users(id),
  foreign key (auction_id) references auctions(id)
);