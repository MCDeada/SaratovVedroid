package com.example.saratov;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.support.v4.content.ContextCompat;


public class FragmentMenuNavig extends Fragment {

    String nameclass;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_navig, container, false);

        LinearLayout profile = view.findViewById(R.id.profile);
        LinearLayout map = view.findViewById(R.id.map);
        LinearLayout news = view.findViewById(R.id.news);
        LinearLayout report = view.findViewById(R.id.report);
        LinearLayout menu = view.findViewById(R.id.menu);

        ImageView profileImage = view.findViewById(R.id.reportImage);
        ImageView mapImage = view.findViewById(R.id.mapImage);
        ImageView newsImage = view.findViewById(R.id.newsImage);
        ImageView reportImage = view.findViewById(R.id.reportImage);
        ImageView menuImage = view.findViewById(R.id.menuImage);


        nameclass=getActivity().getLocalClassName();

        switch (nameclass) {
            case "SaratovMap":
                mapImage.setColorFilter(ContextCompat.getColor(getContext(), R.color.blue));
                break;
            case "SaratovReport1":
                reportImage.setColorFilter(ContextCompat.getColor(getContext(), R.color.blue));
                break;
            case "SaratovReport2":
                reportImage.setColorFilter(ContextCompat.getColor(getContext(), R.color.blue));
                break;
            case "SaratovReport3":
                reportImage.setColorFilter(ContextCompat.getColor(getContext(), R.color.blue));
                break;
            case "SaratovReport4":
                reportImage.setColorFilter(ContextCompat.getColor(getContext(), R.color.blue));
                break;
            case "SaratovReport5":
                reportImage.setColorFilter(ContextCompat.getColor(getContext(), R.color.blue));
                break;
            case "SaratovReport6":
                reportImage.setColorFilter(ContextCompat.getColor(getContext(), R.color.blue));
                break;
            case "SaratovReport7":
                reportImage.setColorFilter(ContextCompat.getColor(getContext(), R.color.blue));
                break;
            case "SaratovMenu":
                menuImage.setColorFilter(ContextCompat.getColor(getContext(), R.color.blue));
                break;
        }


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nameclass!="SaratovMap") {
                    Intent intent = new Intent(getActivity(), SaratovMap.class);
                    startActivity(intent);
                }
            }
        });

        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nameclass!="SaratovReport1" || nameclass!="SaratovReport2" ||nameclass!="SaratovReport3"||nameclass!="SaratovReport4"||nameclass!="SaratovReport5" ||nameclass!="SaratovReport6" ||nameclass!="SaratovReport7") {
                    Intent intent = new Intent(getActivity(), SaratovReport1.class);
                    startActivity(intent);
                }
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nameclass!="SaratovMenu") {
                    Intent intent = new Intent(getActivity(), SaratovMenu.class);
                    startActivity(intent);
                }
            }
        });

        return view;
    }


}
