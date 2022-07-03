

const express = require("express");
const app = express();
const AWS = require("aws-sdk");


const bodyParser = require("body-parser");
app.use(bodyParser.json());
app.use(
  bodyParser.urlencoded({
    extended: true
  })
);

 
// make all the files in 'public' available
// https://expressjs.com/en/starter/static-files.html
app.use(express.static("public"));
 
// https://expressjs.com/en/starter/basic-routing.html
app.get("/", (request, response) => {
  response.send("OK Express");
});
 
app.get("/test", (request, response) => {
  response.send("OK Express");
});

app.get("/listTables", (request, response) => {
  
  AWS.config.update({region:'us-east-1'});
  
  var dynamodb = new AWS.DynamoDB({apiVersion: '2012-08-10'});
  
  var params = {};
  
  dynamodb.listTables(params,function(err,data){
    
    if(err){
      console.log(err);
    }else{
      response.json(data);
    }
    
  });
  
});

app.get("/insert", (request, response) => {
  
  AWS.config.update({region:'us-east-1'});
  
  var dynamodb = new AWS.DynamoDB({apiVersion: '2012-08-10'});
  
  var client = new AWS.DynamoDB.DocumentClient();
  
  var email = request.query.email;
  var nome = request.query.nome;
  var valor = request.query.valor;
  
  var params = {
  TableName : 'clientes',
  Item: {
       email: email,
       nome: nome,
       data_nascimento: '20/07/1900',
       salario: valor,
       idade: 30
    }
  };
  
  client.put(params, function(err, data) {
    if(err){
      console.log(err);
    }else{
      response.json(data);
    }
  });
  
});
 
app.get("/update", (request, response) => {
  
  AWS.config.update({region:'us-east-1'});
  
  var dynamodb = new AWS.DynamoDB({apiVersion: '2012-08-10'});
  
  var client = new AWS.DynamoDB.DocumentClient();
  
  var email = request.query.email;
  var nome = request.query.nome;
  var valor = request.query.valor;
  
  var params = {
    TableName : 'clientes',
    Key: { 
        email: email,
        nome: nome
       },
    UpdateExpression: 'set #s = :y',
    ConditionExpression: 'idade > :x',
    ExpressionAttributeNames: {
      '#s' : 'salario'
    },
    ExpressionAttributeValues: {
      ':y':valor,
      ':x':21
    }
  };
  
  client.update(params, function(err, data) {
    if(err){
      console.log(err);
    }else{
      response.send(data);
    }
  });
  
});

app.get("/excluir", (request, response) => {
  
  AWS.config.update({region:'us-east-1'});
  
  var dynamodb = new AWS.DynamoDB({apiVersion: '2012-08-10'});
  
  var client = new AWS.DynamoDB.DocumentClient();
  
  var email = request.query.email;
  var nome = request.query.nome;
 
  var params = {
  TableName : 'clientes',
    Key: {
      email: email,
      nome: nome
    }
  };
  
  client.delete(params, function(err, data) {
    if(err){
      console.log(err);
    }else{
      response.send(data);
    }
  });
  
});

app.get("/recuperar", (request, response) => {
  
  AWS.config.update({region:'us-east-1'});
  
  var dynamodb = new AWS.DynamoDB({apiVersion: '2012-08-10'});
  
  var client = new AWS.DynamoDB.DocumentClient();
  
  var email = request.query.email;
  var nome = request.query.nome;
 
  var params = {
  TableName : 'clientes',
    Key: {
      email: email,
      nome: nome
    },
    AttributesToGet: [
      'email',
      'nome' ,
      'idade'
    ],
    ConsistentRead: false,
    ReturnConsumedCapacity: 'TOTAL'
  };
  
  client.get(params, function(err, data) {
    if(err){
      console.log(err);
    }else{
      response.send(data);
    }
  });
  
});

app.get("/busca", (request, response) => {
  
  AWS.config.update({region:'us-east-1'});
  
  var dynamodb = new AWS.DynamoDB({apiVersion: '2012-08-10'});
  
  var client = new AWS.DynamoDB.DocumentClient();
  
  var params = {
    TableName : 'clientes',
    //FilterExpression : 'idade > :idade',
    //ExpressionAttributeValues : {':idade' :  30 },
    IndexName: 'email-nome-index',
    Limit: '2',
    ExclusiveStartKey: {email: 'aaaa@xxx.com', nome: 'aaaa'},
  };
  
  client.scan(params, function(err, data) {
    if(err){
      console.log(err);
    }else{
      response.send(data);
    }
  });
  
});

app.get("/consultar", (request, response) => {
  
  AWS.config.update({region:'us-east-1'});
  
  var dynamodb = new AWS.DynamoDB({apiVersion: '2012-08-10'});
  
  var client = new AWS.DynamoDB.DocumentClient();
  
  var email = request.query.email;
  var idade = request.query.idade;
  
  var params = {
    TableName : 'clientes',
    IndexName: 'email-idade-index',
    KeyConditionExpression: 'email = :email and idade = :idade',
    ExpressionAttributeValues: {
      ':email': email,
      ':idade': parseInt(idade)
    }
  };
  
  client.query(params, function(err, data) {
    if(err){
      console.log(err);
    }else{
      response.send(data);
    }
  });
  
});


app.get("/lerlote", (request, response) => {
  
  AWS.config.update({region:'us-east-1'});
  
  var dynamodb = new AWS.DynamoDB({apiVersion: '2012-08-10'});
  
  var client = new AWS.DynamoDB.DocumentClient();
  
  var email = request.query.email;
  var nome = request.query.nome;
  
  var params = {
  RequestItems: {
      'clientes': {
        Keys: [
          {
             email: email,
             nome: nome
          }
        ]
      },
      'clientes_loja': {
        Keys: [
          {
             email: email,
             nome: nome
          }
        ]
      }
    }
  };

  
  client.batchGet(params, function(err, data) {
    if(err){
      console.log(err);
    }else{
      response.send(data);
    }
  });
  
});

app.get("/gravarlote", (request, response) => {
  
  AWS.config.update({region:'us-east-1'});
  
  var dynamodb = new AWS.DynamoDB({apiVersion: '2012-08-10'});
  
  var client = new AWS.DynamoDB.DocumentClient();
  
  var email = request.query.email;
  var nome = request.query.nome;
  
  var params = {
    RequestItems: {
      'clientes': [
        {
        PutRequest: {
            Item: { 
              email: email,
              nome: nome
            }
          }
        }
      ],
      'clientes_loja': [
        {
          PutRequest: {
            Item: { 
              email: email,
              nome: nome
            }
          }
        }
      ]
    }
  };

  
  client.batchWrite(params, function(err, data) {
    if(err){
      console.log(err);
    }else{
      response.send(data);
    }
  });
  
});


// listen for requests :)
const listener = app.listen(process.env.PORT, () => {
  console.log("Your app is listening on port " + listener.address().port);
});