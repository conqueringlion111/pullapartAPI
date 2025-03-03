package com.pullapart.endpoint;

public enum SEARCH {

    VEHICLE("/Vehicle"),
    SEARCH("/Search");

    public final String path;

    SEARCH(String path) {
        this.path = path;
    }

}
