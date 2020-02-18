package com.muslimadel2018.marvel.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Data {
    @SerializedName("offset")
    @Expose
    int offset;
    @SerializedName("limit")
    @Expose
    int limit;
    @SerializedName("total")
    @Expose
    int total;
    @SerializedName("count")
    @Expose
    int count;
    @SerializedName("results")
    @Expose
    ArrayList<Characters> results;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public ArrayList<Characters> getResults() {
        return results;
    }

    public void setResults(ArrayList<Characters> results) {
        this.results = results;
    }
}
