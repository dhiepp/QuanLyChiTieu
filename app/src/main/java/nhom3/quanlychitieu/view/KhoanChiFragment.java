package nhom3.quanlychitieu.view;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nhom3.quanlychitieu.R;

public class KhoanChiFragment extends Fragment {
    private Context context;

    public KhoanChiFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_khoan_chi, container, false);
        context = root.getContext();
        return root;
    }

}