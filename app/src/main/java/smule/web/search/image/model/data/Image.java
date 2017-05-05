package smule.web.search.image.model.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Image implements Parcelable {



    @SerializedName("contextLink")

    public String contextLink;
    @SerializedName("width")

    public int width;
    @SerializedName("height")

    public int height;
    @SerializedName("byteSize")

    public int byteSize;
    @SerializedName("thumbnailLink")

    public String thumbnailLink;
    @SerializedName("thumbnailWidth")

    public int thumbnailWidth;
    @SerializedName("thumbnailHeight")

    public int thumbnailHeight;

    public Image() {
    }

    public Image(String contextLink, int width, int height, int byteSize, String thumbnailLink, int thumbnailWidth, int thumbnailHeight) {
        this.contextLink = contextLink;
        this.width = width;
        this.height = height;
        this.byteSize = byteSize;
        this.thumbnailLink = thumbnailLink;
        this.thumbnailHeight = thumbnailHeight;
        this.thumbnailWidth = thumbnailWidth;
    }

    private Image(Parcel in) {
        this.contextLink = in.readString();
        this.width = in.readInt();
        this.height = in.readInt();
        this.byteSize = in.readInt();
        this.thumbnailLink = in.readString();
        this.thumbnailWidth = in.readInt();
        this.thumbnailHeight = in.readInt();
    }

    public String getContextLink() {
        return contextLink;
    }

    public void setContextLink(String contextLink) {
        this.contextLink = contextLink;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getByteSize() {
        return byteSize;
    }

    public void setByteSize(int byteSize) {
        this.byteSize = byteSize;
    }

    public String getThumbnailLink() {
        return thumbnailLink;
    }

    public void setThumbnailLink(String thumbnailLink) {
        this.thumbnailLink = thumbnailLink;
    }

    public int getThumbnailWidth() {
        return thumbnailWidth;
    }

    public void setThumbnailWidth(int thumbnailWidth) {
        this.thumbnailWidth = thumbnailWidth;
    }

    public int getThumbnailHeight() {
        return thumbnailHeight;
    }

    public void setThumbnailHeight(int thumbnailHeight) {
        this.thumbnailHeight = thumbnailHeight;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(contextLink);
        dest.writeInt(width);
        dest.writeInt(height);
        dest.writeInt(byteSize);
        dest.writeString(thumbnailLink);
        dest.writeInt(thumbnailHeight);
        dest.writeInt(thumbnailWidth);
    }

    @Override
    public String toString() {
        return "Image{" +
                "contextLink='" + contextLink + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", byteSize=" + byteSize +
                ", thumbnailLink='" + thumbnailLink + '\'' +
                ", thumbnailWidth=" + thumbnailWidth +
                ", thumbnailHeight=" + thumbnailHeight +
                '}';
    }

    public static final Parcelable.Creator<Image> CREATOR = new Parcelable.Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel source) {
            return new Image(source);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };
}
