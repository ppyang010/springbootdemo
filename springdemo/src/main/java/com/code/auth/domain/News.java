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
    private Integer nid;
    private String title;
    private String url;
    @Column(name = "artfrom")
    private String artFrom;

    private String edit;
    private Date time;
    private String keyword;
    private String lead;
    private String content;
    private Integer state;

    @Column(name="ishot")
    private Integer isHot;
    private Integer count;


}
