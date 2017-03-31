var mongoose = require('mongoose');

mongoose.Promise =global.Promise;;

//mongo connectnon 

mongoose.connect('mongodb://localhost/myapi')
    .then(() => console.log('connection successful'))
    .catch((err) => console.error(err));

