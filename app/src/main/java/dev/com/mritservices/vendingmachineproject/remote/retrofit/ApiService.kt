package dev.com.mritservices.vendingmachineproject.remote.retrofit


import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * File created by Bilawal Sharif on 04/11/2024
 * Description:
 */
interface ApiService {
    @POST("/api/customers/login")
    suspend fun guestLogin(@Body request: JsonObject): Response<NetworkResponse<JsonObject>>

    @GET("/api/vending-machines/products/{productId}")
    suspend fun getVendingMachineProduct(@Path("productId") productId: Int): Response<NetworkResponse<List<JsonObject>>>

    //get categories home screen
    @GET("/api/vending-machines/categories/{Id}")
    suspend fun getCategories(@Path("Id") productId: Int): Response<NetworkResponse<List<JsonObject>>>

    //get order from customer app
    @GET("/api/vending-machines/order/sales/order/merchant/{id}/{vmId}")
    suspend fun getOrderDetails(@Path("id") productId: Int,@Path("vmId") vmId:Int): Response<NetworkResponse<JsonObject>>

    //vm configuration
    @POST("/system-app/admins/login")
    suspend fun technicianLogin(@Body technicianLoginRequest: JsonObject): Response<NetworkResponseD<JsonObject>>


}