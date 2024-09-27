package dev.com.mritservices.vendingmachineproject.utility.enums

enum class ErrorMessages(var message:String ,var code:Int) {
   CODE_100400("BadRequest" , 100400),
    CODE_100403("Forbidden" , 100403),
    CODE_100404("NotFound" , 100404),
    CODE_100401("Unauthorized" , 100401),
    CODE_100500("InternalServerError" , 100500) ,
    CODE_100106("MsisdnAlreadyRegistered", 100106),
    CODE_100107("InvalidMsisdn", 100107),
    CODE_100108("InvalidAuthToken", 100108),
    CODE_99999("catchblock" , 99999),
    CODE_1000001("nointerConnection" , 1000001)
}