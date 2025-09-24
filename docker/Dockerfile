# Use Node LTS (Debian-based) instead of Alpine
FROM node:18

WORKDIR /admin
ENV TZ="UTC"

# העתק package.json ו-yarn.lock
COPY package.json yarn.lock ./

# התקנת כל התלותות כולל devDependencies
RUN yarn install --frozen-lockfile

# העתקת tsconfig.json ושאר הקוד
COPY tsconfig.json ./
COPY src ./src
COPY prisma ./prisma
COPY public ./public   # אם יש
COPY other_dirs ./other_dirs  # אם יש

# התקנת TypeScript גלובלי
RUN npm i -g typescript

# בניית האפליקציה
RUN yarn build
RUN npx prisma generate

ENV ADMIN_JS_SKIP_BUNDLE="true"

# חשיפת פורט
EXPOSE 3000

# הרצת האפליקציה
CMD ["sh", "-c", "yarn prisma migrate dev --schema prisma/schema.prisma && yarn start"]
