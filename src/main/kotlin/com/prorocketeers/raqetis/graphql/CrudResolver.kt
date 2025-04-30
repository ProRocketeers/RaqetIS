package com.prorocketeers.raqetis.graphql

import com.prorocketeers.raqetis.model.*
import com.prorocketeers.raqetis.repository.*
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

@Controller
class CrudResolver(
    private val addressRepository: AddressRepository,
    private val contactRepository: ContactRepository,
    private val companyRepository: CompanyRepository,
    private val expertRepository: ExpertRepository,
    private val paymentRepository: PaymentRepository,
    private val companiesContactsRepository: CompaniesContactsRepository,
    private val expertContactsRepository: ExpertContactsRepository,
    private val expertiseRepository: ExpertiseRepository,
    private val expertVehiclesRepository: ExpertVehiclesRepository,
    private val companyDevicesRepository: CompanyDevicesRepository,
    private val expertExpertiseRepository: ExpertExpertiseRepository,
    private val expertBenefitsRepository: ExpertBenefitsRepository,
    private val teamsRepository: TeamsRepository,
    private val teamExpertsRepository: TeamExpertsRepository,
    private val companyCustomerContractsRepository: CompanyCustomerContractsRepository,
    private val companyInternalContractsRepository: CompanyInternalContractsRepository,
    private val expertOrdersRepository: ExpertOrdersRepository,
    private val pricingRepository: PricingRepository,
    private val assignmentRepository: AssignmentRepository,
    private val assignmentExpertiseRepository: AssignmentExpertiseRepository,
    private val auditLogsRepository: AuditLogsRepository
) {

    // ---------------------- QUERIES ----------------------
    @QueryMapping
    fun addresses(): List<Address> = addressRepository.findAll()

    @QueryMapping
    fun address(@Argument id: Int): Address? =
        addressRepository.findById(id).orElse(null)

    @QueryMapping
    fun contacts(): List<Contact> = contactRepository.findAll()

    @QueryMapping
    fun contact(@Argument id: Int): Contact? =
        contactRepository.findById(id).orElse(null)

    @QueryMapping
    fun companies(): List<Company> = companyRepository.findAll()

    @QueryMapping
    fun company(@Argument id: Int): Company? =
        companyRepository.findById(id).orElse(null)

    @QueryMapping
    fun experts(): List<Expert> = expertRepository.findAll()

    @QueryMapping
    fun expert(@Argument id: Int): Expert? =
        expertRepository.findById(id).orElse(null)

    @QueryMapping
    fun payments(): List<Payment> = paymentRepository.findAll()

    @QueryMapping
    fun payment(@Argument id: Int): Payment? =
        paymentRepository.findById(id).orElse(null)

    @QueryMapping
    fun companiesContacts(): List<CompaniesContacts> = companiesContactsRepository.findAll()

    @QueryMapping
    fun companiesContactsByIds(
        @Argument companyId: Int,
        @Argument contactId: Int
    ): CompaniesContacts? =
        companiesContactsRepository.findById(CompaniesContactsId(companyId, contactId)).orElse(null)

    @QueryMapping
    fun expertContacts(): List<ExpertContacts> = expertContactsRepository.findAll()

    @QueryMapping
    fun expertContactsByIds(
        @Argument expertId: Int,
        @Argument contactId: Int
    ): ExpertContacts? =
        expertContactsRepository.findById(ExpertContactsId(expertId, contactId)).orElse(null)

    @QueryMapping
    fun expertises(): List<Expertise> = expertiseRepository.findAll()

    @QueryMapping
    fun expertise(@Argument id: Int): Expertise? =
        expertiseRepository.findById(id).orElse(null)

    @QueryMapping
    fun expertVehicles(): List<ExpertVehicles> = expertVehiclesRepository.findAll()

    @QueryMapping
    fun expertVehicle(@Argument id: Int): ExpertVehicles? =
        expertVehiclesRepository.findById(id).orElse(null)

    @QueryMapping
    fun companyDevices(): List<CompanyDevices> = companyDevicesRepository.findAll()

    @QueryMapping
    fun companyDevice(@Argument id: Int): CompanyDevices? =
        companyDevicesRepository.findById(id).orElse(null)

    @QueryMapping
    fun expertExpertises(): List<ExpertExpertise> = expertExpertiseRepository.findAll()

    @QueryMapping
    fun expertExpertise(@Argument id: Int): ExpertExpertise? =
        expertExpertiseRepository.findById(id).orElse(null)

    @QueryMapping
    fun expertBenefits(): List<ExpertBenefits> = expertBenefitsRepository.findAll()

    @QueryMapping
    fun expertBenefit(@Argument id: Int): ExpertBenefits? =
        expertBenefitsRepository.findById(id).orElse(null)

    @QueryMapping
    fun teams(): List<Teams> = teamsRepository.findAll()

    @QueryMapping
    fun team(@Argument id: Int): Teams? =
        teamsRepository.findById(id).orElse(null)

    @QueryMapping
    fun teamExperts(): List<TeamExperts> = teamExpertsRepository.findAll()

    @QueryMapping
    fun teamExpert(
        @Argument teamID: Int,
        @Argument expertID: Int
    ): TeamExperts? =
        teamExpertsRepository.findById(TeamExpertsId(teamID, expertID)).orElse(null)

    @QueryMapping
    fun companyCustomerContracts(): List<CompanyCustomerContracts> = companyCustomerContractsRepository.findAll()

    @QueryMapping
    fun companyCustomerContract(@Argument id: Int): CompanyCustomerContracts? =
        companyCustomerContractsRepository.findById(id).orElse(null)

    @QueryMapping
    fun companyInternalContracts(): List<CompanyInternalContracts> = companyInternalContractsRepository.findAll()

    @QueryMapping
    fun companyInternalContract(@Argument id: Int): CompanyInternalContracts? =
        companyInternalContractsRepository.findById(id).orElse(null)

    @QueryMapping
    fun expertOrders(): List<ExpertOrders> = expertOrdersRepository.findAll()

    @QueryMapping
    fun expertOrder(@Argument id: Int): ExpertOrders? =
        expertOrdersRepository.findById(id).orElse(null)

    @QueryMapping
    fun pricing(): List<Pricing> = pricingRepository.findAll()

    @QueryMapping
    fun price(@Argument id: Int): Pricing? =
        pricingRepository.findById(id).orElse(null)

    @QueryMapping
    fun assignments(): List<Assignment> = assignmentRepository.findAll()

    @QueryMapping
    fun assignment(@Argument id: Int): Assignment? =
        assignmentRepository.findById(id).orElse(null)

    @QueryMapping
    fun assignmentExpertises(): List<AssignmentExpertise> = assignmentExpertiseRepository.findAll()

    @QueryMapping
    fun assignmentExpertise(@Argument id: Int): AssignmentExpertise? =
        assignmentExpertiseRepository.findById(id).orElse(null)

    @QueryMapping
    fun auditLogs(): List<AuditLogs> = auditLogsRepository.findAll()

    @QueryMapping
    fun auditLog(@Argument id: Int): AuditLogs? =
        auditLogsRepository.findById(id).orElse(null)

    // ---------------------- MUTATIONS ----------------------
    // Address Mutations
    @MutationMapping
    fun createAddress(@Argument input: AddressInput): Address {
        val address = Address(
            street = input.street,
            city = input.city,
            state = input.state,
            postalCode = input.postalCode,
            country = input.country,
            latitude = input.latitude,
            longitude = input.longitude,
            addressType = input.addressType
        )
        return addressRepository.save(address)
    }

    @MutationMapping
    fun updateAddress(@Argument id: Int, @Argument input: AddressInput): Address {
        val existing = addressRepository.findById(id)
            .orElseThrow { RuntimeException("Address not found") }
        val updated = existing.copy(
            street = input.street,
            city = input.city,
            state = input.state,
            postalCode = input.postalCode,
            country = input.country,
            latitude = input.latitude,
            longitude = input.longitude,
            addressType = input.addressType,
            updatedAt = LocalDateTime.now()
        )
        return addressRepository.save(updated)
    }

    @MutationMapping
    fun deleteAddress(@Argument id: Int): Boolean {
        addressRepository.deleteById(id)
        return true
    }

    // Contact Mutations
    @MutationMapping
    fun createContact(@Argument input: ContactInput): Contact {
        val contact = Contact(
            contactName = input.contactName,
            contactType = input.contactType,
            contactValue = input.contactValue,
            isPrimary = input.isPrimary
        )
        return contactRepository.save(contact)
    }

    @MutationMapping
    fun updateContact(@Argument id: Int, @Argument input: ContactInput): Contact {
        val existing = contactRepository.findById(id)
            .orElseThrow { RuntimeException("Contact not found") }
        val updated = existing.copy(
            contactName = input.contactName,
            contactType = input.contactType,
            contactValue = input.contactValue,
            isPrimary = input.isPrimary,
            updatedAt = LocalDateTime.now()
        )
        return contactRepository.save(updated)
    }

    @MutationMapping
    fun deleteContact(@Argument id: Int): Boolean {
        contactRepository.deleteById(id)
        return true
    }

    // Company Mutations
    @MutationMapping
    fun createCompany(@Argument input: CompanyInput): Company {
        val company = Company(
            companyName = input.companyName,
            registrationNumber = input.registrationNumber,
            taxID = input.taxID,
            industry = input.industry,
            addressID = input.addressID
        )
        return companyRepository.save(company)
    }

    @MutationMapping
    fun updateCompany(@Argument id: Int, @Argument input: CompanyInput): Company {
        val existing = companyRepository.findById(id)
            .orElseThrow { RuntimeException("Company not found") }
        val updated = existing.copy(
            companyName = input.companyName,
            registrationNumber = input.registrationNumber,
            taxID = input.taxID,
            industry = input.industry,
            addressID = input.addressID,
            updatedAt = LocalDateTime.now()
        )
        return companyRepository.save(updated)
    }

    @MutationMapping
    fun deleteCompany(@Argument id: Int): Boolean {
        companyRepository.deleteById(id)
        return true
    }

    @MutationMapping
    fun createExpert(@Argument input: ExpertInput): Expert {
        val expert = Expert(
            firstName = input.firstName,
            lastName = input.lastName,
            personalID = input.personalID,
            birthDate = input.birthDate,  // input.birthDate: LocalDate?
            addressID = input.addressID,
            contactID = input.contactID,
            email = input.email,
            specialization = input.specialization,
            level = input.level,
            marketHourlyRate = input.marketHourlyRate,
            marketDailyRate = input.marketDailyRate,
            educationLevel = input.educationLevel,
        )
        return expertRepository.save(expert)
    }

    @MutationMapping
    fun updateExpert(@Argument id: Int, @Argument input: ExpertInput): Expert {
        val existing = expertRepository.findById(id)
            .orElseThrow { RuntimeException("Expert not found") }
        val updated = existing.copy(
            firstName = input.firstName,
            lastName = input.lastName,
            personalID = input.personalID,
            birthDate = input.birthDate,  // input.birthDate: LocalDate?
            addressID = input.addressID,
            contactID = input.contactID,
            email = input.email,
            specialization = input.specialization,
            level = input.level,
            marketHourlyRate = input.marketHourlyRate,
            marketDailyRate = input.marketDailyRate,
            educationLevel = input.educationLevel,
        )
        return expertRepository.save(updated)
    }

    @MutationMapping
    fun deleteExpert(@Argument id: Int): Boolean {
        expertRepository.deleteById(id)
        return true
    }

    //Payment Mutations
    @MutationMapping
    fun createPayment(@Argument input: PaymentInput): Payment {
        val payment = Payment(
            expertID = input.expertID,
            paymentDate = LocalDate.parse(input.paymentDate),
            periodStart = LocalDate.parse(input.periodStart),
            periodEnd = LocalDate.parse(input.periodEnd),
            grossAmount = input.grossAmount.toBigDecimal(),
            netAmount = input.netAmount?.toBigDecimal(),
            taxAmount = input.taxAmount?.toBigDecimal(),
            bonus = input.bonus?.toBigDecimal() ?: BigDecimal.ZERO,
            reimbursement = input.reimbursement?.toBigDecimal() ?: BigDecimal.ZERO,
            currency = input.currency,
            paymentStatus = input.paymentStatus,
            paymentMethod = input.paymentMethod,
            notes = input.notes
        )
        return paymentRepository.save(payment)
    }

    @MutationMapping
    fun updatePayment(@Argument id: Int, @Argument input: PaymentInput): Payment {
        val existing = paymentRepository.findById(id)
            .orElseThrow { RuntimeException("Payment not found") }
        val updatedPayment = existing.copy(
            expertID = input.expertID,
            paymentDate = LocalDate.parse(input.paymentDate),
            periodStart = LocalDate.parse(input.periodStart),
            periodEnd = LocalDate.parse(input.periodEnd),
            grossAmount = input.grossAmount.toBigDecimal(),
            netAmount = input.netAmount?.toBigDecimal(),
            taxAmount = input.taxAmount?.toBigDecimal(),
            bonus = input.bonus?.toBigDecimal() ?: BigDecimal.ZERO,
            reimbursement = input.reimbursement?.toBigDecimal() ?: BigDecimal.ZERO,
            currency = input.currency,
            paymentStatus = input.paymentStatus,
            paymentMethod = input.paymentMethod,
            notes = input.notes
        )

        return paymentRepository.save(updatedPayment)
    }

    @MutationMapping
    fun deletePayment(@Argument("id") paymentID: Int): Boolean {
        paymentRepository.deleteById(paymentID)
        return true
    }

    // CompaniesContacts Mutations (join)
    @MutationMapping
    fun createCompaniesContacts(@Argument input: CompaniesContactsInput): CompaniesContacts {
        val cc = CompaniesContacts(
            companyId = input.companyId,
            contactId = input.contactId
        )
        return companiesContactsRepository.save(cc)
    }

    @MutationMapping
    fun deleteCompaniesContacts(@Argument companyId: Int, @Argument contactId: Int): Boolean {
        companiesContactsRepository.deleteById(CompaniesContactsId(companyId, contactId))
        return true
    }

    // ExpertContacts Mutations (join)
    @MutationMapping
    fun createExpertContacts(@Argument input: ExpertContactsInput): ExpertContacts {
        val ec = ExpertContacts(
            expertId = input.expertId,
            contactId = input.contactId
        )
        return expertContactsRepository.save(ec)
    }

    @MutationMapping
    fun deleteExpertContacts(@Argument expertId: Int, @Argument contactId: Int): Boolean {
        expertContactsRepository.deleteById(ExpertContactsId(expertId, contactId))
        return true
    }

    // Expertise Mutations
    @MutationMapping
    fun createExpertise(@Argument input: ExpertiseInput): Expertise {
        val expertise = Expertise(
            name = input.name,
            type = input.type,
            description = input.description,
            createdAt = LocalDateTime.now()
        )
        return expertiseRepository.save(expertise)
    }

    @MutationMapping
    fun updateExpertise(@Argument id: Int, @Argument input: ExpertiseInput): Expertise {
        val existing = expertiseRepository.findById(id)
            .orElseThrow { RuntimeException("Expertise not found") }
        val updated = existing.copy(
            name = input.name,
            type = input.type,
            description = input.description
        )
        return expertiseRepository.save(updated)
    }

    @MutationMapping
    fun deleteExpertise(@Argument id: Int): Boolean {
        expertiseRepository.deleteById(id)
        return true
    }

    // ExpertVehicles Mutations
    @MutationMapping
    fun createExpertVehicles(@Argument input: ExpertVehiclesInput): ExpertVehicles {
        val ev = ExpertVehicles(
            expertID = input.expertID,
            brand = input.brand,
            model = input.model,
            color = input.color,
            licensePlate = input.licensePlate
        )
        return expertVehiclesRepository.save(ev)
    }

    @MutationMapping
    fun updateExpertVehicles(@Argument id: Int, @Argument input: ExpertVehiclesInput): ExpertVehicles {
        val existing = expertVehiclesRepository.findById(id)
            .orElseThrow { RuntimeException("ExpertVehicles not found") }
        val updated = ExpertVehicles(
            vehicleID = existing.vehicleID,
            expertID = input.expertID,
            brand = input.brand,
            model = input.model,
            color = input.color,
            licensePlate = input.licensePlate
        )
        return expertVehiclesRepository.save(updated)
    }

    @MutationMapping
    fun deleteExpertVehicles(@Argument id: Int): Boolean {
        expertVehiclesRepository.deleteById(id)
        return true
    }

    // CompanyDevices Mutations
    @MutationMapping
    fun createCompanyDevices(@Argument input: CompanyDevicesInput): CompanyDevices {
        val cd = CompanyDevices(
            deviceType = input.deviceType,
            name = input.name,
            serialNumber = input.serialNumber,
            purchasePrice = input.purchasePrice,
            assignedExpertID = input.assignedExpertID
        )
        return companyDevicesRepository.save(cd)
    }

    @MutationMapping
    fun updateCompanyDevices(@Argument id: Int, @Argument input: CompanyDevicesInput): CompanyDevices {
        val existing = companyDevicesRepository.findById(id)
            .orElseThrow { RuntimeException("CompanyDevices not found") }
        val updated = CompanyDevices(
            deviceID = existing.deviceID,
            deviceType = input.deviceType,
            name = input.name,
            serialNumber = input.serialNumber,
            purchasePrice = input.purchasePrice,
            assignedExpertID = input.assignedExpertID
        )
        return companyDevicesRepository.save(updated)
    }

    @MutationMapping
    fun deleteCompanyDevices(@Argument id: Int): Boolean {
        companyDevicesRepository.deleteById(id)
        return true
    }

    // ExpertExpertise Mutations
    @MutationMapping
    fun createExpertExpertise(@Argument input: ExpertExpertiseInput): ExpertExpertise {
        val ee = ExpertExpertise(
            expertID = input.expertID,
            expertiseID = input.expertiseID,
            level = input.level,
            acquiredDate = input.acquiredDate,
            certificate = input.certificate
        )
        return expertExpertiseRepository.save(ee)
    }

    @MutationMapping
    fun updateExpertExpertise(@Argument id: Int, @Argument input: ExpertExpertiseInput): ExpertExpertise {
        val existing = expertExpertiseRepository.findById(id)
            .orElseThrow { RuntimeException("ExpertExpertise not found") }
        val updated = ExpertExpertise(
            expertExpertiseID = existing.expertExpertiseID,
            expertID = input.expertID,
            expertiseID = input.expertiseID,
            level = input.level,
            acquiredDate = input.acquiredDate,
            certificate = input.certificate
        )
        return expertExpertiseRepository.save(updated)
    }

    @MutationMapping
    fun deleteExpertExpertise(@Argument id: Int): Boolean {
        expertExpertiseRepository.deleteById(id)
        return true
    }

    // ExpertBenefits Mutations
    @MutationMapping
    fun createExpertBenefits(@Argument input: ExpertBenefitsInput): ExpertBenefits {
        val eb = ExpertBenefits(
            expertID = input.expertID,
            benefitName = input.benefitName,
            benefitType = input.benefitType,
            period = input.period,
            cost = input.cost
        )
        return expertBenefitsRepository.save(eb)
    }

    @MutationMapping
    fun updateExpertBenefits(@Argument id: Int, @Argument input: ExpertBenefitsInput): ExpertBenefits {
        val existing = expertBenefitsRepository.findById(id)
            .orElseThrow { RuntimeException("ExpertBenefits not found") }
        val updated = ExpertBenefits(
            benefitID = existing.benefitID,
            expertID = input.expertID,
            benefitName = input.benefitName,
            benefitType = input.benefitType,
            period = input.period,
            cost = input.cost
        )
        return expertBenefitsRepository.save(updated)
    }

    @MutationMapping
    fun deleteExpertBenefits(@Argument id: Int): Boolean {
        expertBenefitsRepository.deleteById(id)
        return true
    }

    // Teams Mutations
    @MutationMapping
    fun createTeams(@Argument input: TeamsInput): Teams {
        val team = Teams(
            teamName = input.teamName,
            teamOfficeId = input.teamOfficeId,
            teamLeaderID = input.teamLeaderID,
            createdAt = LocalDateTime.now()
        )
        return teamsRepository.save(team)
    }

    @MutationMapping
    fun updateTeams(@Argument id: Int, @Argument input: TeamsInput): Teams {
        val existing = teamsRepository.findById(id)
            .orElseThrow { RuntimeException("Teams not found") }
        val updated = Teams(
            teamID = existing.teamID,
            teamName = input.teamName,
            teamOfficeId = input.teamOfficeId,
            teamLeaderID = input.teamLeaderID,
            createdAt = existing.createdAt
        )
        return teamsRepository.save(updated)
    }

    @MutationMapping
    fun deleteTeams(@Argument id: Int): Boolean {
        teamsRepository.deleteById(id)
        return true
    }

    // TeamExperts Mutations (join)
    @MutationMapping
    fun createTeamExperts(@Argument input: TeamExpertsInput): TeamExperts {
        val te = TeamExperts(
            teamID = input.teamID,
            expertID = input.expertID,
            assignedAt = input.assignedAt ?: LocalDateTime.now()
        )
        return teamExpertsRepository.save(te)
    }

    @MutationMapping
    fun deleteTeamExperts(@Argument teamID: Int, @Argument expertID: Int): Boolean {
        teamExpertsRepository.deleteById(TeamExpertsId(teamID, expertID))
        return true
    }

    // CompanyCustomerContracts Mutations
    @MutationMapping
    fun createCompanyCustomerContracts(@Argument input: CompanyCustomerContractsInput): CompanyCustomerContracts {
        val ccc = CompanyCustomerContracts(
            companyID = input.companyID,
            contractType = input.contractType,
            startDate = input.startDate,
            endDate = input.endDate,
            totalValue = input.totalValue,
            contractURL = input.contractURL,
            createdAt = LocalDateTime.now()
        )
        return companyCustomerContractsRepository.save(ccc)
    }

    @MutationMapping
    fun updateCompanyCustomerContracts(@Argument id: Int, @Argument input: CompanyCustomerContractsInput): CompanyCustomerContracts {
        val existing = companyCustomerContractsRepository.findById(id)
            .orElseThrow { RuntimeException("CompanyCustomerContracts not found") }
        val updated = CompanyCustomerContracts(
            customerContractID = existing.customerContractID,
            companyID = input.companyID,
            contractType = input.contractType,
            startDate = input.startDate,
            endDate = input.endDate,
            totalValue = input.totalValue,
            contractURL = input.contractURL,
            createdAt = existing.createdAt
        )
        return companyCustomerContractsRepository.save(updated)
    }

    @MutationMapping
    fun deleteCompanyCustomerContracts(@Argument id: Int): Boolean {
        companyCustomerContractsRepository.deleteById(id)
        return true
    }

    // CompanyInternalContracts Mutations
    @MutationMapping
    fun createCompanyInternalContracts(@Argument input: CompanyInternalContractsInput): CompanyInternalContracts {
        val cic = CompanyInternalContracts(
            companyID = input.companyID,
            contractName = input.contractName,
            contractType = input.contractType,
            startDate = input.startDate,
            endDate = input.endDate,
            monthlyCost = input.monthlyCost,
            contractURL = input.contractURL,
            createdAt = LocalDateTime.now()
        )
        return companyInternalContractsRepository.save(cic)
    }

    @MutationMapping
    fun updateCompanyInternalContracts(@Argument id: Int, @Argument input: CompanyInternalContractsInput): CompanyInternalContracts {
        val existing = companyInternalContractsRepository.findById(id)
            .orElseThrow { RuntimeException("CompanyInternalContracts not found") }
        val updated = CompanyInternalContracts(
            internalContractID = existing.internalContractID,
            companyID = input.companyID,
            contractName = input.contractName,
            contractType = input.contractType,
            startDate = input.startDate,
            endDate = input.endDate,
            monthlyCost = input.monthlyCost,
            contractURL = input.contractURL,
            createdAt = existing.createdAt
        )
        return companyInternalContractsRepository.save(updated)
    }

    @MutationMapping
    fun deleteCompanyInternalContracts(@Argument id: Int): Boolean {
        companyInternalContractsRepository.deleteById(id)
        return true
    }

    // ExpertOrders Mutations – předpokládáme, že orderDate je nyní typu LocalDate
    @MutationMapping
    fun createExpertOrders(@Argument input: ExpertOrdersInput): ExpertOrders {
        val eo = ExpertOrders(
            customerContractID = input.customerContractID,
            expertID = input.expertID,
            orderDate = input.orderDate, // orderDate: LocalDate
            totalPrice = input.totalPrice,
            createdAt = LocalDateTime.now()
        )
        return expertOrdersRepository.save(eo)
    }

    @MutationMapping
    fun updateExpertOrders(@Argument id: Int, @Argument input: ExpertOrdersInput): ExpertOrders {
        val existing = expertOrdersRepository.findById(id)
            .orElseThrow { RuntimeException("ExpertOrders not found") }
        val updated = ExpertOrders(
            expertOrderID = existing.expertOrderID,
            customerContractID = input.customerContractID,
            expertID = input.expertID,
            orderDate = input.orderDate, // orderDate: LocalDate
            totalPrice = input.totalPrice,
            createdAt = existing.createdAt
        )
        return expertOrdersRepository.save(updated)
    }

    @MutationMapping
    fun deleteExpertOrders(@Argument id: Int): Boolean {
        expertOrdersRepository.deleteById(id)
        return true
    }

    // Pricing Mutations
    @MutationMapping
    fun createPricing(@Argument input: PricingInput): Pricing {
        val p = Pricing(
            companyID = input.companyID,
            expertID = input.expertID,
            teamID = input.teamID,
            offeredPrice = input.offeredPrice,
            margin = input.margin,
            validFrom = input.validFrom,
            validTo = input.validTo,
            pricingVersion = input.pricingVersion ?: 1,
            createdAt = LocalDateTime.now()
        )
        return pricingRepository.save(p)
    }

    @MutationMapping
    fun updatePricing(@Argument id: Int, @Argument input: PricingInput): Pricing {
        val existing = pricingRepository.findById(id)
            .orElseThrow { RuntimeException(" Pricing not found") }
        val updated = Pricing(
            pricingID = existing.pricingID,
            companyID = input.companyID,
            expertID = input.expertID,
            teamID = input.teamID,
            offeredPrice = input.offeredPrice,
            margin = input.margin,
            validFrom = input.validFrom,
            validTo = input.validTo,
            pricingVersion = input.pricingVersion ?: existing.pricingVersion,
            createdAt = existing.createdAt
        )
        return pricingRepository.save(updated)
    }

    @MutationMapping
    fun deletePricing(@Argument id: Int): Boolean {
        pricingRepository.deleteById(id)
        return true
    }

    // Assignment Mutations
    @MutationMapping
    fun createAssignment(@Argument input: AssignmentInput): Assignment {
        val pricing = pricingRepository.findById(input.pricingID)
            .orElseThrow { RuntimeException("Pricing not found") }
        val a = Assignment(
            expertID = input.expertID,
            companyID = input.companyID,
            startDate = input.startDate,
            endDate = input.endDate,
            description = input.description,
            pricing = pricing
        )
        return assignmentRepository.save(a)
    }

    @MutationMapping
    fun updateAssignment(@Argument id: Int, @Argument input: AssignmentInput): Assignment {
        val existing = assignmentRepository.findById(id)
            .orElseThrow { RuntimeException("Assignment not found") }
        val pricing = pricingRepository.findById(input.pricingID)
            .orElseThrow { RuntimeException("Pricing not found") }
        val updated = Assignment(
            assignmentID = existing.assignmentID,
            expertID = input.expertID,
            companyID = input.companyID,
            startDate = input.startDate,
            endDate = input.endDate,
            description = input.description,
            pricing = pricing
        )
        return assignmentRepository.save(updated)
    }

    @MutationMapping
    fun deleteAssignment(@Argument id: Int): Boolean {
        assignmentRepository.deleteById(id)
        return true
    }

    // AssignmentExpertise Mutations
    @MutationMapping
    fun createAssignmentExpertise(@Argument input: AssignmentExpertiseInput): AssignmentExpertise {
        val ae = AssignmentExpertise(
            assignmentID = input.assignmentID,
            expertiseID = input.expertiseID,
            type = input.type
        )
        return assignmentExpertiseRepository.save(ae)
    }

    @MutationMapping
    fun updateAssignmentExpertise(@Argument id: Int, @Argument input: AssignmentExpertiseInput): AssignmentExpertise {
        val existing = assignmentExpertiseRepository.findById(id)
            .orElseThrow { RuntimeException("AssignmentExpertise not found") }
        val updated = AssignmentExpertise(
            assignmentExpertiseID = existing.assignmentExpertiseID,
            assignmentID = input.assignmentID,
            expertiseID = input.expertiseID,
            type = input.type
        )
        return assignmentExpertiseRepository.save(updated)
    }

    @MutationMapping
    fun deleteAssignmentExpertise(@Argument id: Int): Boolean {
        assignmentExpertiseRepository.deleteById(id)
        return true
    }

    // AuditLogs Mutations
    @MutationMapping
    fun createAuditLogs(@Argument input: AuditLogsInput): AuditLogs {
        val al = AuditLogs(
            tableName = input.tableName,
            operationType = input.operationType,
            recordID = input.recordID,
            changedAt = input.changedAt ?: LocalDateTime.now(),
            changedBy = input.changedBy
        )
        return auditLogsRepository.save(al)
    }

    @MutationMapping
    fun updateAuditLogs(@Argument id: Int, @Argument input: AuditLogsInput): AuditLogs {
        val existing = auditLogsRepository.findById(id)
            .orElseThrow { RuntimeException("AuditLogs not found") }
        val updated = AuditLogs(
            auditLogID = existing.auditLogID,
            tableName = input.tableName,
            operationType = input.operationType,
            recordID = input.recordID,
            changedAt = input.changedAt ?: existing.changedAt,
            changedBy = input.changedBy
        )
        return auditLogsRepository.save(updated)
    }

    @MutationMapping
    fun deleteAuditLogs(@Argument id: Int): Boolean {
        auditLogsRepository.deleteById(id)
        return true
    }
}
