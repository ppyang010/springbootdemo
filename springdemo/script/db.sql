CREATE TABLE `sys_roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role` varchar(100) DEFAULT '' not NULL,
  `description` varchar(100) DEFAULT '' not NULL,
  `available` tinyint(1) DEFAULT '0' not null,
 `is_deleted` tinyint(1) DEFAULT '0' not null,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_sys_roles_role` (`role`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `sys_permissions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `permission` varchar(100) DEFAULT '' not NULL,
  `description` varchar(100) DEFAULT '' not NULL,
  `available` tinyint(1) DEFAULT '0' not null,
  `is_deleted` tinyint(1) DEFAULT '0' not null,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_sys_permissions_permission` (`permission`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `sys_users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT '' not NULL,
  `password` varchar(100) DEFAULT ''  not NULL,
  `salt` varchar(100) DEFAULT '' not NULL,
  `locked` tinyint(1) DEFAULT '0' not null,
  `is_deleted` tinyint(1) DEFAULT '0' not null,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_sys_users_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `sys_users_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  `is_deleted` tinyint(1) DEFAULT '0' not null,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `sys_roles_permissions` (
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  `is_deleted` tinyint(1) DEFAULT '0' not null,
  PRIMARY KEY (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


