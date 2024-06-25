db.getSiblingDB('admin').auth(
    process.env.MONGO_INITDB_ROOT_USERNAME,
    process.env.MONGO_INITDB_ROOT_PASSWORD
);
db = db.getSiblingDB('arcadia');
db.createUser({
    user: process.env.MONGO_USER,
    pwd: process.env.MONGO_PASSWORD,
    roles: ["readWrite"],
});
db.createCollection('animalStatistic');
