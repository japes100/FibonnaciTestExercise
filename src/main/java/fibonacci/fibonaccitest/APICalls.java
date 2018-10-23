package fibonacci.fibonaccitest;

import java.lang.reflect.Array;
import java.util.ArrayList;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.specimpl.MultivaluedMapImpl;

import fibonacci.fibonaccitest.SuperAPICalls;

public class APICalls extends SuperAPICalls {

	/**
     * GET call, @param url, @return ArrayList
     */
    public ArrayList<String> customGetCall(String url) {
        Response response = getApiClient().target(url).request().get();
        String jsonString = response.readEntity(String.class);
        System.out.println("jsonString1 = " + jsonString);
        response.close();
        return convertResponseToArrayList(jsonString);
    }

    /**
     * GET call with range parameters, @param url, @param startIndex, @param finishIndex,@return ArrayList
     */
    public ArrayList<String> rangeGetCall(String url, int startIndex, int finishIndex) {
    	MultivaluedMapImpl<String, Object> multiValueMap = new MultivaluedMapImpl<String, Object>();
        multiValueMap.add("startIndex", startIndex);
        multiValueMap.add("finishIndex", finishIndex);

        Response response = getApiClient().target(url).queryParams(multiValueMap).request().get();
        String jsonString = response.readEntity(String.class);
        System.out.println("jsonString2 = " + jsonString);
        response.close();
        return convertResponseToArrayList(jsonString);
    }

    /**
     * GET call with path parameter, @param url, @param pathParamValue, @return String
     */
    public String singleNumberCall(String url, int pathParamValue) {
        Response response = getApiClient().target(url).path(String.valueOf(pathParamValue)).request().get();
        String jsonString = response.readEntity(String.class);
        response.close();
        return jsonString;
    }

	
}
