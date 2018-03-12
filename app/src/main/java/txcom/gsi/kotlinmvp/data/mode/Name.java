package txcom.gsi.kotlinmvp.data.mode;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class Name implements Parcelable {
    @SerializedName("first")
    public abstract String first();
     @SerializedName("last")
    public abstract String last();

    public static Name create(String first, String last) {
        return new AutoValue_Name(first, last);
    }

    public static TypeAdapter<Name> typeAdapter(Gson gson) {
        return new AutoValue_Name.GsonTypeAdapter(gson);
    }

}
