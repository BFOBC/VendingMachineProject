package dev.com.mritservices.vendingmachineproject.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Product(
    @SerializedName("slotId") var slotId: Int? = null,
    @SerializedName("slotNumber") var slotNumber: Int? = 0,
    @SerializedName("trayId") var trayId: Int? = null,
    @SerializedName("availableQuantity") var availableQuantity: Int? = null,
    @SerializedName("maxQuantity") var maxQuantity: Int? = null,
    @SerializedName("productSku") var productSku: String? = null,
    @SerializedName("productId") var productId: Int? = null,
    @SerializedName("productName") var productName: String? = null,
    @SerializedName("productDescription") var productDescription: String? = null,
    @SerializedName("productCostPrice") var productCostPrice: String? = null,
    @SerializedName("productSellingPrice") var productSellingPrice: String? = null,
    @SerializedName("productLength") var productLength: String? = null,
    @SerializedName("productWidth") var productWidth: String? = null,
    @SerializedName("productWeight") var productWeight: String? = null,
    @SerializedName("productHeight") var productHeight: String? = null,
    @SerializedName("productImage") var productImage: String? = null,
    @SerializedName("productCode") var productCode: Int? = null,
    @SerializedName("productQuantity") var productQuantity: String? = null,
    @SerializedName("productCategoryName") var productCategoryName: String? = null,
    @SerializedName("productCategoryId") var productCategoryId: Int? = null,
    @SerializedName("productTypeId") var productTypeId: Int? = null,
)

