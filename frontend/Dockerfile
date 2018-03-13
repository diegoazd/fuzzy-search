FROM node:8
EXPOSE 3000

WORKDIR /app
ADD ../fuzzy-search /app
RUN chmod +x startup.sh

ENTRYPOINT ["npm", "run", "start"]

