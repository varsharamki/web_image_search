package smule.web.search.image.model.implementation;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import smule.web.search.image.model.interfaces.WebSearchImageModel;

public class WebSearchImageModelImpl implements WebSearchImageModel {

    public WebSearchImageModelImpl() {
    }

    ;

    @Override
    public void sendImageSearchRequest(String url, final OnJSONResponseCallback callback) {


        try {
            AsyncHttpClient client = new AsyncHttpClient(true, 80, 443);
            Log.d("SMULE1 ::",url);
            client.get(url, new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {


                    if (statusCode == 200 && response != null) {
                        callback.onJSONResponse(true,response);

                    } else if (response == null) {
                        // handle this scenario
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    Log.d("SMULE1 ::Failure ", statusCode + "  " + throwable.getMessage());
                    super.onFailure(statusCode, headers, throwable, errorResponse);
                    if (statusCode == 403) {

                    } else if (statusCode == 400) {

                    }
                    callback.onJSONResponse(false,errorResponse);

                }
            });

        } catch (Exception e) {
            StackTraceElement elements[] = e.getStackTrace();
            for (int i = 0, n = elements.length; i < n; i++) {
                Log.d("AsyncHttpClient ", elements[i].getFileName()
                        + ":" + elements[i].getLineNumber()
                        + ">> "
                        + elements[i].getMethodName() + "()");
            }
        } finally {

        }

    }
    public interface OnJSONResponseCallback {
        public void onJSONResponse(boolean success, JSONObject response);
    }
}
