package edu.detectortroyano.com.tipcalc.fragment;

import edu.detectortroyano.com.tipcalc.models.TipRecord;

/**
 * Created by detectortroyano on 11/06/2016.
 */
public interface TipHistoryListFragmentListener {
    void addToList(TipRecord tipRecord);

    void clearList();
}