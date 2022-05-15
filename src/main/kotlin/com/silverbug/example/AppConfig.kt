package com.silverbug.example

import com.fasterxml.jackson.annotation.JsonProperty
import io.dropwizard.Configuration
import io.dropwizard.db.DataSourceFactory
import javax.validation.constraints.NotNull

class AppConfig(
    @NotNull
    @JsonProperty("database")
    var dataSourceFactory: DataSourceFactory
) : Configuration()