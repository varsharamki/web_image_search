package smule.web.search.image.presenter.implementations;


import android.content.Context;
import android.util.Log;
import android.view.Window;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import smule.web.search.image.model.data.ImageSearchResults;
import smule.web.search.image.model.data.WebImageSearchResponse;
import smule.web.search.image.model.implementation.WebSearchImageModelImpl;
import smule.web.search.image.model.interfaces.WebSearchImageModel;
import smule.web.search.image.presenter.interfaces.WebSearchImagePresenter;
import smule.web.search.image.view.interfaces.WebSearchImageView;

public class WebSearchImagePresenterImpl implements WebSearchImagePresenter {
    static final String BASE_URL = "https://www.googleapis.com/customsearch/v1";
    static final String API_KEY = "AIzaSyDP46Hs6hzwiKt4pZWvcJVxSljfvK56IGI";
    static final String API_CX = "000773107618163626375:-mukwin5tnk";

    Context context;
    private WebSearchImageView imageView;
    private WebSearchImageModel imageModel;
    ArrayList<ImageSearchResults> imageSearchResultsArrayList = new ArrayList<ImageSearchResults>();

    public WebSearchImagePresenterImpl(Context context, WebSearchImageView imageView) {
        this.context = context;
        this.imageView = imageView;
    }

    ;

    public WebSearchImagePresenterImpl(WebSearchImageView imageView) {
        this.imageView = imageView;
        this.imageModel = new WebSearchImageModelImpl();
    }

    public WebSearchImagePresenterImpl(WebSearchImageView imageView, WebSearchImageModel imageModel) {
        this.imageView = imageView;
        this.imageModel = new WebSearchImageModelImpl();
    }

    @Override
    public void sendSearchQuery(String query,int startIndex) {
        Log.d("SMULE1 customsear", customSearchQueryURL(query,startIndex));
        String url = customSearchQueryURL(query,startIndex);
      JSONObject response;

       try {
            imageModel = new WebSearchImageModelImpl();
            imageModel.sendImageSearchRequest(url, new WebSearchImageModelImpl.OnJSONResponseCallback(){
                @Override
                public void onJSONResponse(boolean success, JSONObject response) {
                    Gson gson = new GsonBuilder().create();
                    WebImageSearchResponse searchResponse = gson.fromJson(response.toString(), WebImageSearchResponse.class);
                 if(searchResponse!=null) {
                      imageView.displayImageSearch(searchResponse);
                 }
                }
            });


        }catch(JsonParseException | ArrayIndexOutOfBoundsException e){

        }catch(Exception e){

        }finally{
            imageSearchResultsArrayList=null;
        }
    }

    /**
     *
     * @param query
     * @return
     */
    private String customSearchQueryURL(String query,int startIndex) {
        String url = "";
        String start = Integer.toString(startIndex);
        String num = "10";

        try {
            url = String.format("%s?key=%s&cx=%s&searchType=image&q=%s&start=%s&num=%s",
                    BASE_URL,
                    API_KEY,
                    API_CX,
                    URLEncoder.encode(query, "UTF-8"),
                    start,
                    num
            );
        }catch (UnsupportedEncodingException exception) {

        }catch(Exception e){

        }

        return url;
    }


}
