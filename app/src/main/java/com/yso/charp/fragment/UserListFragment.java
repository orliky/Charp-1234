package com.yso.charp.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yso.charp.Interface.ChatItemClickListener;
import com.yso.charp.activity.MainActivity;
import com.yso.charp.adapter.UserListAdapter;
import com.yso.charp.R;
import com.yso.charp.mannager.PersistenceManager;
import com.yso.charp.model.User;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint ("ValidFragment")
public class UserListFragment extends Fragment implements ChatItemClickListener
{

    private RecyclerView mRecyclerView;
    private HashMap<String, User> userList = new HashMap<>();
    private UserListAdapter mAdapter;


    public UserListFragment()
    {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_user_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        userList = PersistenceManager.getInstance().getUsersMap();

        initAdapter(view);

        mAdapter.setClickListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(), new LinearLayoutManager(getContext()).getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        loadUserList();
    }

    private void initAdapter(View view)
    {

        mAdapter = new UserListAdapter(getContext(), userList);
        mRecyclerView = view.findViewById(R.id.list_of_users);

    }

    private void loadUserList()
    {
        FirebaseDatabase.getInstance().getReference().child("Users").addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                for (DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    User user = snapshot.getValue(User.class);
                    if (!user.getPhone().equals(FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber()))
                    {
                        userList.put(user.getPhone(), user);
                    }
                }
                PersistenceManager.getInstance().setUsersMap(userList);
                mAdapter.setItems(userList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });
    }

    @Override
    public void onItemClick(String key)
    {
        Bundle bundle = new Bundle();
        bundle.putString("user_phone", key);

        ((MainActivity) getActivity()).addChatFragment(bundle);
    }
}