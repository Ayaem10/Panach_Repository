package org.movies;


import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class movieRepository implements PanacheRepository<movie>{


    public List<movie> findByCountry(String country) {
        return  list("SELECT m FROM Movie m WHERE m.country=?1 ORDER BY" + "DESC" , country);
    }
}
