package dev.com.mritservices.vendingmachineproject.viewmodels

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.load.engine.Resource
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.com.mritservices.vendingmachineproject.ApplicationClass.Companion.context
import dev.com.mritservices.vendingmachineproject.models.Product
import dev.com.mritservices.vendingmachineproject.models.VendModel
import dev.com.mritservices.vendingmachineproject.remote.retrofit.NetworkResponse
import dev.com.mritservices.vendingmachineproject.repository.OrderRepository
import dev.com.mritservices.vendingmachineproject.utility.Constant.SLAVE_VENDING_MACHINE_SERIAL_PORT
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(private val orderRepository: OrderRepository) : ViewModel() {
    private val serialPortPath: MutableLiveData<String?> =
        MutableLiveData(SLAVE_VENDING_MACHINE_SERIAL_PORT)

    private val TAG = "VendVm"
    private val _orderDetails =
        MutableLiveData<Resource<NetworkResponse<JsonObject>>>()
    private val _updateOrderStatusDetails =
        MutableLiveData<Resource<NetworkResponse<JsonObject>>>()
    private val _recipeResponseList = MutableLiveData<List<JsonObject>>()
    val recipeList: LiveData<List<JsonObject>> get() = _recipeResponseList
    private val _vendParamsList = MutableLiveData<List<JsonObject>>()
    val vendParamsList: LiveData<List<JsonObject>> get() = _vendParamsList
    val orderDetails: LiveData<Resource<NetworkResponse<JsonObject>>>
        get() = _orderDetails
    val updateOrderStatusDetails: LiveData<Resource<NetworkResponse<JsonObject>>>
        get() = _updateOrderStatusDetails


/*
    fun getOrderDetails(
        orderID: Int,
        vmId: Int
    ): LiveData<Resource<NetworkResponse<JsonObject>>> {
        viewModelScope.launch {
            orderRepository.getOrderDetails(orderID, vmId).collect {
                _orderDetails.value = it
            }
        }
        return orderDetails
    }
*/




    //TCN vending
    fun openAndConnectPort(): Boolean {
        return try {
            val result = orderRepository.openAndConnectPort(serialPortPath.value)
            Log.d(TAG, "openAndConnectPort result: $result")
            Toast.makeText(context, "Exception$result", Toast.LENGTH_SHORT).show()

            result
        } catch (exception: Exception) {
            Toast.makeText(context, "Exception$exception", Toast.LENGTH_SHORT).show()
            exception.printStackTrace()
            false
        }
    }

    // For TCN
    fun closePort(): Boolean {
        return try {
            val result = orderRepository.closePort()
            Log.d(TAG, "closePort result: $result")
            true
        } catch (exception: Exception) {
            exception.printStackTrace()
            false
        }
    }

    // FOR TCN
    suspend fun vend(products: Product): String {
        val delayMillis = TimeUnit.MINUTES.toMillis(1) // Convert 1 minute to milliseconds
        //  for (item in products) {
        //      Log.d(TAG, "vending item in slot: ${item.slotNumber} with quantity: ${item.productQuantity}")
        //     val quantity = item.productQuantity?.toInt() ?: 0
        //  for (i in 0 until quantity) {
        //     Log.d(TAG, "quantity: $i")
        return orderRepository.vendItem(products.slotNumber?.toString() ?: "")
        // Thread.sleep(delayMillis)


        //   }
        ///  }

    }

    suspend fun testVend(products: VendModel): String {
        return orderRepository.vendItem(products.slotNumber.toString())
    }

}