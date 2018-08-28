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
        photoData.add(new PhotoData("https://goo.gl/hC3WrG", "1"));
        photoData.add(new PhotoData("https://goo.gl/iKQjUy", "2"));
        photoData.add(new PhotoData("https://goo.gl/V13vQH", "3"));
        photoData.add(new PhotoData("https://goo.gl/Kuszxy", "4"));
        photoData.add(new PhotoData("https://goo.gl/GjNyyh", "5"));
        photoData.add(new PhotoData("https://goo.gl/GjNyyh", "6"));
        photoData.add(new PhotoData("https://goo.gl/GjNyyh", "7"));
        photoData.add(new PhotoData("https://goo.gl/GjNyyh", "8"));
        photoData.add(new PhotoData("https://goo.gl/Kuszxy", "9"));
        photoData.add(new PhotoData("https://goo.gl/Kuszxy", "10"));
        photoData.add(new PhotoData("https://goo.gl/Kuszxy", "11"));
        photoData.add(new PhotoData("https://goo.gl/Kuszxy", "12"));
        photoData.add(new PhotoData("https://goo.gl/iKQjUy", "13"));
        photoData.add(new PhotoData("https://goo.gl/iKQjUy", "14"));
        photoData.add(new PhotoData("https://goo.gl/V13vQH", "15"));
        photoData.add(new PhotoData("https://goo.gl/GjNyyh", "16"));
        photoData.add(new PhotoData("https://goo.gl/GjNyyh", "17"));
        photoData.add(new PhotoData("https://goo.gl/GjNyyh", "18"));
        photoData.add(new PhotoData("https://goo.gl/GjNyyh", "19"));
        photoData.add(new PhotoData("https://goo.gl/Kuszxy", "20"));
        photoData.add(new PhotoData("https://goo.gl/PoYnzA", "21"));
        photoData.add(new PhotoData("https://goo.gl/9oJauM", "22"));
        photoData.add(new PhotoData("https://goo.gl/9oJauM", "23"));
        photoData.add(new PhotoData("https://goo.gl/9oJauM", "24"));
        photoData.add(new PhotoData("https://goo.gl/PoYnzA", "25"));
        photoData.add(new PhotoData("https://goo.gl/PoYnzA", "26"));
        photoData.add(new PhotoData("https://goo.gl/PoYnzA", "27"));


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
