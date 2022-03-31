package org.skyfaced.mvp.model;

import java.util.List;

public class FileDto {
    private final List<String> files;

    public FileDto(List<String> files) {
        this.files = files;
    }

    public List<String> getFiles() {
        return files;
    }
}
