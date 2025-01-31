package src.api.opencage;

import com.opencagedata.jopencage.JOpenCageGeocoder;
import com.opencagedata.jopencage.model.JOpenCageForwardRequest;
import com.opencagedata.jopencage.model.JOpenCageResponse;
import com.opencagedata.jopencage.model.JOpenCageResult;
import src.api.keys.OpenCageKey;

public class OpenCageServ {

    public String obtainGeoInfo (Integer number, String neighbourhood, String city) {

        String apiKey = OpenCageKey.OPEN_CAGE_APIKEY;

        JOpenCageGeocoder jOpenCageGeocoder = new JOpenCageGeocoder(apiKey);

        String address = number + " " + neighbourhood + ", " + city;

        JOpenCageForwardRequest request = new JOpenCageForwardRequest(address);

        JOpenCageResponse response = jOpenCageGeocoder.forward(request);

        return "";

    }

}
