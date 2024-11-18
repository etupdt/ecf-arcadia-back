db.getSiblingDB('admin').auth(
    process.env.MONGO_INITDB_ROOT_USERNAME,
    process.env.MONGO_INITDB_ROOT_PASSWORD
);
db = db.getSiblingDB(process.env.MONGO_DB);
db.createUser({
    user: process.env.MONGO_USER,
    pwd: process.env.MONGO_PASSWORD,
    roles: ["readWrite"],
});
db.createCollection('animalStatistic');
db.animalStatistic.insertMany([
    {firstname: "Jumbo", date: (new Date(Date.now())).toISOString().split('T')[0], qty: 2},
    {firstname: "Jumbo", date: (new Date(Date.now() - 24*3600*1000)).toISOString().split('T')[0], qty: 3},
    {firstname: "Jumbo", date: (new Date(Date.now() - 2*24*3600*1000)).toISOString().split('T')[0], qty: 1},
    {firstname: "Jumbo", date: (new Date(Date.now() - 3*24*3600*1000)).toISOString().split('T')[0], qty: 2},
    {firstname: "Jumbo", date: (new Date(Date.now() - 5*24*3600*1000)).toISOString().split('T')[0], qty: 1},
    {firstname: "Jumbo", date: (new Date(Date.now() - 7*24*3600*1000)).toISOString().split('T')[0], qty: 1},
    {firstname: "Jumbo", date: (new Date(Date.now() - 8*24*3600*1000)).toISOString().split('T')[0], qty: 2},
]);
db.animalStatistic.insertMany([
    {firstname: "Gigi", date: (new Date(Date.now())).toISOString().split('T')[0], qty: 1},
    {firstname: "Gigi", date: (new Date(Date.now() - 24*3600*1000)).toISOString().split('T')[0], qty: 1},
    {firstname: "Gigi", date: (new Date(Date.now() - 3*24*3600*1000)).toISOString().split('T')[0], qty: 2},
    {firstname: "Gigi", date: (new Date(Date.now() - 5*24*3600*1000)).toISOString().split('T')[0], qty: 1},
    {firstname: "Gigi", date: (new Date(Date.now() - 7*24*3600*1000)).toISOString().split('T')[0], qty: 2},
    {firstname: "Gigi", date: (new Date(Date.now() - 8*24*3600*1000)).toISOString().split('T')[0], qty: 2},
]);
db.animalStatistic.insertMany([
    {firstname: "Flash", date: (new Date(Date.now())).toISOString().split('T')[0], qty: 1},
    {firstname: "Flash", date: (new Date(Date.now() - 24*3600*1000)).toISOString().split('T')[0], qty: 1},
    {firstname: "Flash", date: (new Date(Date.now() - 2*24*3600*1000)).toISOString().split('T')[0], qty: 1},
    {firstname: "Flash", date: (new Date(Date.now() - 3*24*3600*1000)).toISOString().split('T')[0], qty: 2},
    {firstname: "Flash", date: (new Date(Date.now() - 5*24*3600*1000)).toISOString().split('T')[0], qty: 2},
    {firstname: "Flash", date: (new Date(Date.now() - 7*24*3600*1000)).toISOString().split('T')[0], qty: 2},
    {firstname: "Flash", date: (new Date(Date.now() - 8*24*3600*1000)).toISOString().split('T')[0], qty: 1},
]);
db.animalStatistic.insertMany([
    {firstname: "Coco", date: (new Date(Date.now())).toISOString().split('T')[0], qty: 1},
    {firstname: "Coco", date: (new Date(Date.now() - 24*3600*1000)).toISOString().split('T')[0], qty: 3},
    {firstname: "Coco", date: (new Date(Date.now() - 3*24*3600*1000)).toISOString().split('T')[0], qty: 4},
    {firstname: "Coco", date: (new Date(Date.now() - 4*24*3600*1000)).toISOString().split('T')[0], qty: 3},
    {firstname: "Coco", date: (new Date(Date.now() - 7*24*3600*1000)).toISOString().split('T')[0], qty: 4},
    {firstname: "Coco", date: (new Date(Date.now() - 8*24*3600*1000)).toISOString().split('T')[0], qty: 5},
]);
db.animalStatistic.insertMany([
    {firstname: "Croco", date: (new Date(Date.now())).toISOString().split('T')[0], qty: 7},
    {firstname: "Croco", date: (new Date(Date.now() - 24*3600*1000)).toISOString().split('T')[0], qty: 2},
    {firstname: "Croco", date: (new Date(Date.now() - 3*24*3600*1000)).toISOString().split('T')[0], qty: 2},
    {firstname: "Croco", date: (new Date(Date.now() - 5*24*3600*1000)).toISOString().split('T')[0], qty: 2},
    {firstname: "Croco", date: (new Date(Date.now() - 7*24*3600*1000)).toISOString().split('T')[0], qty: 3},
    {firstname: "Croco", date: (new Date(Date.now() - 8*24*3600*1000)).toISOString().split('T')[0], qty: 3},
]);
