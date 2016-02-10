package model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Kim on 2016-02-09.
 */
@Entity
@Table(name = "film_category", schema = "sakila", catalog = "")
@IdClass(FilmCategoryEntityPK.class)
public class FilmCategoryEntity {
    private short filmId;
    private byte categoryId;
    private Timestamp lastUpdate;

    @Id
    @Column(name = "film_id")
    public short getFilmId() {
        return filmId;
    }

    public void setFilmId(short filmId) {
        this.filmId = filmId;
    }

    @Id
    @Column(name = "category_id")
    public byte getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(byte categoryId) {
        this.categoryId = categoryId;
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

        FilmCategoryEntity that = (FilmCategoryEntity) o;

        if (filmId != that.filmId) return false;
        if (categoryId != that.categoryId) return false;
        if (lastUpdate != null ? !lastUpdate.equals(that.lastUpdate) : that.lastUpdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) filmId;
        result = 31 * result + (int) categoryId;
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        return result;
    }
}
