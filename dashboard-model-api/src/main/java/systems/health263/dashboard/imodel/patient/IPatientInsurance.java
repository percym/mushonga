package systems.health263.dashboard.imodel.patient;


import systems.health263.dashboard.imodel.general.IActive;

/**
 * IPatientInsurance interface for the patient class
 *
 * @author Munyaradzi Takayindisa
 */
public interface IPatientInsurance extends IActive {

    /**
     * Returns the {@link IMedicalAid medicalAid} for this patient.
     *
     * @return the {@link IMedicalAid medicalAid} for this patient.
     */
    IMedicalAid getMedicalAid();

    /**
     * Sets the {@link IMedicalAid medicalAid} for this patient.
     *
     * @param medicalAid the {@link IMedicalAid medicalAid} for this patient.
     */
    void setMedicalAid(IMedicalAid medicalAid);

    /**
     * Returns the memberNumber for this patient.
     *
     * @return the memberNumber for this patient.
     */
    String getMemberNumber();

    /**
     * Sets the memberNumber for this patient.
     *
     * @param memberNumber the memberNumber for this patient.
     */
    void setMemberNumber(String memberNumber);

    /**
     * Returns the dependantCode for this patient.
     *
     * @return the dependantCode for this patient.
     */
    String getDependantCode();

    /**
     * Sets the dependantCode for this patient.
     *
     * @param dependantCode the dependantCode for this patient.
     */
    void setDependantCode(String dependantCode);


}
