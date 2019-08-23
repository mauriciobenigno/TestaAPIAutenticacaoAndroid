package com.benigno.testaapi.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

public class registroMVCView extends AppCompatActivity {

    com.benigno.testaapi.Model.Remoto.funcAPI funcAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    EditText edtLogin;
    EditText edtSenha;
    Button btRegistar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_mvcview);

        // iniciarlizar comunicação com API
        funcAPI = RetrofitClient.getInstance().create(funcAPI.class);

        btRegistar = (Button)findViewById(R.id.btConfirmCadastro);
        edtLogin = (EditText)findViewById(R.id.edtCadLogin);
        edtSenha = (EditText)findViewById(R.id.edtCadSenha);


        btRegistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog dialog = new SpotsDialog.Builder().setContext(registroMVCView.this).build();
                dialog.show();

                DbConta conta = new DbConta(edtLogin.getText().toString(),edtSenha.getText().toString(),"");
                compositeDisposable.add(funcAPI.registrarConta(conta).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(String s) throws Exception {
                                String verificador = ContaAtual.RemovePrimeiroeUltimoChar(s);
                                if(verificador.equals("REGTRUE"))
                                    DialogCadastro();

                                Toast.makeText(registroMVCView.this,s,Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                dialog.dismiss();
                                Toast.makeText(registroMVCView.this,throwable.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        })
                );
            }
        });

    }

    public void DialogCadastro()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(registroMVCView.this);
        builder.setTitle("Conta criada!");
        builder.setMessage("Conta cadastratada com usuário: "+edtLogin.getText().toString()+ "\nDeseja cadastrar uma nova conta?");
        builder.setPositiveButton("Cadastar ", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                edtLogin.setText("");
                edtSenha.setText("");
            }
        });
        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                finish();
            }
        });
        AlertDialog alerta = builder.create();
        alerta.show();
    }

    @Override
    protected void onStop()
    {
        compositeDisposable.clear();
        super.onStop();
    }
}
