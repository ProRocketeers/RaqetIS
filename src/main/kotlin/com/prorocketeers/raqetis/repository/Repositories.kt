package com.prorocketeers.raqetis.repository

import com.prorocketeers.raqetis.model.Address
import com.prorocketeers.raqetis.model.Assignment
import com.prorocketeers.raqetis.model.AssignmentExpertise
import com.prorocketeers.raqetis.model.AuditLogs
import com.prorocketeers.raqetis.model.CompaniesContacts
import com.prorocketeers.raqetis.model.CompaniesContactsId
import com.prorocketeers.raqetis.model.Company
import com.prorocketeers.raqetis.model.CompanyCustomerContracts
import com.prorocketeers.raqetis.model.CompanyDevices
import com.prorocketeers.raqetis.model.CompanySuppliersContracts
import com.prorocketeers.raqetis.model.Contact
import com.prorocketeers.raqetis.model.Expert
import com.prorocketeers.raqetis.model.ExpertBenefits
import com.prorocketeers.raqetis.model.ExpertContacts
import com.prorocketeers.raqetis.model.ExpertContactsId
import com.prorocketeers.raqetis.model.ExpertExpertise
import com.prorocketeers.raqetis.model.ExpertOrders
import com.prorocketeers.raqetis.model.ExpertVehicles
import com.prorocketeers.raqetis.model.Expertise
import com.prorocketeers.raqetis.model.Pricing
import com.prorocketeers.raqetis.model.Payment
import com.prorocketeers.raqetis.model.TeamExperts
import com.prorocketeers.raqetis.model.TeamExpertsId
import com.prorocketeers.raqetis.model.Teams
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

// Repozitář pro entitu Address
@Repository
interface AddressRepository : JpaRepository<Address, Int>

// Repozitář pro entitu Contact
@Repository
interface ContactRepository : JpaRepository<Contact, Int>

// Repozitář pro entitu Company
@Repository
interface CompanyRepository : JpaRepository<Company, Int>

// Repozitář pro join entitu CompaniesContacts
@Repository
interface CompaniesContactsRepository : JpaRepository<CompaniesContacts, CompaniesContactsId>

// Repozitář pro join entitu ExpertContacts
@Repository
interface ExpertContactsRepository : JpaRepository<ExpertContacts, ExpertContactsId>

// Repozitář pro entitu Expertise
@Repository
interface ExpertiseRepository : JpaRepository<Expertise, Int>

// Repozitář pro entitu Expert
@Repository
interface ExpertRepository : JpaRepository<Expert, Int>

// Repozitář pro entitu Payment
@Repository
interface PaymentRepository : JpaRepository<Payment, Int>

// Repozitář pro entitu ExpertVehicles
@Repository
interface ExpertVehiclesRepository : JpaRepository<ExpertVehicles, Int>

// Repozitář pro entitu CompanyDevices
@Repository
interface CompanyDevicesRepository : JpaRepository<CompanyDevices, Int>

// Repozitář pro entitu ExpertExpertise
@Repository
interface ExpertExpertiseRepository : JpaRepository<ExpertExpertise, Int>

// Repozitář pro entitu ExpertBenefits
@Repository
interface ExpertBenefitsRepository : JpaRepository<ExpertBenefits, Int>

// Repozitář pro entitu Teams
@Repository
interface TeamsRepository : JpaRepository<Teams, Int>

// Repozitář pro join entitu TeamExperts
@Repository
interface TeamExpertsRepository : JpaRepository<TeamExperts, TeamExpertsId>

// Repozitář pro entitu CompanyCustomerContracts
@Repository
interface CompanyCustomerContractsRepository : JpaRepository<CompanyCustomerContracts, Int>

// Repozitář pro entitu CompanyInternalContracts
@Repository
interface CompanySuppliersContractsRepository : JpaRepository<CompanySuppliersContracts, Int>

// Repozitář pro entitu ExpertOrders
@Repository
interface ExpertOrdersRepository : JpaRepository<ExpertOrders, Int>

// Repozitář pro entitu Pricing
@Repository
interface PricingRepository : JpaRepository<Pricing, Int>

// Repozitář pro entitu Assignment
@Repository
interface AssignmentRepository : JpaRepository<Assignment, Int>

// Repozitář pro entitu AssignmentExpertise
@Repository
interface AssignmentExpertiseRepository : JpaRepository<AssignmentExpertise, Int>

// Repozitář pro entitu AuditLogs
@Repository
interface AuditLogsRepository : JpaRepository<AuditLogs, Int>
