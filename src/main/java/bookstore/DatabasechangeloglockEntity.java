package bookstore;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "DATABASECHANGELOGLOCK", schema = "book-store", catalog = "")
public class DatabasechangeloglockEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private int id;
    @Basic
    @Column(name = "LOCKED")
    private boolean locked;
    @Basic
    @Column(name = "LOCKGRANTED")
    private Timestamp lockgranted;
    @Basic
    @Column(name = "LOCKEDBY")
    private String lockedby;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public Timestamp getLockgranted() {
        return lockgranted;
    }

    public void setLockgranted(Timestamp lockgranted) {
        this.lockgranted = lockgranted;
    }

    public String getLockedby() {
        return lockedby;
    }

    public void setLockedby(String lockedby) {
        this.lockedby = lockedby;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DatabasechangeloglockEntity that = (DatabasechangeloglockEntity) o;
        return id == that.id && locked == that.locked && Objects.equals(lockgranted, that.lockgranted) && Objects.equals(lockedby, that.lockedby);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, locked, lockgranted, lockedby);
    }
}
