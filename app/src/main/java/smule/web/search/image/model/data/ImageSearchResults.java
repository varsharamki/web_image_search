package smule.web.search.image.model.data;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class ImageSearchResults implements Parcelable {
    @SerializedName("title")
    public String title;
    @SerializedName("link")

    public String link;
    @SerializedName("displayLink")
    public String displayLink;
    @SerializedName("snippet")

    public String snippet;
    @SerializedName("mime")

    public String mime;

    @SerializedName("image")
    Image image;


    public ImageSearchResults() {
    }

    public ImageSearchResults(String title, String link, String displayLink, String snippet, String mime, Image image) {
        this.title = title;
        this.link = link;
        this.displayLink = displayLink;
        this.snippet = snippet;
        this.mime = mime;
        this.image = image;
    }

    private ImageSearchResults(Parcel in) {
        this.title = in.readString();
        this.link = in.readString();
        this.displayLink = in.readString();
        this.snippet = in.readString();
        this.mime = in.readString();
        this.image = in.readParcelable(Image.class.getClassLoader());

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDisplayLink() {
        return displayLink;
    }

    public void setDisplayLink(String displayLink) {
        this.displayLink = displayLink;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "ImageSearchResults{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", displayLink='" + displayLink + '\'' +
                ", snippet='" + snippet + '\'' +
                ", mime='" + mime + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(link);
        dest.writeString(displayLink);
        dest.writeString(snippet);
        dest.writeString(mime);
        dest.writeParcelable(image, flags);
    }
    public static final Parcelable.Creator<ImageSearchResults> CREATOR = new Parcelable.Creator<ImageSearchResults>() {
        @Override
        public ImageSearchResults createFromParcel(Parcel source) {
            return new ImageSearchResults(source);
        }

        @Override
        public ImageSearchResults[] newArray(int size) {
            return new ImageSearchResults[size];
        }
    };

}
