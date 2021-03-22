package com.gbcom.gwifi.functions.download;

/* renamed from: com.gbcom.gwifi.functions.download.d */
public interface IDownloadFile {
    Long getDownsize();

    Long getFileTotalSize();

    String getLocalFile();

    String getName();

    Integer getStateId();

    String getTempFile();

    String getUrl();
}
