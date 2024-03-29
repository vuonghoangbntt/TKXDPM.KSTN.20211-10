package subsystem_;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;

/**
 * This {@code InterbankBoundary} class connect API and process transaction
 * in our ECO BIKE Software.
 *
 * @author nhom10
 *
 */
@Slf4j
public class InterbankBoundary {
    private final String base_url = "https://ecopark-system-api.herokuapp.com/";

    public String reset(){

        try {
            JsonObject body = new JsonObject();
            body.addProperty("cardCode", "kstn_group10_2021");
            body.addProperty("owner", "Group 10");
            body.addProperty("cvvCode", "998");
            body.addProperty("dateExpired", "1125");
            String reset_path = "api/card/reset-balance";
            String response = new HttpConnector().sendPatch(base_url + reset_path, body.toString());
            if (response != null && new JsonParser().parse(response).isJsonObject()) {
                JsonObject responseJson = new JsonParser().parse(response).getAsJsonObject();
                System.out.println(responseJson);
                return responseJson.get("errorCode").getAsString();
            }
        } catch (Exception e){
            System.out.println("Không kết nối được API anh ơi!");
        }
        return "08";
    }

    public String processTransaction(JsonObject sentJson) {
        // response from api
        try {
            String transaction_path = "api/card/processTransaction";
            String response =  new HttpConnector().sendPatch(base_url + transaction_path,sentJson.toString());
            if (response != null && new JsonParser().parse(response).isJsonObject()) {
                JsonObject responseJson = new JsonParser().parse(response).getAsJsonObject();
                System.out.println("Giao dịch:" + responseJson);
                return responseJson.get("errorCode").getAsString();
            }
        } catch (Exception e) {
            System.out.println("Thằng api không response anh ơi!");
        }
        return "08";
    }
}
