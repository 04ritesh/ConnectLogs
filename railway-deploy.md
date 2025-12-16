# Railway Deployment Guide

## Prerequisites
1. Push all services to GitHub repositories
2. Sign up for Railway account
3. Connect GitHub to Railway

## Service Deployment Order
1. Deploy Gateway service first
2. Deploy User-service, Experience-service, Tag-service, Likes-service
3. Configure environment variables

## Environment Variables for Each Service

### Gateway Service
```
PORT=8080
SPRING_PROFILES_ACTIVE=prod
```

### User Service
```
PORT=8081
SPRING_PROFILES_ACTIVE=prod
DATABASE_URL=${DATABASE_URL}
SPRING_DATASOURCE_URL=${DATABASE_URL}
```

### Experience Service
```
PORT=8082
SPRING_PROFILES_ACTIVE=prod
DATABASE_URL=${DATABASE_URL}
SPRING_DATASOURCE_URL=${DATABASE_URL}
```

### Tag Service
```
PORT=8083
SPRING_PROFILES_ACTIVE=prod
DATABASE_URL=${DATABASE_URL}
SPRING_DATASOURCE_URL=${DATABASE_URL}
```

### Likes Service
```
PORT=8084
SPRING_PROFILES_ACTIVE=prod
DATABASE_URL=${DATABASE_URL}
SPRING_DATASOURCE_URL=${DATABASE_URL}
```

## Deployment Options

### Option 1: Separate Services (Recommended)
- 5 separate Railway services + 1 database
- Cost: ~$4-6/month
- Best for production

### Option 2: Combined Deployment
- 1 Railway service running all apps
- Cost: ~$2-3/month
- Good for development/testing

## Step-by-Step Deployment

### Step 1: Prepare Code
1. Push each service to GitHub (separate repos or monorepo)
2. Ensure each service has proper application.properties

### Step 2: Railway Setup
1. Sign up for Railway
2. Choose **$5 Hobby Plan** (better than 30-day trial)
3. Connect GitHub account

### Step 3: Create Database
1. Click "New Project"
2. Select "Provision PostgreSQL"
3. Note the DATABASE_URL from Variables tab

### Step 4: Deploy Services (Do this 5 times)
1. Click "New Service" in same project
2. Select "GitHub Repo"
3. Choose your service repo
4. Railway auto-detects Spring Boot
5. Add environment variables:
   - PORT=808X (different for each)
   - SPRING_PROFILES_ACTIVE=prod
   - DATABASE_URL=${{Postgres.DATABASE_URL}}

### Step 5: Configure Gateway
1. Update Gateway service URLs to Railway domains
2. Each service gets URL like: https://servicename-production.up.railway.app

### Step 6: Test
1. Access Gateway URL
2. Test API endpoints
3. Check logs in Railway dashboard