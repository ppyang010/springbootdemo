package com.code.entity;

import com.code.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author ccy
 * @since 2020-08-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysUsers extends BaseEntity {


    private String username;

    private String password;

    private String salt;

    private Boolean locked;


}
