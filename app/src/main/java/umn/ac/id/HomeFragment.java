package umn.ac.id;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    ImageView korea1, korea2, jepang1, jepang2;

    private ArrayList<MovieItem> movieItems = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new MovieAdapter(movieItems, getActivity()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        movieItems.add(new MovieItem(R.drawable.mv1, "The Penthouse", "0", "0"));
        movieItems.add(new MovieItem(R.drawable.mv2, "Vincenzo", "1", "0"));
        movieItems.add(new MovieItem(R.drawable.jepang1, "Demon Slayer", "2", "0"));
        movieItems.add(new MovieItem(R.drawable.jepang2, "Dr. Stone", "3", "0"));


        return root;
    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//
//        korea1 = view.findViewById(R.id.korea1);
////        korea2 = view.findViewById(R.id.vidkorea2);
////        jepang1 = view.findViewById(R.id.jepang1);
////        jepang2 = view.findViewById(R.id.jepang2);
//
//        korea1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), Korea1Activity.class);
//                startActivity(intent);
//            }
//        });
//
//        korea2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(),Korea2Activity.class);
//                startActivity(intent);
//            }
//        });
//
////        jepang1.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Intent intent = new Intent(getActivity(),Jepang1Activity.class);
////                startActivity(intent);
////            }
////        });
//
////        jepang2.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Intent intent = new Intent(getActivity(),Jepang2Activity.class);
////                startActivity(intent);
////            }
////        });
//
//    }
//

    }
