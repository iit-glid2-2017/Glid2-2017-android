package com.iit.glid2017.app;
import com.google.gson.annotations.SerializedName;

public class JokeWrapper {

    @SerializedName("type")
    private String mType;
    @SerializedName("value")
    private Value mValue;


    public String getJoke() {
        return mValue.getJoke();
    }
    public long getJokeId() {
        return mValue.getJokeId();
    }


    class Value {

        @SerializedName("id")
        private long mId;

        @SerializedName("joke")
        private String mJoke;

        String getJoke(){
            return mJoke;
        }

        long getJokeId(){
            return mId;
        }

    }

}