package br.com.braspag.internal.data

import br.com.braspag.data.*

internal data class EnrollData(

    // order data
    val transactionId: String? = null,
    val transactionMode: String? = null,
    val merchantUrl: String? = null,
    val merchantNewCustomer: Boolean? = null,
    val orderNumber: String, // required
    val currency: String, // required
    val totalAmount: Long? = null,
    val installments: Int? = null,
    val paymentMethod: PaymentMethod? = null,

    // card
    val cardNumber: String, // required
    val cardExpirationMonth: String, // required
    val cardExpirationYear: String,  // required
    val cardAlias: String? = null,
    val defaultCard: Boolean? = null,
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
    val shipToSameAsBillTo: Boolean? = null,
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
    val orderProductCode: String? = null,
    val orderCountLast24Hours: Int? = null,
    val orderCountLast6Months: Int? = null,
    val orderCountLast1Year: Int? = null,
    val orderCardAttemptsLast24Hours: Int? = null,
    val orderMarketingOptin: Boolean? = null,
    val orderMarketingSource: String? = null,

    // user data
    val userAccountGuest: Boolean? = null,
    val userAccountCreatedDate: String? = null,
    val userAccountChangedDate: String? = null,
    val userAccountPasswordChangedDate: String? = null,
    val userAccountAuthenticationMethod: String? = null,
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
    val authNotifyOnly: Boolean? = null,

    val authSuppressChallenge: Boolean? = null,

    val recurringEndDate: String? = null,
    val recurringFrequency: Int? = null,
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

            deviceIpAddress = deviceIpAddress
        )
    }
}