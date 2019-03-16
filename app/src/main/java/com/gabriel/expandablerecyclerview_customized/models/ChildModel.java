package com.gabriel.expandablerecyclerview_customized.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Gabriel on 19-03-2018.
 */

public class ChildModel implements Parcelable {

    public enum SWIPE_STATE {
        OPEN,
        CLOSED
    }

    private String id;
    private String childName;
    private SWIPE_STATE swipe_state = SWIPE_STATE.CLOSED;

    public ChildModel(String id, String childName) {
        this.id = id;
        this.childName = childName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public SWIPE_STATE getSwipe_state() {
        return swipe_state;
    }

    public void setSwipe_state(SWIPE_STATE swipe_state) {
        this.swipe_state = swipe_state;
    }

    protected ChildModel(Parcel in) {
        childName = in.readString();
    }

    public static final Creator<ChildModel> CREATOR = new Creator<ChildModel>() {
        @Override
        public ChildModel createFromParcel(Parcel in) {
            return new ChildModel(in);
        }

        @Override
        public ChildModel[] newArray(int size) {
            return new ChildModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(childName);
    }
}
