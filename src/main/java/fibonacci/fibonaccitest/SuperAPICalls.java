package fibonacci.fibonaccitest;

import com.google.gson.Gson;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

import java.util.ArrayList;

/**
 * Java class to initialise RESTEasy API client
 */
public abstract class SuperAPICalls {

    private ResteasyClient apiClient;
    private Gson gson;

    public SuperAPICalls() {
        apiClient = new ResteasyClientBuilder().build();
        gson = new Gson();
    }

    public ResteasyClient getApiClient() {
        return apiClient;
    }

    private Gson getGson() {
        return gson;
    }

    public ArrayList<String> convertResponseToArrayList(String jsonArrayString) {
        return getGson().fromJson(jsonArrayString, ArrayList.class);
    }
}
