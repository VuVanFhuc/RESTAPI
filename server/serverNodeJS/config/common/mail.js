var nodemailer = require("nodemailer");
const transporter = nodemailer.createTransport({
    service: "gmail",
    auth: {
        user: "phucvvph34858@fpt.edu.vn",
        pass: "mat khau moi"
    }
});
module.exports = transporter 