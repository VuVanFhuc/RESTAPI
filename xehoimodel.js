const mongoose = require('mongoose');
const carSchema = new mongoose.Schema({
    ten: {
        type: String,
        required: true,
    },
    namsx: {
        type: Number,
    },
    hang: {
        type: String,
        required: true,
    },
    gia: {
        type: Number
    }
});
const xehoimodel = new mongoose.model('car', carSchema);
module.exports=xehoimodel;