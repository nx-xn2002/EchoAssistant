# 建库
create database if not exists echo;
use echo;

create table if not exists user
(
    id           bigint auto_increment
        primary key,
    userAccount  varchar(256)                       null comment '用户账号',
    userName     varchar(256)                       null comment '用户昵称',
    avatarUrl    varchar(1024)                      null comment '用户头像',
    gender       tinyint                            null comment '性别',
    userPassword varchar(512)                       not null comment '密码',
    phone        varchar(128)                       null comment '电话',
    email        varchar(512)                       null comment '邮箱',
    userRole     int      default 1                 null comment '用户角色 0 - 管理员, 1 - 学生, 2 - 老师',
    createTime   datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime   datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deletedFlag  tinyint  default 0                 not null comment '删除状态 默认0'
) comment '用户表';

create table if not exists question_bank
(
    id          bigint auto_increment comment 'id' primary key,
    title       varchar(256)                       null comment '标题',
    description text                               null comment '描述',
    userId      int                                not null comment '创建用户id',
    createTime  datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime  datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deletedFlag tinyint  default 0                 not null comment '删除状态 默认0',
    index idx_title (title)
) comment '题库表';

create table if not exists question
(
    id          bigint auto_increment comment 'id' primary key,
    title       varchar(256)                       null comment '标题',
    content     text                               null comment '内容',
    userId      bigint                             not null comment '创建用户 id',
    createTime  datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime  datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deletedFlag tinyint  default 0                 not null comment '删除状态 默认0',
    index idx_title (title),
    index idx_userId (userId)
) comment '题目表';

create table if not exists question_bank_question
(
    id             bigint auto_increment comment 'id' primary key,
    questionBankId bigint                             not null comment '题库 id',
    questionId     bigint                             not null comment '题目 id',
    userId         bigint                             not null comment '创建用户 id',
    createTime     datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime     datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deletedFlag    tinyint  default 0                 not null comment '删除状态 默认0',
    UNIQUE (questionBankId, questionId)
) comment '题库题目表';

create table if not exists question_tag
(
    id          bigint auto_increment comment 'id' primary key,
    tagName     varchar(256)                       null comment '标签名',
    userId      int                                not null comment '创建用户id',
    createTime  datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime  datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deletedFlag tinyint  default 0                 not null comment '删除状态 默认0',
    index idx_tagName (tagName)
) comment '标签表';

create table if not exists question_tag_question
(
    id            bigint auto_increment comment 'id' primary key,
    questionTagId bigint                             not null comment '题库 id',
    questionId    bigint                             not null comment '题目 id',
    userId        bigint                             not null comment '创建用户 id',
    createTime    datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime    datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deletedFlag   tinyint  default 0                 not null comment '删除状态 默认0',
    UNIQUE (questionTagId, questionId)
) comment '标签题目表';
