package com.code.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author ccy
 * JPA @MappedSuperclass 注解说明
 * https://blog.csdn.net/heardy/article/details/7924192
 * 1.@MappedSuperclass注解只能标准在类上：@Target({java.lang.annotation.ElementType.TYPE})
 * <p>
 * 2.标注为@MappedSuperclass的类将不是一个完整的实体类，他将不会映射到数据库表，但是他的属性都将映射到其子类的数据库字段中。
 * <p>
 * 3.标注为@MappedSuperclass的类不能再标注@Entity或@Table注解，也无需实现序列化接口。
 * <p>
 * 但是如果一个标注为@MappedSuperclass的类继承了另外一个实体类或者另外一个同样标注了@MappedSuperclass的类的话，他将可以使用@AttributeOverride或@AttributeOverrides注解重定义其父类(无论是否是实体类)的属性映射到数据库表中的字段。
 * <p>
 * 比如可以重定义字段名或长度等属性，使用@AttributeOverride中的子属性@Column进行具体的定义。
 * <p>
 * 注意：对于其父类中标注@Lob注解的属性将不能重载，并且@AttributeOverride里的@Column设置都将不起作用。
 */
@MappedSuperclass
@Data

public class BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 创建时间
     */
    @Column(insertable = false, updatable = false)
    private Date createdTime;

    /**
     * 修改时间
     */
    @Column(insertable = false, updatable = false)
    private Date modifiedTime;

    /**
     * 逻辑删除 0：正常 1：已删除
     */
    @Column(insertable = false)
    private Integer isDeleted;
}
