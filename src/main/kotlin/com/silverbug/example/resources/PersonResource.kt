package com.silverbug.example.resources

import com.codahale.metrics.annotation.Metered
import com.codahale.metrics.annotation.Timed
import com.silverbug.example.core.Person
import com.silverbug.example.db.PersonDao
import io.dropwizard.hibernate.UnitOfWork
import java.util.OptionalInt
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/person")
@Produces(MediaType.APPLICATION_JSON)
open class PersonResource(val personDao: PersonDao) {
    @GET
    @UnitOfWork
    fun getAll(): List<Person?> {
        return personDao.fetchAll()
    }

    @GET
    @Path("/{id}")
    @Timed
    @UnitOfWork
    fun getById(@PathParam("id") id: OptionalInt): Person? {
        val person = personDao.findById(id.asInt)
        if (person != null)
            return personDao.findById(id.asInt)
        else {
            throw WebApplicationException("No Person found", Response.Status.NOT_FOUND)

        }
    }

    @POST
    @UnitOfWork
    @Metered(name = "create_person_meter")
    fun create(person: Person): Person? {
        return personDao.create(person)
    }

}
