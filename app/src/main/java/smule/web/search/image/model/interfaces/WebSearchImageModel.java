package smule.web.search.image.model.interfaces;


import smule.web.search.image.model.implementation.WebSearchImageModelImpl;

public interface WebSearchImageModel {

    void sendImageSearchRequest(String url, WebSearchImageModelImpl.OnJSONResponseCallback callback);
}
