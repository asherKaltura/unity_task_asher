# Use Node LTS (Debian-based) instead of Alpine
FROM node:18

WORKDIR /admin
ENV TZ="UTC"

COPY package.json yarn.lock ./

RUN yarn install --frozen-lockfile

COPY tsconfig.json ./
COPY src ./src
COPY prisma ./prisma
COPY public ./public

RUN npm i -g typescript

RUN yarn build
RUN npx prisma generate


ENV ADMIN_JS_SKIP_BUNDLE="true"
ENV HOST=0.0.0.0   # <-- Add this to bind Node to all network interfaces

EXPOSE 3000


CMD ["sh", "-c", "yarn prisma migrate dev --schema prisma/schema.prisma && yarn start --hostname 0.0.0.0"]
