package com.prorocketeers.raqetis.model

import jakarta.persistence.*
import java.io.Serializable
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

// ===== Enumy =====
enum class AddressType { Home, Work, Billing, Shipping }
enum class ContactType { Phone, Email, Other, Website }
enum class SeniorityType { Hard, Soft }
enum class SeniorityLevel { Junior, Medior, Senior, Expert }
enum class BenefitType { Material, NonMaterial }
enum class BenefitPeriod { Daily, Monthly, Yearly }
enum class ContractType { Framework, Order, Subscription, Service, Other}
enum class AssignmentExpertiseType { Used, Acquired }
enum class OperationType { INSERT, UPDATE, DELETE }
enum class PaymentStatus { Pending, Paid, Failed, Canceled }
enum class PaymentMethod { BankTransfer, Cash, Crypto, Other }


// ===== Entity: Address =====
@Entity
@Table(name = "Addresses")
open class Address(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val addressID: Int = 0,
    open val street: String = "",
    open val city: String = "",
    open val state: String = "",
    open val postalCode: String = "",
    open val country: String = "",
    open val latitude: BigDecimal = BigDecimal.ZERO,
    open val longitude: BigDecimal = BigDecimal.ZERO,
    @Enumerated(EnumType.STRING)
    open val addressType: AddressType = AddressType.Home,
    open val createdAt: LocalDateTime = LocalDateTime.now(),
    open val updatedAt: LocalDateTime = LocalDateTime.now()
) {
    // Bezparametrický konstruktor pro JPA
    protected constructor() : this(
        0, "", "", "", "", "",
        BigDecimal.ZERO, BigDecimal.ZERO,
        AddressType.Home, LocalDateTime.now(), LocalDateTime.now()
    )

    fun copy(
        street: String = this.street,
        city: String = this.city,
        state: String = this.state,
        postalCode: String = this.postalCode,
        country: String = this.country,
        latitude: BigDecimal = this.latitude,
        longitude: BigDecimal = this.longitude,
        addressType: AddressType = this.addressType,
        updatedAt: LocalDateTime = LocalDateTime.now()
    ): Address {
        return Address(
            addressID = this.addressID,
            street = street,
            city = city,
            state = state,
            postalCode = postalCode,
            country = country,
            latitude = latitude,
            longitude = longitude,
            addressType = addressType,
            createdAt = this.createdAt,
            updatedAt = updatedAt
        )
    }

    @OneToMany(mappedBy = "address", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    open var companies: MutableList<Company> = mutableListOf()

    @OneToMany(mappedBy = "address", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    open var experts: MutableList<Expert> = mutableListOf()
}

// ===== Entity: Contact =====
@Entity
@Table(name = "Contacts")
open class Contact(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val contactID: Int = 0,
    open val contactName: String = "",
    @Enumerated(EnumType.STRING)
    open val contactType: ContactType = ContactType.Phone,
    open val contactValue: String = "",
    open val isPrimary: Boolean = false,
    open val createdAt: LocalDateTime = LocalDateTime.now(),
    open val updatedAt: LocalDateTime = LocalDateTime.now()
) {
    protected constructor() : this(0, "", ContactType.Phone, "", false, LocalDateTime.now(), LocalDateTime.now())

    fun copy(
        contactName: String = this.contactName,
        contactType: ContactType = this.contactType,
        contactValue: String = this.contactValue,
        isPrimary: Boolean = this.isPrimary,
        updatedAt: LocalDateTime = LocalDateTime.now()
    ): Contact {
        return Contact(
            contactID = this.contactID, // zachováváme původní ID
            contactName = contactName,
            contactType = contactType,
            contactValue = contactValue,
            isPrimary = isPrimary,
            createdAt = this.createdAt, // zachováváme původní datum vytvoření
            updatedAt = updatedAt
        )
    }

    @OneToMany(mappedBy = "contact", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    open var expertContacts: MutableList<ExpertContacts> = mutableListOf()

    @OneToMany(mappedBy = "contact", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    open var companiesContacts: MutableList<CompaniesContacts> = mutableListOf()

    @OneToMany(mappedBy = "contact", fetch = FetchType.LAZY)
    open var experts: MutableList<Expert> = mutableListOf()
}

// ===== Entity: Company =====
@Entity
@Table(name = "Companies")
open class Company(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val companyID: Int = 0,
    open val companyName: String = "",
    @Column(unique = true)
    open val registrationNumber: String? = null,
    @Column(unique = true)
    open val taxID: String? = null,
    open val industry: String? = null,
    open val addressID: Int? = null,
    open val createdAt: LocalDateTime = LocalDateTime.now(),
    open val updatedAt: LocalDateTime = LocalDateTime.now()
) {
    protected constructor() : this(0, "", null, null, null, null, LocalDateTime.now(), LocalDateTime.now())

    fun copy(
        companyName: String = this.companyName,
        registrationNumber: String? = this.registrationNumber,
        taxID: String? = this.taxID,
        industry: String? = this.industry,
        addressID: Int? = this.addressID,
        updatedAt: LocalDateTime = LocalDateTime.now()
    ): Company {
        return Company(
            companyID = this.companyID,
            companyName = companyName,
            registrationNumber = registrationNumber,
            taxID = taxID,
            industry = industry,
            addressID = addressID,
            createdAt = this.createdAt,
            updatedAt = updatedAt
        )
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "addressID", insertable = false, updatable = false)
    open var address: Address? = null

    @OneToMany(mappedBy = "company", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    open var companiesContacts: MutableList<CompaniesContacts> = mutableListOf()

    @OneToMany(mappedBy = "company", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    open var companyCustomerContracts: MutableList<CompanyCustomerContracts> = mutableListOf()

    @OneToMany(mappedBy = "company", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    open var companyInternalContracts: MutableList<CompanySuppliersContracts> = mutableListOf()

    @OneToMany(mappedBy = "company", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    open var pricing: MutableList<Pricing> = mutableListOf()

    @OneToMany(mappedBy = "company", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    open var assignments: MutableList<Assignment> = mutableListOf()
}

// ===== Join Entity: CompaniesContacts =====
@Entity
@Table(name = "CompaniesContacts")
@IdClass(CompaniesContactsId::class)
open class CompaniesContacts(
    @Id
    open val companyId: Int = 0,

    @Id
    open val contactId: Int = 0
) : Serializable {
    protected constructor() : this(0, 0)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "companyId", insertable = false, updatable = false)
    open var company: Company? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contactId", insertable = false, updatable = false)
    open var contact: Contact? = null
}

data class CompaniesContactsId(
    var companyId: Int = 0,
    var contactId: Int = 0
) : Serializable


// ===== Join Entity: ExpertContacts =====
@Entity
@Table(name = "ExpertContacts")
@IdClass(ExpertContactsId::class)
open class ExpertContacts(
    @Id
    open val expertId: Int = 0,

    @Id
    open val contactId: Int = 0
) : Serializable {
    protected constructor() : this(0, 0)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expertId", insertable = false, updatable = false)
    open var expert: Expert? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contactId", insertable = false, updatable = false)
    open var contact: Contact? = null
}

data class ExpertContactsId(
    var expertId: Int = 0,
    var contactId: Int = 0
) : Serializable


// ===== Entity: Expertise =====
@Entity
@Table(name = "Expertise")
open class Expertise(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val expertiseID: Int = 0,
    open val name: String = "",
    @Enumerated(EnumType.STRING)
    open val type: SeniorityType = SeniorityType.Hard,
    open val description: String? = null,
    open val createdAt: LocalDateTime = LocalDateTime.now()
) {
    protected constructor() : this(0, "", SeniorityType.Hard, null, LocalDateTime.now())

    fun copy(
        name: String = this.name,
        type: SeniorityType = this.type,
        description: String? = this.description,
        createdAt: LocalDateTime = this.createdAt
    ): Expertise {
        return Expertise(
            expertiseID = this.expertiseID,
            name = name,
            type = type,
            description = description,
            createdAt = createdAt
        )
    }

    @OneToMany(mappedBy = "expertise", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    open var expertExpertise: MutableList<ExpertExpertise> = mutableListOf()

    @OneToMany(mappedBy = "expertise", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    open var assignmentExpertise: MutableList<AssignmentExpertise> = mutableListOf()
}

// ===== Entity: Expert =====
@Entity
@Table(name = "Experts")
open class Expert(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val expertID: Int = 0,
    open val firstName: String = "",
    open val lastName: String = "",
    open val personalID: String? = null,
    open val birthDate: LocalDate? = null, // změněno z LocalDateTime? na LocalDate?
    open val addressID: Int? = null,
    open val contactID: Int? = null,
    open val email: String = "",
    open val specialization: String? = null,
    @Enumerated(EnumType.STRING)
    open val level: SeniorityLevel,
    open val marketHourlyRate: BigDecimal = BigDecimal.ZERO,
    open val marketDailyRate: BigDecimal = BigDecimal.ZERO,
    open val educationLevel: String? = null,
    open val createdAt: LocalDateTime = LocalDateTime.now()
) {
    protected constructor() : this(
        0, "", "", null, null, null, null, "",
        null, SeniorityLevel.Medior,  BigDecimal.ZERO, BigDecimal.ZERO, null, LocalDateTime.now()
    )

    fun copy(
        firstName: String = this.firstName,
        lastName: String = this.lastName,
        personalID: String? = this.personalID,
        birthDate: LocalDate? = this.birthDate,
        addressID: Int? = this.addressID,
        contactID: Int? = this.contactID,
        email: String = this.email,
        specialization: String? = this.specialization,
        level: SeniorityLevel = this.level,
        marketHourlyRate: BigDecimal = this.marketHourlyRate,
        marketDailyRate: BigDecimal = this.marketDailyRate,
        educationLevel: String? = this.educationLevel,
        createdAt: LocalDateTime = this.createdAt
    ): Expert {
        return Expert(
            expertID = this.expertID,
            firstName = firstName,
            lastName = lastName,
            personalID = personalID,
            birthDate = birthDate,
            addressID = addressID,
            contactID = contactID,
            email = email,
            specialization = specialization,
            level = level,
            marketHourlyRate = marketHourlyRate,
            marketDailyRate = marketDailyRate,
            educationLevel = educationLevel,
            createdAt = createdAt
        )
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "addressID", insertable = false, updatable = false)
    open var address: Address? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contactID", insertable = false, updatable = false)
    open var contact: Contact? = null

    @OneToMany(mappedBy = "expert", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    open var expertContacts: MutableList<ExpertContacts> = mutableListOf()

    @OneToMany(mappedBy = "expert", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    open var expertVehicles: MutableList<ExpertVehicles> = mutableListOf()

    @OneToMany(mappedBy = "expert", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    open var expertExpertise: MutableList<ExpertExpertise> = mutableListOf()

    @OneToMany(mappedBy = "expert", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    open var expertBenefits: MutableList<ExpertBenefits> = mutableListOf()

    @OneToMany(mappedBy = "expert", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    open var teamExperts: MutableList<TeamExperts> = mutableListOf()

    @OneToMany(mappedBy = "expert", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    open var expertOrders: MutableList<ExpertOrders> = mutableListOf()

    @OneToMany(mappedBy = "expert", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    open var assignments: MutableList<Assignment> = mutableListOf()

    @OneToMany(mappedBy = "expert", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    open var companyDevices: MutableList<CompanyDevices> = mutableListOf()

    @OneToMany(mappedBy = "expert", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    open var payments: MutableList<Payment> = mutableListOf()

    // Aktualizováno: název join tabulky změněn na "TeamExperts", aby odpovídal initDB.sql
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "TeamExperts",
        joinColumns = [JoinColumn(name = "expertID")],
        inverseJoinColumns = [JoinColumn(name = "teamID")]
    )
    open var teams: MutableList<Teams> = mutableListOf()

    @OneToMany(mappedBy = "expert", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    open var pricing: MutableList<Pricing> = mutableListOf()
}

// ===== Entity: Payment =====
@Entity
@Table(name = "Payments")
open class Payment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val paymentID: Int = 0,
    open val expertID: Int,
    open val paymentDate: LocalDate,
    open val periodStart: LocalDate,
    open val periodEnd: LocalDate,
    open val grossAmount: BigDecimal,
    open val netAmount: BigDecimal? = null,
    open val taxAmount: BigDecimal? = null,
    open val bonus: BigDecimal = BigDecimal.ZERO,
    open val reimbursement: BigDecimal = BigDecimal.ZERO,
    open val currency: String = "",
    open val paymentStatus: PaymentStatus,
    open val paymentMethod: PaymentMethod,
    open val notes: String? = null,
    open val createdAt: LocalDateTime = LocalDateTime.now(),
    open val updatedAt: LocalDateTime = LocalDateTime.now()
) {
    protected constructor() : this(
        0, 0, LocalDate.now(), LocalDate.now(), LocalDate.now(), BigDecimal.ZERO,
        null, null, BigDecimal.ZERO, BigDecimal.ZERO, "CZK", PaymentStatus.Pending,
        PaymentMethod.BankTransfer, null, LocalDateTime.now(), LocalDateTime.now()
    )

    fun copy(
        expertID: Int = this.expertID,
        paymentDate: LocalDate = this.paymentDate,
        periodStart: LocalDate = this.periodStart,
        periodEnd: LocalDate = this.periodEnd,
        grossAmount: BigDecimal = this.grossAmount,
        netAmount: BigDecimal? = this.netAmount,
        taxAmount: BigDecimal? = this.taxAmount,
        bonus: BigDecimal = this.bonus,
        reimbursement: BigDecimal = this.reimbursement,
        currency: String = this.currency,
        paymentStatus: PaymentStatus = this.paymentStatus,
        paymentMethod: PaymentMethod = this.paymentMethod,
        notes: String? = this.notes,
        createdAt: LocalDateTime = this.createdAt,
        updatedAt: LocalDateTime = this.updatedAt
    ): Payment {
        return Payment(
            paymentID = this.paymentID,
            expertID = expertID,
            paymentDate = paymentDate,
            periodStart = periodStart,
            periodEnd = periodEnd,
            grossAmount = grossAmount,
            netAmount = netAmount,
            taxAmount = taxAmount,
            bonus = bonus,
            reimbursement = reimbursement,
            currency = currency,
            paymentStatus = paymentStatus,
            paymentMethod = paymentMethod,
            notes = notes,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expertID", insertable = false, updatable = false)
    open var expert: Expert? = null
}


// ===== Entity: ExpertVehicles =====
@Entity
@Table(name = "ExpertVehicles")
open class ExpertVehicles(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val vehicleID: Int = 0,
    open val expertID: Int,
    open val brand: String = "",
    open val model: String = "",
    open val color: String = "",
    open val licensePlate: String = ""
) {
    protected constructor() : this(0, 0, "", "", "", "")

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expertID", insertable = false, updatable = false)
    open var expert: Expert? = null
}

// ===== Entity: CompanyDevices =====
@Entity
@Table(name = "CompanyDevices")
open class CompanyDevices(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val deviceID: Int = 0,
    open val deviceType: String = "",
    open val name: String? = null,
    open val serialNumber: String? = null,
    open val purchasePrice: BigDecimal,
    open val assignedExpertID: Int? = null
) {
    protected constructor() : this(0, "", null, null, BigDecimal.ZERO, null)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignedExpertID", insertable = false, updatable = false)
    open var expert: Expert? = null
}

// ===== Entity: ExpertExpertise =====
@Entity
@Table(name = "ExpertExpertise")
open class ExpertExpertise(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val expertExpertiseID: Int = 0,
    open val expertID: Int,
    open val expertiseID: Int,
    @Enumerated(EnumType.STRING)
    open val level: SeniorityLevel,
    open val acquiredDate: LocalDateTime? = null,
    open val certificate: String? = null
) {
    protected constructor() : this(0, 0, 0, SeniorityLevel.Junior, null, null)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expertID", insertable = false, updatable = false)
    open var expert: Expert? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expertiseID", insertable = false, updatable = false)
    open var expertise: Expertise? = null
}

// ===== Entity: ExpertBenefits =====
@Entity
@Table(name = "ExpertBenefits")
open class ExpertBenefits(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val benefitID: Int = 0,
    open val expertID: Int,
    open val benefitName: String = "",
    @Enumerated(EnumType.STRING)
    open val benefitType: BenefitType,
    @Enumerated(EnumType.STRING)
    open val period: BenefitPeriod,
    open val cost: BigDecimal
) {
    protected constructor() : this(0, 0, "", BenefitType.Material, BenefitPeriod.Daily, BigDecimal.ZERO)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expertID", insertable = false, updatable = false)
    open var expert: Expert? = null
}

// ===== Entity: Teams =====
@Entity
@Table(name = "Teams")
open class Teams(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val teamID: Int = 0,
    open val teamName: String = "",
    open val teamOfficeId: String? = null,
    open val teamLeaderID: Int? = null,
    open val createdAt: LocalDateTime = LocalDateTime.now()
) {
    protected constructor() : this(0, "", null, null, LocalDateTime.now())

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teamLeaderID", insertable = false, updatable = false)
    open var leader: Expert? = null

    @OneToMany(mappedBy = "team", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    open var teamExperts: MutableList<TeamExperts> = mutableListOf()

    @OneToMany(mappedBy = "team", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    open var pricing: MutableList<Pricing> = mutableListOf()
}

// ===== Join Entity: TeamExperts =====
@Entity
@Table(name = "TeamExperts")
@IdClass(TeamExpertsId::class)
open class TeamExperts(
    @Id
    open val teamID: Int = 0,

    @Id
    open val expertID: Int = 0,

    open val assignedAt: LocalDateTime = LocalDateTime.now()
) : Serializable {
    protected constructor() : this(0, 0, LocalDateTime.now())

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teamID", insertable = false, updatable = false)
    open var team: Teams? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expertID", insertable = false, updatable = false)
    open var expert: Expert? = null
}

data class TeamExpertsId(
    var teamID: Int = 0,
    var expertID: Int = 0
) : Serializable

// ===== Entity: Contracts =====
@Entity
@Table(name = "Contracts")
open class Contract(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val contractID: Int = 0,
    open val contractNumber: String? = null,
    @Enumerated(EnumType.STRING)
    open val contractType: ContractType,
    open val title: String,
    open val validFrom: LocalDate,
    open val validTo: LocalDate? = null,
    open val documentLink: String? = null,
    open val createdAt: LocalDateTime = LocalDateTime.now(),
    open val updatedAt: LocalDateTime = LocalDateTime.now(),
    @OneToOne(mappedBy = "contract", fetch = FetchType.LAZY)
    open var customerContract: CompanyCustomerContracts? = null,
    @OneToOne(mappedBy = "contract", fetch = FetchType.LAZY)
    open var supplierContract: CompanySuppliersContracts? = null
) {
    protected constructor() : this(
        0, null, ContractType.Other, "", LocalDate.now(), null, null,
        LocalDateTime.now(), LocalDateTime.now()
    )
}

// ===== Entity: CompanyCustomerContracts =====
@Entity
@Table(name = "CompanyCustomerContracts")
open class CompanyCustomerContracts(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val customerContractID: Int = 0,
    open val contractID: Int,
    open val companyID: Int,
    open val totalValue: BigDecimal? = null
) {
    protected constructor() : this(0, 0, 0, null)

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contractID", insertable = false, updatable = false)
    open var contract: Contract? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "companyID", insertable = false, updatable = false)
    open var company: Company? = null
}


// ===== Entity: CompanySuppliersContracts =====
@Entity
@Table(name = "CompanySuppliersContracts")
open class CompanySuppliersContracts(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val internalContractID: Int = 0,
    open val contractID: Int,
    open val companyID: Int,
    open val monthlyCost: BigDecimal? = null
) {
    protected constructor() : this(0, 0, 0, null)

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contractID", insertable = false, updatable = false)
    open var contract: Contract? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "companyID", insertable = false, updatable = false)
    open var company: Company? = null
}

// ===== Entity: ExpertOrders =====
@Entity
@Table(name = "ExpertOrders")
open class ExpertOrders(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val expertOrderID: Int = 0,
    open val customerContractID: Int,
    open val expertID: Int,
    open val orderDate: LocalDateTime,
    open val totalPrice: BigDecimal,
    open val createdAt: LocalDateTime = LocalDateTime.now()
) {
    protected constructor() : this(0, 0, 0, LocalDateTime.now(), BigDecimal.ZERO, LocalDateTime.now())

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerContractID", insertable = false, updatable = false)
    open var contract: CompanyCustomerContracts? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expertID", insertable = false, updatable = false)
    open var expert: Expert? = null

}

// ===== Entity: Pricing =====
@Entity
@Table(name = "Pricing")
open class Pricing(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val pricingID: Int = 0,
    open val companyID: Int? = null,
    open val expertID: Int? = null,
    open val teamID: Int? = null,
    open val offeredPrice: BigDecimal,
    open val margin: BigDecimal,
    open val validFrom: LocalDateTime,
    open val validTo: LocalDateTime? = null,
    open val pricingVersion: Int = 1,
    open val createdAt: LocalDateTime = LocalDateTime.now()
) {
    constructor() : this(0, null, null, null, BigDecimal.ZERO, BigDecimal.ZERO, LocalDateTime.now(), null, 1, LocalDateTime.now())

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "companyID", insertable = false, updatable = false)
    open var company: Company? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expertID", insertable = false, updatable = false)
    open var expert: Expert? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teamID", insertable = false, updatable = false)
    open var team: Teams? = null

    @OneToOne(mappedBy = "pricing", optional = false, fetch = FetchType.LAZY)
    open lateinit var assignment: Assignment
}

// ===== Entity: Assignment =====
@Entity
@Table(name = "Assignments")
open class Assignment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val assignmentID: Int = 0,
    open val expertID: Int,
    open val companyID: Int,
    open val startDate: LocalDateTime,
    open val endDate: LocalDateTime? = null,
    open val description: String? = null,

    @OneToOne(optional = false, cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "pricingID", nullable = false, unique = true)
    open val pricing: Pricing
) {
    protected constructor() : this( 0, 0, 0, LocalDateTime.now(), null, null, Pricing())

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expertID", insertable = false, updatable = false)
    open var expert: Expert? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "companyID", insertable = false, updatable = false)
    open var company: Company? = null

    @OneToMany(mappedBy = "assignment", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    open var assignmentExpertise: MutableList<AssignmentExpertise> = mutableListOf()

}

// ===== Entity: AssignmentExpertise =====
@Entity
@Table(name = "AssignmentExpertise")
open class AssignmentExpertise(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val assignmentExpertiseID: Int = 0,
    open val assignmentID: Int,
    open val expertiseID: Int,
    @Enumerated(EnumType.STRING)
    open val type: AssignmentExpertiseType
) {
    protected constructor() : this(0, 0, 0, AssignmentExpertiseType.Used)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignmentID", insertable = false, updatable = false)
    open var assignment: Assignment? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "expertiseID", insertable = false, updatable = false)
    open var expertise: Expertise? = null
}

// ===== Entity: AuditLogs =====
@Entity
@Table(name = "AuditLogs")
open class AuditLogs(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val auditLogID: Int = 0,
    open val tableName: String = "",
    @Enumerated(EnumType.STRING)
    open val operationType: OperationType,
    open val recordID: Int,
    open val changedAt: LocalDateTime = LocalDateTime.now(),
    open val changedBy: String? = null
) {
    protected constructor() : this(0, "", OperationType.INSERT, 0, LocalDateTime.now(), null)
}
