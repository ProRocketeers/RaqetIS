const GRAPHQL_ENDPOINT = 'https://1374-81-30-251-65.ngrok-free.app/graphql';

function loadExpertsToSheet() {
  const expertQuery = `
    query {
      experts {
        expertID
        firstName
        lastName
        personalID
        birthDate
        addressID
        contactID
        email
        specialization
        level
        educationLevel
      }
    }
  `;

  const expertiseQuery = `
    query {
      expertExpertises {
        expertID
        expertiseID
        level
      }
    }
  `;

  const expertiseNameQuery = `
    query {
      expertises {
        expertiseID
        name
      }
    }
  `;

  const pricingQuery = `
    query {
      pricing {
        expertID
        offeredPrice
        validFrom
      }
    }
  `;

  const teamExpertQuery = `
    query {
      teamExperts {
        expertID
        teamID
      }
    }
  `;

  const teamsQuery = `
    query {
      teams {
        teamID
        teamName
      }
    }
  `;

  const experts = fetchGraphQL(expertQuery).experts;
  const expertExpertises = fetchGraphQL(expertiseQuery).expertExpertises;
  const expertises = fetchGraphQL(expertiseNameQuery).expertises;
  const pricing = fetchGraphQL(pricingQuery).pricing;
  const teamExperts = fetchGraphQL(teamExpertQuery).teamExperts;
  const teams = fetchGraphQL(teamsQuery).teams;

  const expertiseMap = {};
  expertises.forEach(e => expertiseMap[e.expertiseID] = e.name);

  const groupedExpertise = {};
  expertExpertises.forEach(e => {
    if (!groupedExpertise[e.expertID]) groupedExpertise[e.expertID] = [];
    groupedExpertise[e.expertID].push(`${expertiseMap[e.expertiseID] || "?"} (${e.level})`);
  });

  const latestPricing = {};
  pricing.forEach(p => {
    latestPricing[p.expertID] = p.offeredPrice;
  });

  const teamMap = {};
  teams.forEach(t => teamMap[t.teamID] = t.teamName);

  const groupedTeams = {};
  teamExperts.forEach(te => {
    if (!groupedTeams[te.expertID]) groupedTeams[te.expertID] = [];
    groupedTeams[te.expertID].push(teamMap[te.teamID] || `Team#${te.teamID}`);
  });

  const sheet = SpreadsheetApp.getActiveSpreadsheet().getActiveSheet();
  sheet.clear();
  sheet.appendRow([
    'First Name', 'Last Name', 'Team', 'Personal ID', 'Birth Date',
    'Email', 'Specialization', 'Level', 'Education Level', 'Expertises', 'Last Offered Price (CZK)', 'Hourly Rate', 'Daily Rate', 'Expert ID (Hidden)'
  ]);

  experts.forEach(expert => {
    const expText = (groupedExpertise[expert.expertID] || []).join(', ');
    const price = latestPricing[expert.expertID] || '—';
    const teamsText = (groupedTeams[expert.expertID] || []).join(', ');

    sheet.appendRow([
      expert.firstName,
      expert.lastName,
      teamsText,
      expert.personalID,
      expert.birthDate,
      expert.email,
      expert.specialization,
      expert.level,
      expert.educationLevel,
      expText,
      price,
      '',
      '',
      expert.expertID
    ]);
  });
}

function syncAllRowsToBackend() {
  const sheet = SpreadsheetApp.getActiveSpreadsheet().getActiveSheet();
  const range = sheet.getRange(2, 1, sheet.getLastRow() - 1, 14);
  const rows = range.getValues();

  rows.forEach((row, index) => {
    const rowNumber = index + 2;
    const [
      firstName, lastName, teamsText, personalID, birthDateRaw,
      email, specialization, level, educationLevel,
      expertisesText, offeredPrice, hourlyRate, dailyRate, expertID
    ] = row.map(cell => cell || "");

    const birthDate = (birthDateRaw instanceof Date)
      ? Utilities.formatDate(birthDateRaw, Session.getScriptTimeZone(), "yyyy-MM-dd")
      : birthDateRaw;

    if (!firstName && !lastName && expertID) {
      const deleteMutation = `
        mutation {
          deleteExpert(id: ${expertID})
        }
      `;
      try {
        fetchGraphQL(deleteMutation);
        Logger.log(`🗑️ Expert ${expertID} byl smazán (řádek ${rowNumber})`);
      } catch (e) {
        Logger.log(`❌ Chyba při mazání experta na řádku ${rowNumber}: ${e}`);
      }
      return;
    }

    if (!firstName || !lastName) return;

    const mutation = expertID ? `
      mutation {
        updateExpert(id: ${expertID}, input: {
          firstName: "${firstName}"
          lastName: "${lastName}"
          personalID: "${personalID}"
          birthDate: "${birthDate}"
          email: "${email}"
          specialization: "${specialization}"
          level: ${level || 'Medior'}
          educationLevel: "${educationLevel}"
          marketHourlyRate: ${hourlyRate || 0}
          marketDailyRate: ${dailyRate || 0}
        }) {
          expertID
        }
      }
    ` : `
      mutation {
        createExpert(input: {
          firstName: "${firstName}"
          lastName: "${lastName}"
          personalID: "${personalID}"
          birthDate: "${birthDate}"
          email: "${email}"
          specialization: "${specialization}"
          level: ${level || 'Medior'}
          educationLevel: "${educationLevel}"
          marketHourlyRate: ${hourlyRate || 0}
          marketDailyRate: ${dailyRate || 0}
        }) {
          expertID
        }
      }
    `;

    try {
      const result = fetchGraphQL(mutation);
      const newID = result?.createExpert?.expertID || result?.updateExpert?.expertID;
      if (newID) {
        sheet.getRange(rowNumber, 14).setValue(newID);

        const teams = teamsText.split(',').map(t => t.trim()).filter(Boolean);

        teams.forEach(teamName => {
          const assignTeamMutation = `
            mutation {
              createTeamExperts(input: {
                expertID: ${newID},
                teamID: ${getTeamIdByName(teamName)}
              }) {
                teamID
              }
            }
          `;
          fetchGraphQL(assignTeamMutation);
        });
      } else {
        Logger.log(`⚠️ Řádek ${rowNumber}: expertID nebylo vráceno.`);
      }
    } catch (e) {
      Logger.log(`❌ Chyba na řádku ${rowNumber}: ${e}`);
    }
  });

  SpreadsheetApp.getUi().alert('✅ Synchronizace dokončena!');
}

function getTeamIdByName(name) {
  const teamsQuery = `
    query {
      teams {
        teamID
        teamName
      }
    }
  `;
  const teams = fetchGraphQL(teamsQuery).teams;
  const match = teams.find(t => t.teamName === name);
  return match ? match.teamID : null;
}

function fetchGraphQL(query) {
  const response = UrlFetchApp.fetch(GRAPHQL_ENDPOINT, {
    method: 'post',
    contentType: 'application/json',
    payload: JSON.stringify({ query }),
    muteHttpExceptions: true,
  });

  const text = response.getContentText();
  Logger.log("📡 GraphQL raw response: " + text);

  const json = JSON.parse(text);
  return json.data;
}

function onOpen() {
  const ui = SpreadsheetApp.getUi();
  ui.createMenu('💼 Expert Sync')
    .addItem('🔄 Načíst experty', 'loadExpertsToSheet')
    .addItem('⬆️ Synchronizovat změny', 'syncAllRowsToBackend')
    .addToUi();
}
