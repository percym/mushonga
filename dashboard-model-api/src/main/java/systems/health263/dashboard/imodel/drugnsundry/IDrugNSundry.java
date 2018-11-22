package systems.health263.dashboard.imodel.drugnsundry;


import systems.health263.dashboard.imodel.general.IGenericNaming;

/**
 * Copyright © 2018 NewHealth263 (Pty) Ltd. All rights reserved.
 * Interface for DrugNSundry
 * @author percym
 */
public interface IDrugNSundry extends IGenericNaming {
    /**
     * Sets the systemId of the drug or sundry
     *
     * @param systemId of the of the drug or sundry
     */
    void setSystemId(String systemId);

    /**
     * Gets the systemId of the drug or sundry
     *
     * @return the systemId of the drug or sundry
     */
    String getSystemId();

    /**
     * Sets the code of sundry or drug by type.
     *
     * @param drugORSundry the code of sundry or drug by type.
     */
    void setDrugORSundry(String drugORSundry);

    /**
     * Gets the  code of sundry or drug by type.
     *
     * @return the code of sundry or drug by type.
     */
    String getDrugORSundry();


}