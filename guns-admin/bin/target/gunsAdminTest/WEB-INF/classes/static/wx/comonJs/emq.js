

var mqtt;  
var reconnectTimeout = 2000;  

function MQTTconnect() {  
    mqtt = new Paho.MQTT.Client(  
    		mqttWsHost,  
    		Number(mqttWsPort),  
    		custNo);  
    var options = {  
        timeout: 3,  
        
        useSSL:true,
        onSuccess: onConnect,  
        onFailure: function (message) {  alert(message.errorMessage);
            $('#status').val("Connection failed: " + message.errorMessage + "Retrying");  
           // setTimeout(MQTTconnect, reconnectTimeout);  
        }  
    };  

    mqtt.onConnectionLost = onConnectionLost;  
    mqtt.onMessageArrived = onMessageArrived;  

       
    options.userName = mqttUserName;  
    options.password = mqttPassword;  
    
   // console.log("Host="+ host + ", port=" + port + " TLS = " + useTLS + " username=" + username + " password=" + password);  
    mqtt.connect(options);  
}  

function sendMsg(msg){
	var message = new Paho.MQTT.Message(msg);  
    message.destinationName = recvSuffix+appointorCustNo;  
    message.qos=0;  
    mqtt.send(message);  
}
function onConnect() {     
	//alert(recvSuffix+custNo);
    // Connection succeeded; subscribe to our topic  
    mqtt.subscribe(recvSuffix+custNo, {qos: 0});      
}  

function onConnectionLost(response) { // alert("onConnectionLost");
    //setTimeout(MQTTconnect, reconnectTimeout);  
    
};  

function onMessageArrived(message) {  

    var topic = message.destinationName;  
    var payload = message.payloadString;  

    alert(payload);
};  