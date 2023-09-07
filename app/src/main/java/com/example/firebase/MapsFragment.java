package com.example.firebase;

import static android.content.ContentValues.TAG;

import androidx.annotation.ContentView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLngBounds;
import com.google.firebase.firestore.*;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MapsFragment extends Fragment {
    private LatLng mauritiusLatLng = new LatLng(-20.348404, 57.552152);
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference colRef = db.collection("Maurice");
    private OnMapReadyCallback callback = new OnMapReadyCallback() {
        private GoogleMap mMap;

        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {

            mMap = googleMap;
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mauritiusLatLng, 10));

            LatLng m1 = new LatLng(-20.4683058, 57.6200287);
            mMap.addMarker(new MarkerOptions()
                    .position(m1)
                    .title("L`escalier")
                    .snippet("Altitude : 1852")
            );

            LatLng m2 = new LatLng(-20.460198, 57.632718);
            mMap.addMarker(new MarkerOptions()
                    .position(m2)
                    .title("Plein Bois")
                    .snippet("Altitude : 1718")
            );
            LatLng m3 = new LatLng(-20.491662, 57.557877);
            mMap.addMarker(new MarkerOptions()
                    .position(m3)
                    .title("Riv.des Anguilles")
                    .snippet("Altitude : 1793")
            );
            LatLng m4 = new LatLng(-20.514085, 57.510099);
            mMap.addMarker(new MarkerOptions()
                    .position(m4)
                    .title("Surinam")
                    .snippet("Altitude : 1606")
            );
            LatLng m5 = new LatLng(-20.499327, 57.463029);
            mMap.addMarker(new MarkerOptions()
                    .position(m5)
                    .title("Chemin Grenier")
                    .snippet("Altitude : 1440")
            );
            LatLng m6 = new LatLng(-20.392952, 57.587545);
            mMap.addMarker(new MarkerOptions()
                    .position(m6)
                    .title("Union Park")
                    .snippet("Altitude : 1740")
            );
            LatLng m7 = new LatLng(-20.405447, 57.609797);
            mMap.addMarker(new MarkerOptions()
                    .position(m7)
                    .title("Rose Belle")
                    .snippet("Altitude : 1641")
            );
            LatLng m8 = new LatLng(-20.369001, 57.615374);
            mMap.addMarker(new MarkerOptions()
                    .position(m8)
                    .title("Cluny")
                    .snippet("Altitude : 1716")
            );
            LatLng m9 = new LatLng(-20.343462, 57.547699);
            mMap.addMarker(new MarkerOptions()
                    .position(m9)
                    .title("16eme mille")
                    .snippet("Altitude : 1743")
            );
            LatLng m10 = new LatLng(-20.279151, 57.422802);
            mMap.addMarker(new MarkerOptions()
                    .position(m10)
                    .title("Beau Songes")
                    .snippet("Altitude : 1618")
            );
            LatLng m11 = new LatLng(-20.260059, 57.751277);
            mMap.addMarker(new MarkerOptions()
                    .position(m11)
                    .title("Bel Air")
                    .snippet("Altitude : 1743")
            );
            LatLng m12 = new LatLng(-20.219355, 57.744848);
            mMap.addMarker(new MarkerOptions()
                    .position(m12)
                    .title("Camp Itier")
                    .snippet("Altitude : 1740")
            );
            LatLng m13 = new LatLng(-20.198873, 57.759417);
            mMap.addMarker(new MarkerOptions()
                    .position(m13)
                    .title("Mare la Chaux")
                    .snippet("Altitude : 1410")
            );
            LatLng m14 = new LatLng(-20.235197, 57.800866);
            mMap.addMarker(new MarkerOptions()
                    .position(m14)
                    .title("Trou d`Eau Douce")
                    .snippet("Altitude : 1174")
            );
            LatLng m15 = new LatLng(-20.127674, 57.538478);
            mMap.addMarker(new MarkerOptions()
                    .position(m15)
                    .title("Terre Rouge")
                    .snippet("Altitude : 1430")
            );
            LatLng m16 = new LatLng(-20.104135, 57.580315);
            mMap.addMarker(new MarkerOptions()
                    .position(m16)
                    .title("Pamplemousses")
                    .snippet("Altitude : 1464")
            );
            LatLng m17 = new LatLng(-20.056673, 57.583038);
            mMap.addMarker(new MarkerOptions()
                    .position(m17)
                    .title("Fond Du Sac")
                    .snippet("Altitude : 1462")
            );
            LatLng m18 = new LatLng(-20.020673, 57.626732);
            mMap.addMarker(new MarkerOptions()
                    .position(m18)
                    .title("Petit Rafrey")
                    .snippet("Altitude : 1408")
            );
            LatLng m19 = new LatLng(-19.989983, 57.612412);
            mMap.addMarker(new MarkerOptions()
                    .position(m19)
                    .title("Cap Malheureux")
                    .snippet("Altitude : 1246")
            );
            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(@NonNull Marker marker) {
                    db.collection("Maurice").get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                //colRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                String title = marker.getTitle();
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        boolean foundTitle = false;
                                        //ArrayList<String> documentIds = new ArrayList<>();
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            Log.e("varda", document.getId() + " => " + document.getData());
                                            // Log.e("varda","is good");
                                           // documentIds.add(document.getId());

                                            //Log.e("varda","is good");
                                            //for (String docId : documentIds) {
                                                if (document.getId().equals(title)) {
                                                    foundTitle= true;
                                                    Log.e("varda", "docid" + title);
                                                    double[] dataArray = new double[9];

                                                    Double data = document.getDouble("Altitude");
                                                    Double data1 = document.getDouble("LotArea");
                                                    Double data2 = document.getDouble("CycleDuration");
                                                    Double data3 = document.getDouble("Density");
                                                    Double data4 = document.getDouble("Irrigation");
                                                    //Log.e("varda","is good"+ data4);
                                                    Double data5 = document.getDouble("Rainfall");
                                                    Double data6 = document.getDouble("TotalN");
                                                    // Log.e("varda","is good"+ data6);
                                                    Double data7 = document.getDouble("P205");
                                                    // Log.e("varda","is good"+ data7);
                                                    Double data8 = document.getDouble("K2O");
                                                    // Log.e("varda","is good"+ data8);
                                                    //Log.e("varda", "is good", task.getException());
                                                    try {
                                                        dataArray[0] = data;
                                                        Log.e("varda", "is good " + dataArray[0]);
                                                        dataArray[1] = data1;
                                                        dataArray[2] = data2;
                                                        dataArray[3] = data3;
                                                        dataArray[4] = data4;
                                                        dataArray[5] = data5;
                                                        dataArray[6] = data6;
                                                        dataArray[7] = data7;
                                                        dataArray[8] = data8;
                                                        //Log.e("varda", "is good", task.getException());
                                                        sendPredictionRequest(dataArray);
                                                    } catch (Exception e) {
                                                        Log.e("varda", "problem " + e);
                                                    }
                                            }
                                        }
                                        if(!foundTitle){
                                            Log.e("varda", "Title not found in Firestore");
                                        }
                                        }else{
                                        Log.e("varda", "Error getting documents: ", task.getException());
                                    }
                                }
                                });
                                return false;
                            }
                });
            }
        };
        @Nullable
        @Override
            public View onCreateView (@NonNull LayoutInflater inflater,
                    @Nullable ViewGroup container,
                    @Nullable Bundle savedInstanceState){
                return inflater.inflate(R.layout.fragment_maps, container, false);
            }

            @Override
            public void onViewCreated (@NonNull View view, @Nullable Bundle savedInstanceState){
                super.onViewCreated(view, savedInstanceState);
                SupportMapFragment mapFragment =
                        (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
                if (mapFragment != null) {
                    mapFragment.getMapAsync(callback);
                }
            }
            private void sendPredictionRequest (double[] dataArray) {
            try{
                OkHttpClient client = new OkHttpClient().newBuilder().build();
                MediaType mediaType = MediaType.parse("application/json; charset=utf-8");

                JSONArray jsonArray = new JSONArray();
                for(double value: dataArray){
                    jsonArray.put(value);
                }
                JSONObject jsonData = new JSONObject();
                jsonData.put("params",jsonArray);

                RequestBody body = RequestBody.create(jsonData.toString(), mediaType);
                Request request = new Request.Builder()
                        .url("http://10.0.2.2:3000/predict")
                        .method("POST", body)
                        .addHeader("Content-Type", "application/json")
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        Log.e("varda","Request failed: " + e.getMessage());
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        if(response.isSuccessful()) {
                            String prediction = response.body().string();
                            getActivity().runOnUiThread(() -> {
                                Toast.makeText(getActivity(), "Prediction is: " + prediction, Toast.LENGTH_LONG).show();
                            });
                        }
                    }
                });
                } catch (Exception ex) {
                    Log.e("varda", "error " + ex);
                }
            }
};