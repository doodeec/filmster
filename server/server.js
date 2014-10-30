/**
 * Created by dbartos on 30.10.2014.
 */

var express = require('express');
var data = {
    page1: require('./mock/page1.json'),
    page2: require('./mock/page2.json'),
    page3: require('./mock/page3.json')
};
var app = express();

app.get('/', function(req, res){
    res.send('hello world');
});

app.get('/movies', function(req, res){
    res.set('Content-Type', 'application/json');
    var page = req.param('page');

    if (page && page > 0 && page < 4) {
        res.send(data['page'+page]);
    } else {
        res.send(data['page3']);
    }
});

app.listen(9875);