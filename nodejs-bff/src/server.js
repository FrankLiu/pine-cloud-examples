'use strict';

const express = require('express');
const rp = require('request-promise');
const app = express();
const Eureka = require('eureka-js-client').Eureka;
const eurekaUtil = require('./eureka-util');
const eurekaConf = require('./config/eureka');

const eurekaClient = new Eureka(eurekaConf);
eurekaClient.logger.level('debug');
eurekaClient.start(function(error){
  console.log(error || 'complete');

});

app.get('/compute/add', function(req, res) {
    let computeServer = eurekaUtil.getComputeRootUrl(eurekaClient);
    let a = req.query.a || 0;
    let b = req.query.b || 0;
    rp(computeServer + "/add?a="+a+"&b="+b)
    .then(function(result) {
        res.send(result);
    })
    .catch(function(err) {
        console.log("compute service eerror: ", err);
    });
});

app.get('/books', function (req, res) {
//    var books = [];
//    books.push({ bookname: 'Nodejs Web Development', author: 'David Herron' });
//    books.push({ bookname: 'Mastering Web Application Development with Express ', author: 'Alexandru Vlăduțu' });
//    console.log('-----------')
//    res.json(books);

    let bookServer = eurekaUtil.getBookRootUrl(eurekaClient);
    console.log()
    rp(bookServer + "/books")
    .then(function (data) {
        res.json(data);
    })
    .catch(function (err) {
        console.log("book service error: ", err);
    });
});

app.get('/info',function(req, res){
    res.json({name:'node-bff service',status:'ok'});
} );
app.get('/health',function(req, res){
  res.json({name:'node-bff service',status:'ok'});
} );
app.listen(4001, function () {
    console.log('Node-BFF Service app listening on port 4001!')
});




