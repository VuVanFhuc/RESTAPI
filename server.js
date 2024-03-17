const express = require('express');

const app = express();

const port = 3000;

app.listen(port, () => {

    console.log(`Example app listenning on port`);

});

const uri = 'mongodb+srv://phucvvph34858:HWt0ipMc9ALl1Cw3@monggodb.flkdrwz.mongodb.net/vuvanphuc';

const mongoose = require('mongoose');

const xehoimodel = require('./xehoimodel')

app.get('/', async (req, res) => {

    await mongoose.connect(uri);

    let cars =await xehoimodel.find();

    console.log(cars);

    res,send(cars)

});

//chức năng delete 

app.get('/xoa/:id',async(req,res)=>{

    await mongoose.connect(uri);

    let id =req.params.id;

    console.log(id);

    // xử lý lỗi khi id không đúng 

    await xehoimodel.deleteOne({_id:id});

    res.redirect('../')
});

// chức năng update

app.get('/update/:ten',async (req,res)=>{

    await mongoose.connect(uri);

    console.log('kết nối db thành công ');

    let tenxe =req.params.ten;

    console.log(tenxe);

    let tenxemoi = tenxe + 'sửa tên xe thành công thành xe 2024';

    await xehoimodel.updateOne({ten:tenxe},{ten:tenxemoi});

    let cars = await xehoimodel.find({});

    res.send(cars)
});
// chức năng add 

app.post('/add', async (req, res) => {
    await mongoose.connect(uri);

    try {
        // Lấy dữ liệu từ yêu cầu POST
        const { ten, namsx, hang, gia } = req.body;

        // Tạo một bản ghi mới
        const newCar = new xehoimodel({
            ten: ten,
            namsx: namsx,
            hang: hang,
            gia: gia
        });

        // Lưu bản ghi mới vào cơ sở dữ liệu
        await newCar.save();

        res.send("Thêm xe hơi mới thành công!");
    } catch (error) {
        res.status(500).send("Đã xảy ra lỗi khi thêm xe hơi mới: " + error);
    }
});
