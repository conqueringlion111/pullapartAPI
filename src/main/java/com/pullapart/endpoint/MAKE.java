package com.pullapart.endpoint;

public enum MAKE {
    MAKE("/Make");
    public final String path;

    MAKE(String path) {
        this.path = path;
    }
}
