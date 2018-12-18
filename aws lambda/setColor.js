// Create the DynamoDB service object
    
    // Load the AWS SDK for Node.js
var AWS = require('aws-sdk');
// Set the region 

exports.handler = (event, context, callback) => {
    // TODO implement
    

var ddb = new AWS.DynamoDB({});
var data1= new Date();
var data2= data1.toString();
var params = {
  
  
  Item: {
    'vermell' : {N: event.vermell},
    'blau': {N: event.blau},
    'verd' : {N: event.verd},
    'clear' : {N: event.clear},
    'date' : {S: data2} ,
  },
  TableName: 'colors'
};

// Call DynamoDB to add the item to the table
ddb.putItem(params, function(err, data) {
  if (err) {
   
     callback(null, err);
  } else {
    callback(null, 'succeess');
  }
});
   
};