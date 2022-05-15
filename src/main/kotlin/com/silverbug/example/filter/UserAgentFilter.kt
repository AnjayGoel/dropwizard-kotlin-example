package com.silverbug.example.filter

import java.io.IOException
import javax.ws.rs.WebApplicationException
import javax.ws.rs.container.ContainerRequestContext
import javax.ws.rs.container.ContainerRequestFilter
import javax.ws.rs.core.HttpHeaders
import javax.ws.rs.core.Response
import javax.ws.rs.ext.Provider


@Provider
class UserAgentFilter : ContainerRequestFilter {
    @Throws(IOException::class)
    override fun filter(requestContext: ContainerRequestContext) {
        val userAgentHeader = requestContext.getHeaderString(HttpHeaders.USER_AGENT)
        if (userAgentHeader == null) {
            val cause: Exception = IllegalArgumentException("User-Agent Header was not specified")
            throw WebApplicationException(cause, Response.Status.BAD_REQUEST)
        }
    }
}

