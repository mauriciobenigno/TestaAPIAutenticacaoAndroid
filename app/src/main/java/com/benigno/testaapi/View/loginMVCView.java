package com.benigno.testaapi.View;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.benigno.testaapi.Conta.ContaAtual;
import com.benigno.testaapi.Model.DbConta;
import com.benigno.testaapi.Model.Remoto.RetrofitClient;
import com.benigno.testaapi.Model.Remoto.funcAPI;
import com.benigno.testaapi.R;

import dmax.dialog.SpotsDialog;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class loginMVCView extends AppCompatActivity {

    funcAPI funcAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    EditText edtLogin;
    EditText edtSenha;
    Button btLogar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_mvcview);

        // inicializar comunicação com API
        funcAPI = RetrofitClient.getInstance().create(funcAPI.class);


        btLogar = (Button)findViewById(R.id.btLogar);
        edtLogin = (EditText)findViewById(R.id.edtLogin);
        edtSenha = (EditText)findViewById(R.id.edtSenha);


        btLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog dialog = new SpotsDialog.Builder().setContext(loginMVCView.this).build();
                dialog.show();

                DbConta conta = new DbConta(edtLogin.getText().toString(),edtSenha.getText().toString(),"");

                compositeDisposable.add(funcAPI.loginConta(conta)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(String s) throws Exception {
                                //Toast.makeText(loginMVCView.this,s,Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                                String verificador = ContaAtual.RemovePrimeiroeUltimoChar(s);
                                if(verificador.contains("LOGTRUE"))
                                {
                                    ContaAtual.logado=true;
                                    if(verificador.contains("admin"))
                                    {
                                        ContaAtual.NomeUsuario="admin";
                                        ContaAtual.admin=true;
                                    }
                                    else
                                    {
                                        ContaAtual.NomeUsuario="usuario";
                                        ContaAtual.admin=false;
                                    }
                                    Intent intent = new Intent(loginMVCView.this, telaLogadoMVCView.class);
                                    startActivity(intent);
                                }
                                else
                                    ContaAtual.Deslogar();
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                dialog.dismiss();
                                String verificador = ContaAtual.RemovePrimeiroeUltimoChar(throwable.getMessage());
                                Toast.makeText(loginMVCView.this,throwable.getMessage(),Toast.LENGTH_SHORT).show();
                                if(verificador.contains("LOGTRUE"))
                                {
                                    ContaAtual.logado=true;
                                    if(verificador.contains("admin"))
                                    {
                                        ContaAtual.NomeUsuario="admin";
                                        ContaAtual.admin=true;
                                    }
                                    else
                                    {
                                        ContaAtual.NomeUsuario="usuario";
                                        ContaAtual.admin=false;
                                    }
                                    Intent intent = new Intent(loginMVCView.this, telaLogadoMVCView.class);
                                    startActivity(intent);
                                }
                                else
                                    ContaAtual.Deslogar();
                            }
                        }));

            }
        });
    }

    @Override
    protected void onStop()
    {
        compositeDisposable.clear();
        super.onStop();
    }
}