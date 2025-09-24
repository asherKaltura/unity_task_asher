# Use Node LTS (Debian-based) instead of Alpine
FROM node:18

WORKDIR /admin
ENV TZ="UTC"

# העתק package.json ו-yarn.lock
COPY package.json yarn.lock ./

# התקנת כל התלותות + devDependencies
RUN yarn install --frozen-lockfile

# העתקת שאר הקוד
COPY . .

# התקנת TypeScript גלובלי
RUN npm i -g typescript

# בניית האפליקציה
RUN yarn build
RUN npx prisma generate

ENV ADMIN_JS_SKIP_BUNDLE="true"

# חשיפת פורט
EXPOSE 3000

# הרצת האפליקציה עם Prisma migrations
CMD yarn prisma migrate dev --schema prisma/schema.prisma && yarn start
