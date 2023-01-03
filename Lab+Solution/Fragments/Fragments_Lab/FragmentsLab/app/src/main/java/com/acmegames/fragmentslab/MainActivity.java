package com.acmegames.fragmentslab;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.acmegames.fragmentslab.fragment.ContentRequestListener;
import com.acmegames.fragmentslab.fragment.PreviewFragment;
import com.acmegames.fragmentslab.fragment.UrlListFragment;


public class MainActivity extends AppCompatActivity
        implements ContentRequestListener {

    public static final int PREVIEW_REQUEST_CODE = 253;

    private PreviewFragment mFragPreview;
    private UrlListFragment mFragList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        mFragPreview = (PreviewFragment) fragmentManager.findFragmentById(R.id.fragPreview);
        mFragList = (UrlListFragment) fragmentManager.findFragmentById(R.id.fragList);
    }

    @Override
    public void contentChanged(String newContent) {
        //when in single pane mode mFragPreview will be null
        if(mFragPreview != null) {
            mFragPreview.onContentChangeRequest(newContent);
        }
        //if null then open webpreviewactivity
        else{
            Intent previewIntent = new Intent(MainActivity.this, PreviewActivity.class);
            previewIntent.putExtra(PreviewActivity.URL_BUNDLE_KEY, newContent);
            MainActivity.this.startActivityForResult(previewIntent, PREVIEW_REQUEST_CODE);
        }
    }

    @Override
    public void addNewContentItem(String item) {
        if(mFragList != null){
            mFragList.onItemAdded(item);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PREVIEW_REQUEST_CODE && resultCode == RESULT_OK &&
                data != null && data.hasExtra(PreviewActivity.URL_BUNDLE_KEY)){
            addNewContentItem(data.getStringExtra(PreviewActivity.URL_BUNDLE_KEY));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
