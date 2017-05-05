package smule.web.search.image.model.data;


import com.google.gson.annotations.SerializedName;

public class Request {
    @SerializedName("title")
    String title;
    @SerializedName("totalResults")
    int totalResults;
    @SerializedName("searchTerms")
String searchTerms;
    @SerializedName("count")
int count;
    @SerializedName("startIndex")
int startIndex;
    @SerializedName("inputEncoding")
    String inputEncoding;
    @SerializedName("outputEncoding")
String outputEncoding;
    @SerializedName("safe")
String safe;
    @SerializedName("searchType")
String searchType;

    public Request(){};
    public Request(String title, int totalResults, String searchTerms, int count, int startIndex, String inputEncoding, String outputEncoding, String safe, String searchType) {
        this.title = title;
        this.totalResults = totalResults;
        this.searchTerms = searchTerms;
        this.count = count;
        this.startIndex = startIndex;
        this.inputEncoding = inputEncoding;
        this.outputEncoding = outputEncoding;
        this.safe = safe;
        this.searchType = searchType;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public String getSearchTerms() {
        return searchTerms;
    }

    public void setSearchTerms(String searchTerms) {
        this.searchTerms = searchTerms;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public String getInputEncoding() {
        return inputEncoding;
    }

    public void setInputEncoding(String inputEncoding) {
        this.inputEncoding = inputEncoding;
    }

    public String getOutputEncoding() {
        return outputEncoding;
    }

    public void setOutputEncoding(String outputEncoding) {
        this.outputEncoding = outputEncoding;
    }

    public String getSafe() {
        return safe;
    }

    public void setSafe(String safe) {
        this.safe = safe;
    }

    @Override
    public String toString() {
        return "Request{" +
                "title='" + title + '\'' +
                ", totalResults=" + totalResults +
                ", searchTerms='" + searchTerms + '\'' +
                ", count=" + count +
                ", startIndex=" + startIndex +
                ", inputEncoding='" + inputEncoding + '\'' +
                ", outputEncoding='" + outputEncoding + '\'' +
                ", safe='" + safe + '\'' +
                ", searchType='" + searchType + '\'' +
                '}';
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }


}
