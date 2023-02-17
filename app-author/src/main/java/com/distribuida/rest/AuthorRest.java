package com.distribuida.rest;

import com.distribuida.db.Author;
import com.distribuida.repository.AuthorRepository;
import com.distribuida.servicios.AuthorService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
@Path("/api/author")
public class AuthorRest {

    @Inject
    AuthorService authorService;

    @GET
    @Produces("application/json")
    public List<Author> list() {
        return authorService.findAllAuthors();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Author get(@PathParam("id") Long id) {
        return authorService.findAuthor(id);
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response create(Author author) {
        try {
            authorService.saveAuthor(author);
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al guardar").build();
        }
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response update(@PathParam("id") long id, Author author) throws Exception {
        try {
            authorService.updateAuthor(id, author);
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al actualizar author" + ex.getMessage()).build();
        }
        return Response.status((Response.Status.OK)).build();
    }

    @DELETE
    @Path("/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response delete(@PathParam("id") Long id) {
        try {
            authorService.deleteAuthor(id);
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al eliminar author").build();
        }
        return Response.status((Response.Status.OK)).build();
    }

}
