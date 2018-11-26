package systems.health263.dashboard.imodel.clinical;


import systems.health263.dashboard.imodel.general.IPrimaryKey;

/*
 * IDataICD10 interface to hold ICD10 entity.
 *
 * Copyright © 2018 NewHealth263 (Pty) Ltd. All rights reserved.
 *
 * @author Munyaradzi Takayindisa
 *
 */
public interface IDataICD10 extends IPrimaryKey {

    /**
     * Returns the codeField for the ICD10.
     *
     * @return the codeField for the ICD10.
     */
    String getCodeField();

    /**
     * Sets the codeField for the ICD10.
     *
     * @param codeField for the ICD10.
     */
    void setCodeField(String codeField);

    /**
     * Returns the descriptionField for the ICD10.
     *
     * @return the descriptionField for the ICD10.
     */
    String getDescriptionField();

    /**
     * Sets the descriptionField for the ICD10.
     *
     * @param descriptionField for the ICD10.
     */
    void setDescriptionField(String descriptionField);

}
