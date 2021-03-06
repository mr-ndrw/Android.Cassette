package andrewtorski.cassette.data.repository.datasource;

import android.database.Cursor;

import java.util.LinkedList;
import java.util.List;

import andrewtorski.cassette.data.db.RecordingDataDbAdapter;
import andrewtorski.cassette.data.entity.RecordingEntity;

/**
 * {@link andrewtorski.cassette.data.repository.datasource.RecordingDataStore} implementation based on SQLite database.
 */
public class DbRecordingDataStore implements RecordingDataStore {

    private RecordingDataDbAdapter dbAdapter;

    public DbRecordingDataStore() {
        dbAdapter = RecordingDataDbAdapter.getInstance();
        dbAdapter.open();
    }

    @Override
    public RecordingEntity create(RecordingEntity recordingEntity) {

        long cassetteId = recordingEntity.cassetteId;
        int sequenceInCassette = recordingEntity.sequenceInTheCassette;
        long dateTimeOfRecording = recordingEntity.dateTimeOfRecording;
        String filePath = recordingEntity.audioFilePath;
        int length = recordingEntity.length;
        long id = dbAdapter.create(cassetteId, sequenceInCassette, dateTimeOfRecording, filePath, length);

        recordingEntity.id = id;

        return recordingEntity;
    }

    @Override
    public RecordingEntity get(long recordingId) {
        Cursor cursor = dbAdapter.getById(recordingId);

        if (cursor == null) {
            return null;
        }

        RecordingEntity recordingEntity = RecordingEntity.createFromCursor(cursor);

        return recordingEntity;
    }

    @Override
    public List<RecordingEntity> getAll() {
        Cursor cursor = dbAdapter.getAll();

        return DbRecordingDataStore.getListOfRecordingsFromCursor(cursor);
    }

    @Override
    public List<RecordingEntity> getAllForCassette(long cassetteId) {
        Cursor cursor = dbAdapter.getAllForCassette(cassetteId);

        return DbRecordingDataStore.getListOfRecordingsFromCursor(cursor);
    }

    @Override
    public List<RecordingEntity> getAllBetweenDates(long fromDate, long toDate) {
        Cursor cursor = dbAdapter.getAllBetween(fromDate, toDate);

        return DbRecordingDataStore.getListOfRecordingsFromCursor(cursor);
    }

    @Override
    public List<RecordingEntity> getAllBetweenDatesForCassette(long cassetteId, long fromDate, long toDate) {
        Cursor cursor = dbAdapter.getAllForCassetteBetweenDates(cassetteId, fromDate, toDate);

        return DbRecordingDataStore.getListOfRecordingsFromCursor(cursor);
    }

    @Override
    public List<RecordingEntity> getAllWithTitleOrDescriptionLike(String searchClause) {
        Cursor cursor = dbAdapter.getAllTitleDescriptionLike(searchClause);

        return DbRecordingDataStore.getListOfRecordingsFromCursor(cursor);
    }

    @Override
    public boolean update(RecordingEntity recordingEntity) {

        long id = recordingEntity.id;
        String title = recordingEntity.title,
                description = recordingEntity.description;

        boolean wasSuccess = dbAdapter.update(id, title, description);

        return wasSuccess;
    }

    @Override
    public boolean delete(long id) {
        return dbAdapter.delete(id);
    }

    private static List<RecordingEntity> getListOfRecordingsFromCursor(Cursor cursor) {
        List<RecordingEntity> recordingEntityList = new LinkedList<>();

        if (cursor == null) {
            return recordingEntityList;
        }

        RecordingEntity recordingEntity;
        while (cursor.moveToNext()) {
            recordingEntity = RecordingEntity.createFromCursor(cursor);
            if (recordingEntity != null) {
                recordingEntityList.add(recordingEntity);
            }
        }

        cursor.close();

        return recordingEntityList;
    }

    public int count() {
        return dbAdapter.count();
    }
}
