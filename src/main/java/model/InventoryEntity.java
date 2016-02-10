package model;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Kim on 2016-02-09.
 */
@Entity
@Table(name = "inventory", schema = "sakila")
public class InventoryEntity implements Serializable {
    private short inventory_id;
    private short filmId;
    private byte storeId;
    private Timestamp lastUpdate;

    @Id
    @Column(name = "inventory_id")
    public short getInventoryId() {
        return inventory_id;
    }

    public void setInventoryId(short filmId) {
        this.inventory_id = inventory_id;
    }

    @Basic
    @Column(name = "film_id")
    public short getFilmId() {
        return filmId;
    }

    public void setFilmId(short filmId) {
        this.filmId = filmId;
    }

    @Basic
    @Column(name = "store_id")
    public byte getStoreId() {
        return storeId;
    }

    public void setStoreId(byte storeId) {
        this.storeId = storeId;
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

        InventoryEntity that = (InventoryEntity) o;

        if (filmId != that.filmId) return false;
        if (storeId != that.storeId) return false;
        if (lastUpdate != null ? !lastUpdate.equals(that.lastUpdate) : that.lastUpdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) filmId;
        result = 31 * result + (int) storeId;
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        return result;
    }
}
