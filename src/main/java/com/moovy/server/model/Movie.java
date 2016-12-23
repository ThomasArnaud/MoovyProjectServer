package com.moovy.server.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * Movie class generated from the database.
 *
 * @author Thomas Arnaud (thomas.arnaud@etu.univ-lyon1.fr)
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 * @author Alexis Rabilloud (alexis.rabilloud@etu.univ-lyon1.fr)
 */
@Entity
public class Movie
{
    private int id;
    private String title;
    private int duration;
    private Date releaseDate;
    private int budget;
    private int benefit;
    private List<Character> characters;
    private Director director;
    private List<Category> categories;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    @Basic
    @Column(name = "title", nullable = false, length = 30)
    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    @Basic
    @Column(name = "duration", nullable = false)
    public int getDuration()
    {
        return duration;
    }

    public void setDuration(int duration)
    {
        this.duration = duration;
    }

    @Basic
    @Column(name = "release_date", nullable = false)
    public Date getReleaseDate()
    {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate)
    {
        this.releaseDate = releaseDate;
    }

    @Basic
    @Column(name = "budget", nullable = false)
    public int getBudget()
    {
        return budget;
    }

    public void setBudget(int budget)
    {
        this.budget = budget;
    }

    @Basic
    @Column(name = "benefit", nullable = false)
    public int getBenefit()
    {
        return benefit;
    }

    public void setBenefit(int benefit)
    {
        this.benefit = benefit;
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

        Movie movie = (Movie) o;

        if (id != movie.id)
        {
            return false;
        }
        if (duration != movie.duration)
        {
            return false;
        }
        if (budget != movie.budget)
        {
            return false;
        }
        if (benefit != movie.benefit)
        {
            return false;
        }
        if (title != null ? !title.equals(movie.title) : movie.title != null)
        {
            return false;
        }
        if (releaseDate != null ? !releaseDate.equals(movie.releaseDate) : movie.releaseDate != null)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + duration;
        result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);
        result = 31 * result + budget;
        result = 31 * result + benefit;
        return result;
    }

    @OneToMany(mappedBy = "id.movie", cascade = CascadeType.ALL)
    @JsonIgnore
    public List<Character> getCharacters()
    {
        return characters;
    }

    public void setCharacters(List<Character> characters)
    {
        this.characters = characters;
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "id_director", referencedColumnName = "id", nullable = false)
    public Director getDirector()
    {
        return director;
    }

    public void setDirector(Director director)
    {
        this.director = director;
    }

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(
        name = "movie_category",
        joinColumns = @JoinColumn(name = "id_movie", referencedColumnName = "id", nullable = false),
        inverseJoinColumns = @JoinColumn(name = "code_category", referencedColumnName = "code", nullable = false)
    )
    public List<Category> getCategories()
    {
        return categories;
    }

    public void setCategories(List<Category> categories)
    {
        this.categories = categories;
    }
}
