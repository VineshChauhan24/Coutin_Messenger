package coutinhodeveloper.com.coutinmessenger.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;


import coutinhodeveloper.com.coutinmessenger.R;
import coutinhodeveloper.com.coutinmessenger.application.ConfiguracaoFirebase;
import coutinhodeveloper.com.coutinmessenger.model.Usuario;


/** Created by Guilherme Coutinho
 *  on 16/09/2018
 */

public class LoginActivity extends AppCompatActivity {

    //private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private Firebase firebase; //old version
   // private DatabaseReference firebase;
    private EditText email;
    private EditText senha;
    private Button botaoLogar;
    private Usuario usuario;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        //databaseReference = ConfiguracaoFirebase.getFirebase();
        firebase = ConfiguracaoFirebase.getFirebase();




        verificarUsuarioLogado();


        email = findViewById(R.id.edit_login_email);
        senha = findViewById(R.id.edit_login_senha);
        botaoLogar = findViewById(R.id.botao_logar);

        botaoLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuario = new Usuario();
                usuario.setEmail(email.getText().toString());
                usuario.setSenha(senha.getText().toString());
                validarLogin();

            }
        });




    }

    public void abrirCadastroUsuario(View view){

        Intent intent = new Intent(LoginActivity.this,CadastroUsuarioactivity.class);
        startActivity(intent);
    }

    private void validarLogin(){

        FirebaseAuth.getInstance().signInWithEmailAndPassword(usuario.getEmail(),usuario.getSenha())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        abrirTelaPrincipal();
                    }
                });
    }

    private void abrirTelaPrincipal(){
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void verificarUsuarioLogado(){

        if (firebase.getAuth()!=null){
            abrirTelaPrincipal();

        }
    }
}
