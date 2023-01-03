package com.acmegames.fragmentslab.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageButton;

import com.acmegames.fragmentslab.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PreviewFragment extends androidx.fragment.app.Fragment {

    private WebView mPreview;
    private EditText mNewUrl;
    private ContentRequestListener mRequestListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_preview, container, false);
        mPreview = (WebView) view.findViewById(R.id.wvPreview);
        mPreview.getSettings().setJavaScriptEnabled(true);
        mPreview.setWebViewClient(new WebViewClient());

        mNewUrl = (EditText) view.findViewById(R.id.etNewUrl);
        ImageButton addNewUrl = (ImageButton) view.findViewById(R.id.btnAdd);
        addNewUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRequestListener.addNewContentItem(mNewUrl.getText().toString());
            }
        });
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
            mRequestListener = (ContentRequestListener) activity;
    }

    public void onContentChangeRequest(String newUrl){
        mPreview.loadUrl(newUrl);
    }
}
