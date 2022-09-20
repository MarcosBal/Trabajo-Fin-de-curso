package com.riberadeltajo.marcos.proyectofindecurso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Query;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class FirebaseController {

    DatabaseReference mRootReference;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    final DatabaseReference usuarios;

    public FirebaseController() {
        this.mRootReference = FirebaseDatabase.getInstance().getReference();;
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        usuarios = database.getReference("Usuarios");
    }

    public void subirDatosFirebase(){
        if (mAuth.getCurrentUser() != null) {
            Map<String, Object> datosUsuario = new HashMap<>();
            datosUsuario.put("PartidasGanadas", 0);
            datosUsuario.put("PartidasPerdidas", 0);
            datosUsuario.put("PartidasEmpatadas", 0);
            mRootReference.child("Usuarios").child(mAuth.getCurrentUser().getUid()).setValue(datosUsuario);
        }
    }

    public void obtenerDatos(Callback callback) {
        Estadisticas e = new Estadisticas();
        if (mAuth.getCurrentUser() != null) {

            mRootReference.child("Usuarios").child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        e.setGanadas(dataSnapshot.child("PartidasGanadas").getValue(Integer.class));
                        e.setPerdidas(dataSnapshot.child("PartidasPerdidas").getValue(Integer.class));
                        e.setEmpates(dataSnapshot.child("PartidasEmpatadas").getValue(Integer.class));
                        callback.onCallback(e);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

    public void actualizarDatos(int ganadas, int perdidas, int empatadas) {
        if (mAuth.getCurrentUser() != null) {
            DatabaseReference estadisticas = mRootReference.child("Usuarios").child(mAuth.getCurrentUser().getUid());
            estadisticas.runTransaction(new Transaction.Handler() {
                @NonNull
                @Override
                public Transaction.Result doTransaction(@NonNull MutableData currentData) {
                    int dataGanadas = currentData.child("PartidasGanadas").getValue(Integer.class);
                    int dataPerdidas = currentData.child("PartidasPerdidas").getValue(Integer.class);
                    int dataEmpatadas = currentData.child("PartidasEmpatadas").getValue(Integer.class);

                    currentData.child("PartidasGanadas").setValue(dataGanadas + ganadas);
                    currentData.child("PartidasPerdidas").setValue(dataPerdidas + perdidas);
                    currentData.child("PartidasEmpatadas").setValue(dataEmpatadas + empatadas);
                    
                    return Transaction.success(currentData);
                }

                @Override
                public void onComplete(@Nullable DatabaseError error, boolean committed, @Nullable DataSnapshot currentData) {

                }
            });
        }
    }


    public void comprobarRegistros() {
        if (mAuth.getCurrentUser() != null) {
            mRootReference.child("Usuarios").child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (!dataSnapshot.exists()) {
                        subirDatosFirebase();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

}
