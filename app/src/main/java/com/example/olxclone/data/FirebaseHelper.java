package com.example.olxclone.data;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by João Bosco on 30/09/2022.
 */
public class FirebaseHelper {

    private static FirebaseAuth auth;
    private static DatabaseReference databaseReference;
    private static StorageReference storageReference;

    public static StorageReference getStorageReference(){
        if(storageReference == null){
            storageReference = FirebaseStorage.getInstance().getReference();
        }
        return storageReference;
    }

    public static String getIdFirebase(){
        return getAuth().getUid();
    }

    public static DatabaseReference getDatabaseReference(){
        if(databaseReference == null){
            databaseReference = FirebaseDatabase.getInstance().getReference();
        }
        return databaseReference;
    }

    public static FirebaseAuth getAuth(){
        if(auth == null){
            auth = FirebaseAuth.getInstance();
        }
        return auth;
    }

    public static boolean getAuthentication(){
        return getAuth().getCurrentUser() != null;
    }

    public static String validFirebase(String error) {
        String message = "";

        if (error.contains("There is no user record corresponding to this identifier")) {
            message = "E-mail não cadastrado!";
        } else if (error.contains("The email address is badly formatted")) {
            message = "Formato de e-mail inválido!";
        } else if (error.contains("The password is invalid or the user does not have a password")) {
            message = "Senha inválida, tente novamente.";
        } else if (error.contains("The email address is already in use by another account")) {
            message = "Este e-mail já está em uso.";
        } else if (error.contains("Password should be at least 6 characters")) {
            message = "Insira uma senha com no mínimo 6 caracteres.";
        }

        return message;
    }

}
