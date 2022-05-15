package com.silverbug.example.db

import com.silverbug.example.core.Person
import io.dropwizard.hibernate.AbstractDAO
import org.hibernate.SessionFactory


class PersonDao(sessionFactory: SessionFactory) : AbstractDAO<Person>(sessionFactory) {
    fun fetchAll(): List<Person> {
        return query("SELECT u FROM Person u").list()
    }

    fun findById(id: Int): Person? {
        return get(id)
    }

    fun create(person: Person): Person? {
        return persist(person)
    }

}