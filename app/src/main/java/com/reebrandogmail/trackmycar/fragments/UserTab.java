package com.reebrandogmail.trackmycar.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.reebrandogmail.trackmycar.ClickListener;
import com.reebrandogmail.trackmycar.R;
import com.reebrandogmail.trackmycar.Util.DBHandler;
import com.reebrandogmail.trackmycar.adapter.DividerItemDecoration;
import com.reebrandogmail.trackmycar.adapter.RecyclerTouchListener;
import com.reebrandogmail.trackmycar.adapter.UserAdapter;
import com.reebrandogmail.trackmycar.model.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by renan.brando on 18/07/2017.
 */

public class UserTab extends Fragment {

    private List<User> userList = new ArrayList<>();
    private RecyclerView recyclerView;
    private UserAdapter mAdapter;
    private DBHandler db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.activity_user_tab, container, false);
        ButterKnife.bind(this, view);

        db = new DBHandler(view.getContext());

        recyclerView = (RecyclerView) view.findViewById(R.id.rvProfile);

        mAdapter = new UserAdapter(getActivity(), userList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);

        prepareUsersData();

        return view;
    }

    private void prepareUsersData() {
        List<User> users = db.getAllUsers();
        for (User user : users){
            userList.add(user);
        }
        mAdapter.notifyDataSetChanged();
    }

    // Generic method for swapping fragments in the activity with extra value
    private void swapFragments(int activity, Fragment fragment){
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(activity, fragment);
        transaction.disallowAddToBackStack();
        transaction.commit();
    }

    @OnClick(R.id.fbAddUser)
    public void add(){
        swapFragments(R.id.fragment_main, new AddUserFragment());
    }

}
