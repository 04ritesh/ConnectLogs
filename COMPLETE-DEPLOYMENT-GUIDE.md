# COMPLETE RAILWAY DEPLOYMENT GUIDE
## Follow these steps EXACTLY - No questions needed!

### PART 1: RAILWAY ACCOUNT SETUP

**Step 1:** Go to https://railway.app
**Step 2:** Click "Start a New Project"
**Step 3:** Click "Login with GitHub" 
**Step 4:** Authorize Railway to access your GitHub
**Step 5:** You'll see pricing options - Click "$5/month Hobby Plan"
**Step 6:** Add payment method (required even for free credits)

### PART 2: CREATE DATABASE FIRST

**Step 7:** Click "New Project" (big purple button)
**Step 8:** Click "Provision PostgreSQL" (database icon)
**Step 9:** Wait 30 seconds for database to deploy
**Step 10:** Click on the PostgreSQL service that appeared
**Step 11:** Click "Variables" tab
**Step 12:** Copy the DATABASE_URL value (starts with postgresql://)
**Step 13:** Keep this tab open - you'll need this URL

### PART 3: DEPLOY USER SERVICE

**Step 14:** Go back to project dashboard (click project name at top)
**Step 15:** Click "New Service" button
**Step 16:** Click "GitHub Repo"
**Step 17:** Find and click your User-service repository
**Step 18:** Click "Deploy" 
**Step 19:** Wait 2-3 minutes for deployment
**Step 20:** Click on the User-service that appeared
**Step 21:** Click "Variables" tab
**Step 22:** Click "New Variable" and add:
   - Name: PORT
   - Value: 8081
**Step 23:** Click "New Variable" and add:
   - Name: SPRING_PROFILES_ACTIVE  
   - Value: prod
**Step 24:** Click "New Variable" and add:
   - Name: DATABASE_URL
   - Value: ${{Postgres.DATABASE_URL}}
**Step 25:** Click "Settings" tab
**Step 26:** Copy the domain URL (like: user-service-production.up.railway.app)
**Step 27:** Save this URL in notepad

### PART 4: DEPLOY EXPERIENCE SERVICE

**Step 28:** Go back to project dashboard
**Step 29:** Click "New Service" button
**Step 30:** Click "GitHub Repo"
**Step 31:** Find and click your Experience-service repository
**Step 32:** Click "Deploy"
**Step 33:** Wait 2-3 minutes for deployment
**Step 34:** Click on Experience-service
**Step 35:** Click "Variables" tab
**Step 36:** Add these 3 variables (same as User service):
   - PORT: 8082
   - SPRING_PROFILES_ACTIVE: prod
   - DATABASE_URL: ${{Postgres.DATABASE_URL}}
**Step 37:** Copy domain URL from Settings tab
**Step 38:** Save this URL in notepad

### PART 5: DEPLOY TAG SERVICE

**Step 39:** Repeat steps 28-38 but:
   - Choose Tag-service repository
   - Use PORT: 8083
   - Save the domain URL

### PART 6: DEPLOY LIKES SERVICE

**Step 40:** Repeat steps 28-38 but:
   - Choose Likes-service repository  
   - Use PORT: 8084
   - Save the domain URL

### PART 7: DEPLOY GATEWAY (LAST)

**Step 41:** Go back to project dashboard
**Step 42:** Click "New Service" button
**Step 43:** Click "GitHub Repo"
**Step 44:** Find and click your Gateway repository
**Step 45:** Click "Deploy"
**Step 46:** Wait 2-3 minutes for deployment
**Step 47:** Click on Gateway service
**Step 48:** Click "Variables" tab
**Step 49:** Add these variables:
   - PORT: 8080
   - SPRING_PROFILES_ACTIVE: prod
**Step 50:** Click "Settings" tab
**Step 51:** Copy Gateway domain URL
**Step 52:** This is your main application URL!

### PART 8: FINAL TESTING

**Step 53:** Open Gateway URL in browser
**Step 54:** Add /actuator/health to URL (e.g., https://gateway-xxx.railway.app/actuator/health)
**Step 55:** Should see: {"status":"UP"}
**Step 56:** Test each service:
   - Gateway-URL/api/users/health
   - Gateway-URL/api/experiences/health  
   - Gateway-URL/api/tags/health
   - Gateway-URL/api/likes/health

### TROUBLESHOOTING

**If service fails to start:**
1. Click service name
2. Click "Deployments" tab
3. Click latest deployment
4. Check logs for errors
5. Most common issue: Wrong PORT variable

**If database connection fails:**
1. Check DATABASE_URL variable is exactly: ${{Postgres.DATABASE_URL}}
2. Ensure PostgreSQL service is running

**If Gateway can't reach services:**
1. Check all services are deployed and running
2. Verify each service has correct PORT variable
3. Check service URLs are accessible

### FINAL CHECKLIST
✅ PostgreSQL database running
✅ User-service deployed (PORT: 8081)
✅ Experience-service deployed (PORT: 8082)  
✅ Tag-service deployed (PORT: 8083)
✅ Likes-service deployed (PORT: 8084)
✅ Gateway deployed (PORT: 8080)
✅ All services have DATABASE_URL variable
✅ Gateway URL works
✅ Health endpoints respond

### YOUR DEPLOYED URLS
- Main App: [Gateway URL from Step 51]
- User Service: [URL from Step 26]  
- Experience Service: [URL from Step 37]
- Tag Service: [URL from Step 39]
- Likes Service: [URL from Step 40]

**DEPLOYMENT COMPLETE! 🎉**

**Monthly Cost:** ~$4-5 with $5 credit = Almost FREE!