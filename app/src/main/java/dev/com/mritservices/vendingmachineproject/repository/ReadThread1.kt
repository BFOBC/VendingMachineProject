package dev.com.mritservices.vendingmachineproject.repository

import android.util.Log
import dev.com.mritservices.vendingmachineproject.repository.OrderRepository.Companion.mInputStream
import dev.com.mritservices.vendingmachineproject.utility.extensions.appendDataToInternalStorage
import java.io.IOException
import java.util.Arrays
class ReadThread1 : Thread() {
    private val TAG = "VmConfigurationRepoImp"
    private var result: String? = null // Variable to hold the result

    override fun run() {
        super.run()
        while (!isInterrupted) {
            var size: Int?
            try {
                val buffer = ByteArray(64)
                if (mInputStream == null) return
                size = mInputStream?.read(buffer)
                if (size != null && size > 0) {
                    bytesToHex(Arrays.copyOfRange(buffer, 0, size))?.let { hexString ->
                        Log.d(TAG, "data: $hexString")

                        result = when (hexString) {
                            "020502000000000306" -> "Shipment successful"
                            "020502000001010306" -> "Unlock completely when locking"
                            "020502000002020306" -> "Door not closed completely when locking"
                            "020502000003030306" -> "Motor current is heavier"
                            "020502000004040306" -> "Lift plate did not drop to the bottom while exceeding the limit steps"
                            "020502000005050306" -> "Max layer less than delivery layer"
                            "020502000006060306" -> "Timeout during plate movement back"
                            "020502000007070306" -> "Timeout during normal operation"
                            "020502000008080306" -> "Timeout during plate movement down"
                            "020502000009090306" -> "Unable to inspect locker working"
                            "02050200000A0A0306" -> "Timeout waiting for inspecting layer drop sensor"
                            "02050200000B0B0306" -> "Hide the drop sensor"
                            "02050200000C0C0306" -> "No sending or receiving via drop sensor"
                            "02050200000D0D0306" -> "Plate did not release when departing"
                            "02050200000E0E0306" -> "Timeout for push boarding operation"
                            "02050200000F0F0306" -> "Pushing board current is heavy"
                            "020502000010100306" -> "No current detected for pushing board"
                            "020502000011110306" -> "No cargoes in the fetch door"
                            "020502000012120306" -> "Cargoes detected inside the fetch door"
                            "020502000013130306" -> "Goods stuck at the fetch door"
                            "020502000014140306" -> "Lift motor open circuit"
                            "020502000015150306" -> "Drive board error"
                            "020502000016160306" -> "Flash cancel error"
                            "020502000017170306" -> "Flash write error"
                            "020502000018180306" -> "Error command"
                            "020502000019190306" -> "Checking error"
                            "02050200001A1A0306" -> "Locker open"
                            "02050200001B1B0306" -> "Invalid motor"
                            "02050200001C1C0306" -> "Timeout for turning"
                            "02050200001D1D0306" -> "No response from the drive board"
                            "02050200001E1E0306" -> "Do not release when plate departs some distance"
                            "02050200001F1F0306" -> "Timeout for push boarding operation"
                            "020502000020200306" -> "Pushing board current is heavy"
                            "020502000021210306" -> "No current detected for pushing board"
                            "020502000022220306" -> "No cargoes in the fetch door"
                            "020502000023230306" -> "Cargoes detected inside the fetch door"
                            "020502000024240306" -> "Goods stuck at the fetch door"
                            "020502000025250306" -> "Motor open circuit"
                            "020502000026260306" -> "Drive board error"
                            "020502000027270306" -> "Flash cancel error"
                            "020502000028280306" -> "Flash write error"
                            "020502000029290306" -> "Error command"
                            "02050200002A2A0306" -> "Checking error"
                            "02050200002B2B0306" -> "Locker open"
                            "02050200002C2C0306" -> "Invalid motor"
                            "02050200002D2D0306" -> "Timeout for turning"
                            "02050200002E2E0306" -> "No response from the drive board"
                            "02050200002F2F0306" -> "Do not release when plate departs some distance"
                            "020502000030300306" -> "Timeout for push boarding operation"
                            "020502000031310306" -> "Pushing board current is heavy"
                            "020502000032320306" -> "No current detected for pushing board"
                            "020502000033330306" -> "No cargoes in the fetch door"
                            "020502000034340306" -> "Cargoes detected inside the fetch door"
                            "020502000035350306" -> "Goods stuck at the fetch door"
                            "020502000036360306" -> "Motor open circuit"
                            "020502000037370306" -> "Drive board error"
                            "020502000038380306" -> "Flash cancel error"
                            "020502000039390306" -> "Flash write error"
                            "02050200003A3A0306" -> "Error command"
                            "02050200003B3B0306" -> "Checking error"
                            "02050200003C3C0306" -> "Locker open"
                            "02050200003D3D0306" -> "Invalid motor"
                            "02050200003E3E0306" -> "Timeout for turning"
                            "02050200003F3F0306" -> "No response from the drive board"
                            "020502000040400306" -> "Do not release when plate departs some distance"
                            "020502000041410306" -> "Timeout for push boarding operation"
                            "020502000042420306" -> "Pushing board current is heavy"
                            "020502000043430306" -> "No current detected for pushing board"
                            "020502000044440306" -> "No cargoes in the fetch door"
                            "020502000045450306" -> "Cargoes detected inside the fetch door"
                            "020502000046460306" -> "Goods stuck at the fetch door"
                            "020502000047470306" -> "Motor open circuit"
                            else -> null // Return null if none of the conditions match
                        }

                        appendDataToInternalStorage("SlaveMachine.txt", "data : $hexString : result : $result")

                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
                return
            }
        }
    }

    fun getResult(): String? {
        return result // Return the result
    }

    private val hexArray = "0123456789ABCDEF".toCharArray()
    fun bytesToHex(bytes: ByteArray): String? {
        val hexChars = CharArray(bytes.size * 2)
        for (j in bytes.indices) {
            val v = bytes[j].toInt() and 0xFF
            hexChars[j * 2] = hexArray[v ushr 4]
            hexChars[j * 2 + 1] = hexArray[v and 0x0F]
        }
        return String(hexChars)
    }
}
