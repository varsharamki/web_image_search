package smule.web.search.image.model.interfaces;


import org.json.JSONObject;

import smule.web.search.image.model.implementation.WebSearchImageModelImpl;

public interface WebSearchImageModel {

    void sendImageSearchRequest(String url,WebSearchImageModelImpl.OnJSONResponseCallback callback);
}
