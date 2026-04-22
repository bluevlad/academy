# Base image using Node.js 18 (LTS) on Alpine Linux for a small footprint
FROM node:18-alpine

# Set existing working directory
WORKDIR /app

# Copy package.json and package-lock.json first to leverage Docker cache
COPY package.json package-lock.json ./

# Install dependencies
# Using --legacy-peer-deps to avoid potential conflicts with older packages
RUN npm install --legacy-peer-deps

# Copy the rest of the application code
COPY . .

# Expose port 3000 for the Vite dev server
EXPOSE 3000

# Start the application with Vite dev server
CMD ["npm", "run", "dev"]
