package com.benigno.testaapi.Model;

public class DbConta {
    public String Usuario;
    public String Senha;
    public String SaltCript;

    public DbConta() {
    }

    public DbConta(String usuario, String senha, String saltCript) {
        Usuario = usuario;
        Senha = senha;
        SaltCript = saltCript;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String senha) {
        Senha = senha;
    }

    public String getSaltCript() {
        return SaltCript;
    }

    public void setSaltCript(String saltCript) {
        SaltCript = saltCript;
    }
}
