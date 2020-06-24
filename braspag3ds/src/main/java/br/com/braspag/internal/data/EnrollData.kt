package br.com.braspag.internal.data

import br.com.braspag.data.*
import com.google.gson.annotations.SerializedName

internal data class EnrollData(

    // order data

    val transactionId: String? = null,
    val transactionMode: TransactionMode? = null,

    @SerializedName("merchanturl")
    val merchantUrl: String? = null,
    val merchantNewCustomer: Boolean? = null,

    @SerializedName("OrderNumber")
    val orderNumber: String, // required

    @SerializedName("Currency")
    val currency: String, // required

    @SerializedName("totalamount")
    val totalAmount: Long? = null,

    val installments: Int? = null,

    @SerializedName("cardnumber")
    val cardNumber: String, // required

    @SerializedName("cardexpirationmonth")
    val cardExpirationMonth: String, // required

    @SerializedName("cardexpirationyear")
    val cardExpirationYear: String,  // required

    val cardAlias: String? = null,
    val defaultCard: Boolean? = null,

    @SerializedName("paymentmethod")
    val paymentMethod: PaymentMethod? = null,

    val cardAddedDate: String? = null,

    // billing address
    val billToCustomerId: String? = null,
    val billToContactName: String? = null,
    val billToPhoneNumber: String? = null,
    val billToEmail: String? = null,
    val billToStreet1: String? = null,
    val billToStreet2: String? = null,
    val billToCity: String? = null,
    val billToState: String? = null,
    val billToZipCode: String? = null,
    val billToCountry: String? = null,

    // shipping address
    val shipToSameAddressAsBillTo: Boolean? = null,
    val shipToAddressee: String? = null,
    val shipToPhoneNumber: String? = null,
    val shipToEmail: String? = null,
    val shipToStreet1: String? = null,
    val shipToStreet2: String? = null,
    val shipToCity: String? = null,
    val shipToState: String? = null,
    val shipToZipCode: String? = null,
    val shipToShippingMethod: String? = null,
    val shipToFirstUsageDate: String? = null,
    val shipToCountry: String? = null,

    val cart: List<CartItemData>? = null,

    val deviceIpAddress: String? = null,

    val device: List<DeviceData>? = null,

    val orderRecurrence: Boolean? = null,
    val orderProductCode: ProductCode? = null,
    val orderCountLast24Hours: Int? = null,
    val orderCountLast6Months: Int? = null,
    val orderCountLast1Year: Int? = null,
    val orderCardAttemptsLast24Hours: Int? = null,
    val orderMarketingOptin: Boolean? = null,
    val orderMarketSource: String? = null,

    // user data
    val userAccountGuest: Boolean? = null,
    val userAccountCreateDate: String? = null,
    val userAccountChangeDate: String? = null,
    val userAccountPasswordChangedDate: String? = null,
    val userAccountAuthenticationMethod: AuthenticationMethod? = null,
    val userAccountAuthenticationProtocol: String? = null,
    val userAccountAuthenticationTimestamp: String? = null,

    // airline company info
    val airlineTravelLeg: List<TravelLeg>? = null,
    val airlinePassenger: List<Passenger>? = null,
    val airlineNumberOfPassengers: Long? = null,
    val airlineBillToPassportCountry: String? = null,
    val airlineBillToPassportNumber: String? = null,

    // extra data
    val mdd1: String? = null,
    val mdd2: String? = null,
    val mdd3: String? = null,
    val mdd4: String? = null,
    val mdd5: String? = null,

    // auth options
    @SerializedName("authnotifyonly")
    val authNotifyOnly: Boolean? = null,

    @SerializedName("authsuppresschallenge")
    val authSuppressChallenge: Boolean? = null,

    val recurringEndDate: String? = null,
    val recurringFrequency: RecurringFrequency? = null,
    val recurringOriginalPurchaseDate: String? = null

) {
    companion object {

        fun createInstance(
            order: OrderData,
            card: CardData,
            authOptions: OptionsData?,
            billTo: BillToData?,
            shipTo: ShipToData?,
            cart: List<CartItemData>? = null,
            device: List<DeviceData>? = null,
            user: UserData?,
            airline: AirlineData?,
            mdd: MddData?,
            recurring: RecurringData?,
            deviceIpAddress: String?
        ) = EnrollData(
            orderNumber = order.orderNumber,
            currency = order.currencyCode,
            totalAmount = order.totalAmount,
            paymentMethod = order.paymentMethod,
            installments = order.installments,
            orderRecurrence = order.recurrence,
            orderProductCode = order.productCode,
            orderCountLast24Hours = order.countLast24Hours,
            orderCountLast6Months = order.countLast6Months,
            orderCountLast1Year = order.countLast1Year,
            orderCardAttemptsLast24Hours = order.cardAttemptsLast24Hours,
            orderMarketingOptin = order.marketingOptIn,
            orderMarketSource = order.marketingSource,
            transactionMode = order.transactionMode,
            merchantUrl = order.merchantUrl,

            cardNumber = card.number,
            cardExpirationMonth = card.expirationMonth,
            cardExpirationYear = card.expirationYear,
            cardAlias = card.cardAlias,
            defaultCard = card.defaultCard,

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

            shipToSameAddressAsBillTo = shipTo?.sameAsBillTo,
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
            userAccountCreateDate = user?.createdDate,
            userAccountChangeDate = user?.changedDate,
            userAccountPasswordChangedDate = user?.passwordChangedDate,
            userAccountAuthenticationMethod = user?.authenticationMethod,
            userAccountAuthenticationProtocol = user?.authenticationProtocol,
            userAccountAuthenticationTimestamp = user?.authenticationTimestamp,

            merchantNewCustomer = user?.newCustomer,

            airlineTravelLeg = airline?.travelLeg,
            airlinePassenger = airline?.passenger,
            airlineNumberOfPassengers = airline?.numberOfPassengers,
            airlineBillToPassportCountry = airline?.billToPassportCountry,
            airlineBillToPassportNumber = airline?.billToPassportNumber,

            recurringEndDate = recurring?.endDate,
            recurringFrequency = recurring?.frequency,
            recurringOriginalPurchaseDate = recurring?.originalPurchaseDate,

            mdd1 = mdd?.mdd1,
            mdd2 = mdd?.mdd2,
            mdd3 = mdd?.mdd3,
            mdd4 = mdd?.mdd4,
            mdd5 = mdd?.mdd5,

            deviceIpAddress = deviceIpAddress
        )
    }
}