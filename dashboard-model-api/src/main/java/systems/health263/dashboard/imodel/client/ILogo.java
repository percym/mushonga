package systems.health263.dashboard.imodel.client;


import systems.health263.dashboard.imodel.general.IPrimaryKey;

/**
 * ILogo interface for this e-bill
 *
 * @author Munyaradzi Takayindisa
 */
public interface ILogo  extends IPrimaryKey {

    /**
     * Returns the fileName for this file.
     *
     * @return the fileName for this file.
     */
    String getFileName();


    /**
     * Sets the fileName for this client.
     *
     * @param fileName for this client.
     */
    void setFileName(String fileName);


    /**
     * Sets the fileDownloadUri for this file.
     *
     * @return  fileDownloadUri for this client.
     */
    String getFileDownloadUri();

    /**
     * Sets the fileDownloadUri for this file.
     *
     * @param fileDownloadUri for this file.
     */
    void setFileDownloadUri(String fileDownloadUri);


    /**
     * Gets the fileType for this file.
     *
     * @return  fileType for this file.
     */
    String getFileType();

    /**
     * Sets the fileType for this file.
     *
     * @param fileType for this file.
     */
    void setFileType(String fileType);
    /**
     * Gets the size for this file.
     *
     * @return  size for this file.
     */
    Long getSize();

    /**
     * Sets the fileType for this file.
     *
     * @param size for this file.
     */
    void setSize(Long size);


}
