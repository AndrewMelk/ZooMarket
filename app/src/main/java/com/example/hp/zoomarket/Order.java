package com.example.hp.zoomarket;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.EditText;

import java.util.Date;

/**
 * Created by HP on 29.02.2016.
 */
public class Order  implements Parcelable{
    private String name;
    private int number;
    private String itemName;
    private int cost;

    public Order() {
    }

    public Order(String name, int number, String itemName, int cost) {
        this.name = name;
        this.number = number;
        this.itemName = itemName;
        this.cost = cost;
    }

    protected Order(Parcel in) {
        name = in.readString();
        number = in.readInt();
        itemName = in.readString();
        cost = in.readInt();
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Order{" +
                "name='" + name + '\'' +
                ", number=" + number +
                ", itemName='" + itemName + '\'' +
                ", cost=" + cost +
                '}';
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(number);
        dest.writeString(itemName);
        dest.writeInt(cost);


    }
}
