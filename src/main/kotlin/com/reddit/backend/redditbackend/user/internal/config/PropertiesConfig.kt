package com.reddit.backend.redditbackend.user.internal.config

import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationPropertiesScan("com.reddit.backend.redditbackend.user.internal.config.properties")
class PropertiesConfig