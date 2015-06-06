package com.example.rahulpandey.databindingexample;

import android.content.Context;
import android.databinding.tool.util.L;
import android.util.Log;

import com.android.volley.toolbox.HurlStack;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.OkUrlFactory;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


public class OkHttpUrlStack extends HurlStack {
    private static final String TAG = "OkHttpUrlStack";
    private static final int CONNECT_TIMEOUT_MILLIS = 60 * 1000; // 30s
    private static final int READ_TIMEOUT_MILLIS = 85 * 1000; // 45s
    private final OkUrlFactory okUrlFactory;
    // Default maximum disk usage in bytes
    private static final int DEFAULT_DISK_USAGE_BYTES = 25 * 1024 * 1024;

    // Default cache folder name
    private static final String DEFAULT_CACHE_DIR = "photos";

    public OkHttpUrlStack(Context mContext) {
        this(new OkHttpClient(), mContext);
    }

    public OkHttpUrlStack(OkHttpClient client, Context mContext) {
        if (client == null) {
            throw new NullPointerException("Client must not be null.");
        }
        client.setConnectTimeout(CONNECT_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
        client.setReadTimeout(READ_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS);
        client.setCache(getCache(mContext));
        this.okUrlFactory = new OkUrlFactory(client);
    }

    @Override
    protected HttpURLConnection createConnection(URL url) throws IOException {
        return okUrlFactory.open(url);
    }

    private static Cache getCache(Context mContext) {
        return new Cache(getCacheDir(mContext), DEFAULT_DISK_USAGE_BYTES);
    }

    private static File getCacheDir(Context mContext) {
        // define cache folder
        File rootCache = mContext.getExternalCacheDir();
        if (rootCache == null) {
            L.w(TAG, "Can't find External Cache Dir, switching to application specific cache directory");
            rootCache = mContext.getCacheDir();
        }
        File cacheDir = new File(rootCache, DEFAULT_CACHE_DIR);
        if (cacheDir.mkdir()) {
            Log.w(TAG, "Directory Already exists");
        }
        File outputFile = null;
        try {
            outputFile = File.createTempFile(UUID.randomUUID().toString(), "tmp", cacheDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputFile;

    }
}
