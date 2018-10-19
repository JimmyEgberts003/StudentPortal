package com.egberts.jimmy.studentportal;

import android.os.Parcel;
import android.os.Parcelable;

public class Portal implements Parcelable {

    private String mTitle;
    private String mUrl;

    public Portal(String mTitle, String mUrl) {
        this.mTitle = mTitle;
        this.mUrl = mUrl;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    /*
     * from this point starts the Parcable. Parcable makes it possible to send over the object specified in this class.
     * Serializable can be used as well but is a lot slower because parcable creates less garbage objects which makes it double as fast.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mTitle);
        dest.writeString(this.mUrl);
    }

    protected Portal(Parcel in) {
        this.mTitle = in.readString();
        this.mUrl = in.readString();
    }

    public static final Parcelable.Creator<Portal> CREATOR = new Parcelable.Creator<Portal>() {
        @Override
        public Portal createFromParcel(Parcel source) {
            return new Portal(source);
        }

        @Override
        public Portal[] newArray(int size) {
            return new Portal[size];
        }
    };
}
