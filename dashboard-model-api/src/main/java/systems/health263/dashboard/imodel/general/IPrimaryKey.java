package systems.health263.dashboard.imodel.general;

import java.io.Serializable;

/**
 * core interface for any bean
 *
 * @Author Percy Mugadza
 * */
public interface IPrimaryKey extends Serializable {
    /**
     * Returns the primary key for the record
     * @return primary key
     *
     * */

    Long getId();
    /**
     *Sets the primary key for the record
     * @param  id the primary key
     *
     * */

    void setId(Long id);

}
