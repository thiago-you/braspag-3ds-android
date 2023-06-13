package br.com.braspag.internal.data

import br.com.braspag.data.AirlineData
import br.com.braspag.data.BillToData
import br.com.braspag.data.CardData
import br.com.braspag.data.CartItemData
import br.com.braspag.data.DeviceData
import br.com.braspag.data.GiftCardCurrency
import br.com.braspag.data.GiftCardData
import br.com.braspag.data.MddData
import br.com.braspag.data.OptionsData
import br.com.braspag.data.OrderData
import br.com.braspag.data.Passenger
import br.com.braspag.data.PaymentMethod
import br.com.braspag.data.RecurringData
import br.com.braspag.data.ShipToData
import br.com.braspag.data.TravelLeg
import br.com.braspag.data.UserData
import com.google.gson.annotations.SerializedName
import java.io.Serializable

internal data class EnrollData(

    // order data
    @SerializedName("transactionId")
    val transactionId: String? = null,
    @SerializedName("transactionMode")
    val transactionMode: String? = null,
    @SerializedName("merchantUrl")
    val merchantUrl: String? = null,
    @SerializedName("merchantNewCustomer")
    val merchantNewCustomer: Boolean? = null,
    @SerializedName("orderNumber")
    val orderNumber: String, // required
    @SerializedName("currency")
    val currency: String, // required
    @SerializedName("totalAmount")
    val totalAmount: Long? = null,
    @SerializedName("installments")
    val installments: Int? = null,
    @SerializedName("paymentMethod")
    val paymentMethod: PaymentMethod? = null,

    // card
    @SerializedName("cardNumber")
    val cardNumber: String, // required
    @SerializedName("cardExpirationMonth")
    val cardExpirationMonth: String, // required
    @SerializedName("cardExpirationYear")
    val cardExpirationYear: String, // required
    @SerializedName("cardAlias")
    val cardAlias: String? = null,
    @SerializedName("defaultCard")
    val defaultCard: Boolean? = null,
    @SerializedName("cardAddedDate")
    val cardAddedDate: String? = null,

    // gift card
    @SerializedName("giftCardAmount")
    val giftCardAmount: Long? = null,
    @SerializedName("giftCardCurrency")
    val giftCardCurrency: GiftCardCurrency? = null,

    // billing address
    @SerializedName("billToCustomerId")
    val billToCustomerId: String? = null,
    @SerializedName("billToContactName")
    val billToContactName: String? = null,
    @SerializedName("billToPhoneNumber")
    val billToPhoneNumber: String? = null,
    @SerializedName("billToEmail")
    val billToEmail: String? = null,
    @SerializedName("billToStreet1")
    val billToStreet1: String? = null,
    @SerializedName("billToStreet2")
    val billToStreet2: String? = null,
    @SerializedName("billToCity")
    val billToCity: String? = null,
    @SerializedName("billToState")
    val billToState: String? = null,
    @SerializedName("billToZipCode")
    val billToZipCode: String? = null,
    @SerializedName("billToCountry")
    val billToCountry: String? = null,

    // shipping address
    @SerializedName("shipToSameAsBillTo")
    val shipToSameAsBillTo: Boolean? = null,
    @SerializedName("shipToAddressee")
    val shipToAddressee: String? = null,
    @SerializedName("shipToPhoneNumber")
    val shipToPhoneNumber: String? = null,
    @SerializedName("shipToEmail")
    val shipToEmail: String? = null,
    @SerializedName("shipToStreet1")
    val shipToStreet1: String? = null,
    @SerializedName("shipToStreet2")
    val shipToStreet2: String? = null,
    @SerializedName("shipToCity")
    val shipToCity: String? = null,
    @SerializedName("shipToState")
    val shipToState: String? = null,
    @SerializedName("shipToZipCode")
    val shipToZipCode: String? = null,
    @SerializedName("shipToShippingMethod")
    val shipToShippingMethod: String? = null,
    @SerializedName("shipToFirstUsageDate")
    val shipToFirstUsageDate: String? = null,
    @SerializedName("shipToCountry")
    val shipToCountry: String? = null,

    @SerializedName("cart")
    val cart: List<CartItemData>? = null,

    @SerializedName("deviceIpAddress")
    val deviceIpAddress: String? = null,

    @SerializedName("device")
    val device: List<DeviceData>? = null,

    @SerializedName("orderRecurrence")
    val orderRecurrence: Boolean? = null,
    @SerializedName("orderProductCode")
    val orderProductCode: String? = null,
    @SerializedName("orderCountLast24Hours")
    val orderCountLast24Hours: Int? = null,
    @SerializedName("orderCountLast6Months")
    val orderCountLast6Months: Int? = null,
    @SerializedName("orderCountLast1Year")
    val orderCountLast1Year: Int? = null,
    @SerializedName("orderCardAttemptsLast24Hours")
    val orderCardAttemptsLast24Hours: Int? = null,
    @SerializedName("orderMarketingOptin")
    val orderMarketingOptin: Boolean? = null,
    @SerializedName("orderMarketingSource")
    val orderMarketingSource: String? = null,

    // user data
    @SerializedName("userAccountGuest")
    val userAccountGuest: Boolean? = null,
    @SerializedName("userAccountCreatedDate")
    val userAccountCreatedDate: String? = null,
    @SerializedName("userAccountChangedDate")
    val userAccountChangedDate: String? = null,
    @SerializedName("userAccountPasswordChangedDate")
    val userAccountPasswordChangedDate: String? = null,
    @SerializedName("userAccountAuthenticationMethod")
    val userAccountAuthenticationMethod: String? = null,
    @SerializedName("userAccountAuthenticationProtocol")
    val userAccountAuthenticationProtocol: String? = null,
    @SerializedName("userAccountAuthenticationTimestamp")
    val userAccountAuthenticationTimestamp: String? = null,

    // airline company info
    @SerializedName("airlineTravelLeg")
    val airlineTravelLeg: List<TravelLeg>? = null,
    @SerializedName("airlinePassenger")
    val airlinePassenger: List<Passenger>? = null,
    @SerializedName("airlineNumberOfPassengers")
    val airlineNumberOfPassengers: Long? = null,
    @SerializedName("airlineBillToPassportCountry")
    val airlineBillToPassportCountry: String? = null,
    @SerializedName("airlineBillToPassportNumber")
    val airlineBillToPassportNumber: String? = null,

    // extra data
    @SerializedName("mdd1")
    val mdd1: String? = null,
    @SerializedName("mdd2")
    val mdd2: String? = null,
    @SerializedName("mdd3")
    val mdd3: String? = null,
    @SerializedName("mdd4")
    val mdd4: String? = null,
    @SerializedName("mdd5")
    val mdd5: String? = null,

    // auth options
    @SerializedName("authNotifyOnly")
    val authNotifyOnly: Boolean? = null,

    @SerializedName("authSuppressChallenge")
    val authSuppressChallenge: Boolean? = null,

    @SerializedName("recurringEndDate")
    val recurringEndDate: String? = null,
    @SerializedName("recurringFrequency")
    val recurringFrequency: Int? = null,
    @SerializedName("recurringOriginalPurchaseDate")
    val recurringOriginalPurchaseDate: String? = null,
) : Serializable {
    companion object {
        fun createInstance(
            order: OrderData,
            card: CardData,
            giftCard: GiftCardData?,
            authOptions: OptionsData?,
            billTo: BillToData?,
            shipTo: ShipToData?,
            cart: List<CartItemData>? = null,
            device: List<DeviceData>? = null,
            user: UserData?,
            airline: AirlineData?,
            mdd: MddData?,
            recurring: RecurringData?,
            deviceIpAddress: String?,
        ) = EnrollData(
            orderNumber = order.orderNumber,
            currency = order.currencyCode,
            totalAmount = order.totalAmount,
            paymentMethod = order.paymentMethod,
            installments = order.installments,
            orderRecurrence = order.recurrence,
            orderProductCode = order.productCode?.value,
            orderCountLast24Hours = order.countLast24Hours,
            orderCountLast6Months = order.countLast6Months,
            orderCountLast1Year = order.countLast1Year,
            orderCardAttemptsLast24Hours = order.cardAttemptsLast24Hours,
            orderMarketingOptin = order.marketingOptIn,
            orderMarketingSource = order.marketingSource,
            transactionMode = order.transactionMode?.value,
            merchantUrl = order.merchantUrl,

            cardNumber = card.number,
            cardExpirationMonth = card.expirationMonth,
            cardExpirationYear = card.expirationYear,
            cardAlias = card.cardAlias,
            defaultCard = card.defaultCard,

            giftCardAmount = giftCard?.giftCardAmount,
            giftCardCurrency = giftCard?.giftCardCurrency,

            authNotifyOnly = authOptions?.notifyOnly,
            authSuppressChallenge = authOptions?.suppressChallenge,

            billToCustomerId = billTo?.customerId,
            billToContactName = billTo?.contactName,
            billToPhoneNumber = billTo?.phoneNumber,
            billToEmail = billTo?.email,
            billToStreet1 = billTo?.street1,
            billToStreet2 = billTo?.street2,
            billToCity = billTo?.city,
            billToState = billTo?.state,
            billToZipCode = billTo?.zipCode,
            billToCountry = billTo?.country,

            shipToSameAsBillTo = shipTo?.sameAsBillTo,
            shipToAddressee = shipTo?.addressee,
            shipToPhoneNumber = shipTo?.phoneNumber,
            shipToEmail = shipTo?.email,
            shipToStreet1 = shipTo?.street1,
            shipToStreet2 = shipTo?.street2,
            shipToCity = shipTo?.city,
            shipToState = shipTo?.state,
            shipToZipCode = shipTo?.zipCode,
            shipToShippingMethod = shipTo?.shippingMethod,
            shipToFirstUsageDate = shipTo?.firstUsageDate,
            shipToCountry = shipTo?.country,

            cart = cart,
            device = device,

            userAccountGuest = user?.guest,
            userAccountCreatedDate = user?.createdDate,
            userAccountChangedDate = user?.changedDate,
            userAccountPasswordChangedDate = user?.passwordChangedDate,
            userAccountAuthenticationMethod = user?.authenticationMethod?.value,
            userAccountAuthenticationProtocol = user?.authenticationProtocol,
            userAccountAuthenticationTimestamp = user?.authenticationTimestamp,

            merchantNewCustomer = user?.newCustomer,

            airlineTravelLeg = airline?.travelLeg,
            airlinePassenger = airline?.passenger,
            airlineNumberOfPassengers = airline?.numberOfPassengers,
            airlineBillToPassportCountry = airline?.billToPassportCountry,
            airlineBillToPassportNumber = airline?.billToPassportNumber,

            recurringEndDate = recurring?.endDate,
            recurringFrequency = recurring?.frequency?.value,
            recurringOriginalPurchaseDate = recurring?.originalPurchaseDate,

            mdd1 = mdd?.mdd1,
            mdd2 = mdd?.mdd2,
            mdd3 = mdd?.mdd3,
            mdd4 = mdd?.mdd4,
            mdd5 = mdd?.mdd5,

            deviceIpAddress = deviceIpAddress,
        )
    }
}
