package com.wyf.blog.ssm.pojo.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
@Table(name = "core_post")
public class CorePost implements Serializable {
    /**
     * 主键
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 对应作者 ID
     */
    @Column(name = "post_author")
    private Long postAuthor;

    /**
     * 正文
     */
    @Column(name = "post_content")
    private String postContent;

    /**
     * 标题
     */
    @Column(name = "post_title")
    private String postTitle;

    /**
     * 摘要
     */
    @Column(name = "post_excerpt")
    private String postExcerpt;

    /**
     * 文章状态：publish(发布) draft(草稿)
     */
    @Column(name = "post_status")
    private String postStatus;

    /**
     * 评论状态：open(开放) closed(关闭)
     */
    @Column(name = "comment_status")
    private String commentStatus;

    /**
     * 文章缩略名
     */
    @Column(name = "post_name")
    private String postName;

    /**
     * 文章内容过滤
     */
    @Column(name = "post_content_filtered")
    private String postContentFiltered;

    /**
     * 父文章
     */
    @Column(name = "post_parent")
    private Long postParent;

    /**
     * 唯一标识符(短链接)
     */
    @Column(name = "guid")
    private String guid;

    /**
     * 排序
     */
    @Column(name = "menu_order")
    private Integer menuOrder;

    /**
     * 文章类型：post(文章) technology(技术) tools(工具) books(书籍)
     */
    @Column(name = "post_type")
    private String postType;

    /**
     * MIME 类型
     */
    @Column(name = "post_mime_type")
    private String postMimeType;

    /**
     * 评论总数
     */
    @Column(name = "comment_count")
    private Long commentCount;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}