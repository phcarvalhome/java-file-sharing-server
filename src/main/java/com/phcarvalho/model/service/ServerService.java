package com.phcarvalho.model.service;

import com.phcarvalho.model.corba.FileMetadata;
import com.phcarvalho.model.corba.server.ServerServicePOA;

import java.util.*;
import java.util.stream.Collectors;

public class ServerService extends ServerServicePOA {

    private Map<String, List<FileMetadata>> fileMetadataMap;

    public ServerService() {
        fileMetadataMap = new HashMap<>();
    }

    @Override
    public void connectToServer(FileMetadata[] fileMetadataArray) {
        Arrays.stream(fileMetadataArray)
                .filter(fileMetadata -> isNewFileMetadata(fileMetadata))
                .forEach(fileMetadata -> fileMetadataMap.put(fileMetadata.user.id, addAndGetFileMetadataList(fileMetadata)));
    }

    private boolean isNewFileMetadata(FileMetadata fileMetadata) {
        return fileMetadataMap.values()
                .stream()
                .flatMap(fileMetadataList -> fileMetadataList.stream())
                .noneMatch(fileMetadataFromMap -> fileMetadataFromMap.user.id.equals(fileMetadata.user.id) &&
                        fileMetadataFromMap.name.equals(fileMetadata.name));
    }

    private List<FileMetadata> addAndGetFileMetadataList(FileMetadata fileMetadata) {
        List<FileMetadata> fileMetadataList = Optional.ofNullable(fileMetadataMap.get(fileMetadata.user.id))
                .orElse(new ArrayList<>());

        fileMetadataList.add(fileMetadata);

        return fileMetadataList;
    }

    @Override
    public FileMetadata[] search(String text) {
        return fileMetadataMap.values()
                .stream()
                .flatMap(fileMetadataList -> fileMetadataList.stream())
                .filter(fileMetadata -> fileMetadata.name.contains(text))
                .collect(Collectors.toList()).toArray(new FileMetadata[]{});
    }
}
