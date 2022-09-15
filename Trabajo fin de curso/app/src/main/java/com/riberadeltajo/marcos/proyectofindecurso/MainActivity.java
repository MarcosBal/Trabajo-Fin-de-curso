package com.riberadeltajo.marcos.proyectofindecurso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.annotations.Nullable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button tvNotLogin;
    Button signInButton;
    Button signOutButton;
    GoogleSignInClient mGoogleSignInClient;
    private final int RC_SING_IN=102;
    private FirebaseAuth mAuth;
    private final String TAG = "MARCOS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        googleAuth();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setTheme(R.style.ProyectoFinDeCurso);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvNotLogin = findViewById(R.id.btnNotLogin);
        tvNotLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lanzarSiguienteActividad();
            }
        });

        signOutButton = findViewById(R.id.signoutButton);
        signInButton = findViewById(R.id.signInButton);

        signInButton.setOnClickListener(this);
        signOutButton.setOnClickListener(this);
    }

    private void googleAuth(){
        GoogleSignInOptions gso = new
                GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        mAuth=FirebaseAuth.getInstance();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RC_SING_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d("RIBERADELTAJO", "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());

            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w("RIBERADELTAJO", "Google sign in failed", e);

            }

        }
    }

    private void firebaseAuthWithGoogle(String idToken) {

        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("RIBERADELTAJO", "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //A ESTE ESTOY --> ESTOY AUTENTIFICADO!!!
                            updateUI(user);
                            lanzarSiguienteActividad();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("RIBERADELTAJO", "signInWithCredential:failure", task.getException());
                            updateUI(null);
                        }
                    }
                });
    }

    public void updateUI(FirebaseUser user){
        if (user != null) {
            Toast.makeText(this,"Bienvenido "+user.getDisplayName(),Toast.LENGTH_LONG).show();
            signInButton.setVisibility(View.GONE);
            signOutButton.setVisibility(View.VISIBLE);
        } else {
            signInButton.setVisibility(View.VISIBLE);
            signOutButton.setVisibility(View.GONE);
        }
    }

    private void signOut() {
        // Firebase sign out
        mAuth.signOut();
        // Google sign out
        mGoogleSignInClient.signOut().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        updateUI(null);
                    }
                });
    }

    public void lanzarSiguienteActividad(){
        Intent i=new Intent(getApplicationContext(),InicioActivity.class);
        startActivity(i);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.signInButton) {
            Intent signIntent = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(signIntent,RC_SING_IN);
        } else if (id == R.id.signoutButton) {
            signOut();
        }
    }
}