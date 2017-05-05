package smule.web.search.image.model.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WebImageSearchResponse {

    @SerializedName("queries")
    Request request;
    @SerializedName("items")
    List<ImageSearchResults> searchResponse;

    public WebImageSearchResponse() {
    }

    ;

    public WebImageSearchResponse(Request request, List<ImageSearchResults> searchResponse) {
        this.request = request;
        this.searchResponse = searchResponse;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public List<ImageSearchResults> getSearchResponse() {
        return searchResponse;
    }

    public void setSearchResponse(List<ImageSearchResults> searchResponse) {
        this.searchResponse = searchResponse;
    }
}
