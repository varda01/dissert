package com.example.firebase;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.widget.Toast;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class form extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button btn1;
    EditText eText;
    EditText eNumText;
    EditText eProduction;
    EditText eAreaHa;
    EditText eNewPlantation;
    RadioButton rad4;
    RadioButton rad5;
    RadioButton rad6;
    RadioButton rad7;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    // TODO: Rename and change types and number of parameters
    public static form newInstance(String param1, String param2) {
        form fragment = new form();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public form() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    String radiobut;
    String production;
    String areaHar;
    String newPlants;
    String month;
    String year;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_form, container, false);
        eText = view.findViewById(R.id.eTxt);
        eNumText = view.findViewById(R.id.eTxtNum);
        eProduction = view.findViewById(R.id.production2);
        eAreaHa = view.findViewById(R.id.areaHa);
        eNewPlantation = view.findViewById(R.id.edit);
        rad4 = view.findViewById(R.id.radBtn6);
        rad5 = view.findViewById(R.id.radBtn7);
        rad6 = view.findViewById(R.id.radBtn8);
        rad7 = view.findViewById(R.id.radBtn9);
        btn1 = view.findViewById(R.id.btn11);

        CollectionReference dataCollectionRef = db.collection("ProductionInfo");
        DocumentReference documentRef = dataCollectionRef.document();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(eText.getText().toString())
                        && !TextUtils.isEmpty(eNumText.getText().toString()) && !TextUtils.isEmpty(eProduction.getText().toString())
                        && !TextUtils.isEmpty(eAreaHa.getText().toString()) && !TextUtils.isEmpty(eNewPlantation.getText().toString())) {
                    month = eText.getText().toString().trim();
                    year = eNumText.getText().toString().trim();
                    production = eProduction.getText().toString().trim();
                    areaHar = eAreaHa.getText().toString().trim();
                    newPlants = eNewPlantation.getText().toString().trim();

                    if (rad4.isChecked()){
                            radiobut = rad4.getText().toString();
                    }

                    else if (rad5.isChecked()){
                                radiobut = rad5.getText().toString();
                    }
                    else if (rad6.isChecked()){
                            radiobut = rad6.getText().toString();
                    }
                    else {
                        radiobut = rad7.getText().toString();
                    }

                    saveData(month,year,radiobut,production,areaHar,newPlants);
                } else {
                    Toast.makeText(getActivity(), "Empty fields", Toast.LENGTH_SHORT).show();
                }


            }
        });
        return view;
    }
    private void saveData(String month, String year, String radiobut, String production, String areaHar, String newPlants) {
        DocumentReference docRef = db.collection("ProductionInfo").document();

        Map<String, Object> data = new HashMap<>();
        data.put("Year", year);
        data.put("Month", month);
        data.put("Region", radiobut);
        data.put("Production", production);
        data.put("Area Harvested", areaHar);
        data.put("New Plantation", newPlants);

        docRef.set(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getActivity(),"Data is successfully stored in database", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "Error: "+ e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
