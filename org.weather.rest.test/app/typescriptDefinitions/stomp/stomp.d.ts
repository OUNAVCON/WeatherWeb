// Type definitions for stomp.js
// Custom type definition, not complete.

interface Stomp {
    over(socketType:any) : Client;
}

interface StompMessage {
    body : string;
}

interface HeartBeatConfig {
    incoming : number;
    outgoing : number;
}

interface StompError {
    headers : ErrorHeader;
}

interface ErrorHeader {
    contentLength : string;
    contentType : string;
    message : string;
    version : string;
}

interface Client {
    connect(user: string, password : string, onConnect: () => any, onError : (error:StompError) => any, path : string);
    disconnect();
    subscribe(url : string, handler: (message : StompMessage) => any);
    send(url : string, headers: {[key:string] : any}, body:any);
    heartbeat : HeartBeatConfig;
}

declare var Stomp : Stomp;