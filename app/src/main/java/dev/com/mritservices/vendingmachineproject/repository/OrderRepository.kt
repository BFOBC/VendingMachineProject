package dev.com.mritservices.vendingmachineproject.repository

import android.serialport.SerialPort
import android.util.Log
import com.google.gson.JsonObject
import dev.com.mritservices.vendingmachineproject.remote.retrofit.ApiService
import dev.com.mritservices.vendingmachineproject.remote.retrofit.NetworkResponse
import dev.com.mritservices.vendingmachineproject.utility.enums.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

class OrderRepository @Inject constructor(private val apiInterface: ApiService) {
    private val TAG = "VmConfigurationRepoImp"
    private var mSerialPort: SerialPort? = null
    private val baudrate = 9600
    private var mReadThread: ReadThread1?=null
    companion object{
        var mOutputStream: OutputStream?=null
        var mInputStream: InputStream?=null
    }
    fun getOrderDetails(orderID:Int,vmId:Int): Flow<Resource<NetworkResponse<JsonObject>>> {
        return flow {
            emit(Resource.loading(data = null))
            try {
                apiInterface.getOrderDetails(orderID,vmId).let {
                    if (it.isSuccessful) {
                        Log.d("auth", "payload: ${it.body()}")
                        if (it.body()!!.status == 2000) {
                            emit(Resource.success(it.body()))
                        } else {
                            emit(Resource.error(it.body()!!.message, data = null))
                        }
                    } else {
                        emit(Resource.error(it.message(), data = null))
                    }
                }
            } catch (ex: Exception) {
                emit(Resource.error(ex.message!!, data = null))
            }
        }
    }
  suspend  fun vendItem(slotNumber: String):String {
        try {
            // command  020602002a00002a0305
/*            val bytesToSend = byteArrayOf(
                0x02, 0x06, 0x02, 0x00, slotNumber.toByte(), 0x00, 0x00, slotNumber.toByte(), 0x03, 0x05
            )*/
            val convertSlot= Integer.toHexString((slotNumber.toInt()%100)).toString().uppercase()
            val vendCommand = byteArrayOf(0x02, 0x06, 0x02, 0x00, convertSlot.toByte(16), 0x00, 0x00, convertSlot.toByte(16), 0x03, 0x05)
            mOutputStream?.write(vendCommand, 0, vendCommand.size)
            delay(10000)

        } catch (exception: Exception){
            exception.printStackTrace()
        }
      return mReadThread?.getResult().toString()
    }
    fun openAndConnectPort(serialPort: String?): Boolean {
        try {
            mSerialPort = SerialPort(File(serialPort.toString()), baudrate)
            mOutputStream = mSerialPort?.outputStream
            mInputStream = mSerialPort?.inputStream
            mReadThread = ReadThread1()
            mReadThread?.start()
            return true
        } catch (exception: Exception){
            exception.printStackTrace()
            return false
        }
        return false
    }
    fun closePort(): Boolean {
        try {
            if (mSerialPort != null) {
                mSerialPort?.close()
                mSerialPort = null
                return true
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
            return false
        }
        return false
    }
}