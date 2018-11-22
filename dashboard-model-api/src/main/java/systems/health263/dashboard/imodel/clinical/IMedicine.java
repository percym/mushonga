package systems.health263.dashboard.imodel.clinical;

/**
 * An interface for the uptake of medicine
 *
 * @author Percy mugadza
 */
public interface IMedicine  {

    /**
     * Returns the dosage for a particular drug to be administered
     *
     * @return the dosage for this item.
     * */
        double getDosage();

    /**
     * Sets the dosage for a particular drug to be administered
     *
     * @param   dosage for this item.
     * */
        void setDosage(double dosage);


    /**
     * Returns the days for a particular drug to be administered
     *
     * @return the days for this item.
     * */
        double getDays();

    /**
     * Sets the days for a particular drug to be administered
     *
     * @param   days for this item.
     * */
    void setDays(double days);

    /**
     * Returns the unit for a particular drug to be administered
     *
     * @return the unit for this item.
     * */
    double getUnits();

    /**
     * Sets the unit for a particular drug to be administered
     *
     * @param  units for this item.
     * */
    void setUnits(double units);


}
