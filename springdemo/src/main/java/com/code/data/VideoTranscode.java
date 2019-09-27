package com.code.data;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Accessors(chain = true)
@Table(name = "video_transcode")
@Entity
public class VideoTranscode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date createdTime;
    private Date modifiedTime = new Date();
    private Boolean isDeleted = Boolean.FALSE;

    /**
     * 腾讯云文件id
     */
    @Column(name = "file_id")
    private String fileId;

    /**
     * url
     */
    @Column(name = "url")

    private String url;

    /**
     * 码率
     */
    @Column(name = "definition")
    private Integer definition;

    /**
     * 大小
     */
    @Column(name = "size")
    private Long size;
}