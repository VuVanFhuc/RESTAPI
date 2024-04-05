const mongoose = require('mongoose');
// code 4/4/2024
const local = "mongodb+srv://vuvanphuc:Nhutren@monggodb.flkdrwz.mongodb.net/server_3";

const connect = async () => {
    try {
        await mongoose.connect(local);
        console.log('Connect success');
    } catch (error) {
        console.error('Connection to MongoDB failed:', error);
    }
}

module.exports = { connect };
