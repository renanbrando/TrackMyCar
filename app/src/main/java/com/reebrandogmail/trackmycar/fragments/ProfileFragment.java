package com.reebrandogmail.trackmycar.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.reebrandogmail.trackmycar.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by renan.brando on 06/07/2017.
 */

public class ProfileFragment extends Fragment {

    private FragmentTabHost mTabHost;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.activity_profile_fragment, container, false);
        ButterKnife.bind(this, view);

        mTabHost = (FragmentTabHost)rootView.findViewById(android.R.id.tabhost);
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("fragmentb").setIndicator("Fragment B"),
                FragmentB.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("fragmentc").setIndicator("Fragment C"),
                FragmentC.class, null);

        return view;
    }

}
