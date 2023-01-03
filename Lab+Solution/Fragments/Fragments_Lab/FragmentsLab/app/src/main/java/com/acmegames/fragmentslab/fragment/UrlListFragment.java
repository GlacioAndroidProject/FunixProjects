package com.acmegames.fragmentslab.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.ListFragment;

import com.acmegames.fragmentslab.R;

import java.util.ArrayList;
import java.util.Arrays;


public class UrlListFragment extends ListFragment {

    private ContentRequestListener mContentListner;
    private ArrayList<String> mItemList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mItemList = new ArrayList<String>
                (Arrays.asList(getResources().getStringArray(R.array.url_string_array)));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1,
                mItemList);
        setListAdapter(adapter);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContentListner = (ContentRequestListener) activity;
    }



    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        mContentListner.contentChanged((String) l.getAdapter().getItem(position));
    }


    public void onItemAdded(String newUrl){
        mItemList.add(newUrl);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1,
                mItemList);
        setListAdapter(adapter);
    }
}
