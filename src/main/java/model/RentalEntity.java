package model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Kim on 2016-02-09.
 */
@Entity
@Table(name = "rental", schema = "sakila", catalog = "")
public class RentalEntity {
    private int rentalId;
    private short customerId;
    private byte staffId;
    private Timestamp lastUpdate;

    @Id
    @Column(name = "rental_id")
    public int getRentalId() {
        return rentalId;
    }

    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    @Basic
    @Column(name = "customer_id")
    public short getCustomerId() {
        return customerId;
    }

    public void setCustomerId(short customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "staff_id")
    public byte getStaffId() {
        return staffId;
    }

    public void setStaffId(byte staffId) {
        this.staffId = staffId;
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

        RentalEntity that = (RentalEntity) o;

        if (rentalId != that.rentalId) return false;
        if (customerId != that.customerId) return false;
        if (staffId != that.staffId) return false;
        if (lastUpdate != null ? !lastUpdate.equals(that.lastUpdate) : that.lastUpdate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rentalId;
        result = 31 * result + (int) customerId;
        result = 31 * result + (int) staffId;
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        return result;
    }
}
