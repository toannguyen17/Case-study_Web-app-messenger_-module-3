
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

    this.socket.onopen    = this._onOpen;
    this.socket.onclose   = this._onClose;
    this.socket.onmessage = this._onMessage;
    this.socket.onerror   = this._onError;
}

Connection.prototype._decodeMessage = function(message) {
    return JSON.parse(message)
}


Connection.prototype._onOpen = function() {
    console.log("Cconnect.....");
}

Connection.prototype._onClose = function() {
    console.log("Disconnect.....");
    //AppMessenger.connection.connect();
}

Connection.prototype._onError = function(message) {
    console.log(message);
}

Connection.prototype._onMessage = function(message) {
    AppMessenger.onmessage(message.data);
}


