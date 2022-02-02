FROM node:14-alpine as build-step
RUN mkdir -p /app
WORKDIR /app
COPY package.json /app
RUN npm install
COPY . /app
RUN npm run build

FROM nginx:1.17-alpine
COPY --from=build-step /app/dist/product-catalog /usr/share/nginx/html
