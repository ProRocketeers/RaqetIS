-- Tabulka pro ukládání adres
CREATE TABLE Addresses
(
    AddressID   SERIAL PRIMARY KEY,
    Street      VARCHAR(255),
    City        VARCHAR(255),
    State       VARCHAR(255),
    PostalCode  VARCHAR(50),
    Country     VARCHAR(100),
    Latitude    DECIMAL(9, 6),
    Longitude   DECIMAL(9, 6),
    AddressType VARCHAR(50) CHECK (AddressType IN ('Home', 'Work', 'Billing', 'Shipping')),
    CreatedAt   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt   TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabulka pro ukládání kontaktů
CREATE TABLE Contacts
(
    ContactID    SERIAL PRIMARY KEY,
    ContactName  VARCHAR(255) NOT NULL,
    ContactType  VARCHAR(50) CHECK (ContactType IN ('Phone', 'Email', 'Other', 'Website')),
    ContactValue VARCHAR(255) NOT NULL,
    IsPrimary    BOOLEAN   DEFAULT FALSE,
    CreatedAt    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt    TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabulka pro ukládání informací o firmách
CREATE TABLE Companies
(
    CompanyID          SERIAL PRIMARY KEY,
    CompanyName        VARCHAR(255) NOT NULL,
    RegistrationNumber VARCHAR(50) UNIQUE,
    TaxID              VARCHAR(50) UNIQUE,
    Industry           VARCHAR(255),
    AddressID          INT,
    CreatedAt          TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt          TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (AddressID) REFERENCES Addresses (AddressID)
);

-- Relace mezi experty a kontakty (0-N)
CREATE TABLE CompaniesContacts
(
    CompanyID INT NOT NULL,
    ContactID INT NOT NULL,
    PRIMARY KEY (CompanyID, ContactID),
    FOREIGN KEY (CompanyID) REFERENCES Companies (CompanyID) ON DELETE CASCADE,
    FOREIGN KEY (ContactID) REFERENCES Contacts (ContactID) ON DELETE CASCADE
);

-- Tabulka pro expertýzy
CREATE TABLE Expertise
(
    ExpertiseID SERIAL PRIMARY KEY,
    Name        VARCHAR(255) NOT NULL,
    Type        VARCHAR(10)  NOT NULL CHECK (Type IN ('Hard', 'Soft')),
    Description TEXT,
    CreatedAt   TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabulka pro ukládání informací o odbornících
CREATE TABLE Experts
(
    ExpertID         SERIAL PRIMARY KEY,
    FirstName        VARCHAR(255) NOT NULL,
    LastName         VARCHAR(255) NOT NULL,
    PersonalID       VARCHAR(50),
    BirthDate        DATE,
    AddressID        INT,
    ContactID        INT,
    Email            VARCHAR(255),
    Specialization   VARCHAR(255),
    Level            VARCHAR(10)  NOT NULL CHECK (Level IN ('Junior', 'Medior', 'Senior', 'Expert')),
    MarketHourlyRate DECIMAL(10, 2),
    MarketDailyRate  DECIMAL(10, 2),
    EducationLevel   VARCHAR(255),
    CreatedAt        TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (AddressID) REFERENCES Addresses (AddressID),
    FOREIGN KEY (ContactID) REFERENCES Contacts (ContactID)
);

-- Relace mezi experty a kontakty (0-N)
CREATE TABLE ExpertContacts
(
    ExpertID  INT NOT NULL,
    ContactID INT NOT NULL,
    PRIMARY KEY (ExpertID, ContactID),
    FOREIGN KEY (ExpertID) REFERENCES Experts (ExpertID) ON DELETE CASCADE,
    FOREIGN KEY (ContactID) REFERENCES Contacts (ContactID) ON DELETE CASCADE
);

-- Tabulka pro uchovávání informací o platbách/mzdách expertů
CREATE TABLE Payments
(
    PaymentID     SERIAL PRIMARY KEY,
    ExpertID      INT            NOT NULL,
    PaymentDate   DATE           NOT NULL,
    PeriodStart   DATE           NOT NULL,
    PeriodEnd     DATE           NOT NULL,
    GrossAmount   DECIMAL(15, 2) NOT NULL,
    NetAmount     DECIMAL(15, 2),
    TaxAmount     DECIMAL(15, 2),
    Bonus         DECIMAL(15, 2)                                                                   DEFAULT 0.00,
    Reimbursement DECIMAL(15, 2)                                                                   DEFAULT 0.00,
    Currency      VARCHAR(10)                                                                      DEFAULT 'CZK',
    PaymentStatus VARCHAR(50) CHECK (PaymentStatus IN ('Pending', 'Paid', 'Failed', 'Canceled'))   DEFAULT 'Pending',
    PaymentMethod VARCHAR(50) CHECK (PaymentMethod IN ('BankTransfer', 'Cash', 'Crypto', 'Other')) DEFAULT 'BankTransfer',
    Notes         TEXT,
    CreatedAt     TIMESTAMP                                                                        DEFAULT CURRENT_TIMESTAMP,
    UpdatedAt     TIMESTAMP                                                                        DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (ExpertID) REFERENCES Experts (ExpertID) ON DELETE CASCADE
);

-- Tabulka pro ukládání informací o vozidlech expertů
CREATE TABLE ExpertVehicles
(
    VehicleID    SERIAL PRIMARY KEY,
    ExpertID     INT,
    Brand        VARCHAR(255),
    Model        VARCHAR(255),
    Color        VARCHAR(50),
    LicensePlate VARCHAR(50),
    FOREIGN KEY (ExpertID) REFERENCES Experts (ExpertID)
);

-- Tabulka pro ukládání zařízení firmy
CREATE TABLE CompanyDevices
(
    DeviceID         SERIAL PRIMARY KEY,
    DeviceType       VARCHAR(255) NOT NULL,
    Name             VARCHAR(255),
    SerialNumber     VARCHAR(255),
    PurchasePrice    DECIMAL(10, 2),
    AssignedExpertID INT,
    FOREIGN KEY (AssignedExpertID) REFERENCES Experts (ExpertID) ON DELETE SET NULL
);

-- Relace mezi experty a jejich expertýzami
CREATE TABLE ExpertExpertise
(
    ExpertExpertiseID SERIAL PRIMARY KEY,
    ExpertID          INT         NOT NULL,
    ExpertiseID       INT         NOT NULL,
    Level             VARCHAR(10) NOT NULL CHECK (Level IN ('Junior', 'Medior', 'Senior', 'Expert')),
    AcquiredDate      DATE,
    Certificate       VARCHAR(255),
    FOREIGN KEY (ExpertID) REFERENCES Experts (ExpertID) ON DELETE CASCADE,
    FOREIGN KEY (ExpertiseID) REFERENCES Expertise (ExpertiseID) ON DELETE CASCADE
);

-- Tabulka pro ukládání informací o benefitech expertů
CREATE TABLE ExpertBenefits
(
    BenefitID   SERIAL PRIMARY KEY,
    ExpertID    INT          NOT NULL,
    BenefitName VARCHAR(255) NOT NULL,
    BenefitType VARCHAR(50) CHECK (BenefitType IN ('Material', 'Non-Material')),
    Period VARCHAR (50) CHECK (Period IN ('Daily', 'Monthly', 'Yearly')),
    Cost        DECIMAL(10, 2),
    FOREIGN KEY (ExpertID) REFERENCES Experts (ExpertID)
);

CREATE TABLE Teams
(
    TeamID       SERIAL PRIMARY KEY,
    TeamName     VARCHAR(255) NOT NULL,
    TeamOfficeId VARCHAR(255),
    TeamLeaderID INT,
    CreatedAt    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (TeamLeaderID) REFERENCES Experts (ExpertID) ON DELETE SET NULL
);

-- Relace mezi týmy a odborníky (M:N)
CREATE TABLE TeamExperts
(
    TeamID     INT NOT NULL,
    ExpertID   INT NOT NULL,
    AssignedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (TeamID, ExpertID),
    FOREIGN KEY (TeamID) REFERENCES Teams (TeamID),
    FOREIGN KEY (ExpertID) REFERENCES Experts (ExpertID)
);

-- Tabulka pro ukládání informací o smlouvách se zákazníky
CREATE TABLE CompanyCustomerContracts
(
    CustomerContractID SERIAL PRIMARY KEY,
    CompanyID          INT  NOT NULL,
    ContractType       VARCHAR(20) CHECK (ContractType IN ('Framework', 'Order')),
    StartDate          DATE NOT NULL,
    EndDate            DATE,
    TotalValue         DECIMAL(15, 2),
    ContractURL        VARCHAR(2048),
    CreatedAt          TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (CompanyID) REFERENCES Companies (CompanyID)
);

-- Tabulka pro ukládání informací o interních smlouvách (opakující se výdaje)
CREATE TABLE CompanyInternalContracts
(
    InternalContractID SERIAL PRIMARY KEY,
    CompanyID          INT          NOT NULL,
    ContractName       VARCHAR(255) NOT NULL,
    ContractType       VARCHAR(50) CHECK (ContractType IN ('Subscription', 'Service', 'Other')),
    StartDate          DATE         NOT NULL,
    EndDate            DATE,
    MonthlyCost        DECIMAL(10, 2),
    ContractURL        VARCHAR(2048),
    CreatedAt          TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (CompanyID) REFERENCES Companies (CompanyID)
);

-- Tabulka pro ukládání informací o objednávkách expertů v rámci zákaznických smluv
CREATE TABLE ExpertOrders
(
    ExpertOrderID      SERIAL PRIMARY KEY,
    CustomerContractID INT  NOT NULL,
    ExpertID           INT  NOT NULL,
    OrderDate          DATE NOT NULL,
    TotalPrice         DECIMAL(15, 2),
    CreatedAt          TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (CustomerContractID) REFERENCES CompanyCustomerContracts (CustomerContractID),
    FOREIGN KEY (ExpertID) REFERENCES Experts (ExpertID)
);

-- Tabulka pro cenotvorbu (např. historie nabídek)
CREATE TABLE Pricing
(
    PricingID      SERIAL PRIMARY KEY,
    CompanyID      INT,
    ExpertID       INT,
    TeamID         INT,
    OfferedPrice   DECIMAL(15, 2),
    Margin         DECIMAL(5, 2) DEFAULT 0.00,
    ValidFrom      DATE NOT NULL,
    ValidTo        DATE,
    PricingVersion INT           DEFAULT 1,
    CreatedAt      TIMESTAMP     DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (CompanyID) REFERENCES Companies (CompanyID),
    FOREIGN KEY (ExpertID) REFERENCES Experts (ExpertID),
    FOREIGN KEY (TeamID) REFERENCES Teams (TeamID)
);

-- Tabulka pro historii kariéry (assignments)
CREATE TABLE Assignments
(
    AssignmentID SERIAL PRIMARY KEY,
    ExpertID     INT  NOT NULL,
    CompanyID    INT  NOT NULL,
    PricingID    INT  NOT NULL UNIQUE,
    StartDate    DATE NOT NULL,
    EndDate      DATE,
    Description  TEXT,
    FOREIGN KEY (ExpertID) REFERENCES Experts (ExpertID),
    FOREIGN KEY (CompanyID) REFERENCES Companies (CompanyID),
    FOREIGN KEY (PricingID) REFERENCES Pricing (PricingID)
);

-- Relace mezi přiřazeními a používanými expertízami
CREATE TABLE AssignmentExpertise
(
    AssignmentExpertiseID SERIAL PRIMARY KEY,
    AssignmentID          INT NOT NULL,
    ExpertiseID           INT NOT NULL,
    Type                  VARCHAR(10) CHECK (Type IN ('Used', 'Acquired')),
    FOREIGN KEY (AssignmentID) REFERENCES Assignments (AssignmentID) ON DELETE CASCADE,
    FOREIGN KEY (ExpertiseID) REFERENCES Expertise (ExpertiseID) ON DELETE CASCADE
);

-- Tabulka pro audit logy
CREATE TABLE AuditLogs
(
    AuditLogID    SERIAL PRIMARY KEY,
    TableName     VARCHAR(255) NOT NULL,
    OperationType VARCHAR(50) CHECK (OperationType IN ('INSERT', 'UPDATE', 'DELETE')),
    RecordID      INT          NOT NULL,
    ChangedAt     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ChangedBy     VARCHAR(255)
);
