package com.silverbug.example.health

import com.codahale.metrics.health.HealthCheck
import org.hibernate.SessionFactory


class DatabaseHealthCheck(private val sessionFactory: SessionFactory) : HealthCheck() {
    @Throws(Exception::class)
    override fun check(): Result {
        val session = sessionFactory.openSession()
        val isHealthy = session.doReturningWork { conn -> { conn.isValid(1) } }()
        session.close()
        return if (isHealthy)
            Result.healthy("Connection Open")
        else
            Result.unhealthy("Connection Close")
    }
}