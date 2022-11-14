db = db.getSiblingDB('mydb');

db.createCollection('roles');

db.roles.insertMany([
                                   { name: "ROLE_USER" },
                                   { name: "ROLE_MODERATOR" },
                                   { name: "ROLE_ADMIN" },
                                ]);
