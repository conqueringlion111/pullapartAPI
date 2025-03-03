package com.pullapart.endpoint;

public enum LOCATION {
    LOCATION("/Location");

    public final String path;

    LOCATION(String path) {
        this.path = path;
    }
}
