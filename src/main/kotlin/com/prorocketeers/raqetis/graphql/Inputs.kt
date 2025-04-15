// src/main/kotlin/com/example/demo/graphql/Inputs.kt
package com.prorocketeers.raqetis.graphql

import com.prorocketeers.raqetis.model.*
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

// Vstupní data pro entity Address
data class AddressInput(
    val street: String,
    val city: String,
    val state: String,
    val postalCode: String,
    val country: String,
    val latitude: BigDecimal,
    val longitude: BigDecimal,
    val addressType: AddressType
)

// Vstupní data pro entity Contact
data class ContactInput(
    val contactName: String,
    val contactType: ContactType,
    val contactValue: String,
    val isPrimary: Boolean
)

// Vstupní data pro entity Company
data class CompanyInput(
    val companyName: String,
    val registrationNumber: String?,
    val taxID: String?,
    val industry: String?,
    val addressID: Int?
)

// Vstupní data pro join entitu CompaniesContacts
data class CompaniesContactsInput(
    val companyId: Int,
    val contactId: Int
)

// Vstupní data pro join entitu ExpertContacts
data class ExpertContactsInput(
    val expertId: Int,
    val contactId: Int
)

// Vstupní data pro entitu Expertise
data class ExpertiseInput(
    val name: String,
    val type: SeniorityType,
    val description: String?
)

// Vstupní data pro entitu Expert
data class ExpertInput(
    val firstName: String,
    val lastName: String,
    val personalID: String?,
    val birthDate: LocalDate?,
    val addressID: Int?,
    val contactID: Int?,
    val email: String,
    val specialization: String?,
    val level: SeniorityLevel,
    val marketHourlyRate: BigDecimal,
    val marketDailyRate: BigDecimal,
    val educationLevel: String?,
)

// Vstupní data pro entitu Payment
data class PaymentInput (
    val expertID: Int,
    val paymentDate: String,
    val periodStart: String,
    val periodEnd: String,
    val grossAmount: Float,
    val netAmount: Float?,
    val taxAmount: Float?,
    val bonus: Float?,
    val reimbursement: Float?,
    val currency: String,
    val paymentStatus: PaymentStatus,
    val paymentMethod: PaymentMethod,
    val notes: String?
)

// Vstupní data pro entitu ExpertVehicles
data class ExpertVehiclesInput(
    val expertID: Int,
    val brand: String,
    val model: String,
    val color: String,
    val licensePlate: String
)

// Vstupní data pro entitu CompanyDevices
data class CompanyDevicesInput(
    val deviceType: String,
    val name: String?,
    val serialNumber: String?,
    val purchasePrice: BigDecimal,
    val assignedExpertID: Int?
)

// Vstupní data pro entitu ExpertExpertise
data class ExpertExpertiseInput(
    val expertID: Int,
    val expertiseID: Int,
    val level: SeniorityLevel,
    val acquiredDate: LocalDateTime?,
    val certificate: String?
)

// Vstupní data pro entitu ExpertBenefits
data class ExpertBenefitsInput(
    val expertID: Int,
    val benefitName: String,
    val benefitType: BenefitType,
    val period: BenefitPeriod,
    val cost: BigDecimal
)

// Vstupní data pro entitu Teams
data class TeamsInput(
    val teamName: String,
    val teamOfficeId: String?,
    val teamLeaderID: Int?
)

// Vstupní data pro join entitu TeamExperts
data class TeamExpertsInput(
    val teamID: Int,
    val expertID: Int,
    val assignedAt: LocalDateTime? = null
)

// Vstupní data pro entitu CompanyCustomerContracts
data class CompanyCustomerContractsInput(
    val companyID: Int,
    val contractType: CustomerContractType,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime?,
    val totalValue: BigDecimal,
    val contractURL: String?
)

// Vstupní data pro entitu CompanyInternalContracts
data class CompanyInternalContractsInput(
    val companyID: Int,
    val contractName: String,
    val contractType: InternalContractType,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime?,
    val monthlyCost: BigDecimal,
    val contractURL: String?
)

// Vstupní data pro entitu ExpertOrders
data class ExpertOrdersInput(
    val customerContractID: Int,
    val expertID: Int,
    val orderDate: LocalDateTime,
    val totalPrice: BigDecimal
)

// Vstupní data pro entitu Pricing
data class PricingInput(
    val companyID: Int?,
    val expertID: Int?,
    val teamID: Int?,
    val offeredPrice: BigDecimal,
    val margin: BigDecimal,
    val validFrom: LocalDateTime,
    val validTo: LocalDateTime?,
    val pricingVersion: Int? = 1
)

// Vstupní data pro entitu Assignment
data class AssignmentInput(
    val expertID: Int,
    val companyID: Int,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime?,
    val description: String?,
    val pricingID: Int
)

// Vstupní data pro entitu AssignmentExpertise
data class AssignmentExpertiseInput(
    val assignmentID: Int,
    val expertiseID: Int,
    val type: AssignmentExpertiseType
)

// Vstupní data pro entitu AuditLogs
data class AuditLogsInput(
    val tableName: String,
    val operationType: OperationType,
    val recordID: Int,
    val changedAt: LocalDateTime? = null,
    val changedBy: String?
)
