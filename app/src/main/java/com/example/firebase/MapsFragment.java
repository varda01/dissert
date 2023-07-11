package com.example.firebase;

import static android.content.ContentValues.TAG;

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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
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

import java.util.ArrayList;
import java.util.List;

public class MapsFragment extends Fragment {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference colRef = db.collection("Maurice");
    private OnMapReadyCallback callback = new OnMapReadyCallback() {
        private GoogleMap mMap;
        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            LatLng m1 = new LatLng(	-20.4683058, 57.6200287);
            mMap.addMarker(new MarkerOptions()
                    .position(m1)
                    .title("L`escalier")
                    .snippet("Altitude : 1852")
            );

            LatLng m2 = new LatLng(	-20.460198, 57.632718);
            mMap.addMarker(new MarkerOptions()
                    .position(m2)
                    .title("Plein Bois")
                    .snippet("owner")
            );
            LatLng m3 = new LatLng(	-20.491662, 57.557877);
            mMap.addMarker(new MarkerOptions()
                    .position(m3)
                    .title("Riv.des Anguilles")
                    .snippet("owner")
            );
            LatLng m4 = new LatLng(	-20.514085, 57.510099);
            mMap.addMarker(new MarkerOptions()
                    .position(m4)
                    .title("Surinam")
                    .snippet("owner")
            );
            LatLng m5 = new LatLng(	-20.499327,  57.463029);
            mMap.addMarker(new MarkerOptions()
                    .position(m5)
                    .title("Chemin Grenier")
                    .snippet("owner")
            );
            LatLng m6 = new LatLng(	-20.392952,  57.587545);
            mMap.addMarker(new MarkerOptions()
                    .position(m6)
                    .title("Union Park")
                    .snippet("owner")
            );
            LatLng m7 = new LatLng(	-20.405447,  57.609797);
            mMap.addMarker(new MarkerOptions()
                    .position(m7)
                    .title("Rose Belle")
                    .snippet("owner")
            );
            LatLng m8 = new LatLng(	-20.369001,  57.615374);
            mMap.addMarker(new MarkerOptions()
                    .position(m8)
                    .title("Cluny")
                    .snippet("owner")
            );
            LatLng m9 = new LatLng(	-20.343462,  57.547699);
            mMap.addMarker(new MarkerOptions()
                    .position(m9)
                    .title("16eme mille")
                    .snippet("owner")
            );
            LatLng m10 = new LatLng(-20.279151,  57.422802);
            mMap.addMarker(new MarkerOptions()
                    .position(m10)
                    .title("Beau Songes")
                    .snippet("owner")
            );
            LatLng m11 = new LatLng(-20.260059,  57.751277);
            mMap.addMarker(new MarkerOptions()
                    .position(m11)
                    .title("Bel Air")
                    .snippet("owner")
            );
            LatLng m12 = new LatLng(-20.219355,  57.744848);
            mMap.addMarker(new MarkerOptions()
                    .position(m12)
                    .title("Camp Itier")
                    .snippet("owner")
            );
            LatLng m13 = new LatLng(-20.198873,  57.759417);
            mMap.addMarker(new MarkerOptions()
                    .position(m13)
                    .title("Mare la Chaux")
                    .snippet("owner")
            );
            LatLng m14 = new LatLng(-20.235197,  57.800866);
            mMap.addMarker(new MarkerOptions()
                    .position(m14)
                    .title("Trou d`Eau Douce")
                    .snippet("owner")
            );
            LatLng m15 = new LatLng(-20.127674,  57.538478);
            mMap.addMarker(new MarkerOptions()
                    .position(m15)
                    .title("Terre Rouge")
                    .snippet("owner")
            );
            LatLng m16 = new LatLng(-20.104135,  57.580315);
            mMap.addMarker(new MarkerOptions()
                    .position(m16)
                    .title("Pamplemousses")
                    .snippet("owner")
            );
            LatLng m17 = new LatLng(-20.056673,  57.583038);
            mMap.addMarker(new MarkerOptions()
                    .position(m17)
                    .title("Fond Du Sac")
                    .snippet("owner")
            );
            LatLng m18 = new LatLng(-20.020673,  57.626732);
            mMap.addMarker(new MarkerOptions()
                    .position(m18)
                    .title("Petit Rafrey")
                    .snippet("owner")
            );
            LatLng m19 = new LatLng(-19.989983, 57.612412);
            mMap.addMarker(new MarkerOptions()
                    .position(m19)
                    .title("Cap Malheureux")
                    .snippet("owner")
            );
            mMap.moveCamera(CameraUpdateFactory.newLatLng(m1));
            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(@NonNull Marker marker) {
                    colRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                String title = marker.getTitle();
                                Query query = colRef.whereEqualTo(FieldPath.documentId(), title);
                                if ((query.get()).isSuccessful()) {
                                    double[] dataArray = new double[9];
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        Double data = document.getDouble("Altitude");
                                        Double data1 = document.getDouble("LotArea");
                                        Double data2 = document.getDouble("CycleDuration");
                                        Double data3 = document.getDouble("Density");
                                        Double data4 = document.getDouble("Irrigation");
                                        Double data5 = document.getDouble("Rainfall");
                                        Double data6 = document.getDouble("TotalN.kg");
                                        Double data7 = document.getDouble("P205.kg");
                                        Double data8 = document.getDouble("K2O.kg");

                                        dataArray[0]= data;
                                        dataArray[1]= data1;
                                        dataArray[2]= data2;
                                        dataArray[3]= data3;
                                        dataArray[4]= data4;
                                        dataArray[5]= data5;
                                        dataArray[6]= data6;
                                        dataArray[7]= data7;
                                        dataArray[8]= data8;

                                        sendPredictionRequest(dataArray);
                                    }
                                } else {
                                    Log.d(TAG, "Error getting documents: ", task.getException());
                                }
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
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
    private void sendPredictionRequest(double[] dataArray){

        // Create a request queue using Volley
        RequestQueue reqQueue = Volley.newRequestQueue(getContext());

        // Set the API endpoint URL
        String url = "http://127.0.0.1:8080/predict";

        // Create a JSON object for the request body, if required
        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put("Params", dataArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Create a request object with the necessary parameters
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, requestBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle the response data
                        String prediction = response.toString();
                        // Process the prediction or update UI accordingly
                        Toast.makeText(getActivity(), "Prediction for yields is: " + prediction, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle the error response
                    }
                });
        // Add the request to the request queue
        reqQueue.add(request);
    }
}