package com.acmegames.fragmentslab.fragment;

/**
 * Created by michael hantler on 1/11/2015.
 */
public interface ContentRequestListener {
    public void contentChanged(String newContent);
    public void addNewContentItem(String item);
}
