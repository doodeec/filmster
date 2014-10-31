/**
 * Created by dbartos on 30.10.2014.
 */

var express = require('express');
var data = {
    page1: require('./mock/page1.json'),
    page2: require('./mock/page2.json'),
    page3: require('./mock/page3.json'),
    page4: require('./mock/page4.json'),
    pageEmpty: require('./mock/pageEmpty.json')
};
var app = express();

app.get('/', function(req, res){
    res.send('hello world');
});

app.get('/movies', function(req, res){
    res.set('Content-Type', 'application/json');
    var page = req.param('page');

    if (page && page > 0 && page < 5) {
        res.send(data['page'+page]);
    } else {
        res.send(data['pageEmpty']);
    }
});

app.listen(9875, function() {
    console.log('API server listening on port 9875');
});