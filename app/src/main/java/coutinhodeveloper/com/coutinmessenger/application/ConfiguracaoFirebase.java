package coutinhodeveloper.com.coutinmessenger.application;

import com.firebase.client.Firebase;

public final class ConfiguracaoFirebase {

    private static Firebase firebase;
    private static final String URL_FIREBASE = "https://coutinmessenger.firebaseio.com/";

    public static Firebase getFirebase(){
        if (firebase==null){
            firebase = new Firebase(URL_FIREBASE);

        }

        return firebase;
    }

}