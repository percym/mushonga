package info.mushonga.search.imodel.general;

import java.io.Serializable;

/**
 * Interface to specify the basic operations permitted on an entity bean.
 *
 * @author Munyaradzi Takayindisa
 */
public interface IPrimaryKey extends Serializable {

    /**
     * Returns the primary key for this record.
     *
     * @return the primary key for this record.
     */
    Long getId();

    /**
     * Sets the primary key for this record.
     *
     * @param id The primary key for this record.
     */
    void setId(Long id);

}