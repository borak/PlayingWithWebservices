package model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Kim on 2016-02-09.
 */
@Entity
@Table(name = "film_actor", schema = "sakila")
// @IdClass(FilmActorEntityPK.class)
public class FilmActorEntity implements Serializable {
    private short actorId;
    private short filmId;
    private Timestamp lastUpdate;

    @Id
    @Column(name = "actor_id")
    public short getActorId() {
        return actorId;
    }

    public void setActorId(short actorId) {
        this.actorId = actorId;
    }

    @Id
    @Column(name = "film_id")
    public short getFilmId() {
        return filmId;
    }

    public void setFilmId(short filmId) {
        this.filmId = filmId;
    }

    @Basic
    @Column(name = "last_update")
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FilmActorEntity that = (FilmActorEntity) o;

        if (actorId != that.actorId) return false;
        if (filmId != that.filmId) return false;
        if (lastUpdate != null ? !lastUpdate.equals(that.lastUpdate) : that.lastUpdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) actorId;
        result = 31 * result + (int) filmId;
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        return result;
    }
}
