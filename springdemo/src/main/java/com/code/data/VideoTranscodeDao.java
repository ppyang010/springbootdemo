package com.code.data;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Administrator on 2017/10/5.
 * 继承 JpaRepository  表对应的实体类 和 主键
 */
public interface VideoTranscodeDao extends JpaRepository<VideoTranscode,Integer> {


}
