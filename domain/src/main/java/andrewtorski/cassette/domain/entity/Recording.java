package andrewtorski.cassette.domain.entity;

import java.io.File;
import java.util.Date;

/**
 * Wraps an audio-file with data about a Recording.
 */
public class Recording {

    //region Private Fields

    /**
     * Unique identifier of the Recording.
     */
    private long id;

    /**
     * Title of this Recording.
     * Not required.
     */
    private String title;

    /**
     * Description of the Recording.
     * Not required.
     */
    private String descripition;

    /**
     * Date and Time of the Recording.
     */
    private Date dateTimeOfRecording;

    /**
     * Length of the recording.
     * Expressed in milliseconds.
     */
    private int lengthInMiliseconds;

    /**
     * Path to the audio file with the capture.
     */
    private String audioFilePath;

    /**
     * Reference to the actual File with the audio capture.
     */
    private File audioFile;

    /**
     * Sequence of this Recording in the Cassette.
     * Cassette is compromised of many recordings. First recording will
     * have number 0, second 1... etc
     */
    private int sequenceInTheCassette;

    //region Navigation Fields

    /**
     * Identifier of the Cassette to which this Recording belongs.
     */
    private long cassetteId;

    /**
     * Cassette to which this Recording belongs.
     */
    private Cassette cassette;

    //endregion Navigation Fields

    //endregion Private Fields

    //region Constructors

    public Recording() {
        id = -1;
    }

    /**
     * Constructor which only should be used when Mapping from DAL RecordingEntity
     * to Domain Recording.
     */
    public Recording(long id, long cassetteId, String title,
                     String description, Date dateTimeOfRecording,
                     int lengthInMilliseconds, String audioFilePath,
                     int sequenceInTheCassette) {
        this.id = id;
        this.cassetteId = cassetteId;
        this.title = title;
        this.descripition = description;
        this.dateTimeOfRecording = dateTimeOfRecording;
        this.lengthInMiliseconds = lengthInMilliseconds;
        this.audioFilePath = audioFilePath;
        this.sequenceInTheCassette = sequenceInTheCassette;
        this.cassetteId = cassetteId;
    }

    /**
     * Constructor which only should be used when Mapping from Presentation RecordingModel to
     * Domain Recording.
     */
    public Recording(File audioFile, int lengthInMiliseconds, Cassette cassette) {
        this.audioFile = audioFile;
        this.audioFilePath = this.audioFile.getPath();
        this.lengthInMiliseconds = lengthInMiliseconds;
        this.cassette = cassette;
        this.cassetteId = this.cassette.getId();
        this.sequenceInTheCassette = this.cassette.incrementAndReturnNumberOfRecordings();
    }

    //endregion Constructors

    //region Getters

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescripition() {
        return descripition;
    }

    public void setDescripition(String descripition) {
        this.descripition = descripition;
    }

    public long getId() {
        return id;
    }

    public Date getDateTimeOfRecording() {
        return dateTimeOfRecording;
    }

    public int getLengthInMiliseconds() {
        return lengthInMiliseconds;
    }

    public String getAudioFilePath() {
        return audioFilePath;
    }

    public File getAudioFile() {
        return audioFile;
    }

    public int getSequenceInTheCassette() {
        return sequenceInTheCassette;
    }

    public long getCassetteId() {
        return cassetteId;
    }

    public Cassette getCassette() {
        return cassette;
    }

    //endregion Getters
}
