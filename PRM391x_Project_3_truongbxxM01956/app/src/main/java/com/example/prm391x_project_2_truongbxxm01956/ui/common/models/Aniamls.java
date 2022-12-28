package com.example.prm391x_project_2_truongbxxm01956.ui.common.models;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Aniamls implements Parcelable {
    private String name, description, phone = "";
    private int photo;
    private boolean isFavorite = false;

    public Aniamls(String name, String description, int photo, boolean isFavorite) {
        this.name = name;
        this.description = description;
        this.photo = photo;
        this.isFavorite = isFavorite;
    }

    protected Aniamls(Parcel in) {
        name = in.readString();
        description = in.readString();
        photo = in.readInt();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            isFavorite = in.readBoolean();
        }
    }

    public static final Creator<Aniamls> CREATOR = new Creator<Aniamls>() {
        @Override
        public Aniamls createFromParcel(Parcel in) {
            return new Aniamls(in);
        }

        @Override
        public Aniamls[] newArray(int size) {
            return new Aniamls[size];
        }
    };

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPhoto() {
        return photo;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeInt(photo);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            dest.writeBoolean(isFavorite);
        }
    }
}
