# Use Node LTS (Debian-based) instead of Alpine
FROM node:18

WORKDIR /admin
ENV TZ="UTC"


COPY package.json yarn.lock ./


RUN yarn install --frozen-lockfile


COPY tsconfig.json ./
COPY ./docker/src ./src
COPY ./docker/prisma ./prisma
COPY ./docker/public ./public


RUN npm i -g typescript


RUN yarn build
RUN npx prisma generate

ENV ADMIN_JS_SKIP_BUNDLE="true"


EXPOSE 3000


CMD ["sh", "-c", "yarn prisma migrate dev --schema prisma/schema.prisma && yarn start"]
