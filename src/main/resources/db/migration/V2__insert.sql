INSERT INTO Addresses (Street, City, State, PostalCode, Country, Latitude, Longitude, AddressType)
VALUES ('Nádražní 123', 'Ostrava', 'Moravskoslezský', '70200', 'Česká republika', 49.8345, 18.2923, 'Home'),
       ('Porubská 15', 'Ostrava-Poruba', 'Moravskoslezský', '70800', 'Česká republika', 49.8390, 18.1627, 'Work'),
       ('Opavská 25', 'Ostrava-Poruba', 'Moravskoslezský', '70800', 'Česká republika', 49.8341, 18.1665, 'Billing'),
       ('Hlavní třída 78', 'Ostrava-Poruba', 'Moravskoslezský', '70800', 'Česká republika', 49.8323, 18.1634,
        'Shipping'),
       ('Závodní 86', 'Ostrava-Vítkovice', 'Moravskoslezský', '70300', 'Česká republika', 49.8034, 18.2644, 'Home'),
       ('Hornická 9', 'Karviná', 'Moravskoslezský', '73301', 'Česká republika', 49.8541, 18.5412, 'Work'),
       ('Těšínská 112', 'Havířov', 'Moravskoslezský', '73601', 'Česká republika', 49.7799, 18.4201, 'Home'),
       ('Masarykovo náměstí 5', 'Ostrava', 'Moravskoslezský', '70200', 'Česká republika', 49.8352, 18.2928, 'Billing'),
       ('Studentská 50', 'Ostrava-Poruba', 'Moravskoslezský', '70800', 'Česká republika', 49.8349, 18.1582, 'Shipping'),
       ('Frýdecká 77', 'Ostrava-Hrabová', 'Moravskoslezský', '72000', 'Česká republika', 49.7795, 18.2876, 'Work');

INSERT INTO Contacts (ContactName, ContactType, ContactValue, IsPrimary)
VALUES ('Anna Nováková', 'Phone', '+420 602 123 456', TRUE),
       ('Tomáš Dvořák', 'Email', 'tomas.dvorak@example.com', TRUE),
       ('Jana Malá', 'Website', 'https://janamala.cz', FALSE),
       ('Petr Kučera', 'Other', 'Skype: petr.kucera', FALSE),
       ('Lucie Horáková', 'Phone', '+420 777 654 321', TRUE),
       ('Martin Svoboda', 'Email', 'martin.svoboda@firma.cz', FALSE),
       ('Veronika Veselá', 'Website', 'https://veronikavesela.cz', FALSE),
       ('Radek Král', 'Phone', '+420 601 987 654', FALSE),
       ('Klára Fialová', 'Other', 'Telegram: @klarafiala', TRUE),
       ('Ondřej Beneš', 'Email', 'ondrej.benes@consulting.cz', TRUE);

INSERT INTO Companies (CompanyName, RegistrationNumber, TaxID, Industry, AddressID)
VALUES ('Škoda Auto a.s.', '00177041', 'CZ00177041', 'Automobilový průmysl', 1),
       ('ČEZ, a.s.', '45274649', 'CZ45274649', 'Energetika', 2),
       ('Seznam.cz, a.s.', '26168685', 'CZ26168685', 'Informační technologie', 3),
       ('Alza.cz a.s.', '27082440', 'CZ27082440', 'E-commerce', 4),
       ('T-Mobile Czech Republic a.s.', '64949681', 'CZ64949681', 'Telekomunikace', 5),
       ('Avast Software s.r.o.', '02176475', 'CZ02176475', 'Kyberbezpečnost', 6),
       ('Kofola ČeskoSlovensko a.s.', '24261980', 'CZ24261980', 'Potravinářství', 7),
       ('Continental Barum s.r.o.', '18187741', 'CZ18187741', 'Výroba pneumatik', 8),
       ('Zásilkovna s.r.o.', '28408306', 'CZ28408306', 'Logistika', 9),
       ('IKEA Česká republika s.r.o.', '25112836', 'CZ25112836', 'Maloobchod - nábytek', 10);

INSERT INTO CompaniesContacts (CompanyID, ContactID)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 4),
       (5, 5),
       (6, 6),
       (7, 7),
       (8, 8),
       (9, 9),
       (10, 10);

INSERT INTO Expertise (Name, Type, Description)
VALUES ('Java Development', 'Hard', 'Vývoj backendových aplikací v jazyce Java.'),
       ('React Native', 'Hard', 'Vývoj mobilních aplikací pomocí React Native.'),
       ('Databázový design', 'Hard', 'Návrh a optimalizace relačních databází.'),
       ('Kybernetická bezpečnost', 'Hard', 'Zabezpečení systémů a ochrana dat.'),
       ('Cloud Computing', 'Hard', 'Práce s cloudovými platformami jako AWS nebo Azure.'),
       ('Týmová spolupráce', 'Soft', 'Efektivní spolupráce v týmu a komunikace.'),
       ('Prezentační dovednosti', 'Soft', 'Jasná a profesionální prezentace nápadů.'),
       ('Time management', 'Soft', 'Efektivní řízení času a prioritizace úkolů.'),
       ('Kreativní řešení problémů', 'Soft', 'Hledání inovativních řešení komplexních problémů.'),
       ('Vedení týmu', 'Soft', 'Schopnost vést a motivovat tým k dosažení cílů.');

INSERT INTO Experts (FirstName, LastName, PersonalID, BirthDate, AddressID, ContactID,
                     Email, Specialization, Level, MarketHourlyRate, MarketDailyRate, EducationLevel)
VALUES ('Eva', 'Kučerová', '856203/1245', '1990-03-15', 1, 1, 'eva.kucerova@example.com', 'IT konzultant', 'Senior',
        850.00, 6800.00, 'VŠ'),
       ('Tomáš', 'Beneš', '891215/5489', '1989-12-15', 2, 2, 'tomas.benes@example.com', 'Vývojář', 'Expert', 900.00,
        7200.00, 'VŠ'),
       ('Lucie', 'Malá', '920314/1111', '1992-03-14', 3, 3, 'lucie.mala@example.com', 'UX designér', 'Medior', 700.00,
        5600.00, 'VOŠ'),
       ('Jan', 'Horák', '870110/5566', '1987-01-10', 4, 4, 'jan.horak@example.com', 'DevOps', 'Senior', 800.00, 6400.00,
        'VŠ'),
       ('Karolína', 'Černá', '900802/8752', '1990-08-02', 5, 5, 'karolina.cerna@example.com', 'Projektový manažer',
        'Expert', 950.00, 7600.00, 'VŠ'),
       ('Petr', 'Král', '931127/6543', '1993-11-27', 6, 6, 'petr.kral@example.com', 'Tester', 'Junior', 500.00, 4000.00,
        'SŠ'),
       ('Barbora', 'Vlková', '850312/1247', '1985-03-12', 7, 7, 'barbora.vlkova@example.com', 'Analytik', 'Medior',
        680.00, 5400.00, 'VŠ'),
       ('Adam', 'Nový', '910421/9998', '1991-04-21', 8, 8, 'adam.novy@example.com', 'Architekt', 'Expert', 980.00,
        7800.00, 'VŠ'),
       ('Jana', 'Fialová', '940730/1221', '1994-07-30', 9, 9, 'jana.fialova@example.com', 'Scrum master', 'Senior',
        870.00, 6900.00, 'VŠ'),
       ('Michal', 'Dvořák', '880120/7777', '1988-01-20', 10, 10, 'michal.dvorak@example.com', 'Vývojář', 'Medior',
        750.00, 6000.00, 'VŠ');

INSERT INTO ExpertContacts (ExpertID, ContactID)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 4),
       (5, 5),
       (6, 6),
       (7, 7),
       (8, 8),
       (9, 9),
       (10, 10);

INSERT INTO Payments (ExpertID, PaymentDate, PeriodStart, PeriodEnd,
                      GrossAmount, NetAmount, TaxAmount, Bonus, Reimbursement,
                      Currency, PaymentStatus, PaymentMethod, Notes)
VALUES (1, '2025-03-31', '2025-03-01', '2025-03-31', 75000.00, 60000.00, 15000.00, 5000.00, 1000.00, 'CZK', 'Paid',
        'BankTransfer', 'Březnová mzda'),
       (2, '2025-03-31', '2025-03-01', '2025-03-31', 68000.00, 54500.00, 13500.00, 4000.00, 1200.00, 'CZK', 'Paid',
        'BankTransfer', 'Březnová mzda'),
       (3, '2025-03-31', '2025-03-01', '2025-03-31', 70000.00, 56000.00, 14000.00, 3000.00, 800.00, 'CZK', 'Paid',
        'Cash', 'Hotově vyplaceno'),
       (4, '2025-03-31', '2025-03-01', '2025-03-31', 72000.00, 57000.00, 15000.00, 6000.00, 500.00, 'CZK', 'Paid',
        'BankTransfer', 'Bonus za projekt'),
       (5, '2025-03-31', '2025-03-01', '2025-03-31', 65000.00, 52000.00, 13000.00, 2000.00, 900.00, 'CZK', 'Paid',
        'BankTransfer', 'Standardní výplata'),
       (6, '2025-03-31', '2025-03-01', '2025-03-31', 74000.00, 59000.00, 15000.00, 4000.00, 700.00, 'CZK', 'Paid',
        'Other', 'Crypto převod'),
       (7, '2025-03-31', '2025-03-01', '2025-03-31', 71000.00, 56500.00, 14500.00, 3000.00, 650.00, 'CZK', 'Paid',
        'BankTransfer', 'Výplata bez zpoždění'),
       (8, '2025-03-31', '2025-03-01', '2025-03-31', 67000.00, 53000.00, 14000.00, 2500.00, 750.00, 'CZK', 'Paid',
        'BankTransfer', 'Doplněné po konzultaci'),
       (9, '2025-03-31', '2025-03-01', '2025-03-31', 76000.00, 61000.00, 15000.00, 5000.00, 1000.00, 'CZK', 'Paid',
        'BankTransfer', 'Nejvyšší výplata'),
       (10, '2025-03-31', '2025-03-01', '2025-03-31', 69000.00, 55000.00, 14000.00, 3500.00, 850.00, 'CZK', 'Paid',
        'BankTransfer', 'Standardní výplata');

INSERT INTO ExpertVehicles (ExpertID, Brand, Model, Color, LicensePlate)
VALUES (1, 'Škoda', 'Octavia', 'Stříbrná', '1T2 3456'),
       (2, 'Volkswagen', 'Golf', 'Modrá', '2B7 8932'),
       (3, 'Peugeot', '208', 'Červená', '7U6 4567'),
       (4, 'Renault', 'Clio', 'Zelená', '5Z9 1123'),
       (5, 'Hyundai', 'i30', 'Bílá', '3A4 7744'),
       (6, 'Toyota', 'Corolla', 'Černá', '6K8 8876'),
       (7, 'Kia', 'Ceed', 'Šedá', '8P5 6633'),
       (8, 'Mazda', '3', 'Fialová', '4L9 3455'),
       (9, 'BMW', '3 Series', 'Tmavě modrá', '9D0 2233'),
       (10, 'Mercedes-Benz', 'A-Class', 'Zlatá', '1K7 9012');

INSERT INTO CompanyDevices (DeviceType, Name, SerialNumber, PurchasePrice, AssignedExpertID)
VALUES ('Notebook', 'Dell Latitude 5420', 'SN-DL5420-001', 32000.00, 1),
       ('Smartphone', 'iPhone 13', 'SN-IP13-002', 25000.00, 2),
       ('Tablet', 'iPad Air', 'SN-IPAD-003', 18000.00, 3),
       ('Monitor', 'Samsung 27"', 'SN-SM27-004', 7000.00, 4),
       ('Printer', 'HP LaserJet Pro', 'SN-HPPR-005', 9500.00, 5),
       ('Router', 'TP-Link Archer C7', 'SN-TPLC7-006', 2500.00, 6),
       ('Notebook', 'Lenovo ThinkPad X1', 'SN-LNTPX1-007', 36000.00, 7),
       ('Smartwatch', 'Garmin Venu 2', 'SN-GRMV2-008', 9000.00, 8),
       ('Keyboard', 'Logitech MX Keys', 'SN-LGMXK-009', 2800.00, 9),
       ('Mouse', 'Logitech MX Master 3', 'SN-LGMXM3-010', 2400.00, 10);

INSERT INTO ExpertExpertise (ExpertID, ExpertiseID, Level, AcquiredDate, Certificate)
VALUES (1, 1, 'Junior', '2020-01-15', 'cert_java_basic.pdf'),
       (1, 2, 'Medior', '2021-06-10', 'cert_sql_intermediate.pdf'),
       (2, 3, 'Senior', '2019-09-12', 'cert_project_mgmt.pdf'),
       (2, 4, 'Expert', '2022-03-22', 'cert_data_analysis.pdf'),
       (3, 1, 'Medior', '2020-11-01', 'cert_java_advanced.pdf'),
       (4, 2, 'Junior', '2021-02-17', NULL),
       (5, 5, 'Senior', '2018-07-09', 'cert_devops.pdf'),
       (6, 3, 'Medior', '2021-10-30', NULL),
       (7, 4, 'Junior', '2022-05-20', 'cert_react.pdf'),
       (8, 5, 'Expert', '2023-01-12', 'cert_kubernetes.pdf');

INSERT INTO ExpertBenefits (ExpertID, BenefitName, BenefitType, Period, Cost)
VALUES (1, 'Multisport karta', 'Non-Material', 'Monthly', 1200.00),
       (2, 'Firemní notebook', 'Material', 'Yearly', 30000.00),
       (3, 'Stravenky', 'Material', 'Monthly', 1500.00),
       (4, 'Home office', 'Non-Material', 'Daily', 0.00),
       (5, 'Firemní telefon', 'Material', 'Yearly', 15000.00),
       (6, 'Online kurzy', 'Non-Material', 'Monthly', 500.00),
       (7, 'Služební auto', 'Material', 'Monthly', 8000.00),
       (8, 'Flexibilní pracovní doba', 'Non-Material', 'Daily', 0.00),
       (9, 'Příspěvek na dovolenou', 'Material', 'Yearly', 10000.00),
       (10, 'Mentoring', 'Non-Material', 'Monthly', 2000.00);

INSERT INTO Teams (TeamName, TeamOfficeId, TeamLeaderID)
VALUES ('Vývojový tým A', 'Ostrava-OF01', 1),
       ('Tým QA', 'Ostrava-OF02', 2),
       ('Tým DevOps', 'Ostrava-OF03', 3);

INSERT INTO TeamExperts (TeamID, ExpertID)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (2, 4),
       (2, 5),
       (3, 6),
       (2, 7),
       (3, 8),
       (3, 9),
       (3, 10);

INSERT INTO CompanyCustomerContracts (CompanyID, ContractType, StartDate, EndDate, TotalValue, ContractURL)
VALUES (1, 'Framework', '2022-01-01', '2024-12-31', 5000000.00, 'https://skoda-auto.cz/contract1.pdf'),
       (2, 'Order', '2023-03-15', '2023-09-15', 1200000.00, 'https://cez.cz/contract2.pdf'),
       (3, 'Framework', '2021-06-01', '2025-05-31', 3500000.00, 'https://seznam.cz/contract3.pdf'),
       (4, 'Order', '2023-01-01', NULL, 800000.00, 'https://alza.cz/contract4.pdf'),
       (5, 'Framework', '2020-04-01', '2025-04-01', 7800000.00, 'https://t-mobile.cz/contract5.pdf'),
       (6, 'Order', '2022-11-10', '2023-11-10', 900000.00, 'https://avast.com/contract6.pdf'),
       (7, 'Framework', '2022-07-01', NULL, 1500000.00, 'https://kofola.cz/contract7.pdf'),
       (8, 'Order', '2024-01-01', '2024-12-31', 1000000.00, 'https://barum.cz/contract8.pdf'),
       (9, 'Framework', '2021-09-01', '2026-09-01', 6700000.00, 'https://zasilkovna.cz/contract9.pdf'),
       (10, 'Order', '2023-05-01', NULL, 1300000.00, 'https://ikea.cz/contract10.pdf');

INSERT INTO CompanyInternalContracts (CompanyID, ContractName, ContractType, StartDate, EndDate, MonthlyCost,
                                      ContractURL)
VALUES (1, 'Microsoft 365', 'Subscription', '2023-01-01', NULL, 12500.00, 'https://contracts.skoda-auto.cz/m365.pdf'),
       (2, 'Údržba elektráren', 'Service', '2022-06-01', '2025-06-01', 42000.00,
        'https://contracts.cez.cz/maintenance.pdf'),
       (3, 'VPN Seznam', 'Other', '2021-03-01', NULL, 8000.00, 'https://contracts.seznam.cz/vpn.pdf'),
       (4, 'Cloud hosting AWS', 'Subscription', '2022-11-01', NULL, 15000.00, 'https://contracts.alza.cz/aws.pdf'),
       (5, 'Datové linky', 'Service', '2023-01-15', '2026-01-15', 24000.00, 'https://contracts.t-mobile.cz/links.pdf'),
       (6, 'Antivirové licence', 'Subscription', '2023-04-01', NULL, 9500.00,
        'https://contracts.avast.com/licences.pdf'),
       (7, 'Distribuce nápojů', 'Service', '2020-10-01', '2024-10-01', 30000.00,
        'https://contracts.kofola.cz/distribution.pdf'),
       (8, 'Pneumatika testování', 'Other', '2021-07-01', '2025-07-01', 18000.00,
        'https://contracts.barum.cz/testing.pdf'),
       (9, 'Logistický software', 'Subscription', '2023-05-01', NULL, 12500.00,
        'https://contracts.zasilkovna.cz/logistics.pdf'),
       (10, 'Správa IT infrastruktury', 'Service', '2021-01-01', '2026-01-01', 20000.00,
        'https://contracts.ikea.cz/it-support.pdf');

INSERT INTO ExpertOrders (CustomerContractID, ExpertID, OrderDate, TotalPrice)
VALUES (1, 1, '2024-01-15', 50000.00),
       (2, 2, '2024-01-20', 45000.00),
       (3, 3, '2024-02-05', 60000.00),
       (4, 4, '2024-02-10', 30000.00),
       (5, 5, '2024-03-01', 52000.00),
       (6, 6, '2024-03-12', 47000.00),
       (7, 7, '2024-03-20', 55000.00),
       (8, 8, '2024-04-01', 48000.00),
       (9, 9, '2024-04-10', 51000.00),
       (10, 10, '2024-04-15', 53000.00);

INSERT INTO Pricing (CompanyID, ExpertID, TeamID, OfferedPrice, Margin, ValidFrom, ValidTo)
VALUES (1, 1, 1, 8500.00, 15.00, '2024-01-01', '2024-03-31'),
       (2, 2, 1, 9200.00, 10.00, '2024-01-10', '2024-04-10'),
       (3, 3, 1, 7800.00, 12.00, '2024-02-01', NULL),
       (4, 4, 2, 8100.00, 8.00, '2024-02-15', NULL),
       (5, 5, 2, 9600.00, 20.00, '2024-03-01', '2024-06-01'),
       (6, 6, 3, 7400.00, 5.00, '2024-03-10', NULL),
       (7, 7, 2, 8700.00, 18.00, '2024-03-20', '2024-04-20'),
       (8, 8, 3, 9000.00, 14.00, '2024-04-01', NULL),
       (9, 9, 3, 8900.00, 11.00, '2024-04-10', NULL),
       (10, 10, 3, 9300.00, 16.00, '2024-04-15', NULL);

INSERT INTO Assignments (ExpertID, CompanyID, PricingID, StartDate, EndDate, Description)
VALUES (1, 1, 1, '2024-01-01', '2024-03-31', 'Implementace softwarového řešení pro výrobu'),
       (2, 2, 2, '2024-01-10', '2024-04-10', 'Optimalizace energetických systémů'),
       (3, 3, 3, '2024-02-01', NULL, 'Vývoj webového rozhraní pro hledání'),
       (4, 4, 4, '2024-02-15', NULL, 'Automatizace objednávkového systému'),
       (5, 5, 5, '2024-03-01', '2024-06-01', 'Zavedení nového CRM systému'),
       (6, 6, 6, '2024-03-10', NULL, 'Testování bezpečnostních protokolů'),
       (7, 7, 7, '2024-03-20', '2024-04-20', 'Analýza trhu a zákaznických dat'),
       (8, 8, 8, '2024-04-01', NULL, 'Vývoj logistického plánovače'),
       (9, 9, 9, '2024-04-10', NULL, 'Implementace platební brány'),
       (10, 10, 10, '2024-04-15', NULL, 'Zavedení systému správy zásob');

INSERT INTO AssignmentExpertise (AssignmentID, ExpertiseID, Type)
VALUES (1, 1, 'Used'),
       (1, 2, 'Acquired'),
       (2, 3, 'Used'),
       (3, 4, 'Used'),
       (4, 5, 'Acquired'),
       (5, 6, 'Used'),
       (6, 7, 'Used'),
       (7, 8, 'Acquired'),
       (8, 9, 'Used'),
       (9, 10, 'Acquired');

