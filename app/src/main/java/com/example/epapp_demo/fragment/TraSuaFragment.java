package com.example.epapp_demo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.epapp_demo.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TraSuaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TraSuaFragment extends Fragment {

    public TraSuaFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.tra_sua_fragment, container, false);
        ImageView back = view.findViewById(R.id.backTrasua);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment newFragment = new HomeFragment();
                // consider using Java coding conventions (upper first char class names!!!)
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack
                transaction.replace(R.id.frame_layout, newFragment);
                transaction.addToBackStack(null);
                // Commit the transaction
                transaction.commit();

            }
        });

        return view;
    }
}