package com.benigno.testaapi.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.benigno.testaapi.Conta.ContaAtual;
import com.benigno.testaapi.R;

public class telaLogadoMVCView extends AppCompatActivity {

    Button btRegistrar;
    Button btExcluir;
    Button btListar;
    Button btDeslogar;
    TextView txtBoasVindas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_logado_mvcview);

        btRegistrar = (Button)findViewById(R.id.btCadNovaConta);
        btExcluir = (Button)findViewById(R.id.btExcCad);
        btListar = (Button)findViewById(R.id.btListarContas);
        btDeslogar = (Button)findViewById(R.id.btDeslogar);
        txtBoasVindas = (TextView)findViewById(R.id.txtBoasVindas);



        // Adicionando nome de usuário ao texto de boas vindas
        txtBoasVindas.setText(txtBoasVindas.getText().toString()+" "+ ContaAtual.NomeUsuario);

        // Acionando o botão de Registro de usuários
        btRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ContaAtual.admin) {
                    Intent intent = new Intent(telaLogadoMVCView.this, registroMVCView.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(telaLogadoMVCView.this,"Somente Admins podem cadastrar usuários",Toast.LENGTH_SHORT).show();
            }
        });

        // Exclui contas do banco de dados
        btExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContaAtual.admin) {
                    //Intent intent = new Intent(telaLogadoMVCView.this, registroMVCView.class);
                    //startActivity(intent);
                    Toast.makeText(telaLogadoMVCView.this,"Função ainda não implementada na API",Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(telaLogadoMVCView.this,"Somente Admins podem excluir usuários",Toast.LENGTH_SHORT).show();
            }
        });

        // Qualquer usuário pode listar as contas existentes
        btListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContaAtual.admin) {
                    //Intent intent = new Intent(telaLogadoMVCView.this, registroMVCView.class);
                    //startActivity(intent);
                    Toast.makeText(telaLogadoMVCView.this,"Função ainda não implementada na API",Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(telaLogadoMVCView.this,"Somente Admins podem excluir usuários",Toast.LENGTH_SHORT).show();
            }
        });

        // Desloga o usuário
        btDeslogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContaAtual.Deslogar();
                finish();
            }
        });
    }
}
