# 🚀 Starting the RaqetIS Backend

To run the backend locally, follow the steps below. 
This will start a PostgreSQL database using Docker 
and launch the Spring Boot application in development mode.

### 🐳 Start PostgreSQL with Docker Compose

Use Docker Desktop to run the PostgreSQL database locally. 

### ⚙️ Set Active Profile to development

The application uses Spring profiles to switch between environments.
Use the `development` profile. 

### ▶️ Run the Spring Boot Application

Just `Shift+F10` or

```bash
./gradlew bootRun --args='--spring.profiles.active=development'
```

You can then access the GraphQL playground at:

```
http://localhost:8443/graphiql?path=/graphql
```

# 📱 Running the Mobile App (Expo + Expo Go)

The mobile part of the project is built using [Expo](https://expo.dev/) 
and can be easily run on your phone using the **Expo Go** app.

### ✅ Set up the IP address for local development

In the file `tsconfig.ts` replace the base GraphQL API endpoint with **your machine’s local IP address**:

```
 "http://<your_ip_address>:8443/graphql";
```

### ✅ Start the Expo development server

```
npx expo start
```

This will open the Expo DevTools in your browser.
Press `s` to run it in Expo Go (Mobile app)

### ✅ Test the API connection

You can verify the connection to the backend by using **Postman**: 

```http
POST http://<your_ap_address>:8443/graphql
Content-Type: application/json

{
  "query": "query expert { experts { firstName lastName personalID birthDate addressID contactID specialization educationLevel } }"
}
```

If the response is successful, the mobile app should also be able to communicate with the backend.

# 🔄 Google Sheets Integration with Backend

To enable Google Sheets to communicate with the local backend,
we use ngrok to expose the local server to the internet.

### 🧩 Start ngrok on backend port

```bash
ngrok http 8443
```

You’ll get a public HTTPS URL like: `https://abc1-22-33-44-55.ngrok-free.app`.
Copy this URL — it will be used in the Google Apps Script.

### ✍️ Paste ngrok URL into Google Apps Script

In your **Google Sheet**, open the **Apps Script editor** `(Extensions > Apps Script)`, 
and at the top of the script file, update this line:

```javascript
const GRAPHQL_ENDPOINT = 'https://ngrok_generated_url/graphql';
```

### ✅ Use the Sheet UI to sync

After saving the script, reload your Google Sheet.
You’ll see a new menu: `💼 Expert Sync`

From there, you can:

🔄 Load Experts from the backend

⬆️ Synchronize Changes made in the sheet (CRUD)