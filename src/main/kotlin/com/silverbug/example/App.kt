package com.silverbug.example

import com.silverbug.example.core.Person
import com.silverbug.example.db.PersonDao
import com.silverbug.example.filter.UserAgentFilter
import com.silverbug.example.health.DatabaseHealthCheck
import com.silverbug.example.resources.PersonResource
import io.dropwizard.Application
import io.dropwizard.db.PooledDataSourceFactory
import io.dropwizard.hibernate.HibernateBundle
import io.dropwizard.setup.Bootstrap
import io.dropwizard.setup.Environment

class App : Application<AppConfig>() {

    private val hibernate: HibernateBundle<AppConfig?> = object : HibernateBundle<AppConfig?>(
        Person::class.java
    ) {
        override fun getDataSourceFactory(configuration: AppConfig?): PooledDataSourceFactory {
            return configuration!!.dataSourceFactory
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) = App().run(*args)
    }

    override fun run(appConfig: AppConfig, environment: Environment) {
        val personDao = PersonDao(hibernate.sessionFactory);
        environment.jersey().register(PersonResource(personDao));
        environment.jersey().register(UserAgentFilter::class.java)
        environment.healthChecks().register("database", DatabaseHealthCheck(hibernate.sessionFactory));

    }

    override fun initialize(bootstrap: Bootstrap<AppConfig>?) {
        bootstrap!!.addBundle(hibernate);
    }
}