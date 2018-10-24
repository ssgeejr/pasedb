


docker rmi pasedb:mongo && docker build -t pasedb:mongo .



docker run -ti --rm --name mongo -p 27017:27017 -v /opt/pasedb/mongodb/db:/data/db2 pasedb:mongo
