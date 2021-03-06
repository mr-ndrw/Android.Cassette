package andrewtorski.cassette.domain.entity;

import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * Represents a Cassette - a tape composed of many different Recordings captured sequentially.
 * This structure also simple data like title, description, date of creation.
 * A Cassette may be compiled, meaning that all included Recordings are compiled into one coherent
 * file will all Recordings contained within.
 */
public class Cassette {

    //region Private Fields

    /**
     * Unique Identifier of the Cassete.
     */
    private long id;

    /**
     * Title of this Cassette.
     */
    private String title;

    /**
     * Description of this Cassette.
     * Not required.
     */
    private String description;

    /**
     * Date and Time of the creation of this Cassette.
     */
    private Date dateTimeOfCreation;

    /**
     * Total length of all Recordings.
     * Expressed in milliseconds.
     */
    private int length;

    /**
     * Was this Cassette compiled to one File.
     */
    private boolean isCompiled;

    /**
     * Compiled audio file path.
     * This string is null or empty if this.wasCompiled is null.
     */
    private String compiledFilePath;

    /**
     * Compiled audio file.
     * This file is essentially all Recordings merged into one file.
     * This reference is null, if this.wasCompiled is null.
     */
    private File compiledFile;

    /**
     * Date and Time of when this Cassette was compiled to one file.
     * This reference is null, if this.wasCompiled is null.
     */
    private Date dateTimeOfCompilation;

    /**
     * Number of the Recordings on this Cassette.
     */
    private int numberOfRecordings;

    /**
     * Collection of Recordings on this Cassette.
     */
    private List<Recording> recordings;

    //endregion PrivateFields

    //region Constructors

    public Cassette() {
        this.id = -1;
    }

    /**
     * Constructor which should be used when Mapping from DAL Cassette Entity to Domain Cassette.
     */
    public Cassette(long id, String title, String description,
                    Date dateTimeOfCreation, int length, boolean isCompiled,
                    String compiledFilePath, Date dateTimeOfCompilation,
                    int numberOfRecordings) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dateTimeOfCreation = dateTimeOfCreation;
        this.length = length;
        this.isCompiled = isCompiled;
        this.compiledFilePath = compiledFilePath;
        this.dateTimeOfCompilation = dateTimeOfCompilation;
        this.numberOfRecordings = numberOfRecordings;
    }

    /**
     * Constructors which should be used when mapping from Presentation CassetteModel to Domain
     * Cassette.
     */
    public Cassette(String title, String description) {
        this.title = title;
        this.description = description;
        this.dateTimeOfCreation = new Date();
    }

    //endregion Constructors

    //region Methods

    public int incrementAndReturnNumberOfRecordings() {
        return ++numberOfRecordings;
    }

    //endregion Methods

    //region Getters & Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateTimeOfCreation() {
        return dateTimeOfCreation;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getLengthInSeconds() {
        return this.length / 1000;
    }

    public boolean isCompiled() {
        return isCompiled;
    }

    public String getCompiledFilePath() {
        return compiledFilePath;
    }

    public void setCompiledFilePath(String compiledFilePath) {
        this.compiledFilePath = compiledFilePath;
    }

    public void setCompiled(boolean wasCompiled) {
        this.isCompiled = wasCompiled;
    }

    public File getCompiledFile() {
        return compiledFile;
    }

    public void setCompiledFile(File compiledFile) {
        this.compiledFile = compiledFile;
    }

    public Date getDateTimeOfCompilation() {
        return dateTimeOfCompilation;
    }

    public void setDateTimeOfCompilation(Date dateTimeOfCompilation) {
        this.dateTimeOfCompilation = dateTimeOfCompilation;
    }

    public int getNumberOfRecordings() {
        return numberOfRecordings;
    }

    public void setNumberOfRecordings(int numberOfRecordings) {
        this.numberOfRecordings = numberOfRecordings;
    }

    public List<Recording> getRecordings() {
        return recordings;
    }

    public void setRecordings(List<Recording> recordings) {
        this.recordings = recordings;
    }

    //endregion Getters&Setters
}
