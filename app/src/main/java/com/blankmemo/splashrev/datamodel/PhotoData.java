package com.blankmemo.splashrev.datamodel;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by hongyuchen on 2018-08-25.
 */

public class PhotoData implements Parcelable {
    private String mUrl;
    private String mAuthor;

// main constructor
    public PhotoData(String url, String author) {
        this.mUrl = url;
        this.mAuthor = author;
    }
// constructor for reading data back from the parcel
    protected PhotoData(Parcel in) {
        mUrl = in.readString();
        mAuthor = in.readString();
    }
// generates instances of Parcelable class from PhotoData.
    public static final Creator<PhotoData> CREATOR = new Creator<PhotoData>() {
        @Override
        public PhotoData createFromParcel(Parcel in) {
            return new PhotoData(in);
        }

        @Override
        public PhotoData[] newArray(int size) {
            return new PhotoData[size];
        }
    };

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String author) {
        this.mAuthor = author;
    }

    //manually enter array of photos
    public static ArrayList<PhotoData> getPhotoData() {

        ArrayList<PhotoData> photoData = new ArrayList<>();
        photoData.add(new PhotoData("https://goo.gl/hC3WrG", "john"));
        photoData.add(new PhotoData("https://goo.gl/iKQjUy", "smith"));
        photoData.add(new PhotoData("https://goo.gl/V13vQH", "mark"));
        photoData.add(new PhotoData("https://goo.gl/Kuszxy", "tina"));
        photoData.add(new PhotoData("https://goo.gl/GjNyyh", "coco"));

        return photoData;

    }

    @Override
    public int describeContents() {
        return 0;
    }
// write to the parcel
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mUrl);
        parcel.writeString(mAuthor);
    }
}
