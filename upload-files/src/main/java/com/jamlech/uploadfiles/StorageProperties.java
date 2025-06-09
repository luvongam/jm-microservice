package com.jamlech.uploadfiles;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

/**
 * Configuration properties for file storage settings.
 */
@ConfigurationProperties("storage")
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String location;

    /**
     * Default constructor
     */
    public StorageProperties() {
        this.location = "upload-dir";
    }

    /**
     * Constructor with location parameter
     * @param location the storage location
     */
    public StorageProperties(@DefaultValue("upload-dir") String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location != null ? location : "upload-dir";
    }

    @Override
    public String toString() {
        return "StorageProperties{location='" + location + "'}";
    }
}