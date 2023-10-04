package org.movies;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Link;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.awt.*;
import java.net.URI;
import java.util.List;

import static org.jboss.resteasy.reactive.RestResponse.StatusCode.*;

@Path("/movies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class movieResource {
    @Inject
    movieRepository movierepository;

    @GET
    public Response getall(){
        List<movie> movies = movierepository.listAll();
        return Response.ok(movies).build();
    }
    @GET
    @Path("{id}")
    public Response getbyid(@PathParam("id") long id){
        return movierepository.findByIdOptional(id)
                .map(movie -> Response.ok(movie).build())
                .orElse(Response.status(NOT_FOUND).build());

    }

    @GET
    @Path("title/{title}")
    public Response getbytitle(@PathParam("title") String title){
        return movierepository.find("title" , title)
                .singleResultOptional().map(movie -> Response.ok(movie).build())
                .orElse(Response.status(NOT_FOUND).build());
    }

    @GET
    @Path("country/{country}")
    public Response getbycountry(@PathParam("country") String country){
        List<movie> movies = movierepository.findByCountry(country);
        return Response.ok(movies).build();
    }
    @POST
    @Transactional
    public Response createmovie(movie mov){
        movierepository.persist(mov);
        if (movierepository.isPersistent(mov)) {
            return Response.created(URI.create("/movies" + mov.getId())).build();
        }
        return Response.status(BAD_REQUEST).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deletebyid(@PathParam("id") long id){

        boolean deleted = movierepository.deleteById(id);
        return deleted? Response.noContent().build() : Response.status(NOT_FOUND).build();
    }
    @PUT
    @Path("{id}")
    @Transactional
    public Response updatemovie(@PathParam("id") long id , movie mov){

        return movierepository.findByIdOptional(id)
                .map( m ->{
                    m.setTitle(mov.getTitle());
                    return Response.ok(m).build();
                }).orElse(Response.status(NOT_FOUND).build());

    }

}
