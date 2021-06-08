package com.shiftdev.freshstartec;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.shiftdev.freshstartec.databinding.FragmentServicesBinding;

public class ServicesFragment extends Fragment {
     FragmentServicesBinding binding;

     public ServicesFragment() {

     }

     @Override
     public void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
     }

     @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
          return inflater.inflate(R.layout.fragment_services, container, false);
     }
}
