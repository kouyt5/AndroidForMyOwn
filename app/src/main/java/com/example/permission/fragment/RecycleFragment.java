package com.example.permission.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.permission.R;
import com.example.permission.adapter.RecyclerdAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RecycleFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RecycleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecycleFragment extends Fragment {

    private ArrayList<String> titles;
    private RecyclerView recyclerView;
    private OnFragmentInteractionListener mListener;

    public RecycleFragment() {
        // Required empty public constructor
    }

    public static RecycleFragment newInstance() {
        RecycleFragment fragment = new RecycleFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTitle();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_recycle, container, false);
        recyclerView=(RecyclerView)view.findViewById(R.id.recycle_fragment);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerdAdapter recyclerdAdapter= new RecyclerdAdapter(getContext(),titles);
        recyclerView.setAdapter(recyclerdAdapter);

        recyclerdAdapter.setOnItemClickListenser(new RecyclerdAdapter.OnItemClickListenser() {
            @Override
            public void OnItemClick(int position) {
                Toast.makeText(getContext(),"点击"+position,Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void initTitle(){
        titles=new ArrayList<String>();
       for(int i=0;i<10;i++){
           titles.add("chen"+i);
       }
    }
}
