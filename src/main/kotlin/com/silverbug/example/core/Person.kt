package com.silverbug.example.core

import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.Hibernate
import javax.persistence.*

@Entity
data class Person(
    @Id @JsonProperty("id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0,
    @JsonProperty("name") var name: String = "",
    @JsonProperty("email") var email: String = "",
    @JsonProperty("profile_url") var profile_url: String = ""
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Person

        return id != null && id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id )"
    }
}