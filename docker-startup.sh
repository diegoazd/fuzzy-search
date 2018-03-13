#!/bin/bash

docker run -it --rm -d --name fuzzy-frontend -p 8080:8080 diegoazd/fuzzy-search-backend:1.0 
docker run -it --rm -d --name fuzzy-backend -p 3000:3000 diegoazd/fuzzy-search-frontend:1.0 
