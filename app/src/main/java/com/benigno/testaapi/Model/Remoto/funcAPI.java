package com.benigno.testaapi.Model.Remoto;

import com.benigno.testaapi.Model.DbConta;


import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface funcAPI {
    //https://apiautenticacao.azurewebsites.net/api/registro
    @POST("api/registro")
    Observable<String> registrarConta(@Body DbConta conta);
    //https://apiautenticacao.azurewebsites.net/api/login
    @POST("api/login")
    Observable<String> loginConta(@Body DbConta conta);
}
