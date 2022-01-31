package com.pmdm.saj.definitiveconcesionario.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Car implements Parcelable {
    public int ref, price, kms, year, doors, power;
    public String title, fuel, shift, color, description, location, url;
    public String[] images;

    public Car(int ref, int price, int kms, int year, int doors, int power, String title,
               String fuel, String shift, String color, String description, String location,
               String url, String images) {
        this.ref = ref;
        this.price = price;
        this.kms = kms;
        this.year = year;
        this.doors = doors;
        this.power = power;
        this.title = title;
        this.fuel = fuel;
        this.shift = shift;
        this.color = color;
        this.description = description;
        this.location = location;
        this.url = url;
        this.images = images.split(";");
    }


    protected Car(Parcel in) {
        ref = in.readInt();
        price = in.readInt();
        kms = in.readInt();
        year = in.readInt();
        doors = in.readInt();
        power = in.readInt();
        title = in.readString();
        fuel = in.readString();
        shift = in.readString();
        color = in.readString();
        description = in.readString();
        location = in.readString();
        url = in.readString();
        images = in.createStringArray();
    }

    public static final Creator<Car> CREATOR = new Creator<Car>() {
        @Override
        public Car createFromParcel(Parcel in) {
            return new Car(in);
        }

        @Override
        public Car[] newArray(int size) {
            return new Car[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(ref);
        parcel.writeInt(price);
        parcel.writeInt(kms);
        parcel.writeInt(year);
        parcel.writeInt(doors);
        parcel.writeInt(power);
        parcel.writeString(title);
        parcel.writeString(fuel);
        parcel.writeString(shift);
        parcel.writeString(color);
        parcel.writeString(description);
        parcel.writeString(location);
        parcel.writeString(url);
        parcel.writeStringArray(images);
    }
}
