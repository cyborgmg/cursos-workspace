const reqObject = {
    url: 'https://rhsso.enterprisetrn.corp/auth/realms/pre-prod/protocol/openid-connect/token',
    method: 'POST',
    header: ['Content-Type: application/x-www-form-urlencoded', 'Cookie: BIGipServerpool_rhsso.enterprisetrn.corp=4246622730.62753.0000'],
    body: {
        mode: 'urlencoded',
        urlencoded: [{
                "key": "client_id",
                "value": "performance",
                "type": "text",
                "enabled": true
            },
            {
                "key": "client_secret",
                "value": "8003d161-0933-4292-b748-7cb47a70ff0f",
                "type": "text",
                "enabled": true
            },
            {
                "key": "grant_type",
                "value": "client_credentials",
                "type": "text",
                "enabled": true
            },
        ]
    }
};
pm.sendRequest(reqObject, (err, res) => {
    console.log(res)
    var jsonData = JSON.parse(res.stream);
    postman.setEnvironmentVariable("mic_access_token_ppr", jsonData.access_token);
    console.log(postman.getEnvironmentVariable("mic_access_token_ppr"));
    pm.collectionVariables.set("variable_key", "variable_value");
});
var uuid = require('uuid');
postman.setEnvironmentVariable('guid', uuid.v4().replace(/-/g, ""));