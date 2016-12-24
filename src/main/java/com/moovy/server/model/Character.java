package com.moovy.server.model;

import javax.persistence.*;

/**
 * Character class generated from the database.
 *
 * @author Thomas Arnaud (thomas.arnaud@etu.univ-lyon1.fr)
 * @author Bruno Buiret (bruno.buiret@etu.univ-lyon1.fr)
 * @author Alexis Rabilloud (alexis.rabilloud@etu.univ-lyon1.fr)
 */
@Entity
@AssociationOverrides({
    @AssociationOverride(name = "id.actor", joinColumns = @JoinColumn(name = "id_actor")),
    @AssociationOverride(name = "id.movie", joinColumns = @JoinColumn(name = "id_movie"))
})
public class Character
{
    private CharacterPK id;
    private String name;

    @Basic
    @Column(name = "name", nullable = false, length = 30)
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
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

        Character character = (Character) o;

        if (name != null ? !name.equals(character.name) : character.name != null)
        {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        return name != null ? name.hashCode() : 0;
    }

    @EmbeddedId
    public CharacterPK getId()
    {
        return id;
    }

    public void setId(CharacterPK id)
    {
        this.id = id;
    }
}
