package com.bentbase.backend.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;

@Configuration
@EnableWebMvc
@Import (BeanValidatorPluginsConfiguration.class)
public class SwaggersConfig {

}
