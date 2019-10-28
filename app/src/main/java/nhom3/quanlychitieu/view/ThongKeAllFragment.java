package nhom3.quanlychitieu.view;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nhom3.quanlychitieu.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThongKeAllFragment extends Fragment {


    public ThongKeAllFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_thong_ke_all, container, false);
    }

}
