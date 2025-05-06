const GRAPHQL_ENDPOINT = 'https://b519-81-30-251-65.ngrok-free.app/graphql';

function resetOAuth() {
  getOAuthService().reset();
  SpreadsheetApp.getUi().alert("OAuth resetnutý. Spusť znovu autorizaci.");
}

function getOAuthService() {
  return OAuth2.createService('google')
    .setAuthorizationBaseUrl('https://accounts.google.com/o/oauth2/auth')
    .setTokenUrl('https://oauth2.googleapis.com/token')
    .setClientId('CLIENT_ID')
    .setClientSecret('CLIENT_SECRET')
    .setCallbackFunction('authCallback')
    .setPropertyStore(PropertiesService.getScriptProperties())
    .setScope('openid email profile')
    .setParam('access_type', 'offline')
    .setParam('prompt', 'consent');
}

function authCallback(request) {
  const oauthService = getOAuthService();
  const isAuthorized = oauthService.handleCallback(request);
  if (isAuthorized) {
    Logger.log('Authorization successful!');
    return HtmlService.createHtmlOutput('Authorized, you may close this tab.');
  } else {
    Logger.log('Authorization denied');
    return HtmlService.createHtmlOutput('Authorization failed.');
  }
}

function authorize() {
  const oauthService = getOAuthService();
  if (!oauthService.hasAccess()) {
    const url = oauthService.getAuthorizationUrl();
    Logger.log("Go to: " + url);
    const html = HtmlService.createHtmlOutput(`<a href="${url}" target="_blank">Click here to authorize</a>`);
    SpreadsheetApp.getUi().showModalDialog(html, 'Google Login');
    Logger.log("hasAccess: " + getOAuthService().hasAccess());
  } else {
    Logger.log("Already authorized");
    SpreadsheetApp.getUi().alert("Already authorized");
  }
}

function getIdToken() {
  const service = getOAuthService();
  if (!service.hasAccess()) {
    throw new Error("User not authorized. Please run authorize() first.");
  }
  const idToken = service.getIdToken();
  if (!idToken) {
    throw new Error("Could not retrieve ID Token.");
  }
  Logger.log("ID Token: " + idToken);
  Logger.log("hasAccess in getIdToken: " + service.hasAccess());
  return idToken;
}

function getUserInfo() {
  const oauthService = getOAuthService();
  if (oauthService.hasAccess()) {
    const response = UrlFetchApp.fetch('https://www.googleapis.com/oauth2/v1/userinfo?alt=json', {
      headers: {
        Authorization: 'Bearer ' + oauthService.getAccessToken()
      }
    });
    const userInfo = JSON.parse(response.getContentText());
    Logger.log(userInfo);
    return userInfo;
  } else {
    Logger.log('No access yet. Please authorize first.');
    return 'No access yet. Please authorize first.';
  }
}

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

  const palette = [
    "#c8eb7f", "#ebbf7f", "#7f8feb", "#7febbc", "#a77feb",
    "#eb7fa7", "#7feb8f", "#d0eb7f", "#917feb", "#ebc37f"
  ];
  const uniqueTeamNames = teams.map(t => t.teamName);
  const teamColorMap = {};
  uniqueTeamNames.forEach((name, idx) => {
    teamColorMap[name] = palette[idx % palette.length];
  });

  const sheet = SpreadsheetApp.getActiveSpreadsheet().getActiveSheet();
  sheet.clear();
  sheet.appendRow([
    'First Name', 'Last Name', 'Team', 'Personal ID', 'Birth Date',
    'Email', 'Specialization', 'Level', 'Education Level', 'Expertises', 'Last Offered Price (CZK)', 'Hourly Rate', 'Daily Rate', 'Expert ID (Hidden)'
  ]);

  const headerRange = sheet.getRange(1, 1, 1, sheet.getLastColumn());
  headerRange.setFontWeight('bold');

  experts.forEach((expert, i) => {
    const expText = (groupedExpertise[expert.expertID] || []).join(', ');
    const price = latestPricing[expert.expertID] || '—';
    const teamsText = (groupedTeams[expert.expertID] || []).join(', ');

    const rowIndex = i + 2;
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

    const firstTeam = teamsText.split(',')[0]?.trim();
    const color = teamColorMap[firstTeam] || "#FFFFFF";
    sheet.getRange(i + 2, 1, 1, 3).setBackground(color);
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
        Logger.log(`Expert ${expertID} byl smazán (řádek ${rowNumber})`);
      } catch (e) {
        Logger.log(`Chyba při mazání experta na řádku ${rowNumber}: ${e}`);
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
        Logger.log(`Řádek ${rowNumber}: expertID nebylo vráceno.`);
      }
    } catch (e) {
      Logger.log(`Chyba na řádku ${rowNumber}: ${e}`);
    }
  });

  SpreadsheetApp.getUi().alert('Synchronizace dokončena!');
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

function sendToBackend(data) {
  const mutation = `
    mutation {
      securedAction(data: "${data}")
    }
  `;

  const response = fetchGraphQL(mutation);
  Logger.log("Response from securedAction: " + response);
}

function fetchGraphQL(query) {
  const idToken = getIdToken();

  Logger.log("Sending GraphQL query: " + query);

  const response = UrlFetchApp.fetch(GRAPHQL_ENDPOINT, {
    method: 'post',
    contentType: 'application/json',
    headers: {
      Authorization: `Bearer ${idToken}`,
    },
    payload: JSON.stringify({ query }),
    muteHttpExceptions: true,
  });

  const text = response.getContentText();
  Logger.log("📡 GraphQL response: " + text);

  const json = JSON.parse(text);
  if (json.errors) {
    Logger.log("GraphQL errors: " + JSON.stringify(json.errors));
    throw new Error("GraphQL error: " + JSON.stringify(json.errors));
  }

  return json.data;
}

function onOpen() {

  const ui = SpreadsheetApp.getUi();
  const oauthService = getOAuthService();

  if (!oauthService.hasAccess()) {
    SpreadsheetApp.getUi().alert("🔐 Přihlas se pomocí menu → 💼 Menu → 🔑 Autorizovat Google");
  }

  ui.createMenu('💼 Menu')
    .addItem('🔑 Autorizovat Google', 'authorize')
    .addItem('🔄 Načíst experty', 'loadExpertsToSheet')
    .addItem('⬆️ Synchronizovat změny', 'syncAllRowsToBackend')
    .addItem(' Reset Auth', 'resetOAuth')
    .addToUi();
}
