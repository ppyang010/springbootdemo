package com.code;

import com.code.domain.SequenceProperties;
import com.code.util.Sequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ccy
 * @description
 * @time 2019/3/15 下午4:28
 */

@EnableConfigurationProperties({SequenceProperties.class})
@Configuration
public class GidConfig {

    @Autowired
    private SequenceProperties sequenceProperties;
    @Bean
    @ConditionalOnMissingBean(Sequence.class)
    public Sequence sequence() {
        System.out.println("gid starter sequence bean init");
        System.out.println(sequenceProperties.toString());
        return new Sequence();
    }
}
