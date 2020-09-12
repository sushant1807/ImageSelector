package com.sushant.android.imageselector.models;

import java.util.List;

public class PixabayImageList {

    private int total;
    private int totalHits;
    private List<ImageModel> hits;

    public PixabayImageList(int total, int totalHits, List<ImageModel> hits) {
        this.total = total;
        this.totalHits = totalHits;
        this.hits = hits;
    }

    public int getTotal() {
        return total;
    }

    public int getTotalHits() {
        return totalHits;
    }

    public List<ImageModel> getHits() {
        return hits;
    }

    public int getTotalOfPages() {
        return (int) Math.ceil(total / 20.0);
    }
}