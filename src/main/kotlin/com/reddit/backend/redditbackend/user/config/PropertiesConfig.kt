package com.reddit.backend.redditbackend.user.config

import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationPropertiesScan("com.reddit.backend.redditbackend.user.config.properties")
class PropertiesConfig