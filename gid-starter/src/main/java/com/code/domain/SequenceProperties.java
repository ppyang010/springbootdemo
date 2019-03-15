package com.code.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ccy
 */
@Data
@ConfigurationProperties(prefix = "gid.sequence")
public class SequenceProperties {
    private Long datacenterId;
    private Long workerId;
}
