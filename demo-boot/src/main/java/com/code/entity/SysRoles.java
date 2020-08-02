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
public class SysRoles extends BaseEntity {


    private String role;

    private String description;

    private Boolean available;


}
