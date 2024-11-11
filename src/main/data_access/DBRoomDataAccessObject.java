package data_access;

import org.json.JSONException;
import org.json.JSONObject;

import entity.Room;
import entity.RoomFactory;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import use_case.navigation.NavigationDataAccessInterface;

/**
 * The DAO for room data.
 */
public class DBRoomDataAccessObject implements NavigationDataAccessInterface{
    private static final String ROOMCODE = "roomcode";
    private static final String MESSAGE = "message";

}
