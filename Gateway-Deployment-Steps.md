# GATEWAY DEPLOYMENT - EXACT STEPS

## Check Your GitHub Setup First:
**Option A:** Each service is a separate GitHub repository
**Option B:** All services are in one GitHub repository (monorepo)

---

## FOR OPTION A (Separate Repos):
**Step 1:** Go to Railway project dashboard
**Step 2:** Click "New Service" button
**Step 3:** Click "GitHub Repo"
**Step 4:** Find and click your Gateway repository
**Step 5:** Click "Deploy"
**Step 6:** Wait 2-3 minutes for deployment

---

## FOR OPTION B (Monorepo - All services in one repo):
**Step 1:** Go to Railway project dashboard
**Step 2:** Click "New Service" button
**Step 3:** Click "GitHub Repo"
**Step 4:** Find and click your ConnectLogs repository
**Step 5:** **BEFORE clicking Deploy** - Click "Configure"
**Step 6:** In "Root Directory" field, type: `Gateway`
**Step 7:** Click "Deploy"
**Step 8:** Wait 2-3 minutes for deployment

---

## AFTER DEPLOYMENT (Both Options):
**Step 9:** Click on the Gateway service that appeared
**Step 10:** Click "Variables" tab
**Step 11:** Add these variables:
   - Name: PORT, Value: 8080
   - Name: SPRING_PROFILES_ACTIVE, Value: prod

**Step 12:** Click "Settings" tab
**Step 13:** Copy the domain URL (your main app URL!)

---

## IF DEPLOYMENT FAILS:
1. Click Gateway service
2. Click "Deployments" tab
3. Click latest deployment
4. Check build logs for errors
5. Common fix: Ensure pom.xml is in Gateway folder

---

## WHICH OPTION DO YOU HAVE?
- **Separate repos:** You created 5 different GitHub repositories
- **Monorepo:** You pushed the entire ConnectLogs folder as one repository

**Check your GitHub account to see which setup you have, then follow the matching steps above.**