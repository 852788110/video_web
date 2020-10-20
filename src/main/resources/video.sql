CREATE TABLE IF NOT EXISTS `video`(
   id bigint not null auto_increment,
   user_id bigint comment'作者id',
   user_name varchar(64) comment'作者名字',
   name 	varchar(64) not null comment'视频名称',
   path     varchar(255) comment'视频地址',
   pic_path varchar(255) comment'视频封面地址,如果该值为null的话，那么就以视频的第一帧为封面',
   view_counts bigint comment'视频的播放量',
   tags	varchar(255) comment'视频的标签',
   description  text comment'视频的描述信息',
   create_time datetime comment'视频的创建时间',
   modified_time datetime comment'视频的修改时间，默认和创建时间一样',
   stars bigint comment'视频的点赞数',
   primary key(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `user`(
   id bigint not null auto_increment,
   name varchar(64) comment'用户名称',
   icon varchar(255) comment'用户头像',
   video_count int comment'上传的视频数量',
   video_comment_count int comment'视频评论数量',
   create_time datetime comment'注册时间',
   primary key(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;