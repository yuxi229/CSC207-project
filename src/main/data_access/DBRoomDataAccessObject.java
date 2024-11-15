package data_access;

import java.io.IOException;

import entity.Room;
import entity.RoomFactory;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import use_case.navigation.NavigationDataAccessInterface;

/**
 * The DAO for room data.
 */
public class DBRoomDataAccessObject implements NavigationDataAccessInterface {
    private static final int SUCCESS_CODE = 200;
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String STATUS_CODE_LABEL = "status_code";
    private static final String ROOMCODE = "roomcode";
    private static final String MESSAGE = "message";
    private final RoomFactory roomFactory;

    public DBRoomDataAccessObject(RoomFactory roomFactory) {
        this.roomFactory = roomFactory;
    }

    @Override
    public boolean existsByRoomCode(String roomCode) {
        return false;
    }

    @Override
    public Room get(String roomCode) {
        return null;
    }
}

//@Override
//public boolean existsByRoomCode(String roomCode) {
//    final OkHttpClient client = new OkHttpClient().newBuilder()
//            .build();
//    final Request request = new Request.Builder()
//            .url(String.format("http://vm003.teach.cs.toronto.edu:20112/checkIfUserExists?username=%s", username))
//            .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
//            .build();
//    try {
//        final Response response = client.newCall(request).execute();
//
//        final JSONObject responseBody = new JSONObject(response.body().string());
//
//        return responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE;
//    }
//    catch (IOException | JSONException ex) {
//        throw new RuntimeException(ex);
//    }
//}
//
//}
