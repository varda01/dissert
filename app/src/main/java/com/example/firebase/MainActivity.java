package com.example.firebase;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;
    EditText editText;
    EditText editText2;
    TextView textView2;
    Button button;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser currentUser;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private CollectionReference collectionReference = db.collection("Users");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.img1);
        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editTxt);
        editText2 = findViewById(R.id.editPass);
        textView2 = findViewById(R.id.textView2);
        button = findViewById(R.id.btn1);

        firebaseAuth = FirebaseAuth.getInstance();


        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Activity4.class));
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginEmailPasswordUser(
                        editText.getText().toString().trim(),
                        editText2.getText().toString().trim()
                );
            }
        });

    }

    private void LoginEmailPasswordUser(String email, String pwd) {
        // Checking for empty texts
        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pwd)) {

            firebaseAuth.signInWithEmailAndPassword(email, pwd)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            FirebaseUser user = firebaseAuth.getCurrentUser();

                            if (user != null) {
                                final String currentUserId = user.getUid();

                                collectionReference.
                                        whereEqualTo("email", email)
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                                       // Log.d("varda05", document.getId() + " => " + document.getData());
                                                        if(pwd.equals(document.get("password"))){
                                                            Toast.makeText(MainActivity.this, "Successfully logged in", Toast.LENGTH_LONG).show();
                                                            Intent dash = new Intent(getApplicationContext(), Activity3.class);
                                                            startActivity(dash);
                                                           // startActivity(new Intent(MainActivity.this, Activity3.class));
                                                        }else {
                                                            Toast.makeText(MainActivity.this, "Invalid password", Toast.LENGTH_LONG).show();
                                                        }
                                                    }
                                                } else {
                                                    Log.d("varda05", "Error getting documents: ", task.getException());
                                                }
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                // If Failed:
                                                Log.e("varda05", e.toString());
                                                Toast.makeText(MainActivity.this,
                                                        "Something went wrong " + e, Toast.LENGTH_LONG).show();
                                            }
                                        });

                                        /*.addSnapshotListener(new EventListener<QuerySnapshot>() {
                                            @Override
                                            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                                               // Log.e("varda05", error.getMessage().toString());

                                                if (error == null && value != null) {
                                                    Log.e("varda05", "working");
                                                    if (!value.isEmpty()) {
                                                        // Getting all QueryDocSnapShots
                                                        /*for (QueryDocumentSnapshot snapshot : value) {
                                                            Log.e("varda05", "StartingAct");

                                                            startActivity(new Intent(MainActivity.this, Activity3.class));
                                                        }
                                                    }
                                                }
                                            }
                                        });*/
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // If Failed:
                            Log.e("varda05", e.toString());
                            Toast.makeText(MainActivity.this,
                                    "Something went wrong " + e, Toast.LENGTH_LONG).show();
                        }
                    });
        } else {
            Toast.makeText(MainActivity.this,
                    "Please Enter email & password"
                    , Toast.LENGTH_SHORT).show();
        }
    }
}