package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Activity4 extends AppCompatActivity {

    ImageView imageView;
    TextView textView;
    EditText editText;
    EditText editText2;
    EditText editText3;
    Button button;
    ImageView imageView2;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser currentUser;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private CollectionReference collectionReference = db.collection("Users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);
        imageView = findViewById(R.id.img3);
        textView = findViewById(R.id.text3);
        editText = findViewById(R.id.editName);
        editText2 = findViewById(R.id.editEmailAddr);
        editText3 = findViewById(R.id.editPass2);
        button = findViewById(R.id.butn2);
        imageView2 = findViewById(R.id.img2);

        firebaseAuth = FirebaseAuth.getInstance();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                currentUser = firebaseAuth.getCurrentUser();

                if (currentUser != null){
                    // User Already Logged IN

                }else{
                    // No user yet!
                }
            }
        };

        imageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(editText.getText().toString())
                        && !TextUtils.isEmpty(editText2.getText().toString())
                        && !TextUtils.isEmpty(editText3.getText().toString())){

                    String email = editText2.getText().toString().trim();
                    String password = editText3.getText().toString().trim();
                    String username = editText.getText().toString().trim();
                    CreateEmailAccount(email,password,username);
                }else{
                    Toast.makeText(Activity4.this, "Empty Fields", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void CreateEmailAccount(final String email, final String password, final String username) {

        if (!TextUtils.isEmpty(email)
                && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(username)){

            firebaseAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                              //  Log.e("varda05", task.getResult().toString());
                            }
                            if (task.isSuccessful()) {
                               // Log.e("varda05", "working");
                                // we take user to Next Activity
                                currentUser = firebaseAuth.getCurrentUser();


                                if (currentUser != null) {
                                    String currentUserId = currentUser.getUid();


                                    // Create a userMap so we can create a user in the User Collection in Firestore
                                    Map<String, String> userObj = new HashMap<>();
                                    userObj.put("userId", currentUserId);
                                    userObj.put("email", email.toString().trim());
                                    userObj.put("username", username.toString().trim());
                                    userObj.put("password", password.toString().trim());
;

                                    //Adding Users to Firestore
                                    collectionReference.add(userObj)
                                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                @Override
                                                public void onSuccess(DocumentReference documentReference) {
                                                    documentReference.get()
                                                            .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                                                @Override
                                                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                                    if (Objects.requireNonNull(task.getResult()).exists()) {
                                                                        String name = task.getResult().getString("username");

                                                                        Toast.makeText(Activity4.this, "Successfully registered", Toast.LENGTH_SHORT).show();

                                                                        Intent i = new Intent(Activity4.this, MainActivity.class);

                                                                        i.putExtra("username", name);
                                                                        i.putExtra("userId", currentUserId);
                                                                        startActivity(i);
                                                                    } else {

                                                                    }
                                                                }
                                                            }).addOnFailureListener(new OnFailureListener() {
                                                                @Override
                                                                public void onFailure(@NonNull Exception e) {
                                                                    // Display Failed Message
                                                                   // Log.e("varda05", e.toString());
                                                                    Toast.makeText(Activity4.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                                                                }
                                                            });
                                                }
                                            });
                                }
                             }
                        }
                    });
        }


    }
    @Override
    protected void onStart() {
        super.onStart();

        currentUser = firebaseAuth.getCurrentUser();
        firebaseAuth.addAuthStateListener(authStateListener);
    }
}