package com.moovy.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Character primary key class.
 *
 * @author Thomas Arnaud (thomas.arnaud@etu.univ-lyon1.fr)
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 * @author Alexis Rabilloud (alexis.rabilloud@etu.univ-lyon1.fr)
 */
@Embeddable
public class CharacterPK implements Serializable
{
    private Actor actor;
    private Movie movie;

    public CharacterPK()
    {
    }

    public CharacterPK(Actor actor, Movie movie)
    {
        this.actor = actor;
        this.movie = movie;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }

        CharacterPK that = (CharacterPK) o;

        if (actor != null ? !actor.equals(that.actor) : that.actor != null)
        {
            return false;
        }
        return movie != null ? movie.equals(that.movie) : that.movie == null;
    }

    @Override
    public int hashCode()
    {
        int result = actor != null ? actor.hashCode() : 0;
        result = 31 * result + (movie != null ? movie.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "id_actor", referencedColumnName = "id", nullable = false)
    public Actor getActor()
    {
        return actor;
    }

    public void setActor(Actor actor)
    {
        this.actor = actor;
    }

    @ManyToOne
    @JoinColumn(name = "id_movie", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    public Movie getMovie()
    {
        return movie;
    }

    public void setMovie(Movie movie)
    {
        this.movie = movie;
    }
}
