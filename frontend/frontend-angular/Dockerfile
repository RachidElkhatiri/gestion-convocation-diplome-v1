FROM node:20-alpine AS builder
WORKDIR /app
COPY package*.json ./
RUN npm ci
COPY . .
RUN npm run build \
    && if [ -d /app/dist/frontend-angular/browser ]; then cp -R /app/dist/frontend-angular/browser /app/dist-out; else cp -R /app/dist/frontend-angular /app/dist-out; fi

FROM nginx:1.27-alpine
COPY nginx/default.conf /etc/nginx/conf.d/default.conf
RUN rm -rf /usr/share/nginx/html/*
COPY --from=builder /app/dist-out/. /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
