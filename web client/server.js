
var bus ={

}

var express = require('express');//import express and add it to var
var app = express();
var fs = require("fs");
//  var info={}
//var rawdata = fs.readFileSync('data.json'); //raw data
//var data = JSON.parse(rawdata); //data convered into jsonformat

//console.log(data);

var server = app.listen(3000, lis);
 function  lis() {
   console.log("server on ");
}

app.use(express.static('public'));

app.get('/add/:busid/:lat/:lon/:time',sendbus); //busid be the variable //?makes it optional
function sendbus(req,res){
  var data =req.params;
  var busid =data.busid;

  var lat=data.lat;
  var lon =data.lon;
  var time =data.time;

  //  "lat":[lat],
  //  "lon":[lon],
  //  "time":[time]

   bus[busid]={busid,lat,lon,time};
   //key value pair
//   bus[busid].lat.push("asd");
    var data = JSON.stringify(bus,null,2); //Stringify is opposite of parsing u need to covert data to raw texxt format to save it to .json file
   fs.writeFile('data.json',data,finished);

   function finished(err){
     console.log('data added');
   }
  res.send("this is "+busid+lat +lon+time);
}
app.get('/get/:id',getid);
function getid(req,res)
{ var rawdata =fs.readFileSync('data.json');
var data =JSON.parse(rawdata);
res.send(data[req.params.id]);
console.log(data[1].lat);

}

app.get('/getall',getall);
function getall(req,res) {
//  res.send(bus);
var rawdata = fs.readFileSync('data.json'); //raw data
var data = JSON.parse(rawdata); //data convered into jsonformat
res.send(data);
}




















app.get('/showdata',function(req,res){
  fs.readFile(__dirname+"/"+"data.json","utf8",function(err,data){
    console.log(data);
    res.end(data);
  });
})
//app.use(express.bodyParser());
app.get('/wines',function(req,res){

res.send([{name:'cigs1'},{name:'wine2'}]);

});

app.get('/wines/:id',function(req,res){
res.send({id:req.params.id, name:"the name",description:"description"});

});
//new code
/*
app.post('/receive',function (req,res){
var body= '';
filePath ='data.txt';
request.on('data',function(data){
body+=data;
});
request.on('end',function(){
fs.appendFile(filePath,body,fucntion(){response.end();});
});
});
*/
//codeend
