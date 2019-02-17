package com.code.auth.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "news")
public class News implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private String url;
    private String artFrom;

    private String edit;
    private String keyword;
    private String lead;
    private String content;
    private Integer state;

    private Integer isHot;
    private Integer pageView;

    private Date createTime;
    private Date modifyTime;

}
