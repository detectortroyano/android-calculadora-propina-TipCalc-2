package edu.detectortroyano.com.tipcalc.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import edu.detectortroyano.com.tipcalc.R;
import edu.detectortroyano.com.tipcalc.activity.TipDetailActivity;
import edu.detectortroyano.com.tipcalc.adapters.OnItemClickListener;
import edu.detectortroyano.com.tipcalc.adapters.TipAdapter;
import edu.detectortroyano.com.tipcalc.models.TipRecord;


/**
 * A simple {@link Fragment} subclass.
 */
public class TipHistoryListFragment extends Fragment implements TipHistoryListFragmentListener, OnItemClickListener{
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    TipAdapter adapter;

    public TipHistoryListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tip_history_list, container, false);
        ButterKnife.bind(this,view);
        initAdapter();
        initRecyclerView();
        return view;
    }

    private void initAdapter(){
        if(adapter == null){
            adapter = new TipAdapter(getActivity().getApplicationContext(),this);
        }
    }

    private void initRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }
    /*@Override
    public void action(String str) {
        Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
    }*/

    @Override
    public void addToList(TipRecord tipRecord) {
        adapter.add(tipRecord);
    }

    @Override
    public void clearList() {
        adapter.clear();
    }

    @Override
    public void onItemClick(TipRecord tipRecord) {
        //Toast.makeText(getActivity(), tipRecord.getDateFormatted(), Toast.LENGTH_SHORT).show();
        //Log.e(this.getClass().getName(), tipRecord.getDateFormatted());
        Intent intent = new Intent(getActivity(), TipDetailActivity.class);
        intent.putExtra(TipDetailActivity.TIP_KEY, tipRecord.getTip());
        intent.putExtra(TipDetailActivity.BILL_TOTAL_KEY, tipRecord.getBill());
        intent.putExtra(TipDetailActivity.DATE_KEY, tipRecord.getDateFormatted());
        startActivity(intent);
    }
}
