package com.acmegames.fragmentslab;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.acmegames.fragmentslab.fragment.ContentRequestListener;
import com.acmegames.fragmentslab.fragment.PreviewFragment;


public class PreviewActivity extends AppCompatActivity implements ContentRequestListener{
    public static final String URL_BUNDLE_KEY = "url";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        PreviewFragment previewFragment = (PreviewFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragPreview);
        String url = getIntent().getExtras().getString(URL_BUNDLE_KEY);
        previewFragment.onContentChangeRequest(url);
    }

    @Override
    public void contentChanged(String newContent) {
        //do nothing this callback won't be called in this situation
    }

    @Override
    public void addNewContentItem(String item) {
        //return result to main activity
        Intent urlIntent = new Intent();
        urlIntent.putExtra(URL_BUNDLE_KEY, item);
        setResult(RESULT_OK, urlIntent);
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_preview, menu);
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
