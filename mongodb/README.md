
git show --stat --oneline HEAD

# for full details
git log --name-only


docker rmi pasedb:mongo && docker build -t pasedb:mongo .



docker run -ti --rm --name mongo -p 27017:27017 -v /opt/pasedb/mongodb/db:/data/db2 pasedb:mongo




docker run -it --entrypoint 'mongod --bind_ip_all' --hostname MONGODB --name=MONGODB --net=bridge --expose=27017 mongo



docker run -ti --rm --name mongodb --hostname mongo.pasedb  -v /opt/pasedb/mongodb/db:/data/db pasedb:mongo

version: '3'
services:
    db:
        image: pasedb:db
        container_name: pasedb
        tty: true
        stdin_open: true
        restart: always

    ui:
        image: pasedb:ui
        container_name: pasedbui
        tty: true
        stdin_open: true
        links:
            - db
        ports:
            - 80:8080

#        networks:
#              pasedb_web:
#                aliases:
#                 - md
#              pasedb_dmz:
#                aliases:
#                 - md

#networks:
#    pasedb_web:
#        driver: bridge
#        ipam:
#            driver: default
#            config: [{subnet: 10.10.1.0/24}]
#    pasedb_dmz:
#        driver: bridge
#        ipam:
#            driver: default
#            config: [{subnet: 10.10.2.0/24}]
