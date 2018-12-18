package com.mcuhq.coloretsApp

import android.util.Log
import com.amazonaws.http.HttpMethodName
import com.amazonaws.mobile.client.AWSMobileClient
import com.amazonaws.mobileconnectors.apigateway.ApiClientFactory
import com.amazonaws.mobileconnectors.apigateway.ApiRequest
import com.amazonaws.util.IOUtils
import com.mcuhq.coloretsApp.amazonaws.mobile.api.id8a6y7xdey6.ColorsApiClient
import com.mcuhq.coloretsApp.biblio.ColorModelCreator
import com.mcuhq.coloretsApp.biblio.colormODEL
import kotlin.concurrent.thread

/**
 * Created by carlos on 27/11/2018.
 */
//objecte singleton que crida internet (el núvol) i allotja en si mateix el array de colors rebuts, a través i facilitant-se amb el colormodelCreator

 object GlobalFile {

    public var list1=ArrayList<colormODEL>()
private var apiClient: ColorsApiClient? = null
   public fun apiGetCallWithPath(path: String) {
       var modelcreator: ColorModelCreator?=null;

        apiClient = ApiClientFactory()
                .credentialsProvider(AWSMobileClient.getInstance().credentialsProvider)
                .build(ColorsApiClient::class.java)
       modelcreator= ColorModelCreator();

        val parameters = mapOf("lang" to "en_US")
        val headers = mapOf("Content-Type" to "application/json")

        val request = ApiRequest(ColorsApiClient::class.java.simpleName)
                .withPath(path)
                .withHttpMethod(HttpMethodName.GET)
                .withHeaders(headers)
                .withParameters(parameters)

        thread(start = true) {
            try {
                Log.e("wow1", "Accedint GET API path $path")

                val response = apiClient?.execute(request)
                val responseContentStream = response?.getContent()
                if (responseContentStream != null) {
                    val responseData = IOUtils.toString(responseContentStream)
                   list1 =modelcreator.createColors(responseData);
                    Log.e("wow11", list1.toString());

                }

            } catch (ex: Exception) {
                Log.e("wow1", "Error invoking API")
                Log.e("Api error", ex.toString())
            }
        }

    }


    fun apiPutCallWithPath(path: String, body: String) {

        Log.e("wow15", "1time")
        apiClient = ApiClientFactory()
                .credentialsProvider(AWSMobileClient.getInstance().credentialsProvider)
                .build(ColorsApiClient::class.java)
        val parameters = mapOf("lang" to "en_US")
        val headers = mapOf("Content-Type" to "application/json")
        val contentLenght = body.toByteArray().size

        val contentToUse = contentLenght.toString()

        Log.e("Body", body)
        Log.e("Body Lenght byte", contentToUse)
        Log.e("wow1", "E145I")
        val request = ApiRequest(ColorsApiClient::class.java.simpleName)
                .withPath(path)
                .withHttpMethod(HttpMethodName.PUT)
                .withHeaders(headers)
                .addHeader("Content-Length", contentLenght.toString())
                .withParameters(parameters)
                .withBody(body)

        thread(start = true) {
            try {
                Log.e("wow2", "Accedint PUT API path $path")
                val response = apiClient?.execute(request)
                val responseContentInt = response?.statusCode
                Log.e("Result: ", responseContentInt.toString())
            } catch (ex: Exception) {
                Log.e("wow2", "Error invoking API")
                Log.e("Api error", ex.toString())
            }
        }


    }

}