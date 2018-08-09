package com.code.auth.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
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
    private String artfrom;

    private String edit;
    private Date time;
    private String keyword;
    private String lead;
    private String content;
    private Integer state;
    private Integer isHot;
    private Integer count;


}
