package com.benigno.testaapi.Conta;

public class ContaAtual {
    private ContaAtual(){}

    public static String NomeUsuario = "indefinido";
    public static boolean logado = false;
    public static boolean admin = false;

    public static  void Deslogar()
    {
        ContaAtual.logado=false;
        ContaAtual.admin=false;
        ContaAtual.NomeUsuario="indefinido";
    }


    public static String RemovePrimeiroeUltimoChar(String texto)
    {
        String resultado = new String();
        for(int i = 1 ; i< texto.length()-1;i++)
            resultado+=texto.charAt(i);
        return resultado;
    }
}
