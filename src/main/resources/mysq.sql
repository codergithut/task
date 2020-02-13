CREATE TABLE `job_data` (
  `id` varchar(36) NOT NULL,
  `job_code` varchar(36) NOT NULL,
  `job_name` varchar(45) DEFAULT NULL,
  `job_des` varchar(45) DEFAULT NULL,
  `start_task_tpl_code` varchar(36) NOT NULL,
  `is_finished` tinyint(4) NOT NULL,
  `dead_line` datetime DEFAULT NULL,
  PRIMARY KEY (`id`));


CREATE TABLE `fr`.`task_tpl_data` (
  `id` VARCHAR(36) NOT NULL,
  `task_code` VARCHAR(36) NOT NULL,
  `task_name` VARCHAR(45) NULL,
  `task_des` VARCHAR(45) NULL,
  `task_type` VARCHAR(45) NULL,
  `depend_task_tpl_code` VARCHAR(36) NULL,
  `publisher_user_id` VARCHAR(36) NULL,
  `receiver_user_id` VARCHAR(36) NULL,
  `next_task_tpl_code` VARCHAR(36) NULL,
  `is_parent` TINYINT NULL,
  PRIMARY KEY (`id`));


CREATE TABLE `fr`.`task_ins_data` (
  `id` VARCHAR(36) NOT NULL,
  `task_ins_code` VARCHAR(36) NOT NULL,
  `task_tpl_code` VARCHAR(36) NOT NULL,
  `depend_task_tpl_code` VARCHAR(36) NULL,
  `task_status` VARCHAR(45) NULL,
  `job_code` VARCHAR(36) NULL,
  `next_task_tpl_code` VARCHAR(36) NULL,
  PRIMARY KEY (`id`));



CREATE TABLE `fr`.`task_ins_ext_data` (
  `id` VARCHAR(36) NOT NULL,
  `task_ins_ext_code` VARCHAR(36) NOT NULL,
  `task_ins_code` VARCHAR(36) NOT NULL,
  `type` VARCHAR(45) NULL,
  `task_ins_ext_desc` VARCHAR(45) NULL,
  `task_ins_ext_datacol` VARCHAR(45) NULL,
  `ext_date` DATETIME NULL,
  PRIMARY KEY (`id`));

 CREATE TABLE `fr`.`task_tpl_desc_meta_data` (
   `task_tpl_desc_code` VARCHAR(36) NOT NULL,
   `task_code` VARCHAR(36) NOT NULL,
   `meta_name` VARCHAR(45) NULL,
   `meta_type` VARCHAR(45) NULL,
   PRIMARY KEY (`task_tpl_desc_code`));

