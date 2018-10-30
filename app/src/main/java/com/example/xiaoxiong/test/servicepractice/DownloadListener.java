package com.example.xiaoxiong.test.servicepractice;

public interface DownloadListener {

    void onProgress(int progress);
    void onSuccess();
    void onFailed();
    void onPaused();
    void onCanceled();

}
