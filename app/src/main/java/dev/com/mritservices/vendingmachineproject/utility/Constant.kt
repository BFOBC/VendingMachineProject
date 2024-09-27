package dev.com.mritservices.vendingmachineproject.utility

import androidx.navigation.NavController

object Constant {
     var navController:NavController?=null

     const val PRINTER_SERIAL_PORT = "/dev/ttyS4"
     const val BILL_ACCEPTOR_SERIAL_PORT = "/dev/ttyS1"
     const val SLAVE_VENDING_MACHINE_SERIAL_PORT = "/dev/ttyS3"
     const val VENDING_MACHINE = "VENDING MACHINE"
     const val DISPENSING_STATUS = "pending"
     const val PAYMENT_METHOD_INFO = "WAAFI"
     var VENDING_MACHINE_ID = 26
     const val BAUD_RATE=9600
}