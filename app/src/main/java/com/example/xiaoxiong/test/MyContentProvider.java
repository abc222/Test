package com.example.xiaoxiong.test;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {

    public static final int TABLE1_DIR = 0;
    public static final int TABLE1_ITEM = 1;
    public static final int TABLE2_DIR = 2;
    public static final int TABLE2_ITEM = 3;

    private static UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.example.xiaoxiong.test.mycontetnprovider","table1",TABLE1_DIR);
        uriMatcher.addURI("com.example.xiaoxiong.test.mycontetnprovider","table1/#", TABLE1_ITEM);
        uriMatcher.addURI("com.example.xiaoxiong.test.mycontetnprovider","table2", TABLE2_DIR);
        uriMatcher.addURI("com.example.xiaoxiong.test.mycontetnprovider","table2/#", TABLE2_ITEM);
    }

    public MyContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        switch (uriMatcher.match(uri)) {
            case TABLE1_DIR:
                //查询table1表中的所有数据
                break;
            case TABLE1_ITEM:
                //查询table1表中的单条数据
                break;
            case TABLE2_DIR:
                //查询table2表中的所有数据
                break;
            case TABLE2_ITEM:
                //查询table2表中的单条数据
                break;
                default:
        }
        // TODO: Implement this to handle query requests from clients.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
