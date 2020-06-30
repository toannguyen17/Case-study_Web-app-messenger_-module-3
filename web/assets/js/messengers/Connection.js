
var Connection = function () {
    this.url = 'ws://localhost/websocket';
    this.obj = null;
}

Connection.prototype.connect = function () {
    console.log("get Connect websocket....");
    if ('WebSocket' in window) {
        this.socket = new WebSocket(this.url);
    } else if ('MozWebSocket' in window) {
        this.socket = new MozWebSocket(this.url);
    } else {
        console.log('Error: WebSocket is not supported by this browser.');
        return;
    }

    this.socket.onopen    = this._onSocketConnect;
    this.socket.onclose   = this._onSocketDisconnect;
    this.socket.onmessage = this._onSocketData;
    this.socket.onerror   = this._onSocketError;
}

Connection.prototype._decodeMessage = function(message) {
    return JSON.parse(message)
}

Connection.prototype._encodeMessage = function(message) {
    return JSON.stringify(message)
}

Connection.prototype._onSocketConnect = function() {
    console.log("Cconnect.....");
}

Connection.prototype._onSocketDisconnect = function() {
    console.log("Disconnect.....");
    //AppMessenger.connection.connect();
}

Connection.prototype._onSocketError = function(message) {
    console.log(message);
}

Connection.prototype._onSocketData = function(message) {
    let data = message.data;
    AppMessenger.onmessage(data);
}


