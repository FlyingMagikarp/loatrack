FROM node:20
ENV API_BASE_URL = ""
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
EXPOSE 3000
CMD npm run dev