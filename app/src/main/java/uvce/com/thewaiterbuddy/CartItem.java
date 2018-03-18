package uvce.com.thewaiterbuddy;

import android.os.Parcel;
import android.os.Parcelable;

public class CartItem extends FoodItem implements Parcelable {
    public static final Creator<CartItem> CREATOR = new Creator<CartItem>() {
        @Override
        public CartItem createFromParcel(Parcel in) {
            return new CartItem(in);
        }

        @Override
        public CartItem[] newArray(int size) {
            return new CartItem[size];
        }
    };
    static final String TYPE = "cartitem";
    private int quantity, id;
    private boolean selected;

    CartItem(FoodItem foodItem, int _quantity, int _id) {
        super(foodItem.getName(), foodItem.getCost());
        quantity = _quantity;
        id = _id;
        selected = false;
    }

    private CartItem(Parcel in) {
        super(in.readString(), in.readDouble());
        quantity = in.readInt();
    }

    int getQuantity() {
        return quantity;
    }

    void setQuantity(int _quantity) {
        quantity = _quantity;
    }

    public int getId() {
        return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(getName());
        parcel.writeDouble(getCost());
        parcel.writeInt(getQuantity());
    }
}
