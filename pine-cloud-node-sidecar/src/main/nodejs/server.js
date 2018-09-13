const express = require('express');
const app = express();

app.get('/books', function (req, res) {
    var books = [];
    books.push({ bookname: 'Nodejs Web Development', author: 'David Herron' });
    books.push({ bookname: 'Mastering Web Application Development with Express ', author: 'Alexandru Vlăduțu' });
    console.log('-----------')
    res.json(books);
});

app.get('/info',function(req, res){
    res.json({name:'book service',status:'ok'});
});

app.get('/health',function(req, res){
  res.json({name:'book service',status:'ok'});
});

app.listen(3000, function () {
    console.log('Book Service app listening on port 3000!')
});

