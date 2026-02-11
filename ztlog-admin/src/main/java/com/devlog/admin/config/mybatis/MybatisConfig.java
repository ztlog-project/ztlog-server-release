package com.devlog.admin.config.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.devlog.admin.mapper")
public class MybatisConfig {
}
